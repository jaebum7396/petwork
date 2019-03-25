package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.ParcelDao;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.District;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.ParcelAniView;
import com.petwork.model.vo.ParcelImg;

public class ParcelService {
	public List<ParcelAniView> parcelList(int cPage, int numPerPage, String head) {
		Connection conn=getConnection();
		List<ParcelAniView> list=new ParcelDao().parcelList(conn,cPage,numPerPage,head);
		close(conn);
		return list;
	}
	public List<Animal> findAnimal(String raceCode) {
		Connection conn=getConnection();
		List<Animal> ani=new ParcelDao().selectMember(conn,raceCode);
		close(conn);
		return ani;
	}

	public List<District> findCity(String cityCode) {
		Connection conn=getConnection();
		List<District> city = new ParcelDao().findCity(conn,cityCode);
		close(conn);
		return city;
	}
	public ParcelAniView parcelDetailView(int postNo) {
		Connection conn=getConnection();
		ParcelAniView pav = new ParcelDao().parcelDetailView(conn,postNo);
		close(conn);
		return pav;
	}
	public Member parcelDetailMember(String id) {
		Connection conn=getConnection();
		Member m = new ParcelDao().parcelDetailMember(conn,id);
		close(conn);
		return m;
	}
	public int getAnimalNo(String animalName) {
		Connection conn=getConnection();
		int animalNo = new ParcelDao().getAnimalNo(conn,animalName);
		close(conn);
		return animalNo;
	}
	public int insertPost(Parcel p) {
		Connection conn=getConnection();
		int result = new ParcelDao().insertPost(conn,p);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public String getCityName(String city) {
		Connection conn=getConnection();
		String cityName = new ParcelDao().getCityName(conn, city);
		close(conn);
		return cityName;
	}
	public Parcel findPostNo(Parcel p) {
		Connection conn=getConnection();
		Parcel returnP = new ParcelDao().findPostNo(conn,p);
		close(conn);
		return returnP;
	}
	public int insertImg(ParcelImg parcelImg) {
		Connection conn=getConnection();
		int result = new ParcelDao().insertImg(conn,parcelImg);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int selectCount(String head) {
		Connection conn=getConnection();
		int result = new ParcelDao().selectCount(conn,head);
		close(conn);
		return result;
	}
	public List<ParcelImg> parcelDetailSubImg(int postNo) {
		Connection conn=getConnection();
		List<ParcelImg> list = new ParcelDao().parcelDetailSubImg(conn,postNo);
		close(conn);
		return list;
	}
	public ParcelImg parcelDetailMainImg(int postNo) {
		Connection conn=getConnection();
		ParcelImg pImg = new ParcelDao().parcelDetailMainImg(conn,postNo);
		close(conn);
		return pImg;
	}
	public ParcelAniView reWrite(int postNo) {
		Connection conn=getConnection();
		ParcelAniView returnP = new ParcelDao().reWrite(conn,postNo);
		close(conn);
		return returnP;
	}
	public int updatePost(Parcel p) {
		Connection conn=getConnection();
		int result = new ParcelDao().updatePost(conn,p);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int updateImg(ParcelImg parcelImg) {
		Connection conn=getConnection();
		int result = new ParcelDao().updateImg(conn,parcelImg);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public List<ParcelImg> findSubImg(int postNo) {
		Connection conn=getConnection();
		List<ParcelImg> oriFile = new ParcelDao().findSubImg(conn,postNo);
		close(conn);
		return oriFile;
	}
	public String findCityCode(String cityName) {
		Connection conn=getConnection();
		String cityCode = new ParcelDao().findCityCode(conn,cityName);
		close(conn);
		return cityCode;
	}
	public int parcelDeleteImg(int postNo) {
		Connection conn = getConnection();
		int result = new ParcelDao().parcelDeleteImg(conn,postNo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int parcelDeletePost(int postNo) {
		Connection conn = getConnection();
		int result = new ParcelDao().parcelDeletePost(conn,postNo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public List<ParcelImg> parcelImg(int postNo) {
		Connection conn=getConnection();
		List<ParcelImg> pImg = new ParcelDao().parcelImg(conn,postNo);
		close(conn);
		return pImg;
	}
	public int parcelComplete(int postNo) {
		Connection conn = getConnection();
		int result = new ParcelDao().parcelComplete(conn,postNo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public List<Animal> allAnimalList(int cPage, int numPerPage, String head) {
		Connection conn = getConnection();
		List<Animal> list = new ParcelDao().allAnimalList(conn,cPage,numPerPage,head);
		close(conn);
		return list;
	}
	public int animalCount(String head) {
		Connection conn=getConnection();
		int result = new ParcelDao().animalCount(conn,head);
		close(conn);
		return result;
	}
	public int addAnimal(String raceCode, String animalName) {
		Connection conn = getConnection();
		int result = new ParcelDao().addAnimal(conn,raceCode,animalName);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteAnimal(String animalNo, String raceCode) {
		Connection conn = getConnection();
		int result = new ParcelDao().deleteAnimal(conn,animalNo,raceCode);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


}
