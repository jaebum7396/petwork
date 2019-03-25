package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.City;
import com.petwork.model.vo.Doctor;

public class DoctorDao {
	Properties prop = new Properties();

	public DoctorDao() {
		String fileName=DoctorDao.class.getResource("./doctor-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	

	public int insertDoctor(Connection conn,Doctor d)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertDoctor");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, d.getDoctorId());
			pstmt.setString(2, d.getDoctorPwd());
			pstmt.setString(3,d.getDoctorLicense());
			pstmt.setString(4,d.getDoctorName());
			pstmt.setString(5,d.getDoctorHospital());
			pstmt.setString(6,d.getDoctorAddress());
			pstmt.setString(7,d.getDoctorPhone());
			pstmt.setString(8,d.getDoctorImg());
			result=pstmt.executeUpdate();
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(pstmt);
		}
		return result;
	}
	public List<Doctor> doctorListShow(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("doctorListShow");
		List<Doctor> list=new ArrayList();
		try {
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next())
			{
			Doctor d =new Doctor();
			d.setDoctorId(rs.getString("doctor_id"));
			d.setDoctorName(rs.getString("doctor_Name"));
			d.setDoctorHospital(rs.getString("doctor_hospital"));
			d.setDoctorPhone(rs.getString("doctor_phone"));
			d.setDoctorImg(rs.getString("doctor_img"));
			d.setDoctorAddress(rs.getString("doctor_address"));
			d.setDoctorLicense(rs.getString("doctor_license"));
			d.setDoctorPwd(rs.getString("doctor_pwd"));
			list.add(d);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Doctor> selectDoctorList(Connection conn,int cPage,int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String sql=prop.getProperty("selectDoctorList");
		List<Doctor> list=new ArrayList();
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Doctor d = new Doctor();
				d.setDoctorNo(rs.getInt("doctor_no"));
				d.setDoctorId(rs.getString("doctor_id"));
				d.setDoctorPwd(rs.getString("doctor_pwd"));
				d.setDoctorLicense(rs.getString("doctor_license"));
				d.setDoctorName(rs.getString("doctor_name"));
				d.setDoctorHospital(rs.getString("doctor_hospital"));
				d.setDoctorAddress(rs.getString("doctor_address"));
				d.setDoctorPhone(rs.getString("doctor_phone"));
				d.setDoctorImg(rs.getString("doctor_img"));
				list.add(d);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public int selectDoctorCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "";
		
		sql=prop.getProperty("selectDoctorCount");
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	
	}


	public Doctor selectDoctorClick(Connection conn, int doctorNo) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int result=0;
		Doctor d=null;
		String sql =prop.getProperty("selectDoctorClick");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,doctorNo);
			
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				d=new Doctor();
				d.setDoctorNo(rs.getInt("doctor_no"));
				d.setDoctorId(rs.getString("doctor_id"));
				d.setDoctorPwd(rs.getString("doctor_pwd"));
				d.setDoctorLicense(rs.getString("doctor_license"));
				d.setDoctorName(rs.getString("doctor_name"));
				d.setDoctorHospital(rs.getString("doctor_hospital"));
				d.setDoctorAddress(rs.getString("doctor_address"));
				d.setDoctorPhone(rs.getString("doctor_phone"));
				d.setDoctorImg(rs.getString("doctor_img"));
			}
		}	catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		
		return d;
	}


	public int updateClickDoctor(Connection conn,Doctor d) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateClickDoctor");
		try
		{		pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,d.getDoctorName());		
				pstmt.setString(2,d.getDoctorHospital() );
				pstmt.setString(3, d.getDoctorAddress());
				pstmt.setString(4, d.getDoctorPhone());
				pstmt.setString(5, d.getDoctorImg());
				pstmt.setInt(6, d.getDoctorNo());
				result=pstmt.executeUpdate();
				
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(pstmt);
		}
		
		return result;
	}


	public int doctorDelete(int doctorNo,Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("doctorDelete");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, doctorNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(pstmt);
		}
		return result;
	}


	public List<Doctor> selectName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		List<Doctor> list= new ArrayList();
		String sql=prop.getProperty("selectName");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1); 
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Doctor d = new Doctor();
				d.setDoctorNo(rs.getInt("doctor_no"));
				d.setDoctorId(rs.getString("doctor_id"));
				d.setDoctorPwd(rs.getString("doctor_pwd"));
				d.setDoctorLicense(rs.getString("doctor_license"));
				d.setDoctorName(rs.getString("doctor_name"));
				d.setDoctorHospital(rs.getString("doctor_hospital"));
				d.setDoctorAddress(rs.getString("doctor_address"));
				d.setDoctorPhone(rs.getString("doctor_phone"));
				d.setDoctorImg(rs.getString("doctor_img"));
				list.add(d);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(rs);
			close(pstmt);
		}
		return list;
	}



		public List<Doctor> selectAddress(Connection conn, String searchKeyword, int cPage, int numPerPage) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt=null;
			List<Doctor> list = new ArrayList();
			ResultSet rs=null;
			String sql = prop.getProperty("selectAddress");
			Doctor d=null;
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchKeyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1); 
				pstmt.setInt(3, cPage*numPerPage);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					d = new Doctor();
					d.setDoctorNo(rs.getInt("doctor_no"));
					d.setDoctorId(rs.getString("doctor_id"));
					d.setDoctorPwd(rs.getString("doctor_pwd"));
					d.setDoctorLicense(rs.getString("doctor_license"));
					d.setDoctorName(rs.getString("doctor_name"));
					d.setDoctorHospital(rs.getString("doctor_hospital"));
					d.setDoctorAddress(rs.getString("doctor_address"));
					d.setDoctorPhone(rs.getString("doctor_phone"));
					d.setDoctorImg(rs.getString("doctor_img"));
					list.add(d);
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return list;
		}


		public int doctorOut(Connection conn, String outDoctorId) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt=null;
			int result=0;
			String sql=prop.getProperty("doctorOut");
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, outDoctorId);
				result=pstmt.executeUpdate();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(pstmt);
			}
			return result;
		}


		public City findCity(Connection conn, String cityCode) {
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      String sql=prop.getProperty("CityList");
			      City city = null;
			      try {
			         pstmt=conn.prepareStatement(sql);
			         pstmt.setString(1, cityCode);
			         rs = pstmt.executeQuery();
			         if(rs.next()) {
			        	 city=new City();
			        	 city.setCityCode(rs.getString("city_code"));
			        	 city.setCityName(rs.getString("city_name"));
			        	 
			         }
			      } catch (SQLException e) {
			         e.printStackTrace();
			      } finally {
			         close(rs);
			         close(pstmt);
			      }
			      return city;
			   }


		public List<Doctor> doctorAreaList(Connection conn, String cityCode) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("doctorAreaList");
			List<Doctor> doctorAreaList = new ArrayList();
			Doctor d=null;
			try {
			         pstmt=conn.prepareStatement(sql);
			         pstmt.setString(1, cityCode);
			         rs = pstmt.executeQuery();
			         while(rs.next()) {
			        	 	d=new	Doctor();
			        	 	d.setDoctorNo(rs.getInt("doctor_no"));
							d.setDoctorId(rs.getString("doctor_id"));
							d.setDoctorPwd(rs.getString("doctor_pwd"));
							d.setDoctorLicense(rs.getString("doctor_license"));
							d.setDoctorName(rs.getString("doctor_name"));
							d.setDoctorHospital(rs.getString("doctor_hospital"));
							d.setDoctorAddress(rs.getString("doctor_address"));
							d.setDoctorPhone(rs.getString("doctor_phone"));
							d.setDoctorImg(rs.getString("doctor_img"));
							doctorAreaList.add(d);
						
			         }
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return doctorAreaList;
	
		}


		public List<Doctor> SeoulDoctorList(Connection conn) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("SeoulDoctorList");
			List<Doctor> SeoulDoctorList = new ArrayList();
			Doctor d=null;
			try {
			         pstmt=conn.prepareStatement(sql);
			         pstmt.setString(1, "서울특별시");
			         rs = pstmt.executeQuery();
			         while(rs.next()) {
			        	 	d=new Doctor();
			        	 	d.setDoctorNo(rs.getInt("doctor_no"));
							d.setDoctorId(rs.getString("doctor_id"));
							d.setDoctorPwd(rs.getString("doctor_pwd"));
							d.setDoctorLicense(rs.getString("doctor_license"));
							d.setDoctorName(rs.getString("doctor_name"));
							d.setDoctorHospital(rs.getString("doctor_hospital"));
							d.setDoctorAddress(rs.getString("doctor_address"));
							d.setDoctorPhone(rs.getString("doctor_phone"));
							d.setDoctorImg(rs.getString("doctor_img"));
							SeoulDoctorList.add(d);
						
			         }
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return SeoulDoctorList;
	
		}




		public int selectDoctorNameCount(Connection conn, String searchKeyword) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int result=0;
			String sql=prop.getProperty("selectDoctorNameCount");
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, searchKeyword);
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					result=rs.getInt("cnt");
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return result;
		}


		public int selectDoctorAddressCount(Connection conn, String searchKeyword) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int result=0;
			String sql=prop.getProperty("selectDoctorAddressCount");
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchKeyword+"%");
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					result=rs.getInt("cnt");
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return result;
		}


		public List<Doctor> doctorMapList(Connection conn, String cityName) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql = prop.getProperty("doctorMapList");
			List<Doctor> doctorList = new ArrayList();
			Doctor d=null;
			try {
			         pstmt=conn.prepareStatement(sql);
			         pstmt.setString(1, "%" + cityName + "%");
			         rs = pstmt.executeQuery();
			         while(rs.next()) {
			        	 	d=new Doctor();

							d.setDoctorName(rs.getString("doctor_name"));
							d.setDoctorHospital(rs.getString("doctor_hospital"));
							d.setDoctorAddress(rs.getString("doctor_address"));
							d.setDoctorPhone(rs.getString("doctor_phone"));
							d.setDoctorX(rs.getString("doctor_x"));
							d.setDoctorY(rs.getString("doctor_y"));
							doctorList.add(d);
						
			         }
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				close(rs);
				close(pstmt);
			}
			return doctorList;
	
		}
		
		
}