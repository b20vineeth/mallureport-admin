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
	@JoinColumn(name = "upduserid", nullable = false)
	private UserSetup  upUser;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updby", nullable = false)
	private UserSetup  updatedBy; 
	@Column(name = "status", length=1)
	private String status="Y";
	@Column(name = "upddat", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@Column(name = "validto", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityTo;
	@Column(name = "validfrm", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityFrom;
	@Column(name = "createddate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "cmpcod",length=10)
	private String companyCode;
	
	 
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
	public Date getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}
	public Date getValidityFrom() {
		return validityFrom;
	}
	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public UserSetup getUpUser() {
		return upUser;
	}
	public void setUpUser(UserSetup upUser) {
		this.upUser = upUser;
	}
	public UserSetup getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserSetup updatedBy) {
		this.updatedBy = updatedBy;
	}
 
	
}
