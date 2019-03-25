package com.petwork.model.vo;

import java.util.Date;

public class SharingComment {
	
	private int sharingPostNo;
	private int sharingCommentNo;
	private int sharingCommentLevel;
	private int sharingCommentRef;
	private String sharingCommentWriter;
	private Date sharingCommentDate;
	private String sharingCommentContent;
	
	public SharingComment() {
	}

	public SharingComment(int sharingPostNo, int sharingCommentNo, int sharingCommentLevel, int sharingCommentRef,
			String sharingCommentWriter, Date sharingCommentDate, String sharingCommentContent) {
		super();
		this.sharingPostNo = sharingPostNo;
		this.sharingCommentNo = sharingCommentNo;
		this.sharingCommentLevel = sharingCommentLevel;
		this.sharingCommentRef = sharingCommentRef;
		this.sharingCommentWriter = sharingCommentWriter;
		this.sharingCommentDate = sharingCommentDate;
		this.sharingCommentContent = sharingCommentContent;
	}

	public int getSharingPostNo() {
		return sharingPostNo;
	}

	public void setSharingPostNo(int sharingPostNo) {
		this.sharingPostNo = sharingPostNo;
	}

	public int getSharingCommentNo() {
		return sharingCommentNo;
	}

	public void setSharingCommentNo(int sharingCommentNo) {
		this.sharingCommentNo = sharingCommentNo;
	}

	public int getSharingCommentLevel() {
		return sharingCommentLevel;
	}

	public void setSharingCommentLevel(int sharingCommentLevel) {
		this.sharingCommentLevel = sharingCommentLevel;
	}

	public int getSharingCommentRef() {
		return sharingCommentRef;
	}

	public void setSharingCommentRef(int sharingCommentRef) {
		this.sharingCommentRef = sharingCommentRef;
	}

	public String getSharingCommentWriter() {
		return sharingCommentWriter;
	}

	public void setSharingCommentWriter(String sharingCommentWriter) {
		this.sharingCommentWriter = sharingCommentWriter;
	}

	public Date getSharingCommentDate() {
		return sharingCommentDate;
	}

	public void setSharingCommentDate(Date sharingCommentDate) {
		this.sharingCommentDate = sharingCommentDate;
	}

	public String getSharingCommentContent() {
		return sharingCommentContent;
	}

	public void setSharingCommentContent(String sharingCommentContent) {
		this.sharingCommentContent = sharingCommentContent;
	}

	@Override
	public String toString() {
		return "SharingComment [sharingPostNo=" + sharingPostNo + ", sharingCommentNo=" + sharingCommentNo
				+ ", sharingCommentLevel=" + sharingCommentLevel + ", sharingCommentRef=" + sharingCommentRef
				+ ", sharingCommentWriter=" + sharingCommentWriter + ", sharingCommentDate=" + sharingCommentDate
				+ ", sharingCommentContent=" + sharingCommentContent + "]";
	}
	
	

}
