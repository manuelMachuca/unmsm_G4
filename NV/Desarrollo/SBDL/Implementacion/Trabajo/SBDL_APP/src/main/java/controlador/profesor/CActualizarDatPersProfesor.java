package controlador.profesor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationListener;

import dao.entidad.Docente;
import dao.entidad.Persona;
import excepciones.NegocioExcepcion;
import modelo.DocenteService;
import modelo.PersonaService;
import util.AplicacionUtil;
import util.HibernateUtil;
import util.seguridad.SeguridadParametros;

public class CActualizarDatPersProfesor {

	private PersonaService personaService;
	private DocenteService docenteService;
	
	
	private Docente docente;
	private Persona persona;
	

    
    //PARA LA IMAGEN
    private Blob foto;
    private UploadedFile file;
    private byte[] byteData;
    private StreamedContent imagen;
    
	public CActualizarDatPersProfesor() {
		docenteService = new DocenteService();
		personaService = new PersonaService();
	
		
		int personaId=	AplicacionUtil.obtenerUsuario().getPersona().getIdpersona();
		docente = new Docente();
		docente = docenteService.obtenerDocente(personaId);
		System.out.println(docente);
		this.persona = docente.getPersona();
	}
	
	public void redireccionar() throws IOException{
	  AplicacionUtil.redireccionar(SeguridadParametros.ACC_DATOS_PERSONALES_PROFESOR);
	}

	public void handlefileupload(FileUploadEvent event){
		file = null;
        file = event.getFile();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            byteData = IOUtils.toByteArray(file.getInputstream());
            Blob img = Hibernate.getLobCreator(session).createBlob(byteData);
            persona.setFoto(img);
//            personaService.actualizarPersona(persona);
//            dpdao.ActualizarDatosPersonales(personalseleccionado);
            
            AplicacionUtil.mostrarMensajeJSF(1,"ÉXITO","SE SUBIO EXITOSAMENTE LA IMAGEN");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
//                    FacesMessage.SEVERITY_INFO, "SE ACTUALIZO LA FOTO", ""));
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void actualizarProfesor() {
		int severidad = 1;
		String titulo;
		String descripcion;
		
		
		personaService = new PersonaService();
		
		try{
		personaService.actualizarPersona(persona);
		titulo = "ÉXITO!";
		descripcion = "SE HA ACTUALIZADO EL PROFESOR CON ÉXITO";
		AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
		}catch(NegocioExcepcion ne){
		AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE ACTUALIZO EL PROFESOR");
		}
	}

	
	public StreamedContent getImagen() throws SQLException {
		
		this.imagen = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        
        String id = fc.getExternalContext().getRequestParameterMap().get("photo_id");
        int id_est = Integer.parseInt(id);
        Integer pr = new Integer(id_est);
        
        byte[] photoData = personaService.obtenerImagen(pr);
        return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}
	
	public PersonaService getPersonaService() {
		return personaService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
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

	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public byte[] getByteData() {
		return byteData;
	}

	public void setByteData(byte[] byteData) {
		this.byteData = byteData;
	}



}


