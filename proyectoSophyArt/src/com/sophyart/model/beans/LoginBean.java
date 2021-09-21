/**
 * LoginBean.java
 * 
 */

package com.sophyart.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sophyart.model.dao.implement.IUsuarioDao;
import com.sophyart.model.entidad.Userart;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String r="/vistas/paginaIngreso.xhtml";
	public LoginBean() {
		usuario= new Userart();
		actualizarLista();
		ingresado=false;
	}
	private boolean ingresado;
	
	public boolean isIngresado() {
		return ingresado;
	}

	public void setIngresado(boolean ingresado) {
		this.ingresado = ingresado;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String pasarClase() {
		return submitejemplocaptcha();
	}	private Userart usuario;
	
	private ArrayList<Userart> lista;
	private IUsuarioDao dao;
	
	
	private void actualizarLista() {
		lista= new ArrayList<>();
		dao= new IUsuarioDao();
		List<Object> obj= dao.darLista();
		for (Object object : obj) {
			lista.add((Userart)object);
		}
	}
	

	public Userart getUsuario() {
		return usuario;
	}

	public void setUsuario(Userart usuario) {
		this.usuario = usuario;
	}

	public Userart buscarUsuario(String user) {
		for (Userart userart : lista) {
			if(userart.getUserName().equals(user)) {
				return userart;
			}
		}return null;
	}
	public String submitejemplocaptcha() {
		Userart usu= buscarUsuario(usuario.getUserName());
		if(usu!=null) {
			
			/**
			 * validar si el usuario que ingreso si existe en el sistema paso a seguir
			 * a)verificar que este activo o que no tenga 3 o mas intento 
			 * 1) validar si su contraseña corresponde a la ingresada por parametro, si no correspodne numero de intentos aumenta 
			 * 2)  si las credenciales son correctas validar si el tiempo del ultimo cambio fue hace menos de 30 dias, si no exede los 30 ingresa 
			 * 2)	si
			 */
			if(usu.getActive().equals("I")) {
					return mostrarInfo("el usuario se encuentra inactivo, contactese con el administrador", null);
			}else {
				if(usu.getPassword().equals(usuario.getPassword())) {
					usuario=usu;
					ingresado=true;
					return mostrarInfo("BIENVENIDO "+usuario.getFullName(), "/vistasGenerales/tablaUsuario");
				}
			}
			
		}
	        return mostrarInfo("Credenciales invalidas",null);
		}
	private String mostrarInfo(String a,String b) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, a, a);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(msg);System.out.println(b);
		return b;
	}
		
	
}


