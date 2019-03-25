package com.petwork.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.petwork.model.vo.FindPost;
import com.petwork.model.vo.ProtectPost;

public class MissingDao {

	Properties prop=new Properties();

	public MissingDao(){
		String fileName=MemberDao.class.getResource("./missing-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}

	//FindPost 찾아주세요
	public int insertFindPost(Connection conn, FindPost p) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("insertFindPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getMemberId());
			pstmt.setInt(2, p.getPetNo());
			pstmt.setString(3, p.getFindPostTitle());
			pstmt.setString(4, p.getFindPostContent());
			pstmt.setString(5, p.getFindPostMissingAddress());
			pstmt.setDate(6, new java.sql.Date(p.getFindPostMissingDate().getTime()));
			pstmt.setString(7, String.valueOf(p.getFindPostReward()));
			pstmt.setInt(8, p.getFindPostSum());
			pstmt.setString(9, p.getFindPostImgAddress());
			result=pstmt.executeUpdate();						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int selectFindPostCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectFindPostCount");
		try {
			pstmt=conn.prepareStatement(sql);
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

	public List<FindPost> selectFindPostList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<FindPost> list=new ArrayList<FindPost>();
		String sql=prop.getProperty("selectFindPostList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				FindPost p=new FindPost();
				p.setFindPostNo(rs.getInt("find_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setFindPostTitle(rs.getString("find_post_title"));
				p.setFindPostContent(rs.getString("find_post_content"));
				p.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				p.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				p.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				p.setFindPostSum(rs.getInt("find_post_sum"));
				p.setFindPostImgAddress(rs.getString("find_post_img_address"));
				p.setFindPostDate(rs.getDate("find_post_date"));
				p.setFindPostYn(rs.getString("find_post_yn").charAt(0));
				p.setAnimalName(rs.getString("animal_name"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				list.add(p);				
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

	public FindPost selectFindPost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FindPost f = null;
		String sql = prop.getProperty("selectFindPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				f = new FindPost();
				//순서 바꾸면 안됨! long형 데이터 먼저 읽기!
				f.setFindPostContent(rs.getString("find_post_content")); 
				f.setFindPostNo(rs.getInt("find_post_no"));
				f.setMemberId(rs.getString("member_id"));
				f.setMemberPhone(rs.getString("member_phone"));
				f.setFindPostTitle(rs.getString("find_post_title"));			
				f.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				f.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				f.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				f.setFindPostSum(rs.getInt("find_post_sum"));
				f.setFindPostImgAddress(rs.getString("find_post_img_address"));
				f.setFindPostDate(rs.getDate("find_post_date"));
				f.setFindPostYn(rs.getString("find_post_yn").charAt(0));
				f.setAnimalName(rs.getString("animal_name"));
				f.setPetIdentifyNo(rs.getString("pet_identify_no"));
				f.setPetName(rs.getString("pet_name"));
				f.setPetGender(rs.getString("pet_gender").charAt(0));
				f.setRaceCode(rs.getString("race_code").charAt(0));
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
		return f;
	}

	public boolean postDuplicateCheck(Connection conn, int petNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		String sql=prop.getProperty("postDuplicateCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, petNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result = true;
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

	public int updateFindPost(Connection conn, FindPost p) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("updateFindPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getFindPostTitle());
			pstmt.setString(2, p.getFindPostContent());
			pstmt.setString(3, p.getFindPostMissingAddress());
			pstmt.setDate(4, new java.sql.Date(p.getFindPostMissingDate().getTime()));
			pstmt.setString(5, String.valueOf(p.getFindPostReward()));
			pstmt.setInt(6, p.getFindPostSum());
			pstmt.setString(7, p.getFindPostImgAddress());
			pstmt.setInt(8, p.getFindPostNo());
			result=pstmt.executeUpdate();						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int finishFindPost(Connection conn, int postNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("finishFindPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int deleteFindPost(Connection conn, int postNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("deleteFindPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int selectFindPostCount(Connection conn, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectCategoryFindPostCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
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

	public List<FindPost> selectFindPostList(Connection conn, int cPage, int numPerPage, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<FindPost> list=new ArrayList<FindPost>();
		String sql=prop.getProperty("selectCategoryFindPostList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				FindPost p=new FindPost();
				p.setFindPostNo(rs.getInt("find_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setFindPostTitle(rs.getString("find_post_title"));
				p.setFindPostContent(rs.getString("find_post_content"));
				p.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				p.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				p.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				p.setFindPostSum(rs.getInt("find_post_sum"));
				p.setFindPostImgAddress(rs.getString("find_post_img_address"));
				p.setFindPostDate(rs.getDate("find_post_date"));
				p.setFindPostYn(rs.getString("find_post_yn").charAt(0));
				p.setAnimalName(rs.getString("animal_name"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				list.add(p);				
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

	public int selectFindPostCount(Connection conn, String searchCategory, String searchWord) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "";
		
		if(searchCategory.equals("member_id"))
		{
			sql = "SELECT COUNT(*) AS CNT FROM FIND JOIN MEMBER ON(FIND.MEMBER_ID = MEMBER.MEMBER_ID) JOIN PET USING(PET_NO) WHERE MEMBER.MEMBER_ID LIKE '%" + searchWord + "%'";
		}else {
			sql = "SELECT COUNT(*) AS CNT FROM FIND JOIN MEMBER ON(FIND.MEMBER_ID = MEMBER.MEMBER_ID) JOIN PET USING(PET_NO) WHERE "
					+ searchCategory + " LIKE '%" + searchWord + "%'";
		}
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
			close(stmt);
		}
		return result;
	}

	public List<FindPost> selectFindPostList(Connection conn, int cPage, int numPerPage, String searchCategory, String searchWord) {
		Statement stmt = null;
		ResultSet rs = null;
		List<FindPost> list=new ArrayList<FindPost>();
		String sql = "";
		
		if(searchCategory.equals("member_id")){
			sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM ((SELECT * FROM FIND F JOIN MEMBER M ON(F.MEMBER_ID = M.MEMBER_ID) JOIN PET USING(PET_NO) JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) WHERE M.MEMBER_ID LIKE %s ORDER BY FIND_POST_DATE DESC)) V )V WHERE RNUM BETWEEN %d AND %d",
					"'%"+searchWord+"%'", (cPage-1)*numPerPage+1, cPage*numPerPage);		
		}
		else {
			sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM ((SELECT * FROM FIND F JOIN MEMBER M ON(F.MEMBER_ID = M.MEMBER_ID) JOIN PET USING(PET_NO) JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) WHERE %s LIKE %s ORDER BY FIND_POST_DATE DESC)) V )V WHERE RNUM BETWEEN %d AND %d",
					searchCategory, "'%"+searchWord+"%'", (cPage-1)*numPerPage+1, cPage*numPerPage);
		}
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				FindPost p=new FindPost();
				p.setFindPostNo(rs.getInt("find_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setFindPostTitle(rs.getString("find_post_title"));
				p.setFindPostContent(rs.getString("find_post_content"));
				p.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				p.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				p.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				p.setFindPostSum(rs.getInt("find_post_sum"));
				p.setFindPostImgAddress(rs.getString("find_post_img_address"));
				p.setFindPostDate(rs.getDate("find_post_date"));
				p.setFindPostYn(rs.getString("find_post_yn").charAt(0));
				p.setAnimalName(rs.getString("animal_name"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				list.add(p);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public int selectCityFindPostCount(Connection conn, String cityCode) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "SELECT COUNT(*) AS CNT FROM FIND JOIN MEMBER USING(MEMBER_ID) JOIN PET USING(PET_NO)" 
				+ " WHERE SUBSTR(FIND_POST_MISSING_ADDRESS, 1, (INSTR(FIND_POST_MISSING_ADDRESS,' ')-1)) =" 
				+ " (SELECT CITY_NAME FROM CITY WHERE CITY_CODE = '" + cityCode + "')";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
			close(stmt);
		}
		return result;
	}

	public List<FindPost> selectCityFindPostList(Connection conn, int cPage, int numPerPage, String cityCode) {
		Statement stmt = null;
		ResultSet rs = null;
		List<FindPost> list=new ArrayList<FindPost>();

		String sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM "
				+ "((SELECT * FROM FIND F JOIN MEMBER M ON(F.MEMBER_ID = M.MEMBER_ID) "
				+ "JOIN PET USING(PET_NO)JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) "
				+ "WHERE SUBSTR(FIND_POST_MISSING_ADDRESS, 1, (INSTR(FIND_POST_MISSING_ADDRESS,' ')-1))"
				+ " = (SELECT CITY_NAME FROM CITY WHERE CITY_CODE = '%s') ORDER BY FIND_POST_DATE DESC)) V )V "
				+ "WHERE RNUM BETWEEN %d AND %d", cityCode, (cPage-1)*numPerPage+1, cPage*numPerPage);
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				FindPost p=new FindPost();
				p.setFindPostNo(rs.getInt("find_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setFindPostTitle(rs.getString("find_post_title"));
				p.setFindPostContent(rs.getString("find_post_content"));
				p.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				p.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				p.setFindPostReward(rs.getString("find_post_reward").charAt(0));
				p.setFindPostSum(rs.getInt("find_post_sum"));
				p.setFindPostImgAddress(rs.getString("find_post_img_address"));
				p.setFindPostDate(rs.getDate("find_post_date"));
				p.setFindPostYn(rs.getString("find_post_yn").charAt(0));
				p.setAnimalName(rs.getString("animal_name"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				list.add(p);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	//ProtectPost 보호중입니다
	public int selectProtectPostCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectProtectPostCount");
		try {
			pstmt=conn.prepareStatement(sql);
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
	
	public List<ProtectPost> selectProtectPostList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ProtectPost> list=new ArrayList<ProtectPost>();
		String sql=prop.getProperty("selectProtectPostList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ProtectPost p=new ProtectPost();
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setProtectPostImgAddress(rs.getString("protect_post_img_address"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setProtectPostYn(rs.getString("protect_post_yn").charAt(0));
				list.add(p);				
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

	public int selectProtectPostCount(Connection conn, String searchCategory, String searchWord) {		
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "";
		if(searchCategory.equals("pet_identify_no"))
		{
			sql ="SELECT COUNT(*) AS CNT FROM PROTECT WHERE PROTECT_POST_PET_IDENTIFY_NO LIKE '%" + searchWord + "%'";
		}
		else{
			sql ="SELECT COUNT(*) AS CNT FROM PROTECT JOIN MEMBER USING(MEMBER_ID) WHERE " + searchCategory + " LIKE '%" + searchWord + "%'";
		}
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
			close(stmt);
		}
		return result;
	}
	
	public int insertProtectPost(Connection conn, ProtectPost p) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("insertProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getMemberId());
			pstmt.setString(2, p.getProtectPostTitle());			
			pstmt.setString(3, String.valueOf(p.getRaceCode()));
			pstmt.setString(4, p.getAnimalNo());
			pstmt.setString(5, String.valueOf(p.getProtectPostPetGender()));
			pstmt.setString(6, p.getProtectPostContent());
			pstmt.setString(7, p.getProtectPostFindAddress());
			pstmt.setDate(8, new java.sql.Date(p.getProtectPostFindDate().getTime()));
			pstmt.setString(9, p.getProtectPostImgAddress());
			pstmt.setString(10, p.getProtectPetIdentifyNo());
		
			
			result=pstmt.executeUpdate();						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public ProtectPost selectProtectPost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProtectPost p = null;
		String sql = prop.getProperty("selectProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				p = new ProtectPost();
				//순서 바꾸면 안됨! long형 데이터 먼저 읽기!
				p.setProtectPostContent(rs.getString("protect_post_content")); 
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setMemberPhone(rs.getString("member_phone"));
				p.setProtectPostTitle(rs.getString("protect_post_title"));			
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setProtectPostImgAddress(rs.getString("protect_post_img_address"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setAnimalNo(rs.getString("animal_no"));
				p.setRaceCode(rs.getString("race_code").charAt(0));
				p.setProtectPostPetGender(rs.getString("protect_post_pet_gender").charAt(0));
				p.setProtectPetIdentifyNo(rs.getString("protect_post_pet_identify_no"));
				p.setProtectPostGiveDate(rs.getDate("protect_post_give_date"));
				p.setProtectPostGiveMemberId(rs.getString("protect_post_give_memberid"));
				p.setProtectPostDate(rs.getDate("protect_post_date"));
				p.setProtectPostYn(rs.getString("protect_post_yn").charAt(0));
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
		return p;
	}

	public boolean searchIdProtectPost(Connection conn, String id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		String sql=prop.getProperty("searchIdProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result = true;
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

	public int finishProtectPost(Connection conn, int postNo, String giveId) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("finishProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, giveId);
			pstmt.setInt(2, postNo);
			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int deleteProtectPost(Connection conn, int postNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("deleteProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;	
	}

	public int updateProtectPost(Connection conn, ProtectPost p) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("updateProtectPost");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getProtectPostTitle());		
			pstmt.setString(2, p.getProtectPostContent());
			pstmt.setString(3, p.getProtectPetIdentifyNo());
			pstmt.setString(4, String.valueOf(p.getProtectPostPetGender()));
			pstmt.setString(5, p.getAnimalNo());
			pstmt.setString(6, p.getProtectPostFindAddress());
			pstmt.setDate(7, new java.sql.Date(p.getProtectPostFindDate().getTime()));
			pstmt.setString(8, p.getProtectPostImgAddress());
			pstmt.setInt(9, p.getProtectPostNo());

			result=pstmt.executeUpdate();						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;
	}
	
	public int selectProtectPostCount(Connection conn, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectCategoryProtectPostCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
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

	public List<ProtectPost> selectProtectPostList(Connection conn, int cPage, int numPerPage, String category) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ProtectPost> list=new ArrayList<ProtectPost>();
		String sql=prop.getProperty("selectCategoryProtectPostList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ProtectPost p=new ProtectPost();
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setProtectPostImgAddress(rs.getString("protect_post_img_address"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setProtectPostYn(rs.getString("protect_post_yn").charAt(0));				
				list.add(p);				
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

	public List<ProtectPost> selectProtectPostList(Connection conn, int cPage, int numPerPage, String searchCategory,
			String searchWord) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ProtectPost> list=new ArrayList<ProtectPost>();
		String sql = "";
		
		if(searchCategory.equals("pet_identify_no"))
		{
			sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM ((SELECT * FROM PROTECT JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) WHERE PROTECT_POST_PET_IDENTIFY_NO LIKE %s ORDER BY PROTECT_POST_DATE DESC)) V )V WHERE RNUM BETWEEN %d AND %d",
					"'%"+searchWord+"%'", (cPage-1)*numPerPage+1, cPage*numPerPage);
		}else {
			sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM ((SELECT * FROM PROTECT JOIN MEMBER USING(MEMBER_ID) JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) WHERE %s LIKE %s ORDER BY PROTECT_POST_DATE DESC)) V )V WHERE RNUM BETWEEN %d AND %d",
					searchCategory, "'%"+searchWord+"%'", (cPage-1)*numPerPage+1, cPage*numPerPage);
		}
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ProtectPost p=new ProtectPost();
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setProtectPostImgAddress(rs.getString("protect_post_img_address"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setProtectPostYn(rs.getString("protect_post_yn").charAt(0));				

				list.add(p);					
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public int selectCityProtectPostCount(Connection conn, String cityCode) {
		
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql = "SELECT COUNT(*) AS CNT FROM PROTECT WHERE SUBSTR(PROTECT_POST_FIND_ADDRESS, 1, "
				+ "(INSTR(PROTECT_POST_FIND_ADDRESS,' ')-1)) = "
				+ "(SELECT CITY_NAME FROM CITY WHERE CITY_CODE = '" + cityCode + "')";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
			close(stmt);
		}
		return result;
		
	}

	public List<ProtectPost> selectCityProtectPostList(Connection conn, int cPage, int numPerPage, String cityCode) {
		Statement stmt = null;
		ResultSet rs = null;
		List<ProtectPost> list=new ArrayList<ProtectPost>();

		String sql = String.format("SELECT * FROM (SELECT ROWNUM AS RNUM, V.* FROM "
				+ "((SELECT * FROM PROTECT JOIN ANIMAL USING(RACE_CODE, ANIMAL_NO) "
				+ "WHERE SUBSTR(PROTECT_POST_FIND_ADDRESS, 1, (INSTR(PROTECT_POST_FIND_ADDRESS,' ')-1))"
				+ " = (SELECT CITY_NAME FROM CITY WHERE CITY_CODE = '%s') ORDER BY PROTECT_POST_DATE DESC)) V )V "
				+ "WHERE RNUM BETWEEN %d AND %d", cityCode, (cPage-1)*numPerPage+1, cPage*numPerPage);
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ProtectPost p=new ProtectPost();
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setProtectPostImgAddress(rs.getString("protect_post_img_address"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setProtectPostYn(rs.getString("protect_post_yn").charAt(0));	
				list.add(p);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public List<FindPost> selectFindPostId(Connection conn, String id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<FindPost> list=new ArrayList<FindPost>();
		String sql=prop.getProperty("selectFindPostId");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				FindPost p=new FindPost();
				p.setFindPostNo(rs.getInt("find_post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setPetNo(rs.getInt("pet_no"));
				p.setPetIdentifyNo(rs.getString("pet_identify_no"));
				p.setFindPostMissingAddress(rs.getString("find_post_missing_address"));
				p.setFindPostMissingDate(rs.getDate("find_post_missing_date"));
				p.setFindPostDate(rs.getDate("find_post_date"));
				p.setAnimalName(rs.getString("animal_name"));
				list.add(p);				
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

	public List<ProtectPost> selectProtectPostAllList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ProtectPost> list=new ArrayList<ProtectPost>();
		String sql=prop.getProperty("selectProtectPostAllList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ProtectPost p=new ProtectPost();
				p.setProtectPostNo(rs.getInt("protect_post_no"));
				p.setProtectPostFindAddress(rs.getString("protect_post_find_address"));
				p.setProtectPostFindDate(rs.getDate("protect_post_find_date"));
				p.setAnimalName(rs.getString("animal_name"));
				p.setProtectPetIdentifyNo(rs.getString("protect_post_pet_identify_no"));
				list.add(p);				
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

	public int updateProtectPostYn(Connection conn, String identifyNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("updateProtectPostYn");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, identifyNo);

			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}

		return result;
	}
}
