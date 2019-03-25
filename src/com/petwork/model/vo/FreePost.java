package com.petwork.model.vo;

import java.util.Date;
import java.util.List;

public class FreePost extends FreeImg{
	
	private int freePostNo;
	private String freePostTitle;
	private String freePostWriter;
	private String freePostContent;
	private Date freePostDate;
	private String race_code;
	private int Count;
	
	public FreePost() {
	}

	public FreePost(String freePostTitle, int freePostNo, Date freePostDate, String freePostContent, String freePostWriter,
			String race_code, int count) {
		super();
		this.freePostTitle = freePostTitle;
		this.freePostNo = freePostNo;
		this.freePostDate = freePostDate;
		this.freePostContent = freePostContent;
		this.freePostWriter = freePostWriter;
		this.race_code = race_code;
		Count = count;
	}

	public String getFreePostTitle() {
		return freePostTitle;
	}

	public void setFreePostTitle(String freePostTitle) {
		this.freePostTitle = freePostTitle;
	}

	public int getFreePostNo() {
		return freePostNo;
	}

	public void setFreePostNo(int freePostNo) {
		this.freePostNo = freePostNo;
	}

	public Date getFreePostDate() {
		return freePostDate;
	}

	public void setFreePostDate(Date freePostDate) {
		this.freePostDate = freePostDate;
	}

	public String getFreePostContent() {
		return freePostContent;
	}

	public void setFreePostContent(String freePostContent) {
		this.freePostContent = freePostContent;
	}

	public String getFreePostWriter() {
		return freePostWriter;
	}

	public void setFreePostWriter(String freePostWriter) {
		this.freePostWriter = freePostWriter;
	}

	public String getRace_code() {
		return race_code;
	}

	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	@Override
	public String toString() {
		return "FreePost [freePostTitle=" + freePostTitle + ", freePostNo=" + freePostNo + ", freePostDate="
				+ freePostDate + ", freePostContent=" + freePostContent + ", freePostWriter=" + freePostWriter + ", race_code="
				+ race_code + ", Count=" + Count + "]";
	}

	
	

	

}
