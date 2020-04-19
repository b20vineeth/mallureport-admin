package com.easypick.admin.vo;
 

import java.util.ArrayList;
import java.util.List;

import com.easypick.framework.utility.vo.AbstractVo;

public class SettingsVo  implements AbstractVo{
 
	private Integer settingsId;
	private String type;
	private String category;
	private String tag;
	private String status="Y";
	
	private String term;
	private String categoryCode;
	
	
	
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Integer getSettingsId() {
		return settingsId;
	}
	public void setSettingsId(Integer settingsId) {
		this.settingsId = settingsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static List<SettingsVo> generateCategory() {
		 
		List<SettingsVo> categoryVos=new ArrayList<>();
		 
		populateCategory(categoryVos, "mov", "Movie");
		populateCategory(categoryVos, "event", "Event");
		populateCategory(categoryVos, "p-m", "Profile (M)");
		populateCategory(categoryVos, "p-f", "Profile (F)");
		populateCategory(categoryVos, "mod", "Model");
		return categoryVos;
	}
	private static void populateCategory(List<SettingsVo> categoryVos, String categorCode, String categorName) {
		SettingsVo vo;
		vo=new SettingsVo();
		vo.setCategory(categorName);
		vo.setCategoryCode(categorCode);
		categoryVos.add(vo);
	} 
	 
}
