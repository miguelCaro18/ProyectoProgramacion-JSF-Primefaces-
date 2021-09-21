package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the exposicionart database table.
 * 
 */
@Entity
@NamedQuery(name="Exposicionart.findAll", query="SELECT e FROM Exposicionart e")
public class Exposicionart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String activo;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFinal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicial;

	private short idProducto;

	private String tipoExposicion;

	public Exposicionart() {
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

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public short getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(short idProducto) {
		this.idProducto = idProducto;
	}

	public String getTipoExposicion() {
		return this.tipoExposicion;
	}

	public void setTipoExposicion(String tipoExposicion) {
		this.tipoExposicion = tipoExposicion;
	}

}