package com.easypick.web.common.bussinesscontroller;

import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;

public interface ControllerInterface {

	ResponseVo getdata(SettingsVo show) throws BussinessException;

	ResponseVo testdata(MovieVo movie) throws BussinessException;
 
	
	  

}
