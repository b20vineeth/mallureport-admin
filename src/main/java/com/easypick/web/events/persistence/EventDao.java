package com.easypick.web.events.persistence;

import java.util.List;

import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.Profile;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.vo.CastDataVo;
import com.easypick.web.events.vo.GalleryDataVo;
import com.easypick.web.events.vo.MovieDataVo;
import com.easypick.web.events.vo.ReviewDataVo;
import com.easypick.web.events.vo.VideoDataVo;

public interface EventDao {

	MovieDataVo findMovie(Integer id, WatchDogVo watchdog);

	List<GalleryDataVo> findAllGallery(Integer id, WatchDogVo watchdog);

	List<CastDataVo> findAllCast(String cast, WatchDogVo watchdog);

	List<VideoDataVo> findAllVideo(Integer id, WatchDogVo watchdog);
 

	List<ReviewDataVo> findAllReviewData(Integer id, WatchDogVo watchdog);

	void saveMovieData(MovieDataVo movie, WatchDogVo watchdog);

	void updateProfileDetails(WatchDogVo watchdog, Profile profile, Integer id);

	void updateMovieCast(Integer id, WatchDogVo watchdog, Profile profile);

	void updateProfileMovie(Integer profileId, WatchDogVo watchdog, Movie movie);

	void updateProfileGallery(Integer galleryId, WatchDogVo watchdog, Movie movie);

	void updateGalleryProfile(Integer galleryId, WatchDogVo watchdog, Profile profile);

	void updateMovieToVideo(Integer videoId, WatchDogVo watchdog, Movie movie);

	void updateProfileIdtoVideo(Integer videoId, WatchDogVo watchdog, Profile profile);
	 

}
