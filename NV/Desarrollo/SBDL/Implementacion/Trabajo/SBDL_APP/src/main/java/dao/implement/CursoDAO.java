package dao.implement;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.entidad.Curso;
import dao.entidad.Persona;
import dao.interfaces.ICursoDAO;
import excepciones.PersistenciaExcepcion;
import util.HibernateUtil;

public class CursoDAO implements ICursoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Curso> listCurso() {
		try {

			ArrayList<Curso> cursoLista = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Curso.class, "cur");

			cursoLista =  (ArrayList<Curso>) criteria.list();
		
//			session.close();
			return cursoLista;

		} catch (HibernateException he) {
			throw new PersistenciaExcepcion("ERROR EN LA ENTIDAD");
		}
	}

}
