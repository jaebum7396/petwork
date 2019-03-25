package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.FreePostDao;
import com.petwork.model.vo.FreeComment;
import com.petwork.model.vo.FreeImg;
import com.petwork.model.vo.FreePost;

public class FreePostService {
	
	public List<FreePost> selectList(int cPage, int numPerPage){
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectList(conn, cPage, numPerPage);
		
		System.out.println("디비 연결 됬나?");
		
		close(conn);
		
		return list;
	}
	
	public List<FreePost> choiceSelectList(int cPage, int numPerPage,String race_code){
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().choiceSelectList(conn,cPage,numPerPage,race_code);
		
		close(conn);
		
		return list;
	}
	
	public int selectCount(String race_code) {
		Connection conn = getConnection();
		
		int result = new FreePostDao().selectCount(conn, race_code);
		
		close(conn);
		System.out.println("서비스 :"+result);
		return result;
	}
	
	public int insertFreePost(FreePost freePost){
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().insertFreePost(conn, freePost);
		
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}
	
	//img 데이터 접속~!
	public int upload(FreeImg freeImg) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().upload(conn, freeImg);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//게시판 번호 리턴 하는 방법
	public FreePost selectOne(FreePost freePost) {
		
		Connection conn = getConnection();
		
		FreePost rfreePost = new FreePostDao().selectOne(conn, freePost);
		
		close(conn);
		
		return rfreePost;
	}
	
	//게시판 번호 가지고 게시글 하나 가져오기
	public List<FreePost> selectViewText(int freePostNo) {
		
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectViewText(conn, freePostNo);
		
		close(conn);
		
		return list;
	}
	
	public List<FreePost> selectView(int freePostNo, boolean hasRead ) {
		
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectViewText(conn, freePostNo);
		if(!hasRead) {
			int result = new FreePostDao().updateCount(conn, freePostNo);
			System.out.println("조회수 값 확인 "+result);
			if(result>0) {
				System.out.println("커밋이니?");
				commit(conn);
			}
			else {
				System.out.println("롤뱃이니?");
				rollback(conn);
			}
		}
		close(conn);
		
		return list;
	}

	public int updateFreePostImg(FreeImg freeImg, int index) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().updateFreePostImg(conn, freeImg, index);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int updateFreePost(FreePost freePost, int index) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().updateFreePost(conn, freePost, index);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int deleteFreePostImg(int freePostNo) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().deleteFreePostImg(conn, freePostNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int deleteFreePost(int freePostNo) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().deleteFreePost(conn, freePostNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int insertComment(FreeComment freeComment) {
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().insertComment(conn, freeComment);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//댓글 리스트 불러 오는 방법
	public List<FreeComment> selectCommentList(int freePostNo){
		Connection conn = getConnection();
		
		List<FreeComment> list = new FreePostDao().selectCommentList(conn, freePostNo);
		
		close(conn);
		
		return list;
	}
	
	//댓글 삭제
	public int deleteFreeComment(int freePostNo, int freeCommentNo) {
		
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().deleteFreeComment(conn, freePostNo, freeCommentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//제목 키워드 검색
	public List<FreePost> selectFreePostTitle(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectFreePostTitle(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//작성자 키워드 검색
	public List<FreePost> selectFreePostWriter(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectFreePostWriter(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//내용 키워드 검색
	public List<FreePost> selectFreePostContent(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<FreePost> list = new FreePostDao().selectFreePostContent(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//제목 키워드 검색 카운트
	public int selectFreePostTitleCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().selectFreePostTitleCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}
	
	//작성자 키워드 검색 카운트
	public int selectFreePostWriterCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().selectFreePostWriterCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}
	
	//내용 키워드 검색 카운트
	public int selectFreePostContentCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new FreePostDao().selectFreePostContentCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}

	public List<FreePost> selectAdminList() {
		Connection conn = getConnection();
		
		List<FreePost> adminList = new FreePostDao().selectAdminList(conn);
		
		close(conn);
		
		return adminList;
	}
	

}
