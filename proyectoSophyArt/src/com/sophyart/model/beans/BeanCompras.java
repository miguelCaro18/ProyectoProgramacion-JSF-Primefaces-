package com.sophyart.model.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;

import com.sophyart.model.dao.implement.IUsuarioDao;
import com.sophyart.model.entidad.Userart;


@ManagedBean(name="beanCompra")
@SessionScoped
public class BeanCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Userart> usuarios;
	private ArrayList<Userart> usuariosElegidos;
	private Userart  usuarioElegido;
	private Userart usuarioNuevo;
	private IUsuarioDao dao;
	
	
	public void eliminarUsuario() {
		
	}
	
	public ArrayList<Userart> getUsuariosElegidos() {
		return usuariosElegidos;
	}
	public List<String> estados(){
		List<String> es=new ArrayList<String>();
		es.add("Activo");
		es.add("Inactivo");
		return es;
	}
	public List<String> tipo(){
		List<String> es=new ArrayList<String>();
		es.add("Administrador");
		es.add("Auxiliar Adm");
		es.add("Empleado");
		return es;
	}
	public void setUsuariosElegidos(ArrayList<Userart> usuariosElegidos) {
		this.usuariosElegidos = usuariosElegidos;
	}
	public IUsuarioDao getDao() {
		return dao;
	}
	public void setDao(IUsuarioDao dao) {
		this.dao = dao;
	}
	public ArrayList<Userart> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Userart> usuarios) {
		this.usuarios = usuarios;
	}
	public Userart getUsuarioElegido() {
		return usuarioElegido;
	}
	public void setUsuarioElegido(Userart usuarioElegido) {
		this.usuarioElegido = usuarioElegido;
	}
	public Userart getUsuarioNuevo() {
		return usuarioNuevo;
	}
	public void setUsuarioNuevo(Userart usuarioNuevo) {
		this.usuarioNuevo = usuarioNuevo;
	}
	public BeanCompras() {
		usuarioElegido= new Userart();
		usuarioNuevo= new Userart();
		usuarios= new ArrayList<>();
		dao= new IUsuarioDao();
		List<Object> obj= dao.darLista();
		for (Object object : obj) {
			usuarios.add((Userart)object);
		}
	}
	public void actualizarLista() {
		usuarios= new ArrayList<>();
		List<Object> obj= dao.darLista();
		for (Object object : obj) {
			usuarios.add((Userart)object);
		}
	}
	public String pasarVistaClientes() {
		return "/vistasGenerales/tablaClientes";	
	}
	public String pasarVistaCompras() {
		return "/vistasGenerales/tablaCompras";
	}
	public String pasarVistaAuditoria() {
		return "/vistasGenerales/tablaAuditoria";
	}
	public String pasarVistaEmpresa() {
		return "/vistasGenerales/tablaEmpresa";	
		}
	public String pasarVistaUsuarios() {
		return "/vistasGenerales/tablaUsuario";	
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
	
	public void agregarNuevoUsuario() {
 
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/AgregarUsuario", options, null);
	}
	public void modificarUsuario() {
		 
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/modificarUsuario", options, null);
	}

}
