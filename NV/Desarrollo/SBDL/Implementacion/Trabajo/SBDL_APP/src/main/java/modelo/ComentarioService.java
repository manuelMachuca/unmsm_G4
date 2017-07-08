package modelo;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Comentario;
import dao.entidad.CursoXDocenteId;
import dao.interfaces.IComentarioDAO;

public class ComentarioService {
	IComentarioDAO iComentarioDAO = DAOFactory.getInstance().getComentarioDAO();
	
	public void insertarComentario(Comentario comentario){
		iComentarioDAO.insertarComentario(comentario);
	}
	
	public ArrayList<Comentario> obtenerListaComentarios(CursoXDocenteId id){
		return iComentarioDAO.obtenerlista(id);
		
	}
	
}
