/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.Alumno;
import dao.entidad.CursoXDocente;
import dao.entidad.Persona;
import dao.entidad.ValorCursoXDocente;

/**
 *
 * @author Miauuu
 */
public interface IValorCursoDocenteDAO {
    
    public abstract ValorCursoXDocente buscarValoracionDeAlumno(CursoXDocente cursoDocente,Alumno alumno);
    public abstract ArrayList<ValorCursoXDocente> buscarTodasValoracionesDeCurso(CursoXDocente cursoXDocente);
    
    public abstract void registrarValoracion(CursoXDocente cursoDocente,Alumno alumno,Integer valor);
    
    public abstract void actualizarValoracion(CursoXDocente cursoDocente,Alumno alumno,Integer valor,Integer antiguo);
    
}
