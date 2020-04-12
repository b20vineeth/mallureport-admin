package com.easypick.web.common.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.multipart.MultipartFile;

import com.easypick.admin.admin.job.ThreadPoolTaskSchedulerExamples;
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.GalleryContentVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.admin.vo.UploadFile;
import com.easypick.admin.vo.UploadedFile;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.commonUtility.GalleryXmlParse;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.auth.bussinesscontroller.UserControllerInterface;
import com.easypick.web.common.bussinesscontroller.ControllerInterface;
import com.google.gson.Gson;
import com.mysql.fabric.Response;
import com.sprhib.init.Initializer;
import com.sprhib.init.WebAppConfig;

@Controller
public class CommonController {

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
	protected ControllerInterface commonController;
 
	
	
	@RequestMapping(value = "/admin.common.getdata", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getdata(ModelMap modelMap, @RequestBody @RequestParam("type") String type,
			@RequestBody @RequestParam(value = "tag", required = false) String tag)
			throws BussinessException
	 {
			gson=new Gson();
		 try {
				SettingsVo show=new SettingsVo();
		 		show.setType(type);
		 		if(Objects.nonNull(tag) && tag.trim().length()>0)
		 		{
		 			show.setTag(tag);
		 		}
				ResponseVo response = commonController.getdata(show);
				 
				return gson.toJson(response.getObjectList()).toString();
			} catch (BussinessException e) {
				return "F"; 
			}
			
		}
	
	
	@RequestMapping(value = "/admin.common.testdata", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String testdata(ModelMap modelMap, @RequestBody @RequestParam("type") String type,
			@RequestBody @RequestParam(value = "tag", required = false) String tag)
			throws BussinessException
	 {
			gson=new Gson();
		 try {
				 MovieVo movie=new MovieVo();
				 movie.setFilterType("R");
				ResponseVo response = commonController.testdata(movie);
				 
				return gson.toJson(response.getObjectList()).toString();
			} catch (BussinessException e) {
				return "F"; 
			}
			
		}
	
 
	 
}
