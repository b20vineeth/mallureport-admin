package com.easypick.web.settings.persistence;
 
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Settings;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class SettingsSqlDao implements SettingsDao {

	@Override
	public ResponseVo getAllSettings(WatchDogVo watchdog, SettingsVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();

		sql.append("from Settings settings where settings.status='Y' and settings.type=:settings");

		sql.append(" order by settings.settingsId desc ");
		ResponseVo responseVo = new ResponseVo();
		Page page1 = new Page();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		List<Settings> settings = query.setFirstResult(0).setMaxResults(25).getResultList();
		responseVo.setObjectList(Settings.formateSettingsVos(settings));

		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo saveSettings(WatchDogVo watchdog, SettingsVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		ResponseVo responseVo = new ResponseVo();
		Page page1 = new Page();
		Settings settings = null;
		if (Objects.nonNull(vo.getSettingsId())) {
			sql.append("from Settings settings where settings.settingsId=:settingsId");
			Query query = watchdog.getSessionString().createQuery(sql.toString());
			query.setParameter("settingsId", vo.getSettingsId());
			try {
				settings = (Settings) query.getSingleResult();
				Settings.populateSettings(settings, vo);

			} catch (Exception e) {
				Settings.populateSettings(settings, vo);
			}
		}

		responseVo.setResponse(true);
		return responseVo;
	}


	 

}
