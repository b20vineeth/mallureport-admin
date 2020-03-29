package com.easypick.defaults.admin.user.bussinesscontroller;

import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;

public interface ControllerInterface {
	ResponseVo saveLanguage(LanguageVo vo)throws BussinessException;

	ResponseVo getLanguageList(LanguageVo vo) throws BussinessException;

	ResponseVo getLanguage(LanguageVo vo) throws BussinessException;

	ResponseVo saveCategoryType(CategoryTypeVo vo)throws BussinessException;

	ResponseVo getCategoryTypeList(CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryType(CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategory(CategoryVo vo) throws BussinessException;

	ResponseVo getCategoryList(CategoryVo vo) throws BussinessException;

	ResponseVo saveCategory(CategoryVo vo) throws BussinessException;

	ResponseVo saveMovie(MovieVo vo) throws BussinessException;

	ResponseVo getMovieList(MovieVo vo) throws BussinessException;

	ResponseVo getMovie(MovieVo vo) throws BussinessException;

	ResponseVo profileSave(ProfileVo vo) throws BussinessException;

	ResponseVo getProfileList(ProfileVo vo)  throws BussinessException;

	ResponseVo getProfile(ProfileVo profileVo) throws BussinessException;
 

	ResponseVo saveVideoVo(VideoVo vo) throws BussinessException;

	ResponseVo getVideoList(VideoVo vo) throws BussinessException;

	ResponseVo getVideo(VideoVo videoVo) throws BussinessException;

	ResponseVo saveGalleryVo(GalleryVo vo) throws BussinessException;

	ResponseVo getGalleryList(GalleryVo vo) throws BussinessException;

	ResponseVo getGallery(GalleryVo galleryVo)  throws BussinessException;

	ResponseVo getGenderList();

	ResponseVo updateThumbnail(String fileName, int parseInt) throws BussinessException;

	ResponseVo enablePriority(MovieVo vo) throws BussinessException;

	ResponseVo getMovieReview(MovieReviewVo vo) throws BussinessException;

	ResponseVo saveMovieReview(MovieReviewVo vo) throws BussinessException;

}
