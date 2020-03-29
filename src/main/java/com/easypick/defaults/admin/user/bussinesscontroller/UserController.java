package com.easypick.defaults.admin.user.bussinesscontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.user.persistence.UserDao;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 
@Repository
public class UserController implements UserControllerInterface {
	@Autowired
	private Map<String, UserDao> dao;
	@Override
	public String validateUser(UserSetupVo vo) throws BussinessException {
		 
		return dao.get("userSqlDao").validateUser(vo);
	}
	

}
