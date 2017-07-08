/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Persona;

/**
 *
 * @author Miauuu
 */
public interface IPersonaDAO {
    
    public abstract void registrarPersona(Persona persona);
    
    public abstract void actualizarPersona(Persona persona);
    
    public abstract Persona obtenerPersona(int id);
    public abstract boolean correoNoExiste(String correo);
    
    public abstract byte[] obtenerImagen(int idPersona);
}
