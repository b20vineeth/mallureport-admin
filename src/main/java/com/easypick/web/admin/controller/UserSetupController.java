package com.easypick.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easypick.framework.utility.controller.ActionControllerInterface;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;

import com.easypick.admin.vo.UserSetupVo;

@Controller
public class UserSetupController {

	Gson gson;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	protected ActionControllerInterface action;

	@RequestMapping(value = "/usersetup.create", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView userCreate(ModelMap modelMap, @RequestParam(value = "data", required = false) String track) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "userSetup");
		input.put("param2", "create");
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo);
			return new ModelAndView("Screen/" + vo.getScreenMode());

		} catch (BussinessException e) {

			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}

	}

	@RequestMapping(value = "/usersetup.save", method = RequestMethod.POST)
	@ResponseBody
	public String userSave(ModelMap modelMap, @RequestParam(value = "datas", required = true) String data) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "userSetup");
		input.put("param2", "Save");
		input.put("data", data);

		ResponseVo responseVo = new ResponseVo();

		try {
			responseVo = action.performAction(input);
			if (responseVo.getResponse()) {
				return new Gson().toJson(responseVo);
			} else {
				return new Gson().toJson(responseVo);
			}

		} catch (BussinessException e) {
			responseVo.setResponse(false);
			return new Gson().toJson(responseVo);

		}
	}
	
 
	
	@RequestMapping(value = "/usersetup.deactivate", method = RequestMethod.GET)
	@ResponseBody
	public String userdeactivate(ModelMap modelMap, @RequestParam(value = "id", required = false) String id) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "userSetup");
		input.put("param2", "deactivate");
		UserSetupVo userSetupVo = new UserSetupVo();
		userSetupVo.setUserId(id);
		
		input.put("data", new Gson().toJson(userSetupVo).toString());
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo);
			return "true";

		} catch (BussinessException e) {

			modelMap.addAttribute("response", e.getMessage());
		 
		}
		return "false";

	}
	@RequestMapping(value = "/usersetup.list", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView  userList(ModelMap modelMap, @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "validityfrom", required = false) String validityFrom,
			@RequestParam(value = "validityto", required = false) String validityto,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "status", required = false) String status) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "userSetup");
		input.put("param2", "list");

		UserSetupVo userSetupVo = new UserSetupVo();
		userSetupVo.setUsername(username);
		userSetupVo.setFirstName(firstName);
		userSetupVo.setLastName(lastName);
		userSetupVo.setValidityFrom(validityFrom);
		userSetupVo.setValidityto(validityto);
		userSetupVo.setStatus(status == null ? "Y" : status);
		userSetupVo.setEmail(email);
		try {
			userSetupVo.setPage(page == null ? 1 : Integer.parseInt(page));
		} catch (Exception e) {
			userSetupVo.setPage(1);
		}
		input.put("data", new Gson().toJson(userSetupVo).toString());

		ResponseVo responseVo = new ResponseVo();
		 try {
			responseVo = action.performAction(input);
			modelMap.addAttribute("response", responseVo); 
			responseVo.setScreenMode("usersetup/List");
			responseVo.setFilterObj(userSetupVo);	
			
			return new ModelAndView("Screen/" + responseVo.getScreenMode());

		} catch (BussinessException e) {
			modelMap.addAttribute("response", responseVo);
			return new ModelAndView("Screen/" + responseVo.getScreenMode());

		}
	}
	
	
	
	
	
	
	
	

}
