package com.easypick.framework.utility.persistence;

import java.util.Map;

import com.easypick.admin.vo.ControllerSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface WebDao {
	ResponseVo execute(WatchDogVo watchDogVo, Map<String,String> input) throws BussinessException;

	ResponseVo execute(WatchDogVo watchdog, ControllerSetupVo controllerVo) throws BussinessException;
 
}
