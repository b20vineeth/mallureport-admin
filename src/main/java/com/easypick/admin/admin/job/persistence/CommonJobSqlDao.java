package com.easypick.admin.admin.job.persistence;
 
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.HomePageMap;
import com.easypick.admin.entity.Job;
import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.admin.vo.JobVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class CommonJobSqlDao implements CommonDao {
	 
	@Override
	public void saveHomegeData(WatchDogVo watchdog, HomePageSetupVo homePageSetupVo) {
		StringBuilder sql = new StringBuilder();
		HomePageMap homapge = null;
		try {
			sql.append("from HomePageMap map where map.status='Y' and map.type=:type ");
			homapge = (HomePageMap) watchdog.getSessionString().createQuery(sql.toString())
					.setParameter("type", homePageSetupVo.getType()).getSingleResult();
			homapge.setUpdateon(new Date());
			homapge.setTagidx(0);
			homapge.setData(homePageSetupVo.getData());
		} catch (Exception e) {
			homapge = new HomePageMap();
			homapge.setUpdateon(new Date());
			homapge.setTagidx(0);
			homapge.setType(homePageSetupVo.getType());
			homapge.setData(homePageSetupVo.getData());
		}
		watchdog.getSessionString().saveOrUpdate(homapge);
		
	}

	@Override
	public ResponseVo saveJob(WatchDogVo watchdog, JobVo vo) {

		StringBuilder sql = new StringBuilder();
		Job job = null;
		try {
			sql.append("from Job job where job.status='Y' and job.jobid=:jobid ");
			job = (Job) watchdog.getSessionString().createQuery(sql.toString())
					.setParameter("jobid", vo.getJobId()).getSingleResult();
		 } catch (Exception e) {
			job = new Job();
			
		 }
		job.setUpdateon(new Date());
		job.setJobCode(getJobCode(vo.getJobfile()));
		job.setTagidx(0);
		job.setJobType(vo.getType());
		job.setCrobExpression(vo.getCronExp());
		job.setJobFile(vo.getJobfile());
		job.setJobName(vo.getJobName());
		job.setStatus("Y");
		watchdog.getSessionString().saveOrUpdate(job);
		vo.setJobCode(job.getJobCode());
		ResponseVo response=new ResponseVo();
		response.setObject(vo);
		
		return response;
	}

	private String getJobCode(String jobfile) {
		String[] files=jobfile.split("\\.");
		String jobCode=null;
		for(String file:files)
		{
			jobCode=file;
		}
		 return jobCode;
	}

	@Override
	public ResponseVo viewJob(WatchDogVo watchdog, JobVo vo) {
		StringBuilder sql = new StringBuilder();
		String selectQuery="SELECT t.NEXT_FIRE_TIME,t.PREV_FIRE_TIME,job.jobid,job.job_name,job.job_code,job.cron_exp,job.job_type ";
		sql.append( " FROM job job left join qrtz_triggers t on job.job_code=t.JOB_NAME where job.status='Y' ");
		if (Objects.nonNull(vo.getJobName())) {
			sql.append(" and job.job_name like '%" + vo.getJobName() + "%'");
		}
		if (Objects.nonNull(vo.getType())) {
			sql.append(" and job.job_type='" + vo.getType() + "'");
		}

		 
		Page page1 = new Page();
		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		page1.setCurrentPage(page);
		if (page > 0)
			page = page - 1;
		
		page = page * page1.getPerPage();
		sql.append(" order by job.job_name ");
		Query query = watchdog.getSessionString().createSQLQuery(selectQuery+sql.toString());
		List<Object[]> items  = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		query = watchdog.getSessionString().createSQLQuery("Select 1 " + sql.toString());
		List<Object> movieCount = query.getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Job.formateJobVos(items));
		page1.setTotalResult(movieCount.size());
		page1.updateTotalPage();
		responseVo.setPage(page1);
		return responseVo;
	}

	@Override
	public ResponseVo findJob(WatchDogVo watchdog, JobVo vo) {
	 
		StringBuilder sql = new StringBuilder();
		sql.append("from Job job where job.status='Y' and job.jobid=:job ");
		Job job = (Job) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("job", vo.getJobId()).getSingleResult();
		ResponseVo responseVo=new ResponseVo();
		responseVo.setObject(Job.populate(job));
		return responseVo;
	}

	@Override
	public JobVo findJob(WatchDogVo watchdog, String jobCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Job job where job.status='Y' and job.jobCode=:jobCode ");
		Job job = (Job) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("jobCode",jobCode).getSingleResult();
		return Job.populate(job) ; 
		 
	}

}
