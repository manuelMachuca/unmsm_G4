import java.io.File;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;

import dao.entidad.Persona;
import modelo.PersonaService;

public class PersonaTest {

	PersonaService personaService = new PersonaService();
	
	@Test	
	@Ignore
	@Transactional
	public void actualizarPersona() {
		
		String nombre = "cesotar";
		
		Persona persona = new Persona();
		persona = personaService.obtenerPersona(38);
		persona.setNombres(nombre);
						
		System.out.println(persona);
		
	}
	@Test
//	@Ignore
	@Transactional
	public void pruebas(){
//		HttpServletRequest req = null;
//		String contextPath = req.getContextPath();
//		System.out.println(contextPath);
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		path = path.substring(6);
		System.out.println(path);
		String workingDir = new File("").getAbsolutePath();
		System.out.println(workingDir);
		
	}
	
	
}
