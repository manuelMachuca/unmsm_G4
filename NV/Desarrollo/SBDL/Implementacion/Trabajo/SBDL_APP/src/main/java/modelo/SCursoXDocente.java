/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Comentario;
import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import dao.entidad.ValorCursoXDocente;
import dao.interfaces.IComentarioDAO;
import dao.interfaces.ICursoDocenteDAO;
import dao.interfaces.ICursoFavoritoDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;
import modelo.alumno.SCursosFavoritos;
import modelo.alumno.SValorCursoXDocente;

/**
 *
 * @author Manuel Machuca
 */
public class SCursoXDocente {
    
    ICursoDocenteDAO iCursoDocenteDAO = DAOFactory.getInstance().getCursoDocenteDAO();
	IComentarioDAO iComentarioDAO = DAOFactory.getInstance().getComentarioDAO();
    SCursoXDocente sCursoXDocente;
    AlumnoService alumnoService;
    SValorCursoXDocente sValorCursoXDocente;
    ICursoFavoritoDAO iCursoFavoritoDAO = DAOFactory.getInstance().getCursoFavoritoDAO();
	
    public ArrayList<CursoXDocente> listarCurso_Profesor(String valor){
    	try{
    		System.out.println("LISTA BSUCADA CON ESTE PARAMETRO:" + valor);
    		
    		ArrayList<CursoXDocente> cursoXDocenteList = iCursoDocenteDAO.listarCurso_Profesor(valor);
    		
    		for (CursoXDocente cursoXDocente : cursoXDocenteList) {
    			agregarPromedioValoracion(cursoXDocente);
        	}
    		
    		return cursoXDocenteList;
    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DESCONOCIDO",pe);
    	}
    	
    
    }
    
    public CursoXDocente buscarCursoDocenteXIds(Integer idCurso,Integer idDocente){
    	try{
    		
    		CursoXDocente cursoXDocente = iCursoDocenteDAO.buscarCursoDocenteXIds(idCurso, idDocente);    		
    		return cursoXDocente;
    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DESCONOCIDO ",pe);
    	}
    	
    
    }
    public void valorarCurso(Integer idCurso,Integer idDocente,Integer adicional){
    	try{
    		
    		iCursoDocenteDAO.valorarCurso(idCurso, idDocente,adicional);    		
    		    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DESCONOCIDO ",pe);
    	}
    }
    public void actualizarValorarCurso(Integer idCurso,Integer idDocente,Integer antiguo,Integer adicional){
    	try{
    		    		
    		iCursoDocenteDAO.actualizarValorarCurso(idCurso,idDocente,adicional,antiguo);
    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DESCONOCIDO ",pe);
    	}
    }
    
    //Adicional para agregar el promedio de valoracion al curso docente
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
    
	public ArrayList<CursoXDocente> listaCursoProfe(Docente docente){
		return iCursoDocenteDAO.listaCursoProfe(docente);
	}
	
	public void anularCurso(CursoXDocente cursoXDocente){
		
		ArrayList<Comentario> listaComentario= new ArrayList<>();
		listaComentario = iComentarioDAO.obtenerlista(cursoXDocente.getId());
		
		iCursoFavoritoDAO.eliminarDeFavoritos(cursoXDocente.getCurso().getIdcurso(),cursoXDocente.getDocente().getIddocente());
		
		for(Comentario coment : listaComentario){
			iComentarioDAO.borrarComentario(coment);
		}
		
		
		iCursoDocenteDAO.anularCurso(cursoXDocente);

	}
        
}

