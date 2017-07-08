/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Alumno;
import dao.entidad.Usuario;

/**
 *
 * @author Miauuu
 */
public interface IUserDAO {
    
    public abstract Usuario ValidarUsuario(String usuario,String clave);
    
}
