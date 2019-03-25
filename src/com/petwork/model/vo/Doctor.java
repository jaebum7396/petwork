package com.petwork.model.vo;

public class Doctor {
	private int doctorNo;
	private String doctorId;
	private String doctorPwd;
	private String doctorLicense;
	private String doctorName;
	private String doctorHospital;
	private String doctorAddress;
	private String doctorPhone;
	private String doctorImg;
	private String doctorX;
	private String doctorY;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doctor(int doctorNo, String doctorId, String doctorPwd, String doctorLicense, String doctorName,
			String doctorHospital, String doctorAddress, String doctorPhone, String doctorImg) {
		super();
		this.doctorNo = doctorNo;
		this.doctorId = doctorId;
		this.doctorPwd = doctorPwd;
		this.doctorLicense = doctorLicense;
		this.doctorName = doctorName;
		this.doctorHospital = doctorHospital;
		this.doctorAddress = doctorAddress;
		this.doctorPhone = doctorPhone;
		this.doctorImg = doctorImg;
	}
	
	public Doctor(int doctorNo, String doctorId, String doctorPwd, String doctorLicense, String doctorName,
			String doctorHospital, String doctorAddress, String doctorPhone, String doctorImg, String doctorX,
			String doctorY) {
		super();
		this.doctorNo = doctorNo;
		this.doctorId = doctorId;
		this.doctorPwd = doctorPwd;
		this.doctorLicense = doctorLicense;
		this.doctorName = doctorName;
		this.doctorHospital = doctorHospital;
		this.doctorAddress = doctorAddress;
		this.doctorPhone = doctorPhone;
		this.doctorImg = doctorImg;
		this.doctorX = doctorX;
		this.doctorY = doctorY;
	}
	public int getDoctorNo() {
		return doctorNo;
	}
	public void setDoctorNo(int doctorNo) {
		this.doctorNo = doctorNo;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorPwd() {
		return doctorPwd;
	}
	public void setDoctorPwd(String doctorPwd) {
		this.doctorPwd = doctorPwd;
	}
	public String getDoctorLicense() {
		return doctorLicense;
	}
	public void setDoctorLicense(String doctorLicense) {
		this.doctorLicense = doctorLicense;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorHospital() {
		return doctorHospital;
	}
	public void setDoctorHospital(String doctorHospital) {
		this.doctorHospital = doctorHospital;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getDoctorImg() {
		return doctorImg;
	}
	public void setDoctorImg(String doctorImg) {
		this.doctorImg = doctorImg;
	}
	public String getDoctorX() {
		return doctorX;
	}
	public void setDoctorX(String doctorX) {
		this.doctorX = doctorX;
	}
	public String getDoctorY() {
		return doctorY;
	}
	public void setDoctorY(String doctorY) {
		this.doctorY = doctorY;
	}




}