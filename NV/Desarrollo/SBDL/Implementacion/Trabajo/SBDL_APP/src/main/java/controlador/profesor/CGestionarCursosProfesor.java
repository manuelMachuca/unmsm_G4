package controlador.profesor;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.taglibs.standard.tag.common.core.RemoveTag;

import com.mysql.fabric.xmlrpc.base.Array;

import dao.entidad.Curso;
import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import excepciones.NegocioExcepcion;
import modelo.CursoService;
import modelo.DocenteService;
import modelo.SCursoXDocente;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;

public class CGestionarCursosProfesor {

	private DocenteService docenteService;
	private CursoService cursoService;
	private SCursoXDocente sCursoXDocente;

	private Docente docente;
	private Curso curso;
	private CursoXDocente cursoXDocente;

	private ArrayList<Curso> listaCurso;
	private ArrayList<CursoXDocente> listaCursoDoc;

	public CGestionarCursosProfesor() {
		docenteService = new DocenteService();
		cursoService = new CursoService();
		docente = new Docente();
		curso = new Curso();
		cursoXDocente = new CursoXDocente();
		sCursoXDocente = new SCursoXDocente();
		listaCurso = new ArrayList<>();
		listaCursoDoc = new ArrayList<>();

		int personaId = AplicacionUtil.obtenerUsuario().getPersona().getIdpersona();
		docente = new Docente();
		docente = docenteService.obtenerDocente(personaId);

		listarCursosDelProfesor();
		
	}

	public void listarCursosDelProfesor() {
		listaCursoDoc = sCursoXDocente.listaCursoProfe(docente);
		listarCursos();
		validarCursos();
	}

	public void listarCursos() {
		listaCurso = cursoService.listaCurso();
	}
	
	public void validarCursos(){
		
		ArrayList<Curso> listaTemp = new ArrayList<>();
		
		for(Curso listacur : listaCurso){
			for(CursoXDocente listacurdoc : listaCursoDoc){
				if(listacurdoc.getCurso().getIdcurso() == listacur.getIdcurso()){
					listaTemp.add(listacur);
				}
			}
		}
		listaCurso.removeAll(listaTemp);
	}

	public void redireccionar() throws IOException {
		AplicacionUtil.redireccionar(SeguridadParametros.ACC_CURSOS_PROFESOR);
	}

	public void asignarCurso(Curso cur) {
		int severidad = 1;
		String titulo;
		String descripcion;

		try {
			docenteService.asignarCurso(docente, cur);
			titulo = "ÉXITO!";
			descripcion = "SE ASIGNO CORRECTAMENTE EL PROFESOR AL ALUMNO";
			AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
			listarCursosDelProfesor();
		} catch (NegocioExcepcion ne) {
			AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE PUDO ASIGNAR EL CURSO");
		}

	}
	
	public void desasignarCurso(CursoXDocente cursoXDocente){
		int severidad = 1;
		String titulo;
		String descripcion;

		try {
			sCursoXDocente.anularCurso(cursoXDocente);
			titulo = "ÉXITO!";
			descripcion = "SE DESASIGNO CORRECTAMENTE EL CURSO";
			AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
			listarCursosDelProfesor();
		} catch (NegocioExcepcion ne) {
			AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE PUDO DESASIGNAR EL CURSO");
		}
	}

	public DocenteService getDocenteService() {
		return docenteService;
	}

	public void setDocenteService(DocenteService docenteService) {
		this.docenteService = docenteService;
	}

	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public ArrayList<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(ArrayList<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}

	public ArrayList<CursoXDocente> getListaCursoDoc() {
		return listaCursoDoc;
	}

	public void setListaCursoDoc(ArrayList<CursoXDocente> listaCursoDoc) {
		this.listaCursoDoc = listaCursoDoc;
	}

	public SCursoXDocente getsCursoXDocente() {
		return sCursoXDocente;
	}

	public void setsCursoXDocente(SCursoXDocente sCursoXDocente) {
		this.sCursoXDocente = sCursoXDocente;
	}

	public CursoXDocente getCursoXDocente() {
		return cursoXDocente;
	}

	public void setCursoXDocente(CursoXDocente cursoXDocente) {
		this.cursoXDocente = cursoXDocente;
	}
	
	

}
