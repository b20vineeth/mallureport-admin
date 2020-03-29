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

import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo; 

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

	 
	 
	public static List<Gallery> populateAttribute(GalleryVo vo) {
		List<Gallery> vos=new ArrayList<Gallery>();
		Gallery gallery=null;
		 
		String[] urls=vo.getGalleryUrl().split(",");
		for(String url:urls)
		{
			String[] exp=url.split("/");
			if(exp.length>1)
			{
			gallery=new Gallery();
			gallery.setDescription(vo.getDescription());
			gallery.setGalleryUrl(url);
			gallery.setShortDesc(vo.getShortDesc());
			gallery.setStatus("Y");
			gallery.setTag(vo.getTag());
			gallery.setThumbnail(exp[0]+"/"+exp[1]+"/thumb_"+exp[2]);
			gallery.setTitle(vo.getTitle());
			gallery.setUpdatedDate(new Date());
			gallery.setUrl(vo.getUrl()+"_"+StringUitity.generateRandomNumber(9999,1111));
			vos.add(gallery);
			}
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
			 vo.setTag(galleryVo.getTag());
			 vo.setThumbnail(galleryVo.getThumbnail());
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
		 vo.setTag(gallery.getTag());
		 vo.setTitle(gallery.getTitle());
		 vo.setThumbnail(gallery.getThumbnail());
		 vo.setUrl(gallery.getUrl());
		 vo.setGalleryUrl(gallery.getGalleryUrl());
		return vo;
	} 
	 
 
  
}
