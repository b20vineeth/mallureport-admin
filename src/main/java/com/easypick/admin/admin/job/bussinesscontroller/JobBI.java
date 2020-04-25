package com.easypick.admin.admin.job.bussinesscontroller;

import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface JobBI {

	ResponseVo execute(WatchDogVo watchdog);

}
