package com.easypick.admin.entity;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.web.events.vo.VideoDataVo; 

@Entity
@Table(name = "video" , uniqueConstraints = { @UniqueConstraint(columnNames = { "url"}) })
public class Video  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer videoId;
	 
	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name = "url")
	private String Url;
 
	
	@Column(name = "shortdesc", length=250)
	private String shortDesc;
	
	@Column(name = "title", length=250)
	private String title;
	
	
	@Column(name = "thumbnail", length=250)
	private String thumbnail;
	
	@Column(name = "tag", columnDefinition="TEXT")
	private String tag;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;

	 
	@Column(name = "status", length=1)
	private String status="Y";
	
	@Column(name = "upddat", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;
	
	@Column(name = "movie_tag", length=200)
	private String movieTag;
	
	@Column(name = "profile_tag", length=200)
	private String profileTag;
	
	@Column(name = "tagidx", length=1)
	private Integer tagidx;

	
	
	
	
	public Date getUpdateon() {
		return updateon;
	}

	public void setUpdateon(Date updateon) {
		this.updateon = updateon;
	}

	public Integer getTagidx() {
		return tagidx;
	}

	public void setTagidx(Integer tagidx) {
		this.tagidx = tagidx;
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
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public static Video populateVideoVo(VideoVo vo) {
		Video video=new Video();
		if(vo.getVideoId()!=0)
			video.setVideoId(vo.getVideoId());
		
		video.setDescription(vo.getDescription());
		video.setShortDesc(vo.getShortDesc());
		video.setStatus("Y");
		video.setTag(StringUitity.convertToTags(vo.getTag()));
		video.setThumbnail(vo.getThumbnail());
		video.setTitle(vo.getTitle());
		video.setUpdateon(new Date());
		video.setUrl(vo.getUrl());
		video.setVideoUrl(vo.getVideoUrl());
		video.setProfileTag(StringUitity.convertToTags(vo.getProfile()));
		video.setMovieTag(StringUitity.convertToTags(vo.getMovie()));
		video.setTagidx(0);
		return video;
	}

	 
 

	public static List<? extends AbstractVo> formateVideoVos(List<Video> videos) {
		List<VideoVo> videoVos = new ArrayList<>();
		VideoVo vo = null;
		for (Video video : videos) {
			vo = new VideoVo();
			vo.setVideoId(video.getVideoId());
			vo.setShortDesc(video.getShortDesc());
			vo.setTag(video.getTag());
			vo.setTitle(video.getTitle());
			videoVos.add(vo);
		}

		return videoVos;
	}

	public static Object formateVideoVo(Video video) {
		
		VideoVo vo =new VideoVo();
		vo.setDescription(video.getDescription());
		vo.setShortDesc(video.getShortDesc());
		vo.setTag(video.getTag());
		vo.setThumbnail(video.getThumbnail());
		vo.setTitle(video.getTitle());
		vo.setUrl(video.getUrl());
		vo.setVideoId(video.getVideoId());
		vo.setVideoUrl(video.getVideoUrl());
		 return vo;
	}

	public static List<VideoDataVo> formateVideoDataVo(List<Video> videos) {
		List<VideoDataVo> videoVos = new ArrayList<>();
		VideoDataVo vo = null;
		for (Video video : videos) {
			vo = new VideoDataVo();
			vo.setThumbnail(video.getThumbnail());
			vo.setTitle(video.getTitle());
			vo.setUrl(video.getUrl());
			vo.setVideoUrl(video.getVideoUrl());
			videoVos.add(vo);
		}

		return videoVos;
	} 
	 
 
  
}
