package com.easypick.defaults.web.bussinesscontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.persistence.SettingsDao;
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class DefaultBussinessController implements ControllerBI {

	@Autowired
	private Map<String, ControllerBI> controller;
	@Autowired
	private Map<String, SettingsDao> settingsDao;
	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {

		ResponseVo vo = null;
		try {
			 return processKey(watchdog);

		} catch (Exception e) {
			throw new BussinessException("404");
		}


	}
	private ResponseVo processKey(WatchDogVo watchdog) throws BussinessException {
		 
		 try{
			return controller.get(watchdog.getInput().get("key1")+"BussinessController").execute(watchdog);
		 }
		 catch(Exception e)
		 {
			 return controller.get("commonNavigateBussinessController").execute(watchdog);
			 
		 }
		  
	}

	 

}
