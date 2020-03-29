package com.easypick.web.home.controller;

import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
@Repository
public class CommonExecuteController  implements ControllerBI {

	 
	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {
		ResponseVo vo=new  ResponseVo();
		return vo;
	}
	 

}
