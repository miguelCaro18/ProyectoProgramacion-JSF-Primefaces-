package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productoart database table.
 * 
 */
@Entity
@NamedQuery(name="Productoart.findAll", query="SELECT p FROM Productoart p")
public class Productoart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String activo;

	private String descripcion;

	private String nombre;

	private String rutaFoto;

	private int valorProducto;

	public Productoart() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaFoto() {
		return this.rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public int getValorProducto() {
		return this.valorProducto;
	}

	public void setValorProducto(int valorProducto) {
		this.valorProducto = valorProducto;
	}

}