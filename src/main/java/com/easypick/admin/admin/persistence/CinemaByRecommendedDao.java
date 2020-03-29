package com.easypick.admin.admin.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
 
import com.easypick.admin.entity.Language;
import com.easypick.admin.entity.Movie; 
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByRecommendedDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		MovieVo movievo = (MovieVo) vo.getObject();
		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y'");
		query = watchDog.getSessionString().createQuery(sql.toString());
		List<Language> languageVos = query.setFirstResult(0).setMaxResults(15).getResultList();
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
		 
		sql.append("from Movie movie where movie.status='Y' ");
		
		sql.append(" and movie.recommendedFlag='Y' "
				+ " and movie.relaseDate<= :relaseDate ");
		sql.append(
				" order by "
				+ " case when movie.priorityFlag = 'Y' "
				+ " then 2 when movie.priorityFlag = 'N' "
				+ " then 2   else 1 end  asc, "
				+ " movie.relaseDate desc ");

		Query query = watchDog.getSessionString().createQuery(sql.toString());
		query.setParameter("relaseDate", StringUitity.removeTime(new Date())); 

		List<Movie> movieVos = query.setFirstResult(0).setMaxResults(6).getResultList();

		return Movie.formateMovieVos(movieVos);
	}

}
