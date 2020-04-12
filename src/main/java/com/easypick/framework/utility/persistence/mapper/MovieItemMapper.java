package com.easypick.framework.utility.persistence.mapper;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.easypick.admin.vo.MovieVo; 

public class MovieItemMapper {
	MovieVo movie;
	 

	public MovieVo map(Object[] items) {
		movie=new MovieVo();
		movie.setThumbnail(items[0].toString());
		movie.setShortDesc(items[1].toString());
		movie.setMovieCode(items[2].toString());
		movie.setMovieName(items[3].toString());
		movie.setReleaseDate(items[4].toString());
		movie.setMovieRate(items[5].toString());
		movie.setLanguageName(items[6].toString());
		return movie;
	}


	public MovieVo listItem(Object[] items) {
		 
		movie=new MovieVo();
		movie.setMovieCode(items[0].toString());
		movie.setMovieName(items[1].toString());
		movie.setCast(items[2].toString());
		movie.setLanguageName(items[3].toString());
		movie.setThumbnail(items[4].toString());
		movie.setThumbnail2(items[5].toString());
		 
		try {
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(items[7].toString());
		movie.setReleaseDate(new SimpleDateFormat("dd MMMM, yyyy").format(date1));
		} catch (ParseException e) {
			 e.printStackTrace();
			e.printStackTrace();
		}   
		movie.setMovieRate(Objects.nonNull(items[8])?items[8].toString():"0");
		
		movie.setCertificate(Objects.nonNull(items[9])?items[9].toString():" ");
		movie.setMovieType(Objects.nonNull(items[10])?items[10].toString():" ");
	    return movie;
	}

}
