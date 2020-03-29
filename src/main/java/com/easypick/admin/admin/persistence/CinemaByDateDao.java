package com.easypick.admin.admin.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Language;
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.Video;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByDateDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		MovieVo movievo = (MovieVo) vo.getObject();
		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y'");
		query = watchDog.getSessionString().createQuery(sql.toString());
		List<Language> languageVos = query.setFirstResult(0).setMaxResults(15).getResultList();
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		for (Language languageVo : languageVos) {
			movievo.setLanguageName(languageVo.getLangCode());
			List<? extends AbstractVo > movies = (List<MovieVo>) fetchMovieDetails(watchDog, movievo);
			if (movies.size() > 0)
				{
					if(Objects.isNull(vo.getObjectMapList()))
					{
						vo.setObjectMapList(objectMapList);
					}
					vo.getObjectMapList().put("Movie-"+movievo.getFilterType()+"-"+languageVo.getLangCode(), movies);
				}

		} 
		vo.setResponse(true);
		return vo;

	}

	private List<? extends AbstractVo> fetchMovieDetails(WatchDogVo watchDog, MovieVo movievo) {
		StringBuilder sql = new StringBuilder();
		Query query;
		sql.append("from Movie movie where movie.status='Y'");
		if ("U".equals(movievo.getFilterType())) {
			sql.append(" and movie.relaseDate>:date");
		} else {
			sql.append(" and movie.relaseDate<=:date");
		}
		sql.append(" and movie.lang.langCode=:langCode ");
		sql.append(
				" order by case when movie.priorityFlag = 'Y' then 2 when movie.priorityFlag = 'N' then 2   else 1 end , movie.relaseDate asc");

		query = watchDog.getSessionString().createQuery(sql.toString());
		query.setParameter("date", StringUitity.removeTime(new Date()));
		query.setParameter("langCode", movievo.getLanguageName());

		List<Movie> movieVos = query.setFirstResult(0).setMaxResults(10).getResultList();

		return Movie.formateMovieVos(movieVos);
	}

}
