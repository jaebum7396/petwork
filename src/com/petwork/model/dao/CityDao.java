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
import com.petwork.model.vo.District;

public class CityDao {
	
	Properties prop=new Properties();

	public CityDao(){
		String fileName=MemberDao.class.getResource("./city-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
	public List<City> selectCityList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<City> list=new ArrayList();
		String sql=prop.getProperty("selectCityList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				City c=new City();
				c.setCityCode(rs.getString("city_code"));
				c.setCityName(rs.getString("city_name"));
				list.add(c);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public List<District> selectDistrict(Connection conn, String cityCode) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<District> list=new ArrayList();
		String sql=prop.getProperty("selectDistrict");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cityCode);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				District d = new District();
				d.setCityCode(rs.getString("city_code"));
				d.setDistrictCode(rs.getString("district_code"));
				d.setDistrictName(rs.getString("district_name"));
				list.add(d);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
