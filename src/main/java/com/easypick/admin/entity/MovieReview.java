package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.vo.AbstractVo;

@Entity
@Table(name = "movrev")
public class MovieReview {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mov_re_id")
	private Integer movieReviewId;

	@Column(name = "title")
	private String title;

	@Column(name = "movrevdat", columnDefinition = "DATE")
	private Date reviewDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movid", nullable = false)
	private Movie movie;

	@Column(name = "description")
	private String description;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "tag")
	private String tag;

	@Column(name = "status", length = 1)
	private String status = "Y";

	public Integer getMovieReviewId() {
		return movieReviewId;
	}

	public void setMovieReviewId(Integer movieReviewId) {
		this.movieReviewId = movieReviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static MovieReviewVo formateMovieReviewVo(MovieReview movie) {
		MovieReviewVo vo = new MovieReviewVo();
		vo.setMovieId(movie.getMovie().getMovieId());
		vo.setMovieName(movie.getMovie().getMovieName());
		vo.setTitle(movie.getTitle());
		vo.setShortDesc(movie.getShortDesc());
		vo.setDescription(movie.getDescription());
		vo.setTag(movie.getTag());
		vo.setMovieReviewId(movie.getMovieReviewId());
		return vo;
	}

	public static MovieReview populateMovieReviewVo(MovieReviewVo vo) {
		MovieReview review = new MovieReview();

		if (vo.getMovieReviewId() != 0)
			review.setMovieReviewId(vo.getMovieReviewId());
		review.setReviewDate(new Date());
		review.setShortDesc(vo.getShortDesc());
		review.setDescription(vo.getDescription());
		review.setStatus("Y");
		review.setTag(vo.getTag());
		review.setTitle(vo.getTitle());
		Movie movie = new Movie();
		movie.setMovieId(vo.getMovieId());
		review.setMovie(movie);
		return review;
	}

	public static List<? extends AbstractVo> formateMovieReviews(List<MovieReview> movieVos) {
		List<MovieReviewVo> movieReviewVo = new ArrayList<>();
		MovieReviewVo vo = null;
		for (MovieReview movie : movieVos) {
			vo = new MovieReviewVo();
			vo.setShortDesc(movie.getShortDesc());
			vo.setTag(movie.getTag());
			vo.setTitle(movie.getTitle());
			vo.setTag(movie.getTag()); 
			movieReviewVo.add(vo);
		}
		return movieReviewVo;
	}

}
