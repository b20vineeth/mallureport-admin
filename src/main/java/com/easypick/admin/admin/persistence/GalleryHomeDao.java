package com.easypick.admin.admin.persistence;
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.MovieGallery; 
import com.easypick.admin.vo.MovieGalleryVo;
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class GalleryHomeDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		 
		sql.append("from MovieGallery ");
		Query query = watchDog.getSessionString().createQuery(sql.toString()); 
		List<MovieGallery> movieGalleryVos = query.getResultList();
		Map<String,List<? extends AbstractVo>> map=groupByMovietype(movieGalleryVos);

		if(Objects.isNull(vo.getObjectMapList()))
		{
			vo.setObjectMapList(objectMapList);
		}
		 vo.setObjectMapList(map);
		return vo;

	}

	private Map<String, List<? extends AbstractVo>> groupByMovietype(List<MovieGallery> movieGalleryVos) {

		Map<String,  List<? extends AbstractVo>> gallery=new HashMap<>();
		 MovieGalleryVo  galleryVo;
		for(MovieGallery movieGallery:movieGalleryVos)
		{
			if(Objects.isNull(gallery.get(movieGallery.getType())))
			{
				 galleryVo=new  MovieGalleryVo();
				 galleryVo.setTag(movieGallery.getTag().replace("#", ""));
				 galleryVo.setThumb1(movieGallery.getThumb1());
				 galleryVo.setTitle(movieGallery.getTitle());
				 galleryVo.setUrl(movieGallery.getUrl());
				 gallery.put(movieGallery.getType(),Stream.of(galleryVo).collect(Collectors.toList()));
			}
			else
			{
				 List galleryList = gallery.get(movieGallery.getType());
				 galleryVo=new  MovieGalleryVo();
				 galleryVo.setTag(movieGallery.getTag().replace("#", ""));
				 galleryVo.setThumb1(movieGallery.getThumb1());
				 galleryVo.setTitle(movieGallery.getTitle());
				 galleryVo.setUrl(movieGallery.getUrl());
				 galleryList.add(galleryVo);   
				
			}
		}

		return gallery;
	}

 

	 



	 
}
