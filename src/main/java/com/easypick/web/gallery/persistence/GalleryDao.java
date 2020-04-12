package com.easypick.web.gallery.persistence;
 
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface GalleryDao {

	ResponseVo updateThumbnail(String fileName, int id, WatchDogVo watchdog) throws BussinessException;

	ResponseVo saveGalleryVo(WatchDogVo watchdog, GalleryVo vo) throws BussinessException;

	ResponseVo getGalleryList(WatchDogVo watchdog, GalleryVo vo) throws BussinessException;

	ResponseVo getGallery(WatchDogVo watchdog, GalleryVo vo) throws BussinessException;

	ResponseVo saveCineGallery(CinemaGalleryVo vo, WatchDogVo watchdog) throws BussinessException;

	  


}
