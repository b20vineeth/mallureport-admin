package com.easypick.admin.gallery.bussinesscontroller;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class GalleryBussinessController  implements ControllerBI {

	@Autowired
	private Map<String, Dao> dao;

	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException { 

		ResponseVo vo=null;
		try
		{
			watchdog.setModule(watchdog.getInput().get("param1")); 
			if(watchdog.getInput().containsKey("param2"))
			{
				watchdog.setSubModule(watchdog.getInput().get("param2"));
				return processKey(watchdog,vo);
			}
			else
			{
				return galleryHome(watchdog);
			}

		}
		catch(Exception e)
		{
			throw new BussinessException("404");
		}


	}
	private ResponseVo processKey(WatchDogVo watchdog, ResponseVo vo) throws BussinessException {

		//vo=dao.get("verifySubModule").execute(watchdog);
	 
			watchdog.setKeyLength(watchdog.getInput().size());
			return collectData(vo,watchdog);

		 

	}

	private ResponseVo collectData(ResponseVo vo, WatchDogVo watchdog) throws BussinessException {

		switch(watchdog.getKeyLength())
		{
		case 1:
			return param(watchdog,"OneParameter");
		case 2:
			return param(watchdog,"TwoParameter");
		case 3:
			return param(watchdog,"ThreeParameter");
		case 4:
			return param(watchdog,"FourParameter");
		default:
			throw new BussinessException("404");

		}


	}

	private ResponseVo param(WatchDogVo watchdog, String string) throws BussinessException {

		return dao.get("collectGalleryDataBy"+string).execute(watchdog);
	}
	private ResponseVo galleryHome(WatchDogVo watchdog) throws BussinessException {

		return param(watchdog,"OneParameter");
	}
}
