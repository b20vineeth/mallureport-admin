package com.easypick.admin.entity;
 
 
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.easypick.admin.vo.CinemaGalleryVo; 
  

@Entity
@Table(name = "cinegallery")
public class CineGallery {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cineid")
	private Integer cineId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movid", nullable = false)
	private Movie movie;

	@Column(name = "thumb1")
	private String thumb1;
	
	@Column(name = "thumb2")
	private String thumb2;
	
	@Column(name = "thumb3")
	private String thumb3;
	
	@Column(name = "thumb4")
	private String thumb4;

	public Integer getCineId() {
		return cineId;
	}

	public void setCineId(Integer cineId) {
		this.cineId = cineId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getThumb1() {
		return thumb1;
	}

	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}

	public String getThumb2() {
		return thumb2;
	}

	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}

	public String getThumb3() {
		return thumb3;
	}

	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}

	public String getThumb4() {
		return thumb4;
	}

	public void setThumb4(String thumb4) {
		this.thumb4 = thumb4;
	}

	public static CineGallery populateCinemaGalleryVo(CinemaGalleryVo vo) {
		CineGallery gallery=new CineGallery();
		Movie movie=new Movie();
		movie.setMovieId(Integer.parseInt(vo.getMovid()));
		gallery.setMovie(movie);
		
		if(Objects.nonNull(vo.getThumb1()))
			gallery.setThumb1(vo.getThumb1());
		if(Objects.nonNull(vo.getThumb2()))
			gallery.setThumb2(vo.getThumb2());
		
		if(Objects.nonNull(vo.getThumb3()))
			gallery.setThumb3(vo.getThumb3());
		 
		return gallery;
	}

	public static CineGallery populateCinemaGallery(CineGallery gallery,CinemaGalleryVo vo) {

		if(Objects.nonNull(vo.getThumb1()))
			gallery.setThumb1(vo.getThumb1());
		if(Objects.nonNull(vo.getThumb2()))
			gallery.setThumb2(vo.getThumb2());
		
		if(Objects.nonNull(vo.getThumb3()))
			gallery.setThumb3(vo.getThumb3());
		
		return gallery; 
	}
	
	
	

}
