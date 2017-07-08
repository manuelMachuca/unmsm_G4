import javax.transaction.Transactional;

import org.junit.Test;

import dao.entidad.Persona;
import modelo.NivelEstudioService;
import modelo.PersonaService;

public class NivelEstudioTest {
	
	NivelEstudioService  nivelEstudioService= new NivelEstudioService();
	
	@Test
//	@Ignore
	@Transactional
	public void obtenerListaNE() {
		
		System.out.println(nivelEstudioService.obtenerListaNivelEstudio());
		
	}
	

}
