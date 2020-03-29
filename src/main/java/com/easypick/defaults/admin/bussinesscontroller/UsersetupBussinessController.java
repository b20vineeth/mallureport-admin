package com.easypick.defaults.admin.bussinesscontroller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.validation.ValidationBI;
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class UsersetupBussinessController implements ControllerBI {

	private String USERDAO_KEY = "UsersetupDao";
	@Autowired
	private Map<String, Dao> dao;

	@Autowired
	private Map<String, ValidationBI> validation;

	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {

		ResponseVo vo = null;
		try {
			watchdog.setModule(watchdog.getInput().get("param1").toLowerCase());
			if (watchdog.getInput().containsKey("param2")) {
				watchdog.setSubModule(watchdog.getInput().get("param2").toLowerCase());
				return processKey(watchdog, vo);
			} else {
				throw new BussinessException("404");
			}

		} catch (Exception e) {
			throw new BussinessException("404");
		}

	}

	private ResponseVo processKey(WatchDogVo watchdog, ResponseVo vo) throws BussinessException {

		watchdog.setKeyLength(watchdog.getInput().size());
		return collectData(vo, watchdog);

	}

	private ResponseVo collectData(ResponseVo vo, WatchDogVo watchdog) throws BussinessException {

		switch (watchdog.getSubModule()) {
		case UserSetupVo.CREATE:
			return param(watchdog, UserSetupVo.CREATE);
		case UserSetupVo.SAVE:
			return param(watchdog, UserSetupVo.SAVE);
		case UserSetupVo.LIST:
			return param(watchdog, UserSetupVo.LIST);
		case UserSetupVo.DEACTIVATE:
			return param(watchdog, UserSetupVo.DEACTIVATE);
		case UserSetupVo.UPDATE:
			return param(watchdog, UserSetupVo.UPDATE);
		case UserSetupVo.DELETE:
			return param(watchdog, UserSetupVo.DELETE);
		default:
			throw new BussinessException("404");

		}

	}

	private ResponseVo param(WatchDogVo watchdog, String req) throws BussinessException {

		StringBuilder controlerKey = new StringBuilder();
		controlerKey.append(req);
		controlerKey.append("UsersetupDao");

		ResponseVo vo = new ResponseVo();
		if (req.equalsIgnoreCase(UserSetupVo.CREATE)) {
			vo.setScreenMode("usersetup/create");

		} else if (req.equalsIgnoreCase(UserSetupVo.SAVE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getErrors().isEmpty())
				return dao.get(controlerKey.toString()).execute(watchdog, vo);
		} else if (req.equalsIgnoreCase(UserSetupVo.LIST)) {
			vo = validateAndUpdateForm(watchdog, req);
			vo.setScreenMode("usersetup/List");
			return dao.get(controlerKey.toString()).execute(watchdog, vo);

		}
		else if (req.equalsIgnoreCase(UserSetupVo.DEACTIVATE)) {
			vo = validateAndUpdateForm(watchdog, req);
			 return dao.get(controlerKey.toString()).execute(watchdog, vo);

		} else if (req.equalsIgnoreCase(UserSetupVo.UPDATE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getObjectList().size() == 0)
				return dao.get(controlerKey.toString()).execute(watchdog, vo);

		} else if (req.equalsIgnoreCase(UserSetupVo.DELETE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getObjectList().size() == 0)
				return dao.get(controlerKey.toString()).execute(watchdog, vo);

		}
		return vo;
	}

	private ResponseVo validateAndUpdateForm(WatchDogVo watchdog, String req) throws BussinessException {

		return validation.get("usersetupValidation").execute(watchdog, req);
	}

}
