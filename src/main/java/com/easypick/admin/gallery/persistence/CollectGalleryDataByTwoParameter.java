package com.easypick.admin.gallery.persistence; 
import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.exception.ExceptionList;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.persistence.mapper.GalleryItemsMapper;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.query.GalleryResourceQuery; 
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CollectGalleryDataByTwoParameter implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo1)  throws BussinessException {
		Map<String, String> pageItem=watchDogVo.getInput();

		StringBuilder queryString=null;
		ResponseVo vo=null;
		queryString=new StringBuilder();
		queryString.append(GalleryResourceQuery.HOME_PAGE_QUERY);
		queryString.append(" and tag like \"%"+pageItem.get("param2")+"%\" order by gallery_id desc");
		vo=populateData(queryString.toString(),watchDogVo);
		Page page=totalResult(watchDogVo);
		if(Objects.isNull(vo))
		{
			vo=new ResponseVo();
		}
		vo.setPage(page);
		vo.setScreenMode("GalleryList");
		return vo;


	}

	private Page totalResult(WatchDogVo watchDogVo) {

		StringBuilder query=new  StringBuilder();
		query.append(CommonResourceQuery.START_FIND_COUNT_ROW_RESULT);
		query.append(GalleryResourceQuery.COUNT_HOME_PAGE_QUERY);
		query.append(CommonResourceQuery.END_FIND_COUNT_ROW_RESULT);
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(query.toString()); 
		Integer rowCount =0;
		rowCount =Integer.parseInt( (String) q.uniqueResult());
		Page page=new Page();
		page.setTotalResult(rowCount);
		try
		{
			Integer totalPage=totalPage(rowCount);
					
			page.setTotalPage(totalPage);
		}
		catch(Exception e)
		{
			page.setTotalPage(0);
		}
		
		return page;

	}

	private Integer totalPage(Integer rowCount) {
		Integer rem=rowCount%Page.MAX_RESULT;
		int total=(rowCount-rem)/Page.MAX_RESULT;
		if(rem>0)
		{
			total+=1;
		}
		 
		return total;
	}

	private ResponseVo populateData(String queryString01, WatchDogVo watchDogVo) throws BussinessException{

		 ResponseVo vo=null;
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(queryString01); 
		q.setFirstResult(0);
		q.setMaxResults(Page.MAX_RESULT);
		List<Object[]> galleryItems= (List<Object[]>)((org.hibernate.Query) q).list(); 
		GallerySetupVo galleryVo=null; 
		List<GallerySetupVo> galleryVos=new ArrayList<>();
		for(Object[] items: galleryItems){

			galleryVo=new GalleryItemsMapper().homePageMmapper(items);
			galleryVos.add(galleryVo);

		}
		if(!galleryVos.isEmpty())
		{
			vo=new ResponseVo();
			vo.setObjectList(galleryVos);
		}
		else
		{
			throw new BussinessException(ExceptionList.RESULT_EMPTY);
		}
		return vo;

	}

	 

}
