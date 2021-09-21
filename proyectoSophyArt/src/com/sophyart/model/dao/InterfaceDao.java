package com.sophyart.model.dao;


import java.util.List;

public interface InterfaceDao {

	public void guardar(Object o);
	public void actualizar(Object o);
	public void remover(Object o);
	public Object darEntidad(long id);
	public List<Object> darLista();
	
}
