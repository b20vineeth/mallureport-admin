package com.easypick.web.auth.bussinesscontroller;
 
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException; 
public interface UserControllerInterface {

	UserSetupVo validateUser(UserSetupVo vo) throws BussinessException;

	

}
