package com.easypick.admin.admin.validation;

import com.easypick.framework.utility.commonUtility.DateOperation;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.exception.ErrorVo;
import com.easypick.framework.utility.validation.CommonValidator;
import com.easypick.framework.utility.validation.ValidationBI;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.admin.query.Query;
import com.easypick.admin.vo.UserSetupVo;

@Repository
public class UsersetupValidation implements ValidationBI {
	WatchDogVo watchDogVo;

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, String type) throws BussinessException {
		this.watchDogVo = watchDogVo;
		return validateForm(populateForm(watchDogVo), type);
	}

	private ResponseVo validateForm(ResponseVo form, String type) throws BussinessException {

		UserSetupVo userSetupVo = (UserSetupVo) form.getObject();
		switch (type.toLowerCase()) {

		case UserSetupVo.SAVE:
			return validateSave(userSetupVo, form);
		case UserSetupVo.LIST:
			return validateList(userSetupVo, form);
		case UserSetupVo.UPDATE:
			return validateUpdate(userSetupVo, form);
		case UserSetupVo.DELETE:
			return validateDelete(userSetupVo, form);
		case UserSetupVo.DEACTIVATE:
			return validateDeactivate(userSetupVo, form);
		default:
			throw new BussinessException("404");

		}

	}

	private ResponseVo validateDeactivate(UserSetupVo userSetupVo, ResponseVo form) {
		ResponseVo vo = new ResponseVo();
		vo.setObject(userSetupVo);
		return vo;

	}

	private ResponseVo validateDelete(UserSetupVo vo, ResponseVo form) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseVo validateUpdate(UserSetupVo vo, ResponseVo form) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseVo validateList(UserSetupVo vo, ResponseVo form) {

		return form;
	}

	private ResponseVo validateSave(UserSetupVo userSetupVo, ResponseVo form) {

		ResponseVo vo = new ResponseVo();
		List<ErrorVo> errors = new ArrayList<>();
		validateEmail(userSetupVo, errors);
		validateUserName(userSetupVo, errors);
		validateName(userSetupVo, errors, "F");
		validateName(userSetupVo, errors, "L");
		validatePhone(userSetupVo, errors);
		validatePassword(userSetupVo, errors);
		vo.setErrors(errors);
		vo.setObject(userSetupVo);
		return vo;
	}

	private void validatePassword(UserSetupVo userSetupVo, List<ErrorVo> errors) {
		Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
		Pattern lowerCasePatten = Pattern.compile("[a-z ]");
		Pattern digitCasePatten = Pattern.compile("[0-9 ]");

		ErrorVo error = new ErrorVo();
		if (Objects.nonNull(userSetupVo.getPassword())) {
			if (!userSetupVo.getPassword().equals(userSetupVo.getConfirmPassword())) {
				error = new ErrorVo();
				error.setField("Password");
				error.setErrorMsg("password and confirm password does not match");
				error.setValue(userSetupVo.getPassword());
				errors.add(error);

			}
			if (userSetupVo.getPassword().length() < 8) {
				error = new ErrorVo();
				error.setField("Password");
				error.setErrorMsg("Password lenght must have alleast 8 character !!");
				error.setValue(userSetupVo.getPassword());
				errors.add(error);
			}

			if (!UpperCasePatten.matcher(userSetupVo.getPassword()).find()) {
				error = new ErrorVo();
				error.setField("Password");
				error.setErrorMsg("Password must have atleast one uppercase character !!");
				error.setValue(userSetupVo.getPassword());
				errors.add(error);
			}
			if (!lowerCasePatten.matcher(userSetupVo.getPassword()).find()) {
				error = new ErrorVo();
				error.setField("Password");
				error.setErrorMsg("Password must have atleast one lowercase character !!");
				error.setValue(userSetupVo.getPassword());
				errors.add(error);
			}
			if (!digitCasePatten.matcher(userSetupVo.getPassword()).find()) {
				error = new ErrorVo();
				error.setField("Password");
				error.setErrorMsg("Password must have atleast one digit character !!");
				error.setValue(userSetupVo.getPassword());
				errors.add(error);
			}
		} else {
			error = new ErrorVo();
			error.setField("Password");
			error.setErrorMsg("Invalid Password");
			error.setValue(userSetupVo.getPassword());
			errors.add(error);
		}

	}

	private void validatePhone(UserSetupVo userSetupVo, List<ErrorVo> errors) {
		ErrorVo error = new ErrorVo();
		if (!userSetupVo.getMob().isEmpty()) {
			boolean result = new CommonValidator().validateMobile(userSetupVo.getMob());
			if (!result) {
				error.setField("Mobile");
				error.setErrorMsg("Invalid Mobile number");
				error.setValue(userSetupVo.getMob());
				errors.add(error);
			} else {
				if (validateUserMobileAlreadyExist(userSetupVo.getUsername())) {
					error.setField("Mobile");
					error.setErrorMsg("Already Mobile Number Exist");
					error.setValue(userSetupVo.getMob());
					errors.add(error);
				}
			}
		} else {
			error.setField("Username");
			error.setErrorMsg("  Username is Mandatory");
			error.setValue(userSetupVo.getUsername());
			errors.add(error);
		}
	}

	private boolean validateUserMobileAlreadyExist(String mobile) {

		StringBuilder queryString01 = new StringBuilder();
		queryString01.append(Query.CHECK_MOBILE_EXIST_IN_USERSETUP);
		SQLQuery q = this.watchDogVo.getSessionString().createSQLQuery(queryString01.toString());
		q.setParameter("cmpcod", this.watchDogVo.getCmpcode().toUpperCase());
		q.setParameter("mobile", mobile);
		List<Object[]> usercodeVo = (List<Object[]>) ((org.hibernate.Query) q).list();
		int count = 0;
		count = usercodeVo.size();

		if (count > 0) {
			return true;
		}
		return false;
	}

	private void validateName(UserSetupVo userSetupVo, List<ErrorVo> errors, String type) {

		ErrorVo error = new ErrorVo();
		boolean result = new CommonValidator().validateName(userSetupVo, type);
		if (!result) {
			String message = type.equals("F") ? "First name" : "Last name";
			error.setField(type.equals("F") ? "firstname" : "Lastname");
			error.setErrorMsg("Invalid " + message);
			error.setValue(type.equals("F") ? userSetupVo.getFirstName() : userSetupVo.getLastName());
			errors.add(error);
		}
	}

	private void validateUserName(UserSetupVo userSetupVo, List<ErrorVo> errors) {
		ErrorVo error = new ErrorVo();
		if (!userSetupVo.getUsername().isEmpty()) {
			boolean result = new CommonValidator().validateUsername(userSetupVo.getUsername());
			if (!result) {
				error.setField("Username");
				error.setErrorMsg("Invalid Username");
				error.setValue(userSetupVo.getUsername());
				errors.add(error);
			} else {
				if (validateUserNameAlreadyExist(userSetupVo.getUsername())) {
					error.setField("Username");
					error.setErrorMsg("Already Username Exist");
					error.setValue(userSetupVo.getUsername());
					errors.add(error);
				}
			}
		} else {
			error.setField("Username");
			error.setErrorMsg("  Username is Mandatory");
			error.setValue(userSetupVo.getUsername());
			errors.add(error);
		}

	}

	private boolean validateUserNameAlreadyExist(String username) {

		StringBuilder queryString01 = new StringBuilder();
		queryString01.append(Query.CHECK_USERNAME_EXIST_IN_USERSETUP);
		SQLQuery q = this.watchDogVo.getSessionString().createSQLQuery(queryString01.toString());
		q.setParameter("cmpcod", this.watchDogVo.getCmpcode().toUpperCase());
		q.setParameter("usercode", username);
		List<Object[]> usercodeVo = (List<Object[]>) ((org.hibernate.Query) q).list();
		int count = 0;
		count = usercodeVo.size();

		if (count > 0) {
			return true;
		}
		return false;

	}

	private void validateEmail(UserSetupVo userSetupVo, List<ErrorVo> errors) {

		ErrorVo error = new ErrorVo();
		boolean result = new CommonValidator().validateEmail(userSetupVo.getEmail());
		if (!result) {
			error.setField("email");
			error.setErrorMsg("Invalid Email");
			error.setValue(userSetupVo.getEmail());
			errors.add(error);
		} else {
			if (validateEmailAlreadyExist(userSetupVo.getEmail())) {
				error.setField("email");
				error.setErrorMsg("Already Email Exist");
				error.setValue(userSetupVo.getEmail());
				errors.add(error);
			}
		}
	}

	private Boolean validateEmailAlreadyExist(String email) {

		StringBuilder queryString01 = new StringBuilder();
		queryString01.append(Query.CHECK_EMAIL_EXIST_IN_USERSETUP);

		SQLQuery q = this.watchDogVo.getSessionString().createSQLQuery(queryString01.toString());
		q.setParameter("cmpcod", this.watchDogVo.getCmpcode().toUpperCase());
		q.setParameter("email", email);
		List<Object[]> emailVo = (List<Object[]>) ((org.hibernate.Query) q).list();
		int count = 0;
		count = emailVo.size();

		if (count > 0) {
			return true;
		}
		return false;

	}

	private ResponseVo populateForm(WatchDogVo watchDogVo) {
		Gson gson = new Gson();
		UserSetupVo userSetupVo = null;
		ResponseVo vo = null;
		if (!watchDogVo.getInput().get("data").isEmpty()) {
			userSetupVo = gson.fromJson(watchDogVo.getInput().get("data"), UserSetupVo.class);
			vo = new ResponseVo();
			if (Objects.nonNull(userSetupVo.getValidityto())) {
				try {
					userSetupVo.setValiditytoFromat(new DateOperation().formateDate(userSetupVo.getValidityto(),"yyyy-mm-dd"));
				} catch (ParseException e) {
				}
			}

			if (Objects.nonNull(userSetupVo.getValidityFrom())) {
				try {
					userSetupVo.setValidityFromFromat(new DateOperation().formateDate(userSetupVo.getValidityFrom(),"yyyy-mm-dd"));
				} catch (ParseException e) {
				}
			}
			vo.setObject(userSetupVo);
		}

		return vo;

	}

}
