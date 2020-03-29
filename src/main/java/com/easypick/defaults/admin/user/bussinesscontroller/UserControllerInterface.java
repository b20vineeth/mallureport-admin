package com.easypick.defaults.admin.user.bussinesscontroller;

import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;

public interface UserControllerInterface {

	String validateUser(UserSetupVo vo) throws BussinessException;

	

}
