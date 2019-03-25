package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.MissingDao;
import com.petwork.model.vo.FindPost;
import com.petwork.model.vo.ProtectPost;

public class MissingService {
	
	//FindPost
	public int insertFindPost(FindPost p)
	{
		Connection conn = getConnection();
		int result = new MissingDao().insertFindPost(conn, p);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public FindPost selectFindPost(int postNo) {
		Connection conn = getConnection();
		FindPost post = new MissingDao().selectFindPost(conn, postNo);
		close(conn);
		return post;
	}

	public boolean postDuplicateCheck(int petNo) {
		Connection conn = getConnection();
		boolean result = new MissingDao().postDuplicateCheck(conn, petNo);
		close(conn);
		return result;
	}

	public int updateFindPost(FindPost p) {
		Connection conn = getConnection();
		int result = new MissingDao().updateFindPost(conn, p);
		close(conn);
		return result;
	}

	// 실종동물을 찾은 경우
	public int finishFindPost(int postNo) {
		Connection conn = getConnection();
		int result = new MissingDao().finishFindPost(conn, postNo);
		close(conn);
		return result;
	}

	public int deleteFindPost(int postNo) {
		Connection conn = getConnection();
		int result = new MissingDao().deleteFindPost(conn, postNo);
		close(conn);
		return result;
	}

	public List<FindPost> selectFindPostList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FindPost> list = new MissingDao().selectFindPostList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public List<FindPost> selectFindPostList(int cPage, int numPerPage, String category) {
		Connection conn = getConnection();
		List<FindPost> list = new MissingDao().selectFindPostList(conn,cPage,numPerPage,category);
		close(conn);
		return list;
	}

	public List<FindPost> selectFindPostList(int cPage, int numPerPage, String searchCategory, String searchWord){
		Connection conn = getConnection();
		List<FindPost> list = new MissingDao().selectFindPostList(conn, cPage, numPerPage, searchCategory, searchWord);
		close(conn);
		return list;
	}
	
	public List<FindPost> selectCityFindPostList(int cPage, int numPerPage, String cityCode) {
		Connection conn = getConnection();
		List<FindPost> list = new MissingDao().selectCityFindPostList(conn,cPage,numPerPage,cityCode);
		close(conn);
		return list;
	}

	
	public int selectFindPostCount() {
		Connection conn = getConnection();
		int result = new MissingDao().selectFindPostCount(conn);
		close(conn);
		return result;
	}

	public int selectFindPostCount(String category) {
		Connection conn = getConnection();
		int result = new MissingDao().selectFindPostCount(conn, category);
		close(conn);
		return result;
	}
	
	public int selectFindPostCount(String searchCategory, String searchWord) {
		Connection conn = getConnection();
		int result = new MissingDao().selectFindPostCount(conn, searchCategory, searchWord);
		close(conn);
		return result;
	}

	public int selectCityFindPostCount(String cityCode) {
		Connection conn = getConnection();
		int result = new MissingDao().selectCityFindPostCount(conn, cityCode);
		close(conn);
		return result;
	}
	
	
	//ProtectPost
	public int selectProtectPostCount(String searchCategory, String searchWord) {
		Connection conn = getConnection();
		int result = new MissingDao().selectProtectPostCount(conn, searchCategory, searchWord);
		close(conn);
		return result;
	}

/*	public List<ProtectPost> selectProtectPostList(int cPage, int numPerPage, String searchCategory, String searchWord){
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectProtectPostList(conn, cPage, numPerPage, searchCategory, searchWord);
		close(conn);
		return list;
	}*/

	public int selectProtectPostCount() {
		Connection conn = getConnection();
		int result = new MissingDao().selectProtectPostCount(conn);
		close(conn);
		return result;
	}
	
	public List<ProtectPost> selectProtectPostList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectProtectPostList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public int selectProtectPostCount(String category) {
		Connection conn = getConnection();
		int result = new MissingDao().selectProtectPostCount(conn, category);
		close(conn);
		return result;
	}
	
	public int selectCityProtectPostCount(String cityCode) {
		Connection conn = getConnection();
		int result = new MissingDao().selectCityProtectPostCount(conn, cityCode);
		close(conn);
		return result;
	}
	
	public int insertProtectPost(ProtectPost p)
	{
		Connection conn = getConnection();
		int result = new MissingDao().insertProtectPost(conn, p);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public ProtectPost selectProtectPost(int postNo) {
		Connection conn = getConnection();
		ProtectPost post = new MissingDao().selectProtectPost(conn, postNo);
		close(conn);
		return post;
	}

	public boolean searchIdProtectPost(String id) {
		Connection conn = getConnection();
		boolean result = new MissingDao().searchIdProtectPost(conn, id);
		close(conn);
		return result;
	}

	//실종동물을 돌려준 경우
	public int finishProtectPost(int postNo, String giveId) {
		Connection conn = getConnection();
		int result = new MissingDao().finishProtectPost(conn, postNo, giveId);
		close(conn);
		return result;
	}

	public int deleteProtectPost(int postNo) {
		Connection conn = getConnection();
		int result = new MissingDao().deleteProtectPost(conn, postNo);
		close(conn);
		return result;
	}

	public int updateProtectPost(ProtectPost p) {
		Connection conn = getConnection();
		int result = new MissingDao().updateProtectPost(conn, p);
		close(conn);
		return result;
	}

	public List<ProtectPost> selectProtectPostList(int cPage, int numPerPage, String category) {
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectProtectPostList(conn,cPage,numPerPage,category);
		close(conn);
		return list;
	}

	public List<ProtectPost> selectProtectPostList(int cPage, int numPerPage, String searchCategory, String searchWord) {
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectProtectPostList(conn, cPage, numPerPage, searchCategory, searchWord);
		close(conn);
		return list;
	}

	public List<ProtectPost> selectCityProtectPostList(int cPage, int numPerPage, String cityCode) {
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectCityProtectPostList(conn,cPage,numPerPage,cityCode);
		close(conn);
		return list;
	}

	public List<FindPost> selectFindPostId(String id) {
		Connection conn = getConnection();
		List<FindPost> list = new MissingDao().selectFindPostId(conn, id);
		close(conn);
		return list;
	}

	public List<ProtectPost> selectProtectPostAllList() {
		Connection conn = getConnection();
		List<ProtectPost> list = new MissingDao().selectProtectPostAllList(conn);
		close(conn);
		return list;
	}

	public int updateProtectPostYn(String identifyNo) {
		Connection conn = getConnection();
		int result = new MissingDao().updateProtectPostYn(conn, identifyNo);
		close(conn);
		return result;
	}
}
