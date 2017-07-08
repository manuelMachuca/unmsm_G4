package modelo.seguridad;

import dao.DAOFactory;
import dao.entidad.Usuario;
import dao.interfaces.IUserDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;

public class SValidarUsuario {
	
	
	 IUserDAO iuserDAO = DAOFactory.getInstance().getUserDAO();
	 
	public Usuario validarUsuario(String usuario, String clave){
		try{
		Usuario user = iuserDAO.ValidarUsuario(usuario,clave);
		
		return user;
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("Error al recuperar",pe);
		}
	}
	
}
