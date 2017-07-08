/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import util.HibernateUtil;
import dao.entidad.Alumno;
import dao.entidad.CursoXDocente;
import dao.entidad.Persona;
import dao.entidad.ValorCursoXDocente;
import dao.entidad.ValorCursoXDocenteId;
import dao.interfaces.IPersonaDAO;
import dao.interfaces.IValorCursoDocenteDAO;
import excepciones.PersistenciaExcepcion;

import java.util.ArrayList;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Manuel Machuca
 */
public class ValorCursoDocenteDAO implements IValorCursoDocenteDAO {

	@Override
	public ValorCursoXDocente buscarValoracionDeAlumno(CursoXDocente cursoDocente, Alumno alumno) {
		try{ 
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ValorCursoXDocente.class);
			
			criteria.add(Restrictions.eq("id.idalumno",alumno.getIdalumno()));
			criteria.add(Restrictions.eq("id.idcurDoc",cursoDocente.getId().getIdcurDoc()));
			
			return (ValorCursoXDocente) criteria.uniqueResult();	
			
		}catch(HibernateException he){
			throw new PersistenceException("ERROR AL BUSCAR POR ENTIDAD");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ValorCursoXDocente> buscarTodasValoracionesDeCurso(CursoXDocente cursoXDocente) {
		try{ 
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ValorCursoXDocente.class);
			
			criteria.add(Restrictions.eq("cursoXDocente",cursoXDocente));
			
			return (ArrayList<ValorCursoXDocente>)criteria.list();	
			
		}catch(HibernateException he){
			throw new PersistenceException("ERROR AL BUSCAR POR ENTIDAD");
		}
	}
 
	@Override
	public void registrarValoracion(CursoXDocente cursoDocente, Alumno alumno, Integer valor) {
		 
        Transaction tx = null;
		try{ 
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			
			ValorCursoXDocente valorCursoXDocente = new ValorCursoXDocente();
			valorCursoXDocente.setAlumno(alumno);
			
			ValorCursoXDocenteId id = new ValorCursoXDocenteId();
			id.setIdalumno(alumno.getIdalumno());
			id.setIdcurDoc(cursoDocente.getId().getIdcurDoc());
			id.setIdcurso(cursoDocente.getCurso().getIdcurso());
			id.setIddocente(cursoDocente.getDocente().getIddocente());
			
			valorCursoXDocente.setId(id);
			valorCursoXDocente.setCursoXDocente(cursoDocente);
			valorCursoXDocente.setValor(valor);
			
			session.save(valorCursoXDocente);
            
            session.getTransaction().commit();
            session.close();
			
		}catch(HibernateException he){
			throw new PersistenceException("ERROR AL REGISTRAR POR ENTIDAD");
		}
		
	}

	@Override
	public void actualizarValoracion(CursoXDocente cursoDocente, Alumno alumno, Integer valor,Integer antiguo) {
		Transaction tx = null;
		try{ 
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.createSQLQuery("update valora_alum_curs_x_docent "
					+ "set valor = valor - :antiguo + :adicional "
					+ "where IDCUR_DOC = :IDCUR_DOC "
					+ "and IDALUMNO = :IDALUMNO")
				.setParameter("IDCUR_DOC", cursoDocente.getId().getIdcurDoc())
				.setParameter("IDALUMNO", alumno.getIdalumno())
				.setParameter("antiguo", antiguo)
				.setParameter("adicional", valor)
				.executeUpdate();	
			
			tx.commit();
    		
		}catch(HibernateException he){
			throw new PersistenceException("ERROR AL ACTUALIZAR POR ENTIDAD");
		}
		
	}    
}
