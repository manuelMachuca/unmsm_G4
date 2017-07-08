/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.entidad.NivelEstudio;
import dao.entidad.Perfil;
import dao.entidad.Persona;
import dao.interfaces.INivelEstudioDAO;
import dao.interfaces.IPerfilDAO;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author Miauuu
 */
public class NivelEstudioDAO implements INivelEstudioDAO{

	@Override
	public NivelEstudio buscarNivelEstudio(int nivelEstudio) {
		Transaction tx = null;
        NivelEstudio nivelEstu = new NivelEstudio();
      try{
          
          Session session = HibernateUtil.getSessionFactory().openSession();
           tx = session.beginTransaction();
          
           Criteria criteria = session.createCriteria(NivelEstudio.class);
           criteria.add(Restrictions.eq("idnivelestudio", nivelEstudio));
           nivelEstu = (NivelEstudio) criteria.uniqueResult();
           session.close();
            
           return nivelEstu;
           
      }catch(HibernateException he){
              if (tx != null) {
               tx.rollback();}
          System.out.println(he);
          return null;
      }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<NivelEstudio> obtenerListaNivelEstudio() {
		Transaction tx = null;
        ArrayList<NivelEstudio> listaNivelEstudio  = new ArrayList<NivelEstudio>();
        
      try{
          
          Session session = HibernateUtil.getSessionFactory().openSession();
           tx = session.beginTransaction();
          
           Criteria criteria = session.createCriteria(NivelEstudio.class);
           
            listaNivelEstudio = (ArrayList<NivelEstudio>) criteria.list();
            session.close();
           return listaNivelEstudio;
           
      }catch(HibernateException he){
              if (tx != null) {
               tx.rollback();}
          System.out.println(he);
          return null;
      }
	}
}