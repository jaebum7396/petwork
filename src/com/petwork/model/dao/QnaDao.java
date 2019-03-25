package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.petwork.model.vo.Member;
import com.petwork.model.vo.Qna;

public class QnaDao {
	
	Properties prop = new Properties();
	
	public QnaDao() {
		String fileName = QnaDao.class.getResource("./qna.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Qna selectAdmin(Connection conn, String selectAdmin) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdmin");
		Qna qnaAdmin = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectAdmin);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				qnaAdmin=new Qna();
				qnaAdmin.setId(rs.getString("member_id"));
				qnaAdmin.setName(rs.getString("member_name"));
				qnaAdmin.setPhone(rs.getString("member_phone"));
				qnaAdmin.setEmail(rs.getString("member_email"));
				qnaAdmin.setPw(rs.getString("member_pwd"));
				qnaAdmin.setAddress(rs.getString("member_address"));
				qnaAdmin.setBirth(rs.getDate("member_birth"));
				qnaAdmin.setGender(rs.getString("member_gender").charAt(0));
				qnaAdmin.setAdminYN(rs.getString("admin_yn").charAt(0));
				qnaAdmin.setMemberYN(rs.getString("member_yn").charAt(0));
				qnaAdmin.setEnrollDate(rs.getDate("ent_date"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaAdmin;
	}
	
	public Qna selectOne(Connection conn, Member m) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdmin");
		Qna rqna = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rqna=new Qna();
				rqna.setId(rs.getString("member_id"));
				rqna.setName(rs.getString("member_name"));
				rqna.setPhone(rs.getString("member_phone"));
				rqna.setEmail(rs.getString("member_email"));
				rqna.setPw(rs.getString("member_pwd"));
				rqna.setAddress(rs.getString("member_address"));
				rqna.setBirth(rs.getDate("member_birth"));
				rqna.setGender(rs.getString("member_gender").charAt(0));
				rqna.setAdminYN(rs.getString("admin_yn").charAt(0));
				rqna.setMemberYN(rs.getString("member_yn").charAt(0));
				rqna.setEnrollDate(rs.getDate("ent_date"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return rqna;
	}

}
