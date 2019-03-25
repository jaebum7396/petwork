package com.petwork.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.petwork.model.dao.FaqDao;
import com.petwork.model.vo.Faq;

public class FaqService {
	
	public ArrayList<Faq> selectAll()
	{
		Connection conn = getConnection();
		ArrayList<Faq> list = new FaqDao().selectAll(conn);
		close(conn);
		return list;		
	}
	
	public int writeFaq(Faq f)
	{
		Connection conn = getConnection();
		int result = new FaqDao().writeFaq(conn, f);		
		if(result > 0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Faq selectFaq(int num) {
		Connection conn = getConnection();
		Faq f = new FaqDao().selectFaq(conn, num);		
		close(conn);
		return f;
	}

	public int updateFaq(Faq f) {
		Connection conn = getConnection();
		int result = new FaqDao().updateFaq(conn, f);
		if(result == 0)
		{
			rollback(conn);
		}
		else {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public int deleteFaq(int no) {
		
		Connection conn = getConnection();
		int result = new FaqDao().deleteFaq(conn,no);
		if(result == 0)
		{
			rollback(conn);
		}
		else {
			commit(conn);
		}
		return result;
	}
}
