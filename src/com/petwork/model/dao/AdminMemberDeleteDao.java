package com.petwork.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.Pet;

public class AdminMemberDeleteDao 
{
	Properties prop=new Properties();
	Pet pet=null;
	List<Pet> petList=null;
	int result=0;
	String sql=null;
	PreparedStatement pstmt=null;
	
	public AdminMemberDeleteDao()
	{
		String fileName=AdminMemberDeleteDao.class.getResource("./memberquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public int adminMemberDeleteDao(Connection conn, String[] delMember)
	{
		PreparedStatement pstmt=null;
		
		switch(delMember.length)
		{
			case 1 : sql=prop.getProperty("adminMemberDelete1"); break;
			case 2 : sql=prop.getProperty("adminMemberDelete2"); break;
			case 3 : sql=prop.getProperty("adminMemberDelete3"); break;
			case 4 : sql=prop.getProperty("adminMemberDelete4"); break;
			case 5 : sql=prop.getProperty("adminMemberDelete5"); break;		 	
		}
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			switch(delMember.length)
			{
				case 1 : pstmt.setString(1, delMember[0]); break;
				case 2 : pstmt.setString(1, delMember[0]); pstmt.setString(2, delMember[1]); break;
				case 3 : pstmt.setString(1, delMember[0]); pstmt.setString(2, delMember[1]); pstmt.setString(3, delMember[2]); break;
				case 4 : pstmt.setString(1, delMember[0]); pstmt.setString(2, delMember[1]); pstmt.setString(3, delMember[2]); pstmt.setString(4, delMember[3]); break; 
				case 5 : pstmt.setString(1, delMember[0]); pstmt.setString(2, delMember[1]); pstmt.setString(3, delMember[2]); pstmt.setString(4, delMember[3]); pstmt.setString(5, delMember[4]);
			}			
						
			result=pstmt.executeUpdate();
			
			if(result!=0)
			{
				commit(conn);
			}
			else
			{
				rollback(conn);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);			
		}		
		return result;
	}
	
	public int adminPetDeleteDao(Connection conn, String[] delPet)
	{
		PreparedStatement pstmt=null;
		
		switch(delPet.length)
		{
			case 1 : sql=prop.getProperty("adminPetDelete1"); break;
			case 2 : sql=prop.getProperty("adminPetDelete2"); break;
			case 3 : sql=prop.getProperty("adminPetDelete3"); break;
			case 4 : sql=prop.getProperty("adminPetDelete4"); break;
			case 5 : sql=prop.getProperty("adminPetDelete5"); break;		 	
		}
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			switch(delPet.length)
			{
				case 1 : pstmt.setString(1, delPet[0]); break;
				case 2 : pstmt.setString(1, delPet[0]); pstmt.setString(2, delPet[1]); break;
				case 3 : pstmt.setString(1, delPet[0]); pstmt.setString(2, delPet[1]); pstmt.setString(3, delPet[2]); break;
				case 4 : pstmt.setString(1, delPet[0]); pstmt.setString(2, delPet[1]); pstmt.setString(3, delPet[2]); pstmt.setString(4, delPet[3]); break; 
				case 5 : pstmt.setString(1, delPet[0]); pstmt.setString(2, delPet[1]); pstmt.setString(3, delPet[2]); pstmt.setString(4, delPet[3]); pstmt.setString(5, delPet[4]);
			}			
						
			result=pstmt.executeUpdate();
			
			if(result!=0)
			{
				commit(conn);
			}
			else
			{
				rollback(conn);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);			
		}		
		return result;
	}

}
