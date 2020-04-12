package com.easypick.web.settings.controller;
 
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
import com.easypick.admin.vo.SettingsVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.auth.bussinesscontroller.UserControllerInterface;
import com.easypick.web.category.bussinesscontroller.CategoryBussinessInterface;
import com.easypick.web.common.bussinesscontroller.ControllerInterface;
import com.easypick.web.language.bussinesscontroller.LanguageBussinessInterface;
import com.easypick.web.movie.bussinesscontroller.MovieBussinessInterface;
import com.easypick.web.settings.bussinesscontroller.SettingsBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class SettingsCommonController {

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
	protected SettingsBussinessInterface settingsController;
	

	
	
	@RequestMapping(value = "/admin.settings.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView settingsView(ModelMap modelMap, @RequestBody @RequestParam("type") String type)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			
		 	if (Objects.nonNull(type)) {
		 		SettingsVo show=new SettingsVo();
		 		show.setType(type);
		 		modelMap.put("type", type);
		 		try{
		 		ResponseVo response = settingsController.getAllSettings(show);
		 		modelMap.put("response", response); 
				
		 		}
		 		catch(Exception e)
		 		{
		 			
		 		}
		 		 
			}
		 	else{
		 	modelMap.put("redirectUrl", "login");
		 	}
			return new ModelAndView("Admin/user/ViewSettings");
		}
	}	
	@RequestMapping(value = "/admin.settings.save", method = RequestMethod.POST)
	@ResponseBody
	public String saveSettings(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			SettingsVo Settings = gson.fromJson(data, SettingsVo.class);
			try {
				ResponseVo response = settingsController.saveSettingsVo(Settings);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	@RequestMapping(value = "/admin.settings.create", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView createSettings(ModelMap modelMap, @RequestBody @RequestParam("type") String type)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response=new ResponseVo();
			List<SettingsVo> category =SettingsVo.generateCategory();
			modelMap.put("category", category);
			
			 
		 	if (Objects.nonNull(type)) {
		 		SettingsVo show=new SettingsVo();
		 		show.setType(type);
		 		  response.setObject(show);
		 		 modelMap.put("response", response); 
			}
		 	else{
		 	modelMap.put("redirectUrl", "login");
		 	}
			return new ModelAndView("Admin/user/CreateSettings");
		}
	}
	
	
	
	
	
	
	
}
