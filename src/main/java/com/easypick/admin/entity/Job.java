package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.JobVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;

@Entity
@Table(name = "job", uniqueConstraints = { @UniqueConstraint(columnNames = { "job_code" }) })
public class Job {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobid")
	private Integer jobid;

	@Column(name = "job_name")
	private String jobName;

	@Column(name = "job_code")
	private String jobCode;

	@Column(name = "cron_exp")
	private String crobExpression;
	
	@Column(name = "job_file")
	private String jobFile;
	
	@Column(name = "job_type")
	private String jobType;
	
	@Column(name = "last_executed_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastExec;
	
	@Column(name = "next_fire_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextFireTime;
	
	
	@Column(name = "status", length = 1)
	private String status;
	
 
	@Column(name = "updated_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;
	
	@Column(name = "tagidx" ,length=1)
	private Integer tagidx=0;

	
	
	
	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getCrobExpression() {
		return crobExpression;
	}

	public void setCrobExpression(String crobExpression) {
		this.crobExpression = crobExpression;
	}

	public String getJobFile() {
		return jobFile;
	}

	public void setJobFile(String jobFile) {
		this.jobFile = jobFile;
	}

	public Date getLastExec() {
		return lastExec;
	}

	public void setLastExec(Date lastExec) {
		this.lastExec = lastExec;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateon() {
		return updateon;
	}

	public void setUpdateon(Date updateon) {
		this.updateon = updateon;
	}

	public Integer getTagidx() {
		return tagidx;
	}

	public void setTagidx(Integer tagidx) {
		this.tagidx = tagidx;
	}

	public static List<? extends AbstractVo> formateJobVos(List<Object[]> items) {
		List<JobVo> jobVos = new ArrayList<>();
		JobVo vo = null;
		 for (Object[] job : items) {
			vo = new JobVo();
			vo.setNextFireTime(StringUitity.convertNumberToDate(Objects.nonNull(job[0])?job[0].toString():""));
			vo.setPreviousFireTime(StringUitity.convertNumberToDate(Objects.nonNull(job[1])?job[1].toString():""));
			vo.setJobId(Integer.parseInt(job[2].toString()));
			vo.setJobName(job[3].toString());
			vo.setJobCode(job[4].toString());
			vo.setCronExp(job[5].toString());
			vo.setType(job[6].toString());
			vo.setJobStatus(Objects.nonNull(job[1])?"A":"D");
			jobVos.add(vo);
		}
		return jobVos;
	}

	public static JobVo populate(Job job) {
		JobVo vo=new  JobVo();
		vo.setJobfile(job.getJobFile());
		vo.setJobName(job.getJobName());
		vo.setType(job.getJobType());
		vo.setCronExp(job.getCrobExpression());
		vo.setJobCode(job.getJobCode());
		vo.setJobId(job.getJobid());
		return vo;
	}

	
	
	
	 
}
