package com.easypick.framework.utility.vo;

import java.util.List;
import java.util.Map;

import com.easypick.admin.vo.GallerySetupVo; 
import com.easypick.framework.utility.exception.ErrorVo;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo {

	Object object; 
	String screenMode;
	Boolean result;
	public List<? extends AbstractVo> objectList;
	public Map<String, ? extends AbstractVo> objectMap;
	public Map<String, List<? extends AbstractVo>> objectMapList;
	Page page;
	Boolean response = false;
	List<ErrorVo> errors;
	public Map<String,String> stringMap;
	String resposeObjectList;
	List<GallerySetupVo> galleryList;
	String resposeObject;
	String version = "VER_ICO_0.01.01";

	Object filterObj;
	String module;
	String subModule;
	String domain = "http://localhost:8080";
	Map<String,Map<String, List<? extends AbstractVo>>> jsonobj;
	String event;
	
	Integer id;
	

	public Map<String, Map<String, List<? extends AbstractVo>>> getJsonobj() {
		return jsonobj;
	}

	public void setJsonobj(Map<String, Map<String, List<? extends AbstractVo>>> jsonobj) {
		this.jsonobj = jsonobj;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public Object getFilterObj() {
		return filterObj;
	}

	public void setFilterObj(Object filterObj) {
		this.filterObj = filterObj;
	}

	public List<ErrorVo> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorVo> errors) {
		this.errors = errors;
	}

	public List<GallerySetupVo> getGalleryList() {
		return galleryList;
	}

	public void setGalleryList(List<GallerySetupVo> galleryList) {
		this.galleryList = galleryList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Map<String, List<? extends AbstractVo>> getObjectMapList() {
		return objectMapList;
	}

	public void setObjectMapList(Map<String, List<? extends AbstractVo>> objectMapList) {
		this.objectMapList = objectMapList;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	 
	public String getScreenMode() {
		return screenMode;
	}

	public void setScreenMode(String screenMode) {
		this.screenMode = screenMode;
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

	public Map<String, ? extends AbstractVo> getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(Map<String, ? extends AbstractVo> objectMap) {
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
