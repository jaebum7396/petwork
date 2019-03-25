package com.petwork.model.dao;

import static common.JDBCTemplate.close; 
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class SignUpDao 
{
	Properties prop=new Properties();
	PreparedStatement pstmt=null;
	int result=0;
	
	public SignUpDao()
	{
		String fileName=MemberDao.class.getResource("./memberquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}	
	
	public int signUpDao(Connection conn, Member signUpMember)
	{
		String sql=prop.getProperty("insertMember");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, signUpMember.getId());
			pstmt.setString(2, signUpMember.getPw());
			pstmt.setString(3, signUpMember.getName());
			pstmt.setDate(4, signUpMember.getBirth());
			pstmt.setString(5, String.valueOf(signUpMember.getGender()));
			pstmt.setString(6, signUpMember.getPhone());
			pstmt.setString(7, signUpMember.getEmail());
			pstmt.setString(8, signUpMember.getAddress());
			
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
