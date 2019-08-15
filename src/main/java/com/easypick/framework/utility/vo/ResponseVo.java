package com.easypick.framework.utility.vo;

import java.util.List;
import java.util.Map;
 

public class ResponseVo {

	Object object;
	List<Object> objectlist;
	String ScreenMode;
	Boolean result;
	public List <?  extends AbstractVo> objectList;
	 
	Map<Integer,?  extends AbstractVo> objectMap;
	 
	Boolean response=false;
	 
	String resposeObjectList;
	 
	String resposeObject;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<Object> getObjectlist() {
		return objectlist;
	}

	public void setObjectlist(List<Object> objectlist) {
		this.objectlist = objectlist;
	}

	public String getScreenMode() {
		return ScreenMode;
	}

	public void setScreenMode(String screenMode) {
		ScreenMode = screenMode;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public List<? extends AbstractVo> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<? extends AbstractVo> objectList) {
		this.objectList = objectList;
	}

	public Map<Integer, ? extends AbstractVo> getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(Map<Integer, ? extends AbstractVo> objectMap) {
		this.objectMap = objectMap;
	}

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}

	public String getResposeObjectList() {
		return resposeObjectList;
	}

	public void setResposeObjectList(String resposeObjectList) {
		this.resposeObjectList = resposeObjectList;
	}

	public String getResposeObject() {
		return resposeObject;
	}

	public void setResposeObject(String resposeObject) {
		this.resposeObject = resposeObject;
	}
	
 
}
