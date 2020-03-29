package com.easypick.admin.user.persistence;

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
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CommonAttributeDao {

	ResponseVo saveLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

	ResponseVo getLanguageList(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

	ResponseVo getLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

	ResponseVo saveCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryTypeList(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategory(WatchDogVo watchdog, CategoryVo vo) throws BussinessException;

	ResponseVo getCategoryList(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo saveCategory(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo saveMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo getMovieList(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo getMovie(WatchDogVo watchdog, MovieVo vo)  throws BussinessException;

	ResponseVo profileSave(WatchDogVo watchdog, ProfileVo vo)  throws BussinessException;

	ResponseVo getProfileList(WatchDogVo watchdog, ProfileVo vo)  throws BussinessException;

	ResponseVo getProfile(WatchDogVo watchdog, ProfileVo vo)   throws BussinessException;

	ResponseVo saveVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException;

	ResponseVo getVideoList(WatchDogVo watchdog, VideoVo vo) throws BussinessException;

	ResponseVo getVideo(WatchDogVo watchdog, VideoVo vo)  throws BussinessException;

	ResponseVo saveGalleryVo(WatchDogVo watchdog, GalleryVo vo)  throws BussinessException;

	ResponseVo getGalleryList(WatchDogVo watchdog, GalleryVo vo)   throws BussinessException;

	ResponseVo getGallery(WatchDogVo watchdog, GalleryVo vo)   throws BussinessException;

	ResponseVo updateThumbnail(String fileName, int id, WatchDogVo watchdog)   throws BussinessException;

	ResponseVo enablePriority(MovieVo vo, WatchDogVo watchdog)   throws BussinessException;

	ResponseVo getMovieReview(MovieReviewVo vo, WatchDogVo watchdog)  throws BussinessException;

	ResponseVo saveMovieReview(MovieReviewVo vo, WatchDogVo watchdog)  throws BussinessException;



}
