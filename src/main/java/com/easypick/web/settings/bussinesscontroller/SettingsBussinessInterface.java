package com.easypick.web.settings.bussinesscontroller;
    
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface SettingsBussinessInterface {
 
 

	ResponseVo saveSettingsVo(SettingsVo settings)throws BussinessException;

	ResponseVo getAllSettings(SettingsVo show) throws BussinessException;

	 
	

}
