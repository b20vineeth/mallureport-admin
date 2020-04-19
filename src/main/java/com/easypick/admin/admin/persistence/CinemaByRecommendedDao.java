package com.easypick.admin.admin.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByRecommendedDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		MovieVo movievo = (MovieVo) vo.getObject();
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		List<? extends AbstractVo > movies = (List<MovieVo>) fetchMovieDetails(watchDog, movievo);
		if (movies.size() > 0)
			{
				if(Objects.isNull(vo.getObjectMapList()))
				{
					vo.setObjectMapList(objectMapList);
				}
				vo.getObjectMapList().put("Movie-Recommended", movies);
			}
		return vo;

	}

	private List<? extends AbstractVo> fetchMovieDetails(WatchDogVo watchDog, MovieVo movievo) {
		StringBuilder sql = new StringBuilder();
		SQLQuery query;
		sql.append(
				"SELECT movie.movie_code ,movie.movie_name,movie.cin_cast,lang.langnam,"
				+ "gallery.thumb1,gallery.thumb2,movie.short_desc , movie.cin_rel_dat,movie.rate,movie.certificate,movie.mov_type "
						+ " FROM  movie  movie inner join langsetup lang on movie.cin_lang=lang.id "
						+ " INNER join cinegallery gallery on gallery.movid=movie.movieId "
						+ " where movie.status='Y' and movie.recommended_flag='Y' ");

		 
		sql.append(
				" order by case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N' then 2   else 1 end ,"
						+ " movie.cin_rel_dat asc limit 3");

		query = watchDog.getSessionString().createSQLQuery(sql.toString());
		 

		List<Object[]> objects = (List<Object[]>) (query).list();
		List<MovieVo> movieVos = new ArrayList<>();
		if (objects.size() > 0) {
			for (Object[] items : objects) {
				try {
					MovieVo movieVo = new MovieItemMapper().listItem(items);
					movieVos.add(movieVo);

				} catch (Exception e) {

				}
			}
		
		}
		return movieVos;
	}

}
