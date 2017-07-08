package controlador.profesor;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import dao.entidad.Docente;
import dao.entidad.NivelEstudio;
import dao.entidad.Persona;
import excepciones.NegocioExcepcion;
import modelo.DocenteService;
import modelo.NivelEstudioService;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;

public class CActualizarDatosProfesor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DocenteService docenteService;
	private NivelEstudioService nivelEstudioService;
	
	private ArrayList<NivelEstudio> listaNivelEstudio;
	private Docente docente;
	private Persona persona;
	private NivelEstudio nivelEstudio;
	
	private String nivelProfesor;
	
	public CActualizarDatosProfesor() {
		docenteService = new DocenteService();
		nivelEstudioService = new NivelEstudioService();
		nivelEstudio = new NivelEstudio();


		int personaId = AplicacionUtil.obtenerUsuario().getPersona().getIdpersona();
		docente = new Docente();
		docente = docenteService.obtenerDocente(personaId);
		this.persona = docente.getPersona();
		setearNivelEstudio();
		listaNivelEstudio = new ArrayList<>();
		listaNivelEstudio = nivelEstudioService.obtenerListaNivelEstudio();
		System.out.println(listaNivelEstudio);
	}
	
	public void setearNivelEstudio(){
		if(docente.getNivelEstudio()!=null){
			setNivelEstudio(docente.getNivelEstudio());
			System.out.println(nivelEstudio);
		}
	}
	
	public void validarSiTieneNivel(){
		if(docente.getNivelEstudio()==null)
			setNivelProfesor("No Aplica");
		else
			setNivelProfesor(docente.getNivelEstudio().getNombre());
		
	}

	public void actualizarDatosProfesor(){
		int severidad = 1;
		String titulo;
		String descripcion;
		
		try{
//			System.out.println(docente.getNivelEstudio());
			docenteService.actualizarDocente(docente,nivelEstudio.getIdnivelestudio());
			titulo = "SE HAN ACTUALIZADO SUS DATOS ACAD�MICOS DEL PROFSOR CON ÉXITO!";
			descripcion = "SE HAN ACTUALIZADO LOS DATOS ACADEMICOS DEL PROFSOR CON ÉXITO";
			AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
			}catch(NegocioExcepcion ne){
			AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE ACTUALIZO EL PROFESOR");
			}

	}
	
	
	public void redireccionar() throws IOException{
		  AplicacionUtil.redireccionar(SeguridadParametros.ACC_DATOS_PROFESOR);
		}

	public DocenteService getDocenteService() {
		return docenteService;
	}

	public void setDocenteService(DocenteService docenteService) {
		this.docenteService = docenteService;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public ArrayList<NivelEstudio> getListaNivelEstudio() {
		return listaNivelEstudio;
	}

	public void setListaNivelEstudio(ArrayList<NivelEstudio> listaNivelEstudio) {
		this.listaNivelEstudio = listaNivelEstudio;
	}

	public NivelEstudioService getNivelEstudioService() {
		return nivelEstudioService;
	}

	public void setNivelEstudioService(NivelEstudioService nivelEstudioService) {
		this.nivelEstudioService = nivelEstudioService;
	}

	public String getNivelProfesor() {
		return nivelProfesor;
	}

	public void setNivelProfesor(String nivelProfesor) {
		this.nivelProfesor = nivelProfesor;
	}

	public NivelEstudio getNivelEstudio() {
		return nivelEstudio;
	}

	public void setNivelEstudio(NivelEstudio nivelEstudio) {
		this.nivelEstudio = nivelEstudio;
	}

	
	
	
}
