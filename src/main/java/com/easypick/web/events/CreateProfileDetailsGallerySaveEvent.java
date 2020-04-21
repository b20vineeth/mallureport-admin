package com.easypick.web.events;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.Profile;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.persistence.EventDao;
import com.google.gson.Gson;

@Component
@EventListener(eventKey = "com.admin.saveGallery", priority = "1")
public class CreateProfileDetailsGallerySaveEvent implements EventImpl {

	@Autowired
	private EventDao dao;

	Gson gson;

	@Override
	public void execute(WatchDogVo watchdog, ResponseVo vo) {

		List<GalleryVo> galleryVos = (List<GalleryVo>) vo.getObjectList();
		for (GalleryVo galleryVo : galleryVos) {
			if (Objects.nonNull(galleryVo.getProfile())) {
		}
			String[] profiles = galleryVo.getProfile().split(",");

			Profile profile = null;
			for (String names : profiles) {
				int values = 0;
				try {
					values = Integer.parseInt(names);
				} catch (Exception e) {

					profile = new Profile();
					profile.setProfileCode("t-" + names.replace(" ", "-"));
					profile.setProfileName(names);
					 
					watchdog.getSessionString().saveOrUpdate(profile);
					dao.updateGalleryProfile(galleryVo.getGalleryId(), watchdog, profile);

				}
			}

		}
	}
}

 
