package com.easypick.admin.admin.job;
 

import org.quartz.InterruptableJob; 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException; 
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.easypick.admin.admin.job.service.JobService;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.common.bussinesscontroller.ControllerInterface;
 

public class CronJob extends QuartzJobBean implements InterruptableJob{
	
	private volatile boolean toStopFlag = true;
	
	@Autowired
	JobService jobService;
	@Autowired
	protected ControllerInterface commonController;
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	//	JobKey key = jobExecutionContext.getJobDetail().getKey();
	//	System.out.println("Cron Job started with key :" + key.getName() + ", Group :"+key.getGroup() + " , Thread Name :"+Thread.currentThread().getName() + " ,Time now :"+new Date());
		
	//	System.out.println("======================================");
	//	System.out.println("Accessing annotation example: "+jobService.getAllJobs());
	//	List<Map<String, Object>> list = jobService.getAllJobs();
	//	System.out.println("Job list :"+list);
		System.out.println("================== Start ====================");
		MovieVo movie=new MovieVo();
		 movie.setFilterType("R");
		try {
			ResponseVo response = commonController.testdata(movie);
		} catch (BussinessException e) {
		 
			e.printStackTrace();
		}
		System.out.println("================== End ====================");
		
		//*********** For retrieving stored key-value pairs ***********/
		/*JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
		String myValue = dataMap.getString("myKey");
		System.out.println("Value:" + myValue);*/

		 
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Stopping thread... ");
		toStopFlag = false;
	}

}