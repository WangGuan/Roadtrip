package com.wy.roadtrip.vo;

/**
 * 线路信息
 * 
 * @author wangyi
 * 
 */
public class LineInfoVo {

	private boolean isShown;

	private boolean isSelected;

	private String title;

	public boolean isShown() {
		return isShown;
	}

	public void setShown(boolean isShown) {
		this.isShown = isShown;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
