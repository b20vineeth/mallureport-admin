package com.easypick.framework.utility.persistence;

import java.util.List;

import com.easypick.framework.utility.vo.WatchDogVo;

public interface SystemDao {

	List<String> getSystemParameter(WatchDogVo watchdog, String key);

}
