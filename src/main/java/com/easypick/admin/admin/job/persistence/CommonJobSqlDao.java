package com.easypick.admin.admin.job.persistence;
 
import java.util.Date;  
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.HomePageMap;  
import com.easypick.admin.vo.HomePageSetupVo; 
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class CommonJobSqlDao implements CommonDao {
	 
	@Override
	public void saveHomegeData(WatchDogVo watchdog, HomePageSetupVo homePageSetupVo) {
		StringBuilder sql = new StringBuilder();
		HomePageMap homapge = null;
		try {
			sql.append("from HomePageMap lang where lang.status='Y' and lang.type=:type ");
			homapge = (HomePageMap) watchdog.getSessionString().createQuery(sql.toString())
					.setParameter("type", homePageSetupVo.getType()).getSingleResult();
			homapge.setUpdateon(new Date());
			homapge.setTagidx(0);
			homapge.setData(homePageSetupVo.getData());
		} catch (Exception e) {
			homapge = new HomePageMap();
			homapge.setUpdateon(new Date());
			homapge.setTagidx(0);
			homapge.setType(homePageSetupVo.getType());
			homapge.setData(homePageSetupVo.getData());
		}
		watchdog.getSessionString().saveOrUpdate(homapge);
		
	}

}
