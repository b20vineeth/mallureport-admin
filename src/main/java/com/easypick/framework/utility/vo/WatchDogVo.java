package com.easypick.framework.utility.vo;

import java.util.Map;

import org.hibernate.Session;

import com.easypick.admin.vo.UserSetupVo;
 

public class WatchDogVo {
	
	Session sessionString;
	Map<String, String> input;
	Map<String, String> category;
	String module;
	String subModule;
	Integer keyLength;
	String cmpcode;
	String type;
	Page page;
	UserSetupVo userSetupVo;
	
	
	 

	public UserSetupVo getUserSetupVo() {
		return userSetupVo;
	}

	public void setUserSetupVo(UserSetupVo userSetupVo) {
		this.userSetupVo = userSetupVo;
	}

	public Map<String, String> getCategory() {
		return category;
	}

	public void setCategory(Map<String, String> category) {
		this.category = category;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCmpcode() {
		return cmpcode;
	}

	public void setCmpcode(String cmpcode) {
		this.cmpcode = cmpcode;
	}

	public Integer getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(Integer keyLength) {
		this.keyLength = keyLength;
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

	public Map<String, String> getInput() {
		return input;
	}

	public void setInput(Map<String, String> input) {
		this.input = input;
	}

	public Session getSessionString() {
		return sessionString;
	}

	public void setSessionString(Session sessionString) {
		this.sessionString = sessionString;
	} 
	
	
}
