package com.easypick.framework.utility.commonUtility;
 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.WatchDogVo;

public class SaveData {
	public SaveData(Object vo, WatchDogVo watchDogVo) throws BussinessException{
		try{
			watchDogVo.getSessionString().save(vo);
		}
		catch(Exception e)
		{
			throw new BussinessException(e.getMessage());
		}
	}

	 

}
