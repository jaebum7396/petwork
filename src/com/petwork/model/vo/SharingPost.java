package com.petwork.model.vo;

import java.util.Date;

public class SharingPost extends SharingImg{
	
	private String sharingPostTitle;//나눔 게시판 제목
	private int sharingPostNo;//나눔 게시판 번호
	private Date sharingPostDate;//나눔게시판 작성일
	private String sharingPostWriter;//나눔게시판 작성자
	private String sharingPostAddress;//나눔 거래주소
	private String sharingPostYN;//거래 여부
	private String sharingPostContent;//나눔 게시판 내용
	private String productCategoryCode;//상품 코드
	private String productCategoryName;//상품이름
	private String race_code;//품종 코드
	private int count;//조회수
	
	public SharingPost() {
	}

	public SharingPost(String sharingPostTitle, int sharingPostNo, Date sharingPostDate, String sharingPostWriter,
			String sharingPostAddress, String sharingPostYN, String sharingPostContent, String productCategoryCode,
			String productCategoryName, String race_code, int count) {
		super();
		this.sharingPostTitle = sharingPostTitle;
		this.sharingPostNo = sharingPostNo;
		this.sharingPostDate = sharingPostDate;
		this.sharingPostWriter = sharingPostWriter;
		this.sharingPostAddress = sharingPostAddress;
		this.sharingPostYN = sharingPostYN;
		this.sharingPostContent = sharingPostContent;
		this.productCategoryCode = productCategoryCode;
		this.productCategoryName = productCategoryName;
		this.race_code = race_code;
		this.count = count;
	}

	public String getSharingPostTitle() {
		return sharingPostTitle;
	}

	public void setSharingPostTitle(String sharingPostTitle) {
		this.sharingPostTitle = sharingPostTitle;
	}

	public int getSharingPostNo() {
		return sharingPostNo;
	}

	public void setSharingPostNo(int sharingPostNo) {
		this.sharingPostNo = sharingPostNo;
	}

	public Date getSharingPostDate() {
		return sharingPostDate;
	}

	public void setSharingPostDate(Date sharingPostDate) {
		this.sharingPostDate = sharingPostDate;
	}

	public String getSharingPostWriter() {
		return sharingPostWriter;
	}

	public void setSharingPostWriter(String sharingPostWriter) {
		this.sharingPostWriter = sharingPostWriter;
	}

	public String getSharingPostAddress() {
		return sharingPostAddress;
	}

	public void setSharingPostAddress(String sharingPostAddress) {
		this.sharingPostAddress = sharingPostAddress;
	}

	public String getSharingPostYN() {
		return sharingPostYN;
	}

	public void setSharingPostYN(String sharingPostYN) {
		this.sharingPostYN = sharingPostYN;
	}

	public String getSharingPostContent() {
		return sharingPostContent;
	}

	public void setSharingPostContent(String sharingPostContent) {
		this.sharingPostContent = sharingPostContent;
	}

	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getRace_code() {
		return race_code;
	}

	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SharingPost [sharingPostTitle=" + sharingPostTitle + ", sharingPostNo=" + sharingPostNo
				+ ", sharingPostDate=" + sharingPostDate + ", sharingPostWriter=" + sharingPostWriter
				+ ", sharingPostAddress=" + sharingPostAddress + ", sharingPostYN=" + sharingPostYN
				+ ", sharingPostContent=" + sharingPostContent + ", productCategoryCode=" + productCategoryCode
				+ ", productCategoryName=" + productCategoryName + ", race_code=" + race_code + ", count=" + count
				+ "]";
	}

	
}
