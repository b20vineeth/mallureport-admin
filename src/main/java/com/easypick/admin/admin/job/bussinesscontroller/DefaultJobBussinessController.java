package com.easypick.admin.admin.job.bussinesscontroller;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.job.persistence.CommonDao;
import com.easypick.admin.vo.JobVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class DefaultJobBussinessController implements DefaultJobControllerInterface {

	@Autowired
	CommonDao dao;

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getSession() {
		Session session;

		try {
			session = sessionFactory.openSession();

		} catch (SessionException se) {
			session = sessionFactory.openSession();
		}

		return session;
	}

	@Override
	public ResponseVo saveJob(JobVo vo, UserSetupVo userSetupVo) throws BussinessException {
	 	ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setUserSetupVo(userSetupVo);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveJob(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getJobList(JobVo vo, UserSetupVo userSetupVo) throws BussinessException {
		 
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setUserSetupVo(userSetupVo);
			watchdog.setCmpcode("CM");
			responseVo = dao.viewJob(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo findJob(JobVo vo, UserSetupVo userSetupVo) throws BussinessException {
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setUserSetupVo(userSetupVo);
			watchdog.setCmpcode("CM");
			responseVo = dao.findJob(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public JobVo findJob(String jobCode) throws BussinessException {
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			JobVo vo = dao.findJob(watchdog, jobCode);
			this.tx.commit();
			return vo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

 

}
