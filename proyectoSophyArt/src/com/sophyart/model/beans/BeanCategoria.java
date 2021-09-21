package com.sophyart.model.beans;


import java.io.Serializable;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import com.sophyart.model.dao.implement.ICategoriaDao;
import com.sophyart.model.entidad.Categoriaart;



@ManagedBean(name="beanCate")
@SessionScoped
public class BeanCategoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Categoriaart> categorias;
	private ArrayList<Categoriaart> categoriasElegidas;
	private Categoriaart nueva;
	private ICategoriaDao dao;
	/**
	 * Constructor de la clase
	 * @return
	 */
	public BeanCategoria() {
		dao= new ICategoriaDao();
		categorias= (ArrayList<Categoriaart>)(Object)dao.darLista();
		categoriasElegidas= new ArrayList<>();
		nueva = new Categoriaart();
		
	}
	public void actualizar() {
		categorias= (ArrayList<Categoriaart>)(Object)dao.darLista();
	}

	public ArrayList<Categoriaart> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoriaart> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<Categoriaart> getCategoriasElegidas() {
		return categoriasElegidas;
	}

	public void setCategoriasElegidas(ArrayList<Categoriaart> categoriasElegidas) {
		this.categoriasElegidas = categoriasElegidas;
	}

	public ICategoriaDao getDao() {
		return dao;
	}

	public void setDao(ICategoriaDao dao) {
		this.dao = dao;
	}

	public Categoriaart getNueva() {
		return nueva;
	}
	public void setNueva(Categoriaart nueva) {
		this.nueva = nueva;
	}
	public void cambiarNombre(String n) {
		if(n==null) {
			
		}else {
		if("".equals(n)) {
			
		}else {
			
		}
		}
	}
	public void agregarNuevaCategoria() {
		
	}
	public String binario(short i) {
		int a=(int)i;
		if(i==0) {
			return "inactivo";
		}return "activo";
	}
	public Categoriaart getModificar() {
		return modificar;
	}
	public void setModificar(Categoriaart modificar) {
		this.modificar = modificar;
	}
	private Categoriaart modificar;

}
