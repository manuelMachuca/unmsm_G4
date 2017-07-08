package util;

import java.util.ArrayList;
import java.util.Date;

import dao.entidad.Comentario;
import dao.entidad.CursoXDocente;
import excepciones.NegocioExcepcion;
import modelo.ComentarioService;

public class ComentarioController {

	ComentarioService comentarioService;
	Comentario comentario;
	ArrayList<Comentario> listaComentarios;
	CursoXDocente cursoXDocente;
	String usuario;

	public ComentarioController() {
		comentario = new Comentario();
		listaComentarios = new ArrayList<>();
		cursoXDocente = new CursoXDocente();
		comentarioService = new ComentarioService();

	}

	public void listarComentarios() {
		listaComentarios = comentarioService.obtenerListaComentarios(cursoXDocente.getId());
	}

	public void insertarComentario() {
		int severidad = 1;
		String titulo;
		String descripcion;

		try {
		
			if (comentario.getComentario() == null) {
				AplicacionUtil.mostrarMensajeJSF(2, "NO HA ESCRITO NADA!","");
			} else {
				comentario.setCursoXDocente(cursoXDocente);
				comentario.setFecha(new Date());
				comentario.setUsuario(AplicacionUtil.obtenerUsuario().getUsername());
				comentarioService.insertarComentario(comentario);
				listarComentarios();
				comentario = new Comentario();

				titulo = "ÉXITO!";
				descripcion = "SE HA AGREGADO SU COMENTARIO";
				AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
			}
		} catch (NegocioExcepcion ne) {
			AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE AGREGO EL COMENTARIO");
		}
	}

	public ComentarioService getComentarioService() {
		return comentarioService;
	}

	public void setComentarioService(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public ArrayList<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public CursoXDocente getCursoXDocente() {
		return cursoXDocente;
	}

	public void setCursoXDocente(CursoXDocente cursoXDocente) {
		this.cursoXDocente = cursoXDocente;
		listarComentarios();
	}

}
