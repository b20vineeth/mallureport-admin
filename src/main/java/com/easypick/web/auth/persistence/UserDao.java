package com.easypick.web.auth.persistence;

import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;

public interface UserDao {

	UserSetupVo validateUser(UserSetupVo vo) throws BussinessException;

}
