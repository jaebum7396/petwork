package com.petwork.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class MyPageDao 
{
	Properties prop=new Properties();
	Pet pet=null;
	List<Pet> petList=null;
	int result=0;
	PreparedStatement pstmt=null;
	
	public MyPageDao()
	{
		String fileName=MyPageDao.class.getResource("./memberquery.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public List<Pet> myPageLoadingDao(Connection conn, Member m)
	{
		ResultSet rs=null;
		
		
		petList=new ArrayList();
		
		String sql=prop.getProperty("myPetLoad");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,m.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				pet=new Pet();
				
				pet.setPetNo(rs.getInt("pet_No"));
				pet.setPetName(rs.getString("pet_name"));
				pet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				pet.setPetBirth(rs.getDate("pet_birth"));
									
				petList.add(pet);
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
		
		return petList;
	}
	
	public List<Pet> myPagePetLoadingDao(Connection conn, int petNo)
	{
		ResultSet rs=null;		
		
		petList=new ArrayList();
		
		String sql=prop.getProperty("myPetDetailLoad");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		String birthString= null;		
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,petNo);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				pet=new Pet();
				
				pet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				pet.setPetName(rs.getString("pet_name"));
				pet.setAnimalName(rs.getString("animal_name"));
				pet.setPetBirthStringFormat(sdf.format(rs.getDate("pet_birth")));
				pet.setPetGender(rs.getString("pet_gender").charAt(0));
				pet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
									
				petList.add(pet);
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
		
		return petList;
	}
	
	
	
	public int myPageMemberUpdateDao(Connection conn, Member myPUpdate)
	{
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("myPageMemberUpdate");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, myPUpdate.getPw());
			pstmt.setString(2, myPUpdate.getPhone());
			pstmt.setString(3, myPUpdate.getEmail());
			pstmt.setString(4, myPUpdate.getAddress());
			pstmt.setString(5, myPUpdate.getName());
			pstmt.setString(6, myPUpdate.getId());
			
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
	
	public int myPageMemberDeleteDao(Connection conn, Member myPDel)
	{
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("myPageMemberDrop");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, "Y");
			pstmt.setString(2, myPDel.getId());
			
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
	
	public int myPagePetDeleteDao(Connection conn, int deletePetNo)
	{	
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("myPagePetDrop");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deletePetNo);
						
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
	

	public int insertPetDao(Connection conn, Pet insertPet)
	{
		String sql=prop.getProperty("insertMyPet");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, insertPet.getMemberId());
			pstmt.setString(2, insertPet.getPetIdentifyNo());
			pstmt.setString(3, String.valueOf(insertPet.getRaceCode()));
			pstmt.setString(4, insertPet.getAnimalNo());
			pstmt.setDate(5, insertPet.getPetBirth());
			pstmt.setString(6, String.valueOf(insertPet.getPetGender()));
			pstmt.setString(7, insertPet.getPetName());
			pstmt.setString(8, String.valueOf(insertPet.getPetNeutering()));
			
			result=pstmt.executeUpdate();
			
			if(result>0)
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
	
	public int updatePetDao(Connection conn, Pet insertPet)
	{
		String sql=prop.getProperty("myPagePetupdate");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, insertPet.getPetIdentifyNo());
			pstmt.setString(2, insertPet.getPetName());
			pstmt.setString(3, String.valueOf(insertPet.getPetNeutering()));
			pstmt.setInt(4, insertPet.getPetNo());
			
			result=pstmt.executeUpdate();
			
			if(result>0)
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
