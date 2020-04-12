package com.easypick.web.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easypick.framework.utility.controller.ActionControllerInterface;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;
import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.admin.vo.UserSetupVo; 

@Controller
public class GallerySetupController {

	Gson gson;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected ApplicationEventPublisher publisher;
	@Autowired
	protected HttpSession session;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	protected ActionControllerInterface action;

	@RequestMapping(value = "/gallerysetup.create", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView create(ModelMap modelMap, @RequestParam(value = "data", required = false) String track) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
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
	
	@RequestMapping(value = "/gallerysetup.bulkUpload", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView bulkUpload(ModelMap modelMap, @RequestParam(value = "data", required = false) String track) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
		input.put("param2", "bulkUpload");
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

	@RequestMapping(value = "/gallerysetup.save", method = RequestMethod.POST)
	@ResponseBody
	public String userSave(ModelMap modelMap, @RequestParam(value = "datas", required = true) String data) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
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

	@RequestMapping(value = "/gallerysetup.bulkUploadGallery", method = RequestMethod.POST)
	@ResponseBody
	public String bulkUploadGallery(ModelMap modelMap, @RequestParam(value = "datas", required = true) String data) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
		input.put("param2", "bulkSave");
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

/*	@RequestMapping(value = "/gallerysetup.fileUpload", method = RequestMethod.POST)  
	public String uploadFile(Model model, MultipartFile file) throws IOException {
		String line = "";
	    String cvsSplitBy = ",";
	    BufferedReader br = null;
	    InputStreamReader inputStreamReader=new InputStreamReader(file.getInputStream()); 
	    br = new BufferedReader(inputStreamReader);
	   
	  /*  while ((line = br.readLine()) != null) {
	    	String[] country = line.split(cvsSplitBy);

            System.out.println("Country->"+country);

        }*/
	   //  publisher.publishEvent(new BlogModifiedEvent(br));
	  /*
	    model.addAttribute("message", "File: " + file.getOriginalFilename() 
	      + " has been uploaded successfully!");
	    return "excel";
	}*/
/*
	@RequestMapping(value = "/gallerysetup.uploadExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView uploadExcel(ModelMap modelMap, @RequestParam(value = "data", required = false) String track) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
		input.put("param2", "uploadExcel");
		ResponseVo vo;
		try {
			vo = action.performAction(input);
			modelMap.addAttribute("response", vo);
			return new ModelAndView("Screen/" + vo.getScreenMode());

		} catch (BussinessException e) {

			modelMap.addAttribute("response", e.getMessage());
			return new ModelAndView("Screen/redirect");

		}

	}*/

	@RequestMapping(value = "/gallerysetup.deactivate", method = RequestMethod.GET)
	@ResponseBody
	public String userdeactivate(ModelMap modelMap, @RequestParam(value = "id", required = false) String id) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
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

	@RequestMapping(value = "/gallerysetup.list", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView userList(ModelMap modelMap, @RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "validityfrom", required = false) String validityFrom,
			@RequestParam(value = "validityto", required = false) String validityto,
			@RequestParam(value = "page", required = false) String page, 
			@RequestParam(value = "status", required = false) String status) {

		Map<String, String> input = new HashMap<String, String>();
		input.put("param1", "gallerysetup");
		input.put("param2", "list");

		GallerySetupVo gallerySetupVo = new GallerySetupVo();
		gallerySetupVo.setTitle(title);
		gallerySetupVo.setDescription(description);
		gallerySetupVo.setTag(tag);
		gallerySetupVo.setValidityFrom(validityFrom);
		gallerySetupVo.setValidityto(validityto);
		gallerySetupVo.setStatus(status == null ? "Y" : status);
		 
		try {
			gallerySetupVo.setPage(page == null ? 1 : Integer.parseInt(page));
		} catch (Exception e) {
			gallerySetupVo.setPage(1);
		}
		input.put("data", new Gson().toJson(gallerySetupVo).toString());

		ResponseVo responseVo = new ResponseVo();
		try {
			responseVo = action.performAction(input);
			modelMap.addAttribute("response", responseVo); 
			responseVo.setFilterObj(gallerySetupVo);

			return new ModelAndView("Screen/" + responseVo.getScreenMode());

		} catch (BussinessException e) {
			modelMap.addAttribute("response", responseVo);
			return new ModelAndView("Screen/" + responseVo.getScreenMode());

		}
	}

}
