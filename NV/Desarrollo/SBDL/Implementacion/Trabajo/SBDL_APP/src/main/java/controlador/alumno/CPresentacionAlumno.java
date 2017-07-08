package controlador.alumno;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import dao.entidad.Alumno;
import dao.entidad.Curso;
import dao.entidad.CursoFavorito;
import dao.entidad.CursoFavoritoId;
import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import dao.entidad.Informaciondetalle;
import dao.entidad.ValorCursoXDocente;
import excepciones.NegocioExcepcion;
import modelo.AlumnoService;
import modelo.SCursoXDocente;
import modelo.alumno.SCursosFavoritos;
import modelo.alumno.SInformacionDetalle;
import modelo.alumno.SValorCursoXDocente;
import util.AplicacionUtil;
import util.seguridad.Usuario;

public class CPresentacionAlumno implements Serializable {

	
	//Para actualizar la lista de este metodo que esta en tipo session
	private CFavoritoAlumno beanFavorito;
	
	private ArrayList<Informaciondetalle> listaUltimosCursos;
	private Informaciondetalle cursoSeleccionado;
	private CursoXDocente cursoXDocente;
	private SCursoXDocente sCursoXDocente;
	private AlumnoService alumnoService;
	// Servicios
	private SInformacionDetalle informacionDetalleService;
	private SCursosFavoritos sCursoFavoritos;
	
	//@Autowired
	private SValorCursoXDocente sValorCursoXDocente;
	public String primeraStar;
	public String segundaStar;
	// INICIALIZAR SERVICIO!!
	
	public CPresentacionAlumno() {
		listar();
	}

	public void listar() {
		Usuario usuario = AplicacionUtil.obtenerUsuario();
		informacionDetalleService = new SInformacionDetalle();
		listaUltimosCursos = informacionDetalleService
				.listarCursosInfoDetalleDeAlumno(usuario.getPersona().getIdpersona());
	}


	public void verDetalles(Informaciondetalle cursoSeleccionado) {
		sCursoXDocente = new SCursoXDocente();
		this.cursoXDocente = new CursoXDocente();
		
		this.cursoXDocente = sCursoXDocente.buscarCursoDocenteXIds(cursoSeleccionado.getCurso().getIdcurso(),
				cursoSeleccionado.getDocente().getIddocente());
		this.cursoSeleccionado = cursoSeleccionado;
		
		AplicacionUtil.ejecutar("PF('wizardInfoCurProfe').next()");

	}

	public void verDetalles(CursoXDocente curso) {

		this.cursoXDocente = curso;

		AplicacionUtil.ejecutar("PF('wizardInfoCurProfe').next()");

	}

	public void eliminarInfoDetalle(Informaciondetalle cursoSeleccionado) throws IOException {

		informacionDetalleService = new SInformacionDetalle();
		informacionDetalleService.eliminarInfoDetalle(cursoSeleccionado.getId());

		listar();
		
		beanFavorito.listarFavoritos();
	}

	public void valorarCurso(Informaciondetalle informaciondetalle,Integer valoracion)  {
		try{
			sCursoXDocente = new SCursoXDocente();
			
			Usuario usuario = AplicacionUtil.obtenerUsuario();
			CursoXDocente cursoDocente = sCursoXDocente.buscarCursoDocenteXIds(informaciondetalle.getCurso().getIdcurso(),
					informaciondetalle.getDocente().getIddocente());
			
			AlumnoService service = new AlumnoService();
			Alumno alumno = service.buscarAlumno(usuario.getPersona().getIdpersona());
			
			sValorCursoXDocente = new SValorCursoXDocente();
			//La anterior ultima registrada
			ValorCursoXDocente valoracionBuscada = sValorCursoXDocente.buscarValoracionDeAlumno(cursoDocente,alumno);
			
			//Si nunca valoro ese curso
			if(valoracionBuscada == null){
				sCursoXDocente.valorarCurso(informaciondetalle.getCurso().getIdcurso(),
						informaciondetalle.getDocente().getIddocente(),valoracion);
				sValorCursoXDocente.registrarValoracion(cursoDocente,alumno,valoracion);
			}else{
				//Si ya encuentra cambia el valor por el nuevo
				Integer antiguo = valoracionBuscada.getValor();
				sCursoXDocente.actualizarValorarCurso(informaciondetalle.getCurso().getIdcurso(),
						informaciondetalle.getDocente().getIddocente(),antiguo,valoracion);
				sValorCursoXDocente.actualizarValoracion(cursoDocente,alumno,valoracion);
			}
			
			listar();
			
			AplicacionUtil.mostrarMensajeJSF(2,"EXITO", "Se valoro con "+ valoracion +" estrellas.");
			
		}catch(NegocioExcepcion ne){
			AplicacionUtil.mostrarMensajeJSF(3, "ERROR AL VALORAR" , "No se pudo valorar: "+ ne.getMessage());
		}
	}

