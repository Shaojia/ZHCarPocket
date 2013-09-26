package com.coortouch.app.entity;

import java.io.Serializable;

public class AppSetting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6000981284957007673L;
	private String CompanyLogo;
	private int LanguageType;
	private int ShareType;
	public String getCompanyLogo() {
		return CompanyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		CompanyLogo = companyLogo;
	}
	public int getLanguageType() {
		return LanguageType;
	}
	public void setLanguageType(int languageType) {
		LanguageType = languageType;
	}
	public int getShareType() {
		return ShareType;
	}
	public void setShareType(int shareType) {
		ShareType = shareType;
	}
	
	
	
}
