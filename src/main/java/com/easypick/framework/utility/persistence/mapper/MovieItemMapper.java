package com.easypick.framework.utility.persistence.mapper;
 
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

}
