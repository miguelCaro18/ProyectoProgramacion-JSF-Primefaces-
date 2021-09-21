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
import javax.servlet.http.Part;

import org.primefaces.PrimeFaces;

import com.sophyart.model.dao.implement.IAuditoriaDao;
import com.sophyart.model.dao.implement.IProductosDao;
import com.sophyart.model.entidad.Auditart;
import com.sophyart.model.entidad.Productoart;



@ManagedBean(name="beanProdu")
@SessionScoped
public class BeanProductos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Productoart> Productos;
	private ArrayList<Productoart> ProductosElegidos;
	private Productoart  ProductoElegido;
	private Productoart ProductoNuevo;
	private IProductosDao dao;
	private Part archivo;
	private String ip;
	private LoginBean login;

	public Part getArchivo() {
		return archivo;
	}

	public void setArchivo(Part archivo) {
		this.archivo = archivo;
	}

	public void eliminarProducto() {
		if(ProductoElegido.getActivo().equals("I")) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null,new FacesMessage("Modificación",  "El Producto no se ha podido modificar ya se encuentra inactivo") );
		}else {
			ProductoElegido.setActivo("I");
		dao.actualizar(ProductoElegido);
		actualizarLista();
		Auditart a= new Auditart();
		a.setAddressIP(ip);
		a.setCreateDate(new Date());
		a.setOperationCrud("E");
		a.setTableName("PRODUCTO");
		a.setUserId(login.getUsuario().getId());
		a.setTableId((ProductoElegido.getId()));
		new IAuditoriaDao().guardar(a);
 
		   FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Modificación",  "El Producto elegido ha cambiado su estado a inactivo") );
		}
	}
	
	public ArrayList<Productoart> getProductosElegidos() {
		
		return ProductosElegidos;
	}
	public List<String> estados(){
		List<String> es=new ArrayList<String>();
		es.add("Activo");
		es.add("Inactivo");
		return es;
	}
	
	public void setProductosElegidos(ArrayList<Productoart> ProductosElegidos) {
		this.ProductosElegidos = ProductosElegidos;
	}
	public IProductosDao getDao() {
		return dao;
	}
	public void setDao(IProductosDao dao) {
		this.dao = dao;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Productoart> getProductos() {
		Productos=(ArrayList<Productoart>)(Object)dao.darLista();
		return Productos;
	}
	public void setProductos(ArrayList<Productoart> Productos) {
		this.Productos = Productos;
	}
	public Productoart getProductoElegido() {
		return ProductoElegido;
	}
	public void setProductoElegido(Productoart ProductoElegido) {
		this.ProductoElegido = ProductoElegido;
	}
	public Productoart getProductoNuevo() {
		return ProductoNuevo;
	}
	public void setProductoNuevo(Productoart ProductoNuevo) {
		this.ProductoNuevo = ProductoNuevo;
	}
	@SuppressWarnings("unchecked")
	public BeanProductos() {
		FacesContext fc = FacesContext.getCurrentInstance();
		login=fc.getApplication().evaluateExpressionGet(fc, "#{loginBean}", LoginBean.class);
		ProductoElegido= new Productoart();
		ProductoNuevo= new Productoart();
		Productos= new ArrayList<>();
		dao= new IProductosDao();
		Productos=(ArrayList<Productoart>)(Object)dao.darLista();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = request.getRemoteAddr();
		}
		ip=ipAddress;
	}
	public void actualizarLista() {
		Productos= new ArrayList<>();
		List<Object> obj= dao.darLista();
		for (Object object : obj) {
			Productos.add((Productoart)object);
		}
	}
    public String estadoProducto(String a) {
    	if(a.equals("A")) {
    		return "Activo";
    	}else {
    		return "Inactivo";
    	}
    }
    public void modificarElegido() {
    	dao.actualizar(ProductoElegido);
    	PrimeFaces.current().dialog().closeDynamic("");
		ProductoElegido.setActivo("I");
		dao.actualizar(ProductoElegido);
		actualizarLista();
		Auditart a= new Auditart();
		a.setAddressIP(ip);
		a.setCreateDate(new Date());
		a.setOperationCrud("M");
		a.setTableName("PRODUCTO");
		a.setUserId(login.getUsuario().getId());
		a.setTableId((ProductoElegido.getId()));
		new IAuditoriaDao().guardar(a);
    }
    
    public void agregarProducto() {
    	
    	String a=archivo.getSubmittedFileName();
    	boolean r1=a.endsWith(".jpg");
    	boolean r2=a.endsWith("JPG");
    	if(r1||r2) {
    		System.out.println("documento valido");
				ProductoNuevo.setRutaFoto(a);
			   	ProductoNuevo.setActivo("A");
			   	dao.guardar(ProductoNuevo);
			   	PrimeFaces.current().dialog().closeDynamic("");
			   	Auditart a1= new Auditart();
				a1.setAddressIP(ip);
				a1.setCreateDate(new Date());
				a1.setOperationCrud("A");
				a1.setTableName("PRODUCTO");
				a1.setUserId(login.getUsuario().getId());
				a1.setTableId((dao.darLista().size()+1));
				new IAuditoriaDao().guardar(a1);
			
    	}else {
    		FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,new FacesMessage("Error",  "El documento no es valido (jpg o JPG extensión") );
    	}

    	
    }
	
	public void agregarNuevoProducto() {
 
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/AgregarProducto", options, null);
	}
	public void modificarProducto() {
		 
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/modificarProducto", options, null);
	}

}
