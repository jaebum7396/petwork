package com.petwork.model.vo;

import java.sql.Date;

public class Pet 
{
	private String memberId;
	private String petName;
	private String petIdentifyNo;
	private String animalName;	
	private Date petBirth;
	private char petGender;
	private char petNeutering;
	private int petNo;
	private char petYn;
	private char raceCode;
	private String animalNo;
	private String petBirthStringFormat;
	
	public Pet() 
	{
		super();
	}

	public Pet(String memberId, String petName, String petIdentifyNo, String animalName, Date petBirth, char petGender,
			char petNeutering, int petNo, char petYn, char raceCode, String animalNo, String petBirthStringFormat) {
		super();
		this.memberId = memberId;
		this.petName = petName;
		this.petIdentifyNo = petIdentifyNo;
		this.animalName = animalName;
		this.petBirth = petBirth;
		this.petGender = petGender;
		this.petNeutering = petNeutering;
		this.petNo = petNo;
		this.petYn = petYn;
		this.raceCode = raceCode;
		this.animalNo = animalNo;
		this.petBirthStringFormat = petBirthStringFormat;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetIdentifyNo() {
		return petIdentifyNo;
	}

	public void setPetIdentifyNo(String petIdentifyNo) {
		this.petIdentifyNo = petIdentifyNo;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public Date getPetBirth() {
		return petBirth;
	}

	public void setPetBirth(Date petBirth) {
		this.petBirth = petBirth;
	}

	public char getPetGender() {
		return petGender;
	}

	public void setPetGender(char petGender) {
		this.petGender = petGender;
	}

	public char getPetNeutering() {
		return petNeutering;
	}

	public void setPetNeutering(char petNeutering) {
		this.petNeutering = petNeutering;
	}

	public int getPetNo() {
		return petNo;
	}

	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}

	public char getPetYn() {
		return petYn;
	}

	public void setPetYn(char petYn) {
		this.petYn = petYn;
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

	public String getPetBirthStringFormat() {
		return petBirthStringFormat;
	}

	public void setPetBirthStringFormat(String petBirthStringFormat) {
		this.petBirthStringFormat = petBirthStringFormat;
	}
	
	
}
