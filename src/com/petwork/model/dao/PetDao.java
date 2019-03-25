package com.petwork.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;

import com.petwork.model.vo.Animal;
import com.petwork.model.vo.Pet;

public class PetDao {
	
	Properties prop = new Properties();
	
	public PetDao() {
		String fileName = PetDao.class.getResource("./pet-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 해당 아이디에 등록된 전체 애완동물 리스트 조회
	public List<Pet> selectAllMyPet(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAllMyPet");
		ArrayList<Pet> list = new ArrayList<Pet>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Pet p = new Pet();
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setPetIdentifyNo(rs.getString("pet_identify_no"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				p.setAnimalNo(rs.getString("animal_no"));
				p.setPetBirth(rs.getDate("pet_birth"));
				p.setPetGender(rs.getString("pet_gender").charAt(0));
				p.setPetName(rs.getString("pet_name"));
				p.setPetYn(rs.getString("pet_yn").charAt(0));
				p.setPetNeutering(rs.getString("pet_neutering").charAt(0));
				
				list.add(p);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// select에서 선택한 애완동물 한 마리의 정보 조회(시퀀스 번호로 조회)
	public Pet selectMyPet(Connection conn, int petNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMyPet");
		Pet myPet = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, petNo);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				myPet = new Pet();
				myPet.setMemberId(rs.getString("member_id"));
				myPet.setPetNo(rs.getInt("pet_no"));
				myPet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				myPet.setRaceCode(rs.getString("race_code").charAt(0));
				myPet.setAnimalNo(rs.getString("animal_no"));
				myPet.setPetBirth(rs.getDate("pet_birth"));
				myPet.setPetGender(rs.getString("pet_gender").charAt(0));
				myPet.setPetName(rs.getString("pet_name"));
				myPet.setPetYn(rs.getString("pet_yn").charAt(0));
				myPet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myPet;
	}

	public String selectAnimalName(Connection conn, Animal a) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAnimalName");
		String myPetAniName = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getRaceCode());
			pstmt.setString(2, a.getAnimalNo());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				myPetAniName = rs.getString("animal_name");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myPetAniName;
	}

	public List<Animal> selectAnimalName(Connection conn, String raceCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAnimalNameList");
		List<Animal> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, raceCode);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Animal a = new Animal();
				a.setRaceCode(rs.getString("race_code"));
				a.setAnimalNo(rs.getString("animal_no"));
				a.setAnimalName(rs.getString("animal_name"));
				list.add(a);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
}
