/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Perfil;

/**
 *
 * @author Miauuu
 */
public interface IPerfilDAO {
    
    public abstract Perfil buscarPerfil(int idPerfil);
    
}
