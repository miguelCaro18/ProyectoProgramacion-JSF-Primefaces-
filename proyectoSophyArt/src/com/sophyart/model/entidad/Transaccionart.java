package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaccionart database table.
 * 
 */
@Entity
@NamedQuery(name="Transaccionart.findAll", query="SELECT t FROM Transaccionart t")
public class Transaccionart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String activo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTransaccion;

	private short idClasificado;

	private short idComprador;

	private short idExposicion;

	private short idProducto;

	private short idVendedor;

	private int valorTransaccion;

	public Transaccionart() {
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

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public short getIdClasificado() {
		return this.idClasificado;
	}

	public void setIdClasificado(short idClasificado) {
		this.idClasificado = idClasificado;
	}

	public short getIdComprador() {
		return this.idComprador;
	}

	public void setIdComprador(short idComprador) {
		this.idComprador = idComprador;
	}

	public short getIdExposicion() {
		return this.idExposicion;
	}

	public void setIdExposicion(short idExposicion) {
		this.idExposicion = idExposicion;
	}

	public short getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(short idProducto) {
		this.idProducto = idProducto;
	}

	public short getIdVendedor() {
		return this.idVendedor;
	}

	public void setIdVendedor(short idVendedor) {
		this.idVendedor = idVendedor;
	}

	public int getValorTransaccion() {
		return this.valorTransaccion;
	}

	public void setValorTransaccion(int valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}

}