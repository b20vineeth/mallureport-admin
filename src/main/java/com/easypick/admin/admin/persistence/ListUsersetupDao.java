package com.easypick.admin.admin.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.query.Query;
import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.commonUtility.DateOperation;
import com.easypick.framework.utility.commonUtility.PageConverter;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.persistence.mapper.GalleryItemsMapper;
import com.easypick.framework.utility.persistence.mapper.UserItemsMapper;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.query.GalleryResourceQuery;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class ListUsersetupDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {
		List<UserSetupVo> objectlist = populateUserSetup(watchDogVo, vo);
		vo.setObjectList(objectlist); 
		Page page = totalResult(watchDogVo, vo);
		vo.setPage(page);
		
		return vo;
	}

	private Page totalResult(WatchDogVo watchDogVo, ResponseVo vo) {

		String query = constructPagableQuery(vo);
		SQLQuery q = watchDogVo.getSessionString().createSQLQuery(query);
		q.setParameter("cmpcod", watchDogVo.getCmpcode().toUpperCase());
		Integer rowCount = 0;
		try
		{
		rowCount = Integer.parseInt((String) q.uniqueResult());
		}catch(Exception e)
		{
			System.out.println(e);
		}
		Page page = new Page();
		page.setTotalResult(rowCount);
		try {
			Integer totalPage = new PageConverter().totalPage(rowCount);

			page.setTotalPage(totalPage);
		} catch (Exception e) {
			page.setTotalPage(0);
		}

		return page;

	}

	private String constructPagableQuery(ResponseVo vo) {
		StringBuilder query = new StringBuilder();
		UserSetupVo userSetupVo = (UserSetupVo) vo.getObject();
		query.append(CommonResourceQuery.START_FIND_COUNT_ROW_RESULT);
		query.append(Query.COUNT_LIST_IN_USERSETUP_QUERY);
		
		if (Objects.nonNull(userSetupVo.getEmail()) 
				&& userSetupVo.getEmail().trim().length()>0)
			query.append(" AND EMAIL  like '%" + userSetupVo.getEmail() + "%' ");
		if (Objects.nonNull(userSetupVo.getUsername()) 
				&& userSetupVo.getUsername().trim().length()>0)
			query.append(" AND USER_CODE  like '%" + userSetupVo.getUsername() + "%' ");
		
		query.append(" order by USER_ID desc ");
		query.append(CommonResourceQuery.END_FIND_COUNT_ROW_RESULT);
		return query.toString();
	}

	

	private List<UserSetupVo> populateUserSetup(WatchDogVo watchDogVo, ResponseVo vo) {

		String query = constructQuery(vo);
		SQLQuery q = watchDogVo.getSessionString().createSQLQuery(query);
		q.setParameter("cmpcod", watchDogVo.getCmpcode().toUpperCase()); 

		List<Object[]> objects = (List<Object[]>) ((org.hibernate.Query) q).list();
		List<UserSetupVo> userSetupVos = new ArrayList<>();
		for (Object[] items : objects) {
			try
			{
			UserSetupVo UserSetupVo = new UserItemsMapper().Mapper(items);
			userSetupVos.add(UserSetupVo);
			}
			catch(Exception e)
			{
				
			}
			

		}
		return userSetupVos;
	}

	private String constructQuery(ResponseVo vo) {
		UserSetupVo userSetupVo = (UserSetupVo) vo.getObject();
		StringBuilder queryString01 = new StringBuilder();
		queryString01.append(Query.CHECK_LIST_IN_USERSETUP);
		if (Objects.nonNull(userSetupVo.getEmail()) 
				&& userSetupVo.getEmail().trim().length()>0)
			queryString01.append("AND EMAIL  like '%" + userSetupVo.getEmail() + "%' ");
		if (Objects.nonNull(userSetupVo.getUsername()) 
				&& userSetupVo.getUsername().trim().length()>0)
			queryString01.append("AND USER_CODE  like '%" + userSetupVo.getUsername() + "%' ");
		if(Objects.nonNull(userSetupVo.getValidityFrom()))
		 {
				queryString01.append("AND validfrm  > '" + new DateOperation().convertDateToString(userSetupVo.getValidityFromFromat(),"yyyy-mm-dd hh:mm:ss") + "' ");
		 }
		if(Objects.nonNull(userSetupVo.getValidityto()))
		 {
				queryString01.append("AND VALIDTO  < '" + new DateOperation().convertDateToString(userSetupVo.getValidityFromFromat(),"yyyy-mm-dd hh:mm:ss") + "' ");
		 }
		queryString01.append(" order by USER_ID desc  Limit "+new PageConverter().getStartPage(userSetupVo.getPage()));
		return queryString01.toString();
	}

}
