package com.easypick.defaults.admin.user.bussinesscontroller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.user.persistence.CommonAttributeDao;
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.GenderVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CommonAttributeController implements ControllerInterface {
	@Autowired
	private CommonAttributeDao dao;

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
	public ResponseVo saveLanguage(LanguageVo vo) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveLanguage(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}

	}

	@Override
	public ResponseVo getLanguageList(LanguageVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getLanguageList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getLanguage(LanguageVo vo) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getLanguage(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo saveCategoryType(CategoryTypeVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveCategoryType(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getCategoryTypeList(CategoryTypeVo vo) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getCategoryTypeList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getCategoryType(CategoryTypeVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getCategoryType(watchdog, vo);
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
	public ResponseVo saveMovie(MovieVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveMovie(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getMovieList(MovieVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
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
	public ResponseVo getMovie(MovieVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getMovie(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo profileSave(ProfileVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.profileSave(watchdog, vo);
			this.tx.commit();
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
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo saveVideoVo(VideoVo vo) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveVideo(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getVideoList(VideoVo vo) throws BussinessException {

		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getVideoList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}

	}

	@Override
	public ResponseVo getVideo(VideoVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getVideo(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo saveGalleryVo(GalleryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.saveGalleryVo(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getGalleryList(GalleryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getGalleryList(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getGallery(GalleryVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getGallery(watchdog, vo);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getGenderList() {
		ResponseVo responseVo = new ResponseVo();
		List<GenderVo> gendervos = new ArrayList<>();

		GenderVo vo = new GenderVo();
		vo.setGenderCode("M");
		vo.setGenderName("Male");
		gendervos.add(vo);

		vo = new GenderVo();
		vo.setGenderCode("F");
		vo.setGenderName("Female");
		gendervos.add(vo);

		vo = new GenderVo();
		vo.setGenderCode("O");
		vo.setGenderName("Other");
		gendervos.add(vo);

		responseVo.setObjectList(gendervos);

		return responseVo;
	}

	@Override
	public ResponseVo updateThumbnail(String fileName, int id) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.updateThumbnail(fileName, id,watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo enablePriority(MovieVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.enablePriority(vo,watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}

	@Override
	public ResponseVo getMovieReview(MovieReviewVo vo) throws BussinessException {
		WatchDogVo dog = new WatchDogVo();
		ResponseVo responseVo = new ResponseVo();
		try {
			this.session = this.getSession();
			this.tx = this.session.beginTransaction();
			WatchDogVo watchdog = new WatchDogVo();
			watchdog.setSessionString(this.session);
			watchdog.setCmpcode("CM");
			responseVo = dao.getMovieReview(vo,watchdog);
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
			responseVo = dao.saveMovieReview(vo,watchdog);
			this.tx.commit();
			return responseVo;
		} catch (Exception e) {
			throw new BussinessException("404");
		} finally {
			this.session.close();
		}
	}
	

}
