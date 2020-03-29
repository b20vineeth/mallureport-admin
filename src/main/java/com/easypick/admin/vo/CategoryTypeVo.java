package com.easypick.admin.vo;
 
 

import com.easypick.framework.utility.vo.AbstractVo;

public class CategoryTypeVo  implements AbstractVo{
 
	private Integer catTypeId;
	private String catTypeCode;
	private String catTypeName;
	private String status="Y";
	private String page;
	private boolean pagination=true;
	
	 
	public boolean isPagination() {
		return pagination;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public Integer getCatTypeId() {
		return catTypeId;
	}
	public void setCatTypeId(Integer catTypeId) {
		this.catTypeId = catTypeId;
	}
	public String getCatTypeCode() {
		return catTypeCode;
	}
	public void setCatTypeCode(String catTypeCode) {
		this.catTypeCode = catTypeCode;
	}
	public String getCatTypeName() {
		return catTypeName;
	}
	public void setCatTypeName(String catTypeName) {
		this.catTypeName = catTypeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	  
	
	
}
