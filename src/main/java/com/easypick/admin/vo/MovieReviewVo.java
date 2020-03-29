package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class MovieReviewVo implements AbstractVo {

	private Integer movieReviewId; 
	private String shortDesc;
	private String description;
	private String thumbnail;
	private String createdDate;
	private String tag; 
	private String status = "Y"; 
	private String movieName;
	private String languageName;
	private String movieCode; 
	private String langId;
	private String title;
	
	private Integer movieRate;
	
	
	
	public Integer getMovieRate() {
		return movieRate;
	}
	public void setMovieRate(Integer movieRate) {
		this.movieRate = movieRate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMovieReviewId() {
		return movieReviewId;
	}
	public void setMovieReviewId(Integer movieReviewId) {
		this.movieReviewId = movieReviewId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}  
	
	 
	
}
