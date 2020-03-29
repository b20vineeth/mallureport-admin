package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class CategoryVo implements AbstractVo {

	private Integer catId;
	private String catName;
	private String catCode;
	private String catType;
	private String status = "Y";
	private String page;
	private Integer catTypeId;
	private Integer perPage = 0;
	private Integer lang;
	private String langName;
	private String gender="M";
	private String genderdflag;
	private String langFlag;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getCatType() {
		return catType;
	}
	public void setCatType(String catType) {
		this.catType = catType;
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
	public Integer getCatTypeId() {
		return catTypeId;
	}
	public void setCatTypeId(Integer catTypeId) {
		this.catTypeId = catTypeId;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public String getLangName() {
		return langName;
	}
	public void setLangName(String langName) {
		this.langName = langName;
	}

	   

}
