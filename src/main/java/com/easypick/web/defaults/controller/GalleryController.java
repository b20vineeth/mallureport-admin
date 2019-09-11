package com.easypick.web.defaults.controller; 
import java.io.Console;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.servlet.ModelAndView;

import com.easypick.framework.utility.commonUtility.SecurityEncription;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.controller.ActionController;
import com.easypick.framework.utility.controller.ActionControllerInterface;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson; 


@Controller 
public class GalleryController {

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

	@RequestMapping(value="/gallery.view",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView galleryCreate(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request){
		modelMap.addAttribute("version",StringUitity.generateRandomNumber(9999, 1111));
		modelMap.addAttribute("Url-Map","http://localhost/res/"); 
		return new ModelAndView("Screen/gallery/GalleryView");
	}
	
	 
	
	
	
	
	
	
	/*@RequestMapping(value="/gallery.save",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView  gallerySave(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request) 
	{

		ResponseVo vo;
		try {

			return new ModelAndView("Screen/"+vo.getScreenMode());

		} catch (BussinessException e) {

			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}

	}


	@RequestMapping(value="/gallery.list",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView  galleryList(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request) 
	{
		Map<String,String> input=new HashMap<>();
		input.put("param1", param1);
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo);
			return new ModelAndView("Screen/"+vo.getScreenMode());

		} catch (BussinessException e) {

			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}
	}


	@RequestMapping(value="/gallery.update",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView  galleryUpdate(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request) 
	{
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

	@RequestMapping(value="/gallery.cancel",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView galleryCancel(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request) {

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
	@RequestMapping(value="/gallery.edit",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView galleryEdit(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request) {

		Map<String,String> input=new HashMap<>();
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
	}*/




}
