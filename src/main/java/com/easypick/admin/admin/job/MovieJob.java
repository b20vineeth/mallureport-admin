package com.easypick.admin.admin.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.easypick.admin.admin.job.config.JobInterface;
import com.easypick.admin.admin.job.controller.JobControllerInterface;
import com.easypick.admin.admin.job.service.JobService;
import com.easypick.framework.utility.exception.BussinessException;
@Component
public class MovieJob extends QuartzJobBean implements JobInterface {

	private volatile boolean toStopFlag = true;

	@Autowired
	JobService jobService;
	@Autowired
	protected JobControllerInterface commonController;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		invoke();

	}

	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Stopping thread... ");
		toStopFlag = false;
	}

	public void invoke() {

		try {
			commonController.execute("movieHomePageBussinessController");

		} catch (BussinessException e) {
			e.printStackTrace();
		}

	}

}