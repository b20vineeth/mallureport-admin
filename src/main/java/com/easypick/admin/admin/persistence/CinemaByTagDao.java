package com.easypick.admin.admin.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.query.Query;
import com.easypick.admin.entity.UserSetup;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper;
import com.easypick.framework.utility.persistence.mapper.UserItemsMapper;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaByTagDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {

		vo = new ResponseVo();
		String query = constructPagableQuery(Query.GALLERY_LIST_BY_TAG, watchDogVo);
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
			query = constructPagableQuery(Query.GALLERY_COUNT_BY_TAG, watchDogVo);
			q = watchDogVo.getSessionString().createSQLQuery(Query.COUNT_PREFIX + query + Query.COUNT_SUFFIX);
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
