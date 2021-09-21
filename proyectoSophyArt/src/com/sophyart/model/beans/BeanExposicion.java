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

import org.primefaces.PrimeFaces;

import com.sophyart.model.dao.implement.ICategoriaDao;
import com.sophyart.model.dao.implement.IExposicionDao;
import com.sophyart.model.entidad.Categoriaart;
import com.sophyart.model.entidad.Exposicionart;



/**
 * @author PC-PERSONAL
 *
 */
@ManagedBean(name="beanExpo")
@SessionScoped
public class BeanExposicion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Exposicionart> expos;
	private ArrayList<Exposicionart> ExposEle;
	private Exposicionart expoElegida;
	private Exposicionart expoNueva;
	private IExposicionDao dao;
	 private List<Date> rango;
	
	/**
	 * Constructor de la clase BeanExposición
	 * se inicial los arreglos y las variables de exposición 
	 * @return
	 */
	 public void registrarNuevaExposicion() {
		 FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,new FacesMessage("Error", "Hay un error con la fecha de la Exposción"));
	 
	 }
	 
	public BeanExposicion(){
		expoElegida= new Exposicionart();
		expoNueva= new Exposicionart();
		expos= new ArrayList<>();
		ExposEle= new ArrayList<>();
		dao= new IExposicionDao();
		rango= new ArrayList<>();
	}
	public  void itemModificar() {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("height", 400);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/AgregarExposicion", options, null);
	}
	public void itemEliminar() {
		
	}
	public void dialogoRegistrar() {
		listaCategoriasString= darCategoria();
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("height", 400);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        PrimeFaces.current().dialog().openDynamic("/vistasAdm/ModificarExposicion", options, null);
	}
	public ArrayList<Exposicionart> getExpos() {
		return expos;
	}
	public void setExpos(ArrayList<Exposicionart> expos) {
		this.expos = expos;
	}
	public List<Date> getRango() {
		return rango;
	}
	public void setRango(List<Date> rango) {
		this.rango = rango;
	}
	public ArrayList<Exposicionart> getExposEle() {
		return ExposEle;
	}
	public void setExposEle(ArrayList<Exposicionart> exposEle) {
		ExposEle = exposEle;
	}

	public Exposicionart getExpoElegida() {
		return expoElegida;
	}
	public void setExpoElegida(Exposicionart expoElegida) {
		this.expoElegida = expoElegida;
	}
	public Exposicionart getExpoNueva() {
		return expoNueva;
	}
	public void setExpoNueva(Exposicionart expoNueva) {
		this.expoNueva = expoNueva;
	}
	public IExposicionDao getDao() {
		return dao;
	}
	public void setDao(IExposicionDao dao) {
		this.dao = dao;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<String> darCategoria(){
		ArrayList<String> ar= new ArrayList<>();
		cate= new ArrayList<>();
		cate=(ArrayList<Categoriaart>)(Object)new ICategoriaDao().darLista();
		for (Categoriaart c : cate) {
			ar.add(c.getNombre());
		}
		return ar;
	}
	public short darId(String cate) {
		for (Categoriaart c : this.cate) {
			if(c.getNombre().equals(cate)) {
				return c.getId();
			}
		}
		return (short)0;
	}

	private ArrayList<Categoriaart> cate = new ArrayList<>();
	private ArrayList<String> listaCategoriasString;
	
	

}
