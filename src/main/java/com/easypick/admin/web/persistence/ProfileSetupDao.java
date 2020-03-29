package com.easypick.admin.web.persistence;
 
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.ControllerSetupVo;
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.persistence.WebDao;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
@Repository
public class ProfileSetupDao  implements WebDao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, Map<String, String> input) throws BussinessException {

		switch (input.get("count")) {
		case "1":
			return homePage(watchDogVo);
		default:
			return redirectkeyMap(watchDogVo);

		} 
	}

	private ResponseVo redirectkeyMap(WatchDogVo watchDogVo) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseVo homePage(WatchDogVo watchDogVo) {
		 
		return null;
	}

	@Override
	public ResponseVo execute(WatchDogVo watchdog, ControllerSetupVo controllerVo) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	 
	 

}
