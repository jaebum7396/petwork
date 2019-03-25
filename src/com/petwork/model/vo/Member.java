package com.petwork.model.vo;

import java.sql.Date;

public class Member 
{
	private String id;
	private String pw;
	private String name;
	private Date birth;
	private char gender;
	private String phone;
	private String email;
	private String address;
	private char memberYN;
	private Date enrollDate;
	private char adminYN;
	
	public Member() {
		super();
	}

	public Member(String id, String pw, String name, Date birth, char gender, String phone, String email,
			String address, char memberYN, Date enrollDate, char adminYN) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.memberYN = memberYN;
		this.enrollDate = enrollDate;
		this.adminYN = adminYN;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getMemberYN() {
		return memberYN;
	}

	public void setMemberYN(char memberYN) {
		this.memberYN = memberYN;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public char getAdminYN() {
		return adminYN;
	}

	public void setAdminYN(char adminYN) {
		this.adminYN = adminYN;
	}
	
	

}
