package com.wy.roadtrip.vo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * 我的收藏--线路
 * 
 * @author wangyi
 * 
 */
public class CollectRouteVo implements Serializable {
	
	@Expose
	private static final long serialVersionUID = 1L;
	
	private String lid;
	
	private String image;
	
	private String line;
	
	private String title;
	
	private String type;
	
	private String url;

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
