package com.easypick.admin.entity;
 
import java.util.Date; 

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
 

@Entity
@Table(name = "movrev", uniqueConstraints = { @UniqueConstraint(columnNames = { "movcod" }) })
public class MovieReview {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mov_re_id")
	private Integer movieReviewId;

	@Column(name = "title")
	private String title;

	@Column(name = "movcod")
	private String movieCode;
 

	@Column(name = "movrevdat", columnDefinition = "DATE")
	private Date reviewDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movid", nullable = false)
	private Movie movie;


	@Column(name = "decription")
	private String decription;

	@Column(name = "short_desc")
	private String shortDesc; 

	@Column(name = "tag")
	private String tag; 
	
	@Column(name = "status", length=1)
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

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
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

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
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

	
	 

}
