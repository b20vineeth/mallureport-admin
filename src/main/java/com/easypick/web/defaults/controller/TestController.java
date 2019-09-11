package com.easypick.web.defaults.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.google.gson.Gson;
@Controller 
public class TestController {
	
	@RequestMapping(value="/gallery.list",method=RequestMethod.GET)
	@ResponseBody
	public String galleryList(Locale locale,ModelMap modelMap,@RequestBody  @RequestParam("data") String data ,  HttpServletRequest request){
	List<GalleryVo> galleryVos=new ArrayList<>();
	GalleryVo vo =null;
	  for(int i=0;i<=5;i++)
	{
		vo =new GalleryVo();
		vo.setCreatedDate(new Date());
		vo.setDescription("test "+i);
		vo.setTitle("title "+i);
		vo.setId(i);
		vo.setShortDesc("shortDesc-"+i);
		galleryVos.add(vo);
		
	}  
	Page page=new Page();
	page.setCurrentPage(1);
	page.setTotalPage(10);
	page.setTotalResult(250); 
	ResponseVo vo1=new ResponseVo();
	vo1.setPage(page);
   vo1.setGalleryList(galleryVos);
	vo1.setResponse(true);
	Gson gson = new Gson(); 
	return gson.toJson(vo1);
		 
	}

}
