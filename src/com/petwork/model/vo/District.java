package com.petwork.model.vo;

public class District {
	private String cityCode;
	private String districtCode;
	private String districtName;
	public District() {
	}
	public District(String cityCode, String districtCode, String districtName) {
		this.cityCode = cityCode;
		this.districtCode = districtCode;
		this.districtName = districtName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Override
	public String toString() {
		return "District [cityCode=" + cityCode + ", districtCode=" + districtCode + ", districtName=" + districtName
				+ "]";
	}
	

}
