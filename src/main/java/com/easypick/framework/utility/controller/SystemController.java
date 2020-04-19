package com.easypick.framework.utility.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.SystemDao;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class SystemController {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	@Autowired
	private SystemDao dao;
	
	public List<String> getSystemParameter(String Key) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		List<String>  onetime=null;
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			onetime = dao.getSystemParameter(watchdog, Key);
			 this.tx.commit(); 
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
		return onetime;
	}

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
}
