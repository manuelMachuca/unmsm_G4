/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.Alumno;
import dao.entidad.CursoFavorito;
import dao.entidad.Usuario;

/**
 *
 * @author Miauuu
 */
public interface ICursoFavoritoDAO {
    
    public abstract ArrayList<CursoFavorito> listarCursosFavoritos(Integer idAlumno);
    public abstract void eliminarDeFavoritos(Integer idAlumno,Integer idCurso,Integer idDocente);
    public abstract void insertarFavorito(CursoFavorito cursoFavorito);
    public abstract boolean validarRegistro(CursoFavorito cursoFavorito);

    public abstract void eliminarDeFavoritos(Integer idCurso,Integer idDocente);
    
    
}
