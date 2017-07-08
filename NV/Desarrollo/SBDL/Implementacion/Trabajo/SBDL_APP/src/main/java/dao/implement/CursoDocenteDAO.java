/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Alumno;
import dao.entidad.Curso;
import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import dao.interfaces.IAlumnoDAO;
import dao.interfaces.ICursoDocenteDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */

public class CursoDocenteDAO implements ICursoDocenteDAO {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CursoXDocente> listarCurso_Profesor(String valorBuscado) {
      try{
          
          Session session = HibernateUtil.getSessionFactory().openSession();
          
          Criteria criteria = session.createCriteria(CursoXDocente.class,"curdoc")
        		  .createAlias("docente", "doc").createAlias("doc.persona", "per")
        		  .createAlias("curso", "cur").createAlias("cur.categoria", "categ");
          
          
          Criterion enNombre = Restrictions.like("per.nombres", "%"+valorBuscado+"%",MatchMode.ANYWHERE);
          Criterion enApellido = Restrictions.like("per.apellidos", "%"+valorBuscado+"%",MatchMode.ANYWHERE);
          Criterion enCurso = Restrictions.like("cur.nombre", "%"+valorBuscado+"%",MatchMode.ANYWHERE);
          Criterion enCategoria = Restrictions.like("categ.nombrecategoria", "%"+valorBuscado+"%",MatchMode.ANYWHERE);
          
          Criterion expresion = Restrictions.or(enNombre,enApellido,enCurso,enCategoria);
          
          criteria.add(expresion);
         
          return  (ArrayList<CursoXDocente>) criteria.list();
      }catch(HibernateException he){
            
              throw new PersistenciaExcepcion("Error al listar Cursos de Docente ", he);
      }
	}
	
	@Override
	public CursoXDocente buscarCursoDocenteXIds(Integer idCurso,Integer idDocente){
		try{
			  Session session = HibernateUtil.getSessionFactory().openSession();
	          
	          Criteria criteria = session.createCriteria(CursoXDocente.class);
	          criteria.add(Restrictions.eq("id.idcurso", idCurso));
	          criteria.add(Restrictions.eq("id.iddocente", idDocente));
			
	          return (CursoXDocente) criteria.uniqueResult();	
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR AL BUSCAR POR IDS");
		}
		
	}

	@Override
	public void valorarCurso(Integer idCurso, Integer idDocente,Integer adicional) {
		Transaction tx = null;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.createSQLQuery("update curso_x_docente "
					+ "set valoraciones = valoraciones + :valoraciones "
					+ "where idcurso = :idCurso "
					+ "and iddocente = :idDocente")
				.setParameter("idCurso", idCurso)
				.setParameter("idDocente", idDocente)
				.setParameter("valoraciones", adicional)
				.executeUpdate();	
			
			tx.commit();
		}catch(HibernateException he){
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL GUARDAR VALORACION",he);
		}
		
	}

	@Override
	public void actualizarValorarCurso(Integer idCurso, Integer idDocente, Integer adicional, Integer antiguo) {
		Transaction tx = null;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.createSQLQuery("update curso_x_docente "
					+ "set valoraciones = valoraciones - :antiguo + :adicional "
					+ "where idcurso = :idCurso "
					+ "and iddocente = :idDocente")
				.setParameter("idCurso", idCurso)
				.setParameter("idDocente", idDocente)
				.setParameter("antiguo", antiguo)
				.setParameter("adicional", adicional)
				.executeUpdate();	
			
			tx.commit();
		}catch(HibernateException he){
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL GUARDAR VALORACION",he);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CursoXDocente> listaCursoProfe(Docente docente) {
		try{
	          
			 ArrayList<CursoXDocente> lista = new ArrayList<>();
	          Session session = HibernateUtil.getSessionFactory().openSession();
	          
	          Criteria criteria = session.createCriteria(CursoXDocente.class)
	        		  .createAlias("docente", "doc");

	          criteria.add(Restrictions.eq("doc.iddocente", docente.getIddocente()));
	           lista = (ArrayList<CursoXDocente>) criteria.list();
	           
	           System.out.println(lista);
	          return  (ArrayList<CursoXDocente>) criteria.list();
	          
	      }catch(HibernateException he){
	            
	              throw new PersistenciaExcepcion("Error al listar Cursos de Docente ", he);
	      }
	}

	@Override
	public void anularCurso(CursoXDocente cursoXDocente) {
		Transaction tx = null;
	       try{
	    	 
		          Session session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();

				session
				.createSQLQuery("delete from curso_x_docente"
						+ " where idcur_doc = :idCursXDoc")
						.setParameter("idCursXDoc", cursoXDocente.getId().getIdcurDoc())
						.executeUpdate();
	          tx.commit();
	          
//				session.delete(cursoXDocente);
				
//				tx.commit();
//				session.close();
	       }catch(HibernateException he){
//	    	   tx.rollback();
	    	   he.printStackTrace();
	    	   throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD",he);
	       }
		
	}
    
}
