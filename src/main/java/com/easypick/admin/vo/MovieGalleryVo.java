package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class MovieGalleryVo  implements AbstractVo {

	private static final long serialVersionUID = 1L;

	 private Integer galleryId;
 
	 private String thumb1;
	
	 private String title;
	
	 private String  url;
	
	 private String tag;

	 private String type;

	public Integer getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Integer galleryId) {
		this.galleryId = galleryId;
	}

	public String getThumb1() {
		return thumb1;
	}

	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	 

}
