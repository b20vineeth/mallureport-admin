package com.easypick.web.defaults.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.easypick.admin.admin.job.service.JobService;
import com.easypick.framework.utility.commonUtility.ServerResponse;
import com.easypick.framework.utility.commonUtility.ServerResponseCode;
import com.google.gson.Gson; 
@Controller
public class JobController {

	Gson gson;
 
	@Autowired
	@Lazy
	JobService jobService;
	 
	
	@RequestMapping(value = "/job.create",method = RequestMethod.GET)
	@ResponseBody
	public String homePage(ModelMap modelMap,
			@RequestBody @RequestParam("job") String jobName) {
		boolean status=false;
		if(!jobService.isJobWithNamePresent(jobName)){
		Date jobScheduleTime=new Date();
		String cronExpression="*/5 * * * * ?";
		status = jobService.scheduleCronJob(jobName, com.easypick.admin.admin.job.CronJob.class, jobScheduleTime, cronExpression);
		}
		return status+"";
   }
	@RequestMapping(value = "/job.delete",method = RequestMethod.GET)
	@ResponseBody
	public String stopJob(ModelMap modelMap,
			@RequestBody @RequestParam("job") String jobName) {
		 
		if(jobService.isJobWithNamePresent(jobName)){
			boolean isJobRunning = jobService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = jobService.deleteJob(jobName);
				if(status){
					return  " true";
				}else{
					return "false";
				}
			}else{
				return "false";
				}
		}else{
			return "Job doesn't exist";
			 
		}
   }
	@RequestMapping(value = "/job.list",method = RequestMethod.GET)
	@ResponseBody
	public String getAllJobs(){
		 List<Map<String, Object>> list = jobService.getAllJobs();
		 gson=new Gson();
		return gson.toJson(getServerResponse(ServerResponseCode.SUCCESS, list)).toString();
	}
	
	
	
	@RequestMapping(value = "/job.startJobNow",method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(!jobService.isJobRunning(jobName)){
				boolean status = jobService.startJobNow(jobName);

				if(status){
					//Success
					return getServerResponse(ServerResponseCode.SUCCESS, true);

				}else{
					//Server error
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			}else{
				//Job already running
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}
	
	public ServerResponse getServerResponse(int responseCode, Object data){
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse; 
	}
}
