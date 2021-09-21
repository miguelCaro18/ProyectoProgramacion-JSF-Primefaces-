package com.sophyart.model.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import com.sophyart.model.dao.implement.IAuditoriaDao;
import com.sophyart.model.dao.implement.IParametroDao;
import com.sophyart.model.entidad.Auditart;
import com.sophyart.model.entidad.Parametroart;


@ManagedBean(name="beanPara")
@SessionScoped
public class BeanParametros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Parametroart> parametros;
	private ArrayList<Parametroart> parametrosElegidos;
	private Parametroart elegido;
	private Parametroart nuevoPara;
	private  IParametroDao dao;
	private String parametroNuevoString;
	private String pasarParametroElegido;
	private LoginBean login;
	private String ip;
	
	public String getPasarParametroElegido() {
		if(elegido.getTipoParametro()==null){
			
		}if(elegido.getTipoParametro().equals("T")) {
			return elegido.getValorTexto();
		}return ""+elegido.getValorNumerico();
	}
	public void setPasarParametroElegido(String pasarParametroElegido) {
		this.pasarParametroElegido = pasarParametroElegido;
	}

	public String darParametroElegidob() {
		if(elegido.getTipoParametro().equals("T")) {
			return "/[a-z_]/i";
		}return "/[0-9_]/i";
	}
	
	public String getParametroNuevoString() {
		return parametroNuevoString;
	}
	public void setParametroNuevoString(String parametroNuevoString) {
		this.parametroNuevoString = parametroNuevoString;
	}
	public ArrayList<Parametroart> getUsuariosElegidos() {
		return parametrosElegidos;
	}
	public List<String> estados(){
		List<String> es=new ArrayList<String>();
		es.add("Activo");
		es.add("Inactivo");
		return es;
	}
	public ArrayList<Parametroart> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<Parametroart> parametros) {
		this.parametros = parametros;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Parametroart> getParametrosElegidos() {
		parametrosElegidos=(ArrayList<Parametroart>)(Object)dao.darLista();
		return parametrosElegidos;
	}

	public void setParametrosElegidos(ArrayList<Parametroart> parametrosElegidos) {
		this.parametrosElegidos = parametrosElegidos;
	}

	public Parametroart getElegido() {
		return elegido;
	}

	public void setElegido(Parametroart elegido) {
		this.elegido = elegido;
	}

	public Parametroart getNuevoPara() {
		return nuevoPara;
	}

	public void setNuevoPara(Parametroart nuevoPara) {
		this.nuevoPara = nuevoPara;
	}

	public IParametroDao getDao() {
		return dao;
	}

	public void setDao(IParametroDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public BeanParametros() {
		FacesContext fc = FacesContext.getCurrentInstance();
		login=fc.getApplication().evaluateExpressionGet(fc, "#{loginBean}", LoginBean.class);
		elegido= new Parametroart();
		nuevoPara= new Parametroart();
		parametros= new ArrayList<>();
		dao= new IParametroDao();
		parametroNuevoString="";
		pasarParametroElegido="";
		parametrosElegidos=(ArrayList<Parametroart>)(Object)dao.darLista();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = request.getRemoteAddr();
		}
		ip=ipAddress;
	}
	@SuppressWarnings("unchecked")
	public void actualizarLista() {
		parametrosElegidos=(ArrayList<Parametroart>)(Object)dao.darLista();
	}
	public String modificarTipo(String a) {
		if(a.equals("T")) {
			return ("Texto");
		}return "Numerico";
	}
	public boolean parametro() {
		if(nuevoPara.getTipoParametro()==null) {
			return false;
		}return true;
	}
	public String agregarMask() {
		if(nuevoPara.getTipoParametro()!=null) {
		if(nuevoPara.getTipoParametro().equals("N")) {
			return "pnum";
		}
		}return "alpha";
	}
	public void stringParametroNuevo(String a) {
		parametroNuevoString=a;
	}
	public void modificarElegidoDatos() {
		try {
			if(elegido.getTipoParametro().equals("T")) {
				elegido.setValorTexto(pasarParametroElegido);
			}else {
			elegido.setValorNumerico(Integer.parseInt(pasarParametroElegido));
		}
			PrimeFaces.current().dialog().closeDynamic("el parámetro se ha creado");
			dao.actualizar(elegido);
			Auditart a1= new Auditart();
			a1.setAddressIP(ip);
			a1.setCreateDate(new Date());
			a1.setOperationCrud("M");
			a1.setTableName("PARAMETRO");
			a1.setUserId(login.getUsuario().getId());
			a1.setTableId(elegido.getId());
			new IAuditoriaDao().guardar(a1);
			
			
		}catch (Exception e) {
		
		}
		
	}
	public  String agregarParaDatos() {
		boolean e=false;
		for (Parametroart parametroart : parametros) {
			if(parametroart.getCodigoParametro().equals(nuevoPara.getCodigoParametro())) {
				e=true;
			}
		}
		if(e) {
			System.out.println("ya estaba");
			 FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null,new FacesMessage("Error",  "El parámetro con el codigo ingresado ya existe ") );
			return null;
		}else {
		try {
			int p=Integer.parseInt(parametroNuevoString);
			nuevoPara.setValorNumerico(p);
			nuevoPara.setTipoParametro("N");
			nuevoPara.setValorTexto("****");
			e=false;
		} catch (Exception e2) {		

			boolean a = Pattern.matches("^[a-zA-Z]*$", parametroNuevoString);

			if(a) {
				e=false;
				nuevoPara.setValorTexto(parametroNuevoString);
				nuevoPara.setTipoParametro("T");
				nuevoPara.setValorNumerico(0);
			}else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,new FacesMessage("Error",  "El parametro tiene que ser solo numerico o solo alfabetico  ") );
				return null;
			}
	}}if(!e) {
		dao= new IParametroDao();
		dao.guardar(nuevoPara);
		PrimeFaces.current().dialog().closeDynamic("el parámetro se ha creado");
	   	Auditart a1= new Auditart();
		a1.setAddressIP(ip);
		a1.setCreateDate(new Date());
		a1.setOperationCrud("A");
		a1.setTableName("PARAMETRO");
		a1.setUserId(login.getUsuario().getId());
		a1.setTableId((dao.darLista().size()+1));
		new IAuditoriaDao().guardar(a1);
	}
		
		return null;
	}

	public String darParametro(Parametroart p) {
		if(p.getTipoParametro().equals("T")) {
			return p.getValorTexto();
		}return p.getValorNumerico()+"";
	}
	
	public void agregarNuevoParametro() {
		nuevoPara=new Parametroart();
		nuevoPara.setValorNumerico(0);
		nuevoPara.setValorTexto("");
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/AgregarParametro", options, null);
	}

	public void modificarParametro() {
		 
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/modificarParametro", options, null);
	}

}
