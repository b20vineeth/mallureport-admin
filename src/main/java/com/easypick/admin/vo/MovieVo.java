package com.easypick.admin.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
 
import com.easypick.framework.utility.vo.AbstractVo;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieVo implements AbstractVo {

	private Integer movieId;
	private String lang;
	private String shortDesc;
	private String description;
	private String thumbnail;
	private String releaseDate;
	private String tag;
	private String cast;
	private String status = "Y";
	private String page;
	private Integer perPage = 0;
	private Integer catId;
	private String releasefrom;
	private String releaseTo;
	private String movieName;
	private String languageName;
	private String movieCode;
	private String movieRate;
	private String releaseFlag;
	private String langId;
	private String priorityFlag;
	private String filterType;
	private String recommenedFlag;
	private String thumbnail2;
	private String certificate;
	private String movieType;
	private String title;
	private String url;
	private List<DataVo> langVos;
	private List<DataVo> castVos;
	private List<DataVo> movieTypeVos;
	
	
	

	public List<DataVo> getLangVos() {
		return langVos;
	}

	public void setLangVos(List<DataVo> langVos) {
		this.langVos = langVos;
	}

	public List<DataVo> getCastVos() {
		return castVos;
	}

	public void setCastVos(List<DataVo> castVos) {
		this.castVos = castVos;
	}

	public List<DataVo> getMovieTypeVos() {
		return movieTypeVos;
	}

	public void setMovieTypeVos(List<DataVo> movieTypeVos) {
		this.movieTypeVos = movieTypeVos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public String getThumbnail2() {
		return thumbnail2;
	}

	public void setThumbnail2(String thumbnail2) {
		this.thumbnail2 = thumbnail2;
	}

	public String getRecommenedFlag() {
		return recommenedFlag;
	}

	public void setRecommenedFlag(String recommenedFlag) {
		this.recommenedFlag = recommenedFlag;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public String getReleaseFlag() {
		return releaseFlag;
	}

	public void setReleaseFlag(String releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieRate() {
		return movieRate;
	}

	public void setMovieRate(String movieRate) {
		this.movieRate = movieRate;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getReleasefrom() {
		return releasefrom;
	}

	public void setReleasefrom(String releasefrom) {
		this.releasefrom = releasefrom;
	}

	public String getReleaseTo() {
		return releaseTo;
	}

	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
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

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public static void populateMovieVo(List<Object[]> object, List<MovieVo> vos) {

		if (Objects.isNull(vos))
			vos = new ArrayList<>();

		MovieVo movieVo;
		if (object.size() > 0) {
			for (Object[] items : object) {
				try {
					movieVo = new MovieVo();
					
					movieVo.setMovieCode(Objects.nonNull(items[0])?items[0].toString():"");
					movieVo.setMovieName(Objects.nonNull(items[1])?items[1].toString():""); 
					movieVo.setCast(Objects.nonNull(items[2])?items[2].toString():"");
					movieVo.setLanguageName(Objects.nonNull(items[3])?items[3].toString():"");
					movieVo.setThumbnail(Objects.nonNull(items[4])?items[4].toString():"");
					movieVo.setThumbnail2(Objects.nonNull(items[5])?items[5].toString():"");
					movieVo.setShortDesc(Objects.nonNull(items[6])?items[6].toString():"");
					try{
						Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(items[7].toString());
						movieVo.setReleaseDate( new SimpleDateFormat("dd MMM, yyyy").format(date1));
					}
					catch(Exception e) {
						
					}
					movieVo.setMovieRate(Objects.nonNull(items[8])?items[8].toString():"0");
					movieVo.setCertificate(Objects.nonNull(items[9])?items[9].toString():"");
					movieVo.setMovieType(Objects.nonNull(items[10])?items[10].toString():"");
					movieVo.setCastVos(DataVo.populatetag(movieVo.getTag()));
					movieVo.setMovieTypeVos(DataVo.populatetag(movieVo.getMovieType()));
					movieVo.setLangVos(DataVo.populatetag(Objects.nonNull(items[11])?items[11].toString():""));
					vos.add(movieVo);

				} catch (Exception e) {

				}
			}

		}
	}

	public static void populateMovieVoforReview(List<Object[]> obj, List<MovieVo> vos) {

		if (Objects.isNull(vos))
			vos = new ArrayList<>();
		MovieVo movieVo;
		if (obj.size() > 0) {
			for (Object[] items : obj) {
				try {
					movieVo = new MovieVo();
					movieVo.setTitle(Objects.nonNull(items[0])?items[0].toString():"");
					movieVo.setThumbnail(Objects.nonNull(items[1])?items[1].toString():"");
					movieVo.setUrl(Objects.nonNull(items[2])?items[2].toString():"");
					movieVo.setShortDesc(Objects.nonNull(items[3])?items[3].toString():"");
					movieVo.setMovieRate(Objects.nonNull(items[4])?items[4].toString():"0");
					try{
						Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(items[5].toString());
						movieVo.setReleaseDate(new SimpleDateFormat("dd MMM, yyyy").format(date1));
					}
					catch(Exception e) {
						
					}
					 
					vos.add(movieVo);
				} catch (Exception e) {

				}
			}
		}
	}

}
