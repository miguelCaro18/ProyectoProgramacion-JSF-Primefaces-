package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the userart database table.
 * 
 */
@Entity
@NamedQuery(name="Userart.findAll", query="SELECT u FROM Userart u")
public class Userart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastPassword;

	private String emailAddress;

	private String fullName;

	private int intentos;

	private String password;

	private String phoneNumber;

	private String userName;

	private String userType;

	public Userart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getDateLastPassword() {
		return this.dateLastPassword;
	}

	public void setDateLastPassword(Date dateLastPassword) {
		this.dateLastPassword = dateLastPassword;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getIntentos() {
		return this.intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}