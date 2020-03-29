package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.LanguageVo;
import com.easypick.framework.utility.vo.AbstractVo;  

@Entity
@Table(name = "langsetup", uniqueConstraints = { @UniqueConstraint(columnNames = { "langcod"}) } )
public class Language   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	 
	@Column(name = "langnam")
	private String langName;
	
	@Column(name = "langcod")
	private String langCode;
	
	@Column(name = "status")
	private String status="Y";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Object formateVo(Language language) {
		LanguageVo vo = new LanguageVo();
		vo.setLangCode(language.getLangCode());
		vo.setLangName(language.getLangName());
		vo.setLangId(language.getId());
		return vo;
	}

	public static List<? extends AbstractVo> formateVos(List<Language> langVos) {
		List<LanguageVo> vos = new ArrayList<>();
		LanguageVo vo = null;
		for (Language language : langVos) {
			vo = new LanguageVo();
			vo.setLangCode(language.getLangCode());
			vo.setLangName(language.getLangName());
			vo.setLangId(language.getId());
			vos.add(vo);
		}

		return vos;
	}

	public static Language populateLangauageVo(LanguageVo vo) {
		Language language = new Language();
		language.setLangCode(vo.getLangCode());
		language.setLangName(vo.getLangName());
		if (vo.getLangId() != 0)
			language.setId(vo.getLangId());

		language.setStatus("Y");
		return language;
	}

	 
	 
  
}
