package com.easypick.web.events;

import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface EventImpl {
	void execute(WatchDogVo watchdog, ResponseVo vo);
}
