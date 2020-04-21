package com.easypick.web.movie.bussinesscontroller;
  
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface MovieBussinessInterface {

	ResponseVo saveMovie(MovieVo vo, UserSetupVo userSetupVo) throws BussinessException;

	ResponseVo getMovieList(MovieVo vo, UserSetupVo userSetupVo) throws BussinessException;

	ResponseVo getMovie(MovieVo vo, UserSetupVo userSetupVo) throws BussinessException;
	
	ResponseVo getMovieReview(MovieReviewVo vo, UserSetupVo userSetupVo) throws BussinessException;
	
	ResponseVo updateMovieStatus(MovieVo vo, UserSetupVo userSetupVo) throws BussinessException;
	
	ResponseVo enablePriority(MovieVo vo, UserSetupVo userSetupVo) throws BussinessException;

	ResponseVo galleryStatus(CinemaGalleryVo cinemaGallery) throws BussinessException;

	ResponseVo reviewStatus(MovieReviewVo movieReviewVo) throws BussinessException;

	ResponseVo saveMovieReview(MovieReviewVo vo)  throws BussinessException;
 
}
