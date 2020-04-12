package com.easypick.admin.admin.persistence;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
   
import com.easypick.admin.entity.SlideShow; 
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class SlideShowRelativePostDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		StringBuilder sql = new StringBuilder();
	 
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		List<? extends AbstractVo > slideShowVos = (List<? extends AbstractVo>) fetchReviewDetails(watchDog);
		if (slideShowVos.size() > 0)
			{
				if(Objects.isNull(vo.getObjectMapList()))
				{
					vo.setObjectMapList(objectMapList);
				}
				vo.getObjectMapList().put("slideShow-relative", slideShowVos);
			}
		return vo;

	}

	private List<? extends AbstractVo> fetchReviewDetails(WatchDogVo watchDog) {
		StringBuilder sql = new StringBuilder();
		 
		sql.append("from SlideShow slideShow where slideShow.status='Y' and slideShow.slideflg='N' ");
		sql.append( " order by  slideShow.slideshowId desc ");

		Query query = watchDog.getSessionString().createQuery(sql.toString()); 

		List<SlideShow> slideShowVos = query.setFirstResult(0).setMaxResults(2).getResultList();

		return SlideShow.formateSlideShows(slideShowVos);
	}

}
