package com.easypick.web.gallery.bussinesscontroller;

import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;

public interface GalleryCommonBussinessInterface {

	ResponseVo updateThumbnail(String fileName, int parseInt) throws BussinessException;

	ResponseVo saveGalleryVo(GalleryVo vo) throws BussinessException;

	ResponseVo getGalleryList(GalleryVo vo) throws BussinessException;

	ResponseVo getGallery(GalleryVo galleryVo) throws BussinessException;

	ResponseVo saveCineGallery(CinemaGalleryVo vo) throws BussinessException;


	 
	

}
