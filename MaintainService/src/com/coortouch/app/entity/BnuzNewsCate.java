package com.coortouch.app.entity;

import java.io.Serializable;

public class BnuzNewsCate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7848772454448223993L;
	
	private int pid;	
	private int id;	
	private String title;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
}
