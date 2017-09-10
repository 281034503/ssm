package com.cheer.ssm.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4010690753624188405L;
	
	private Long id;
	private String userName;
	private int credite;
	private String password;
	private Date lastLogin;
	private String lastIp;
	

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", credite=" + credite + ", password=" + password
				+ ", lastLogin=" + lastLogin + ", lastIp=" + lastIp + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCredite() {
		return credite;
	}
	public void setCredite(int credite) {
		this.credite = credite;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	
}
