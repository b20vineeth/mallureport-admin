package com.easypick.admin.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.web.events.vo.MovieDataVo;

@Entity
@Table(name = "movie", uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_code" }) })
public class Movie {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieId")
	private Integer movieId;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "movie_code")
	private String movieCode;

	@Column(name = "cin_cast")
	private String cast;

	@Column(name = "cin_rel_dat", columnDefinition = "DATE")
	private Date relaseDate;

	@Column(name = "rate")
	private Integer movieRate;

	@Column(name = "description",columnDefinition="TEXT")
	private String description;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "mov_type",length=9)
	private String movieType;
	
	@Column(name = "certificate",length=2)
	private String certificate;

	@Column(name = "tag")
	private String tag;

	@Column(name = "priority_flag", length=1)
	private String priorityFlag ="N";
	
	
	@Column(name = "recommended_flag", length=1)
	private String recommendedFlag ="N";
	
	@Column(name = "status", length=1)
	private String status = "Y";

	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cin_lang", nullable = true)
	private Language lang;

	 

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getRecommendedFlag() {
		return recommendedFlag;
	}

	public void setRecommendedFlag(String recommendedFlag) {
		this.recommendedFlag = recommendedFlag;
	}

	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	 

	public Integer getMovieRate() {
		return movieRate;
	}

	public void setMovieRate(Integer movieRate) {
		this.movieRate = movieRate;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public Date getRelaseDate() {
		return relaseDate;
	}

	public void setRelaseDate(Date relaseDate) {
		this.relaseDate = relaseDate;
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

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public static Object formateMovieVo(Movie movie) {
		MovieVo vo = new MovieVo();
		vo.setMovieId(movie.getMovieId());
		vo.setCast(movie.getCast().replace("#", ""));
		vo.setDescription(movie.getDescription()); 
		vo.setShortDesc(movie.getShortDesc());
		vo.setMovieType(movie.getMovieType());
		vo.setCertificate(movie.getCertificate());
		vo.setMovieRate(Objects.nonNull(movie.getMovieRate())?movie.getMovieRate().toString():"0"); 
		vo.setReleaseDate(new SimpleDateFormat("dd/MM/yyyy").format(movie.getRelaseDate()).toString());
		vo.setTag(movie.getTag().replace("#", ""));
		vo.setMovieCode(movie.getMovieCode());
		vo.setMovieName(movie.getMovieName());
		vo.setLang(movie.getLang().getId());
		vo.setLanguageName(movie.getLang().getLangName());
		return vo;
	}
	
	
	public static Object formateMovieDataVo(Movie movie) {
		MovieDataVo vo = new MovieDataVo();
		vo.setMovieId(movie.getMovieId());
		vo.setCast(movie.getCast());
		vo.setDescription(movie.getDescription()); 
		vo.setShortDesc(movie.getShortDesc());
		vo.setMovieType(movie.getMovieType());
		vo.setCertificate(movie.getCertificate());
		vo.setMovieRate(Objects.nonNull(movie.getMovieRate())?movie.getMovieRate().toString():"0"); 
		vo.setReleaseDate(new SimpleDateFormat("dd/MM/yyyy").format(movie.getRelaseDate()).toString());
		vo.setTag(movie.getTag());
		vo.setMovieCode(movie.getMovieCode());
		vo.setMovieName(movie.getMovieName());
		vo.setLang(movie.getLang().getId());
		vo.setLanguageName(movie.getLang().getLangName());
		return vo;
	}
	

	public static List<? extends AbstractVo> formateMovieVos(List<Movie> movies) {
		List<MovieVo> movieVos = new ArrayList<>();
		MovieVo vo = null;
		for (Movie movie : movies) {
			vo = new MovieVo();
			vo.setMovieId(movie.getMovieId());
			vo.setShortDesc(movie.getShortDesc());
			vo.setTag(movie.getTag());
			vo.setCast(movie.getCast());
			vo.setMovieRate(Objects.nonNull(movie.getMovieRate())?movie.getMovieRate().toString():"0");
			vo.setMovieCode(movie.getMovieCode());
			vo.setMovieType(movie.getMovieType());
			vo.setMovieName(movie.getMovieName());
			vo.setLang(movie.getLang().getId());
			vo.setLanguageName(movie.getLang().getLangName());
			vo.setPriorityFlag(movie.getPriorityFlag());
			vo.setReleaseDate(movie.getRelaseDate().toString().replace("00:00:00.0", ""));
			vo.setRecommenedFlag(movie.getRecommendedFlag());
			movieVos.add(vo);
		}

		return movieVos;
	}

	public static Movie populateMovieVo(MovieVo vo) {
		Movie movie = new Movie();
		movie.setCast(StringUitity.getTag(vo.getCast()));
		movie.setDescription(vo.getDescription());
		Date date1;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(vo.getReleaseDate());
			movie.setRelaseDate(date1);
		} catch (ParseException e) {
			movie.setRelaseDate(new Date());
		}
		movie.setShortDesc(vo.getShortDesc());
		movie.setStatus(vo.getStatus()); 
		movie.setMovieCode(vo.getMovieCode());
		movie.setMovieName(vo.getMovieName());
		movie.setTag(StringUitity.getTag(vo.getTag())); 
		if(Objects.nonNull(vo.getCertificate()))
			movie.setCertificate(vo.getCertificate());
		
		if(Objects.nonNull(vo.getMovieType()))
			movie.setMovieType(vo.getMovieType());
		
		Language lang = new Language();
		if (Objects.nonNull(vo.getLang())) {
			lang.setId(vo.getLang());
		}

		if (vo.getMovieId() != 0)
			movie.setMovieId(vo.getMovieId());
		movie.setLang(lang);
		return movie;
	}



	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

}
