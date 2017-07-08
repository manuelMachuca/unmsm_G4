/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.DAOFactory;
import dao.entidad.Alumno;
import dao.entidad.Persona;
import dao.entidad.Ubigeo;
import dao.entidad.Usuario;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.IPerfilDAO;
import dao.interfaces.IPersonaDAO;
import dao.interfaces.IUsuarioDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;
import util.AplicacionUtil;
import util.Constantes;

/**
 *
 * @author Miauuu
 */
public class AlumnoService {
    
    IPersonaDAO ipersonaDAO = DAOFactory.getInstance().getPersonaDAO();
    IUsuarioDAO iusuarioDAO = DAOFactory.getInstance().getUsuarioDAO();
    IAlumnoDAO iAlumnoDAO = DAOFactory.getInstance().getAlumnoDAO();
    IPerfilDAO iPerfilDAO = DAOFactory.getInstance().getPerfilDAO();
    private Alumno alumno;
    
    
    public void registrarAlumno(Persona persona, Usuario usuario){
        
    	//instanciar alumno
        try{
	    	alumno = new Alumno();
	    	
	        ipersonaDAO.registrarPersona(persona);
	        usuario.setPersona(persona);
	        usuario.setPerfil(iPerfilDAO.buscarPerfil(Constantes.PERFIL_ALUMNO));
	        
	        iusuarioDAO.agregarUsuario(usuario);
		        alumno.setPersona(persona);
	        iAlumnoDAO.registrarAlumno(alumno);
	        	        
        }catch(PersistenciaExcepcion pe){
        	throw new NegocioExcepcion("ERROR AL REGISTRAR ALUMNO",pe);
        }
    }
    
    public Alumno buscarAlumno(Integer idPersona){
    	try{
    		Alumno alumno = new Alumno();
    		alumno = iAlumnoDAO.obtenerAlumnoDePersona(idPersona);
    		return alumno;
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR AL BUSCAR ALUMNO",pe);
    	}
    }

    
}
