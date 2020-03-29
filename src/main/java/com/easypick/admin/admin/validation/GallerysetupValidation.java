package com.easypick.admin.admin.validation;

import com.easypick.framework.utility.commonUtility.DateOperation;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.exception.ErrorVo;
import com.easypick.framework.utility.validation.CommonValidator;
import com.easypick.framework.utility.validation.ValidationBI;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.query.Query;
import com.easypick.admin.vo.GallerySetupVo;
import com.easypick.admin.vo.UserSetupVo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Repository
public class GallerysetupValidation implements ValidationBI {
	WatchDogVo watchDogVo;

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, String type) throws BussinessException {
		this.watchDogVo = watchDogVo;
		return validateForm(populateForm(watchDogVo), type);
	}

	private ResponseVo validateForm(ResponseVo form, String type) throws BussinessException {

		GallerySetupVo gallerySetupVo = (GallerySetupVo) form.getObject();
		switch (type.toLowerCase()) {

		case GallerySetupVo.SAVE:
			return validateSave(gallerySetupVo, form);
		case GallerySetupVo.LIST:
			return validateList(gallerySetupVo, form);
		case GallerySetupVo.BULK_SAVE:
			return validateBulkSave(gallerySetupVo, form);
		case GallerySetupVo.UPDATE:
			return validateUpdate(gallerySetupVo, form);
		case GallerySetupVo.DELETE:
			return validateDelete(gallerySetupVo, form);
		case GallerySetupVo.DEACTIVATE:
			return validateDeactivate(gallerySetupVo, form);
		default:
			throw new BussinessException("404");

		}

	}

	private ResponseVo validateBulkSave(GallerySetupVo gallerySetupVo, ResponseVo form) {
		List<GallerySetupVo> gallerySetupVos=new GalleryXmlParse().parse(gallerySetupVo);
		ResponseVo responseVo=new ResponseVo();
		responseVo.setObjectList(gallerySetupVos);
		 
		List<ErrorVo> errors = new ArrayList<>();
		for(GallerySetupVo galleryVo:gallerySetupVos)
		{
			validatePicUrl(galleryVo, errors);
			validateThumbURL(galleryVo, errors);
			validateTitle(galleryVo, errors);
			validatePageURL(galleryVo, errors);
		}
		responseVo.setErrors(errors);
		return responseVo;
	}

	

	private ResponseVo validateDeactivate(GallerySetupVo gallerySetupVo, ResponseVo form) {
		ResponseVo vo = new ResponseVo();
		vo.setObject(gallerySetupVo);
		return vo;

	}

	private ResponseVo validateDelete(GallerySetupVo gallerySetupVo, ResponseVo form) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseVo validateUpdate(GallerySetupVo gallerySetupVo, ResponseVo form) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseVo validateList(GallerySetupVo gallerySetupVo, ResponseVo form) {

		return form;
	}

	private ResponseVo validateSave(GallerySetupVo gallerySetupVo, ResponseVo form) {

		ResponseVo vo = new ResponseVo();
		List<ErrorVo> errors = new ArrayList<>();

		validatePicUrl(gallerySetupVo, errors);
		validateThumbURL(gallerySetupVo, errors);
		validateTitle(gallerySetupVo, errors);
		validatePageURL(gallerySetupVo, errors);
		vo.setErrors(errors);
		vo.setObject(gallerySetupVo);
		return vo;
	}

	private void validatePageURL(GallerySetupVo gallerySetupVo, List<ErrorVo> errors) {

		ErrorVo error = null;
		if (Objects.nonNull(gallerySetupVo.getPageUrl())) {

			if (validateUrlAlreadyExist(gallerySetupVo.getPageUrl(), "U")) {
				error = new ErrorVo();
				error.setField("PageURL");
				error.setErrorMsg("Already PageURL is Exist");
				error.setValue(gallerySetupVo.getPageUrl());
				errors.add(error);
			} else {
				gallerySetupVo.setPageUrl(gallerySetupVo.getPageUrl().trim().replace(" ", "-"));
			}

		} else {

			error = new ErrorVo();
			error.setField("PageURL");
			error.setErrorMsg("PageURL is Empty");
			error.setValue(gallerySetupVo.getPicUrl());
			errors.add(error);
		}
	}

	private void validateThumbURL(GallerySetupVo gallerySetupVo, List<ErrorVo> errors) {

		ErrorVo error = null;
		if (Objects.nonNull(gallerySetupVo.getThumbUrl())) {

			if (validateUrlAlreadyExist(gallerySetupVo.getThumbUrl(), "T")) {
				error = new ErrorVo();
				error.setField("ThumbURL");
				error.setErrorMsg("Already ThumbURL is Exist");
				error.setValue(gallerySetupVo.getThumbUrl());
				errors.add(error);
			}
			if (validatePicUrl(gallerySetupVo.getThumbUrl(), "T")) {
				error = new ErrorVo();
				error.setField("ThumbURL");
				error.setErrorMsg("Invalid ThumbURL ");
				error.setValue(gallerySetupVo.getThumbUrl());
				errors.add(error);
			}

		} else {

			error = new ErrorVo();
			error.setField("PicUrl");
			error.setErrorMsg("PicUrl is Empty");
			error.setValue(gallerySetupVo.getPicUrl());
			errors.add(error);
		}
	}

	private void validateTitle(GallerySetupVo gallerySetupVo, List<ErrorVo> errors) {
		ErrorVo error = null;
		if (Objects.nonNull(gallerySetupVo.getTitle())) {
			if (gallerySetupVo.getTitle().length() < 15) {
				error = new ErrorVo();

				error.setField("Title");
				error.setErrorMsg("Title have minimum 15 character");
				error.setValue(gallerySetupVo.getTitle());
				errors.add(error);
			}
		} else {
			error = new ErrorVo();
			error.setField("Title");
			error.setErrorMsg("Title is Empty");
			error.setValue(gallerySetupVo.getTitle());
			errors.add(error);
		}

	}

	private void validatePicUrl(GallerySetupVo gallerySetupVo, List<ErrorVo> errors) {
		ErrorVo error = null;
		if (Objects.nonNull(gallerySetupVo.getPicUrl())) {

			if (validateUrlAlreadyExist(gallerySetupVo.getPicUrl(), "P")) {
				error = new ErrorVo();
				error.setField("PicUrl");
				error.setErrorMsg("Already pic is Exist");
				error.setValue(gallerySetupVo.getPicUrl());
				errors.add(error);
			} else {
				gallerySetupVo.setPicUrl(gallerySetupVo.getPicUrl().trim().replace(" ", "-"));
			}

		} else {

			error = new ErrorVo();
			error.setField("PicUrl");
			error.setErrorMsg("PicUrl is Empty");
			error.setValue(gallerySetupVo.getPicUrl());
			errors.add(error);
		}

	}

	private boolean validatePicUrl(String thumbUrl, String string) {
		if (thumbUrl.contains(" ")) {
			return true;
		}
		return false;
	}

	private boolean validateUrlAlreadyExist(String url, String type) {
		StringBuilder queryString01 = new StringBuilder();
		queryString01.append(Query.CHECK_PICURL_EXIST_IN_GALLERYSETUP);
		if ("P".equals(type)) {
			queryString01.append("  and gallery_url = :url ");
		} else if ("U".equals(type)) {
			queryString01.append("  and gallery_page = :url ");
		} else {
			queryString01.append("  and gallery_thumb = :url ");
		}
		SQLQuery q = this.watchDogVo.getSessionString().createSQLQuery(queryString01.toString());
		q.setParameter("cmpcod", this.watchDogVo.getCmpcode().toUpperCase());
		q.setParameter("url", url);
		List<Object[]> gallerySetupVo = (List<Object[]>) ((org.hibernate.Query) q).list();
		int count = 0;
		count = gallerySetupVo.size();

		if (count > 0) {
			return true;
		}
		return false;
	}

	private ResponseVo populateForm(WatchDogVo watchDogVo) {
		Gson gson = new Gson();
		GallerySetupVo gallerySetupVo = null;
		ResponseVo vo = null;
		if (!watchDogVo.getInput().get("data").isEmpty()) {
			gallerySetupVo = gson.fromJson(watchDogVo.getInput().get("data"), GallerySetupVo.class);
			vo = new ResponseVo();
			if(Objects.nonNull(gallerySetupVo.getValidityFrom()))
			{
				try {
					gallerySetupVo.setValidityFromFromat(new DateOperation().formateDate(gallerySetupVo.getValidityFrom()));
				} catch (ParseException e) { 
				}
			}
			if(Objects.nonNull(gallerySetupVo.getValidityto()))
			{
				try {
					gallerySetupVo.setValiditytoFromat(new DateOperation().formateDate(gallerySetupVo.getValidityto()));
				} catch (ParseException e) {  
				}
			}
			
			vo.setObject(gallerySetupVo);
		}

		return vo;

	}

}
