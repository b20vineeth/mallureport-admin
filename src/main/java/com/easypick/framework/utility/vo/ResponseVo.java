package com.easypick.framework.utility.vo;

import java.util.List;
import java.util.Map;

import com.easypick.admin.vo.GalleryVo;


public class ResponseVo {

	Object object;
	List<Object> objectlist;
	String screenMode;
	Boolean result;
	public List <?  extends AbstractVo> objectList;
	Map<String,?  extends AbstractVo> objectMap;
	Map<String, List<? extends AbstractVo>> objectMapList;
	Page page;
	Boolean response=false;

	String resposeObjectList;
	List<GalleryVo> galleryList;
	String resposeObject;
	String version="VER_ICO_0.01.01";




	public List<GalleryVo> getGalleryList() {
		return galleryList;
	}

	public void setGalleryList(List<GalleryVo> galleryList) {
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

	public List<Object> getObjectlist() {
		return objectlist;
	}

	public void setObjectlist(List<Object> objectlist) {
		this.objectlist = objectlist;
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
