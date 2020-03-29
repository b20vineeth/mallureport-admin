package com.easypick.admin.web.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Repository; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.SettingsDao;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class DirectionSetupDao implements SettingsDao {

	@Override
	public Map<String, String> execute(WatchDogVo watchDogVo, String directionCode) throws BussinessException {

		Map<String, String> map = new HashMap<>();
		/*DirectionSetup direction=null;
		 
		  direction = (DirectionSetup) watchDogVo.getSessionString()
				.createQuery("from DirectionSetup" + " where (directionCode= :directionCode or directionCode=:common) and status='Y' "
						+ "and companyCode=:companyCode and validityFrom<:validityFrom and  validityTo>=:validityTo order by preference desc").setMaxResults(1)
				.setParameter("directionCode", directionCode).setParameter("companyCode", watchDogVo.getCmpcode()).setParameter("common", "common")
				.setParameter("validityFrom", new Date()).setParameter("validityTo", new Date()).getSingleResult();
		 
		map.put("controller", direction.getDirectionPath());*/
		return map;
	}

}
