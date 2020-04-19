package com.easypick.web.categorytype.controller;
  
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

import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.categorytype.bussinesscontroller.CategoryTypeBussinessInterface;
import com.google.gson.Gson; 

@Controller
public class CategoryTypeController {

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
	protected CategoryTypeBussinessInterface commonController;

	 
	

		@RequestMapping(value = "/admin.categoryType.create")
		@ResponseBody
		public ModelAndView categoryTypeCreate(ModelMap modelMap) {

			String username = (String) httpSession.getAttribute("authKey");
			if (Objects.isNull(username)) {
				modelMap.put("redirectUrl", "login");
				return new ModelAndView("Admin/Redirect");
			} else {
				ResponseVo response = new ResponseVo();
				modelMap.put("response", response);
				return new ModelAndView("Admin/user/CreateCategoryType");
			}
		}

		@RequestMapping(value = "/admin.categoryType.save", method = RequestMethod.POST)
		@ResponseBody
		public String categoryTypeSave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
				throws BussinessException {

			String username = (String) httpSession.getAttribute("authKey");
			if (Objects.isNull(username)) {
				modelMap.put("redirectUrl", "login");

			} else {
				gson = new Gson();
				CategoryTypeVo vo = gson.fromJson(data, CategoryTypeVo.class);
				try {
					ResponseVo response = commonController.saveCategoryType(vo);
					return "T";
				} catch (Exception e) {
					return "F";
				}

			}
			return "Y";
		}

		@RequestMapping(value = "/admin.categoryType.view", method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView categoryTypeView(ModelMap modelMap,
				@RequestBody @RequestParam(value = "catTypeCode", required = false) String catTypeCode,
				@RequestBody @RequestParam(value = "catTypeName", required = false) String catTypeName,
				@RequestBody @RequestParam(value = "page", required = false) String page) {

			String username = (String) httpSession.getAttribute("authKey");
			if (Objects.isNull(username)) {
				modelMap.put("redirectUrl", "login");
				return new ModelAndView("Admin/Redirect");
			} else {

				CategoryTypeVo vo = new CategoryTypeVo();
				vo.setCatTypeCode(catTypeCode);
				vo.setCatTypeName(catTypeName);
				vo.setPage(page);
				ResponseVo response = new ResponseVo();
				try {
					response = commonController.getCategoryTypeList(vo);
					if (Objects.nonNull(response.getObjectList())) {
						modelMap.put("result", "true");
						modelMap.put("response", response);

					} else
						modelMap.put("result", "false");
				} catch (BussinessException e) {

				}
				return new ModelAndView("Admin/user/ViewCategoryType");
			}
		}

		@RequestMapping(value = "/admin.categoryType.edit", method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView categoryTypeEdit(ModelMap modelMap,
				@RequestBody @RequestParam(value = "catTypeId") String catTypeId) {

			String username = (String) httpSession.getAttribute("authKey");
			if (Objects.isNull(username)) {
				modelMap.put("redirectUrl", "login");
				return new ModelAndView("Admin/Redirect");
			} else {

				CategoryTypeVo vo = new CategoryTypeVo();
				vo.setCatTypeId(Integer.parseInt(catTypeId));
				ResponseVo response = new ResponseVo();
				try {
					response = commonController.getCategoryType(vo);
					modelMap.put("response", response);
				} catch (BussinessException e) {

				}

				return new ModelAndView("Admin/user/CreateCategoryType");
			}
		}

 
}
