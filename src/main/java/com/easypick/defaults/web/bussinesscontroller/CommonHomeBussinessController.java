package com.easypick.defaults.web.bussinesscontroller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo; 
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.SettingsDao;
import com.easypick.framework.utility.persistence.WebDao; 
import com.easypick.framework.utility.validation.ValidationBI;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class CommonHomeBussinessController implements ControllerBI {

	@Autowired
	private Map<String, WebDao> dao;
	@Autowired
	private Map<String, ControllerBI> controller;
	@Autowired
	private Map<String, SettingsDao> settingsDao;

	@Autowired
	private Map<String, ValidationBI> validation;
	

     
	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {
		ResponseVo vo=new ResponseVo();
		if("tag".equals(watchdog.getInput().get("key1").toLowerCase()))
		{
			return controller.get("tagExecuteController").execute(watchdog);
		}
		else
		{
			updateModuleSubmodule(watchdog.getInput(),watchdog);
			 
			if(Objects.nonNull(controller.get(watchdog.getModule()+"Controller")))
		     {
				constructMethodType(watchdog);
				return controller.get(watchdog.getModule()+"Controller").execute(watchdog);
			 }
			else
			{
				constructMethodType(watchdog);
				return controller.get("commonExecuteController").execute(watchdog);
			}
			
		}
		 
		 
	} 
	private void constructMethodType(WatchDogVo watchdog) throws BussinessException {
		String key=getLastKey(watchdog.getInput());
		if(Objects.nonNull(watchdog.getInput().get(key)))
		{
			if(watchdog.getInput().get(key).contains(".html"))
			 {
				 if(Objects.nonNull(watchdog.getSubModule()) 
						&& !"/".equals(watchdog.getSubModule()))
				{
					watchdog.setType("content");
					 
				}
				else
				{
					 throw new BussinessException("404"); 
				}
				 
				
			 }
			else if(!"0".equals(validatePage(watchdog.getInput().get(key))))
			{

				if("/".equals(watchdog.getSubModule()))
				{
					watchdog.setType("home");
					 
				}
				else
				{
					watchdog.setType("submodule");
					 
				}
				Page page=new Page();
				page.setPages(true);
				page.setCurrentPage(Integer.parseInt(validatePage(watchdog.getInput().get(key))));
				watchdog.setPage(page);
						
			}
			else  
			{
				if("/".equals(watchdog.getSubModule()))
				{
					watchdog.setType("home");
					defaultPageNumber(watchdog);
				}
				else
				{
					watchdog.setType("submodule");
					defaultPageNumber(watchdog);
				}
					
			}
		}
		 
		
	}



	private void defaultPageNumber(WatchDogVo watchdog) {
		Page page=new Page();
		page.setCurrentPage(1);
		watchdog.setPage(page);
	}



	private String getLastKey(Map<String, String> input) {
		 
		Integer inputSize=input.size()-1;
		return "key"+inputSize;
	}

	 

	 

	private void updateModuleSubmodule(Map<String, String> input, WatchDogVo watchdog) {
		watchdog.setSubModule("/");
		for (Map.Entry<String,String> entry : input.entrySet())  
		{
            if("key1".equals(entry.getKey()))
            {
            	if("0".equals(validatePage(entry.getValue())))
            	{
            		watchdog.setModule(entry.getValue());
            	}
            			
            }
            if("key2".equals(entry.getKey()))
            {
            	if("0".equals(validatePage(entry.getValue())))
            	{
            		watchdog.setSubModule(entry.getValue());
            	}
            			
            }
                        
		}
		 
			
		
	}

	  
  
	private String validatePage(String key1) {
		Integer num = 0;
		if (key1.contains("page-")) {
			key1 = key1.trim().replace("page-", "");
			try {
				num = Integer.parseInt(key1);
			} catch (Exception e) {

			}
		}

		return num.toString();
	}

	 

}
