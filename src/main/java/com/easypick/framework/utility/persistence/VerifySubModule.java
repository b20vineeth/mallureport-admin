package com.easypick.framework.utility.persistence;
 import java.util.List;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.mapper.ConfigurationMapper;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.vo.ConfigurationVo;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class VerifySubModule implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo) throws BussinessException {
		 
		StringBuilder queryString01=new  StringBuilder();
		queryString01.append(CommonResourceQuery.FIND_CONGIURATION); 
		ResponseVo response=new ResponseVo();
		if(watchDogVo.getModule()!=null)
		{
				queryString01.append(" and config.module=:module  "); 
				 
		}
		if(watchDogVo.getSubModule()!=null)
		{
			queryString01.append(" and  config.submodule=:submodule  "); 
		}
		queryString01.append(" and validfrm<=CURRENT_DATE and validto>=CURRENT_DATE");
			
		
		SQLQuery q =   watchDogVo.getSessionString().createSQLQuery(queryString01.toString()); 
		q.setParameter("module", watchDogVo.getModule().toLowerCase());
		q.setParameter("submodule", watchDogVo.getSubModule().toLowerCase());
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
