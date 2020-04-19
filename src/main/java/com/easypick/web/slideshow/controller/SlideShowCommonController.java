package com.easypick.web.slideshow.controller;
  
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

import com.easypick.admin.vo.SlideShowVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.slideshow.bussinesscontroller.SlideShowBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class SlideShowCommonController {

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
	protected SlideShowBussinessInterface slideshowController;
	 
	

	
	@RequestMapping(value = "/admin.slideshow.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView slideshowView(ModelMap modelMap, @RequestBody @RequestParam("type") String type)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			
		 	if (Objects.nonNull(type)) {
		 		SlideShowVo show=new SlideShowVo();
		 		show.setType(type);
		 		ResponseVo response = slideshowController.getAllSlideshow(show);
		 		modelMap.put("response", response); 
				modelMap.put("type", type); 
			}
		 	else{
		 	modelMap.put("redirectUrl", "login");
		 	}
			return new ModelAndView("Admin/user/ViewSlideshow");
		}
	}
	
	@RequestMapping(value = "/admin.slideshow.create", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView createslideshow(ModelMap modelMap, @RequestBody @RequestParam("type") String type)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response=new ResponseVo();
		 	if (Objects.nonNull(type)) {
		 		SlideShowVo show=new SlideShowVo();
		 		show.setType(type);
		 		show.setSlideflg("Y");
		 		response.setObject(show);
		 		 modelMap.put("response", response); 
			}
		 	else{
		 	modelMap.put("redirectUrl", "login");
		 	}
			return new ModelAndView("Admin/user/CreateSlideShow");
		}
	}


	@RequestMapping(value = "/admin.slideshow.save", method = RequestMethod.POST)
	@ResponseBody
	public String saveSlideshow(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			SlideShowVo slideShowVo = gson.fromJson(data, SlideShowVo.class);
			try {
				ResponseVo response = slideshowController.saveSlideShowVo(slideShowVo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	@RequestMapping(value = "/admin.slideshow.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView editSlideshow(ModelMap modelMap, @RequestBody @RequestParam("slideShowId") String slideShowId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			SlideShowVo slideShowVo = new SlideShowVo();
			try {
				slideShowVo.setSlideShowid(Integer.parseInt(slideShowId));
				ResponseVo response = slideshowController.getSlideShow(slideShowVo);
				 modelMap.put("response", response); 
			} catch (Exception e) {
				 
			}

		}
		return new ModelAndView("Admin/user/CreateSlideShow");
	}

	@RequestMapping(value = "/admin.slideshow.delete", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSlideshow(ModelMap modelMap, @RequestBody @RequestParam("slideShowId") String slideShowId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			SlideShowVo slideShowVo = new SlideShowVo();
			try {
				slideShowVo.setStatus("N");
				slideShowVo.setSlideShowid(Integer.parseInt(slideShowId));
				ResponseVo response = slideshowController.saveSlideShowVo(slideShowVo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}
	
	
	
	
	
	
	
	
}
