package com.easypick.web.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.Profile;
import com.easypick.admin.vo.MovieVo;
import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.persistence.EventDao;
import com.google.gson.Gson;

@Component
@EventListener(eventKey = "com.admin.saveMovie", priority = "1")
public class SaveProfileDetailsEvent implements EventImpl {

	@Autowired
	private EventDao dao;

	Gson gson;

	@Override
	public void execute(WatchDogVo watchdog, ResponseVo vo) {

		MovieVo movievo = (MovieVo) vo.getObject();
		String[] cast = movievo.getCast().split(",");
		Profile profile = null;
		for (String names : cast) {
			int values = 0;
			try {
				values = Integer.parseInt(names);
			} catch (Exception e) {
				profile = new Profile();
				profile.setProfileCode("t-" + names.replace(" ", "-"));
				profile.setProfileName(names);
				watchdog.getSessionString().saveOrUpdate(profile);
				dao.updateMovieCast(vo.getId(), watchdog, profile);
			}
		}

	}

}
