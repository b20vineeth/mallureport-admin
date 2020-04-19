package com.easypick.web.movie.bussinesscontroller;
  
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface MovieBussinessInterface {

	ResponseVo saveMovie(MovieVo vo) throws BussinessException;

	ResponseVo getMovieList(MovieVo vo) throws BussinessException;

	ResponseVo getMovie(MovieVo vo) throws BussinessException;
	
	ResponseVo getMovieReview(MovieReviewVo vo) throws BussinessException;
	
	ResponseVo updateMovieStatus(MovieVo vo) throws BussinessException;
	
	ResponseVo enablePriority(MovieVo vo) throws BussinessException;

	ResponseVo galleryStatus(CinemaGalleryVo cinemaGallery) throws BussinessException;

	ResponseVo reviewStatus(MovieReviewVo movieReviewVo) throws BussinessException;

	ResponseVo saveMovieReview(MovieReviewVo vo)  throws BussinessException;
 
}
