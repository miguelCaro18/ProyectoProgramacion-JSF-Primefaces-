package com.sophyart.model.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the auditart database table.
 * 
 */
@Entity
@NamedQuery(name="Auditart.findAll", query="SELECT a FROM Auditart a")
public class Auditart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String addressIP;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String operationCrud;

	private int tableId;

	private String tableName;

	private int userId;

	public Auditart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressIP() {
		return this.addressIP;
	}

	public void setAddressIP(String addressIP) {
		this.addressIP = addressIP;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOperationCrud() {
		return this.operationCrud;
	}

	public void setOperationCrud(String operationCrud) {
		this.operationCrud = operationCrud;
	}

	public int getTableId() {
		return this.tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}