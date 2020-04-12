package com.easypick.web.language.persistence;
 
import com.easypick.admin.vo.LanguageVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface LanguageDao {

	ResponseVo saveLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

	ResponseVo getLanguageList(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

	ResponseVo getLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException;

 
 




}
