/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.CursoXDocente;
import dao.entidad.Docente;

/**
 *
 * @author Miauuu
 */
public interface ICursoDocenteDAO {
    
    public abstract ArrayList<CursoXDocente> listarCurso_Profesor(String valorBuscado);
    public abstract CursoXDocente buscarCursoDocenteXIds(Integer idCurso,Integer idDocente);
    public abstract void valorarCurso(Integer idCurso,Integer idDocente,Integer adicional);
	public abstract ArrayList<CursoXDocente> listaCursoProfe(Docente docente);
	public abstract void anularCurso(CursoXDocente cursoXDocente);
    public abstract void actualizarValorarCurso(Integer idCurso,Integer idDocente,Integer adicional,Integer antiguo);
    
}
