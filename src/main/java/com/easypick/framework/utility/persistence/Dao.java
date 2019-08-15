package com.easypick.framework.utility.persistence;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface Dao {
	ResponseVo execute(WatchDogVo watchDogVo) throws BussinessException;
}
