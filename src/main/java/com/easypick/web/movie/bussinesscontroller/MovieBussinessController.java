package com.easypick.web.movie.bussinesscontroller;

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
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.movie.persistence.MovieDao;
import com.google.gson.Gson;

@Repository
public class MovieBussinessController implements MovieBussinessInterface {

	@Autowired
	private MovieDao dao;

	@Autowired
	private CommonAttributeDao commondao;

	@Autowired
	protected ApplicationEventPublisher publisher;

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
	public ResponseVo saveMovie(MovieVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.saveMovie(watchdog, vo);
			this.tx.commit();
			if (Objects.nonNull(responseVo)) {
				responseVo.setEvent("com.admin.saveMovie");
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
	public ResponseVo getMovieList(MovieVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.getMovieList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getMovie(MovieVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.getMovie(watchdog, vo);
			if (Objects.nonNull(responseVo.getObject())) {
				MovieVo movie = (MovieVo) responseVo.getObject();
				Gson gson = new Gson();
				Map<String, String> stringMap = new HashMap<>();
				List<DataVo> castVo = commondao.getCastAutoComplete(watchdog, movie.getCast());
				if (Objects.nonNull(castVo))
					stringMap.put("Cast", gson.toJson(castVo).toString());
				List<DataVo> language = commondao.getLanguageAutoComplete(watchdog, movie.getLang());
				if (Objects.nonNull(language))
					stringMap.put("language", gson.toJson(language).toString());
				List<DataVo> movieType = commondao.getMovieTypeAutoComplete(watchdog, movie.getMovieType());
				if (Objects.nonNull(movieType))
					stringMap.put("MovieType", gson.toJson(movieType).toString());
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

	@Override
	public ResponseVo getMovieReview(MovieReviewVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.getMovieReview(vo, watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo updateMovieStatus(MovieVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.updateMovieStatus(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo enablePriority(MovieVo vo, UserSetupVo user) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CIN");
			watchdog.setUserSetupVo(user);
			responseVo = dao.enablePriority(vo, watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo galleryStatus(CinemaGalleryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.galleryStatus(vo, watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo reviewStatus(MovieReviewVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.reviewStatus(vo, watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo saveMovieReview(MovieReviewVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveMovieReview(vo, watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

}
