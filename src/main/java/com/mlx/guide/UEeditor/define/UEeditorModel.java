package com.mlx.guide.UEeditor.define;

import java.io.Serializable;
/**
 * 
 * 
 * @author QiQi-04-PC
 * 
 * 
 * UEeditor 数据模版 
 *
 */


public class UEeditorModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String original;//源图片名字
	private String size;  //图片大小
	private String state; //上传的状态 SUCCESS、 FAIL
	private String title; //图片上传后的名字
	private String type; //图片的类型 后缀名 如 。jpg
	private String url;  //上传后图片的地址 全地址或者相对地址
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
