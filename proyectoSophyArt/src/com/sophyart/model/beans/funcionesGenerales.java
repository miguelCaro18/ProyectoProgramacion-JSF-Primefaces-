/**
 * LoginBean.java
 * 
 */

package com.sophyart.model.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name="funcionesPase")
@SessionScoped
public class funcionesGenerales implements Serializable
{

	/**
	 * 
	 */
	private LoginBean login;
	private static final long serialVersionUID = 1L;
	public funcionesGenerales() {
		FacesContext fc = FacesContext.getCurrentInstance();
		login=fc.getApplication().evaluateExpressionGet(fc, "#{loginBean}", LoginBean.class);
	}
	public void peticionIniciada() {
		if(!login.isIngresado()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("./../vistasGenerales/login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String pasarVistaCompras() {
		System.out.println("entro a vista compras");
		return "/vistasGenerales/tablaProductos";
	}
	public String cerrarSesion() {
		return "/vistasGenerales/login.xhtml";
	}
	public String pasarVistaClientes() {
		return "/vistasGenerales/tablaClientes";	}
	public String pasarVistaAuditoria() {
		return "/vistasGenerales/tablaAuditoria";
		}
	public String pasarVistaUsuarios() {
		return "/vistasGenerales/tablaUsuario";
	}
	public String pasarVistaEmpresa() {
		return "/vistasGenerales/tablaEmpresas";
	}
	public String pasarVistaExposiciones() {
		return "/vistasGenerales/tablaExposicion";
	}
	public String pasarVistaProductos() {
		return "/vistasGenerales/tablaProductos";
	}
	public String pasarVistaCategoria() {
		return "/vistasGenerales/tablaCategoria";
	}
	public String pasarVistaParametros() {
		return "/vistasGenerales/tablaParametros";
	}
}


