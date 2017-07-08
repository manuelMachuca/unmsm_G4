/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.entidad.Perfil;
import dao.interfaces.IPerfilDAO;
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
public class PerfilDAO implements IPerfilDAO {

    @Override
    public Perfil buscarPerfil(int idPerfil) {
         Transaction tx = null;
         Perfil perfil = new Perfil();
       try{
           
           Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            Criteria criteria = session.createCriteria(Perfil.class);
            criteria.add(Restrictions.eq("idperfil", idPerfil));
            perfil = (Perfil) criteria.uniqueResult();
            session.close();
             
             
            return perfil;
            
       }catch(HibernateException he){
               if (tx != null) {
                tx.rollback();}
           System.out.println(he);
           return null;
       }
       
    }
}