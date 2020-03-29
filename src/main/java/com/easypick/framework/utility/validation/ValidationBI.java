package com.easypick.framework.utility.validation;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface ValidationBI {
	ResponseVo execute(WatchDogVo watchDogVo, String req) throws BussinessException;
}
