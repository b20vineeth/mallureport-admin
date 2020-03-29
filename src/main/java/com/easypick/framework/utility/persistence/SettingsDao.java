package com.easypick.framework.utility.persistence;
 

import java.util.Map;

import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.WatchDogVo;
 

public interface SettingsDao {
	  Map<String,String> execute(WatchDogVo watchDogVo, String  input) throws BussinessException;
 
}
