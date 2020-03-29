package com.easypick.admin.admin.persistence;

import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.Video;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByNameDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		vo = new ResponseVo();
		if (Objects.isNull(watchDog.getInput().get("subtag"))) {
			String movieCode = watchDog.getInput().get("cinemaName");
			StringBuilder sql = new StringBuilder();
			sql.append("from Movie movie where movie.status='Y' and movie.movieCode =:movieCode");
			 
			  query = watchDog.getSessionString().createQuery(sql.toString());
			query.setParameter("movieCode", movieCode);
			Movie movie = (Movie) query.getSingleResult();
			vo.setObject(Movie.formateMovieVo(movie));
			vo.setResponse(true);
		} else {
			
			if("video".equals(watchDog.getInput().get("subtag")))
			{
				 query = watchDog.getSessionString().createQuery(" from Video video where video.status='Y' and video.tag like '%"+watchDog.getInput().get("cinemaName")+"%' ");
				 List<Video> videoVos = query.setFirstResult(0).setMaxResults(10).getResultList();
				 vo.setObjectList(Video.formateVideoVos(videoVos));

			}
			else if("gallery".equals(watchDog.getInput().get("subtag")))
			{
				 query = watchDog.getSessionString().createQuery(" from Gallery gallery where gallery.status='Y' and gallery.tag like '%"+watchDog.getInput().get("cinemaName")+"%' ");
				 List<Gallery> galleryVos = query.setFirstResult(0).setMaxResults(10).getResultList();
				 vo.setObjectList(Gallery.formateGalleryVos(galleryVos));
			}
			else if("about".equals(watchDog.getInput().get("subtag")) 
					|| "cast".equals(watchDog.getInput().get("subtag")))
			{
				String movieCode = watchDog.getInput().get("cinemaName");
				StringBuilder sql = new StringBuilder();
				sql.append("from Movie movie where movie.status='Y' and movie.movieCode =:movieCode");
				 
				  query = watchDog.getSessionString().createQuery(sql.toString());
				query.setParameter("movieCode", movieCode);
				Movie movie = (Movie) query.getSingleResult();
				vo.setObject(Movie.formateMovieVo(movie));
				vo.setResponse(true);
			}

		}
		return vo;

	}

}
