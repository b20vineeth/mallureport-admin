package com.easypick.web.profile.persistence;
  
import com.easypick.admin.vo.ProfileVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface ProfileDao {

	ResponseVo profileSave(WatchDogVo watchdog, ProfileVo vo) throws BussinessException;

	ResponseVo getProfileList(WatchDogVo watchdog, ProfileVo vo) throws BussinessException;

	ResponseVo getProfile(WatchDogVo watchdog, ProfileVo vo) throws BussinessException;
 
}
