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

import com.petwork.model.vo.FreeComment;
import com.petwork.model.vo.FreeImg;
import com.petwork.model.vo.FreePost;

public class FreePostDao {
	
	Properties prop = new Properties();
	
	public FreePostDao() {
		String fileName = FreePostDao.class.getResource("./freePost.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//등록된 전체 리스트 가져오는 매소드
	public List<FreePost> selectList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectList");
		List<FreePost> list = new ArrayList<FreePost>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				freePost.setCount(rs.getInt("free_post_count"));
				list.add(freePost);
			}
			System.out.println(list);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<FreePost> choiceSelectList(Connection conn,int cPage, int numPerPage, String race_code){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("choiceSelectList");
		List<FreePost> list = new ArrayList<FreePost>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, race_code);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				freePost.setCount(rs.getInt("free_post_count"));
				list.add(freePost);
			}
			System.out.println(list);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int selectCount(Connection conn,String race_code) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="";
		if(race_code==null||race_code.equals("A")) {
			sql=prop.getProperty("selectAllCount");
		}
		else {
			sql=prop.getProperty("selectCount");
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
			if(!(race_code==null||race_code.equals("A"))) {
				pstmt.setString(1, race_code);
			}
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
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
		System.out.println("다오 :"+result);
		return result;
	}
	
