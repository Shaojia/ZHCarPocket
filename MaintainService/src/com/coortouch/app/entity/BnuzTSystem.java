package com.coortouch.app.entity;

import java.io.Serializable;

public class BnuzTSystem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494521857906250338L;
	
	private int id;
	private String name;
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
