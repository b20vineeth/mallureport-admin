package com.easypick.admin.vo;
 

import com.easypick.framework.utility.vo.AbstractVo;

public class SlideShowVo  implements AbstractVo{
 
	private Integer slideShowid;
	private String title;
	private String category;
	private String type;
	private String status; 
	private String link; 
	private String picUrl;
	private String slideflg;
	
	
	
	public String getSlideflg() {
		return slideflg;
	}
	public void setSlideflg(String slideflg) {
		this.slideflg = slideflg;
	}
	public Integer getSlideShowid() {
		return slideShowid;
	}
	public void setSlideShowid(Integer slideShowid) {
		this.slideShowid = slideShowid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	} 
	
	 
	
}
