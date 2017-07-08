/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.alumno;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Alumno;
import dao.entidad.CursoXDocente;
import dao.entidad.Informaciondetalle;
import dao.entidad.InformaciondetalleId;
import dao.entidad.Persona;
import dao.entidad.Usuario;
import dao.entidad.ValorCursoXDocente;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.IInformacionDetalleDAO;
import dao.interfaces.IPerfilDAO;
import dao.interfaces.IPersonaDAO;
import dao.interfaces.IUsuarioDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;
import modelo.AlumnoService;
import modelo.SCursoXDocente;
import util.Constantes;

/**
 *
 * @author Manuel Machuca
 */
public class SInformacionDetalle {
    
    IInformacionDetalleDAO iinformacionDetalle= DAOFactory.getInstance().getInformacionDetalleDAO();
    IAlumnoDAO iAlumnoDAO  = DAOFactory.getInstance().getAlumnoDAO();
    SCursoXDocente sCursoXDocente;
    AlumnoService alumnoService;
    SValorCursoXDocente sValorCursoXDocente;
    
    
    public void insertarInformacionDetalle(Informaciondetalle informaciondetalle){
    	try{
    		iinformacionDetalle.insertarInformacionDetalle(informaciondetalle);
    		
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("Error DESCONOCIDO", pe);
    	} 
    }
    
    public boolean noExisteRegistrado(InformaciondetalleId id){
    	try{
    		boolean valor = iinformacionDetalle.noExisteRegistrado(id);
    				
    		return valor;
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("Error DESCONOCIDO", pe);
    	} 
    	
    }
    public ArrayList<Informaciondetalle> listarCursosInfoDetalleDeAlumno(Integer idPersona){
    	
    	try{
    		Alumno alumno = iAlumnoDAO.obtenerAlumnoDePersona(idPersona);
    		System.out.println(alumno);
	    	ArrayList<Informaciondetalle> lista = iinformacionDetalle.listarCursosInfoDetalleDeAlumno(alumno.getIdalumno());
	    	
	    	for (Informaciondetalle informaciondetalle : lista) {
				mostrarValoracion(informaciondetalle);
			}
	    	
	    	return lista;
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("Error DESCONOCIDO", pe);
    	} 
    }
    
    public void eliminarInfoDetalle(InformaciondetalleId idInfoDetalle){
    	try{
    		
	    	iinformacionDetalle.eliminarInfoDetalle(idInfoDetalle);
	    	
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("Error al eliminar", pe);
    	} 
    	
    }

    /**
     * Agrega las listas a informacion detalle
     * para las estrellas
     * @param cursoSeleccionado
     */
    public void mostrarValoracion(Informaciondetalle cursoSeleccionado){
		
    	sCursoXDocente = new SCursoXDocente();
		CursoXDocente cursoDocente = sCursoXDocente.buscarCursoDocenteXIds(cursoSeleccionado.getCurso().getIdcurso(),
				cursoSeleccionado.getDocente().getIddocente());
		
		alumnoService = new AlumnoService();
		Alumno alumno = alumnoService.buscarAlumno(cursoSeleccionado.getAlumno().getPersona().getIdpersona());
		
		sValorCursoXDocente = new SValorCursoXDocente();
		ValorCursoXDocente valoracionBuscada = sValorCursoXDocente.buscarValoracionDeAlumno(cursoDocente,alumno);
		
		ArrayList<Integer> listaStarSeleccionado = new ArrayList<>();
		ArrayList<Integer> listaStarNoSeleccionado = new ArrayList<>();
		    
		
		if(valoracionBuscada != null){
			for (int i = 0; i < valoracionBuscada.getValor(); i++) {
				listaStarSeleccionado.add(i+1);
			}
			for (int i = valoracionBuscada.getValor() ; i < 5; i++) {
				listaStarNoSeleccionado.add(i+1);
			}
		}else{
			for (int i = 0; i < 5; i++) {
				listaStarNoSeleccionado.add(i+1);
			}
		}	
		
		cursoSeleccionado.setSelect(listaStarSeleccionado);
		cursoSeleccionado.setNoSelect(listaStarNoSeleccionado);
	}
    
}
