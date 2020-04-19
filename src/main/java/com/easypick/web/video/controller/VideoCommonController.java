package com.easypick.web.video.controller;
  
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

import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.video.bussinesscontroller.VideoBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class VideoCommonController {

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
	protected VideoBussinessInterface videoController;
	
  
	
	

	@RequestMapping(value = "/admin.video.create")
	@ResponseBody
	public ModelAndView videoCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			return new ModelAndView("Admin/user/CreateVideo");
		}
	}

	@RequestMapping(value = "/admin.video.save", method = RequestMethod.POST)
	@ResponseBody
	public String videoSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			VideoVo vo = gson.fromJson(data, VideoVo.class);
			try {
				ResponseVo response = videoController.saveVideoVo(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.video.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView videoView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "title", required = false) String title,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			VideoVo vo = new VideoVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setTag(tag);
			vo.setTitle(title);

			ResponseVo response = new ResponseVo();
			try {
				response = videoController.getVideoList(vo);

				modelMap.put("title", title);
				modelMap.put("tag", tag);
				modelMap.put("page", page);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("response", response);

				}

			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewVideo");
		}
	}

	@RequestMapping(value = "/admin.video.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView videoEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "videoId", required = false) String videoId) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			VideoVo videoVo = new VideoVo();

			videoVo.setPerPage(200);
			try {
				videoVo.setVideoId(Integer.parseInt(videoId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = videoController.getVideo(videoVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/CreateVideo");
		}
	}

	 
	
}
