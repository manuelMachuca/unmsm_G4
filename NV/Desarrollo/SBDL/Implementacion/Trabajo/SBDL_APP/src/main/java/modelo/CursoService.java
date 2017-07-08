package modelo;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.Curso;
import dao.interfaces.ICursoDAO;

public class CursoService {

	ICursoDAO iCursoDAO = DAOFactory.getInstance().getCursoDAO();
	
	public ArrayList<Curso> listaCurso(){
		return iCursoDAO.listCurso();
	}
	

}
