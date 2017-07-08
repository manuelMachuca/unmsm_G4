
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;

import dao.entidad.Curso;
import dao.entidad.Docente;
import dao.entidad.Persona;
import dao.implement.DocenteDAO;
import modelo.CursoService;
import modelo.DocenteService;

public class DocenteTest {
	
	DocenteService docenteService = new DocenteService();
	DocenteDAO docenteDAO = new DocenteDAO();
	private CursoService cursoService;
	
	
	@Test
	@Ignore
	@Transactional
	public void listarCursoXDocente() {
		
		Persona persona = new Persona();
		Docente docente = new Docente();
		docente = docenteService.obtenerDocente(41);
		
		System.out.println(docente);
		
	}
	
	
	@Test
//	@Ignore
	@Transactional	public void asignarCurso(){
		
		
		//obtener docente
		Docente docente = new Docente();
		docente = docenteService.obtenerDocente(41);
		
		//obtener curso
		cursoService = new CursoService();
		ArrayList<Curso> listacur = new ArrayList<>();
		listacur = cursoService.listaCurso();
		
		docenteService.asignarCurso(docente,listacur.get(0));
	}

}
