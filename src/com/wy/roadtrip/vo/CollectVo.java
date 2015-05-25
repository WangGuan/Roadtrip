package com.wy.roadtrip.vo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * 收藏列表页
 * @author wangyi
 *
 */
public class CollectVo implements Serializable{

	@Expose
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String image;
	
	private String desc;
	
	private String geo;
	
	private String soptid;
	
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getSoptid() {
		return soptid;
	}

	public void setSoptid(String soptid) {
		this.soptid = soptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
