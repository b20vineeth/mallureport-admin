package com.easypick.web.gallery.persistence;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
 
import com.easypick.admin.entity.CineGallery;
import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Movie; 
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.google.gson.Gson; 

@Repository
public class GallerySqlDao implements GalleryDao {

	@Override
	public ResponseVo updateThumbnail(String fileName, int id, WatchDogVo watchdog) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Gallery gallery where gallery.status='Y' and gallery.galleryId=:galleryId");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("galleryId", id);

		Gallery gallery = (Gallery) query.getSingleResult();
		gallery.setThumbnail2(fileName);
		watchdog.getSessionString().saveOrUpdate(gallery);
		return null;
	}
	@Override
	public ResponseVo saveGalleryVo(WatchDogVo watchdog, GalleryVo vo) throws BussinessException {
		if (Objects.nonNull(vo.getGalleryId()) && vo.getGalleryId() > 0) {

			StringBuilder sql = new StringBuilder();

			sql.append("from Gallery gallery where gallery.status='Y'");
			if (Objects.nonNull(vo.getGalleryId()))
				sql.append("and gallery.galleryId=:galleryId");

			ResponseVo responseVo = new ResponseVo();
			Query query = watchdog.getSessionString().createQuery(sql.toString());
			if (Objects.nonNull(vo.getGalleryId()))
				query.setParameter("galleryId", vo.getGalleryId());

			Gallery gallery = (Gallery) query.getSingleResult();
			watchdog.getSessionString().saveOrUpdate(updateGallery(gallery, vo));

		} else {
			List<Gallery> gallerys = Gallery.populateAttribute(vo);
			for (Gallery gallery : gallerys) {
				watchdog.getSessionString().saveOrUpdate(gallery);
				watchdog.getSessionString().flush();
				watchdog.getSessionString().clear();
			}
		}
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}


	@Override
	public ResponseVo getGalleryList(WatchDogVo watchdog, GalleryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Gallery gallery where gallery.status='Y'");
		if (Objects.nonNull(vo.getGalleryId()))
			sql.append("and gallery.galleryId=:galleryId");
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0)
			sql.append("and gallery.tag like '%" + vo.getTag() + "%'");

		if (Objects.nonNull(vo.getTitle()) && vo.getTitle().trim().length() > 0)
			sql.append("and gallery.title like '%" + vo.getTitle() + "%'");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getGalleryId()))
			query.setParameter("galleryId", vo.getGalleryId());

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		Page page1 = new Page();

		page1.setCurrentPage(page);
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by gallery.galleryId desc ");

		List<Gallery> galleryVos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		responseVo.setObjectList(Gallery.formateGalleryVos(galleryVos));
		query = watchdog.getSessionString().createQuery("Select 1 " + sql.toString());
		List<Movie> movieCount = query.getResultList();
		page1.setTotalResult(movieCount.size());
		page1.updateTotalPage();
		responseVo.setPage(page1);
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getGallery(WatchDogVo watchdog, GalleryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Gallery gallery where gallery.status='Y'");
		if (Objects.nonNull(vo.getGalleryId()))
			sql.append("and gallery.galleryId=:galleryId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getGalleryId()))
			query.setParameter("galleryId", vo.getGalleryId());

		Gallery gallery = (Gallery) query.getSingleResult();
		GalleryVo galleryVo = (GalleryVo) Gallery.formateGalleryVo(gallery);
		populateMovieTag(watchdog, gallery, galleryVo);
		populateProfileTag(watchdog, gallery, galleryVo);
		responseVo.setObject(galleryVo);
		responseVo.setResponse(true);
		return responseVo;
	}
	@Override
	public ResponseVo saveCineGallery(CinemaGalleryVo vo, WatchDogVo watchdog) throws BussinessException {

		StringBuilder sql = new StringBuilder();

		sql.append("from CineGallery gallery where gallery.movie.movieId=:movieId");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", Integer.parseInt(vo.getMovid()));
		CineGallery cineGallery = null;
		try {
			cineGallery = (CineGallery) query.getSingleResult();
			cineGallery = CineGallery.populateCinemaGallery(cineGallery, vo);
		} catch (Exception e) {
			cineGallery = CineGallery.populateCinemaGalleryVo(vo);
		}
		watchdog.getSessionString().saveOrUpdate(cineGallery);
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}


	private Object updateGallery(Gallery gallery, GalleryVo vo) {
		gallery.setDescription(vo.getDescription());
		gallery.setShortDesc(vo.getShortDesc());
		gallery.setTag(vo.getTag());
		gallery.setTitle(vo.getTitle());
		String[] movieTag = vo.getMovie().split(",");
		String movietags = "";
		for (String tag : movieTag) {
			movietags = "#" + tag + "#," + movietags;
		}
		String[] profileTag = vo.getProfile().split(",");
		String profileTags = "";
		for (String tag : profileTag) {
			profileTags = "#" + tag + "#," + profileTags;
		}
		gallery.setMovieTag(movietags);
		gallery.setProfileTag(profileTags);
		return gallery;
	}
	
	private void populateMovieTag(WatchDogVo watchdog, Gallery gallery, GalleryVo galleryVo) {
		StringBuilder sql;
		Query query;
		if (Objects.nonNull(gallery)) {
			String tag = getmovieTag(gallery);
			if (tag.trim().length() > 0) {
				galleryVo.setMovieTag(tag);
				sql = new StringBuilder();
				sql.append("select movie.movieId ,movie.movieName from Movie movie where movie.status='Y' ");

				sql.append(" and movie.movieId in(" + tag + ")");

				query = watchdog.getSessionString().createQuery(sql.toString());

				List<Object[]> object = query.setFirstResult(0).setMaxResults(10).getResultList();
				List<DataVo> vos = new ArrayList<>();
				DataVo dataVo = null;

				if (object.size() > 0) {
					DataVo.populateDataVo(object, vos);

					Gson gson = new Gson();
					if (Objects.nonNull(vos))
						galleryVo.setMovie(gson.toJson(vos).toString());

				}
			}
		}
	}

	private void populateProfileTag(WatchDogVo watchdog, Gallery gallery, GalleryVo galleryVo) {
		StringBuilder sql;
		Query query;
		if (Objects.nonNull(gallery)) {
			String tag = getprofileTag(gallery);
			if (tag.trim().length() > 0) {
				galleryVo.setProfileTag(tag);
				sql = new StringBuilder();
				sql.append(
						"select profile.profileId ,profile.profileName from Profile profile where profile.status='Y' ");

				sql.append(" and profile.profileId in(" + tag + ")");

				query = watchdog.getSessionString().createQuery(sql.toString());

				List<Object[]> object = query.setFirstResult(0).setMaxResults(10).getResultList();
				List<DataVo> vos = new ArrayList<>();
				DataVo dataVo = null;

				if (object.size() > 0) {
					DataVo.populateDataVo(object, vos);

					Gson gson = new Gson();
					if (Objects.nonNull(vos))
						galleryVo.setProfile(gson.toJson(vos).toString());

				}
			}
		}

	}
	private String getprofileTag(Gallery gallery) {
		String tag = "";
		if (Objects.nonNull(gallery.getProfileTag())) {
			tag = gallery.getProfileTag();
			tag = tag.replace("#", "").replaceAll(",$", "");
		}
		return tag;
	}


	
	private String getmovieTag(Gallery gallery) {
		String tag = "";
		if (Objects.nonNull(gallery.getMovieTag())) {
			tag = gallery.getMovieTag();
			tag = tag.replace("#", "").replaceAll(",$", "");
		}
		return tag;
	}

}
