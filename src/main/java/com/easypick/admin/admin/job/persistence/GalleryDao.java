package com.easypick.admin.admin.job.persistence;

import java.util.List;
import java.util.Map; 
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface GalleryDao {

	Map<String, List<? extends AbstractVo>> getRunningMovies(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getUpcommingMovies(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getActressDetails(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getActorDetails(WatchDogVo watchdog); 

	 
	 

	 
}
