package com.easypick.admin.admin.persistence;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
  
import com.easypick.admin.entity.MovieReview;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper;
import com.easypick.framework.utility.persistence.mapper.MovieReviewItemMapper;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByLatestReviewDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		
		StringBuilder sql = new StringBuilder();
	 
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		List<? extends AbstractVo > movies = (List<MovieReviewVo>) fetchReviewDetails(watchDog);
		if (movies.size() > 0)
			{
				if(Objects.isNull(vo.getObjectMapList()))
				{
					vo.setObjectMapList(objectMapList);
				}
				vo.getObjectMapList().put("Movie-Review", movies);
			}
		return vo;

	}

	private List<? extends AbstractVo> fetchReviewDetails(WatchDogVo watchDog) {
		StringBuilder sql = new StringBuilder();
		SQLQuery query; 
		sql.append("SELECT rev.short_desc,rev.title ,gallery.thumb1, movie.rate,movie.certificate ,movie.mov_type ,movie.cin_rel_dat "
				+ "FROM `movrev` rev  inner join cinegallery gallery on  gallery.movid=rev.movid "
				+ "inner join movie movie on movie.movieId=rev.movid "
				+ "order by  case when movie.priority_flag = 'Y' then 2 "
				+ "when movie.priority_flag  = 'N'  then 2   else 1 end  asc,   "
				+ "movie.cin_rel_dat  desc  limit 6 ");

		query = watchDog.getSessionString().createSQLQuery(sql.toString());

		List<Object[]> objects = (List<Object[]>) (query).list();
		List<MovieReviewVo> movieReviewVos = new ArrayList<>();
		if (objects.size() > 0) {
			for (Object[] items : objects) {
				try {
					MovieReviewVo movieVo = new MovieReviewItemMapper().listItem(items);
					movieReviewVos.add(movieVo);

				} catch (Exception e) {

				}
			}
		
		}
		return movieReviewVos;
	}

}
