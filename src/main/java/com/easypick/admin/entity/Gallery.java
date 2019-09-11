package com.easypick.admin.entity;


import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;  


@Entity
@Table(name = "gallery"  , uniqueConstraints = { @UniqueConstraint(columnNames = { "pic_url" ,"thumb_url","gallery_url"}) })
 public class Gallery  extends  BaseTable {


	private static final long serialVersionUID = 1L;
	 
 
	@Id
	@GeneratedValue 
	@Column(name = "gallery_id")
	private Integer galleryId ;
	@Column(name = "tag", length=350)
	private String tag;
	
	@Column(name = "title", length=200)
	private String title;
	
	@Column(name = "pic_url", length=500)
	private String picUrl;
	
	@Column(name = "thumb_url", length=500)
	private String thumbUrl;
	
	@Column(name = "description",columnDefinition="TEXT")
	private String description;
	
	@Column(name = "shortdesc", length=500)
	private String shortDesc;
	
	@Column(name = "gallery_url", length=150)
	private String galleryUrl;
	
	
	
	

	public String getGalleryUrl() {
		return galleryUrl;
	}

	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}

	public Integer getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Integer galleryId) {
		this.galleryId = galleryId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	
	
	  
	 
}
