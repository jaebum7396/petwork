package com.petwork.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import com.petwork.model.vo.Faq;
import static common.JDBCTemplate.close;

public class FaqDao {
	
	Properties prop=new Properties();

	public FaqDao(){
		String fileName=FaqDao.class.getResource("./faq-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public ArrayList<Faq> selectAll(Connection conn)
	{
		PreparedStatement pstmt =null;		
		ResultSet rs = null;
		ArrayList<Faq> list = new ArrayList();
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Faq f = new Faq();
				f.setFaqNo(rs.getInt("faq_no"));
				f.setFaqTitle(rs.getString("faq_title"));
				f.setFaqContent(rs.getString("faq_content"));
				
				list.add(f);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int writeFaq(Connection conn, Faq f)
	{
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("writeFaq");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqContent());
			
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
				
	}

	public Faq selectFaq(Connection conn, int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFaq");
		Faq f = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				f = new Faq();
				f.setFaqContent(rs.getString("faq_content"));
				f.setFaqNo(rs.getInt("faq_no"));
				f.setFaqTitle(rs.getString("faq_title"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return f;
		
	}

	public int updateFaq(Connection conn, Faq f) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFaq");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFaqTitle());
			pstmt.setString(2, f.getFaqContent());
			pstmt.setInt(3, f.getFaqNo());
			
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteFaq(Connection conn , int no) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFaq");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		} 
		return result;
	}
}
