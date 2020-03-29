package com.easypick.framework.utility.persistence.mapper;

import java.util.Date; 
import com.easypick.admin.vo.GallerySetupVo; 

public class GalleryItemsMapper {

	public   GallerySetupVo homePageMmapper(Object[] items) {
		GallerySetupVo vo=new GallerySetupVo();
		//vo.setGalleryId(Integer.parseInt(items[0].toString()));
		vo.setThumbUrl(items[1].toString());
		vo.setCreatedDate((Date) items[2]);
		vo.setShortDesc(items[3].toString());
		return vo;
	}

}
