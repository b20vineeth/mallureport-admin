package com.easypick.admin.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.easypick.framework.utility.vo.AbstractVo;

public class VideoVo implements AbstractVo {


 
	private Integer videoId; 
	private String videoUrl; 
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
	
	private String movie;
	private String profile;
	
	private String movieTag;
	private String profileTag;
	
	
	
	
	
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
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
	public static void populateVideoVo(List<Object[]> items, List<VideoVo> vos) {

		 
		if (Objects.isNull(vos))
			vos = new ArrayList<>();

		VideoVo videoVo;
		if (items.size() > 0) {
			for (Object[] item : items) {
				try {
					videoVo = new VideoVo();
					videoVo.setTag(Objects.nonNull(item[0])?item[0].toString().replace("#", ""):"");
					videoVo.setThumbnail(Objects.nonNull(item[1])?item[1].toString():"");
					videoVo.setTitle(Objects.nonNull(item[2])?item[2].toString():"");
					videoVo.setUrl(Objects.nonNull(item[3])?item[3].toString():"");
					vos.add(videoVo);
				}
				catch(Exception e){}
			}
		}
			
	}
	
	
}
