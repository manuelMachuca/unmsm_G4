package controlador.alumno;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import dao.entidad.CursoFavorito;
import dao.entidad.Informaciondetalle;
import modelo.alumno.SCursosFavoritos;
import modelo.alumno.SInformacionDetalle;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;
import util.seguridad.Usuario;

public class CFavoritoAlumno implements Serializable{

	private ArrayList<CursoFavorito> listaCursosFavoritos;
	
	private CursoFavorito cursoFavorito;
	
	//Servicios
	private SCursosFavoritos sCursoFavoritos;
	
	//INICIALIZAR SERVICIO!!
	
	public CFavoritoAlumno() {
		System.out.println(cursoFavorito);
		listarFavoritos();
	}

	public void verDetalles(CursoFavorito cursoFavorito) throws IOException{
		
		this.cursoFavorito  = cursoFavorito;
		listarFavoritos();
		AplicacionUtil.redireccionar(SeguridadParametros.ACC_ALUMNO_FAVORITO);
		
	}
	
	public void listarFavoritos(){
		Usuario usuario = AplicacionUtil.obtenerUsuario();
		sCursoFavoritos =  new SCursosFavoritos();
		listaCursosFavoritos = sCursoFavoritos.listarCursosFavoritos(usuario.getPersona().getIdpersona());
	}
	

	public void eliminarDeFavoritos() throws IOException {
		this.sCursoFavoritos = new SCursosFavoritos();
		
		Integer idAlumno = this.cursoFavorito.getAlumno().getIdalumno();
		Integer idCurso = this.cursoFavorito.getCursoXDocente().getCurso().getIdcurso();
		Integer idDocente = this.cursoFavorito.getCursoXDocente().getDocente().getIddocente();
		
		sCursoFavoritos.eliminarDeFavoritos(idAlumno,idCurso,idDocente);
		
		listarFavoritos();
		
		AplicacionUtil.redireccionar(SeguridadParametros.INICIO_PAGINA_ALUMNO);
	}

	
	public CursoFavorito getCursoFavorito() {
		return cursoFavorito;
	}

	public void setCursoFavorito(CursoFavorito cursoFavorito) {
		this.cursoFavorito = cursoFavorito;
	}

	public ArrayList<CursoFavorito> getListaCursosFavoritos() {
		return listaCursosFavoritos;
	}

	public void setListaCursosFavoritos(ArrayList<CursoFavorito> listaCursosFavoritos) {
		this.listaCursosFavoritos = listaCursosFavoritos;
	}
	
	public void irAInicio() throws IOException{
		AplicacionUtil.redireccionar(SeguridadParametros.INICIO_PAGINA_ALUMNO);
	}
}
