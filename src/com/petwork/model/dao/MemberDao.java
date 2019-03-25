package com.petwork.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class MemberDao 
{
	Properties prop=new Properties();
	Member returnMember=null;
	Doctor returnDoc=null;
	Pet returnPet=null;
	List<Member> memberList=null;
	List<Pet> petList=null;
	int result;
	List<String> resultId=null;
	String starId="";
	String star="";
	
	public MemberDao()
	{
		String fileName=MemberDao.class.getResource("./memberquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	public Doctor doctorMemberDao(Doctor inputDoc, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("doctorCheck");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,inputDoc.getDoctorId());
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				returnDoc=new Doctor
				(
					rs.getInt("doctor_no"),
					rs.getString("doctor_id"),
					rs.getString("doctor_pwd"),
					rs.getString("doctor_license"),
					rs.getString("doctor_name"),
					rs.getString("doctor_hospital"),
					rs.getString("doctor_address"),
					rs.getString("doctor_phone"),
					rs.getString("doctor_img")
				);
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
		
		return returnDoc;
	}
	
	public Member memberDao(Member inputData, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("loginCheck");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, inputData.getId());
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				returnMember=new Member
				(
					rs.getString("member_id"),
					rs.getString("member_pwd"),
					rs.getString("member_name"),
					rs.getDate("member_birth"),
					rs.getString("member_gender").charAt(0),
					rs.getString("member_phone"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_yn").charAt(0),
					rs.getDate("ent_date"),
					rs.getString("admin_yn").charAt(0)
				);
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
		
		return returnMember;
	}
	
	public List<String> findIdDao(String inputPhone,Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		resultId=new ArrayList();
		String sql=prop.getProperty("findId");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, inputPhone);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				String id=rs.getString("member_id");				
				
				for(int i=0; i<id.length()-3; i++)
				{
					star+="*";
				}
				starId=id.substring(0,3)+star;
				resultId.add(starId);
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
		
		return resultId;
	}
	
	
	
	public int passwordRewordingDao(Member m, Connection conn)
	{
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("passwordRewording");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			System.out.println("update sql: "+sql);
			
			pstmt.setString(1, m.getPw());
			pstmt.setString(2, m.getId());
			
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
	
	public int selectMemberCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "";
		
		sql=prop.getProperty("selectMemberCount");
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
	
	public List<Member> memberListDao(int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		memberList=new ArrayList();
		
		String sql=prop.getProperty("memberList");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnMember=new Member
				(
					rs.getString("member_id"),
					rs.getString("member_pwd"),
					rs.getString("member_name"),
					rs.getDate("member_birth"),
					rs.getString("member_gender").charAt(0),
					rs.getString("member_phone"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_yn").charAt(0),
					rs.getDate("ent_date"),
					rs.getString("admin_yn").charAt(0)
				);
				memberList.add(returnMember);
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
		return memberList;
	}
	
	public int selectPetCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "";
		
		sql=prop.getProperty("selectPetCount");
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
	
	public List<Pet> petListDao(int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		petList=new ArrayList();
		
		String sql=prop.getProperty("petList");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnPet=new Pet();
				
				returnPet.setMemberId(rs.getString("member_id"));
				returnPet.setPetName(rs.getString("pet_name"));
				returnPet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				returnPet.setAnimalName(rs.getString("animal_name"));
				returnPet.setPetBirth(rs.getDate("pet_birth"));
				returnPet.setPetGender(rs.getString("pet_gender").charAt(0));
				returnPet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
				returnPet.setPetNo(rs.getInt("pet_no"));
				returnPet.setPetYn(rs.getString("pet_yn").charAt(0));
				
				/*System.out.println(rs.getString("pet_yn"));*/
				/*
				if(rs.getString("pet_yn")!=null)
				{*/				
				petList.add(returnPet);
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
	
	public int selectMemberNameCountDao(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberNameCount");
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
	
	public int selectMemberIdCountDao(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberIdCount");
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
	
	public List<Member> selectNameDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		memberList=new ArrayList();
		
		String sql=prop.getProperty("selectMemberName");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnMember=new Member
				(
					rs.getString("member_id"),
					rs.getString("member_pwd"),
					rs.getString("member_name"),
					rs.getDate("member_birth"),
					rs.getString("member_gender").charAt(0),
					rs.getString("member_phone"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_yn").charAt(0),
					rs.getDate("ent_date"),
					rs.getString("admin_yn").charAt(0)
				);
				memberList.add(returnMember);
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
		return memberList;
	}
	
	
	public List<Member> selectIdDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		memberList=new ArrayList();
		
		String sql=prop.getProperty("selectMemberId");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnMember=new Member
				(
					rs.getString("member_id"),
					rs.getString("member_pwd"),
					rs.getString("member_name"),
					rs.getDate("member_birth"),
					rs.getString("member_gender").charAt(0),
					rs.getString("member_phone"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_yn").charAt(0),
					rs.getDate("ent_date"),
					rs.getString("admin_yn").charAt(0)
				);
				memberList.add(returnMember);
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
		return memberList;
	}
	
	public int selectPetNameCountDao(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectPetNameCount");
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
	
	public int selectPetIdCountDao(Connection conn, String searchKeyword)
	{
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectPetIdCount");
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
	
	public List<Pet> selectPetNameDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		petList=new ArrayList();
		
		String sql=prop.getProperty("selectPetName");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnPet=new Pet();
				
				returnPet.setMemberId(rs.getString("member_id"));
				returnPet.setPetName(rs.getString("pet_name"));
				returnPet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				returnPet.setAnimalName(rs.getString("animal_name"));
				returnPet.setPetBirth(rs.getDate("pet_birth"));
				returnPet.setPetGender(rs.getString("pet_gender").charAt(0));
				returnPet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
				returnPet.setPetNo(rs.getInt("pet_no"));
				returnPet.setPetYn(rs.getString("pet_yn").charAt(0));
				
				petList.add(returnPet);
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
	
	
	public List<Pet> selectPetIdDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		petList=new ArrayList();
		
		String sql=prop.getProperty("selectPetId");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnPet=new Pet();
				
				returnPet.setMemberId(rs.getString("member_id"));
				returnPet.setPetName(rs.getString("pet_name"));
				returnPet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				returnPet.setAnimalName(rs.getString("animal_name"));
				returnPet.setPetBirth(rs.getDate("pet_birth"));
				returnPet.setPetGender(rs.getString("pet_gender").charAt(0));
				returnPet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
				returnPet.setPetNo(rs.getInt("pet_no"));
				returnPet.setPetYn(rs.getString("pet_yn").charAt(0));
				
				petList.add(returnPet);
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
	
	public int selectPetYnCountDao(Connection conn, String searchKeyword)
	{
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectPetYnCount");
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
	
	public List<Pet> selectPetYnDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		petList=new ArrayList();
		
		String sql=prop.getProperty("selectPetYn");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnPet=new Pet();
				
				returnPet.setMemberId(rs.getString("member_id"));
				returnPet.setPetName(rs.getString("pet_name"));
				returnPet.setPetIdentifyNo(rs.getString("pet_identify_no"));
				returnPet.setAnimalName(rs.getString("animal_name"));
				returnPet.setPetBirth(rs.getDate("pet_birth"));
				returnPet.setPetGender(rs.getString("pet_gender").charAt(0));
				returnPet.setPetNeutering(rs.getString("pet_neutering").charAt(0));
				returnPet.setPetNo(rs.getInt("pet_no"));
				returnPet.setPetYn(rs.getString("pet_yn").charAt(0));
				
				petList.add(returnPet);
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
	
	public int selectMemberYnCountDao(Connection conn, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberYnCount");
		
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
	
	public List<Member> selectMemberYnDao(String searchKeyword, int cPage, int numPerPage, Connection conn)
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		memberList=new ArrayList();
		
		String sql=prop.getProperty("selectMemberYn");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1); //로우넘배오는거야
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnMember=new Member
				(
					rs.getString("member_id"),
					rs.getString("member_pwd"),
					rs.getString("member_name"),
					rs.getDate("member_birth"),
					rs.getString("member_gender").charAt(0),
					rs.getString("member_phone"),
					rs.getString("member_email"),
					rs.getString("member_address"),
					rs.getString("member_yn").charAt(0),
					rs.getDate("ent_date"),
					rs.getString("admin_yn").charAt(0)
				);
				memberList.add(returnMember);
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
		return memberList;
	}
}
