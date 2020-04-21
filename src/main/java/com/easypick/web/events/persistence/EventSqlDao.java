package com.easypick.web.events.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.DataSetup;
import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.MovieData;
import com.easypick.admin.entity.MovieReview;
import com.easypick.admin.entity.Profile;
import com.easypick.admin.entity.Video;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.vo.CastDataVo;
import com.easypick.web.events.vo.GalleryDataVo;
import com.easypick.web.events.vo.MovieDataVo;
import com.easypick.web.events.vo.ReviewDataVo;
import com.easypick.web.events.vo.VideoDataVo;
import com.google.gson.Gson;

@Component
public class EventSqlDao implements EventDao {
	 
	Gson gson;

	@Override
	public MovieDataVo findMovie(Integer id, WatchDogVo watchdog) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Movie movie where movie.status='Y' ");
		sql.append(" and movie.movieId=:movieId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", id);
		Movie movie = (Movie) query.getSingleResult();
		MovieDataVo movieVo = (MovieDataVo) Movie.formateMovieDataVo(movie);
		return movieVo;
	}

	@Override
	public List<GalleryDataVo> findAllGallery(Integer id, WatchDogVo watchdog) {

		StringBuilder sql = new StringBuilder();
		sql.append("from Gallery gallery where gallery.status='Y'");
		sql.append("and gallery.movieTag like '%#" + id + "#%' and (gallery.thumbnail1 is not null and gallery.thumbnail1!='')"   );
		sql.append(" order by gallery.galleryId desc ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());

		List<Gallery> galleryVos = query.setFirstResult(0).setMaxResults(6).getResultList();
		List<GalleryDataVo> dataVo = Gallery.formateGalleryDataVo(galleryVos);
		return dataVo;
	}

	@Override
	public List<CastDataVo> findAllCast(String cast, WatchDogVo watchdog) {

		cast = cast.replace("#", "").replaceAll(",$", "");
		StringBuilder sql = new StringBuilder();
		sql.append("from Profile profile where profile.status='Y'");
		sql.append("and (profile.profileId in (" + StringUitity.filterNumber(cast) + ") "
				+ " or profile.profileName in (" + StringUitity.filterNotNumber(cast) + ") ) ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		List<Profile> profileVos = query.getResultList();
		List<CastDataVo> dataVo = Profile.formateCastDataVo(profileVos);
		return dataVo;
	}

	@Override
	public List<VideoDataVo> findAllVideo(Integer id, WatchDogVo watchdog) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Video video where video.status='Y'");
		sql.append("and video.movieTag like '%#" + id + "#%'");
		sql.append(" order by video.videoId desc ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		List<Video> videos = query.setFirstResult(0).setMaxResults(6).getResultList();
		List<VideoDataVo> dataVo = Video.formateVideoDataVo(videos);
		return dataVo;
	}

	@Override
	public List<ReviewDataVo> findAllReviewData(Integer id, WatchDogVo watchdog) {
		StringBuilder sql = new StringBuilder();
		sql.append("from MovieReview movie where movie.status='Y'");
		sql.append("and movie.movie.movieId=:movieId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", id);
		List<MovieReview> movieReviews = query.setFirstResult(0).setMaxResults(6).getResultList();
		List<ReviewDataVo> dataVo = MovieReview.formateReviewDataVo(movieReviews);
		return dataVo;
	}

	@Override
	public void saveMovieData(MovieDataVo movie, WatchDogVo watchdog) {

		StringBuilder sql = new StringBuilder();
		sql.append("from MovieData movie where movie.movieCode=:movieCode");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieCode", movie.getMovieCode());
		MovieData movieData = null;
		gson=new Gson();
		try {
			movieData = (MovieData) query.getSingleResult();
			movieData.setDescription(gson.toJson(movie).toString());
		} catch (Exception e) {
			movieData = new MovieData();
			movieData.setStatus("Y");
			movieData.setDescription(gson.toJson(movie).toString());
			movieData.setMovieCode(movie.getMovieCode());
		}
		watchdog.getSessionString().saveOrUpdate(movieData);

	}

	@Override
	public void updateProfileDetails(WatchDogVo watchdog, Profile profile, Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Movie movie where movie.status='Y' ");
		sql.append(" and movie.movieId=:movieId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", id);
		Movie movie = (Movie) query.getSingleResult();
		String cast=movie.getCast();
		cast=cast.replace(profile.getProfileName(), profile.getProfileId().toString());
		movie.setCast(cast);
		
	}

	@Override
	public void updateMovieCast(Integer id, WatchDogVo watchdog, Profile profile) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Movie movie where movie.status='Y' ");
		sql.append(" and movie.movieId=:movieId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", id);
		Movie movie = (Movie) query.getSingleResult();
		String profileData=movie.getCast();
		profileData=profileData.replace(profile.getProfileName(), profile.getProfileId().toString());
		movie.setCast(profileData);
		watchdog.getSessionString().saveOrUpdate(movie);
		
	}

	@Override
	public void updateProfileMovie(Integer id, WatchDogVo watchdog, Movie movie) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Profile profile where profile.status='Y' ");
		sql.append(" and profile.profileId=:profile ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("profile", id);
		Profile profile = (Profile) query.getSingleResult();
		String filmsData=profile.getFilms();
		filmsData=filmsData.replace(movie.getMovieName(), movie.getMovieId().toString());
		profile.setFilms(filmsData);
		profile.setUpdateon(new Date());
		profile.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(profile);
		
	}

	@Override
	public void updateProfileGallery(Integer id, WatchDogVo watchdog, Movie movie) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Gallery gallery where gallery.status='Y' ");
		sql.append(" and gallery.galleryId=:galleryId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("galleryId", id);
		Gallery gallery = (Gallery) query.getSingleResult();
		String filmsData=gallery.getMovieTag();
		filmsData=filmsData.replace(movie.getMovieName(), movie.getMovieId().toString());
		gallery.setMovieTag(filmsData);
		gallery.setUpdateon(new Date());
		gallery.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(gallery);
		
	}

	@Override
	public void updateGalleryProfile(Integer id, WatchDogVo watchdog, Profile profile) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Gallery gallery where gallery.status='Y' ");
		sql.append(" and gallery.galleryId=:galleryId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("galleryId", id);
		Gallery gallery = (Gallery) query.getSingleResult();
		String filmsData=gallery.getProfileTag();
		filmsData=filmsData.replace(profile.getProfileName(), profile.getProfileId().toString());
		gallery.setProfileTag(filmsData);
		gallery.setUpdateon(new Date());
		gallery.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(gallery);
		
	}

	@Override
	public void updateMovieToVideo(Integer id, WatchDogVo watchdog, Movie movie) {

		StringBuilder sql = new StringBuilder();
		sql.append(" from Video video where video.status='Y' ");
		sql.append(" and video.videoId=:videoId ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("videoId", id);
		Video video = (Video) query.getSingleResult();
		String filmsData=video.getMovieTag();
		filmsData=filmsData.replace(movie.getMovieName(), movie.getMovieId().toString());
		video.setMovieTag(filmsData);
		video.setUpdateon(new Date());
		video.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(video);
		
	}

	@Override
	public void updateProfileIdtoVideo(Integer id, WatchDogVo watchdog, Profile profile) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Video video where video.status='Y' ");
		sql.append(" and video.videoId=:video ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("video", id);
		Video video = (Video) query.getSingleResult();
		String filmsData=video.getProfileTag();
		filmsData=filmsData.replace(profile.getProfileName(), profile.getProfileId().toString());
		video.setProfileTag(filmsData);
		video.setUpdateon(new Date());
		video.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(video);
		
	}

	@Override
	public void updateMovieTypetoMovie(Integer id, WatchDogVo watchdog, DataSetup dataSetup) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Movie movie where movie.status='Y' ");
		sql.append(" and movie.movieId=:movie ");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movie", id);
		Movie movie = (Movie) query.getSingleResult();
		String movieType=movie.getMovieType();
		movieType=movieType.replace(dataSetup.getDateName(), dataSetup.getId().toString());
		movie.setMovieType(movieType);
		movie.setTagidx(0);
		watchdog.getSessionString().saveOrUpdate(movie);
		
	}

}
