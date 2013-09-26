package com.coortouch.app.entity;

import java.io.Serializable;

public class BnuzTUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7848772454448223993L;
	
	private int id;
	private String student_id;
	private String username;
	private String password;
	private String id_num;
	private int gnder;
	private String phone;
	private String email;
	private String qq;
	private int status;
	private String admission_time;
	private String create_time;
	private String account;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String id_num) {
		this.id_num = id_num;
	}
	public int getGnder() {
		return gnder;
	}
	public void setGnder(int gnder) {
		this.gnder = gnder;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdmission_time() {
		return admission_time;
	}
	public void setAdmission_time(String admission_time) {
		this.admission_time = admission_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
