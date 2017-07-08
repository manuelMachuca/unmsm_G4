import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;

import dao.entidad.Curso;
import modelo.CursoService;



//@RunWith(SpringJUnit4ClassRunne.class)
public class CursoTest {
	
	private CursoService cursoService;
	
	@Test
//	@Ignore
	@Transactional
	public void listarCurso() {
		
		cursoService = new CursoService();
		ArrayList<Curso> listacur = new ArrayList<>();
		listacur = cursoService.listaCurso();
//		System.out.println(listacur);
		for(Curso cur:listacur)
			System.out.println(cur.getNombre());
		}
		
	

}

