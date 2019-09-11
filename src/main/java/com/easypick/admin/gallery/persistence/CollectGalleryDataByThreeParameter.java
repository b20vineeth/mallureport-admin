package com.easypick.admin.gallery.persistence; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.commonUtility.PageConverter;
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
public class CollectGalleryDataByThreeParameter implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo) throws BussinessException {
		Map<String, String> pageItem=watchDogVo.getInput();
		String keyElement=pageItem.get("param3");
		ResponseVo vo=null;
		if(Objects.nonNull(keyElement))
		{

			Page page= new PageConverter().validatePageRequest(keyElement.trim());
			if(page.isPages())
			{
				vo=collectPageData(watchDogVo,page,pageItem);
			}
			else
			{
				vo=collectURLData(watchDogVo,page,pageItem);
			}
		}
		else
		{
			throw new BussinessException(ExceptionList.INVALID_PAGE);
		}
		vo.setScreenMode("GalleryList");
		return vo;

	}

	private ResponseVo collectURLData(WatchDogVo watchDogVo, Page page, Map<String, String> pageItem) throws BussinessException {

		StringBuilder queryString=null;
		queryString=new StringBuilder();
		queryString.append(GalleryResourceQuery.SELECT_URL_QUERY);
		ResponseVo vo=null;
		queryString.append(" and tag like \"%"+pageItem.get("param2")+"%\" AND gallery_url LIKE "+'"'+pageItem.get("param3")+'"');
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(queryString.toString()); 
		List<Object[]> galleryItems= (List<Object[]>)((org.hibernate.Query) q).list(); 
		GalleryVo galleryVo=null; 
		for(Object[] items: galleryItems){

			galleryVo=new GalleryItemsMapper().homePageMmapper(items);
		}

		if(Objects.nonNull(galleryVo))
		{
			vo=new ResponseVo();
			vo.setObject(galleryVo); 
		}
		else
		{
			throw new BussinessException(ExceptionList.RESULT_EMPTY);
		}
		return vo;

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
	private ResponseVo collectPageData(WatchDogVo watchDogVo,  Page page, Map<String, String> pageItem) throws BussinessException {
		StringBuilder queryString=null;

		queryString=new StringBuilder();
		queryString.append(GalleryResourceQuery.HOME_PAGE_QUERY);
		ResponseVo vo=null;
		String arg=" and tag like \"%"+pageItem.get("param2")+"%\" ";
		queryString.append(arg);
		queryString.append(" order by gallery_id desc");
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(queryString.toString()); 
		int firstResult=((page.getCurrentPage()-1)*page.MAX_RESULT);
		
		q.setFirstResult(firstResult);
		q.setMaxResults(Page.MAX_RESULT);
		List<Object[]> galleryItems= (List<Object[]>)((org.hibernate.Query) q).list(); 
		GalleryVo galleryVo=null; 
		List<GalleryVo> galleryVos=new ArrayList<>();
		for(Object[] items: galleryItems){

			galleryVo=new GalleryItemsMapper().homePageMmapper(items);
			galleryVos.add(galleryVo);

		}
		totalResult(arg,watchDogVo,page);

		if(!galleryVos.isEmpty())
		{
			vo=new ResponseVo();
			vo.setObjectList(galleryVos);
			vo.setPage(page);
		}
		else
		{
			throw new BussinessException(ExceptionList.RESULT_EMPTY);
		}
		return vo;
	}

	private Page totalResult(String queryString01, WatchDogVo watchDogVo, Page page) {

		StringBuilder query=new  StringBuilder();
		query.append(CommonResourceQuery.START_FIND_COUNT_ROW_RESULT);
		query.append(GalleryResourceQuery.COUNT_PARAM_THREE_PAGE_QUERY);
		query.append(queryString01);
		query.append(CommonResourceQuery.END_FIND_COUNT_ROW_RESULT);
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(query.toString()); 
		Integer rowCount =0;
		rowCount =Integer.parseInt( (String) q.uniqueResult());
		page.setTotalResult(rowCount);
		try
		{
			 
			page.setTotalPage(totalPage(rowCount));
		}
		catch(Exception e)
		{
			page.setTotalPage(0);
		}

		return page;
	}

}
