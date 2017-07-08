package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import modelo.PersonaService;

public class ImageController {

	PersonaService personaService;

	private StreamedContent imagen;

	private HttpServletRequest req;

	public ImageController() {
		personaService = new PersonaService();
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	public StreamedContent getImagen() throws IOException {
		this.imagen = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String id = fc.getExternalContext().getRequestParameterMap().get("photo_id");
		int id_est = Integer.parseInt(id);
		Integer pr = new Integer(id_est);

		byte[] photoData = personaService.obtenerImagen(pr);
		if (photoData != null)
			return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
		else {
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
			path = path.substring(6,111);
			System.out.println(path);
			return new DefaultStreamedContent(new FileInputStream(new File("D:/Workspace - Calidad/profesoraltoque", "/avatar2.jpg")));
//			return new DefaultStreamedContent(externalContext.getResourceAsStream("D:/Workspace - Calidad/profesoraltoque/avatar2.jpg"), "image/jpeg");
		}
	}

}
