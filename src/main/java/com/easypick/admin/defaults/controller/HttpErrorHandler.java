package com.easypick.admin.defaults.controller;


import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HttpErrorHandler   {



	@RequestMapping("/404")
	public ModelAndView  error404(ModelMap modelMap) 
	{
		return new ModelAndView("Error/404");

	}
	@RequestMapping("/403")
	public ModelAndView  error403(ModelMap modelMap) 
	{
		return new ModelAndView("Error/403");

	}
	@RequestMapping("/503")
	public ModelAndView  error503(ModelMap modelMap) 
	{
		return new ModelAndView("Error/503");

	}
	@RequestMapping("/400")
	public ModelAndView  error400(ModelMap modelMap) 
	{
		return new ModelAndView("Error/400");

	}
	@RequestMapping("/500")
	public ModelAndView  error500(ModelMap modelMap) 
	{
		return new ModelAndView("Error/500");

	}

}
