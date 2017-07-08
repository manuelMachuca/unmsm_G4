package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.Comentario;
import dao.entidad.CursoXDocenteId;

public interface IComentarioDAO {

	public abstract void insertarComentario(Comentario comentario);
	public abstract ArrayList<Comentario> obtenerlista(CursoXDocenteId id);
	public abstract void borrarComentario(Comentario comentario);
}
