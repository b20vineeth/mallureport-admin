package com.easypick.admin.user.persistence;

import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;

public interface UserDao {

	String validateUser(UserSetupVo vo) throws BussinessException;

}
