package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empresaart database table.
 * 
 */
@Entity
@NamedQuery(name="Empresaart.findAll", query="SELECT e FROM Empresaart e")
public class Empresaart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	@Column(name="correo_empresa")
	private String correoEmpresa;

	@Column(name="direccion_empresa")
	private String direccionEmpresa;

	@Column(name="estado_empresa")
	private String estadoEmpresa;

	@Column(name="nit_empresa")
	private String nitEmpresa;

	@Column(name="razon_social_empresa")
	private String razonSocialEmpresa;

	@Column(name="telefonos_empresa")
	private String telefonosEmpresa;

	public Empresaart() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getCorreoEmpresa() {
		return this.correoEmpresa;
	}

	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}

	public String getDireccionEmpresa() {
		return this.direccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public String getEstadoEmpresa() {
		return this.estadoEmpresa;
	}

	public void setEstadoEmpresa(String estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}

	public String getNitEmpresa() {
		return this.nitEmpresa;
	}

	public void setNitEmpresa(String nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}

	public String getRazonSocialEmpresa() {
		return this.razonSocialEmpresa;
	}

	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	public String getTelefonosEmpresa() {
		return this.telefonosEmpresa;
	}

	public void setTelefonosEmpresa(String telefonosEmpresa) {
		this.telefonosEmpresa = telefonosEmpresa;
	}

}