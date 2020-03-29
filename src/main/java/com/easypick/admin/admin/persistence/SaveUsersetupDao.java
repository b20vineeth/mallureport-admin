package com.easypick.admin.admin.persistence;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.UserSetup;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
@Repository
public class SaveUsersetupDao  implements Dao {

	 
	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {
		UserSetup userSetup=populateUserSetupVo(vo,watchDogVo);
		new com.easypick.framework.utility.commonUtility.SaveData(userSetup,watchDogVo);
		return updateResult(userSetup,vo);
	}
 
	private ResponseVo updateResult(UserSetup userSetup, ResponseVo vo) {
		UserSetupVo userSetupVo = (UserSetupVo) vo.getObject();
		userSetupVo.setUserId(userSetup.getUserId().toString());
		vo.setResponse(true);
		return vo;
	} 
	private UserSetup populateUserSetupVo(ResponseVo vo, WatchDogVo watchDogVo) {

		UserSetupVo userSetupVo = (UserSetupVo) vo.getObject();
		UserSetup userSetup=new UserSetup();
		//userSetup.setCompanyCode(watchDogVo.getCmpcode().toUpperCase());
	///	userSetup.setCreatedDate(new Date());
		userSetup.setEmail(userSetupVo.getEmail());
		userSetup.setMobile(userSetupVo.getMob());
		//userSetup.setStatus("Y");
		UserSetup upd=new  UserSetup();
		upd.setUserId(1);
	//	userSetup.setUpdatedBy(upd);
		//userSetup.setUpdatedDate(new Date());
		//userSetup.setUpUser(upd);
		userSetup.setUsercode(userSetupVo.getUsername());
		//userSetup.setValidityFrom(new Date());
		long milliseconds = (long) 365 * 24 * 60 * 60 * 1000*10;
        Date validtiy = new Date(new Date().getTime() + milliseconds);
	//	userSetup.setValidityTo(validtiy);
		userSetup.setFirstName(userSetupVo.getFirstName());
		userSetup.setLastName(userSetupVo.getLastName());
		return userSetup;
		
		
	}

}
