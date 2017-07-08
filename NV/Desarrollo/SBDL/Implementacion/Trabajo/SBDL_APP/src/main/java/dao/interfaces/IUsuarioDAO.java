/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Usuario;

/**
 *
 * @author Miauuu
 */
public interface IUsuarioDAO {
    
    public abstract void agregarUsuario(Usuario usuario);
    
    public abstract Usuario obtenerUSuario(int idPers);
    
    public abstract void actualizarUSuario(Usuario user);
}
