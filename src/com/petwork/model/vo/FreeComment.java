package com.petwork.model.vo;

import java.util.Date;

public class FreeComment {
	
	private int freePostNo;
	private int freeCommentNo;
	private int freeCommentLevel;
	private int freeCommentRef;
	private String freeCommentWriter;
	private Date freeCommentDate;
	private String freeCommentContent;
	
	public FreeComment() {
	}

	public FreeComment(int freePostNo, int freeCommentNo, int freeCommentLevel, int freeCommentRef,
			String freeCommentWriter, Date freeCommentDate, String freeCommentContent) {
		super();
		this.freePostNo = freePostNo;
		this.freeCommentNo = freeCommentNo;
		this.freeCommentLevel = freeCommentLevel;
		this.freeCommentRef = freeCommentRef;
		this.freeCommentWriter = freeCommentWriter;
		this.freeCommentDate = freeCommentDate;
		this.freeCommentContent = freeCommentContent;
	}

	public int getFreePostNo() {
		return freePostNo;
	}

	public void setFreePostNo(int freePostNo) {
		this.freePostNo = freePostNo;
	}

	public int getFreeCommentNo() {
		return freeCommentNo;
	}

	public void setFreeCommentNo(int freeCommentNo) {
		this.freeCommentNo = freeCommentNo;
	}

	public int getFreeCommentLevel() {
		return freeCommentLevel;
	}

	public void setFreeCommentLevel(int freeCommentLevel) {
		this.freeCommentLevel = freeCommentLevel;
	}

	public int getFreeCommentRef() {
		return freeCommentRef;
	}

	public void setFreeCommentRef(int freeCommentRef) {
		this.freeCommentRef = freeCommentRef;
	}

	public String getFreeCommentWriter() {
		return freeCommentWriter;
	}

	public void setFreeCommentWriter(String freeCommentWriter) {
		this.freeCommentWriter = freeCommentWriter;
	}

	public Date getFreeCommentDate() {
		return freeCommentDate;
	}

	public void setFreeCommentDate(Date freeCommentDate) {
		this.freeCommentDate = freeCommentDate;
	}

	public String getFreeCommentContent() {
		return freeCommentContent;
	}

	public void setFreeCommentContent(String freeCommentContent) {
		this.freeCommentContent = freeCommentContent;
	}

	@Override
	public String toString() {
		return "FreeComment [freePostNo=" + freePostNo + ", freeCommentNo=" + freeCommentNo + ", freeCommentLevel="
				+ freeCommentLevel + ", freeCommentRef=" + freeCommentRef + ", freeCommentWriter=" + freeCommentWriter
				+ ", freeCommentDate=" + freeCommentDate + ", freeCommentContent=" + freeCommentContent
				+ "]";
	}
	
	
	

}
