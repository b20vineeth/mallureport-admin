package com.easypick.framework.utility.query;

public class GalleryResourceQuery {

	public static final String HOME_PAGE_QUERY="SELECT gallery_id,thumb_url,createddate,shortdesc FROM gallery  where status='Y' ";
	public static final Object SELECT_URL_QUERY = "SELECT gallery_id,gallery_url,createddate,description,pic_url,tag,title FROM `gallery` WHERE `status`='Y'";
	public static final Object COUNT_HOME_PAGE_QUERY = "SELECT count(*) COUNT FROM gallery  where status='Y' ";
	public static final Object COUNT_PARAM_THREE_PAGE_QUERY = "SELECT count(*) COUNT FROM gallery  where status='Y' ";
	
	 private GalleryResourceQuery()
	 {
		 
	 }
}
