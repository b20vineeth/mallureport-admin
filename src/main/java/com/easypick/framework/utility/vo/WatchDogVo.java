package com.easypick.framework.utility.vo;

import java.util.Map;

import org.hibernate.Session;
 

public class WatchDogVo {
	
	Session sessionString;
	Map<String, String> input;
	String module;
	String subModule;
	
	
	
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
