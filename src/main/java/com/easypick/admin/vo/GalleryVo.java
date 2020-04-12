package com.easypick.admin.vo;

import java.util.Date;
import java.util.List;

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
	
	List<GalleryContentVo> content;
	
	private String galleryContent;
	private String movie;
	private String profile;
	
	private String page;
	private Integer perPage = 0;
	
	private String movieTag;
	private String profileTag;
	
	
	  
	public String getMovieTag() {
		return movieTag;
	}
	public void setMovieTag(String movieTag) {
		this.movieTag = movieTag;
	}
	public String getProfileTag() {
		return profileTag;
	}
	public void setProfileTag(String profileTag) {
		this.profileTag = profileTag;
	}
	public List<GalleryContentVo> getContent() {
		return content;
	}
	public void setContent(List<GalleryContentVo> content) {
		this.content = content;
	}
	public String getGalleryContent() {
		return galleryContent;
	}
	public void setGalleryContent(String galleryContent) {
		this.galleryContent = galleryContent;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
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
class Gallerycontent
{
	private String thumb1;
	private String thumb2;
	private String thumb3;
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}
	
}