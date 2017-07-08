/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Alumno;
import dao.entidad.Usuario;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.IUserDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */

public class UserDAO implements IUserDAO{

	@Override
	public Usuario ValidarUsuario(String usuario, String clave) {
	   	Usuario user = new Usuario();
 	   
        Session session = HibernateUtil.getSessionFactory().openSession();
	       try{

	            Criteria criteria = session.createCriteria(Usuario.class,"user");
	            
	            criteria.createAlias("persona", "per");
	            criteria.add(Restrictions.eq("per.correoelectronico", usuario));
	            criteria.add(Restrictions.eq("password", clave));
	            
	            if(criteria.list().isEmpty() )
	            	user =  null;
	            else
	            	user = (Usuario) criteria.uniqueResult();
	            
	            
	            return user;
	            
		}catch(PersistenciaExcepcion pe){
			throw new PersistenceException("Error en entidad " , pe);
		}finally{
			session.close();
		}
		
	}

   
    
}
