package com.easypick.admin.vo;
 
import com.easypick.framework.utility.vo.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobVo implements AbstractVo {
	
	private Integer jobId;
	private String jobName;  
	private String jobfile;
	private String type;
	private String cronExp; 
	private String status;
	private String page="0";  
	private String jobExec;
	private String jobCode;
	private String jobStatus="N"; 
	/**
	 * Job Not yet Start "N"
	 * Job Active Status  "A"
	 * Job Stop Status  "D"
	 */
	private String  nextFireTime;
	private String previousFireTime;
	
	
	public String getPreviousFireTime() {
		return previousFireTime;
	}
	public void setPreviousFireTime(String previousFireTime) {
		this.previousFireTime = previousFireTime;
	}
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getJobCode() {
		return jobCode;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobExec() {
		return jobExec;
	}
	public void setJobExec(String jobExec) {
		this.jobExec = jobExec;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobfile() {
		return jobfile;
	}
	public void setJobfile(String jobfile) {
		this.jobfile = jobfile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	 
}
