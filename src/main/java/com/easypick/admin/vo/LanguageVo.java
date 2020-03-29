package com.easypick.admin.vo;
 

import com.easypick.framework.utility.vo.AbstractVo;

public class LanguageVo  implements AbstractVo{
 
	private Integer langId;
	private String langCode;
	private String langName;
	private String status;
	private String page;
	
	
	
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Integer getLangId() {
		return langId;
	}
	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getLangName() {
		return langName;
	}
	public void setLangName(String langName) {
		this.langName = langName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}  
	 
	
	
	
}
