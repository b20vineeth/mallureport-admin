package com.easypick.web.settings.persistence;
   
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface SettingsDao {
 

	ResponseVo getAllSettings(WatchDogVo watchdog, SettingsVo vo) throws BussinessException;

	ResponseVo saveSettings(WatchDogVo watchdog, SettingsVo vo)  throws BussinessException;
 
}
