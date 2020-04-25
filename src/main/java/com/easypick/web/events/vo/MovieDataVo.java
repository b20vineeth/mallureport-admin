package com.easypick.web.events.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDataVo {
	
	private String shortDesc;
	private String description;
	private String thumbnail;
	private String releaseDate;
	private String tag;
	private String cast;
	private Integer movieId;
	private String movieType;
	private String certificate;
	private String movieRate;
	private String movieCode;
	private String movieName;
	private String languageName;
	private Integer lang; 
	private List<GalleryDataVo> galleryData;  
	private List<CastDataVo> castData; 
	private List<VideoDataVo> videoData;
	private List<ReviewDataVo> reviewData; 
	
	
	
	
	
	
	public List<ReviewDataVo> getReviewData() {
		return reviewData;
	}
	public void setReviewData(List<ReviewDataVo> reviewData) {
		this.reviewData = reviewData;
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
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getMovieRate() {
		return movieRate;
	}
	public void setMovieRate(String movieRate) {
		this.movieRate = movieRate;
	}
	public String getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
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
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public List<GalleryDataVo> getGalleryData() {
		return galleryData;
	}
	public void setGalleryData(List<GalleryDataVo> galleryData) {
		this.galleryData = galleryData;
	}
	public List<CastDataVo> getCastData() {
		return castData;
	}
	public void setCastData(List<CastDataVo> castData) {
		this.castData = castData;
	}
	public List<VideoDataVo> getVideoData() {
		return videoData;
	}
	public void setVideoData(List<VideoDataVo> videoData) {
		this.videoData = videoData;
	}
	
	
	
	  
	
	
}
