package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.FreePostDao;
import com.petwork.model.dao.SharingPostDao;
import com.petwork.model.vo.FreeComment;
import com.petwork.model.vo.FreeImg;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.SharingComment;
import com.petwork.model.vo.SharingImg;
import com.petwork.model.vo.SharingPost;

public class SharingPostService {
	
	//메인 리스트 보여주는 매소
	public List<SharingPost> selectList(int cPage, int numPerPage,String race_code,String product){
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectList(conn, cPage, numPerPage,race_code, product);
		
		System.out.println("디비 연결 됬나?");
		
		close(conn);
		
		return list;
	}
	
	//레이스 코드에 대한 페이징 개수 가져 오는 매소드
	public int selectCount(String race_code, String product) {
		Connection conn = getConnection();
		
		int result = new SharingPostDao().selectCount(conn, race_code, product);
		
		close(conn);
		System.out.println("서비스 :"+result);
		return result;
	}
	
	//레이스 코드 클릭 했을때 리스트 가져오는 매소드
	public List<SharingPost> choiceSelectList(int cPage, int numPerPage,String race_code){
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().choiceSelectList(conn,cPage,numPerPage,race_code);
		
		close(conn);
		
		return list;
	}
	
	//추가 하는 매소드
	public int insertSharingPost(SharingPost sharingPost){
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().insertSharingPost(conn, sharingPost);
		
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}
	
	//게시판 번호 리턴 하는 방법
	public SharingPost selectOne(SharingPost sharingPost) {
		
		Connection conn = getConnection();
		
		SharingPost rsharingPost = new SharingPostDao().selectOne(conn, sharingPost);
		
		close(conn);
		
		return rsharingPost;
	}
	
	//img 데이터 접속~!
	public int upload(SharingImg sharingImg) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().upload(conn, sharingImg);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//조회수 체크 매소드
	public List<SharingPost> selectView(int sharingPostNo ,boolean hasRead) {
		
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectViewText(conn, sharingPostNo);
		if(!hasRead) {
			int result = new SharingPostDao().updateCount(conn, sharingPostNo);
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
	
	//댓글 리스트 불러 오는 방법
	public List<SharingComment> selectCommentList(int sharingPostNo){
		Connection conn = getConnection();
		
		List<SharingComment> list = new SharingPostDao().selectCommentList(conn, sharingPostNo);
		
		close(conn);
		
		return list;
	}
	
	//게시판 번호 가지고 게시글 하나 가져오기
	public List<SharingPost> selectViewText(int sharingPostNo) {
		
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectViewText(conn, sharingPostNo);
		
		close(conn);
		
		return list;
	}
	
	public int updateSharingPostImg(SharingImg sharingImg, int index) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().updateSharingPostImg(conn, sharingImg, index);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int updateSharingPost(SharingPost sharingPost, int index) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().updateSharingPost(conn, sharingPost, index);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//제목 키워드 검색
	public List<SharingPost> selectSharingPostTitle(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectSharingPostTitle(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//작성자 키워드 검색
	public List<SharingPost> selectSharingPostWriter(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectSharingPostWriter(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//내용 키워드 검색
	public List<SharingPost> selectSharingPostContent(String searchKeyword, int cPage, int numPerPage){
		
		Connection conn = getConnection();
		
		List<SharingPost> list = new SharingPostDao().selectSharingPostContent(conn,searchKeyword,cPage,numPerPage);
		
		close(conn);
		
		return list;
	}
	
	//제목 키워드 검색 카운트
	public int selectSharingPostTitleCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().selectSharingPostTitleCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}
	
	//작성자 키워드 검색 카운트
	public int selectSharingPostWriterCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().selectSharingPostWriterCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}
	
	//내용 키워드 검색 카운트
	public int selectSharingPostContentiCount(String searchKeyword){
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().selectSharingPostContentiCount(conn,searchKeyword);
		
		close(conn);
		
		return result;
	}
	
	//사진 게시판 삭제 매소드
	public int deleteSharingPostImg(int sharingPostNo) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().deleteSharingPostImg(conn, sharingPostNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
		
	//사진게시판 삭제 후 게시판 삭제 매소드
	public int deleteSharingPost(int sharingPostNo) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().deleteSharingPost(conn, sharingPostNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//댓글 insert 매소드
	public int insertComment(SharingComment sharingComment) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().insertComment(conn, sharingComment);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//댓글 삭제
	public int deleteSharingComment(int sharingPostNo, int sharingCommentNo) {
		
		Connection conn = getConnection();
		
		int result = new SharingPostDao().deleteSharingComment(conn, sharingPostNo, sharingCommentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public List<SharingPost> selectAdminList() {
		Connection conn = getConnection();
		
		List<SharingPost> adminList = new SharingPostDao().selectAdminList(conn);
		
		close(conn);
		
		return adminList;
	}
	
	
}
