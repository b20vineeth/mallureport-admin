package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.easypick.admin.vo.SettingsVo;
import com.easypick.framework.utility.vo.AbstractVo;

@Entity
@Table(name = "settings")
public class Settings {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settings_id")
	private Integer settingsId;

	@Column(name = "category") 
	private String category;
	
	@Column(name = "tag")
	private String tag;

	@Column(name = "type", length = 10)
	private String type;
 

	@Column(name = "status", length = 1)
	private String status = "Y";

	 
 

	public Integer getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(Integer settingsId) {
		this.settingsId = settingsId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static List<? extends AbstractVo> formateSettingsVos(List<Settings> settings) {
		List<SettingsVo> settingsVos=new ArrayList<>();
		SettingsVo vo = null;
		for (Settings setting : settings) {
			vo = new SettingsVo();
			vo.setSettingsId(setting.getSettingsId());
			vo.setCategory(setting.getCategory());
			vo.setTag(setting.getTag());
			vo.setType(setting.getType());
			settingsVos.add(vo);
			
		}
		return settingsVos;
	}

	public static void populateSettings(Settings settings, SettingsVo vo) {
		 
		if(Objects.isNull(settings)){
			settings=new Settings();
		}
		if(Objects.nonNull(vo.getCategory()))
		{
			settings.setCategory(vo.getCategory());
		}
		if(Objects.nonNull(vo.getTag()))
		{
			settings.setTag(vo.getTag());
		}
		if(Objects.nonNull(vo.getType()))
		{
			settings.setType(vo.getType());
		}
	}

	 

}
