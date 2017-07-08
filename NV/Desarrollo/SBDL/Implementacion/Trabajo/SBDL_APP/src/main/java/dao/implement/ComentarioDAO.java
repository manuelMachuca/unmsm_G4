package dao.implement;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.DAOFactory;
import dao.entidad.Comentario;
import dao.entidad.Curso;
import dao.entidad.CursoXDocenteId;
import dao.interfaces.IComentarioDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

public class ComentarioDAO implements IComentarioDAO {

	@Override
	public void insertarComentario(Comentario comentario) {
		Transaction tx = null;
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.save(comentario);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException he) {
			// tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL INSERTAR", he);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Comentario> obtenerlista(CursoXDocenteId id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Comentario.class,"com")
					.createAlias("cursoXDocente","curDoc");
			
			criteria.add(Restrictions.eq("curDoc.id",id));
			return (ArrayList<Comentario>) criteria.list();


		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD");
		}
	}

	@Override
	public void borrarComentario(Comentario comentario) {
		Transaction tx = null;
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.delete(comentario);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException he) {
			// tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL ELIMINAR", he);
		}
		
	}

}
