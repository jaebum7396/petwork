package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.petwork.model.dao.QnaDao;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Qna;

public class QnaService {
	
	public Qna selectOne(Member m) {
		Connection conn = getConnection();
		
		Qna rqna = new QnaDao().selectOne(conn, m);
		
		close(conn);
		
		return rqna;
	}
	
	public Qna selectAdmin(String selctAdmin) {
		
		Connection conn = getConnection();
		
		Qna qna = new QnaDao().selectAdmin(conn, selctAdmin);
		
		close(conn);
		
		return qna;
	}

}
