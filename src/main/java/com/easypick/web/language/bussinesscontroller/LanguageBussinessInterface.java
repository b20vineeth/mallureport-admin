package com.easypick.web.language.bussinesscontroller;
 
import com.easypick.admin.vo.LanguageVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface LanguageBussinessInterface {

	ResponseVo saveLanguage(LanguageVo vo)throws BussinessException;

	ResponseVo getLanguageList(LanguageVo vo) throws BussinessException;

	ResponseVo getLanguage(LanguageVo vo) throws BussinessException;
	

}
