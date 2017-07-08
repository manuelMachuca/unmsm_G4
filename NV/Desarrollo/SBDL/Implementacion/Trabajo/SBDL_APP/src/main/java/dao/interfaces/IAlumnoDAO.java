/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Alumno;
import dao.entidad.Persona;

/**
 *
 * @author Miauuu
 */
public interface IAlumnoDAO {
    
    public abstract void registrarAlumno(Alumno alumno);
    public abstract Alumno obtenerAlumnoDePersona(Integer idPersona);
    
    
}
