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
public class ContentBaseTable implements Serializable {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "shortdesc", length=250)
	private String shortDesc;
	
	@Column(name = "title", length=250)
	private String title;
	
	
	@Column(name = "thumbnail", length=250)
	private String thumbnail;
	
	@Column(name = "tag", columnDefinition="TEXT")
	private String tag;
	
	@Column(name = "decription", columnDefinition="TEXT")
	private String decription;

	
	
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

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
 
	
}
