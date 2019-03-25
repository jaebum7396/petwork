package com.petwork.model.vo;

public class SharingImg {
	
	private int sharingImgNo;
	private String sharingImgAddress;
	private int sharingPostNo;
	
	public SharingImg() {
	}

	public SharingImg(int sharingImgNo, String sharingImgAddress, int sharingPostNo) {
		super();
		this.sharingImgNo = sharingImgNo;
		this.sharingImgAddress = sharingImgAddress;
		this.sharingPostNo = sharingPostNo;
	}

	public int getSharingImgNo() {
		return sharingImgNo;
	}

	public void setSharingImgNo(int sharingImgNo) {
		this.sharingImgNo = sharingImgNo;
	}

	public String getSharingImgAddress() {
		return sharingImgAddress;
	}

	public void setSharingImgAddress(String sharingImgAddress) {
		this.sharingImgAddress = sharingImgAddress;
	}

	public int getSharingPostNo() {
		return sharingPostNo;
	}

	public void setSharingPostNo(int sharingPostNo) {
		this.sharingPostNo = sharingPostNo;
	}

	@Override
	public String toString() {
		return "SharingImg [sharingImgNo=" + sharingImgNo + ", sharingImgAddress=" + sharingImgAddress
				+ ", sharingPostNo=" + sharingPostNo + "]";
	}
	
	

}
