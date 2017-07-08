/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.DAOFactory;
import dao.entidad.Persona;
import dao.interfaces.IPersonaDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;

/**
 *
 * @author Miauuu
 */
public class PersonaService {
	  IPersonaDAO ipersonaDAO = DAOFactory.getInstance().getPersonaDAO();
	  
    public boolean correoNoExiste(String correoElectronico){
    	try{
    		boolean valor = false;
    		valor = ipersonaDAO.correoNoExiste(correoElectronico);
    		return valor;
    	}catch(PersistenciaExcepcion pe){
    		throw new NegocioExcepcion("ERROR AL BUSCAR ALUMNO",pe);
    	}
    }
    
    public void actualizarPersona(Persona persona){
    	ipersonaDAO.actualizarPersona(persona);
    }
    
    public Persona obtenerPersona(int id){
    	return ipersonaDAO.obtenerPersona(id);
    }

    public byte[] obtenerImagen(int idPersona){
    	return ipersonaDAO.obtenerImagen(idPersona);
    }
}
