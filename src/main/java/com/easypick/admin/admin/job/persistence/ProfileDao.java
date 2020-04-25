package com.easypick.admin.admin.job.persistence;

import java.util.List;
import java.util.Map;

import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface ProfileDao {

	Map<String, List<? extends AbstractVo>> getActress(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getPopularProfile(WatchDogVo watchdog);

	Map<String, List<? extends AbstractVo>> getActor(WatchDogVo watchdog);

	 
	 
	 

	 
}
