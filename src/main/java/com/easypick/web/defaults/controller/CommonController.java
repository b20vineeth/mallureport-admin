package com.easypick.web.defaults.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.multipart.MultipartFile;
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.UploadFile;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.defaults.admin.user.bussinesscontroller.ControllerInterface;
import com.easypick.defaults.admin.user.bussinesscontroller.UserControllerInterface;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;
import com.sprhib.init.Initializer;
import com.sprhib.init.WebAppConfig;

@Controller
public class CommonController {

	Gson gson;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
	@Autowired
	private HttpSession httpSession;
	Map<String, String> privilege = null;
	@Autowired
	protected UserControllerInterface action;
	@Autowired
	protected ControllerInterface commonController;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView login(ModelMap modelMap) {
		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username))
			return new ModelAndView("Admin/Login");
		else {
			modelMap.put("redirectUrl", "admin");
			return new ModelAndView("Admin/Redirect");
		}

	}

	@RequestMapping(value = "/login.auth", method = RequestMethod.POST)
	@ResponseBody
	public String galleryList(ModelMap modelMap, @RequestBody @RequestParam("data") String data) {
		String output = "f";
		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			UserSetupVo vo = new UserSetupVo();
			try {
				gson = new Gson();
				vo = gson.fromJson(data, UserSetupVo.class);
				output = action.validateUser(vo);
				if ("T".equals(output)) {
					httpSession.setAttribute("authKey", vo.getUsername());
				}
			} catch (BussinessException e) {

			}
		} else {
			output = "T";
		}
		return output;

	}

	@RequestMapping(value = "/admin")
	@ResponseBody
	public ModelAndView admin(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			return new ModelAndView("Admin/Home");
		}
	}

	@RequestMapping(value = "/admin.lang.create")
	@ResponseBody
	public ModelAndView langCreate(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			modelMap.put("response", response);
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.save", method = RequestMethod.POST)
	@ResponseBody
	public String langSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			LanguageVo vo = gson.fromJson(data, LanguageVo.class);
			try {
				ResponseVo response = commonController.saveLanguage(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.lang.view")
	@ResponseBody
	public ModelAndView langViewList(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			LanguageVo vo = new LanguageVo();
			try {
				ResponseVo response = commonController.getLanguageList(vo);
				modelMap.put("response", response);
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView langEdit(ModelMap modelMap, @RequestBody @RequestParam(value = "langid") String langid) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			LanguageVo vo = new LanguageVo();
			vo.setLangId(Integer.parseInt(langid));
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getLanguage(vo);
				modelMap.put("response", response);
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView langView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "langCode", required = false) String langCode,
			@RequestBody @RequestParam(value = "langName", required = false) String langName,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			LanguageVo vo = new LanguageVo();
			vo.setLangCode(langCode);
			vo.setLangName(langName);
			vo.setPage(page);
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getLanguageList(vo);
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");
					modelMap.put("response", response);

				} else
					modelMap.put("result", "false");
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewLanguage");
		}
	}

	@RequestMapping(value = "/admin.categoryType.create")
	@ResponseBody
	public ModelAndView categoryTypeCreate(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			modelMap.put("response", response);
			return new ModelAndView("Admin/user/CreateCategoryType");
		}
	}

	@RequestMapping(value = "/admin.categoryType.save", method = RequestMethod.POST)
	@ResponseBody
	public String categoryTypeSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			CategoryTypeVo vo = gson.fromJson(data, CategoryTypeVo.class);
			try {
				ResponseVo response = commonController.saveCategoryType(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.categoryType.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView categoryTypeView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "catTypeCode", required = false) String catTypeCode,
			@RequestBody @RequestParam(value = "catTypeName", required = false) String catTypeName,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			CategoryTypeVo vo = new CategoryTypeVo();
			vo.setCatTypeCode(catTypeCode);
			vo.setCatTypeName(catTypeName);
			vo.setPage(page);
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getCategoryTypeList(vo);
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");
					modelMap.put("response", response);

				} else
					modelMap.put("result", "false");
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewCategoryType");
		}
	}

	@RequestMapping(value = "/admin.categoryType.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView categoryTypeEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "catTypeId") String catTypeId) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			CategoryTypeVo vo = new CategoryTypeVo();
			vo.setCatTypeId(Integer.parseInt(catTypeId));
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getCategoryType(vo);
				modelMap.put("response", response);
			} catch (BussinessException e) {

			}

			return new ModelAndView("Admin/user/CreateCategoryType");
		}
	}

	@RequestMapping(value = "/admin.category.create")
	@ResponseBody
	public ModelAndView categoryCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			CategoryTypeVo vo = new CategoryTypeVo();
			response = commonController.getCategoryTypeList(vo);

			CategoryVo categoyvo = new CategoryVo();
			response.setObject(categoyvo);
			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("response", response);
			}

			LanguageVo languageVo = new LanguageVo();
			response = commonController.getLanguageList(languageVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}

			return new ModelAndView("Admin/user/CreateCategory");
		}
	}

	@RequestMapping(value = "/admin.category.save", method = RequestMethod.POST)
	@ResponseBody
	public String categorySave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			CategoryVo vo = gson.fromJson(data, CategoryVo.class);
			try {
				ResponseVo response = commonController.saveCategory(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.category.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView categoryView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "catCode", required = false) String catCode,
			@RequestBody @RequestParam(value = "catName", required = false) String catName,
			@RequestBody @RequestParam(value = "catType", required = false) String catType,
			@RequestBody @RequestParam(value = "gender", required = false) String gender,
			@RequestBody @RequestParam(value = "language", required = false) String language,
			@RequestBody @RequestParam(value = "genderflag", required = false) Boolean genderflag,
			@RequestBody @RequestParam(value = "langflag", required = false) Boolean langflag,

			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			if (Objects.isNull(catType) || catType.trim().length() == 0) {
				catType = "2";
			}
			CategoryVo vo = new CategoryVo();
			vo.setCatCode(catCode);
			vo.setCatName(catName);
			vo.setCatType(catType);
			if (Objects.nonNull(langflag) && langflag) {
				if (!"0".equals(language) && Objects.nonNull(language)) {
					vo.setLang(Integer.parseInt(language));
					modelMap.put("languageTag", language);
				}
			}
			if (Objects.nonNull(genderflag) && genderflag) {
				if (Objects.nonNull(gender)) {
					vo.setGender(gender);
					modelMap.put("genderTag", gender);
				}
			} else {
				vo.setGender("");
			}

			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getCategoryList(vo);
				CategoryTypeVo categoryTypeVo = new CategoryTypeVo();
				ResponseVo responseMap = commonController.getCategoryTypeList(categoryTypeVo);

				if (Objects.nonNull(responseMap.getObjectList())) {
					modelMap.put("CategoryType", responseMap);

				}
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");

				} else
					modelMap.put("result", "false");

				modelMap.put("response", response);
				LanguageVo languageVo = new LanguageVo();
				response = commonController.getLanguageList(languageVo);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("responseLang", response);

				}

				response = commonController.getGenderList();

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("gender", response);

				}

				modelMap.put("catCode", catCode);
				modelMap.put("catName", catName);
				modelMap.put("catType", catType);

				modelMap.put("page", page);
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewCategory");
		}
	}

	@RequestMapping(value = "/admin.category.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView categoryEdit(ModelMap modelMap, @RequestBody @RequestParam(value = "catId") String catId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			CategoryVo vo = new CategoryVo();
			vo.setCatId(Integer.parseInt(catId));
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getCategory(vo);

			} catch (BussinessException e) {
				throw new BussinessException("404");
			}

			 
			modelMap.put("response", response);
			try {
				LanguageVo languageVo = new LanguageVo();
				response = commonController.getLanguageList(languageVo);
			} catch (BussinessException e) {
				throw new BussinessException("404");
			}
			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}
			return new ModelAndView("Admin/user/CreateCategory");
		}
	}

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
			response = commonController.getLanguageList(vo);

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
				ResponseVo response = commonController.saveMovie(vo);
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
				ResponseVo response = commonController.enablePriority(vo);
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
			@RequestBody @RequestParam(value = "movieCode", required = false) String movieCode,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			MovieReviewVo vo = new MovieReviewVo();
			vo.setMovieCode(movieCode);

			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getMovieReview(vo);
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
				response = commonController.getMovieList(vo);
				LanguageVo languageVo = new LanguageVo();

				ResponseVo responseVo = commonController.getLanguageList(languageVo);

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
				response = commonController.getMovie(vo);

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
			response = commonController.getLanguageList(languageVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}
			CategoryVo catVo = new CategoryVo();
			catVo.setCatType("1");
			catVo.setPerPage(200);
			response = new ResponseVo();
			response = commonController.getCategoryList(catVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseMovie", response);

			}
			return new ModelAndView("Admin/user/CreateMovie");
		}
	}

	@RequestMapping(value = "/admin.profile.create")
	@ResponseBody
	public ModelAndView profileCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			CategoryVo catVo = new CategoryVo();
			catVo.setCatType("2");
			catVo.setPerPage(200);
			response = new ResponseVo();
			ProfileVo profile = new ProfileVo();
			profile.setGender("M");
			response = commonController.getCategoryList(catVo);

			if (Objects.nonNull(response.getObjectList())) {

				modelMap.put("categoryList", response);
				response.setObject(profile);
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/CreateProfile");
		}
	}

	@RequestMapping(value = "/admin.profile.save", method = RequestMethod.POST)
	@ResponseBody
	public String profileSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			ProfileVo vo = gson.fromJson(data, ProfileVo.class);
			try {
				ResponseVo response = commonController.profileSave(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.profile.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView profileView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "profileName", required = false) String profileName,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "gender", required = false) String gender,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			ProfileVo vo = new ProfileVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setProfileName(profileName); 
			vo.setTag(tag);
			vo.setGender(gender);

			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getProfileList(vo);

				modelMap.put("profileName", profileName);
				modelMap.put("tag", tag);
				modelMap.put("gender", gender);
				modelMap.put("page", page);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("response", response);

				}

			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewProfile");
		}
	}

	@RequestMapping(value = "/admin.profile.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView profileEdit(ModelMap modelMap, 
			@RequestBody @RequestParam(value = "profileId", required = false) String profileId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			ProfileVo profileVo = new ProfileVo(); 
			profileVo.setPerPage(200);
			try {
				profileVo.setProfileId(Integer.parseInt(profileId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = commonController.getProfile(profileVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			CategoryVo catVo = new CategoryVo();
			catVo.setCatType("2");
			catVo.setPerPage(200);
			response = new ResponseVo();
			response = commonController.getCategoryList(catVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("categoryList", response);

			}
			return new ModelAndView("Admin/user/CreateProfile");
		}
	}

	@RequestMapping(value = "/admin.video.create")
	@ResponseBody
	public ModelAndView videoCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			return new ModelAndView("Admin/user/CreateVideo");
		}
	}

	@RequestMapping(value = "/admin.video.save", method = RequestMethod.POST)
	@ResponseBody
	public String videoSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			VideoVo vo = gson.fromJson(data, VideoVo.class);
			try {
				ResponseVo response = commonController.saveVideoVo(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.video.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView videoView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "title", required = false) String title,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			VideoVo vo = new VideoVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setTag(tag);
			vo.setTitle(title);

			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getVideoList(vo);

				modelMap.put("title", title);
				modelMap.put("tag", tag);
				modelMap.put("page", page);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("response", response);

				}

			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewVideo");
		}
	}

	@RequestMapping(value = "/admin.video.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView videoEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "videoId", required = false) String videoId) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			VideoVo videoVo = new VideoVo();

			videoVo.setPerPage(200);
			try {
				videoVo.setVideoId(Integer.parseInt(videoId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = commonController.getVideo(videoVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/CreateVideo");
		}
	}

	@RequestMapping(value = "/admin.gallery.create")
	@ResponseBody
	public ModelAndView galleryCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			return new ModelAndView("Admin/user/CreateGallery");
		}
	}

	@RequestMapping(value = "/admin.gallery.uploads", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("uploadfile") MultipartFile[] uploadfiles) {
		List<String> filenames = new ArrayList<>();
		String fileName = null;
		Random rand;
		String monthDate[] = StringUitity.generateUploadPath().split("-");

		String directory = Initializer.UPLOAD_PATH + "\\images\\" + monthDate[0] + "\\" + monthDate[1] + "\\";
		List<UploadFile> uploadFiles = new ArrayList<>();
		UploadFile uploadFile = null;
		for (MultipartFile uploadfile : uploadfiles) {
			uploadFile = new UploadFile();
			rand = new Random();
			int random = rand.nextInt(1000);
			fileName = random + "_" + uploadfile.getOriginalFilename();
			try {

				File file = new File(directory, fileName);
				uploadfile.transferTo(file);
				uploadFile.setFileName(monthDate[0] + "/" + monthDate[1] + "/" + fileName);
				uploadFile.setMonth(monthDate[1]);
				uploadFile.setYear(monthDate[0]);
				BufferedImage bimg = ImageIO.read(new File(directory, fileName));
				int width = bimg.getWidth(); // 100
				int height = bimg.getHeight(); // 250
				double imgWidth = 0.0;
				if (height > 100) {
					double wdHeight = height / 100;
					height = 100;
					imgWidth = width / wdHeight;
				}
				int roundWidth = (int) Math.round(imgWidth);

				BufferedImage img = new BufferedImage(roundWidth, 100, BufferedImage.TYPE_INT_RGB);

				img.createGraphics().drawImage(bimg.getScaledInstance(roundWidth, 100, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(img, "jpg", new File(directory + "//thumb_" + fileName));
				uploadFile.setPath(
						"http://localhost:8081/pic/" + monthDate[0] + "/" + monthDate[1] + "/thumb_" + fileName);
				uploadFiles.add(uploadFile);
			} catch (IOException e) {
				System.out.print("ERROR");

			}

		}
		gson = new Gson();
		fileName = gson.toJson(uploadFiles);

		return fileName;
	}

	
	
	
	
	
	@RequestMapping(value = "/admin.gallery.uploadThumbnail", method = RequestMethod.POST)
	@ResponseBody
	public String uploadThumbnail(@RequestParam("uploadfile") MultipartFile[] uploadfiles,@RequestParam("id") String id ) {
		List<String> filenames = new ArrayList<>();
		String fileName = null;
		Random rand;
		String monthDate[] = StringUitity.generateUploadPath().split("-");

		String directory = Initializer.UPLOAD_PATH + "\\images\\" + monthDate[0] + "\\" + monthDate[1] + "\\";
		List<UploadFile> uploadFiles = new ArrayList<>();
		UploadFile uploadFile = null;
		for (MultipartFile uploadfile : uploadfiles) {
			uploadFile = new UploadFile();
			rand = new Random();
			int random = rand.nextInt(1000);
			fileName = random + "_" + uploadfile.getOriginalFilename();
			try {

				File file = new File(directory, fileName);
				uploadfile.transferTo(file);
				fileName=monthDate[0] + "/" + monthDate[1] + "/"+fileName;
				
				try {
					ResponseVo response = commonController.updateThumbnail(fileName,Integer.parseInt(id));
				} catch (NumberFormatException e) {
				 
					e.printStackTrace();
				} catch (BussinessException e) {
				 
					e.printStackTrace();
				}
			} catch (IOException e) {
				System.out.print("ERROR");

			}

		}
	 

		return fileName;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/admin.gallery.save", method = RequestMethod.POST)
	@ResponseBody
	public String gallerySave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			GalleryVo vo = gson.fromJson(data, GalleryVo.class);
			try {
				ResponseVo response = commonController.saveGalleryVo(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.gallery.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView galleryView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "title", required = false) String title,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			GalleryVo vo = new GalleryVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setTag(tag);
			vo.setTitle(title);

			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getGalleryList(vo);

				modelMap.put("title", title);
				modelMap.put("tag", tag);
				modelMap.put("page", page);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("response", response);

				}

			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewGallery");
		}
	}

	@RequestMapping(value = "/admin.gallery.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView galleryEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "galleryId", required = false) String galleryId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			GalleryVo galleryVo = new GalleryVo();

			galleryVo.setPerPage(200);
			try {
				galleryVo.setGalleryId(Integer.parseInt(galleryId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = commonController.getGallery(galleryVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/CreateGallery");
		}
	}
	
	
	
	
	@RequestMapping(value = "/admin.gallery.updateThumbnail", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateThumbnail(ModelMap modelMap,
			@RequestBody @RequestParam(value = "galleryId", required = false) String galleryId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			GalleryVo galleryVo = new GalleryVo();

			galleryVo.setPerPage(200);
			try {
				galleryVo.setGalleryId(Integer.parseInt(galleryId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = commonController.getGallery(galleryVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/UpdateThumbnail");
		}
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
				ResponseVo response = commonController.saveMovieReview(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	
	
	
	
	
	

}
