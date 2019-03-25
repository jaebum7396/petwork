package com.petwork.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.DoctorDao;
import com.petwork.model.vo.City;
import com.petwork.model.vo.Doctor;
public class DoctorService {
	
	public int insertDoctor(Doctor d)
	{
		Connection conn=getConnection();
		int result = new DoctorDao().insertDoctor(conn,d);
		close(conn);
		return result;
	}
	public List<Doctor> DoctorListShow()
	{
		Connection conn=getConnection();
		List<Doctor> list=new DoctorDao().doctorListShow(conn);
		close(conn);
		return list;
	}
	public List<Doctor> selectDoctorList(int cPage,int numPerPage)
	{
		Connection conn=getConnection();
		List<Doctor> list=new DoctorDao().selectDoctorList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	public int selectDoctorCount()
	{
		Connection conn=getConnection();
		int result=new DoctorDao().selectDoctorCount(conn);
		close(conn);
		return result;
	}
	public Doctor selectDoctorClick(int doctorNo) {
		
		Connection conn=getConnection();
		
		Doctor d=new DoctorDao().selectDoctorClick(conn,doctorNo);
		close(conn);
		return d;
	}
	public int updateClickDoctor(Doctor d) {
		Connection conn=getConnection();
		
		int result=new DoctorDao().updateClickDoctor(conn,d);
		
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int doctorDelete(int doctorNo) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		int result=new DoctorDao().doctorDelete(doctorNo,conn);
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		return result;
	}
	public List<Doctor> selectName(String searchKeyword, int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		List<Doctor> d = new DoctorDao().selectName(conn,searchKeyword,cPage,numPerPage);
		close(conn);
		return d;
	}
	public List<Doctor> selectAddress(String searchKeyword, int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		List<Doctor> d = new DoctorDao().selectAddress(conn,searchKeyword,cPage,numPerPage);
		close(conn);
		return d;
	
	}
	public int doctorOut(String outDoctorId) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		int result=new DoctorDao().doctorOut(conn,outDoctorId);
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		return result;
	}
	
		public City findCity(String cityCode) {
		      Connection conn=getConnection();
		      City city = new DoctorDao().findCity(conn,cityCode);
		      close(conn);
		      return city;
		   }
		public List<Doctor> doctorAreaList(String cityCode) {
				Connection conn=getConnection();
				List<Doctor>  doctorAreaList=new DoctorDao().doctorAreaList(conn,cityCode);
				close(conn);
				return doctorAreaList;
		}
		public List<Doctor> SeoulDoctorList() {
			// TODO Auto-generated method stub
			Connection conn=getConnection();
			List<Doctor>  SeoulDoctorList=new DoctorDao().SeoulDoctorList(conn);
			close(conn);
			return SeoulDoctorList;
		}

		public int selectDoctorNameCount(String searchKeyword) {
			Connection conn=getConnection();
			int  result=new DoctorDao().selectDoctorNameCount(conn,searchKeyword);
			if(result>0)
			{
				commit(conn);
			}else
			{
				close(conn);
			}
			return result;
		}
		public int selectDoctorAddressCount(String searchKeyword) {
			Connection conn=getConnection();
			int  result=new DoctorDao().selectDoctorAddressCount(conn,searchKeyword);
			if(result>0)
			{
				commit(conn);
			}else
			{
				close(conn);
			}
			return result;
		}
		public List<Doctor> doctorMapList(String cityName) {
			Connection conn=getConnection();
			List<Doctor> doctorMapList = new DoctorDao().doctorMapList(conn, cityName);
			close(conn);
			return doctorMapList;
		}
		
	}

