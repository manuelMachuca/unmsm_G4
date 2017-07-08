import org.junit.Ignore;
import org.junit.Test;

import dao.entidad.CursoXDocente;
import modelo.SCursoXDocente;

public class CursoFavoritoTest {

	SCursoXDocente sCursoXDocente;
		
	@Test
//	@Ignore
	public void insertarFavorito(){
		sCursoXDocente = new SCursoXDocente();
		
		//buscar cursoXdocente
		CursoXDocente cursoXDocente = sCursoXDocente.buscarCursoDocenteXIds(1,15);
		System.out.println(cursoXDocente);

		
	}
	
	@Test
	@Ignore 
	public void eliminarFavorito(){
		
	}
}
