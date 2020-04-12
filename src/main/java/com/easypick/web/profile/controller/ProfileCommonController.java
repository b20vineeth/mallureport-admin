package com.easypick.web.profile.controller;
  
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
import com.easypick.admin.vo.ProfileVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 
import com.easypick.web.category.bussinesscontroller.CategoryBussinessInterface; 
import com.easypick.web.profile.bussinesscontroller.ProfileBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class ProfileCommonController {

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
	protected CategoryBussinessInterface categoryController;
	
	@Autowired
	protected ProfileBussinessInterface profileController;

	
	

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
			response = categoryController.getCategoryList(catVo);

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
				ResponseVo response = profileController.profileSave(vo);
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
				response = profileController.getProfileList(vo);

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
			response = profileController.getProfile(profileVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			CategoryVo catVo = new CategoryVo();
			catVo.setCatType("2");
			catVo.setPerPage(200);
			response = new ResponseVo();
			response = categoryController.getCategoryList(catVo);

			if (Objects.nonNull(response.getObjectList())) {
				modelMap.put("categoryList", response);

			}
			return new ModelAndView("Admin/user/CreateProfile");
		}
	}

	
	
	
	
	
	
	
	
	
	
}
