package com.easypick.web.events;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.persistence.EventDao;
import com.easypick.web.events.vo.CastDataVo;
import com.easypick.web.events.vo.GalleryDataVo;
import com.easypick.web.events.vo.MovieDataVo;
import com.easypick.web.events.vo.ReviewDataVo;
import com.easypick.web.events.vo.VideoDataVo;
import com.google.gson.Gson;

@Component
@EventListener(eventKey = "com.admin.saveMovie")
public class SaveMovieDetailsEvent implements EventImpl {

	@Autowired
	private EventDao dao;

	Gson gson;

	@Override
	public void execute(WatchDogVo watchdog, ResponseVo vo) {

		MovieDataVo movie = dao.findMovie(vo.getId(), watchdog);
		List<GalleryDataVo> galleryDataVos = dao.findAllGallery(vo.getId(), watchdog);
		List<CastDataVo> castDataVos = dao.findAllCast(movie.getCast(), watchdog);
		List<VideoDataVo> videoDataVos = dao.findAllVideo(vo.getId(), watchdog);
		List<ReviewDataVo> reviewDataVos = dao.findAllReviewData(vo.getId(), watchdog);
		movie.setCastData(castDataVos);
		movie.setGalleryData(galleryDataVos);
		movie.setVideoData(videoDataVos);
		movie.setReviewData(reviewDataVos);
		dao.saveMovieData(movie, watchdog);

	}

}
