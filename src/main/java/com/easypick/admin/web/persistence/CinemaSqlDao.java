package com.easypick.admin.web.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.CinemaVo;
import com.easypick.admin.vo.ModuleVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.CommonDao;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.validation.CommonValidator;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaSqlDao implements CommonDao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo) throws BussinessException {

		@SuppressWarnings("deprecation") 
		SQLQuery sql=null;
		if(ModuleVo.RUNNING_MOVIES.equals(watchDogVo.getType()))
		{
		 sql = watchDogVo.getSessionString().createSQLQuery(CommonResourceQuery.LIST_CINEMA_DETAILS
				+" order by cat.id desc "+CommonValidator.getlimit(watchDogVo.getPage()))
				.setParameter("lang", "MAL");
		}
		else
		{
			 sql = watchDogVo.getSessionString().createSQLQuery(CommonResourceQuery.LIST_CINEMA_DETAILS
						+" order by cat.id desc "+CommonValidator.getlimit(watchDogVo.getPage()))
						.setParameter("lang", "MAL");
		}
		List<CinemaVo> cinemaVos = null;
		List<Object[]> objectList = (List<Object[]>) ((org.hibernate.Query) sql).list();
		if (Objects.nonNull(objectList)) {
			cinemaVos = new ArrayList<>();
			CinemaVo cinemaVo = null;
			for (Object[] obj : objectList) {
				cinemaVo = new CinemaVo();
				cinemaVo.setCatName(Objects.nonNull(obj[0]) ? obj[0].toString() : "");
				cinemaVo.setCatCode(Objects.nonNull(obj[1]) ? obj[1].toString() : "");
				cinemaVo.setTitle(Objects.nonNull(obj[2]) ? obj[2].toString() : "");
				cinemaVo.setShortDesc(Objects.nonNull(obj[3]) ? obj[3].toString() : "");
				cinemaVo.setThumbUrl(Objects.nonNull(obj[4]) ? obj[4].toString() : "");
				cinemaVos.add(cinemaVo);
			}
			
		}
		
		
		sql = watchDogVo.getSessionString().createSQLQuery(CommonResourceQuery.COUNT_CINEMA_DETAILS)
				 .setParameter("lang", "MAL");
		Object obj =  ((org.hibernate.Query) sql).getSingleResult();
		int count =0;
		 count=Integer.parseInt(obj.toString());
		ResponseVo vo = new ResponseVo();
		vo.setModule(watchDogVo.getModule());
		vo.setSubModule(watchDogVo.getSubModule());
		 Page page=watchDogVo.getPage();
		 page.setTotalResult(count);
		 page.updateTotalPage();
		 vo.setPage(page);
		vo.setObjectList(cinemaVos);
		return vo;
	}

}
