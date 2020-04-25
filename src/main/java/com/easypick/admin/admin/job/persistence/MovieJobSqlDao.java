package com.easypick.admin.admin.job.persistence;

import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
 
import com.easypick.admin.vo.DataVo; 
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo; 
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class MovieJobSqlDao implements MovieDao {

	String sql = null;
	private String filterType = null;
	int resultSize = 6;

	@Override
	public Map<String, List<? extends AbstractVo>> getRunningMovies(WatchDogVo watchdog) {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		this.filterType = "R";
		for (String lang : StringUitity.getLanguage()) {
			sql = constructQuery();
			findMovieDetails(watchdog, map, lang, sql);
		}
		return map;

	}

	@Override
	public Map<String, List<? extends AbstractVo>> getUpcommingMovieVos(WatchDogVo watchdog) {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		this.filterType = "U";
		for (String lang : StringUitity.getLanguage()) {

			sql = constructQuery();
			findMovieDetails(watchdog, map, lang, sql);
		}
		return map;
	}

	@Override
	public Map<String, List<? extends AbstractVo>> getRecommendMovieVos(WatchDogVo watchdog) {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		this.filterType = "P";
		this.resultSize = 3;
		sql = constructQuery();
		findMovieDetails(watchdog, map, "popular", sql);
		return map;
	}

	private String constructQuery() {

		StringBuilder sql = baseQuery();
		if ("P".equals(this.filterType)) {
			sql.append(" and movie.cin_rel_dat<=:date");
			sql.append(" and movie.recommended_flag='Y' ");

		} else {
			if ("U".equals(this.filterType)) {
				sql.append(" and movie.cin_rel_dat>:date");
			} else {
				sql.append(" and movie.cin_rel_dat<=:date");
			}
			sql.append(" and langsetup.langcod=:langCode ");

		}

		sql.append(
				" order by case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N' then 2   else 1 end ,"
						+ " movie.cin_rel_dat desc");

		return sql.toString();
	}

	private StringBuilder baseQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  movie.movie_code ,movie.movie_name,case when movie.cin_cast IS NULL or movie.cin_cast = '' "
				+ " then ' ' else  (SELECT GROUP_CONCAT( concat(profile_name, '#', profile_code)) FROM Profile "
				+ "where  profileid in (REPLACE(movie.cin_cast,'#','')))  end cin_cast ,langsetup.langnam, gallery.thumb1,"
				+ "gallery.thumb2,movie.short_desc  ,movie.cin_rel_dat,movie.rate,movie.certificate, "
				+ "case when movie.mov_type IS NULL or movie.mov_type = '' then ' ' "
				+ "else  (SELECT GROUP_CONCAT( concat(data_name, '#', data_code))  FROM datasetup "
				+ " where type='movieType' and id in (REPLACE(movie.mov_type,'#','')))  end  mov_type,"
				+ " (SELECT GROUP_CONCAT( concat( langsetup.langnam, '#', langsetup.langcod)) FROM "
				+ " language_map inner join langsetup on language=id  where  movid in (REPLACE(movie.movieId,'#','')) )  lang"
				+ " FROM  movie  movie inner join language_map  lang on movie.movieId=lang.movid inner join langsetup "
				+ "langsetup ON langsetup.id=lang.language   INNER join cinegallery gallery "
				+ " on gallery.movid=movie.movieId where movie.status='Y'");
		return sql;
	}

	private void findMovieDetails(WatchDogVo watchdog, Map<String, List<? extends AbstractVo>> map, String lang,
			String sql) {
		Query query = watchdog.getSessionString().createSQLQuery(sql);
		query.setDate("date", StringUitity.removeTime(new Date()));

		if (!"P".equals(this.filterType))
			query.setParameter("langCode", lang);
		List<MovieVo> vos = excuteQuery(query);
		map.put(lang, vos);
	}

	private List<MovieVo> excuteQuery(Query query) {

		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();

		List<MovieVo> vos = new ArrayList<>();
		DataVo dataVo = null;

		if (object.size() > 0) {
			MovieVo.populateMovieVo(object, vos);

		}
		return vos;
	}

	 

}
