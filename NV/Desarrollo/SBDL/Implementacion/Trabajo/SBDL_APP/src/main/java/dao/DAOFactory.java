/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;
import dao.entidad.CursoFavorito;
import dao.implement.AlumnoDAO;
import dao.implement.ComentarioDAO;
import dao.implement.CursoDAO;
import dao.implement.CursoDocenteDAO;
import dao.implement.CursoFavoritoDAO;
import dao.implement.DocenteDAO;
import dao.implement.InformacionDetalleDAO;
import dao.implement.NivelEstudioDAO;
import dao.implement.PerfilDAO;
import dao.implement.PersonaDAO;
import dao.implement.UserDAO;
import dao.implement.UsuarioDAO;
import dao.implement.ValorCursoDocenteDAO;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.IComentarioDAO;
import dao.interfaces.ICursoDAO;
import dao.interfaces.ICursoDocenteDAO;
import dao.interfaces.ICursoFavoritoDAO;
import dao.interfaces.IPerfilDAO;
import dao.interfaces.IPersonaDAO;
import dao.interfaces.IUserDAO;
import dao.interfaces.IUsuarioDAO;
import dao.interfaces.IValorCursoDocenteDAO;
import dao.interfaces.IDocenteDAO;
import dao.interfaces.IInformacionDetalleDAO;
import dao.interfaces.INivelEstudioDAO;


public class DAOFactory {
	
	private static DAOFactory daoFac;
	
	static{
		daoFac = new DAOFactory();
	}
	
	public static DAOFactory getInstance(){
		
		return daoFac;
	}
        
        public IPersonaDAO getPersonaDAO(){
                return new PersonaDAO();
        }

        public IAlumnoDAO getAlumnoDAO(){
                return new AlumnoDAO();
        }
        
        public IDocenteDAO getDocenteDAO(){
            return new DocenteDAO();
        }        
        
        public IUsuarioDAO getUsuarioDAO(){
                return new UsuarioDAO();
        }
	
        public IPerfilDAO getPerfilDAO(){
                return new PerfilDAO();
        }
        
        public INivelEstudioDAO getNivelEstudioDAO(){
            return new NivelEstudioDAO();
        }
        
        public ICursoDocenteDAO getCursoDocenteDAO(){
        	return new CursoDocenteDAO();
        }
        
        public IUserDAO getUserDAO(){
        	return new UserDAO();
        }
        public IInformacionDetalleDAO getInformacionDetalleDAO(){
        	return new InformacionDetalleDAO();
        }
        public ICursoFavoritoDAO getCursoFavoritoDAO(){
        	return new CursoFavoritoDAO();
        }
        
        public ICursoDAO getCursoDAO(){
        	return new CursoDAO();
        }
        public IValorCursoDocenteDAO getValorCursoDocenteDAO(){
        	return new ValorCursoDocenteDAO();
        }
        
        public IComentarioDAO getComentarioDAO(){
        	return new ComentarioDAO();
        }
}
