package com.sophyart.model.dao.implement;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.sophyart.model.conect.HibernateUtil;
import com.sophyart.model.dao.InterfaceDao;
import com.sophyart.model.entidad.Userart;

public class IUsuarioDao implements InterfaceDao{

	@Override
	public void guardar(Object o) {
		Userart guardar=(Userart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.save(guardar);
		t.commit();
	}

	@Override
	public void actualizar(Object o) {
		 Userart actualizar=(Userart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.update(actualizar);
		t.commit();
	}

	@Override
	public void remover(Object o) {
		Userart actualizar=(Userart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.delete(actualizar);
		t.commit();
		
	}

	@Override
	public Object darEntidad(long id) {
		Session ses= HibernateUtil.getSessionFactory().openSession();
		return (Userart)ses.load(Userart.class, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> darLista() {
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		List<Object> r= ses.createQuery("from Userart").list();
		t.commit();
		return r;
	
	}

	public Userart darEntidad1(int id) {

		Session ses= HibernateUtil.getSessionFactory().openSession();
		return (Userart)ses.load(Userart.class, id);
	}

}
