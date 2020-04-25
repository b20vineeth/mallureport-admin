package com.easypick.admin.admin.job.controller;

import java.util.Map; 

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.admin.admin.job.bussinesscontroller.JobBI; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Component
public class CommonJobController implements JobControllerInterface {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	@Autowired
	private Map<String, JobBI> controller;
	
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
			// session = sessionFactory.getCurrentSession();
		} catch (SessionException se) {
			session = sessionFactory.openSession();
		}

		return session;
	}
 
	@Override
	public void execute(String component) throws BussinessException {
		 
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			responseVo = controller.get(component).execute(watchdog);
			this.tx.commit();
			 
 
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}
}
