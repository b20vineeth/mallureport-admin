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
public class GalleryController  implements ControllerBI {
	 
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
					processKey(watchdog,vo);
				}
				else
				{
					galleryHome(vo);
				}

			}
			catch(Exception e)
			{
				throw new BussinessException("404");
			}
			return vo;

		}
	private void processKey(WatchDogVo watchdog, ResponseVo vo) throws BussinessException {
		 
		 vo=dao.get("verifySubModule").execute(watchdog);
		 
	}

	private void galleryHome(ResponseVo vo) {
	 

	}
}
