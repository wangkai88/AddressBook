package com.ehl.addressbook.domain;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private String sex;
	private String email;
	private Date birthday;
	
	public User() {
		super();
		
	}
	
	

	public User(String name, String sex, String email, Date birthday) {
		super();
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.birthday = birthday;
	}



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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "User [birthday=" + birthday + ", email=" + email + ", id=" + id
				+ ", name=" + name + ", sex=" + sex + "]";
	}
	
}
