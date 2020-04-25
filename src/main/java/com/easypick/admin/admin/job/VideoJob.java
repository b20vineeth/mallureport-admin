package com.easypick.admin.admin.job;
 

import org.quartz.InterruptableJob; 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException; 
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.easypick.admin.admin.job.controller.JobControllerInterface;
import com.easypick.admin.admin.job.service.JobService; 
import com.easypick.framework.utility.exception.BussinessException; 

public class VideoJob extends QuartzJobBean implements InterruptableJob{
	
	private volatile boolean toStopFlag = true;
	
	@Autowired
	JobService jobService;
	@Autowired
	protected JobControllerInterface commonController;
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	      
		try {
		 commonController.execute("videoHomePageBussinessController");
		} catch (BussinessException e) {
			e.printStackTrace();
		}
		 
		 
		 
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Stopping thread... ");
		toStopFlag = false;
	}

}