package com.petwork.model.service;

import com.petwork.model.dao.PetDao;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.Pet;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

public class PetService {
	
	public List<Pet> selectAllMyPet(String memberId) {
		Connection conn = getConnection();
		List<Pet> myPetList = new PetDao().selectAllMyPet(conn, memberId);
		close(conn);
		return myPetList;
	}
	
	// select에서 선택한 애완동물 한마리의 정보 반환
	public Pet selectMyPet(int petNo) {
		Connection conn = getConnection();
		Pet myPet = new PetDao().selectMyPet(conn, petNo);
		close(conn);
		return myPet;
	}

	public String selectAnimalName(Animal a) {
		Connection conn = getConnection();
		String myPetAniName = new PetDao().selectAnimalName(conn, a);
		close(conn);
		return myPetAniName;
	}

	public List<Animal> selectAnimalName(String raceCode) {
		Connection conn = getConnection();
		List<Animal> animalNameList = new PetDao().selectAnimalName(conn, raceCode);
		close(conn);
		return animalNameList;
	}
		
}
