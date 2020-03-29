package com.easypick.admin.gallery.persistence; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao; 
import com.easypick.framework.utility.persistence.mapper.GalleryItemsMapper;
import com.easypick.framework.utility.query.GalleryResourceQuery;
import com.easypick.framework.utility.resource.GalleryResource;
import com.easypick.framework.utility.vo.AbstractVo; 
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CollectGalleryDataByOneParameter implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vod) throws BussinessException {

		String[] homePageItem=GalleryResource.GALLERY_HOMEPAGE.split(",");
		StringBuilder queryString=null;
		ResponseVo vo=new ResponseVo();
		for(String homePage :homePageItem)
		{ 
			String[] key=homePage.split("-");
			queryString=new StringBuilder();
			queryString.append(GalleryResourceQuery.HOME_PAGE_QUERY);
			queryString.append(" and tag like \"%"+key[0]+"%\" order by gallery_id desc  limit "+key[1]);
			populateData(queryString.toString(),watchDogVo,vo,key[0]);
		}
		vo.setScreenMode("gallery/GalleryHome");
		return vo;


	}

	private void populateData(String queryString01, WatchDogVo watchDogVo, ResponseVo vo, String key) {


		Map<String,List<?  extends AbstractVo>> objectMap=vo.getObjectMapList();
		if(Objects.isNull(objectMap))
			objectMap=new HashMap<>();
		
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(queryString01);  
		List<Object[]> galleryItems= (List<Object[]>)((org.hibernate.Query) q).list(); 
		GallerySetupVo galleryVo=null; 
		List<GallerySetupVo> galleryVos=new ArrayList<>();
		for(Object[] items: galleryItems){

			galleryVo=new GalleryItemsMapper().homePageMmapper(items);
			galleryVos.add(galleryVo);

		}
		objectMap.put(key, galleryVos);
		vo.setObjectMapList(objectMap);
	}

}
