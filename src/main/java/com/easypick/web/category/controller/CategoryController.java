package com.easypick.web.category.controller;
 
import java.util.ArrayList;
import java.util.List; 
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

import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.GenderVo;
import com.easypick.admin.vo.LanguageVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 
import com.easypick.web.category.bussinesscontroller.CategoryBussinessInterface;
import com.easypick.web.categorytype.bussinesscontroller.CategoryTypeBussinessInterface;
import com.easypick.web.language.bussinesscontroller.LanguageBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class CategoryController {

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
	protected CategoryTypeBussinessInterface categorytypeController;
	
	@Autowired
	protected LanguageBussinessInterface languageController;
	@Autowired
	protected CategoryBussinessInterface categoryController;
	

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
			response = categorytypeController.getCategoryTypeList(vo);

			CategoryVo categoyvo = new CategoryVo();
			response.setObject(categoyvo);
			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("response", response);
			}

			LanguageVo languageVo = new LanguageVo();
			response = languageController.getLanguageList(languageVo);

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
				ResponseVo response = categoryController.saveCategory(vo);
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
				response = categoryController.getCategoryList(vo);
				CategoryTypeVo categoryTypeVo = new CategoryTypeVo();
				ResponseVo responseMap = categorytypeController.getCategoryTypeList(categoryTypeVo);

				if (Objects.nonNull(responseMap.getObjectList())) {
					modelMap.put("CategoryType", responseMap);

				}
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");

				} else
					modelMap.put("result", "false");

				modelMap.put("response", response);
				LanguageVo languageVo = new LanguageVo();
				response = languageController.getLanguageList(languageVo);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("responseLang", response);

				}

				response = getGenderList();

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

	private ResponseVo getGenderList() {
		ResponseVo responseVo = new ResponseVo();
		List<GenderVo> gendervos = new ArrayList<>();

		GenderVo vo = new GenderVo();
		vo.setGenderCode("M");
		vo.setGenderName("Male");
		gendervos.add(vo);

		vo = new GenderVo();
		vo.setGenderCode("F");
		vo.setGenderName("Female");
		gendervos.add(vo);

		vo = new GenderVo();
		vo.setGenderCode("O");
		vo.setGenderName("Other");
		gendervos.add(vo);

		responseVo.setObjectList(gendervos);

		return responseVo;
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
				response = categoryController.getCategory(vo);

			} catch (BussinessException e) {
				throw new BussinessException("404");
			}

			modelMap.put("response", response);
			try {
				LanguageVo languageVo = new LanguageVo();
				response = languageController.getLanguageList(languageVo);
			} catch (BussinessException e) {
				throw new BussinessException("404");
			}
			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("responseLang", response);

			}
			return new ModelAndView("Admin/user/CreateCategory");
		}
	}
 
}
