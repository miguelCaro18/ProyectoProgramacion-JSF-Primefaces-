package com.sophyart.model.beans;


import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.sophyart.model.dao.implement.IAuditoriaDao;
import com.sophyart.model.dao.implement.IUsuarioDao;
import com.sophyart.model.entidad.Auditart;
import com.sophyart.model.entidad.Userart;


@ManagedBean(name="beanAudit")
@SessionScoped
public class BeanAuditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Auditart> audito;
	private ArrayList<Auditart> auditoElegidos;
	private IAuditoriaDao dao;
	

	
	public ArrayList<Auditart> getauditoElegidos() {
		return auditoElegidos;
	}
	public String darGeneral(String a) {
		if(a.equals("R")) {
		return "Registrar";
		}else if(a.equals("A")) {
			return "Actualizar";
		}return "Eliminar";
		
	}
	public List<String> tipo(){
		List<String> es=new ArrayList<String>();
		es.add("Agregar");
		es.add("Modificar");
		es.add("Eliminar");
		return es;
	}
	public void setauditoElegidos(ArrayList<Auditart> auditoElegidos) {
		this.auditoElegidos = auditoElegidos;
	}
	public IAuditoriaDao getDao() {
		return dao;
	}
	public void setDao(IAuditoriaDao dao) {
		this.dao = dao;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Auditart> getaudito() {
		audito=(ArrayList<Auditart>)(Object)dao.darLista();
		return audito;
	}
	public void setaudito(ArrayList<Auditart> audito) {
		this.audito = audito;
	}
	@SuppressWarnings("unchecked")
	public BeanAuditoria() {
		
		audito= new ArrayList<>();
		dao= new IAuditoriaDao();
		auditoElegidos = new ArrayList<>();
		audito=(ArrayList<Auditart>)(Object)dao.darLista();
	}
	public String nombreModifica(int id) {
		IUsuarioDao a= new IUsuarioDao();
		Userart b=(Userart)a.darEntidad1(id);
		return b.getFullName();
	}
}
