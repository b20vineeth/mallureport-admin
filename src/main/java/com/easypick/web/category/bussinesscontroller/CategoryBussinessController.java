package com.easypick.web.category.bussinesscontroller;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.CategoryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.category.persistence.CategoryDao;  
@Repository
public class CategoryBussinessController implements CategoryBussinessInterface {
	
	
	@Autowired
	private CategoryDao dao;
	
	
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
	public ResponseVo getCategoryList(CategoryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getCategoryList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo saveCategory(CategoryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveCategory(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getCategory(CategoryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getCategory(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}
 

}
