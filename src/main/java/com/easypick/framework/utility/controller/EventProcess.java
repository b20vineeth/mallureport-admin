package com.easypick.framework.utility.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.EventImpl;
import com.easypick.web.events.vo.KeyData;

@Service
public class EventProcess {

	@Autowired
	protected ApplicationEventPublisher publisher;

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;

	@Autowired
	private Event event;

	@Autowired
	private Map<String, EventImpl> eventImpl;

	@EventListener
	public void trigger(ResponseVo response) {

		try {
			Map<String, List<KeyData>> events = event.getEvent();
			List<KeyData> eventList = events.get(response.getEvent());
			Collections.sort(eventList,Collections.reverseOrder()); 
			for (KeyData keyData : eventList) {
				this.session = this.getSession();
				this.tx = this.session.beginTransaction();
				WatchDogVo watchdog = new WatchDogVo();
				watchdog.setSessionString(this.session);
				watchdog.setCmpcode("CM");
				eventImpl.get(keyData.getEvent()).execute(watchdog, response);
				this.tx.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
