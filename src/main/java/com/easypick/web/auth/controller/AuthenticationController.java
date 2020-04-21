package com.easypick.web.auth.controller;
 
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

import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.web.auth.bussinesscontroller.UserControllerInterface;
import com.easypick.web.common.bussinesscontroller.ControllerInterface;
import com.google.gson.Gson; 

@Controller
public class AuthenticationController {

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
				vo = action.validateUser(vo);
				if(Objects.nonNull(vo))
				 {
					httpSession.setAttribute("authKey", vo.getUsername());
					httpSession.setAttribute("authdata", vo);
					output = "T";
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
 
}
