package com.easypick.admin.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.easypick.framework.utility.vo.AbstractVo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileVo implements AbstractVo {

	private Integer profileId;
	private String films;
	private String dateofBirth;
	private String description;
	private String shortDesc;
	private String thumbnail;
	private String tag;
	private String status = "Y";
	private String page;
	private Integer catTypeId;
	private Integer perPage = 0;
	private String profileName;
	private String profileCode;
	private String gender;
	private String language;
	private String movie;
	private String catName;
	private List<DataVo> langVos;

	public List<DataVo> getLangVos() {
		return langVos;
	}

	public void setLangVos(List<DataVo> langVos) {
		this.langVos = langVos;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getFilms() {
		return films;
	}

	public void setFilms(String films) {
		this.films = films;
	}

	public String getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getCatTypeId() {
		return catTypeId;
	}

	public void setCatTypeId(Integer catTypeId) {
		this.catTypeId = catTypeId;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public static void populate(List<Object[]> object, List<ProfileVo> vos) {

		if (Objects.isNull(vos))
			vos = new ArrayList<>();

		ProfileVo profileVo;
		if (object.size() > 0) {
			for (Object[] items : object) {
				try {
					// profile_name,profile_code,thumbnail
					profileVo = new ProfileVo();
					profileVo.setProfileName(items[0].toString());
					profileVo.setProfileCode(items[1].toString());
					profileVo.setThumbnail(items[2].toString());
					profileVo.setLangVos(DataVo.populatetag(Objects.nonNull(items[3]) ? items[3].toString() : ""));
					vos.add(profileVo);
				} catch (Exception e) {

				}
			}
		}

	}

}
