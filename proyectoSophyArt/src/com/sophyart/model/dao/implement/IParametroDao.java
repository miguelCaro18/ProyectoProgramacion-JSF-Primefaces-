package com.sophyart.model.dao.implement;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sophyart.model.conect.HibernateUtil;
import com.sophyart.model.dao.InterfaceDao;
import com.sophyart.model.entidad.Parametroart;

public class IParametroDao  implements InterfaceDao{

	@Override
	public void guardar(Object o) {
		Parametroart parametro=(Parametroart)o;
		Session ses= HibernateUtil.getSessionFactory().openSession();
		Transaction t=ses.beginTransaction();
		ses.save(parametro);
		t.commit();
	}

	@Override
	public void actualizar(Object o) {
		Parametroart parametro=(Parametroart)o;
		Session ses= HibernateUtil.getSessionFactory().openSession();
		Transaction t=ses.beginTransaction();
		ses.update(parametro);
		t.commit();
		
	}

	@Override
	public void remover(Object o) {
		Parametroart parametro=(Parametroart)o;
		Session ses= HibernateUtil.getSessionFactory().openSession();
		Transaction t=ses.beginTransaction();
		ses.delete(parametro);
		t.commit();
		
	}

	@Override
	public Object darEntidad(long id) {
		Session ses= HibernateUtil.getSessionFactory().openSession();
		return (Parametroart)ses.load(Parametroart.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> darLista() {
		
		Session ses= HibernateUtil.getSessionFactory().openSession();
		Transaction t=ses.beginTransaction();
		List<Object> r= ses.createQuery("from Parametroart").list();
		t.commit();
		return r;
	
	}

}
