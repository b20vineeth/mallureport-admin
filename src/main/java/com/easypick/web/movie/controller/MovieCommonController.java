package com.easypick.web.movie.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.category.bussinesscontroller.CategoryBussinessInterface;
import com.easypick.web.language.bussinesscontroller.LanguageBussinessInterface;
import com.easypick.web.movie.bussinesscontroller.MovieBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class MovieCommonController {

	Gson gson;
	 
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
	@Autowired
	private HttpSession httpSession;  
	@Autowired
	protected LanguageBussinessInterface languageController;
	
	@Autowired
	protected MovieBussinessInterface movieController;

	@Autowired
	protected CategoryBussinessInterface categoryController;
	

	@RequestMapping(value = "/admin.movie.create")
	@ResponseBody
	public ModelAndView movieCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			LanguageVo vo = new LanguageVo();
			response = languageController.getLanguageList(vo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}

			return new ModelAndView("Admin/user/CreateMovie");
		}
	}

	@RequestMapping(value = "/admin.movie.save", method = RequestMethod.POST)
	@ResponseBody
	public String movieSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			MovieVo vo = gson.fromJson(data, MovieVo.class);
			try {
				ResponseVo response = movieController.saveMovie(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.movie.updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public String enablePriority(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			MovieVo vo = gson.fromJson(data, MovieVo.class);
			try {
				ResponseVo response = movieController.enablePriority(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	@RequestMapping(value = "/admin.movie.updateMovieStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateMovieStatus(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			MovieVo vo = gson.fromJson(data, MovieVo.class);
			try {
				ResponseVo response = movieController.updateMovieStatus(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	
	

	@RequestMapping(value = "/admin.movie.editReview", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView categoryView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "movieId", required = false) Integer movieId,
			@RequestBody @RequestParam(value = "movieName", required = false) String movieName,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			MovieReviewVo vo = new MovieReviewVo();
			vo.setMovieId(movieId);
			vo.setMovieName(movieName);
			ResponseVo response = new ResponseVo();
			try {
				response = movieController.getMovieReview(vo);
			} catch (BussinessException e) {
				response.setObject(vo);
			}
			modelMap.put("response", response);
			return new ModelAndView("Admin/user/CreateMovieReview");
		}
	}

	@RequestMapping(value = "/admin.movie.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView movieView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "movieName", required = false) String movieName,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "cast", required = false) String cast,
			@RequestBody @RequestParam(value = "language", required = false) Integer language,
			@RequestBody @RequestParam(value = "releasefrom", required = false) String releasefrom,
			@RequestBody @RequestParam(value = "releaseTo", required = false) String releaseTo,
			@RequestBody @RequestParam(value = "catId", required = false) String catId,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			MovieVo vo = new MovieVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setMovieName(movieName);
			vo.setTag(tag);
			vo.setCast(cast);
			vo.setLang(language);
			vo.setReleasefrom(releasefrom);
			vo.setReleaseTo(releaseTo);
			try {
				if (Objects.nonNull(catId) && catId.trim().length() > 0)
					vo.setCatId(Integer.parseInt(catId));
			} catch (Exception e) {

			}

			ResponseVo response = new ResponseVo();
			try {
				response = movieController.getMovieList(vo);
				LanguageVo languageVo = new LanguageVo();

				ResponseVo responseVo = languageController.getLanguageList(languageVo);

				modelMap.put("movie_name", movieName);
				modelMap.put("movie_tag", tag);
				modelMap.put("movie_cast", cast);

				modelMap.put("movie_language", language);
				modelMap.put("movie_releasefrom", releasefrom);
				modelMap.put("movie_releaseTo", releaseTo);
				modelMap.put("page", page);

				if (Objects.nonNull(responseVo.getObjectList())) {
					modelMap.put("language", responseVo);

				}
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");
					modelMap.put("response", response);

				} else
					modelMap.put("result", "false");
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewMovie");
		}
	}

	@RequestMapping(value = "/admin.movie.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView movieEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "catId", required = false) String catId,
			@RequestBody @RequestParam(value = "movieId", required = false) String movieId) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			MovieVo vo = new MovieVo();
			if (Objects.nonNull(catId) && catId.trim().length() > 0)
				vo.setCatId(Integer.parseInt(catId));
			if (Objects.nonNull(movieId) && movieId.trim().length() > 0)
				vo.setMovieId(Integer.parseInt(movieId));

			ResponseVo response = new ResponseVo();
			try {
				response = movieController.getMovie(vo);

			} catch (BussinessException e) {
				MovieVo movie = new MovieVo();
				movie.setCatId(Integer.parseInt(catId));
				response.setObject(movie);
				modelMap.put("response", response);
			}
			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}

			LanguageVo languageVo = new LanguageVo();
			response = languageController.getLanguageList(languageVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}
			CategoryVo catVo = new CategoryVo();
			catVo.setCatType("1");
			catVo.setPerPage(200);
			response = new ResponseVo();
			response = categoryController.getCategoryList(catVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseMovie", response);

			}
			return new ModelAndView("Admin/user/CreateMovie");
		}
	}
	
	
	@RequestMapping(value = "/admin.movie.getStatus", method = RequestMethod.POST)
	@ResponseBody
	public String getStatus(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			CinemaGalleryVo cinemaGallery = gson.fromJson(data, CinemaGalleryVo.class);
			MovieReviewVo movieReviewVo = gson.fromJson(data, MovieReviewVo.class);
			try {
				ResponseVo response = movieController.galleryStatus(cinemaGallery);
				List<CinemaGalleryVo> cinemaGalleryVo = (List<CinemaGalleryVo>) response.getObjectList();
				String cinema=gson.toJson(cinemaGalleryVo).toString();
				
				response = movieController.reviewStatus(movieReviewVo);
				List<MovieVo> movievos = (List<MovieVo>) response.getObjectList();
				String review= gson.toJson(movievos).toString();
				Map<String,String> status=new HashMap<>();
				status.put("review", review);
				status.put("cinema", cinema);
				return gson.toJson(status).toString();
				
				
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	


	@RequestMapping(value = "/admin.movie.saveMovieReview", method = RequestMethod.POST)
	@ResponseBody
	public String saveMovieReview(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			MovieReviewVo vo = gson.fromJson(data, MovieReviewVo.class);
			try {
				ResponseVo response = movieController.saveMovieReview(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.movie.reviewStatus", method = RequestMethod.POST)
	@ResponseBody
	public String reviewStatus(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			MovieReviewVo vo = gson.fromJson(data, MovieReviewVo.class);
			try {
				ResponseVo response = movieController.reviewStatus(vo);
				List<MovieVo> movievos = (List<MovieVo>) response.getObjectList();
				return gson.toJson(movievos).toString();
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	
	
	
	
	
}
