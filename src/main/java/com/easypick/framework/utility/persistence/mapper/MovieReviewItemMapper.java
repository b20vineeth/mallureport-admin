package com.easypick.framework.utility.persistence.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.easypick.admin.vo.MovieReviewVo;

public class MovieReviewItemMapper {
	MovieReviewVo  movieReview ;
	public MovieReviewVo listItem(Object[] items) throws ParseException {
		movieReview=new MovieReviewVo();
		movieReview.setShortDesc(items[0].toString());
		movieReview.setTitle(items[1].toString());
		movieReview.setThumbnail(items[2].toString());
		movieReview.setMovieRate(Objects.nonNull(items[3])?Integer.parseInt(items[3].toString()):0);
		String certificate=Objects.nonNull(items[4])?items[4].toString():"";
		String movieType=Objects.nonNull(items[5])?items[5].toString():"";
		movieReview.setMovieCode(movieType+" ( "+certificate+" ) ");
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(items[6].toString());
		movieReview.setCreatedDate(new SimpleDateFormat("dd MMMM, yyyy").format(date1));
		
		return movieReview;
	}

}
