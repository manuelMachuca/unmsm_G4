package modelo.alumno;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Alumno;
import dao.entidad.CursoXDocente;
import dao.entidad.ValorCursoXDocente;
import dao.interfaces.IValorCursoDocenteDAO;
import excepciones.NegocioExcepcion;
import excepciones.PersistenciaExcepcion;

public class SValorCursoXDocente {
	IValorCursoDocenteDAO ivalorCursoDocenteDAO = DAOFactory.getInstance().getValorCursoDocenteDAO();
	
	public ValorCursoXDocente buscarValoracionDeAlumno(CursoXDocente cursoDocente,Alumno alumno){
		try{
			ValorCursoXDocente valorCursoDocente = ivalorCursoDocenteDAO.buscarValoracionDeAlumno(cursoDocente,alumno);
			return valorCursoDocente;
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR AL BUSCAR ", pe);
		}
	}
	
	//trae todas las valoracionde un curso docente para promediar la valoracion del curso
	public ArrayList<ValorCursoXDocente> buscarTodasValoracionesDeCurso(CursoXDocente cursoXDocente){
		try{
			ArrayList<ValorCursoXDocente> listValorCurDoc = ivalorCursoDocenteDAO.buscarTodasValoracionesDeCurso(cursoXDocente);
			
			return listValorCurDoc;
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR DESCONOCIDO AL BUSCAR",pe);
		}
	}
	public void registrarValoracion(CursoXDocente cursoDocente,Alumno alumno,Integer valor){
		try{
			ivalorCursoDocenteDAO.registrarValoracion(cursoDocente,alumno,valor);
			
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR AL BUSCAR ", pe);
		}
	}
	
	public void actualizarValoracion(CursoXDocente cursoDocente,Alumno alumno,Integer valor){
		try{
			ValorCursoXDocente valorCursoDocente = ivalorCursoDocenteDAO.buscarValoracionDeAlumno(cursoDocente, alumno);
    		Integer antiguo = valorCursoDocente.getValor();
    		
			ivalorCursoDocenteDAO.actualizarValoracion(cursoDocente,alumno,valor,antiguo);
			
		}catch(PersistenciaExcepcion pe){
			throw new NegocioExcepcion("ERROR AL BUSCAR ", pe);
		}
	}
}
