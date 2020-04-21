package com.easypick.admin.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.ProfileVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.web.events.vo.CastDataVo;

@Entity
@Table(name = "profile", uniqueConstraints = { @UniqueConstraint(columnNames = { "profile_code" }) })
public class Profile {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profileid")
	private Integer profileId;

	@Column(name = "profile_code")
	private String profileCode;

	@Column(name = "profile_name")
	private String profileName;

	@Column(name = "films")
	private String films;
	@Column(name = "gender", length = 1)
	private String gender;

	@Column(name = "dob", columnDefinition = "DATE", nullable = true)
	private Date dateofBirth;

	@Column(name = "decription")
	private String decription;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "language")
	private String language;

	@Column(name = "tag")
	private String tag;

	@Column(name = "status")
	private String status = "Y";
	
	@Column(name = "updated_on", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;
	
	@Column(name = "tagidx" ,length=1)
	private Integer tagidx;

	
	
	
	
	public Date getUpdateon() {
		return updateon;
	}

	public void setUpdateon(Date updateon) {
		this.updateon = updateon;
	}

	 

	 

	public Integer getTagidx() {
		return tagidx;
	}

	public void setTagidx(Integer tagidx) {
		this.tagidx = tagidx;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFilms() {
		return films;
	}

	public void setFilms(String films) {
		this.films = films;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static List<? extends AbstractVo> formateProfileVos(List<Profile> profileVos) {
		List<ProfileVo> vos = new ArrayList<>();
		ProfileVo vo = null;
		for (Profile profile : profileVos) {
			vo = new ProfileVo();
			if (Objects.nonNull(profile.getDateofBirth())) {
				String datevalue = new SimpleDateFormat("dd/MM/yyyy").format(profile.getDateofBirth()).toString();
				vo.setDateofBirth(datevalue);
			}
			vo.setFilms(profile.getFilms());
			vo.setTag(StringUitity.removeHashTags(profile.getTag())); 
			vo.setGender(profile.getGender());
			vo.setProfileId(profile.getProfileId());
			vo.setLanguage(profile.getLanguage());
			vo.setProfileName(profile.getProfileName());
			vos.add(vo);
		}

		return vos;
	}

	public static Profile populateProfileVo(ProfileVo vo) {
		Profile profile = new Profile();
		profile.setDecription(vo.getDescription());
		profile.setFilms(StringUitity.getTag(vo.getFilms()));
		profile.setShortDesc(vo.getShortDesc());
		profile.setStatus("Y");
		profile.setTag(StringUitity.convertToTags(vo.getTag()));
		profile.setThumbnail(vo.getThumbnail());
		profile.setGender(vo.getGender());
		profile.setProfileCode(vo.getProfileCode());
		profile.setProfileName(vo.getProfileName());
		profile.setLanguage(vo.getLanguage());
		try {
			profile.setDateofBirth(new SimpleDateFormat("dd/MM/yyyy").parse(vo.getDateofBirth()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (vo.getProfileId() != 0)
			profile.setProfileId(vo.getProfileId());
		profile.setUpdateon(new Date());
		profile.setTagidx(0);
		return profile;
	}

	public static Object formateProfileVo(Profile profile) {

		ProfileVo vo = new ProfileVo();
		if(Objects.nonNull(profile.getDateofBirth()))
			vo.setDateofBirth(new SimpleDateFormat("dd/MM/yyyy").format(profile.getDateofBirth()).toString());
		vo.setTag(profile.getTag().replace("#", ""));
		vo.setGender(profile.getGender());
		vo.setThumbnail(profile.getThumbnail());
		vo.setDescription(profile.getDecription());
		vo.setShortDesc(profile.getShortDesc());
		vo.setFilms(profile.getFilms().replace("#", ""));
		vo.setProfileCode(profile.getProfileCode());
		vo.setProfileName(profile.getProfileName());
		vo.setProfileId(profile.getProfileId());
		vo.setLanguage(profile.getLanguage());
		return vo;
	}

	public static List<CastDataVo> formateCastDataVo(List<Profile> profileVos) {
		List<CastDataVo> vos = new ArrayList<>();
		CastDataVo vo = null;
		if (Objects.nonNull(profileVos)) {
			for (Profile profile : profileVos) {
				vo = new CastDataVo();
				vo.setProfileCode(profile.getProfileCode());
				vo.setProfileName(profile.getProfileName());
				vo.setThumbUrl(profile.getThumbnail());
				vos.add(vo);
			}
		}
		return vos;

	}

}
