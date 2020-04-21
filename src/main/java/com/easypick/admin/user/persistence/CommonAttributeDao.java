package com.easypick.admin.user.persistence;

import java.util.List;

import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CommonAttributeDao {

	ResponseVo saveGalleryDetails(WatchDogVo watchdog, MovieVo movie)  throws BussinessException;

	ResponseVo getdata(WatchDogVo watchdog, SettingsVo vo) throws BussinessException;

	 
	List<DataVo> getCastAutoComplete(WatchDogVo watchdog, String cast) throws BussinessException;

	List<DataVo> getFilmAutoComplete(WatchDogVo watchdog, String films)  throws BussinessException;

	List<DataVo> getLanguageAutoComplete(WatchDogVo watchdog, String langId) throws BussinessException;

	List<DataVo> getMovieTypeAutoComplete(WatchDogVo watchdog, String movieType) throws BussinessException;

	 
 




}
