package com.easypick.web.profile.bussinesscontroller;
   
import com.easypick.admin.vo.ProfileVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface ProfileBussinessInterface {
 

	ResponseVo profileSave(ProfileVo vo) throws BussinessException;

	ResponseVo getProfileList(ProfileVo vo) throws BussinessException;

	ResponseVo getProfile(ProfileVo vo) throws BussinessException;

	 
	

}
