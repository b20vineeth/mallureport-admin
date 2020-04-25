package com.easypick.admin.admin.job.bussinesscontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.job.persistence.CommonDao; 
import com.easypick.admin.admin.job.persistence.VideoDao;
import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class VideoHomePageBussinessController implements JobBI {

	@Autowired
	VideoDao dao;
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
		Map<String, List<? extends AbstractVo>> upcommingMovieVos = dao.getUpcommingMovies(watchdog);
		Map<String, List<? extends AbstractVo>> trendingVos = dao.getTrendingVideos(watchdog);
		 vo.getJsonobj().put("trending", trendingVos);
		 vo.getJsonobj().put("running-movies", runningMovieVos);
		vo.getJsonobj().put("upcomming-movies", upcommingMovieVos);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonobj = mapper.writeValueAsString(vo);
			HomePageSetupVo homePageSetupVo = new HomePageSetupVo();
			homePageSetupVo.setData(jsonobj);
			homePageSetupVo.setType("VID");
			commonDao.saveHomegeData(watchdog, homePageSetupVo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return vo;

	}

}
