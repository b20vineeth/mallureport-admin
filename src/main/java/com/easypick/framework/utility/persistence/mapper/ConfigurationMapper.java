package com.easypick.framework.utility.persistence.mapper;

import com.easypick.framework.utility.vo.ConfigurationVo;

public class ConfigurationMapper {

	public ConfigurationVo mapper(Object[] configuration) {
		 ConfigurationVo vo=new ConfigurationVo();
		 vo.setConfigurationId(Integer.parseInt(configuration[0].toString()));
		 vo.setControllerClass(configuration[1].toString());
		 vo.setModule(configuration[2].toString());
		 vo.setSubModule(configuration[3].toString());
		return vo;
	}

 
	 

}
