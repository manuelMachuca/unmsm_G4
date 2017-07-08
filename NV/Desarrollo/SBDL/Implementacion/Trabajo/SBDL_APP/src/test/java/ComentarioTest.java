import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;

import modelo.SCursoXDocente;

public class ComentarioTest {
	SCursoXDocente sCursoXDocente;
	
	@Test
//	@Ignore
	@Transactional
	public void eliminarCursoDocente(){
		sCursoXDocente = new SCursoXDocente();
		
		sCursoXDocente.anularCurso(sCursoXDocente.buscarCursoDocenteXIds(1, 1));
	}

}
