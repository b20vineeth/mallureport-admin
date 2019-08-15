package com.easypick.admin.entity;


import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.Table;  


@Entity
@Table(name = "configuration")



public class Configuration  extends  BaseTable {


	private static final long serialVersionUID = 1L;
	 
 
	@Id
	@GeneratedValue 
	@Column(name = "configuration_id")
	private Integer configurationId ;
	@Column(name = "module", length=70,nullable=false)
	private String module;
	@Column(name = "submodule", length=125,nullable=false)
	private String subModule;

	@Column(name = "controller_class", length=250)
	private String controllerClass;

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
	 
	
	
	
	 





}
