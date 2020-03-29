package com.easypick.admin.vo;

import java.util.Date; 

import com.easypick.framework.utility.vo.AbstractVo;

public class GalleryVo implements AbstractVo {


	public static final String UPLOAD_PATH="C:\\xampp\\htdocs\\gallery\\";
	
	private Integer galleryId; 
	private String galleryUrl; 
	private String url;
  
	private String shortDesc; 
	private String title; 
	private String thumbnail; 
	private String tag; 
	private String description; 
	private String status="Y"; 
	private Date updatedDate;
	
	 
	private String page;
	private Integer perPage = 0;
	public Integer getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(Integer galleryId) {
		this.galleryId = galleryId;
	}
	public String getGalleryUrl() {
		return galleryUrl;
	}
	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	 
	
}
