package com.easypick.framework.utility.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
@Repository
public class SystemSqlDao implements SystemDao{

	@Override
	public List<String> getSystemParameter(WatchDogVo watchdog, String key) {
		 
		
		return null;
	}

}
