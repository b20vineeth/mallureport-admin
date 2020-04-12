package com.easypick.admin.user.persistence;

import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CommonAttributeDao {

	ResponseVo saveGalleryDetails(WatchDogVo watchdog, MovieVo movie)  throws BussinessException;

	ResponseVo getdata(WatchDogVo watchdog, SettingsVo vo) throws BussinessException;

	 
 




}