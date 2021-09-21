package com.sophyart.model.dao.implement;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.sophyart.model.conect.HibernateUtil;
import com.sophyart.model.dao.InterfaceDao;
import com.sophyart.model.entidad.Productoart;

public class IProductosDao implements InterfaceDao{

	@Override
	public void guardar(Object o) {
		Productoart guardar=(Productoart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.save(guardar);
		t.commit();
	}

	@Override
	public void actualizar(Object o) {
		 Productoart actualizar=(Productoart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.update(actualizar);
		t.commit();
	}

	@Override
	public void remover(Object o) {
		Productoart actualizar=(Productoart)o;
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		ses.delete(actualizar);
		t.commit();
		
	}

	@Override
	public Object darEntidad(long id) {
		Session ses= HibernateUtil.getSessionFactory().openSession();
		return (Productoart)ses.load(Productoart.class, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> darLista() {
		Session ses=HibernateUtil.getSessionFactory().openSession();
		Transaction t= ses.beginTransaction();
		List<Object> r= ses.createQuery("from Productoart").list();
		t.commit();
		return r;
	
	}

}
