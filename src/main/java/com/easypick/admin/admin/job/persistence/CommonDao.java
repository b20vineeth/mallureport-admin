package com.easypick.admin.admin.job.persistence;
 

import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CommonDao {

	 
	 	void saveHomegeData(WatchDogVo watchdog, HomePageSetupVo homePageSetupVo);

	 

	 
}
