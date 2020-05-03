package com.easypick.admin.admin.persistence;
 
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.HomePageMap;
import com.easypick.admin.vo.DataVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class HomePagePostSqlDao implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
	 	StringBuilder sql = new StringBuilder(); 
		HomePageMap homapge = null;
		DataVo datavo=(DataVo) vo.getObject();
		try {
			sql.append("from HomePageMap map where map.status='Y' and map.type=:type ");
			homapge = (HomePageMap) watchDog.getSessionString().createQuery(sql.toString())
					.setParameter("type", datavo.getTag()).getSingleResult();
			vo.setResposeObject(homapge.getData());
		}
		catch(Exception e ){
			 
		}
	 
		return vo;

	}

	 
}
