package com.easypick.admin.entity;
 
import java.util.Date; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint; 
@Entity
@Table(name = "homepage_map", uniqueConstraints = { @UniqueConstraint(columnNames = { "type" }) })
public class HomePageMap {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "type", length = 5)
	private String type;
	@Column(name = "data", columnDefinition = "LONGTEXT")
	private String data;
	@Column(name = "status", length = 1)
	private String status = "Y";
	@Column(name = "updated_on", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateon;

	@Column(name = "tagidx", length = 1)
	private Integer tagidx;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

}
