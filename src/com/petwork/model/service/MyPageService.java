package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.MyPageDao;
import com.petwork.model.dao.SignUpDao;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class MyPageService 
{
	Connection conn=null;
	MyPageDao mpd=null;
	int result=0;
	
	public List<Pet> myPageLoadingService(Member m)
	{		
		conn=getConnection();
		mpd=new MyPageDao();
		List petList=mpd.myPageLoadingDao(conn,m);
		close(conn);
		return petList;
	}
	public List<Pet> myPagePetLoadingService(int petNo)
	{
		conn=getConnection();
		mpd=new MyPageDao();
		List petList=mpd.myPagePetLoadingDao(conn,petNo);
		close(conn);
		return petList;
	}
	
	public int myPageMemberUpdateService(Member myPUpdate)
	{
		conn=getConnection();
		mpd=new MyPageDao();
		result=mpd.myPageMemberUpdateDao(conn,myPUpdate);
		close(conn);
		return result;
	}
	
	public int myPageMemberDeleteService(Member myPDel)
	{
		conn=getConnection();
		mpd=new MyPageDao();
		result=mpd.myPageMemberDeleteDao(conn,myPDel);
		close(conn);
		return result;
	}
	
	public int myPagePetDeleteService(int deletePetNo)
	{
		conn=getConnection();
		mpd=new MyPageDao();
		result=mpd.myPagePetDeleteDao(conn, deletePetNo);
		close(conn);
		return result;		
	}
	
	public int insertPetService(Pet insertPet)
	{
		Connection conn=getConnection();
		mpd=new MyPageDao();
		int result=mpd.insertPetDao(conn, insertPet);
		close(conn);
		return result;
	}
	
	public int updatePetService(Pet insertPet)
	{
		Connection conn=getConnection();
		mpd=new MyPageDao();
		int result=mpd.updatePetDao(conn, insertPet);
		close(conn);
		return result;
	}
	
	
}
