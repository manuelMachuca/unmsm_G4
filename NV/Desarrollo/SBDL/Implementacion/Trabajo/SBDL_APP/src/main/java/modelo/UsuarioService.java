/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.DAOFactory;
import dao.entidad.Usuario;
import dao.interfaces.IUsuarioDAO;

/**
 *
 * @author Miauuu
 */
public class UsuarioService {
	
	IUsuarioDAO iUsuarioDAO = DAOFactory.getInstance().getUsuarioDAO();
	
	public Usuario obtenerUsuario(int idPers){
		return iUsuarioDAO.obtenerUSuario(idPers);
	}
    
	public void actualizarUsuario(Usuario user){
		iUsuarioDAO.actualizarUSuario(user);
	}
}
