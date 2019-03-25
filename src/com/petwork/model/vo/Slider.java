package com.petwork.model.vo;

import java.sql.Date;

public class Slider {
	
	private String findPostNo;
	private String memberId;
	private String petNo;
	private String findPostTitle;
	private String findPostContent;
	private String findPostMissingAddress;
	private Date findPostMissingDate;
	private char findPostReward;
	private String findPostSum;
	private String findPostImageAddress;
	private Date findPostDate;
	private char findPostYn;	
		
	public Slider() {
		super();
	}

	public Slider(String findPostNo, String memberId, String petNo, String findPostTitle, String findPostContent,
			String findPostMissingAddress, Date findPostMissingDate, char findPostReward, String findPostSum,
			String findPostImageAddress, Date findPostDate, char findPostYn) {
		super();
		this.findPostNo = findPostNo;
		this.memberId = memberId;
		this.petNo = petNo;
		this.findPostTitle = findPostTitle;
		this.findPostContent = findPostContent;
		this.findPostMissingAddress = findPostMissingAddress;
		this.findPostMissingDate = findPostMissingDate;
		this.findPostReward = findPostReward;
		this.findPostSum = findPostSum;
		this.findPostImageAddress = findPostImageAddress;
		this.findPostDate = findPostDate;
		this.findPostYn = findPostYn;
	}

	public String getFindPostNo() {
		return findPostNo;
	}

	public void setFindPostNo(String findPostNo) {
		this.findPostNo = findPostNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPetNo() {
		return petNo;
	}

	public void setPetNo(String petNo) {
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

	public String getFindPostSum() {
		return findPostSum;
	}

	public void setFindPostSum(String findPostSum) {
		this.findPostSum = findPostSum;
	}

	public String getFindPostImageAddress() {
		return findPostImageAddress;
	}

	public void setFindPostImageAddress(String findPostImageAddress) {
		this.findPostImageAddress = findPostImageAddress;
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
	
}
