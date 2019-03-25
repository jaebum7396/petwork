package com.petwork.model.vo;

public class ParcelImg {
	private int parcelImgNo;
	private String parcelImgAddress;
	private int parcelPostNo;
	private String parcelMainImg;
	public ParcelImg() {
	}
	public ParcelImg(int parcelImgNo, String parcelImgAddress, int parcelPostNo, String parcelMainImg) {
		this.parcelImgNo = parcelImgNo;
		this.parcelImgAddress = parcelImgAddress;
		this.parcelPostNo = parcelPostNo;
		this.parcelMainImg = parcelMainImg;
	}
	public int getParcelImgNo() {
		return parcelImgNo;
	}
	public void setParcelImgNo(int parcelImgNo) {
		this.parcelImgNo = parcelImgNo;
	}
	public String getParcelImgAddress() {
		return parcelImgAddress;
	}
	public void setParcelImgAddress(String parcelImgAddress) {
		this.parcelImgAddress = parcelImgAddress;
	}
	public int getParcelPostNo() {
		return parcelPostNo;
	}
	public void setParcelPostNo(int parcelPostNo) {
		this.parcelPostNo = parcelPostNo;
	}
	public String getParcelMainImg() {
		return parcelMainImg;
	}
	public void setParcelMainImg(String parcelMainImg) {
		this.parcelMainImg = parcelMainImg;
	}
	@Override
	public String toString() {
		return "ParcelImg [parcelImgNo=" + parcelImgNo + ", parcelImgAddress=" + parcelImgAddress + ", parcelPostNo="
				+ parcelPostNo + ", parcelMainImg=" + parcelMainImg + "]";
	}
	
}
