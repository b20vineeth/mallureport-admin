package com.easypick.admin.admin.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Language;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper;
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
			List<? extends AbstractVo> movies = (List<MovieVo>) fetchMovieDetails(watchDog, movievo);
			if (movies.size() > 0) {
				if (Objects.isNull(vo.getObjectMapList())) {
					vo.setObjectMapList(objectMapList);
				}
				vo.getObjectMapList().put("Movie-" + movievo.getFilterType() + "-" + languageVo.getLangCode(), movies);
			}

		}
		vo.setResponse(true);
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
						+ " where movie.status='Y' ");

		if ("U".equals(movievo.getFilterType())) {
			sql.append(" and movie.cin_rel_dat>:date");
		} else {
			sql.append(" and movie.cin_rel_dat<=:date");
		}
		sql.append(" and lang.langcod=:langCode ");
		sql.append(
				" order by case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N' then 2   else 1 end ,"
						+ " movie.cin_rel_dat asc");

		query = watchDog.getSessionString().createSQLQuery(sql.toString());
		query.setParameter("date", StringUitity.removeTime(new Date()));
		query.setParameter("langCode", movievo.getLanguageName());
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
