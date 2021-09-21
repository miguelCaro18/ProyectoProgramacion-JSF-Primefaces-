package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoriaart database table.
 * 
 */
@Entity
@NamedQuery(name="Categoriaart.findAll", query="SELECT c FROM Categoriaart c")
public class Categoriaart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private short activo;

	private String nombre;

	public Categoriaart() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public short getActivo() {
		return this.activo;
	}

	public void setActivo(short activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}