package com.easypick.admin.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.easypick.framework.utility.vo.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataVo implements AbstractVo {
	
	private String tag;
	private Integer value;
	private List<? extends AbstractVo> suggestions;
	private String link;
	
	
	private String langCode;
	private String langId;
	
	
	
	
	
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<? extends AbstractVo> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<? extends AbstractVo> suggestions) {
		this.suggestions = suggestions;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	 
	
	public static void populateDataVo(List<Object[]> object, List<DataVo> vos) {
		DataVo dataVo;
		for (Object[] items : object) {
			dataVo = new DataVo();
			try {
				if (Objects.nonNull(items[0]))
					dataVo.setValue(Integer.parseInt(items[0].toString()));
				if (Objects.nonNull(items[1]))
					dataVo.setTag(items[1].toString());
				vos.add(dataVo);
			} catch (Exception e) {

			}
		}
	}
	public static List<DataVo> populatetag(String tag) {
		String[] item=null;
		List<DataVo>  dataVos=new ArrayList<>();
		DataVo vo=new DataVo();
		if(Objects.nonNull(tag) && tag.trim().length()>0)
		{
			String[] tags=tag.split(",");
			for(String key : tags)
			{
				item=key.split("#");
				vo.setLink(item[0]);
				vo.setTag(item[1]);
				dataVos.add(vo);
			}
			return dataVos;
		}
	  return null;
	}

}
