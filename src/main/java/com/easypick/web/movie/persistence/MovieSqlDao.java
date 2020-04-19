package com.easypick.web.movie.persistence;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
 
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.MovieReview;
import com.easypick.admin.vo.CinemaGalleryVo; 
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class MovieSqlDao implements MovieDao {

	@Override
	public ResponseVo saveMovie(WatchDogVo watchdog, MovieVo vo) throws BussinessException {

		Movie movie = Movie.populateMovieVo(vo);
		watchdog.getSessionString().saveOrUpdate(movie);
		vo.setMovieId(movie.getMovieId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setId(movie.getMovieId());
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
		Page page1 = new Page();
		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * page1.getPerPage();
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

		page1.setCurrentPage(page);
		List<Movie> movie = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		query = watchdog.getSessionString().createQuery("Select 1 " + sql.toString());
		List<Movie> movieCount = query.getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Movie.formateMovieVos(movie));
		page1.setTotalResult(movieCount.size());
		page1.updateTotalPage();
		responseVo.setPage(page1);
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
	public ResponseVo getMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		ResponseVo responseVo = new ResponseVo();
		sql.append("from MovieReview movie where movie.movie.movieId=:movie");

		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movie", vo.getMovieId());

		MovieReview movie = (MovieReview) query.getSingleResult();
		responseVo.setObject(MovieReview.formateMovieReviewVo(movie));
		return responseVo;
	}

	@Override
	public ResponseVo updateMovieStatus(WatchDogVo watchdog, MovieVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from Movie movie where movie.movieId=:movieId");
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		query.setParameter("movieId", vo.getMovieId());
		ResponseVo responseVo = new ResponseVo();
		Movie movie = (Movie) query.getSingleResult();
		if (Objects.nonNull(movie)) {
			movie.setMovieRate(Integer.parseInt(vo.getMovieRate()));
		}
		return responseVo;
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
	public ResponseVo reviewStatus(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT movid,status FROM movrev WHERE movid in (");
		sql.append(vo.getMovieIds() + ")");

		ResponseVo responseVo = new ResponseVo();
		SQLQuery query = watchdog.getSessionString().createSQLQuery(sql.toString());

		List<Object[]> objects = (List<Object[]>) ((org.hibernate.Query) query).list();
		List<MovieVo> movies = new ArrayList<>();
		if (objects.size() > 0) {
			for (Object[] items : objects) {
				try {

					MovieVo movievo = new MovieVo();
					movievo.setMovieId(Integer.parseInt(items[0].toString()));
					movies.add(movievo);

				} catch (Exception e) {

				}

			}
			responseVo.setObjectList(movies);
		}

		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo galleryStatus(CinemaGalleryVo vo, WatchDogVo watchdog) throws BussinessException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT movid,thumb1,thumb2,thumb3 FROM cinegallery WHERE movid in (");
		sql.append(vo.getMovieIds() + ")");

		ResponseVo responseVo = new ResponseVo();
		SQLQuery query = watchdog.getSessionString().createSQLQuery(sql.toString());

		List<Object[]> object = (List<Object[]>) ((org.hibernate.Query) query).list();
		List<CinemaGalleryVo> movies = new ArrayList<>();
		if (object.size() > 0) {
			for (Object[] items : object) {
				try {

					CinemaGalleryVo movievo = new CinemaGalleryVo();
					movievo.setMovid(items[0].toString());
					if (Objects.nonNull(items[1]))
						movievo.setThumb1(items[1].toString());
					if (Objects.nonNull(items[2]))
						movievo.setThumb2(items[2].toString());
					if (Objects.nonNull(items[3]))
						movievo.setThumb3(items[3].toString());
					movies.add(movievo);

				} catch (Exception e) {

				}

			}
			responseVo.setObjectList(movies);
		}

		responseVo.setResponse(true);
		return responseVo;
	}


	@Override
	public ResponseVo saveMovieReview(MovieReviewVo vo, WatchDogVo watchdog) throws BussinessException {

		MovieReview movieReview = MovieReview.populateMovieReviewVo(vo);
		watchdog.getSessionString().saveOrUpdate(movieReview);
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	  

}
