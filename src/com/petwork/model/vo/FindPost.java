package com.petwork.model.vo;

import java.util.Date;

public class FindPost {
	private int findPostNo;
	private String memberId;
	private String memberPhone;
	private int petNo;
	private String findPostTitle;
	private String findPostContent;
	private String findPostMissingAddress;
	private Date findPostMissingDate;
	private char findPostReward; //사례금 여부(Y:있음, N:없음, C:합의)
	private int findPostSum; //사례 금액
	private String findPostImgAddress;
	private Date findPostDate;
	private char findPostYn;
	private String animalName;
	private String petIdentifyNo;
	private String petName;
	private char petGender;
	private char raceCode;
	
	public FindPost() {
		super();
	}

	public FindPost(int findPostNo, String memberId, String memberPhone, int petNo, String findPostTitle,
			String findPostContent, String findPostMissingAddress, Date findPostMissingDate, char findPostReward,
			int findPostSum, String findPostImgAddress, Date findPostDate, char findPostYn, String animalName,
			String petIdentifyNo, String petName, char petGender, char raceCode) {
		super();
		this.findPostNo = findPostNo;
		this.memberId = memberId;
		this.memberPhone = memberPhone;
		this.petNo = petNo;
		this.findPostTitle = findPostTitle;
		this.findPostContent = findPostContent;
		this.findPostMissingAddress = findPostMissingAddress;
		this.findPostMissingDate = findPostMissingDate;
		this.findPostReward = findPostReward;
		this.findPostSum = findPostSum;
		this.findPostImgAddress = findPostImgAddress;
		this.findPostDate = findPostDate;
		this.findPostYn = findPostYn;
		this.animalName = animalName;
		this.petIdentifyNo = petIdentifyNo;
		this.petName = petName;
		this.petGender = petGender;
		this.raceCode = raceCode;
	}

	public int getFindPostNo() {
		return findPostNo;
	}

	public void setFindPostNo(int findPostNo) {
		this.findPostNo = findPostNo;
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

	public int getPetNo() {
		return petNo;
	}

	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}

	public String getFindPostTitle() {
		return findPostTitle;
	}

	public void setFindPostTitle(String findPostTitle) {
		this.findPostTitle = findPostTitle;
	}

	public String getFindPostContent() {
		return findPostContent;
	}

	public void setFindPostContent(String findPostContent) {
		this.findPostContent = findPostContent;
	}

	public String getFindPostMissingAddress() {
		return findPostMissingAddress;
	}

	public void setFindPostMissingAddress(String findPostMissingAddress) {
		this.findPostMissingAddress = findPostMissingAddress;
	}

	public Date getFindPostMissingDate() {
		return findPostMissingDate;
	}

	public void setFindPostMissingDate(Date findPostMissingDate) {
		this.findPostMissingDate = findPostMissingDate;
	}

	public char getFindPostReward() {
		return findPostReward;
	}

	public void setFindPostReward(char findPostReward) {
		this.findPostReward = findPostReward;
	}

	public int getFindPostSum() {
		return findPostSum;
	}

	public void setFindPostSum(int findPostSum) {
		this.findPostSum = findPostSum;
	}

	public String getFindPostImgAddress() {
		return findPostImgAddress;
	}

	public void setFindPostImgAddress(String findPostImgAddress) {
		this.findPostImgAddress = findPostImgAddress;
	}

	public Date getFindPostDate() {
		return findPostDate;
	}

	public void setFindPostDate(Date findPostDate) {
		this.findPostDate = findPostDate;
	}

	public char getFindPostYn() {
		return findPostYn;
	}

	public void setFindPostYn(char findPostYn) {
		this.findPostYn = findPostYn;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getPetIdentifyNo() {
		return petIdentifyNo;
	}

	public void setPetIdentifyNo(String petIdentifyNo) {
		this.petIdentifyNo = petIdentifyNo;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public char getPetGender() {
		return petGender;
	}

	public void setPetGender(char petGender) {
		this.petGender = petGender;
	}

	public char getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(char raceCode) {
		this.raceCode = raceCode;
	}

	
}
