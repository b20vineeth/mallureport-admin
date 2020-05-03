package com.easypick.web.movie.bussinesscontroller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.persistence.Dao;
import com.easypick.admin.vo.DataVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaBussinessController implements ControllerBI {

	@Autowired
	private Map<String, Dao> dao;
	private Integer pageNumber = null;
	private WatchDogVo watchdog;
	List<String> list = Arrays.asList("about", "cast", "gallery", "video");
	List<String> parameter2 = Arrays.asList("running-movies", "upcomming-movies", "popular-film", "movie-review");

	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {

		ResponseVo vo = null;
		try {
			this.watchdog = watchdog;
			switch (watchdog.getInput().size()) {

			case 2:
				return parameter(watchdog.getInput().get("key1"));
			case 3:
				return parameter(watchdog.getInput().get("key1"), watchdog.getInput().get("key2"));
			case 4:
				return parameter(watchdog.getInput().get("key1"), watchdog.getInput().get("key2"),

						watchdog.getInput().get("key3"));
			case 5:
				return parameter(watchdog.getInput().get("key1"), watchdog.getInput().get("key2"),

						watchdog.getInput().get("key3"),watchdog.getInput().get("key4"));

			default:
				throw new BussinessException("404");

			}

		} catch (Exception e) {
			throw new BussinessException("404");
		}

	}

	private ResponseVo parameter(String string, String string2, String string3, String string4) throws BussinessException {
		
		Map<String, String> input = new HashMap<>(); 
		if (string4.contains("page-")) {
			 String pageNumber=string4.replace("page-", "").trim();
			 input.put("submenu", string2);
			 input.put("language", string3);
			 input.put("page", pageNumber);
			 return getDetailsBySubMenuPagination(input);
		}
		else 
		 { 
			 throw new BussinessException("404");
			 
		 }
		
		 
	}

	private ResponseVo parameter(String string, String string2, String string3) throws BussinessException { 
		Map<String, String> input = new HashMap<>(); 
		if (string3.contains("page-")) {
			 String pageNumber=string3.replace("page-", "").trim();
			 input.put("submenu", string2);
			 input.put("page", pageNumber);
			 return getDetailsBySubMenuPagination(input);
		}
		 else if (parameter2.contains(string2)) {
			 input.put("submenu", string2);
			 input.put("language", string3);
			 input.put("page", "1");
			return getDetailsBySubMenuPagination(input);
		}
		 else 
		 { 
			 throw new BussinessException("404");
			 
		 }
		
		 
	}

	 

	private ResponseVo getDetailsBySubMenuPagination(Map<String, String> input) throws BussinessException {
		ResponseVo vo = null;
		this.watchdog.setInput(input);
		vo = dao.get("cinemaBySubMenuDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/ViewMovieList");
		return vo;
	}

	private ResponseVo parameter(String string, String string2) throws BussinessException {
		if (string2.contains("page-")) {
			throw new BussinessException("404");
		}  
		else if (parameter2.contains(string2)) {
			return getDetailsBySubMenu(string2);
		}
		else
		{
			return getMovieDetails(string2);
		} 


	}

	private ResponseVo getMovieDetails(String string2) throws BussinessException {
		ResponseVo vo = null;
		Map<String, String> input = new HashMap<>();
		input.put("movie", string2); 
		this.watchdog.setInput(input);
		vo = dao.get("cinemaDetailsDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/ViewCinema");
		return vo;
	}

	private ResponseVo getDetailsBySubMenu(String string2) throws BussinessException {
		ResponseVo vo = null;
		Map<String, String> input = new HashMap<>();
		input.put("submenu", string2);
		input.put("page", "1");
		this.watchdog.setInput(input);
		vo = dao.get("cinemaBySubMenuDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/ViewMovieList");
		return vo;
	}

	private ResponseVo getDetailsByName(String cinemaName) throws BussinessException {

		ResponseVo vo = null;
		Map<String, String> input = new HashMap<>();
		input.put("cinemaName", cinemaName);
		input.put("page", "1");
		this.watchdog.setInput(input);
		vo = dao.get("cinemaByNameDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/ViewMovie");
		return vo;

	}

	private ResponseVo getDetailsByTag(String tag) throws BussinessException {

		ResponseVo vo = null;
		Map<String, String> input = new HashMap<>();
		input.put("tag", tag);
		input.put("page", Objects.nonNull(pageNumber) ? pageNumber.toString() : "1");
		input.put("subtag", "subTag");
		this.watchdog.setInput(input);
		vo = dao.get("cinemaByTagDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/ViewMovieList");
		return vo;
	}

	private ResponseVo parameter(String string) throws BussinessException {
		ResponseVo vo = new ResponseVo();
		DataVo datavo=new DataVo();
		datavo.setTag("CIN");
		vo.setObject(datavo);
		vo=dao.get("homePagePostSqlDao").execute(this.watchdog, vo);
		vo = dao.get("slideShowDao").execute(this.watchdog, vo);
		vo = dao.get("slideShowRelativePostDao").execute(this.watchdog, vo);
		vo.setScreenMode("movie/Movie");
		return vo;
	}

	private ResponseVo home() {
		System.out.print("home");
		return null;
	}

}
