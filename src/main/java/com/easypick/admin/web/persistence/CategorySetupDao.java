package com.easypick.admin.web.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.SettingsDao;
import com.easypick.framework.utility.query.CommonResourceQuery;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CategorySetupDao implements SettingsDao {

	@Override
	public Map<String, String> execute(WatchDogVo watchDogVo, String rem) throws BussinessException {

		Map<String, String> map = null;
		 
		SQLQuery sql = watchDogVo.getSessionString().createSQLQuery(CommonResourceQuery.FIND_CATEGORY_DETAILS)
				.setParameter("module", watchDogVo.getModule())
				.setParameter("submodule", watchDogVo.getSubModule());
		
		List<Object[]> objectList = (List<Object[]>) ((org.hibernate.Query) sql).list();
		if(Objects.nonNull(objectList))
		{
			map=new HashMap<>();
			for(Object[] obj:objectList)
			{
				map.put("allow_content", Objects.nonNull(obj[0])?obj[0].toString():null);
				map.put("allow_home", Objects.nonNull(obj[1])?obj[1].toString():null);
				map.put("allow_pagination",Objects.nonNull(obj[2])?obj[2].toString():null);
				map.put("controller",Objects.nonNull(obj[3])?obj[3].toString():null);
			}
		}
		return map;
	}

}
