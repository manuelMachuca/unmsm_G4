import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;

import dao.DAOFactory;


import dao.entidad.Alumno;
import dao.entidad.Curso;
import dao.entidad.CursoFavorito;

import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import dao.entidad.Informaciondetalle;
import dao.entidad.InformaciondetalleId;
import dao.entidad.ValorCursoXDocente;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.ICursoFavoritoDAO;
import dao.interfaces.IInformacionDetalleDAO;
import modelo.DocenteService;
import modelo.SCursoXDocente;
import modelo.alumno.SInformacionDetalle;
import modelo.alumno.SValorCursoXDocente;

//@RunWith(SpringJUnit4ClassRunne.class)
public class CursoXDocenteTest {

	SCursoXDocente sCursoXDocente;
	DocenteService docenteService;
	SInformacionDetalle siDetalle;

	@Test
	@Ignore
	@Transactional
	public void listarCursoXDocente() {
		sCursoXDocente = new SCursoXDocente();
		ArrayList<CursoXDocente> cursoXDocente = sCursoXDocente.listarCurso_Profesor("calculo");
		System.out.println(cursoXDocente.size());
		for (CursoXDocente cursoXDocente2 : cursoXDocente) {
			System.out.println("Curso-Docente:" + cursoXDocente2);
		}

	}

	@Test
	@Ignore
	@Transactional
	public void listar() {
		siDetalle = new SInformacionDetalle();

		ArrayList<Informaciondetalle> lista = siDetalle.listarCursosInfoDetalleDeAlumno(1);
		for (Informaciondetalle detalle : lista) {
			System.out.println("Detalle" + detalle);
		}

	}

	@Test
	@Ignore
	@Transactional
	// FUNCIONA CUIDADO
	public void elimar() {
		IInformacionDetalleDAO inter = DAOFactory.getInstance().getInformacionDetalleDAO();
		InformaciondetalleId innn = new InformaciondetalleId();
		innn.setIdalumno(1);
		innn.setIdcurso(1);
		innn.setIddocente(1);

		inter.eliminarInfoDetalle(innn);
	}

	@Test
	@Ignore
	@Transactional
	// FUNCIONA CUIDADO
	public void cursosProfesor() {
		// obtener docente
		Docente docente = new Docente();
		docenteService = new DocenteService();
		sCursoXDocente = new SCursoXDocente();
		docente = docenteService.obtenerDocente(41);

		ArrayList<CursoXDocente> listaCurso = new ArrayList<>();
		listaCurso = sCursoXDocente.listaCursoProfe(docente);
		
		System.out.println(listaCurso);
	}

	@Test
	 @Ignore
	@Transactional
	public void anularCurso(){
		Docente docente = new Docente();
		docenteService = new DocenteService();
		sCursoXDocente = new SCursoXDocente();
		CursoXDocente cursoXDocente;
		docente = docenteService.obtenerDocente(41);
		
		cursoXDocente = sCursoXDocente.buscarCursoDocenteXIds(1,docente.getIddocente());
		sCursoXDocente.anularCurso(cursoXDocente);
	}

	
	@Test
	@Ignore
	@Transactional
	//FUNCIONA CUIDADO
	public void buscar() {
		SValorCursoXDocente sValorCursoXDocente = new SValorCursoXDocente();
		SCursoXDocente service = new SCursoXDocente();
		CursoXDocente cursoDocente = service.buscarCursoDocenteXIds(1, 15);

		IAlumnoDAO iAlumnoDAO  = DAOFactory.getInstance().getAlumnoDAO();

		Alumno alumno = iAlumnoDAO.obtenerAlumnoDePersona(1);
		
		ValorCursoXDocente valoracionBuscada = sValorCursoXDocente.buscarValoracionDeAlumno(cursoDocente,alumno);
		
		System.out.println(valoracionBuscada);
	}
	
	@Test
//	@Ignore
	@Transactional
	//FUNCIONA CUIDADO
	public void eliminarFavorito() {
	
		ICursoFavoritoDAO iCursoFavoritoDAO  = DAOFactory.getInstance().getCursoFavoritoDAO();
		iCursoFavoritoDAO.eliminarDeFavoritos(1, 2, 12);
		
	}
	
	@Test
	@Ignore
	@Transactional
	//FUNCIONA CUIDADO
	public void probar() {
		double promedio=2.5;
		BigDecimal x = new BigDecimal(promedio);
		BigDecimal y = new BigDecimal("3.5");
		int x2 = x.setScale(0, RoundingMode.HALF_UP).intValue();
		int y2 = y.setScale(0, RoundingMode.HALF_UP).intValue();
		System.out.println("x2: " + x2 +", y2: "+ y2);
	}
	
}
