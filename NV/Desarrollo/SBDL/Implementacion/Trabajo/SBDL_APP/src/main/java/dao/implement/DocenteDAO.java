/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.CursoXDocente;
import dao.entidad.Docente;
import dao.interfaces.IDocenteDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */

public class DocenteDAO implements IDocenteDAO {

    @Override
    public void registrarDocente(Docente docente) {
                
        Transaction tx = null;
       try{
           
           Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            session.save(docente);
            
            session.getTransaction().commit();
            session.close();
       }catch(HibernateException he){
               if (tx != null) {
                tx.rollback();}
           System.out.println(he);
       }
    }

	@Override
	public Docente obtenerDocente(int idPersona) {
		Docente docente = new Docente();
        Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			

	            
	            Criteria criteria = session.createCriteria(Docente.class,"docente")
	            						.createAlias("persona", "pers");
	            
	            criteria.add(Restrictions.eq("pers.idpersona",idPersona));
	            
	            docente =  (Docente) criteria.uniqueResult();
//	            session.close();
	            return docente;

	            
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD");
		}finally{
            session.close();
		}
		
	}

	@Override
	public void actualizarDocente(Docente docente, int idNE) {
		Transaction tx = null;
	       try{
//	    	   int idNivelEstudio = docente.getNivelEstudio().getIdnivelestudio();
	           int idDocente = docente.getIddocente();
	    	   int idNivelEstudio = idNE;
	    	   String descBiografia = docente.getDesc_biografia();
	    	   String carrera = docente.getCarrera();
	    	   
	           Session session = HibernateUtil.getSessionFactory().openSession();
	            tx = session.beginTransaction();
	            session.createSQLQuery("update docente "
					+ "set idNivelEstudio = :idNivelEstudio, "
					+ "desc_biografia = :descBiografia, "
					+ "carrera = :carrera "
					+ "where iddocente = :idDocente "
					)
				.setParameter("idNivelEstudio",idNivelEstudio )
				.setParameter("descBiografia", descBiografia)
				.setParameter("carrera", carrera)
				.setParameter("idDocente", idDocente)

				.executeUpdate();	
//	            session.update(persona);
	            
	            tx.commit();
	            session.close();
	       }catch(HibernateException he){
	    	   //tx.rollback();
	    	   throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD",he);
	       }
	}

	@Override
	public void asignarCurso(CursoXDocente cursoXDocente) {
		Transaction tx = null;
	       try{
	    	  Session session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();

				session.save(cursoXDocente);

				session.getTransaction().commit();
				session.close();
	       }catch(HibernateException he){
	    	   //tx.rollback();
	    	   throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD",he);
	       }
		
	}
	
	
	
    
}
