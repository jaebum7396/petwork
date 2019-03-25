package com.petwork.model.vo;

public class Animal {
	private String raceCode;
	private String animalNo;
	private String animalName;
	public Animal() {
	}
	public Animal(String raceCode, String animalNo, String animalName) {
		this.raceCode = raceCode;
		this.animalNo = animalNo;
		this.animalName = animalName;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
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
	@Override
	public String toString() {
		return "Animal [raceCode=" + raceCode + ", animalNo=" + animalNo + ", animalName=" + animalName + "]";
	}
	

}
