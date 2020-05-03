package com.easypick.admin.admin.job.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easypick.admin.admin.job.bussinesscontroller.DefaultJobControllerInterface;
import com.easypick.admin.admin.job.config.JobInterface;
import com.easypick.admin.admin.job.service.JobService;
import com.easypick.admin.vo.JobVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.commonUtility.ServerResponse;
import com.easypick.framework.utility.commonUtility.ServerResponseCode;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;

@Controller
public class JobController {

	Gson gson;

	@Autowired
	@Lazy
	JobService jobService;
	@Autowired
	private Map<String, JobInterface> job;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private DefaultJobControllerInterface jobController;

	@RequestMapping(value = "/admin.job.view")
	@ResponseBody
	public ModelAndView listJobs(ModelMap modelMap,
			@RequestBody @RequestParam(value = "jobName", required = false) String jobName,
			@RequestBody @RequestParam(value = "type", required = false) String type,
			@RequestBody @RequestParam(value = "page", required = false) String page) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			JobVo vo = new JobVo();
			if (Objects.nonNull(jobName) && jobName.trim().length() > 0) {
				vo.setJobName(jobName);
				modelMap.put("jobName", jobName);
			}
			if (Objects.nonNull(type) && type.trim().length() > 0) {
				vo.setType(type);
				modelMap.put("type", type);
			}

			UserSetupVo userSetupVo = (UserSetupVo) httpSession.getAttribute("authdata");
			ResponseVo response = jobController.getJobList(vo, userSetupVo);
			modelMap.put("response", response);
			return new ModelAndView("Admin/job/ViewJob");
		}

	}

	@RequestMapping(value = "/admin.job.create")
	@ResponseBody
	public ModelAndView createJobs(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo vo = new ResponseVo();
			modelMap.put("response", vo);
			return new ModelAndView("Admin/job/CreateJob");
		}

	}

	@RequestMapping(value = "/admin.job.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView editJobs(ModelMap modelMap,
			@RequestBody @RequestParam(value = "jobId", required = true) Integer jobId) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			UserSetupVo userSetupVo = (UserSetupVo) httpSession.getAttribute("authdata");
			JobVo vo = new JobVo();
			vo.setJobId(jobId);
			ResponseVo response = jobController.findJob(vo, userSetupVo);
			modelMap.put("response", response);
			return new ModelAndView("Admin/job/CreateJob");
		}

	}

	@RequestMapping(value = "/admin.job.save", method = RequestMethod.POST)
	@ResponseBody
	public String jobSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");

		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			UserSetupVo userSetupVo = (UserSetupVo) httpSession.getAttribute("authdata");
			gson = new Gson();
			JobVo vo = gson.fromJson(data, JobVo.class);
			try {
				ResponseVo response=jobController.saveJob(vo, userSetupVo);
				vo=(JobVo) response.getObject();
				if (jobService.isJobWithNamePresent(vo.getJobCode())) {
					boolean isJobRunning = jobService.isJobRunning(vo.getJobCode());

					if (!isJobRunning) {
						boolean status = jobService.deleteJob(vo.getJobCode());

					}
				}

				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.job.executeNow", method = RequestMethod.GET)
	@ResponseBody
	public String test(ModelMap modelMap, @RequestBody @RequestParam("jobCode") String data) {

		try {
			job.get(StringUitity.firstCharToLowerCase(data)).invoke();
			return "T";
		} catch (Exception e) {
			return "F";
		}

	}

	@RequestMapping(value = "/admin.job.activate", method = RequestMethod.POST)
	@ResponseBody
	public String activate(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException, ClassNotFoundException {

		String username = (String) httpSession.getAttribute("authKey");

		if (Objects.isNull(username)) {
			return "F";

		} else {
			boolean status = activateJob(data);
			if (status)
				return "T";
			else
				return "F";
		}

	}

	private boolean activateJob(String data) throws BussinessException, ClassNotFoundException {
		boolean status = false;
		gson = new Gson();
		JobVo vo = gson.fromJson(data, JobVo.class);
		if (!jobService.isJobWithNamePresent(vo.getJobCode())) {
			Date jobScheduleTime = new Date();

			JobVo jobVo = jobController.findJob(vo.getJobCode());
			String cronExpression = jobVo.getCronExp();
			Class filePath = Class.forName(jobVo.getJobfile());
			status = jobService.scheduleCronJob(vo.getJobCode(), filePath, jobScheduleTime, cronExpression);
		} else {
			if (!jobService.isJobRunning(vo.getJobCode())) {
				status = jobService.startJobNow(vo.getJobCode());
			}

		}
		return status;
	}

	@RequestMapping(value = "/admin.job.deactivate", method = RequestMethod.POST)
	@ResponseBody
	public String stopJob(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException, ClassNotFoundException {

		String username = (String) httpSession.getAttribute("authKey");

		if (Objects.isNull(username)) {
			return "F";

		} else {
			gson = new Gson();
			JobVo vo = gson.fromJson(data, JobVo.class);
			if (jobService.isJobWithNamePresent(vo.getJobCode())) {
				boolean isJobRunning = jobService.isJobRunning(vo.getJobCode());

				if (!isJobRunning) {
					boolean status = jobService.deleteJob(vo.getJobCode());
					if (status) {
						return " true";
					} else {
						return "false";
					}
				} else {
					return "false";
				}
			} else {
				return "Job doesn't exist";

			}
		}
	}

	@RequestMapping(value = "/admin.job.stop", method = RequestMethod.GET)
	@ResponseBody
	public String stop(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException, ClassNotFoundException {

		String username = (String) httpSession.getAttribute("authKey");

		if (Objects.isNull(username)) {
			return "F";

		} else {
			gson = new Gson();
			JobVo vo = new JobVo();
			vo.setJobCode(data);
			if (jobService.isJobWithNamePresent(vo.getJobCode())) {
				boolean isJobRunning = jobService.isJobRunning(vo.getJobCode());

				if (!isJobRunning) {
					boolean status = jobService.deleteJob(vo.getJobCode());
					if (status) {
						return " true";
					} else {
						return "false";
					}
				} else {
					return "false";
				}
			} else {
				return "Job doesn't exist";

			}
		}
	}

	@RequestMapping(value = "/admin.job.list", method = RequestMethod.GET)
	@ResponseBody
	public String getAllJobs() {
		List<Map<String, Object>> list = jobService.getAllJobs();
		gson = new Gson();
		return gson.toJson(list).toString();
	}

	@RequestMapping(value = "/admin.job.startJobNow", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse startJobNow(@RequestParam("jobCode") String jobCode) {
		if (jobService.isJobWithNamePresent(jobCode)) {

			if (!jobService.isJobRunning(jobCode)) {
				boolean status = jobService.startJobNow(jobCode);

				if (status) {
					return getServerResponse(ServerResponseCode.SUCCESS, true);

				} else {
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			} else {
				// Job already running
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		} else {
			// Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	public ServerResponse getServerResponse(int responseCode, Object data) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse;
	}
}
