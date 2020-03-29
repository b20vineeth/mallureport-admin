package com.easypick.admin.user.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Category;
import com.easypick.admin.entity.CategoryType;
import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Language;
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.MovieReview;
import com.easypick.admin.entity.Profile;
import com.easypick.admin.entity.Video;
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CommonAttributeSqlDao implements CommonAttributeDao {

	@Override
	public ResponseVo saveLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {
		Language language = Language.populateLangauageVo(vo);
		watchdog.getSessionString().saveOrUpdate(language);
		vo.setLangId(language.getId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getLanguageList(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y' ");
		if (Objects.nonNull(vo.getLangCode())) {
			sql.append(" and lang.langCode='" + vo.getLangCode() + "'");
		}
		if (Objects.nonNull(vo.getLangName())) {
			sql.append(" and lang.langName='" + vo.getLangName() + "'");
		}
		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by lang.langName ");
		List<Language> langVos = watchdog.getSessionString().createQuery(sql.toString()).setFirstResult(page)
				.setMaxResults(25).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Language.formateVos(langVos));
		return responseVo;
	}

	@Override
	public ResponseVo getLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y' and lang.id=:id ");
		ResponseVo responseVo = new ResponseVo();
		Language lang = (Language) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getLangId()).getSingleResult();
		responseVo.setObject(Language.formateVo(lang));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo saveCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {

		CategoryType categoryType = CategoryType.populateCategoryTypeVo(vo);
		watchdog.getSessionString().saveOrUpdate(categoryType);
		vo.setCatTypeId(categoryType.getCatTypeId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getCategoryTypeList(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from CategoryType type where type.status='Y' ");
		if (Objects.nonNull(vo.getCatTypeCode())) {
			sql.append(" and type.catTypeCode='" + vo.getCatTypeCode() + "'");
		}
		if (Objects.nonNull(vo.getCatTypeName())) {
			sql.append(" and type.catTypeName='" + vo.getCatTypeName() + "'");
		}
		Integer page = 0;
		Integer maxPage = 75;
		if (vo.isPagination()) {
			page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
			maxPage = 25;
			if (page > 0) {
				page = page - 1;
				page = page * 25;

			}
		}

		sql.append(" order by type.catTypeName ");
		List<CategoryType> categoryTypeVos = watchdog.getSessionString().createQuery(sql.toString())
				.setFirstResult(page).setMaxResults(maxPage).getResultList();

		ResponseVo responseVo = new ResponseVo();

		responseVo.setObjectList(CategoryType.formateCategoryTypeVos(categoryTypeVos));

		return responseVo;
	}

	@Override
	public ResponseVo getCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from CategoryType type where type.status='Y' and type.catTypeId=:id ");
		ResponseVo responseVo = new ResponseVo();
		CategoryType categoryType = (CategoryType) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getCatTypeId()).getSingleResult();
		responseVo.setObject(CategoryType.formateCategoryTypeVo(categoryType));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getCategory(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from Category cat where cat.status='Y' and cat.catId=:id ");
		ResponseVo responseVo = new ResponseVo();
		Category category = (Category) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getCatId()).getSingleResult();
		responseVo.setObject(Category.formateCategoryVo(category));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getCategoryList(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		Page page1 = new Page();
		sql.append("from Category cat where cat.status='Y' ");
		if (Objects.nonNull(vo.getCatCode()) && vo.getCatCode().trim().length() > 0) {
			sql.append(" and cat.categoryCode like '%" + vo.getCatCode() + "%'");
		}
		if (Objects.nonNull(vo.getCatName()) && vo.getCatName().trim().length() > 0) {
			sql.append(" and cat.categoryName like '%" + vo.getCatName() + "%'");
		}
		if (Objects.nonNull(vo.getCatType()) && vo.getCatType().trim().length() > 0) {
			sql.append(" and cat.categoryType.catTypeId='" + vo.getCatType() + "'");
		}

		if (Objects.nonNull(vo.getLang()) && vo.getLang() > 0) {
			sql.append(" and cat.language.id='" + vo.getLang() + "'");
		}
		if (Objects.nonNull(vo.getGender()) && vo.getGender().trim().length() > 0 && !"0".equals(vo.getGender())) {
			sql.append(" and cat.gender='" + vo.getGender() + "'");
		}

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * page1.getPerPage();
		sql.append(" order by cat.categoryName ");
		if (vo.getPerPage() != 0) {
			page1.setPerPage(vo.getPerPage());
		}

		List<Category> categoryVos = watchdog.getSessionString().createQuery(sql.toString()).setFirstResult(page)
				.setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Category.formateCategoryVos(categoryVos));

		Query query = watchdog.getSessionString().createQuery("select count(*) " + sql);
		Long count = (Long) query.uniqueResult();

		page1.setTotalResult(Integer.parseInt(count.toString()));
		page1.setCurrentPage(page);
		page1.updateTotalPage();
		responseVo.setPage(page1);

		return responseVo;
	}

	@Override
	public ResponseVo saveCategory(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		Category category = Category.populateCategoryVo(vo);
		watchdog.getSessionString().saveOrUpdate(category);
		vo.setCatId(category.getCatId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo saveMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException {

		Movie movie = Movie.populateMovieVo(vo);
		watchdog.getSessionString().saveOrUpdate(movie);
		vo.setMovieId(movie.getMovieId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getMovieList(WatchDogVo watchdog, MovieVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append(" from Movie movie where movie.status='Y' ");
		if (Objects.nonNull(vo.getMovieName())) {
			sql.append(" and movie.movieName like '%" + vo.getMovieName() + "%'");
		}
		if (Objects.nonNull(vo.getLang())) {
			sql.append(" and movie.lang.id='" + vo.getLang() + "'");
		}

		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0) {
			sql.append(" and movie.tag like '%" + vo.getTag() + "%'");
		}
		if (Objects.nonNull(vo.getCast()) && vo.getCast().trim().length() > 0) {
			sql.append(" and movie.cast like '%" + vo.getCast() + "%'");
		}
		if (Objects.nonNull(vo.getReleasefrom()) && vo.getReleasefrom().trim().length() > 0
				&& vo.getReleaseTo().trim().length() > 0 && Objects.nonNull(vo.getReleaseTo())) {
			sql.append(" and movie.relaseDate  between :releasefrom and :releaseTo ");
		}

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by movie.movieName ");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getReleasefrom()) && vo.getReleasefrom().trim().length() > 0
				&& vo.getReleaseTo().trim().length() > 0 && Objects.nonNull(vo.getReleaseTo())) {
			try {
				query.setParameter("releasefrom", new SimpleDateFormat("dd-MM-yyyy").parse(vo.getReleasefrom()));
				query.setParameter("releaseTo", new SimpleDateFormat("dd-MM-yyyy").parse(vo.getReleaseTo()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		Page page1 = new Page();
		List<Movie> movie = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Movie.formateMovieVos(movie));
		return responseVo;
	}

	@Override
	public ResponseVo getMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Movie movie where movie.status='Y'");
		if (Objects.nonNull(vo.getMovieId()))
			sql.append("and movie.movieId=:movieId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getMovieId()))
			query.setParameter("movieId", vo.getMovieId());

		Movie movie = (Movie) query.getSingleResult();
		responseVo.setObject(Movie.formateMovieVo(movie));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo profileSave(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {
		Profile profile = Profile.populateProfileVo(vo);
		watchdog.getSessionString().saveOrUpdate(profile);
		vo.setProfileId(profile.getProfileId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getProfileList(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append(" from Profile profile where profile.status='Y' ");
		if (Objects.nonNull(vo.getCatName())) {
			sql.append(" and profile.profileName like '%" + vo.getProfileName() + "%'");
		}
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0) {
			sql.append(" and profile.tag like '%" + vo.getTag() + "%'");
		}
		if (Objects.nonNull(vo.getFilms()) && vo.getFilms().trim().length() > 0) {
			sql.append(" and profile.films like '%" + vo.getFilms() + "%'");
		}
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0) {
			sql.append(" and profile.tag like '%" + vo.getTag() + "%'");
		}
		if (Objects.nonNull(vo.getGender()) && vo.getGender().trim().length() > 0) {
			sql.append(" and profile.gender = '" + vo.getGender() + "'");
		}

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by profile.profileName");

		Query query = watchdog.getSessionString().createQuery(sql.toString());

		Page page1 = new Page();
		List<Profile> profileVos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Profile.formateProfileVos(profileVos));
		return responseVo;
	}

	@Override
	public ResponseVo getProfile(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Profile profile where profile.status='Y'");

		if (Objects.nonNull(vo.getProfileId()))
			sql.append("and profile.profileId=:profileId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getProfileId()))
			query.setParameter("profileId", vo.getProfileId());

		Profile profile = (Profile) query.getSingleResult();
		responseVo.setObject(Profile.formateProfileVo(profile));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo saveVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		Video video = Video.populateVideoVo(vo);
		watchdog.getSessionString().saveOrUpdate(video);
		vo.setVideoId(video.getVideoId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getVideoList(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Video video where video.status='Y'");
		if (Objects.nonNull(vo.getVideoId()))
			sql.append("and video.videoId=:videoId");
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0)
			sql.append("and video.tag like '%" + vo.getTag() + "%'");

		if (Objects.nonNull(vo.getTitle()) && vo.getTitle().trim().length() > 0)
			sql.append("and video.title like '%" + vo.getTitle() + "%'");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getVideoId()))
			query.setParameter("videoId", vo.getVideoId());

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by video.videoId desc ");

		Page page1 = new Page();
		List<Video> videos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		responseVo.setObjectList(Video.formateVideoVos(videos));

		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Video video where video.status='Y'");
		if (Objects.nonNull(vo.getVideoId()))
			sql.append("and video.videoId=:videoId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getVideoId()))
			query.setParameter("videoId", vo.getVideoId());

		Video video = (Video) query.getSingleResult();
		responseVo.setObject(Video.formateVideoVo(video));
		responseVo.setResponse(true);
		return responseVo;
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

	private Object updateGallery(Gallery gallery, GalleryVo vo) {
		gallery.setDescription(vo.getDescription());
		gallery.setShortDesc(vo.getShortDesc());
		gallery.setTag(vo.getTag());
		gallery.setTitle(vo.getTitle());
		return gallery;
	}

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
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by gallery.galleryId desc ");

		Page page1 = new Page();
		List<Gallery> galleryVos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		responseVo.setObjectList(Gallery.formateGalleryVos(galleryVos));

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
		responseVo.setObject(Gallery.formateGalleryVo(gallery));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo updateThumbnail(String fileName, int id, WatchDogVo watchdog) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Gallery gallery where gallery.status='Y' and gallery.galleryId=:galleryId");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("galleryId", id);

		Gallery gallery = (Gallery) query.getSingleResult();
		gallery.setThumbnail(fileName);
		watchdog.getSessionString().saveOrUpdate(gallery);
		return null;
	}

	@Override
	public ResponseVo enablePriority(MovieVo vo, WatchDogVo watchdog) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Movie movie where movie.status='Y' and movie.movieId=:movie");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movie", vo.getMovieId());

		Movie movie = (Movie) query.getSingleResult();
		if (Objects.nonNull(vo.getPriorityFlag()) && vo.getPriorityFlag().trim().length() > 0) {
			movie.setPriorityFlag(vo.getPriorityFlag());
		}
		if (Objects.nonNull(vo.getRecommenedFlag()) && vo.getRecommenedFlag().trim().length() > 0) {
			movie.setRecommendedFlag(vo.getRecommenedFlag());
		}
		watchdog.getSessionString().saveOrUpdate(movie);
		return null;
	}

	@Override
	public ResponseVo getMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		ResponseVo responseVo = new ResponseVo();
		sql.append("from MovieReview movie where movie.movieCode=:movie");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movie", vo.getMovieCode());

		MovieReview movie = (MovieReview) query.getSingleResult();
		responseVo.setObject(movie);
		return responseVo;
	}

	@Override
	public ResponseVo saveMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
