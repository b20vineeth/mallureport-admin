package com.easypick.web.slideshow.persistence;
 
 
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.SlideShow;
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class SlideShowSqlDao implements SlideShowDao {

	@Override
	public ResponseVo saveSlideShowVo(WatchDogVo watchdog, SlideShowVo slideShowVo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from SlideShow slideshow where slideshow.slideshowId=:slideshowId");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("slideshowId", slideShowVo.getSlideShowid());
		SlideShow slideShow = null;
		try {
			slideShow = (SlideShow) query.getSingleResult();
			slideShow = SlideShow.populateSlideShow(slideShow, slideShowVo);
		} catch (Exception e) {
			slideShow = SlideShow.populateSlideShowVo(slideShowVo);
		}
		watchdog.getSessionString().saveOrUpdate(slideShow);
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(slideShowVo);
		return responseVo;
	}

	@Override
	public ResponseVo getSlideShow(WatchDogVo watchdog, SlideShowVo slideShowVo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from SlideShow slideshow where slideshow.slideshowId=:slideshowId");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("slideshowId", slideShowVo.getSlideShowid());
		ResponseVo responseVo = new ResponseVo();
		SlideShow slideShow = (SlideShow) query.getSingleResult();
		responseVo.setObject(SlideShow.formateSlideShowVo(slideShow));
		return responseVo;
	}

	@Override
	public ResponseVo getAllSlideshow(WatchDogVo watchdog, SlideShowVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();

		sql.append("from SlideShow slideShow where slideShow.status='Y'");
		if (Objects.nonNull(vo.getType()) && vo.getType().trim().length() > 0)
			sql.append("and slideShow.type=:type");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getType()) && vo.getType().trim().length() > 0)
			query.setParameter("type", vo.getType());

		sql.append(" order by slideShow.slideshowId desc ");

		Page page1 = new Page();
		List<SlideShow> slideShows = query.setFirstResult(0).setMaxResults(25).getResultList();
		responseVo.setObjectList(SlideShow.formateSlideShowVos(slideShows));

		responseVo.setResponse(true);
		return responseVo;
	}


	 
	 
 

}
