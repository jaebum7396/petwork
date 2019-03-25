package com.petwork.model.vo;

public class FreeImg {
	
	private int FreeImgNo;
	private String FreeImgAddress;
	private int FreePostNo;
	
	public FreeImg() {
	}

	public FreeImg(int freeImgNo, String freeImgAddress, int freePostNo) {
		super();
		FreeImgNo = freeImgNo;
		FreeImgAddress = freeImgAddress;
		FreePostNo = freePostNo;
	}

	public int getFreeImgNo() {
		return FreeImgNo;
	}

	public void setFreeImgNo(int freeImgNo) {
		FreeImgNo = freeImgNo;
	}

	public String getFreeImgAddress() {
		return FreeImgAddress;
	}

	public void setFreeImgAddress(String freeImgAddress) {
		FreeImgAddress = freeImgAddress;
	}

	public int getFreePostNo() {
		return FreePostNo;
	}

	public void setFreePostNo(int freePostNo) {
		FreePostNo = freePostNo;
	}

	@Override
	public String toString() {
		return "FreeImg [FreeImgNo=" + FreeImgNo + ", FreeImgAddress=" + FreeImgAddress + ", FreePostNo=" + FreePostNo
				+ "]";
	}
	
	

}
