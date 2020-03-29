package com.easypick.framework.utility.controller;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class ActionController implements ActionControllerInterface {
	@Autowired
	private Map<String, ControllerBI> controller;

	public static final String HOME_PAGE = "home";
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
			// session = sessionFactory.getCurrentSession();
		} catch (SessionException se) {
			session = sessionFactory.openSession();
		}

		return session;
	}

	@Override
	public ResponseVo performAction(Map<String, String> input) throws BussinessException {
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			if (input.get("param1").contains("default")) {
				watchdog.setKeyLength(input.size() - 1);
			} else {
				watchdog.setKeyLength(input.size());
			}
			watchdog.setInput(input);
			watchdog.setCmpcode("CM");
			ResponseVo vo = null;
			StringBuilder controllerUrl = new StringBuilder();
			controllerUrl.append(input.get("param1").toLowerCase());
			controllerUrl.append("BussinessController");
			vo = controller.get(controllerUrl.toString()).execute(watchdog);
			this.tx.commit();
			return vo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

}
