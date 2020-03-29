package com.easypick.admin.admin.persistence;

import org.springframework.stereotype.Repository;
import com.easypick.admin.entity.UserSetup;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class DeactivateUsersetupDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {
		updateUserSetup(watchDogVo, vo);
		vo.setResponse(true);
		return vo;
	}

	private void updateUserSetup(WatchDogVo watchDogVo, ResponseVo vo) {

		UserSetupVo userVo = (UserSetupVo) vo.getObject();
		UserSetup user = (UserSetup) watchDogVo.getSessionString().get(UserSetup.class, Integer.parseInt(userVo.getUserId()));
	//	user.setStatus("N");
		watchDogVo.getSessionString().merge(user);
	}

}
