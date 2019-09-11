package com.easypick.framework.utility.persistence.mapper;

import java.util.Date; 
import com.easypick.admin.vo.GalleryVo; 

public class GalleryItemsMapper {

	public   GalleryVo homePageMmapper(Object[] items) {
		GalleryVo vo=new GalleryVo();
		//vo.setGalleryId(Integer.parseInt(items[0].toString()));
		vo.setThumbUrl(items[1].toString());
		vo.setCreatedDate((Date) items[2]);
		vo.setShortDesc(items[3].toString());
		return vo;
	}

}
