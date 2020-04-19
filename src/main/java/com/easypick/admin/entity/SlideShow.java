package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.easypick.admin.vo.SlideShowVo;
import com.easypick.framework.utility.vo.AbstractVo;

@Entity
@Table(name = "slideshow")
public class SlideShow {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slideshowid")
	private Integer slideshowId;

	@Column(name = "title")
	private String title;
	
	@Column(name = "slideflg" ,length=1)
	private String slideflg="N";

	@Column(name = "picUrl")
	private String picUrl;

	@Column(name = "link")
	private String link;

	@Column(name = "type", length = 10)
	private String type;
 

	@Column(name = "status", length = 1)
	private String status = "Y";

	 

	 
	public String getSlideflg() {
		return slideflg;
	}

	public void setSlideflg(String slideflg) {
		this.slideflg = slideflg;
	}

	public Integer getSlideshowId() {
		return slideshowId;
	}

	public void setSlideshowId(Integer slideshowId) {
		this.slideshowId = slideshowId;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public static List<? extends AbstractVo> formateSlideShowVos(List<SlideShow> slideShows) {

		List<SlideShowVo> slideShowVo = new ArrayList<>();
		SlideShowVo vo = null;
		for (SlideShow slideShow : slideShows) {
			vo = new SlideShowVo();
			vo.setLink(slideShow.getLink());
			vo.setPicUrl(slideShow.getPicUrl());
			vo.setSlideShowid(slideShow.getSlideshowId());
			vo.setTitle(slideShow.getTitle());
			vo.setType(slideShow.getType());
			vo.setSlideflg(slideShow.getSlideflg());
			slideShowVo.add(vo);
			
		}
		return slideShowVo;
	}

	public static SlideShow populateSlideShow(SlideShow slideShow, SlideShowVo slideShowVo) {
		 
		if(Objects.nonNull(slideShowVo.getLink()) && slideShowVo.getLink().trim().length()>0)
			slideShow.setLink(slideShowVo.getLink());
		if(Objects.nonNull(slideShowVo.getTitle()) && slideShowVo.getTitle().trim().length()>0)
			slideShow.setTitle(slideShowVo.getTitle());
		if(Objects.nonNull(slideShowVo.getPicUrl()) && slideShowVo.getPicUrl().trim().length()>0)
			slideShow.setPicUrl(slideShowVo.getPicUrl());
		if(Objects.nonNull(slideShowVo.getStatus()) && slideShowVo.getStatus().trim().length()>0)
			 slideShow.setStatus(slideShowVo.getStatus());
		
		if(Objects.nonNull(slideShowVo.getSlideflg()) && slideShowVo.getSlideflg().trim().length()>0)
			 slideShow.setSlideflg(slideShowVo.getSlideflg());
		
		return slideShow;
	}

	public static SlideShow populateSlideShowVo(SlideShowVo slideShowVo) {
		SlideShow slideShow=new SlideShow();
		slideShow.setStatus("Y");
		slideShow.setLink(slideShowVo.getLink());
		slideShow.setPicUrl(slideShowVo.getPicUrl());
		slideShow.setTitle(slideShowVo.getTitle());
		slideShow.setType(slideShowVo.getType());
		slideShow.setSlideflg(slideShowVo.getSlideflg());
		return slideShow;
	}

	public static Object formateSlideShowVo(SlideShow slideShow) {
		 
		SlideShowVo slideShowVo=new SlideShowVo(); 
		slideShowVo.setLink(slideShow.getLink());
		slideShowVo.setPicUrl(slideShow.getPicUrl());
		slideShowVo.setTitle(slideShow.getTitle());
		slideShowVo.setType(slideShow.getType());
		slideShowVo.setSlideShowid(slideShow.getSlideshowId());
		slideShowVo.setSlideflg(slideShow.getSlideflg());
		return slideShowVo;
	}

	public static List<? extends AbstractVo> formateSlideShows(List<SlideShow> slideShows) {
	
		List<SlideShowVo> slideShowVos=new ArrayList<>();
		for(SlideShow slideShow : slideShows) 
		{
			SlideShowVo slideShowVo=new SlideShowVo(); 
			slideShowVo.setLink(slideShow.getLink());
			slideShowVo.setPicUrl(slideShow.getPicUrl());
			slideShowVo.setTitle(slideShow.getTitle());  
			slideShowVos.add(slideShowVo);
		}
		return slideShowVos;
	}

}
