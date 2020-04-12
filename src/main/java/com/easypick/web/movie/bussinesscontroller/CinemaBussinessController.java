package com.easypick.web.movie.bussinesscontroller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.admin.admin.persistence.Dao; 
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class CinemaBussinessController implements ControllerBI {

	@Autowired
	private Map<String, Dao> dao;
	private Integer pageNumber = null;
	private WatchDogVo watchdog;
	List<String> list = Arrays.asList("about", "cast", "gallery", "video");

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

			default:
				throw new BussinessException("404");

			}

		} catch (Exception e) {
			throw new BussinessException("404");
		}

	}

	private ResponseVo parameter(String string, String string2, String string3) throws BussinessException {
		ResponseVo vo = new ResponseVo();
		Map<String, String> input = new HashMap<>();
		if (string3.contains("page-")) {
			try {
				String[] page = string3.split("-");
				pageNumber = Integer.parseInt(page[1]);
				input.put("page", pageNumber.toString());
			} catch (Exception e) {
				throw new BussinessException("404");
			}
		} else {
			if (list.contains(string3)) {
				input.put("subtag", string3);
			} else {
				throw new BussinessException("404");
			}
		}

		if (string2.contains("tag_")) {
			string2 = string2.replace("tag_", "");
			input.put("tag", string2);
			if (Objects.isNull(input.get("page")))
				input.put("page", "1");
			this.watchdog.setInput(input);
			vo = dao.get("cinemaByTagDao").execute(this.watchdog, vo);
			vo.setScreenMode("movie/ViewMovieList");
			return vo;
		} else {
			if (Objects.isNull(input.get("page"))) {
				input.put("cinemaName", string2);

				input.put("page", "1");
				this.watchdog.setInput(input);
				vo = dao.get("cinemaByNameDao").execute(this.watchdog, vo);
				vo.setScreenMode("movie/ViewMovie");
				return vo;
			} else
				throw new BussinessException("404");
		}

	}

	private ResponseVo parameter(String string, String string2) throws BussinessException {
		if (string2.contains("page-")) {
			throw new BussinessException("404");
		} else if (string2.contains("tag_")) {
			string2 = string2.replace("tag_", "");
			return getDetailsByTag(string2);
		} else {
			return getDetailsByName(string2);
		}

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
		MovieVo movieVo=new MovieVo();
		movieVo.setPriorityFlag("Y"); 
		Date date=new Date(); 
		movieVo.setFilterType("R"); 
		vo.setObject(movieVo);
		vo = dao.get("cinemaByDateDao").execute(this.watchdog, vo);
		movieVo.setFilterType("U"); 
		vo = dao.get("cinemaByDateDao").execute(this.watchdog, vo);
		vo = dao.get("cinemaByRecommendedDao").execute(this.watchdog, vo);
		vo = dao.get("cinemaByLatestReviewDao").execute(this.watchdog, vo);
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
