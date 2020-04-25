package com.easypick.web.common.controller;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easypick.admin.admin.job.controller.JobControllerInterface;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.controller.EventProcess;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.common.bussinesscontroller.ControllerInterface;
import com.google.gson.Gson;

@Controller
public class CommonController {

	Gson gson;
	 
	@Autowired
	protected ApplicationEventPublisher publisher;
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
	private EventProcess event;
	@Autowired
	protected ControllerInterface commonController;

	@Autowired
	protected JobControllerInterface jobcommonController;
	
	@RequestMapping(value = "/admin.common.getdata", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getdata(ModelMap modelMap, @RequestBody @RequestParam("type") String type,
			@RequestBody @RequestParam(value = "term", required = false) String term)
			throws BussinessException
	 {
			gson=new Gson();
		 try {
				SettingsVo show=new SettingsVo();
		 		show.setType(type);
		 		if(Objects.nonNull(term) && term.trim().length()>0)
		 		{
		 			show.setTerm(term); 
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
			/*gson=new Gson();
		 try {
				 MovieVo movie=new MovieVo();
				 movie.setFilterType("R");
				ResponseVo response = commonController.testdata(movie);
				 
				return gson.toJson(response.getObjectList()).toString();
			} catch (BussinessException e) {
				return "F"; 
			}*/ 
		//event.trigger("com.admin.saveMovie", response);
		/*ResponseVo vo=new ResponseVo();
		vo.setEvent("com.admin.saveMovie");
		publisher.publishEvent(vo);*/
		
		jobcommonController.execute("movieHomePageBussinessController");
		
		
		return "F";
			
		}
	
 
	 
}