	//자유게시판 사진 빼고 글 저장 매소드
	public int insertFreePost(Connection conn, FreePost freePost) {
		
		PreparedStatement pstmt = null;
		int result = 0; 
		String freePostSQL = prop.getProperty("insertFreePost");
		System.out.println("여긴 들어오니?");
		try {
			pstmt = conn.prepareStatement(freePostSQL);
			pstmt.setString(1, freePost.getFreePostTitle());
			pstmt.setString(2, freePost.getFreePostWriter());
			pstmt.setString(3, freePost.getFreePostContent());
			pstmt.setString(4, freePost.getRace_code());
			result = pstmt.executeUpdate();
			System.out.println("삽입문 확인들어가라 제발"+result);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int upload(Connection conn, FreeImg freeImg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("upload");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeImg.getFreeImgAddress());
			pstmt.setInt(2, freeImg.getFreePostNo());
			result = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public FreePost selectOne(Connection conn, FreePost freePost) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		FreePost rfreePost=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freePost.getFreePostTitle());
			pstmt.setString(2, freePost.getFreePostWriter());
			pstmt.setString(3, freePost.getRace_code());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rfreePost=new FreePost();
				rfreePost.setFreePostTitle(rs.getString("free_post_title"));
				rfreePost.setFreePostNo(rs.getInt("free_post_no"));
				rfreePost.setFreePostDate(rs.getDate("free_post_date"));
				rfreePost.setFreePostWriter(rs.getString("free_post_writer"));
				rfreePost.setFreePostContent(rs.getString("free_post_content"));
				rfreePost.setRace_code(rs.getString("race_code"));
				rfreePost.setCount(rs.getInt("free_post_count"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return rfreePost;
	}
	
	//상세페이지 구현 매소드(이미지를 받기 위해서 상속 받음)
	public List<FreePost> selectViewText(Connection conn, int freePostNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectView");
		List<FreePost> list= new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePostTotal=new FreePost();
				freePostTotal.setFreePostTitle(rs.getString("free_post_title"));
				freePostTotal.setFreePostNo(rs.getInt("free_post_no"));
				freePostTotal.setFreePostDate(rs.getDate("free_post_date"));
				freePostTotal.setFreePostWriter(rs.getString("free_post_writer"));
				freePostTotal.setFreePostContent(rs.getString("free_post_content"));
				freePostTotal.setRace_code(rs.getString("race_code"));
				freePostTotal.setCount(rs.getInt("free_post_count"));
				freePostTotal.setFreeImgNo(rs.getInt("free_img_no"));
				freePostTotal.setFreeImgAddress(rs.getString("free_img_address"));
				list.add(freePostTotal);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int updateFreePostImg(Connection conn, FreeImg freeImg, int index) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFreePostImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeImg.getFreeImgAddress());
			pstmt.setInt(2, freeImg.getFreePostNo());
			pstmt.setInt(3, freeImg.getFreeImgNo());
			result = pstmt.executeUpdate();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateFreePost(Connection conn, FreePost freePost, int index) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateFreePost");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freePost.getRace_code());
			pstmt.setString(2, freePost.getFreePostTitle());
			pstmt.setString(3, freePost.getFreePostContent());
			pstmt.setInt(4, freePost.getFreePostNo());
			result = pstmt.executeUpdate();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteFreePostImg(Connection conn, int freePostNo) {
		PreparedStatement pstmt = null;
		int result = 0; 
		String sql = prop.getProperty("deleteFreePostImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			result = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteFreePost(Connection conn, int freePostNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFreePost");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			result = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertComment(Connection conn, FreeComment freeComment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freeComment.getFreePostNo());
			pstmt.setInt(2, freeComment.getFreeCommentLevel());
			pstmt.setString(3, freeComment.getFreeCommentRef()==0?null:String.valueOf(freeComment.getFreeCommentRef()));
			pstmt.setString(4, freeComment.getFreeCommentWriter());
			pstmt.setString(5, freeComment.getFreeCommentContent());
			result = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	//댓글 정렬해서 리스트 보여주는 쿼리 문 실행 매소드
	public List<FreeComment> selectCommentList(Connection conn, int freePostNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCommentList");
		List<FreeComment> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeComment data = new FreeComment();
				data.setFreePostNo(rs.getInt("free_post_no"));
				data.setFreeCommentNo(rs.getInt("free_comment_no"));
				data.setFreeCommentLevel(rs.getInt("free_comment_level"));
				data.setFreeCommentRef(rs.getInt("free_comment_ref"));
				data.setFreeCommentWriter(rs.getString("free_comment_writer"));
				data.setFreeCommentDate(rs.getDate("free_comment_date"));
				data.setFreeCommentContent(rs.getString("free_comment_content"));
				list.add(data);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//댓글 삭제
	public int deleteFreeComment(Connection conn, int freePostNo, int freeCommentNo) {
		System.out.println("다오 들어 오니?");
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFreeComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			pstmt.setInt(2, freeCommentNo);
			result = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("다오 들어 오니?");
			close(pstmt);
		}
		return result;
	}
	
	//제목 검색 매소드
	public List<FreePost> selectFreePostTitle(Connection conn, String searchKeyword, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostTitle");
		List<FreePost> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setCount(rs.getInt("free_post_count"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				list.add(freePost);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//작성자 검색 매소드
	public List<FreePost> selectFreePostWriter(Connection conn, String searchKeyword, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostWriter");
		List<FreePost> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setCount(rs.getInt("free_post_count"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				list.add(freePost);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//내용 검색 매소드
	public List<FreePost> selectFreePostContent(Connection conn, String searchKeyword, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostContent");
		List<FreePost> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setCount(rs.getInt("free_post_count"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				list.add(freePost);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//제목 검색 카운트 매소드
	public int selectFreePostTitleCount(Connection conn, String searchKeyword){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostTitleCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//작성자 검색 카운트 매소드
	public int selectFreePostWriterCount(Connection conn, String searchKeyword){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostWriterCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//내용 검색 카운트 매소드
	public int selectFreePostContentCount(Connection conn, String searchKeyword){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFreePostContentCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//조회수 카운트 하는 매소드
	public int updateCount(Connection conn, int freePostNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freePostNo);
			result = pstmt.executeUpdate();
			System.out.println("카운트 값 결과 값 확인:"+result);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public List<FreePost> selectAdminList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdminList");
		List<FreePost> adminList = new ArrayList<FreePost>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreePost freePost = new FreePost();
				freePost.setFreePostNo(rs.getInt("free_post_no"));
				freePost.setFreePostTitle(rs.getString("free_post_title"));
				freePost.setFreePostWriter(rs.getString("free_post_writer"));
				freePost.setFreePostContent(rs.getString("free_post_content"));
				freePost.setRace_code(rs.getString("race_code"));
				freePost.setFreePostDate(rs.getDate("free_post_date"));
				freePost.setCount(rs.getInt("free_post_count"));
				adminList.add(freePost);
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return adminList;
	}
	
	

}
