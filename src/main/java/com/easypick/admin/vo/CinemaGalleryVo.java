package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class CinemaGalleryVo implements AbstractVo {

	private Integer cineId;
	private String movid;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String  movieIds;
	
	
	
	public String getMovieIds() {
		return movieIds;
	}

	public void setMovieIds(String movieIds) {
		this.movieIds = movieIds;
	}

	public Integer getCineId() {
		return cineId;
	}

	public void setCineId(Integer cineId) {
		this.cineId = cineId;
	}

	 
	public String getMovid() {
		return movid;
	}

	public void setMovid(String movid) {
		this.movid = movid;
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

}
