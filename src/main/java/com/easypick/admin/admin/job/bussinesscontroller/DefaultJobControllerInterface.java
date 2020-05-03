package com.easypick.admin.admin.job.bussinesscontroller;
 
import com.easypick.admin.vo.JobVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 
public interface DefaultJobControllerInterface {
 

ResponseVo saveJob(JobVo vo, UserSetupVo userSetupVo)  throws BussinessException;

ResponseVo getJobList(JobVo vo, UserSetupVo userSetupVo)  throws BussinessException;

ResponseVo findJob(JobVo vo, UserSetupVo userSetupVo)  throws BussinessException;
 

JobVo findJob(String jobCode) throws BussinessException;

	

}