	public void agregarAFavoritos(Informaciondetalle cursoSeleccionado) throws IOException {
		try {
			
			
			sCursoXDocente = new SCursoXDocente();
			CursoXDocente cursoXDocente = sCursoXDocente.buscarCursoDocenteXIds(
					cursoSeleccionado.getCurso().getIdcurso(), cursoSeleccionado.getDocente().getIddocente());
			
			Usuario usuario = AplicacionUtil.obtenerUsuario();
			this.alumnoService = new AlumnoService();
			Alumno alumno = new Alumno();
			alumno = alumnoService.buscarAlumno(usuario.getPersona().getIdpersona());
			
			CursoFavorito cursoFavorito = new CursoFavorito();
			cursoFavorito.setCursoXDocente(cursoXDocente);
			cursoFavorito.setAlumno(alumno);
			
			CursoFavoritoId id = new CursoFavoritoId();
			id.setIdcurDoc(cursoXDocente.getId().getIdcurDoc());
			id.setIdalumno(alumno.getIdalumno());
			id.setIdcurso(cursoXDocente.getCurso().getIdcurso());
			id.setIddocente(cursoXDocente.getDocente().getIddocente());
			
			cursoFavorito.setId(id);
			
			this.sCursoFavoritos = new SCursosFavoritos();
			
			if(sCursoFavoritos.validarRegistro(cursoFavorito)){
				
				sCursoFavoritos.agregarAFavoritos(cursoFavorito);
				
				beanFavorito.listarFavoritos();
				
				AplicacionUtil.actualizarPagina();
			}else{
				AplicacionUtil.mostrarMensajeJSF(2,"OOPS", "Ya se encuentra agregado");
			}	
						
		} catch (NegocioExcepcion ne) {
			AplicacionUtil.mostrarMensajeJSF(2, ne.getMessage(), "NO SE PUDO REGISTRAR");
		}
	}


	
	public CFavoritoAlumno getBeanFavorito() {
		return beanFavorito;
	}

	public void setBeanFavorito(CFavoritoAlumno beanFavorito) {
		this.beanFavorito = beanFavorito;
	}

	public ArrayList<Informaciondetalle> getListaUltimosCursos() {
		return listaUltimosCursos;
	}

	public void setListaUltimosCursos(ArrayList<Informaciondetalle> listaUltimosCursos) {
		this.listaUltimosCursos = listaUltimosCursos;
	}

	public Informaciondetalle getCursoSeleccionado() {
		return cursoSeleccionado;
	}

	public void setCursoSeleccionado(Informaciondetalle cursoSeleccionado) {
		this.cursoSeleccionado = cursoSeleccionado;
	}

	public CursoXDocente getCursoXDocente() {
		return cursoXDocente;
	}

	public void setCursoXDocente(CursoXDocente cursoXDocente) {
		this.cursoXDocente = cursoXDocente;
	}

	public String getPrimeraStar() {
		return primeraStar;
	}

	public void setPrimeraStar(String primeraStar) {
		this.primeraStar = primeraStar;
	}

	public String getSegundaStar() {
		return segundaStar;
	}

	public void setSegundaStar(String segundaStar) {
		this.segundaStar = segundaStar;
	}

	
}
