package com.easypick.framework.utility.bussinessInterface;
 

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface ControllerBI {
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException;
}
