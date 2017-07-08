/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.entidad.Curso;
import dao.entidad.CursoXDocente;
import dao.entidad.Docente;

/**
 *
 * @author Miauuu
 */
public interface IDocenteDAO {
    
    public abstract void registrarDocente(Docente docente);
    
    public abstract Docente obtenerDocente(int idPersona);
    
    public abstract void actualizarDocente(Docente docente,int idNE);
    
    public abstract void asignarCurso(CursoXDocente cursoXDocente);
}
