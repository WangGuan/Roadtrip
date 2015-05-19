package com.wy.roadtrip.vo;

/**
 * 
 * @Des: 我的消息
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月19日 上午11:53:35
 */
public class MyMsgVo {
	
	private boolean isSend;
	
	private long createTime;
	
	private String name;
	
	private String Content;
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
