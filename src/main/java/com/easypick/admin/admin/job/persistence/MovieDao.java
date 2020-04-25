package com.easypick.admin.admin.job.persistence;

import java.util.List;
import java.util.Map;

import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface MovieDao {

	Map<String, List<? extends AbstractVo>> getRunningMovies(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getUpcommingMovieVos(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getRecommendMovieVos(WatchDogVo watchdog);

	 

	 
}
