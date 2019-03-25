package com.petwork.model.vo;

import java.util.Date;

public class ProtectPost {
	private int protectPostNo; //게시글 번호
	private String memberId; //아이디
	private String memberPhone; //전화번호
	private String protectPostTitle;
	private String protectPostContent;
	private String protectPostFindAddress; //찾은장소
	private Date protectPostFindDate; //찾은날짜
	private String protectPostImgAddress;
	private Date protectPostDate; //게시글 작성일
	private char protectPostYn; //돌려준 경우 Y
	private String protectPetIdentifyNo; //인식번호
	private char protectPostPetGender; //성별
	private char raceCode; 
	private String animalNo;
	private String animalName;
	private Date protectPostGiveDate; //돌려준 날짜 
	private String protectPostGiveMemberId; //돌려준 회원 ID

	public ProtectPost() {
		super();
	}

	public ProtectPost(int protectPostNo, String memberId, String memberPhone, String protectPostTitle,
			String protectPostContent, String protectPostFindAddress, Date protectPostFindDate,
			String protectPostImgAddress, Date protectPostDate, char protectPostYn, String protectPetIdentifyNo,
			char protectPostPetGender, char raceCode, String animalNo, String animalName, Date protectPostGiveDate,
			String protectPostGiveMemberId) {
		super();
		this.protectPostNo = protectPostNo;
		this.memberId = memberId;
		this.memberPhone = memberPhone;
		this.protectPostTitle = protectPostTitle;
		this.protectPostContent = protectPostContent;
		this.protectPostFindAddress = protectPostFindAddress;
		this.protectPostFindDate = protectPostFindDate;
		this.protectPostImgAddress = protectPostImgAddress;
		this.protectPostDate = protectPostDate;
		this.protectPostYn = protectPostYn;
		this.protectPetIdentifyNo = protectPetIdentifyNo;
		this.protectPostPetGender = protectPostPetGender;
		this.raceCode = raceCode;
		this.animalNo = animalNo;
		this.animalName = animalName;
		this.protectPostGiveDate = protectPostGiveDate;
		this.protectPostGiveMemberId = protectPostGiveMemberId;
	}

	public int getProtectPostNo() {
		return protectPostNo;
	}

	public void setProtectPostNo(int protectPostNo) {
		this.protectPostNo = protectPostNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getProtectPostTitle() {
		return protectPostTitle;
	}

	public void setProtectPostTitle(String protectPostTitle) {
		this.protectPostTitle = protectPostTitle;
	}

	public String getProtectPostContent() {
		return protectPostContent;
	}

	public void setProtectPostContent(String protectPostContent) {
		this.protectPostContent = protectPostContent;
	}

	public String getProtectPostFindAddress() {
		return protectPostFindAddress;
	}

	public void setProtectPostFindAddress(String protectPostFindAddress) {
		this.protectPostFindAddress = protectPostFindAddress;
	}

	public Date getProtectPostFindDate() {
		return protectPostFindDate;
	}

	public void setProtectPostFindDate(Date protectPostFindDate) {
		this.protectPostFindDate = protectPostFindDate;
	}

	public String getProtectPostImgAddress() {
		return protectPostImgAddress;
	}

	public void setProtectPostImgAddress(String protectPostImgAddress) {
		this.protectPostImgAddress = protectPostImgAddress;
	}

	public Date getProtectPostDate() {
		return protectPostDate;
	}

	public void setProtectPostDate(Date protectPostDate) {
		this.protectPostDate = protectPostDate;
	}

	public char getProtectPostYn() {
		return protectPostYn;
	}

	public void setProtectPostYn(char protectPostYn) {
		this.protectPostYn = protectPostYn;
	}

	public String getProtectPetIdentifyNo() {
		return protectPetIdentifyNo;
	}

	public void setProtectPetIdentifyNo(String protectPetIdentifyNo) {
		this.protectPetIdentifyNo = protectPetIdentifyNo;
	}

	public char getProtectPostPetGender() {
		return protectPostPetGender;
	}

	public void setProtectPostPetGender(char protectPostPetGender) {
		this.protectPostPetGender = protectPostPetGender;
	}

	public char getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(char raceCode) {
		this.raceCode = raceCode;
	}

	public String getAnimalNo() {
		return animalNo;
	}

	public void setAnimalNo(String animalNo) {
		this.animalNo = animalNo;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public Date getProtectPostGiveDate() {
		return protectPostGiveDate;
	}

	public void setProtectPostGiveDate(Date protectPostGiveDate) {
		this.protectPostGiveDate = protectPostGiveDate;
	}

	public String getProtectPostGiveMemberId() {
		return protectPostGiveMemberId;
	}

	public void setProtectPostGiveMemberId(String protectPostGiveMemberId) {
		this.protectPostGiveMemberId = protectPostGiveMemberId;
	}

	@Override
	public String toString() {
		return "ProtectPost [protectPostNo=" + protectPostNo + ", memberId=" + memberId + ", memberPhone=" + memberPhone
				+ ", protectPostTitle=" + protectPostTitle + ", protectPostContent=" + protectPostContent
				+ ", protectPostFindAddress=" + protectPostFindAddress + ", protectPostFindDate=" + protectPostFindDate
				+ ", protectPostImgAddress=" + protectPostImgAddress + ", protectPostDate=" + protectPostDate
				+ ", protectPostYn=" + protectPostYn + ", protectPetIdentifyNo=" + protectPetIdentifyNo
				+ ", protectPostPetGender=" + protectPostPetGender + ", raceCode=" + raceCode + ", animalNo=" + animalNo
				+ ", animalName=" + animalName + ", protectPostGiveDate=" + protectPostGiveDate
				+ ", protectPostGiveMemberId=" + protectPostGiveMemberId + "]";
	}
}
