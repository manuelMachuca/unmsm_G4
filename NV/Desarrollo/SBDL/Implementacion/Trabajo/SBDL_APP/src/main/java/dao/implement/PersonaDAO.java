/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Persona;
import dao.interfaces.IPersonaDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */
public class PersonaDAO implements IPersonaDAO {

	@Override
	public void registrarPersona(Persona persona) {

		Transaction tx = null;
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.save(persona);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException he) {
			// tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL INSERTAR", he);
		}
	}

	@Override
	public Persona obtenerPersona(int id) {
		try {

			Persona pers = new Persona();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Persona.class, "pers");
			criteria.add(Restrictions.eq("pers.idpersona", id));
			pers = (Persona) criteria.uniqueResult();
			session.close();
			return pers;

		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD");
		}

	}

	@Override
	public void actualizarPersona(Persona persona) {
		Transaction tx = null;
		try {

			int idPers = persona.getIdpersona();
			String apellidos = persona.getApellidos();
			String nombres = persona.getNombres();
			int telefono = persona.getTelefono();
			Date fechNac = persona.getFechaNac();
			String direccion = persona.getDireccion();
			Blob foto = persona.getFoto();

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			// session.createSQLQuery("update persona " + "set direccion =
			// :direccion, " + "apellidos = :apellidos, "
			// + "nombres = :nombres, " + "telefono = :telefono, " + "fecha_nac
			// = :fechNac, " + "foto = :foto "
			// + "where idpersona = :idPers ")
			// .setParameter("direccion", direccion)
			// .setParameter("apellidos", apellidos)
			// .setParameter("nombres", nombres)
			// .setParameter("foto", foto)
			// .setParameter("telefono", telefono)
			// .setParameter("fechNac", fechNac)
			// .setParameter("idPers", idPers)
			// .executeUpdate();
			session.update(persona);

			tx.commit();
			session.close();
		} catch (HibernateException he) {
			// tx.rollback();
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD", he);
		}
	}

	@Override
	public boolean correoNoExiste(String correo) {
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Persona.class);

			criteria.add(Restrictions.eq("correoelectronico", correo));
			if (criteria.list().isEmpty())
				return true;
			else
				return false;
		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR AL VERIFICAR CORREO POR ENTIDAD", he);
		}
	}

	@Override
	public byte[] obtenerImagen(int idPersona) {
		byte[] para = null;
		Blob img;
		Transaction tx = null;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			String hql = "select e.foto from Persona e where e.idpersona = " + idPersona;
			Query query = session.createQuery(hql);
			img = (Blob) query.uniqueResult();
			if (img != null) {
				para = img.getBytes(1L, (int) img.length());
				return para;
			} else
				return null;

		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD", he);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
