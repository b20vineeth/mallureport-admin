package com.easypick.admin.admin.job.controller;

import com.easypick.framework.utility.exception.BussinessException; 

public interface JobControllerInterface {
 
	void execute(String controller) throws BussinessException;

}
