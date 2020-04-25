package com.easypick.admin.admin.job.bussinesscontroller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.job.persistence.CommonDao;
import com.easypick.admin.admin.job.persistence.ProfileDao;
import com.easypick.admin.vo.HomePageSetupVo;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
@Repository
public class ProfileHomePageBussinessController implements JobBI {

	@Autowired
	ProfileDao dao;
	@Autowired
	CommonDao commonDao;
	@Override
	public ResponseVo execute(WatchDogVo watchdog) {
	 
	ResponseVo vo=new ResponseVo(); 
	Map<String,Map<String,List<? extends AbstractVo>>> object=  new HashMap<>();
	 if (Objects.isNull(vo.getJsonobj())) {
			vo.setJsonobj(object);
	 } 
	Map<String, List<? extends AbstractVo>> actress=dao.getActress(watchdog);
	Map<String, List<? extends AbstractVo>> actor=dao.getActor(watchdog);
	Map<String, List<? extends AbstractVo>> popular=dao.getPopularProfile(watchdog);
	vo.getJsonobj().put("popular", popular);
	vo.getJsonobj().put("actor", actor);
	vo.getJsonobj().put("actress", actress);
	ObjectMapper mapper = new ObjectMapper();
	try {
		String jsonobj=mapper.writeValueAsString(vo);
		HomePageSetupVo homePageSetupVo=new HomePageSetupVo();
		homePageSetupVo.setData(jsonobj);
		homePageSetupVo.setType("PRO");
		commonDao.saveHomegeData(watchdog,homePageSetupVo);
	} catch (JsonProcessingException e) {
		 e.printStackTrace();
	}
	
	 return vo;
	
	}
	 

}
