package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.Animal;

public class MyPageAnimalSelDao {
	
	Properties prop=new Properties();
	List<Animal> animalList=null;
	Animal race=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	String animalName=null;
	
	public MyPageAnimalSelDao()
	{
		String fileName=MemberDao.class.getResource("./memberquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public List<Animal> myPageAnimalSelDao(String inputRace, Connection conn)
	{
		
		animalList=new ArrayList();
		
		String sql=prop.getProperty("myPageAnimalSel");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,inputRace);
			rs=pstmt.executeQuery();
			int i=0;
			while(rs.next())
			{
				race=new Animal();
				race.setAnimalName(rs.getString("animal_name"));
				race.setAnimalNo(rs.getString("animal_no"));
				
				animalList.add(race);
				
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return animalList;
	}
	
	public String myPageReturnRaceDao(String raceCode, String animalNo, Connection conn)
	{
		String sql=prop.getProperty("myPageAnimalSel");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, raceCode);
			pstmt.setString(2, animalNo);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				animalName=rs.getString("animal_name");								
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		
		return animalName;
	}

}
