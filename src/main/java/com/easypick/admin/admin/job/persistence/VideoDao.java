package com.easypick.admin.admin.job.persistence;

import java.util.List;
import java.util.Map; 
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface VideoDao {

	Map<String, List<? extends AbstractVo>> getRunningMovies(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getUpcommingMovies(WatchDogVo watchdog); 

	Map<String, List<? extends AbstractVo>> getTrendingVideos(WatchDogVo watchdog); 

	 
	 

	 
}
