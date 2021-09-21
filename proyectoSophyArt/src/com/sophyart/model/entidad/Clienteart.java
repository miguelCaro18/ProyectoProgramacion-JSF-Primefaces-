package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the clienteart database table.
 * 
 */
@Entity
@NamedQuery(name="Clienteart.findAll", query="SELECT c FROM Clienteart c")
public class Clienteart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="cpocrdto_clnte")
	private BigDecimal cpocrdtoClnte;

	@Column(name="crreo_clnte")
	private String crreoClnte;

	@Column(name="drccion_clnte")
	private String drccionClnte;

	@Column(name="estdo_clnte")
	private String estdoClnte;

	@Column(name="nit_clnte")
	private String nitClnte;

	@Column(name="nmbre_clnte")
	private String nmbreClnte;

	@Column(name="sldoact_clnte")
	private BigDecimal sldoactClnte;

	@Column(name="tipo_clnte")
	private String tipoClnte;

	@Column(name="tlfno_clnte")
	private String tlfnoClnte;

	public Clienteart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCpocrdtoClnte() {
		return this.cpocrdtoClnte;
	}

	public void setCpocrdtoClnte(BigDecimal cpocrdtoClnte) {
		this.cpocrdtoClnte = cpocrdtoClnte;
	}

	public String getCrreoClnte() {
		return this.crreoClnte;
	}

	public void setCrreoClnte(String crreoClnte) {
		this.crreoClnte = crreoClnte;
	}

	public String getDrccionClnte() {
		return this.drccionClnte;
	}

	public void setDrccionClnte(String drccionClnte) {
		this.drccionClnte = drccionClnte;
	}

	public String getEstdoClnte() {
		return this.estdoClnte;
	}

	public void setEstdoClnte(String estdoClnte) {
		this.estdoClnte = estdoClnte;
	}

	public String getNitClnte() {
		return this.nitClnte;
	}

	public void setNitClnte(String nitClnte) {
		this.nitClnte = nitClnte;
	}

	public String getNmbreClnte() {
		return this.nmbreClnte;
	}

	public void setNmbreClnte(String nmbreClnte) {
		this.nmbreClnte = nmbreClnte;
	}

	public BigDecimal getSldoactClnte() {
		return this.sldoactClnte;
	}

	public void setSldoactClnte(BigDecimal sldoactClnte) {
		this.sldoactClnte = sldoactClnte;
	}

	public String getTipoClnte() {
		return this.tipoClnte;
	}

	public void setTipoClnte(String tipoClnte) {
		this.tipoClnte = tipoClnte;
	}

	public String getTlfnoClnte() {
		return this.tlfnoClnte;
	}

	public void setTlfnoClnte(String tlfnoClnte) {
		this.tlfnoClnte = tlfnoClnte;
	}

}