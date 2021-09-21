package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametroart database table.
 * 
 */
@Entity
@NamedQuery(name="Parametroart.findAll", query="SELECT p FROM Parametroart p")
public class Parametroart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String codigoParametro;

	private String descripcion;

	private String tipoParametro;

	private int valorNumerico;

	private String valorTexto;

	public Parametroart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoParametro() {
		return this.codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoParametro() {
		return this.tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public int getValorNumerico() {
		return this.valorNumerico;
	}

	public void setValorNumerico(int valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public String getValorTexto() {
		return this.valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

}