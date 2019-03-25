package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.petwork.model.dao.AdminMemberDeleteDao;

public class AdminMemberDeleteService 
{
	Connection conn=null;
	AdminMemberDeleteDao amdd=null;
	int result;
	
	public int AdminMemberDeleteService(String[] delMember)
	{		
		conn=getConnection();
		amdd=new AdminMemberDeleteDao();
		result=amdd.adminMemberDeleteDao(conn,delMember);
		close(conn);
		return result;
	}
	
	public int AdminPetDeleteService(String[] delPet)
	{
		conn=getConnection();
		amdd=new AdminMemberDeleteDao();
		result=amdd.adminPetDeleteDao(conn,delPet);
		close(conn);
		return result;
	}
}
