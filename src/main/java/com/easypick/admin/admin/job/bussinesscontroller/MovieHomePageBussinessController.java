package com.easypick.admin.admin.job.bussinesscontroller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.job.persistence.CommonDao;
import com.easypick.admin.admin.job.persistence.MovieDao;
import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
@Repository
public class MovieHomePageBussinessController implements JobBI {

	@Autowired
	MovieDao dao;
	@Autowired
	CommonDao commonDao;
	@Override
	public ResponseVo execute(WatchDogVo watchdog) {

		ResponseVo vo = new ResponseVo();
		Map<String, Map<String, List<? extends AbstractVo>>> object = new HashMap<>();
		if (Objects.isNull(vo.getJsonobj())) {
			vo.setJsonobj(object);
		}
		Map<String, List<? extends AbstractVo>> runningMovieVos = dao.getRunningMovies(watchdog);
		Map<String, List<? extends AbstractVo>> upcommingMovieVos = dao.getUpcommingMovieVos(watchdog);
		Map<String, List<? extends AbstractVo>> recommendMovieVos = dao.getRecommendMovieVos(watchdog);
		Map<String, List<? extends AbstractVo>> movieReviewVos = dao.getMovieReviewVos(watchdog);
		vo.getJsonobj().put("movie-review", movieReviewVos);
		vo.getJsonobj().put("running-movies", runningMovieVos);
		vo.getJsonobj().put("upcomming-movies", upcommingMovieVos);
		vo.getJsonobj().put("recommend-movies", recommendMovieVos);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonobj = mapper.writeValueAsString(vo);
			HomePageSetupVo homePageSetupVo = new HomePageSetupVo();
			homePageSetupVo.setData(jsonobj);
			homePageSetupVo.setType("CIN");
			commonDao.saveHomegeData(watchdog, homePageSetupVo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return vo;

	}
	 

}
