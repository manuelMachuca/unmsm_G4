/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

import javax.persistence.PersistenceException;

import dao.DAOFactory;
import dao.entidad.Curso;
import dao.entidad.CursoXDocente;
import dao.entidad.CursoXDocenteId;
import dao.entidad.Docente;
import dao.entidad.NivelEstudio;
import dao.entidad.Persona;
import dao.entidad.Usuario;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.IDocenteDAO;
import dao.interfaces.INivelEstudioDAO;
import dao.interfaces.IPerfilDAO;
import dao.interfaces.IPersonaDAO;
import dao.interfaces.IUsuarioDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;
import util.Constantes;

/**
 *
 * @author Manuel Machuca
 */
public class DocenteService {
    
    IPersonaDAO ipersonaDAO = DAOFactory.getInstance().getPersonaDAO();
    IUsuarioDAO iusuarioDAO = DAOFactory.getInstance().getUsuarioDAO();
    IDocenteDAO iDocenteDAO = DAOFactory.getInstance().getDocenteDAO();
    IPerfilDAO iPerfilDAO = DAOFactory.getInstance().getPerfilDAO();
    INivelEstudioDAO iNivelEstudioDAO = DAOFactory.getInstance().getNivelEstudioDAO();
    private Docente docente;
    private CursoXDocente cursoXDocente;
    
    public void registrarDocente(Persona persona, Usuario usuario){
      try{
    	//instanciar alumno
        docente = new Docente();
        //registros en orden
        ipersonaDAO.registrarPersona(persona);
        usuario.setPersona(persona);
        usuario.setPerfil(iPerfilDAO.buscarPerfil(Constantes.PERFIL_DOCENTE));
        iusuarioDAO.agregarUsuario(usuario);
        docente.setPersona(persona);

        
        iDocenteDAO.registrarDocente(docente);
        
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR DE PERSISTENCIA", pe);
    	}catch (NegocioExcepcion pe) {
			throw new NegocioExcepcion(pe.getMessage(), pe);
		} catch (Exception e) {
			throw new NegocioExcepcion("ERROR DESCONOCIDO", e);
		}
      
    }
    
    public Docente obtenerDocente(int idPersona){
    	return iDocenteDAO.obtenerDocente(idPersona);
    }
    
    public void actualizarDocente(Docente docente,int idNE){
    	iDocenteDAO.actualizarDocente(docente,idNE);
    }
    
    public void asignarCurso(Docente docente,Curso curso){
    	
    	//SETEAR ID A CURSOXDOCENTEID
    	cursoXDocente = new CursoXDocente();
    	CursoXDocenteId cursoXDocenteId = new CursoXDocenteId();
    	cursoXDocenteId.setIdcurso(curso.getIdcurso());
    	cursoXDocenteId.setIddocente(docente.getIddocente());
    	
    	
    	//SETEAR VALORES A CURSOXDOCENTE	
    	cursoXDocente.setId(cursoXDocenteId);
    	cursoXDocente.setCurso(curso);
    	cursoXDocente.setDocente(docente);
    	cursoXDocente.setValoraciones(0);
    	
    	iDocenteDAO.asignarCurso(cursoXDocente);
    }
    
    
}
