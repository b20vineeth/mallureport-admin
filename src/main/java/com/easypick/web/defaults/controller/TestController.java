package com.easypick.web.defaults.controller;
 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.controller.ActionControllerInterface;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;

@Controller
public class TestController {

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

	Map<String, String> privilege = null;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@RequestMapping(value = "/event")
	@ResponseBody
	public String homePage(ModelMap modelMap) {
		
		eventPublisher.publishEvent("Vineet");
		return "F";

		 

	}
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(ModelMap modelMap) {
		gson=new Gson();
		ResponseVo vo=new ResponseVo();
		MovieVo movieVo=new MovieVo();
		movieVo.setMovieCode("ANU");
		vo.setObject(movieVo);
		return gson.toJson(vo).toString();

		 

	}
	
	
	 
}
