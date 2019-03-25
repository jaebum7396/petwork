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


import com.petwork.model.vo.SharingComment;
import com.petwork.model.vo.SharingImg;
import com.petwork.model.vo.SharingPost;

public class SharingPostDao {
	
Properties prop = new Properties();
	
	public SharingPostDao() {
		String fileName = SharingPostDao.class.getResource("./sharingPost.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//등록된 전체 리스트 가져오는 매소드
	public List<SharingPost> selectList(Connection conn, int cPage, int numPerPage,String race_code, String product){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		if(race_code.equals("A")&&product.equals("allProduct")) {
			 sql = prop.getProperty("selectListAll");
		}else if(!race_code.equals("A")) {
			sql = prop.getProperty("selectListRaceCode");
		}
		else if(!product.equals("allProduct")){
			sql = prop.getProperty("selectListProduct");
		}
		
		List<SharingPost> list = new ArrayList<SharingPost>();
		try {
			pstmt = conn.prepareStatement(sql);
			if(race_code.equals("A")&&product.equals("allProduct")) {
				pstmt.setInt(1, (cPage-1)*numPerPage+1);
				pstmt.setInt(2, cPage*numPerPage);
			}else if(!race_code.equals("A")&&product.equals("allProduct")) {
				pstmt.setString(1, race_code);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
			}else {
				pstmt.setString(1, product);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SharingPost sharingPost = new SharingPost();
				sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
				sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
				sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
				sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
				sharingPost.setRace_code(rs.getString("race_code"));
				sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
				sharingPost.setCount(rs.getInt("sharing_post_count"));					
				sharingPost.setSharingPostAddress(rs.getString("sharing_post_address"));					
				sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
				sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
				list.add(sharingPost);
				}
				System.out.println(list);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public int selectCount(Connection conn,String race_code, String product) {
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int result=0;
			String sql="";
			if(product.equals("allProduct")&&race_code.equals("A")) {
				sql=prop.getProperty("selectAllCount");
			}
			else if(!race_code.equals("A")&&product.equals("allProduct")){
				System.out.println("이상한곳으로 오네");
				sql=prop.getProperty("selectCountRaceCode");
			}
			else {
				System.out.println("여기 들어 왔니?");
				sql=prop.getProperty("selectCountProduct");
			}
			
			try {
				pstmt=conn.prepareStatement(sql);
				if(!(race_code==null||race_code.equals("A"))) {
					pstmt.setString(1, race_code);
				}else if(!(product==null||product.equals("allProduct"))){
					pstmt.setString(1, product);
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
			
			return result;
		}
		
		public List<SharingPost> choiceSelectList(Connection conn,int cPage, int numPerPage, String race_code){
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("choiceSelectList");
			List<SharingPost> list = new ArrayList<SharingPost>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, race_code);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost = new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("sharing_post_address"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					list.add(sharingPost);
				}
				System.out.println(list);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//나눔게시판 사진 빼고 글 저장 매소드
		public int insertSharingPost(Connection conn, SharingPost sharingPost) {
			
			PreparedStatement pstmt = null;
			int result = 0; 
			String sharingPostSQL = prop.getProperty("insertSharingPost");
			
			try {
				pstmt = conn.prepareStatement(sharingPostSQL);
				pstmt.setString(1, sharingPost.getSharingPostTitle());
				pstmt.setString(2, sharingPost.getSharingPostWriter());
				pstmt.setString(3, sharingPost.getSharingPostAddress());
				pstmt.setString(4, sharingPost.getSharingPostContent());
				pstmt.setString(5, sharingPost.getProductCategoryCode());
				pstmt.setString(6, sharingPost.getRace_code());
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
		
		//객체 하나 가지고 오기
		public SharingPost selectOne(Connection conn, SharingPost sharingPost) {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectOne");
			SharingPost rsharingPost=null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sharingPost.getSharingPostTitle());
				pstmt.setString(2, sharingPost.getSharingPostWriter());
				pstmt.setString(3, sharingPost.getRace_code());
				pstmt.setString(4, sharingPost.getSharingPostAddress());
				pstmt.setString(5, sharingPost.getProductCategoryCode());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					rsharingPost=new SharingPost();
					rsharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					rsharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					rsharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					rsharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					rsharingPost.setRace_code(rs.getString("race_code"));
					rsharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					rsharingPost.setCount(rs.getInt("sharing_post_count"));					
					rsharingPost.setSharingPostAddress(rs.getString("sharing_post_address"));					
					rsharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					rsharingPost.setProductCategoryCode(rs.getString("product_category_code"));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				close(rs);
				close(pstmt);
			}
			return rsharingPost;
		}
		
		public int upload(Connection conn, SharingImg sharingImg) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("upload");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sharingImg.getSharingImgAddress());
				pstmt.setInt(2, sharingImg.getSharingPostNo());
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
		
		//상세페이지 구현 매소드(이미지를 받기 위해서 상속 받음)
		public List<SharingPost> selectViewText(Connection conn, int sharingPostNo) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectView");
			List<SharingPost> list= new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost=new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("SHARING_POST_ADDRESS"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					sharingPost.setSharingImgNo(rs.getInt("sharing_img_no"));
					sharingPost.setSharingImgAddress(rs.getString("sharing_img_address"));
					sharingPost.setProductCategoryName(rs.getString("PRODUCT_CATEGORY_NAME"));
					list.add(sharingPost);
					System.out.println("하나 객체 가져온거 다오에서 확인 :"+sharingPost);
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
		
		//조회수 카운트 하는 매소드
		public int updateCount(Connection conn, int sharingPostNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateCount");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
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
		
		//댓글 정렬해서 리스트 보여주는 쿼리 문 실행 매소드
		public List<SharingComment> selectCommentList(Connection conn, int sharingPostNo){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectCommentList");
			List<SharingComment> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingComment data = new SharingComment();
					data.setSharingPostNo(rs.getInt("sharing_post_no"));
					data.setSharingCommentNo(rs.getInt("sharing_comment_no"));
					data.setSharingCommentLevel(rs.getInt("sharing_comment_level"));
					data.setSharingCommentRef(rs.getInt("sharing_comment_ref"));
					data.setSharingCommentWriter(rs.getString("sharing_comment_writer"));
					data.setSharingCommentDate(rs.getDate("sharing_comment_date"));
					data.setSharingCommentContent(rs.getString("sharing_comment_content"));
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
		
		public int updateSharingPostImg(Connection conn, SharingImg sharingImg, int index) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateSharingPostImg");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sharingImg.getSharingImgAddress());
				pstmt.setInt(2, sharingImg.getSharingPostNo());
				pstmt.setInt(3, sharingImg.getSharingImgNo());
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
		
		public int updateSharingPost(Connection conn, SharingPost sharingPost, int index) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateSharingPost");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sharingPost.getRace_code());
				pstmt.setString(2, sharingPost.getSharingPostTitle());
				pstmt.setString(3, sharingPost.getSharingPostContent().trim());
				pstmt.setString(4, sharingPost.getSharingPostAddress());
				pstmt.setString(5, sharingPost.getProductCategoryCode());
				pstmt.setString(6, sharingPost.getSharingPostYN());
				pstmt.setInt(7, sharingPost.getSharingPostNo());
				pstmt.setString(8, sharingPost.getSharingPostWriter());
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
		
		//제목 검색 매소드
		public List<SharingPost> selectSharingPostTitle(Connection conn, String searchKeyword, int cPage, int numPerPage){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostTitle");
			List<SharingPost> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchKeyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost = new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("SHARING_POST_ADDRESS"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					list.add(sharingPost);
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
		public List<SharingPost> selectSharingPostWriter(Connection conn, String searchKeyword, int cPage, int numPerPage){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostWriter");
			List<SharingPost> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchKeyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost = new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("SHARING_POST_ADDRESS"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					list.add(sharingPost);
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
		public List<SharingPost> selectSharingPostContent(Connection conn, String searchKeyword, int cPage, int numPerPage){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostContent");
			List<SharingPost> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchKeyword+"%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost = new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("SHARING_POST_ADDRESS"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					list.add(sharingPost);
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
		public int selectSharingPostTitleCount(Connection conn, String searchKeyword){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostTitleCount");
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
		public int selectSharingPostWriterCount(Connection conn, String searchKeyword){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostWriterCount");
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
		public int selectSharingPostContentiCount(Connection conn, String searchKeyword){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = prop.getProperty("selectSharingPostContentiCount");
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
		
		//게시판 삭제 전에 이미지 삭제 
		public int deleteSharingPostImg(Connection conn, int sharingPostNo) {
			PreparedStatement pstmt = null;
			int result = 0; 
			String sql = prop.getProperty("deleteSharingPostImg");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
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
		
		//게시판 삭제 
		public int deleteSharingPost(Connection conn, int sharingPostNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("deleteSharingPost");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
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
		
		//댓글 등록
		public int insertComment(Connection conn, SharingComment sharingComment) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertComment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingComment.getSharingCommentLevel());
				pstmt.setString(2, sharingComment.getSharingCommentRef()==0?null:String.valueOf(sharingComment.getSharingCommentRef()));
				pstmt.setString(3, sharingComment.getSharingCommentWriter());
				pstmt.setString(4, sharingComment.getSharingCommentContent());
				pstmt.setInt(5, sharingComment.getSharingPostNo());
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
		
		//댓글 삭제
		public int deleteSharingComment(Connection conn, int sharingPostNo, int sharingCommentNo) {
			System.out.println("다오 들어 오니?");
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("deleteSharingComment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sharingPostNo);
				pstmt.setInt(2, sharingCommentNo);
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

		public List<SharingPost> selectAdminList(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql=prop.getProperty("selectAdminList");
			
			List<SharingPost> adminList = new ArrayList<SharingPost>();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SharingPost sharingPost = new SharingPost();
					sharingPost.setSharingPostNo(rs.getInt("sharing_post_no"));
					sharingPost.setSharingPostTitle(rs.getString("sharing_post_title"));
					sharingPost.setSharingPostWriter(rs.getString("sharing_post_writer"));
					sharingPost.setSharingPostContent(rs.getString("sharing_post_content"));
					sharingPost.setRace_code(rs.getString("race_code"));
					sharingPost.setSharingPostDate(rs.getDate("sharing_post_date"));
					sharingPost.setCount(rs.getInt("sharing_post_count"));					
					sharingPost.setSharingPostAddress(rs.getString("sharing_post_address"));					
					sharingPost.setSharingPostYN(rs.getString("sharing_post_yn"));
					sharingPost.setProductCategoryCode(rs.getString("product_category_code"));
					adminList.add(sharingPost);
					}
					System.out.println(adminList);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				return adminList;
		}
		
}
