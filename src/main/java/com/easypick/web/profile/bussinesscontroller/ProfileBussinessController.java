package com.easypick.web.profile.bussinesscontroller;
 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.easypick.admin.user.persistence.CommonAttributeDao;
import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.profile.persistence.ProfileDao;
import com.google.gson.Gson; 
@Repository
public class ProfileBussinessController implements ProfileBussinessInterface {
	
	
	@Autowired
	private ProfileDao dao;
	
	@Autowired
	protected ApplicationEventPublisher publisher;
	
	@Autowired
	private CommonAttributeDao commondao;
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
	public ResponseVo profileSave(ProfileVo vo) throws BussinessException {
	 	ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.profileSave(watchdog, vo);
			this.tx.commit();
			if(Objects.nonNull(responseVo))
			{	
				responseVo.setEvent("com.admin.saveProfile");
				publisher.publishEvent(responseVo);
			}
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getProfileList(ProfileVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getProfileList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getProfile(ProfileVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getProfile(watchdog, vo);
			if(Objects.nonNull(responseVo.getObject()))
			{
				ProfileVo profile=(ProfileVo) responseVo.getObject();
				List<DataVo> filmVo = commondao.getFilmAutoComplete(watchdog, profile.getFilms());
				Gson gson=new Gson();
				Map<String, String> stringMap=new HashMap<>();
				stringMap.put("movie", gson.toJson(filmVo).toString());
				responseVo.setStringMap(stringMap);
			}
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}
	 

}
