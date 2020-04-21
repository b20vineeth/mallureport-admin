package com.easypick.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@MappedSuperclass
public class BaseTable implements Serializable {

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdby", nullable = false)
	private UserSetup  createdBy;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updby", nullable = false)
	private UserSetup  updatedBy; 
	
	
	@Column(name = "upddat", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
 
	@Column(name = "createddate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "cmpcod",length=5)
	private String companyCode;
	
	@Column(name = "tagidx",length=1)
	private Integer tagidx=0;
	
	@Column(name = "status", length=1)
	private String status="Y";
	
	 
	public Integer getTagidx() {
		return tagidx;
	}
	public void setTagidx(Integer tagidx) {
		this.tagidx = tagidx;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	 
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	 
	public UserSetup getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserSetup createdBy) {
		this.createdBy = createdBy;
	}
	public UserSetup getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserSetup updatedBy) {
		this.updatedBy = updatedBy;
	}
	 
	
}
