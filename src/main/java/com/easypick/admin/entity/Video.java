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

import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.vo.AbstractVo; 

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
	private Date updatedDate;
	
	
	
	 

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
		video.setTag(vo.getTag());
		video.setThumbnail(vo.getThumbnail());
		video.setTitle(vo.getTitle());
		video.setUpdatedDate(new Date());
		video.setUrl(vo.getUrl());
		video.setVideoUrl(vo.getVideoUrl());
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
	 
 
  
}
