package com.easypick.framework.utility.vo;

import java.util.Date;

public class ConfigurationVo implements AbstractVo {

	private Integer configurationId ;
	private String module;
	private String subModule;
	private String controllerClass;
	private String status="Y";
	private Date updatedDate;
	private Date validityTo;
	private Date validityFrom;
	private Date createdDate;
	private Date createdBy;
	private Date createdUser;
	
	private String userId;
	private String userName;
	public Integer getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public String getControllerClass() {
		return controllerClass;
	}
	public void setControllerClass(String controllerClass) {
		this.controllerClass = controllerClass;
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
	public Date getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Date createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Date createdUser) {
		this.createdUser = createdUser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}
