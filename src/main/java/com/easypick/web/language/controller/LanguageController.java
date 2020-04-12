package com.easypick.web.language.controller;
  
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

import com.easypick.admin.vo.LanguageVo;   
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 
import com.easypick.web.language.bussinesscontroller.LanguageBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class LanguageController {

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
	protected LanguageBussinessInterface commonController;

	@RequestMapping(value = "/admin.lang.create")
	@ResponseBody
	public ModelAndView langCreate(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = new ResponseVo();
			modelMap.put("response", response);
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.save", method = RequestMethod.POST)
	@ResponseBody
	public String langSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			LanguageVo vo = gson.fromJson(data, LanguageVo.class);
			try {
				ResponseVo response = commonController.saveLanguage(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.lang.view")
	@ResponseBody
	public ModelAndView langViewList(ModelMap modelMap) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			LanguageVo vo = new LanguageVo();
			try {
				ResponseVo response = commonController.getLanguageList(vo);
				modelMap.put("response", response);
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView langEdit(ModelMap modelMap, @RequestBody @RequestParam(value = "langid") String langid) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			LanguageVo vo = new LanguageVo();
			vo.setLangId(Integer.parseInt(langid));
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getLanguage(vo);
				modelMap.put("response", response);
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/CreateLanguage");
		}
	}

	@RequestMapping(value = "/admin.lang.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView langView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "langCode", required = false) String langCode,
			@RequestBody @RequestParam(value = "langName", required = false) String langName,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			LanguageVo vo = new LanguageVo();
			vo.setLangCode(langCode);
			vo.setLangName(langName);
			vo.setPage(page);
			ResponseVo response = new ResponseVo();
			try {
				response = commonController.getLanguageList(vo);
				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("result", "true");
					modelMap.put("response", response);

				} else
					modelMap.put("result", "false");
			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewLanguage");
		}
	}
 
}
