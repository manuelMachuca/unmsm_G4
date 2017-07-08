/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import org.hibernate.Criteria;
import excepciones.PersistenciaExcepcion;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Usuario;
import dao.interfaces.IUsuarioDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */
public class UsuarioDAO implements IUsuarioDAO {



	@Override
	public Usuario obtenerUSuario(int idPers) {

		try {

			Usuario user = new Usuario();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class, "user")
										.createAlias("persona", "pers");

			criteria.add(Restrictions.eq("pers.idpersona", idPers));

			user = (Usuario) criteria.uniqueResult();
			session.close();

			return user;
		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD");
		}

	}

	@Override
	public void actualizarUSuario(Usuario user) {
		
		Transaction tx = null;
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.update(user);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println(he);
		}
		
	}

    @Override
    public void agregarUsuario(Usuario usuario) {
                
        Transaction tx = null;
       try{
           
           Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            session.save(usuario);
            
            session.getTransaction().commit();
            session.close();
       }catch(HibernateException he){
               if (tx != null) tx.rollback();
               throw new PersistenciaExcepcion("ERROR AL AGREGAR USUARIO POR ENTIDAD",he);
       }
    }
    
    
    
}
