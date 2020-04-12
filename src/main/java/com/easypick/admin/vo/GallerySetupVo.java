package com.easypick.admin.vo;

import java.util.Date;

import com.easypick.framework.utility.vo.AbstractVo;

public class GallerySetupVo implements AbstractVo {

	public static final String UPDATE = "update";
	public static final String LIST = "list";
	public static final String SAVE = "save";
	public static final String CREATE = "create";
	public static final String DELETE = "delete";
	public static final String DEACTIVATE = "deactivate";
	public static final String UPLOAD_EXCEL="uploadexcel";
	public static final String UPLOAD_EXCEL_PATH="E:\\Project\\Mallureports\\mallureport\\upload\\";
	public static final String BULK_UPLOAD = "bulkupload";
	public static final String BULK_SAVE = "bulksave";
	
	private String tag;
	private String title;
	private String picUrl;
	private String thumbUrl;
	private String description;
	private String shortDesc;
	private Date createdDate;
	private String id;
	private String pageUrl;
	private String metaData;

	private Date createDateFromat;
	private Date validitytoFromat;
	private Date validityFromFromat;
	private Integer page; 

	private String validityto;
	private String status;
	private String validityFrom;
	 

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getValidityto() {
		return validityto;
	}

	public void setValidityto(String validityto) {
		this.validityto = validityto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDateFromat() {
		return createDateFromat;
	}

	public void setCreateDateFromat(Date createDateFromat) {
		this.createDateFromat = createDateFromat;
	}

	public Date getValiditytoFromat() {
		return validitytoFromat;
	}

	public void setValiditytoFromat(Date validitytoFromat) {
		this.validitytoFromat = validitytoFromat;
	}

	public Date getValidityFromFromat() {
		return validityFromFromat;
	}

	public void setValidityFromFromat(Date validityFromFromat) {
		this.validityFromFromat = validityFromFromat;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

}
