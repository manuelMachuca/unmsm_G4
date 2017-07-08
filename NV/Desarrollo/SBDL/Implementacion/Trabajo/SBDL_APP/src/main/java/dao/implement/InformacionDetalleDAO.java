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
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Informaciondetalle;
import dao.entidad.InformaciondetalleId;
import dao.interfaces.IInformacionDetalleDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

/**
 *
 * @author Manuel Machuca
 */
public class InformacionDetalleDAO implements IInformacionDetalleDAO {
	
	@Override
	public void insertarInformacionDetalle(Informaciondetalle informaciondetalle) {
		Transaction tx = null;
		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.save(informaciondetalle);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException he) {
			if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL INSERTAR", he);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Informaciondetalle> listarCursosInfoDetalleDeAlumno(Integer idAlumno) {
		
		try{
			 
	          Session session = HibernateUtil.getSessionFactory().openSession();
	          
	          Criteria criteria = session.createCriteria(Informaciondetalle.class,"info");
	          criteria.createAlias("alumno", "alu");
	          criteria.add(Restrictions.eq("alu.idalumno", idAlumno));
	                      
	          return (ArrayList<Informaciondetalle>) criteria.list();
		
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR AL LISTAR CURSO DETTALLE DE ALUMNO" , he);
		}
		
	}

	@Override
	public void eliminarInfoDetalle(InformaciondetalleId idInfoDetalle) {
		Transaction tx = null;
		try{
			 
	          Session session = HibernateUtil.getSessionFactory().openSession();
	          tx = session.beginTransaction();
	          
	          Informaciondetalle info  = new Informaciondetalle();
	          info.setId(idInfoDetalle);
	          session.delete(info);
	          session.flush();
	          
	          tx.commit();
		}catch(HibernateException he){
			 if (tx != null) tx.rollback();
			throw new PersistenciaExcepcion("ERROR AL ELIMINAR CURSO DETALLE DE ALUMNO POR ID" , he);
		}
		
		
	}

	@Override
	public boolean noExisteRegistrado(InformaciondetalleId id) {
		
		try{
			 
	          Session session = HibernateUtil.getSessionFactory().openSession();
	          Criteria criteria = session.createCriteria(Informaciondetalle.class);
	          
	          criteria.add(Restrictions.eq("id", id));
	          boolean bool;
	          
	          if(criteria.list().isEmpty())
	        	  bool = true;
	          else
	        	  bool = false;
	          
	          return bool;
	          
		}catch(HibernateException he){
			throw new PersistenciaExcepcion("ERROR AL ELIMINAR CURSO DETALLE DE ALUMNO POR ID" , he);
		}
		
	} 
    
}
