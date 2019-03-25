package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.Animal;
import com.petwork.model.vo.City;
import com.petwork.model.vo.District;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.ParcelAniView;
import com.petwork.model.vo.ParcelImg;

public class ParcelDao {
	Properties prop = new Properties();

	public ParcelDao() {
		String fileName = ParcelDao.class.getResource("./parcel-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<ParcelAniView> parcelList(Connection conn, int cPage, int numPerPage, String head) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(head==null||head.equals("A")) {
			sql=prop.getProperty("parcelList");
		} else {
			sql=prop.getProperty("parcelHeadList");
		}
		
		ParcelAniView pav = null;
		List<ParcelAniView> list = new ArrayList<ParcelAniView>();
		try {
			pstmt=conn.prepareStatement(sql);
			if(head==null||head.equals("A")) {
				pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
				pstmt.setInt(2, cPage * numPerPage);
			} else {
				pstmt.setString(1, head);
				pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
				pstmt.setInt(3, cPage * numPerPage);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pav = new ParcelAniView();
				pav.setTitle(rs.getString("parcel_post_title"));
				pav.setHead(rs.getString("parcel_post_head"));
				pav.setPostNo(rs.getInt("parcel_post_no"));
				pav.setId(rs.getString("member_id"));
				pav.setAnimalNo(rs.getInt("animal_no"));
				pav.setPetBirth(rs.getDate("parcel_post_pet_birth"));
				pav.setAddress(rs.getString("parcel_post_address"));
				pav.setGender(rs.getString("parcel_post_pet_gender"));
				pav.setNeutering(rs.getString("parcel_post_pet_neutering"));
				pav.setPrice(rs.getInt("parcel_post_price"));
				pav.setContent(rs.getString("parcel_post_content"));
				pav.setPostYn(rs.getString("parcel_post_yn"));
				pav.setVaccination(rs.getString("parcel_post_vaccination"));
				pav.setAnimalName(rs.getString("animal_name"));
				pav.setImgAddress(rs.getString("parcel_img_address"));
				pav.setMemberGender(rs.getString("member_gender").charAt(0));
				list.add(pav);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<Animal> findAnimal(Connection conn, String raceCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("animalList");
		Animal ani = null;
		List<Animal> list = new ArrayList<Animal>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, raceCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ani=new Animal();
				ani.setRaceCode(rs.getString("race_code"));
				ani.setAnimalNo(rs.getString("animal_no"));
				ani.setAnimalName(rs.getString("animal_name"));
				list.add(ani);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<District> findCity(Connection conn, String cityCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("districtList");
		District dis = null;
		List<District> list = new ArrayList<District>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cityCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dis=new District();
				dis.setCityCode(rs.getString("city_code"));
				dis.setDistrictCode(rs.getString("district_code"));
				dis.setDistrictName(rs.getString("district_name"));
				list.add(dis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<Animal> selectMember(Connection conn, String raceCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("animalList");
		Animal ani = null;
		List<Animal> list = new ArrayList<Animal>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, raceCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ani=new Animal();
				ani.setRaceCode(rs.getString("race_code"));
				ani.setAnimalNo(rs.getString("animal_no"));
				ani.setAnimalName(rs.getString("animal_name"));
				list.add(ani);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ParcelAniView parcelDetailView(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelDetailView");
		ParcelAniView pav = new ParcelAniView();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pav.setTitle(rs.getString("parcel_post_title"));
				pav.setHead(rs.getString("parcel_post_head"));
				pav.setPostNo(rs.getInt("parcel_post_no"));
				pav.setPostDate(rs.getDate("parcel_post_date"));
				pav.setId(rs.getString("member_id"));
				pav.setAnimalNo(rs.getInt("animal_no"));
				pav.setPetBirth(rs.getDate("parcel_post_pet_birth"));
				pav.setAddress(rs.getString("parcel_post_address"));
				pav.setGender(rs.getString("parcel_post_pet_gender"));
				pav.setNeutering(rs.getString("parcel_post_pet_neutering"));
				pav.setPrice(rs.getInt("parcel_post_price"));
				pav.setContent(rs.getString("parcel_post_content"));
				pav.setPostYn(rs.getString("parcel_post_yn"));
				pav.setVaccination(rs.getString("parcel_post_vaccination"));
				pav.setAnimalName(rs.getString("animal_name"));
				pav.setRaceName(rs.getString("race_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return pav;
	}

	public Member parcelDetailMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelDetailMember");
		Member m = new Member();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m.setId(rs.getString("member_id"));
				m.setPhone(rs.getString("member_phone"));
				m.setGender(rs.getString("member_gender").charAt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return m;
	}

	public int getAnimalNo(Connection conn, String animalName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("getAnimalNo");
		int animalNo = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, animalName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				animalNo = rs.getInt("animal_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return animalNo;
	}

	public int insertPost(Connection conn, Parcel p) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("insertPost");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getTitle());
			pstmt.setString(2, p.getHead());
			pstmt.setString(3, p.getId());
			pstmt.setInt(4, p.getAnimalNo());
			pstmt.setDate(5, p.getPetBirth());
			pstmt.setString(6, p.getAddress());
			pstmt.setString(7, p.getGender());
			pstmt.setString(8, p.getNeutering());
			pstmt.setInt(9, p.getPrice());
			pstmt.setString(10, p.getContent());
			pstmt.setString(11, p.getVaccination());
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String getCityName(Connection conn, String city) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("getCityName");
		String cityName="";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, city);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cityName = rs.getString("city_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return cityName;
	}

	public Parcel findPostNo(Connection conn, Parcel p) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("findPostNo");
		Parcel returnP = new Parcel();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getTitle());
			pstmt.setString(2, p.getHead());
			pstmt.setString(3, p.getId());
			pstmt.setInt(4, p.getAnimalNo());
			pstmt.setDate(5, p.getPetBirth());
			pstmt.setString(6, p.getAddress());
			pstmt.setString(7, p.getGender());
			pstmt.setString(8, p.getNeutering());
			pstmt.setInt(9, p.getPrice());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				returnP.setContent(rs.getString("parcel_post_content"));
				returnP.setPostNo(rs.getInt("parcel_post_no"));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return returnP;
	}

	public int insertImg(Connection conn, ParcelImg parcelImg) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("insertImg");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, parcelImg.getParcelImgAddress());
			pstmt.setInt(2, parcelImg.getParcelPostNo());
			pstmt.setString(3, parcelImg.getParcelMainImg());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectCount(Connection conn, String head) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		if(head==null||head.equals("A")) {
			sql=prop.getProperty("selectCount");
		} else {
			sql=prop.getProperty("selectPartCount");
		}
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			if(!(head==null||head.equals("A"))) {
				pstmt.setString(1, head);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<ParcelImg> parcelDetailSubImg(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelDetailSubImg");
		List<ParcelImg> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ParcelImg pImg = new ParcelImg();
				pImg.setParcelImgNo(rs.getInt("parcel_img_no"));
				pImg.setParcelImgAddress(rs.getString("parcel_img_address"));
				pImg.setParcelPostNo(rs.getInt("parcel_post_no"));
				list.add(pImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ParcelImg parcelDetailMainImg(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelDetailMainImg");
		ParcelImg pImg = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pImg = new ParcelImg();
				pImg.setParcelImgNo(rs.getInt("parcel_img_no"));
				pImg.setParcelImgAddress(rs.getString("parcel_img_address"));
				pImg.setParcelPostNo(rs.getInt("parcel_post_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return pImg;
	}

	public ParcelAniView reWrite(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelDetailView");
		ParcelAniView returnP = new ParcelAniView();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				returnP.setTitle(rs.getString("parcel_post_title"));
				returnP.setHead(rs.getString("parcel_post_head"));
				returnP.setPostNo(rs.getInt("parcel_post_no"));
				returnP.setPostDate(rs.getDate("parcel_post_date"));
				returnP.setId(rs.getString("member_id"));
				returnP.setAnimalNo(rs.getInt("animal_no"));
				returnP.setPetBirth(rs.getDate("parcel_post_pet_birth"));
				returnP.setAddress(rs.getString("parcel_post_address"));
				returnP.setGender(rs.getString("parcel_post_pet_gender"));
				returnP.setNeutering(rs.getString("parcel_post_pet_neutering"));
				returnP.setPrice(rs.getInt("parcel_post_price"));
				returnP.setContent(rs.getString("parcel_post_content"));
				returnP.setPostYn(rs.getString("parcel_post_yn"));
				returnP.setVaccination(rs.getString("parcel_post_vaccination"));
				returnP.setAnimalName(rs.getString("animal_name"));
				returnP.setRaceName(rs.getString("race_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return returnP;
	}

	public int updatePost(Connection conn, Parcel p) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("updatePost");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getTitle());
			pstmt.setString(2, p.getHead());
			pstmt.setString(3, p.getId());
			pstmt.setInt(4, p.getAnimalNo());
			pstmt.setDate(5, p.getPetBirth());
			pstmt.setString(6, p.getAddress());
			pstmt.setString(7, p.getGender());
			pstmt.setString(8, p.getNeutering());
			pstmt.setInt(9, p.getPrice());
			pstmt.setString(10, p.getContent());
			pstmt.setString(11, p.getVaccination());
			pstmt.setInt(12, p.getPostNo());
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateImg(Connection conn, ParcelImg parcelImg) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("updateImg");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, parcelImg.getParcelImgAddress());
			pstmt.setInt(2, parcelImg.getParcelPostNo());
			pstmt.setInt(3, parcelImg.getParcelImgNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ParcelImg> findSubImg(Connection conn,int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("findSubImg");
		ParcelImg pImg = null;
		List<ParcelImg> oriFile = new ArrayList<ParcelImg>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pImg = new ParcelImg();
				pImg.setParcelImgNo(rs.getInt("parcel_img_no"));
				pImg.setParcelImgAddress(rs.getString("parcel_img_address"));
				pImg.setParcelMainImg(rs.getString("parcel_main_img"));
				pImg.setParcelPostNo(rs.getInt("parcel_post_no"));
				oriFile.add(pImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return oriFile;
	}

	public String findCityCode(Connection conn, String cityName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("findCityCode");
		City city = null;
		String cityCode = "";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+cityName+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cityCode = rs.getString("city_code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return cityCode;
	}

	public int parcelDeleteImg(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("parcelDeleteImg");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int parcelDeletePost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("parcelDeletePost");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ParcelImg> parcelImg(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("parcelImg");
		List<ParcelImg> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ParcelImg pImg = new ParcelImg();
				pImg.setParcelImgNo(rs.getInt("parcel_img_no"));
				pImg.setParcelImgAddress(rs.getString("parcel_img_address"));
				pImg.setParcelPostNo(rs.getInt("parcel_post_no"));
				list.add(pImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int parcelComplete(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("parcelComplete");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Animal> allAnimalList(Connection conn, int cPage, int numPerPage, String head) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(head==null||head.equals("A")) {
			sql=prop.getProperty("allAnimalList");
		} else {
			sql=prop.getProperty("allAnimalPartList");
		}
		
		Animal ani = null;
		List<Animal> list = new ArrayList<Animal>();
		try {
			pstmt=conn.prepareStatement(sql);
			if(head==null||head.equals("A")) {
				pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
				pstmt.setInt(2, cPage * numPerPage);
			} else {
				pstmt.setString(1, head);
				pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
				pstmt.setInt(3, cPage * numPerPage);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ani = new Animal();
				ani.setRaceCode(rs.getString("race_code"));
				ani.setAnimalNo(rs.getString("animal_no"));
				ani.setAnimalName(rs.getString("animal_name"));
				
				list.add(ani);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int animalCount(Connection conn, String head) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		if(head==null||head.equals("A")) {
			sql=prop.getProperty("animalCount");
		} else {
			sql=prop.getProperty("animalPartCount");
		}
		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			if(!(head==null||head.equals("A"))) {
				pstmt.setString(1, head);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int addAnimal(Connection conn, String raceCode, String animalName) {
		PreparedStatement pstmt = null;
		String sql="";
		int result = 0;
		if(raceCode.equals("D")) {
			sql=prop.getProperty("addDog");
		} else if(raceCode.endsWith("C")) {
			sql=prop.getProperty("addCat");
		} else if(raceCode.endsWith("E")) {
			sql=prop.getProperty("addEtc");
		}
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, raceCode);
			pstmt.setString(2, animalName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAnimal(Connection conn, String animalNo, String raceCode) {
		PreparedStatement pstmt = null;
		String sql=prop.getProperty("deleteAnimal");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, animalNo);
			pstmt.setString(2, raceCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	
}
