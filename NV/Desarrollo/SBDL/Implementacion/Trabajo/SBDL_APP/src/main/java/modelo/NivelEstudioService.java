package modelo;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.entidad.NivelEstudio;
import dao.interfaces.INivelEstudioDAO;

public class NivelEstudioService {

	INivelEstudioDAO iNivelEstudioDAO = DAOFactory.getInstance().getNivelEstudioDAO();
	
	public ArrayList<NivelEstudio> obtenerListaNivelEstudio() {
		return iNivelEstudioDAO.obtenerListaNivelEstudio();
		}
}
