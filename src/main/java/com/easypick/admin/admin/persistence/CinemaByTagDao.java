package com.easypick.admin.admin.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
  
import com.easypick.admin.vo.MovieVo; 
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper; 
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByTagDao implements Dao {


	public static final String GALLERY_LIST_BY_TAG="SELECT movie.thumbnail,movie.short_desc,movie.movie_code,movie.movie_name ,"
			+ " movie.cin_rel_dat, movie.rate ,lan.`langnam` FROM `movie`  movie inner join langsetup lan on lan.id=movie.cin_lang "
			+ " WHERE movie.status='Y'";
	
	public static final String GALLERY_COUNT_BY_TAG="SELECT  COUNT(*) total FROM `movie`  movie inner join langsetup lan on lan.id=movie.cin_lang "
			+ " WHERE movie.status='Y'";
	public static final String COUNT_PREFIX = " select concat(total,'') total from (";
	public static final String COUNT_SUFFIX = " ) t1";
	
	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {

		vo = new ResponseVo();
		String query = constructPagableQuery(GALLERY_LIST_BY_TAG, watchDogVo);
		SQLQuery q = watchDogVo.getSessionString()
				.createSQLQuery(query + Page.getPage(watchDogVo.getInput().get("page")));
		List<Object[]> objects = (List<Object[]>) ((org.hibernate.Query) q).list();
		if (objects.size() > 0) {
			List<MovieVo> movieVos = new ArrayList<>();
			for (Object[] items : objects) {
				try {
					MovieVo movieVo = new MovieItemMapper().map(items);
					movieVos.add(movieVo);

				} catch (Exception e) {

				}

			}
			vo.setObjectList(movieVos);
			query = constructPagableQuery(GALLERY_COUNT_BY_TAG, watchDogVo);
			q = watchDogVo.getSessionString().createSQLQuery(COUNT_PREFIX + query + COUNT_SUFFIX);
			Integer rowCount = 0;
			try {
				rowCount = Integer.parseInt((String) q.uniqueResult());
				Page page = new Page();
				page.setTotalResult(rowCount);
				page.setPerPage(10);
				page.updateTotalPage();
				watchDogVo.setPage(page);
			} catch (Exception e) {

			}
		} else {
			throw new BussinessException("404");
		}

		return vo;
	}

	private String constructPagableQuery(String galleryListByTag, WatchDogVo watchDogVo) {

		return galleryListByTag + " and tag like '%" + watchDogVo.getInput().get("tag") + "%' ";

	}

}
