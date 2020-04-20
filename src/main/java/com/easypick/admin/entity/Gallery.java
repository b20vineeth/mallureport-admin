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

import com.easypick.admin.vo.GalleryContentVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.web.events.vo.GalleryDataVo;
import com.google.gson.Gson; 

@Entity
@Table(name = "gallery" , uniqueConstraints = { @UniqueConstraint(columnNames = { "url"}) })
public class Gallery  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer galleryId;
	 
	@Column(name = "gallery_url")
	private String galleryUrl;
	
	@Column(name = "url")
	private String Url;
 
	
	@Column(name = "shortdesc", length=250)
	private String shortDesc;
	
	@Column(name = "title", length=250)
	private String title;
	
	
	@Column(name = "thumbnail1", length=550)
	private String thumbnail1;
	@Column(name = "thumbnail2", length=550)
	private String thumbnail2;
	@Column(name = "thumbnail3", length=550)
	private String thumbnail3;
	
	@Column(name = "movie_tag", length=200)
	private String movieTag;
	
	@Column(name = "profile_tag", length=200)
	private String profileTag;
	
	
	@Column(name = "tag", columnDefinition="TEXT")
	private String tag;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
 
	
	@Column(name = "status", length=1)
	private String status="Y";
	 
	@Column(name = "updated_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;
	
	@Column(name = "tagidx" ,length=1)
	private Integer tagidx;
	 
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
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
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

	 
	public String getThumbnail1() {
		return thumbnail1;
	}

	public void setThumbnail1(String thumbnail1) {
		this.thumbnail1 = thumbnail1;
	}

	public String getThumbnail2() {
		return thumbnail2;
	}

	public void setThumbnail2(String thumbnail2) {
		this.thumbnail2 = thumbnail2;
	}

	public String getThumbnail3() {
		return thumbnail3;
	}

	public void setThumbnail3(String thumbnail3) {
		this.thumbnail3 = thumbnail3;
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

	public static List<Gallery> populateAttribute(GalleryVo vo) {
		List<Gallery> vos=new ArrayList<Gallery>();
		Gallery gallery=null;
		Gson gson = new Gson();
		
		 
		
		for(GalleryContentVo url:vo.getContent())
		{
			gallery=new Gallery();
			gallery.setDescription(vo.getDescription());
			gallery.setGalleryUrl(vo.getGalleryUrl());
			gallery.setShortDesc(vo.getShortDesc());
			gallery.setMovieTag(StringUitity.convertToTags(vo.getMovie())); 
			gallery.setStatus("Y");
			gallery.setGalleryUrl(vo.getGalleryUrl());
			gallery.setThumbnail1(url.getThumb1());
			gallery.setThumbnail2(url.getThumb2());
			gallery.setThumbnail3(url.getThumb3()); 
			gallery.setProfileTag(StringUitity.convertToTags(vo.getProfile()));
			gallery.setTag(StringUitity.convertToTags(vo.getTag()));
			gallery.setTitle(vo.getTitle());
			gallery.setUpdateon(new Date());
			gallery.setTagidx(0);
			gallery.setUrl(vo.getUrl()+"_"+StringUitity.generateRandomNumber(9999,1111));
			vos.add(gallery);
			 
		}
		
		
		return vos;
	}

	public static List<? extends AbstractVo> formateGalleryVos(List<Gallery> galleryVos) {
		 List<GalleryVo> vos=new ArrayList<>();
		 GalleryVo vo=null;
		 for(Gallery galleryVo:galleryVos)
		 {
			 vo=new GalleryVo();
			 vo.setDescription(galleryVo.getDescription());
			 vo.setGalleryId(galleryVo.getGalleryId()); 
			 vo.setShortDesc(galleryVo.getShortDesc());
			 vo.setTag(galleryVo.getTag().replace("#", ""));
			 vo.setThumbnail(galleryVo.getThumbnail2());
			 vo.setTitle(galleryVo.getTitle());
			 
			 vos.add(vo);
		 }
		return vos;
	}

	public static Object formateGalleryVo(Gallery gallery) {
		 
			GalleryVo vo=new GalleryVo();
		 vo.setDescription(gallery.getDescription());
		 vo.setGalleryId(gallery.getGalleryId()); 
		 vo.setShortDesc(gallery.getShortDesc());
		 vo.setTag(gallery.getTag().replace("#", ""));
		 vo.setTitle(gallery.getTitle());
		 vo.setThumbnail(gallery.getThumbnail2());
		 vo.setUrl(gallery.getUrl());
		 vo.setGalleryUrl(gallery.getGalleryUrl());
		return vo;
	}

	public static List<GalleryDataVo> formateGalleryDataVo(List<Gallery> galleryVos) {
		 List<GalleryDataVo> vos=new ArrayList<>();
		 GalleryDataVo vo=null;
		 for(Gallery galleryVo:galleryVos)
		 {
			 vo=new GalleryDataVo();
			 
			 vo.setThumbnail(galleryVo.getThumbnail1());
			 vo.setTitle(galleryVo.getTitle());
			 vo.setUrl(galleryVo.getUrl());
			 vos.add(vo);
		 }
		return vos;
	} 
	 
 
  
}
