/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import java.util.ArrayList;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Alumno;
import dao.entidad.CursoFavorito;
import dao.entidad.CursoXDocente;
import dao.entidad.CursoXDocenteId;
import dao.entidad.Informaciondetalle;
import dao.entidad.Usuario;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.ICursoFavoritoDAO;
import dao.interfaces.IUserDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */

public class CursoFavoritoDAO implements ICursoFavoritoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CursoFavorito> listarCursosFavoritos(Integer idAlumno) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CursoFavorito.class);
			
			criteria.add(Restrictions.eq("alumno.idalumno", idAlumno));
						
			return (ArrayList<CursoFavorito>)criteria.list();
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR AL LISTAR FAVORITOS",he);
		}
	}

	@Override
	public void eliminarDeFavoritos(Integer idAlumno,Integer idCurso, Integer idDocente) {
		Transaction tx = null;
		try{
			  Session session = HibernateUtil.getSessionFactory().openSession();
	          tx = session.beginTransaction();
	          
	          session
				.createSQLQuery("delete from curso_favorito"
						+ " where idalumno = :idAlumno"
						+ " AND idcurso = :idCurso"
						+ " AND iddocente  = :idDocente")
						.setParameter("idAlumno", idAlumno)
						.setParameter("idCurso", idCurso)
						.setParameter("idDocente", idDocente)
						.executeUpdate();
	          tx.commit();
						
		}catch(HibernateException he){
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL LISTAR FAVORITOS",he);
		}
	}
	
	

	@Override
	public void insertarFavorito(CursoFavorito cursoFavorito) {
		Transaction tx = null;
		try{
			  Session session = HibernateUtil.getSessionFactory().openSession();
	          tx = session.beginTransaction();
	           
	          session.save(cursoFavorito);
	            
	          session.getTransaction().commit();
	          session.close();
						
		}catch(HibernateException he){
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL INSERTAR FAVORITOS",he);
		}
		
	}

	@Override
	public boolean validarRegistro(CursoFavorito cursoFavorito) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CursoFavorito.class);
			System.out.println(cursoFavorito);
			criteria.add(Restrictions.eq("id.idcurDoc", cursoFavorito.getId().getIdcurDoc()));
			criteria.add(Restrictions.eq("id.idalumno", cursoFavorito.getId().getIdalumno()));
			if(criteria.list().isEmpty()){
				System.out.println(criteria.list());
				System.out.println("Busco: "+ (CursoFavorito) criteria.uniqueResult());
				return true;
			}else
				return false;
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR AL VALIDAR EN BD",he);
		}
	}

	@Override
	public void eliminarDeFavoritos(Integer idCurso, Integer idDocente) {
		Transaction tx = null;
		try{
			  Session session = HibernateUtil.getSessionFactory().openSession();
	          tx = session.beginTransaction();
	          
	          session
				.createSQLQuery("delete from curso_favorito"
						+ " WHERE idcurso = :idCurso"
						+ " AND iddocente  = :idDocente")
						.setParameter("idCurso", idCurso)
						.setParameter("idDocente", idDocente)
						.executeUpdate();
	          tx.commit();
						
		}catch(HibernateException he){
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL LISTAR FAVORITOS",he);
		}
		
	}
   
    
}
