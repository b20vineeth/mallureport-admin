package com.easypick.admin.admin.persistence;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface Dao {

	ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException;

}
