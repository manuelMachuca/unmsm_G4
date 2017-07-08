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

import dao.entidad.Alumno;
import dao.entidad.Persona;
import dao.interfaces.IAlumnoDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */

public class AlumnoDAO implements IAlumnoDAO {

    @Override
    public void registrarAlumno(Alumno alumno) {
                
       Transaction tx = null;
       try{
           
           Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            session.save(alumno);
            
            session.getTransaction().commit();
            session.close();
       }catch(HibernateException he){
               if (tx != null) tx.rollback();
           throw new PersistenciaExcepcion("ERROR AL REGISTRAR POR ENTIDAD",he);
       }
    }

	@Override
	public Alumno obtenerAlumnoDePersona(Integer idPersona) {
		try{
	           
	           Session session = HibernateUtil.getSessionFactory().openSession();
	           Criteria criteria = session.createCriteria(Alumno.class);
	            
	           criteria.add(Restrictions.eq("persona.id",idPersona));
	            return (Alumno) criteria.uniqueResult();
	       }catch(HibernateException he){
	             throw new PersistenciaExcepcion("ERROR AL BUSCAR ALUMNO",he);
	       }
	}
    
}
