package com.easypick.admin.entity;
 
import java.util.Date; 

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
 

@Entity
@Table(name = "datasetup", uniqueConstraints = { @UniqueConstraint(columnNames = { "data_code"}) } )
public class DataSetup   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	 
	@Column(name = "data_code")
	private String code;
	
	@Column(name = "type")
	private String type;

	@Column(name = "data_name")
	private String dateName;
	
	
	@Column(name = "status")
	private String status="Y";
	
	@Column(name = "updated_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;
	
	@Column(name = "tagidx" ,length=1)
	private Integer tagidx;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateon() {
		return updateon;
	}

	public void setUpdateon(Date updateon) {
		this.updateon = updateon;
	}

	public Integer getTagidx() {
		return tagidx;
	}

	public void setTagidx(Integer tagidx) {
		this.tagidx = tagidx;
	}
	 
  
}
