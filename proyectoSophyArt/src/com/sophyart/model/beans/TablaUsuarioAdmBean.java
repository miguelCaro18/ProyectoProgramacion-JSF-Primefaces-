package com.sophyart.model.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


import org.primefaces.PrimeFaces;

import com.sophyart.model.dao.implement.IAuditoriaDao;
import com.sophyart.model.dao.implement.IUsuarioDao;
import com.sophyart.model.entidad.Auditart;
import com.sophyart.model.entidad.Userart;
import com.sophyart.model.mail.envioMensajes;


@ManagedBean(name="AdmUser")
@SessionScoped
public class TablaUsuarioAdmBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Userart> usuarios;
	private ArrayList<Userart> usuariosElegidos;
	private Userart  usuarioElegido;
	private Userart usuarioNuevo;
	private IUsuarioDao dao;
	private boolean admin;
	private LoginBean login;
	private String ip;

	
	/**
	 * Constructor
	 */
	public TablaUsuarioAdmBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		login=fc.getApplication().evaluateExpressionGet(fc, "#{loginBean}", LoginBean.class);
		if(login.getUsuario().getId()!=null	) {
			System.out.println("el usuario se ha registrado en la plataforma");
			 FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null,new FacesMessage("Ingreso", "bienvenido"+login.getUsuario().getFullName()) );
		}else {
			System.out.println("el usuario no se ha registrado");
		}
		
		usuarioElegido= new Userart();
		usuarioNuevo= new Userart();
		usuarios= new ArrayList<>();
		dao= new IUsuarioDao();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = request.getRemoteAddr();
		}
		if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
			ipAddress=request.getLocalAddr();
		}
		ip=ipAddress;
	}
	/**
	 * 
	 *____________________________________________________________________________________________* 
	 */
	
	
	public LoginBean getLogin() {
		return login;
	}
	public void setLogin(LoginBean login) {
		this.login = login;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
		actualizarLista();
		return usuarios;
	}
	public void setUsuarios(ArrayList<Userart> usuarios) {
		this.usuarios = usuarios;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Userart getUsuarioElegido() {
		return usuarioElegido;
	}
	public void setUsuarioElegido(Userart usuarioElegido) {
		this.usuarioElegido = usuarioElegido;
		if(usuarioElegido!=null	) {
			if(usuarioElegido.getUserType().equals("A")) {
			admin=true;
		}else{
			admin=false;
		}
		}
	}
	public Userart getUsuarioNuevo() {
		return usuarioNuevo;
	}
	public void setUsuarioNuevo(Userart usuarioNuevo) {
		this.usuarioNuevo = usuarioNuevo;
	}

	@SuppressWarnings("unchecked")
	public void actualizarLista() {
		usuarios=(ArrayList<Userart>)(Object)dao.darLista();
	}
	public String registrarNuevoUsuario() {
		if(buscarporUser(usuarioNuevo.getUserName())) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,new FacesMessage("Error", "el Username ya se encuentra ocupado intenta con otro"));
		return null;
		}
		prepararNuevoUsuario();
		dao= new IUsuarioDao();
		dao.guardar(usuarioNuevo);
		Auditart a= new Auditart();
		a.setAddressIP(ip);
		a.setCreateDate(new Date());
		a.setOperationCrud("R");
		a.setTableName("USUARIO");
		a.setUserId(login.getUsuario().getId());
		a.setTableId((dao.darLista().size()+1));
		new IAuditoriaDao().guardar(a);
		PrimeFaces.current().dialog().closeDynamic("el usuario se ha creado");
		actualizarLista();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Modificación",  "El usuario ha sido Creado") );
      	String contenido="Se le ha generado un nuevo usuario en la plataforma SophyArt \n"
      			+ "Sus datos son \n"
      			+ "Usuario	:	"+usuarioNuevo.getUserName()+"\n"
      			+ "Contraseña	:	"+usuarioNuevo.getPassword();
        try {
        	envioMensajes.enviarMail(usuarioNuevo.getEmailAddress(), "Nuevo Usuario", contenido);
		} catch (Exception e) {
			System.out.println(e);
		}
        return null;
	}
	public void prepararNuevoUsuario() {
		   usuarioNuevo.setDateLastPassword(new Date());
	        usuarioNuevo.setActive("A");
	        usuarioNuevo.setIntentos(0);
	}
	public boolean buscarporUser(String n) {
		
		for (Userart u : usuarios) {
			if(u.getUserName().equalsIgnoreCase(n)) {
				return true;
			}
		}
		
		return false;
	}
	public void agregarNuevoUsuario() {
		
		usuarioNuevo= new Userart();
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/AgregarUsuario", options, null);
	}
	public boolean esAdmin() {
		if(usuarioElegido.getUserType().equals("A")) {
			return false;
		}return true;
	}
	public void eliminarUsuario() {
		if(usuarioElegido.getActive().equals("I")) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null,new FacesMessage("Modificación",  "El no se ha podido modificar ya se encuentra inactivo") );
		}else {
		usuarioElegido.setActive("I");
		dao.actualizar(usuarioElegido);
		actualizarLista();
		Auditart a= new Auditart();
		a.setAddressIP(ip);
		a.setCreateDate(new Date());
		a.setOperationCrud("E");
		a.setTableName("USUARIO");
		a.setUserId(login.getUsuario().getId());
		a.setTableId(usuarioElegido.getId());
		new IAuditoriaDao().guardar(a);
		   FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Modificación",  "El usuario elegido ha cambiado su estado a inactivo") );
		}
		}
//	public boolean filtroEstado(Object valorTabla, Object valorFiltro) {
//if(valorFiltro.equals("Activo") && valorTabla.equals("A")){
//	return true;
//}
//	return false;
//}
	    public String estadoUsuario(String a) {
	    	if(a.equals("A")) {
	    		return "Activo";
	    	}else {
	    		return "Inactivo";
	    	}
	    }
//        public boolean filtroTipo(Object valorTabla, Object valorFiltro) {
//if(valorFiltro.equals(tipoUsuario((String)valorTabla))) {
//	return true;
//}return false;
//        } esto se soluciona solo con el filterby usando el metodo respectivo a cada tipo o cada estado
        public String tipoUsuario(String a) {
        	if("A".equals(a)) {
        		return "Administrador";
        	}else if("O".equals(a)) {
        		return "Auxiliar Adm";
        	}
        	return "Empleado";
        }
        public void actualizarElegido() {
        	PrimeFaces.current().dialog().closeDynamic("");
    		dao.actualizar(usuarioElegido);
    		Auditart a= new Auditart();
    		a.setAddressIP(ip);
    		a.setCreateDate(new Date());
    		a.setOperationCrud("A");
    		a.setTableName("USUARIO");
    		a.setUserId(login.getUsuario().getId());
    		a.setTableId(usuarioElegido.getId());
    		new IAuditoriaDao().guardar(a);
//             ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//             try {
//				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
             FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage(null,new FacesMessage("Modificación",  "El usuario ha sido modificado") );
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
