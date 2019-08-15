package com.easypick.framework.utility.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.Query.CommonResourceQuery;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.mapper.ConfigurationMapper; 
import com.easypick.framework.utility.vo.ConfigurationVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class VerifySubModule implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo) throws BussinessException {
		 
		StringBuffer Query=new  StringBuffer();
		Query.append(CommonResourceQuery.FIND_CONGIURATION); 
		ResponseVo response=new ResponseVo();
		if(watchDogVo.getModule()!=null)
			Query.append(" and config.module=:module  "); 
		if(watchDogVo.getSubModule()!=null)
			Query.append(" and  config.submodule=:submodule  "); 
		 
		
		Query.append(" and validfrm<=CURRENT_DATE and validto>=CURRENT_DATE");
			
		
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(Query.toString()); 
		List<Object[]> configurations= (List<Object[]>)((org.hibernate.Query) q).list(); 
		ConfigurationVo vo=null;
		for(Object[] configuration: configurations){
			
			vo=new ConfigurationMapper().mapper(configuration);
			 
		}
		if(Objects.nonNull(vo))
		{
			response.setObject(vo);
			response.setResponse(true);
		}
		else
		{
			response.setResponse(false);
		}
		return response;
	}

}
