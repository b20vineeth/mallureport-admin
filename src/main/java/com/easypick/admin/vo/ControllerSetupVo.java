package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class ControllerSetupVo implements AbstractVo {

	private String name;
	private String dao;
	private Integer resultSize;
	private String condition;
	private String pagination="Y";
	
	
	
	

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public Integer getResultSize() {
		return resultSize;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}

}
