package com.easypick.web.defaults.controller; 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.servlet.ModelAndView;

import com.easypick.framework.utility.controller.ActionController;
import com.easypick.framework.utility.controller.ActionControllerInterface;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson; 


@Controller 
public class DefaultController {

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



	Map<String,String> privilege=null;

	@RequestMapping(value="/")
	@ResponseBody
	public ModelAndView homePage(ModelMap modelMap){

		Map<String,String> input=new HashMap<String,String>();
		input.put("param1", "home");
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo.getObject());
			return new ModelAndView("Screen/"+vo.getScreenMode());
			
		} catch (BussinessException e) {
			
			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}

	}


	@RequestMapping(value="/{param1}")
	@ResponseBody
	public ModelAndView oneParameter(ModelMap modelMap,@PathVariable("param1") String param1){
		Map<String,String> input=new HashMap<String,String>();
		input.put("param1", param1);
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo.getObject());
			return new ModelAndView("Screen/"+vo.getScreenMode());
			
		} catch (BussinessException e) {
			
			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}
	}


	@RequestMapping(value="/{param1}/{param2}")
	@ResponseBody
	public ModelAndView twoParameter(ModelMap modelMap,@PathVariable("param1") String param1,
			@PathVariable("param2") String param2){

		Map<String,String> input=new HashMap<String,String>();
		input.put("param1", param1);
		input.put("param2", param2);
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo.getObject());
			return new ModelAndView("Screen/"+vo.getScreenMode());
			
		} catch (BussinessException e) {
			
			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}
	}

	@RequestMapping(value="/{param1}/{param2}/{param3}")
	@ResponseBody
	public ModelAndView threeParameter(ModelMap modelMap,@PathVariable("param1") String param1,
			@PathVariable("param2") String param2,
			@PathVariable("param3") String param3){

		Map<String,String> input=new HashMap<String,String>();
		input.put("param1", param1);
		input.put("param2", param2);
		input.put("param3", param3);
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo.getObject());
			return new ModelAndView("Screen/"+vo.getScreenMode());
			
		} catch (BussinessException e) {
			
			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}
	}
	@RequestMapping(value="/{param1}/{param2}/{param3}/{param4}")
	@ResponseBody
	public ModelAndView fourParameter(ModelMap modelMap,@PathVariable("param1") String param1,
			@PathVariable("param2") String param2,
			@PathVariable("param3") String param3,
			@PathVariable("param4") String param4){

		Map<String,String> input=new HashMap<String,String>();
		input.put("param1", param1);
		input.put("param2", param2);
		input.put("param3", param3);
		input.put("param4", param4);
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo.getObject());
			return new ModelAndView("Screen/"+vo.getScreenMode());
			
		} catch (BussinessException e) {
			
			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}
	}




}
