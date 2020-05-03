package com.easypick.admin.admin.job.persistence;
 

import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.admin.vo.JobVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CommonDao {
 void saveHomegeData(WatchDogVo watchdog, HomePageSetupVo homePageSetupVo);
 ResponseVo saveJob(WatchDogVo watchdog, JobVo vo);
ResponseVo viewJob(WatchDogVo watchdog, JobVo vo);
ResponseVo findJob(WatchDogVo watchdog, JobVo vo);
JobVo findJob(WatchDogVo watchdog, String jobCode);
 }
