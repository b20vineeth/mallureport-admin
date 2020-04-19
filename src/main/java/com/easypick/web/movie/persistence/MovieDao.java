package com.easypick.web.movie.persistence;

import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface MovieDao {

	ResponseVo saveMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo getMovieList(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo getMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo getMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException;

	ResponseVo updateMovieStatus(WatchDogVo watchdog, MovieVo vo) throws BussinessException;

	ResponseVo enablePriority(MovieVo vo, WatchDogVo watchdog) throws BussinessException;

	ResponseVo galleryStatus(CinemaGalleryVo vo, WatchDogVo watchdog) throws BussinessException;

	ResponseVo reviewStatus(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException;

	ResponseVo saveMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException;

	 

 

 
 




}
