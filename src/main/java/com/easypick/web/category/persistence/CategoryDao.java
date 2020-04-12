package com.easypick.web.category.persistence;
 
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.GalleryVo; 
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CategoryDao {
 

	ResponseVo saveCategory(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo getCategory(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo getCategoryList(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	 



}
