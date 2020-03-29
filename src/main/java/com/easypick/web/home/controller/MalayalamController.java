package com.easypick.web.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.admin.vo.CinemaVo;
import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.admin.vo.ModuleVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.CommonDao;
import com.easypick.framework.utility.persistence.WebDao;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class MalayalamController implements ControllerBI {

	@Autowired
	private Map<String, CommonDao> dao;
	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {
		switch (watchdog.getType().toLowerCase()) {
		case ModuleVo.CONTENT:
			return content(watchdog);
		case ModuleVo.HOME:
			return home(watchdog);
		case ModuleVo.SUB_MODULE:
			return submodule(watchdog);
		 
		default:
			throw new BussinessException("404");

		}
	}

	private ResponseVo submodule(WatchDogVo watchdog) throws BussinessException {

		switch (watchdog.getSubModule().toLowerCase()) {
		case ModuleVo.CINEMA:
			return subModuleCinema(watchdog);
		case ModuleVo.NEWS:
			return subModuleNews(watchdog);
		case ModuleVo.RUNNING_MOVIES:
			return subModuleRunningMovies(watchdog);
		case ModuleVo.UPCOMMING_MOVIES:
			return subModuleRunningMovies(watchdog);
		case ModuleVo.CAST:
			return subModuleCast(watchdog);
		default:
			throw new BussinessException("404");

		}
	}

	private ResponseVo subModuleCast(WatchDogVo watchdog) {
		 
		return null;
	}

	private ResponseVo subModuleRunningMovies(WatchDogVo watchdog) {

		return null;
	}

	private ResponseVo subModuleNews(WatchDogVo watchdog) {
		 return null;
	}

	private ResponseVo subModuleCinema(WatchDogVo watchdog) throws BussinessException {
		ResponseVo vo= dao.get("cinemaSqlDao").execute(watchdog);
		watchdog.setType(ModuleVo.RUNNING_MOVIES);
		ResponseVo runningMovies=dao.get("cinemaSqlDao").execute(watchdog); 
		Map<String,List<?  extends AbstractVo>> objectMap=new HashMap<>();
		List<CinemaVo> cinemaVo=(List<CinemaVo>) runningMovies.getObjectList();
		objectMap.put(ModuleVo.RUNNING_MOVIES, cinemaVo);
		vo.setObjectMapList(objectMap);
		vo.setScreenMode("/common/List");
		return vo;
	}

	private ResponseVo news(WatchDogVo watchdog) {
		 return null;
	}

	private ResponseVo cinema(WatchDogVo watchdog) {
		return null;
	}

	private ResponseVo home(WatchDogVo watchdog) throws BussinessException {
		Page page = watchdog.getPage();
		if (Objects.nonNull(page) && page.isPages()) {
			throw new BussinessException("404");
		} else {
			 

		}
		return null;
	}

	private ResponseVo content(WatchDogVo watchdog) throws BussinessException {

		throw new BussinessException("404");
	}

}
