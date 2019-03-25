package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.petwork.model.dao.SignUpDao;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class SignUpService 
{
	public int signUpService(Member signUpMember)
	{
		Connection conn=getConnection();
		SignUpDao sud=new SignUpDao();
		int result=sud.signUpDao(conn, signUpMember);
		close(conn);
		return result;		
	}
	
	
	
}
