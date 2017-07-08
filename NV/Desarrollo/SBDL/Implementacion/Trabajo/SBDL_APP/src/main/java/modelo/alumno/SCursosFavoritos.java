package modelo.alumno;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Alumno;
import dao.entidad.CursoFavorito;
import dao.entidad.CursoXDocente;
import dao.entidad.Informaciondetalle;
import dao.entidad.ValorCursoXDocente;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.ICursoFavoritoDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;

public class SCursosFavoritos {

	ICursoFavoritoDAO iCursoFavoritoDAO = DAOFactory.getInstance().getCursoFavoritoDAO();
	IAlumnoDAO iAlumnoDAO  = DAOFactory.getInstance().getAlumnoDAO();

    SValorCursoXDocente sValorCursoXDocente;
    
	public ArrayList<CursoFavorito> listarCursosFavoritos(Integer id){
		try{
    		Alumno alumno = iAlumnoDAO.obtenerAlumnoDePersona(id);
    		System.out.println(alumno);
	    	ArrayList<CursoFavorito> lista = iCursoFavoritoDAO.listarCursosFavoritos(alumno.getIdalumno());
	    	
	    	for (CursoFavorito cursoFavorito : lista) {
	    		agregarPromedioValoracion(cursoFavorito.getCursoXDocente());
			}
	    	
	    	return lista;
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("Error DESCONOCIDO", pe);
    	}	
	}
	
	public void eliminarDeFavoritos(Integer idAlumno,Integer idCurso,Integer idDocente){
		try{
			iCursoFavoritoDAO.eliminarDeFavoritos(idAlumno,idCurso, idDocente);
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR DESCONOCIDO",pe);
		}
		
	}
	
	public void agregarAFavoritos(CursoFavorito cursoFavorito){
		try{
			iCursoFavoritoDAO.insertarFavorito(cursoFavorito);
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR DESCONOCIDO",pe);
		}
		
	}
	public boolean validarRegistro(CursoFavorito cursoFavorito){
		try{
			boolean resultado = false;
			resultado = iCursoFavoritoDAO.validarRegistro(cursoFavorito);
			
			return resultado;
		}catch (PersistenciaExcepcion pe) {
			throw new NegocioExcepcion("ERROR EN VALIDACION",pe);
		}
	
	}
	
	public void agregarPromedioValoracion(CursoXDocente cursoXDocente){
    	try{
    		sValorCursoXDocente = new SValorCursoXDocente();
    		ArrayList<ValorCursoXDocente> valoracionBuscada = sValorCursoXDocente.buscarTodasValoracionesDeCurso(cursoXDocente);
    		ArrayList<Integer> selectTemp = new ArrayList<>();
    	    ArrayList<Integer> noSelectTemp = new ArrayList<>();
    		
    	    double promedio = 0; double suma = 0;
    	
    		for (ValorCursoXDocente valorCursoXDocente : valoracionBuscada) {
				suma = suma + valorCursoXDocente.getValor();
			}
    		
    		if(valoracionBuscada.size()==0) promedio = 0; 
    		else promedio = suma / valoracionBuscada.size();
    		
    		cursoXDocente.setPromedioValoracion(promedio);
    		
    		BigDecimal x = new BigDecimal(promedio);
    		int xEntero = x.setScale(0, RoundingMode.HALF_UP).intValue();
    		
    		if(xEntero > 0){
	    		for (int i = 0; i < xEntero; i++) {
	    			selectTemp.add(i+1);
				}
	    		for (int i = xEntero ; i < 5; i++) {
	    			noSelectTemp.add(i+1);
				}
    		}else{
    			for (int i = 0; i < 5; i++) {
    				noSelectTemp.add(i+1);
    			}
    		}   		
    		cursoXDocente.setSelect(selectTemp);
    		cursoXDocente.setNoSelect(noSelectTemp);
    		    		
    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DESCONOCIDO",pe);
    	}
    }
}
