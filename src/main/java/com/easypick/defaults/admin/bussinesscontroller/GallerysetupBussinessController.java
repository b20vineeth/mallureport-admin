package com.easypick.defaults.admin.bussinesscontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.bussinessInterface.ControllerBI;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao;
import com.easypick.framework.utility.validation.ValidationBI;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.ResponseVo;

@Repository
public class GallerysetupBussinessController implements ControllerBI {

	@Autowired
	private Map<String, Dao> dao;

	@Autowired
	private Map<String, ValidationBI> validation;

	@Override
	public ResponseVo execute(WatchDogVo watchdog) throws BussinessException {

		ResponseVo vo = null;
		try {
			watchdog.setModule(watchdog.getInput().get("param1").toLowerCase());
			if (watchdog.getInput().containsKey("param2")) {
				watchdog.setSubModule(watchdog.getInput().get("param2").toLowerCase());
				return processKey(watchdog, vo);
			} else {
				throw new BussinessException("404");
			}

		} catch (Exception e) {
			throw new BussinessException("404");
		}

	}

	private ResponseVo processKey(WatchDogVo watchdog, ResponseVo vo) throws BussinessException {

		watchdog.setKeyLength(watchdog.getInput().size());
		return collectData(vo, watchdog);

	}

	private ResponseVo collectData(ResponseVo vo, WatchDogVo watchdog) throws BussinessException {

		switch (watchdog.getSubModule()) {
		case UserSetupVo.CREATE:
			return param(watchdog, GallerySetupVo.CREATE);
		case GallerySetupVo.SAVE:
			return param(watchdog, GallerySetupVo.SAVE);
		case GallerySetupVo.UPLOAD_EXCEL:
			return param(watchdog, GallerySetupVo.UPLOAD_EXCEL);
		case GallerySetupVo.BULK_UPLOAD:
			return param(watchdog, GallerySetupVo.BULK_UPLOAD);
		case GallerySetupVo.BULK_SAVE:
			return param(watchdog, GallerySetupVo.BULK_SAVE);
		case GallerySetupVo.LIST:
			return param(watchdog, GallerySetupVo.LIST);
		case GallerySetupVo.DEACTIVATE:
			return param(watchdog, GallerySetupVo.DEACTIVATE);
		case GallerySetupVo.UPDATE:
			return param(watchdog, GallerySetupVo.UPDATE);
		case GallerySetupVo.DELETE:
			return param(watchdog, GallerySetupVo.DELETE);
		default:
			throw new BussinessException("404");

		}

	}

	private ResponseVo param(WatchDogVo watchdog, String req) throws BussinessException {

		StringBuilder controlerKey = constructControlKey(req);
		ResponseVo vo = new ResponseVo();
		if (req.equalsIgnoreCase(GallerySetupVo.CREATE)) {
			vo.setScreenMode("Gallerysetup/create");

		} else if (req.equalsIgnoreCase(GallerySetupVo.UPLOAD_EXCEL)) {
			vo.setScreenMode("Gallerysetup/UploadExcel");

		} else if (req.equalsIgnoreCase(GallerySetupVo.BULK_UPLOAD)) {
			vo.setScreenMode("Gallerysetup/BulkUpload");

		} else if (req.equalsIgnoreCase(GallerySetupVo.SAVE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getErrors().isEmpty())
				return dao.get(controlerKey.toString()).execute(watchdog, vo);
		} else if (req.equalsIgnoreCase(GallerySetupVo.BULK_SAVE)) {
			vo = validateAndUpdateForm(watchdog, req);
			controlerKey = constructControlKey(GallerySetupVo.SAVE);
			if (vo.getErrors().isEmpty()) {
				List<? extends AbstractVo> galleryvo = vo.getObjectList();
				for (AbstractVo gallerySetupVo : galleryvo) {
					 vo.setObject(gallerySetupVo);
					 dao.get(controlerKey.toString()).execute(watchdog, vo);
				}

			}

		} else if (req.equalsIgnoreCase(GallerySetupVo.LIST)) {
			vo = validateAndUpdateForm(watchdog, req);
			vo.setScreenMode("Gallerysetup/List");
			return dao.get(controlerKey.toString()).execute(watchdog, vo);

		} else if (req.equalsIgnoreCase(GallerySetupVo.DEACTIVATE)) {
			vo = validateAndUpdateForm(watchdog, req);
			return dao.get(controlerKey.toString()).execute(watchdog, vo);

		} else if (req.equalsIgnoreCase(GallerySetupVo.UPDATE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getObjectList().size() == 0)
				return dao.get(controlerKey.toString()).execute(watchdog, vo);

		} else if (req.equalsIgnoreCase(GallerySetupVo.DELETE)) {
			vo = validateAndUpdateForm(watchdog, req);
			if (vo.getObjectList().size() == 0)
				return dao.get(controlerKey.toString()).execute(watchdog, vo);

		}
		return vo;
	}

	private StringBuilder constructControlKey(String req) {
		StringBuilder controlerKey = new StringBuilder();
		controlerKey.append(req);
		controlerKey.append("GallerysetupDao");
		return controlerKey;
	}

	private ResponseVo validateAndUpdateForm(WatchDogVo watchdog, String req) throws BussinessException {

		return validation.get("gallerysetupValidation").execute(watchdog, req);
	}

}
