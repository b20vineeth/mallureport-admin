package com.easypick.framework.utility.controller;

import java.util.Map;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;

public interface ActionControllerInterface { 

	public ResponseVo performAction(Map<String, String> input) throws BussinessException;
}
