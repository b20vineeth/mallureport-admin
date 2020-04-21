package com.easypick.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "language_map")
public class LanguageMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapid")
	private Integer mapid;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language", nullable = false)
	private Language language;

	@Column(name = "status")
	private String status = "Y";

	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static List<LanguageMap> populate(String lang) {
		List<LanguageMap> langmaps = new ArrayList<>();
		LanguageMap langMap;
		String[] langs = lang.split(",");
		Language language;
		for (String lan : langs) {
			
			try {
				langMap = new LanguageMap();
				language = new Language();
				language.setId(Integer.parseInt(lan));
				langMap.setLanguage(language);
				langmaps.add(langMap);
			} catch (Exception e) {
				 
			}
			
			
		}
		return langmaps;
	}

}
