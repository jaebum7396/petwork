package com.petwork.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petwork.model.dao.MemberDao;
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

public class MemberService 
{
	Connection conn=null;
	MemberDao ld=null;
	List<Member> memberList=null;
	List<Pet> petList=null;
	List<String> resultId=null;
	
	public Doctor doctorMemberService(Doctor inputDoc)
	{
		ld=new MemberDao();
		conn=getConnection();
		Doctor returnDoc=ld.doctorMemberDao(inputDoc, conn);
		close(conn);
		return returnDoc;
	}
	
	public Member memberService(Member inputData)
	{
		ld=new MemberDao();
		conn=getConnection();
		Member returnData=ld.memberDao(inputData, conn);
		close(conn);
		return returnData;
	}
	public List<String> findIdService(String inputPhone)
	{
		ld=new MemberDao();
		conn=getConnection();
		resultId=ld.findIdDao(inputPhone, conn);
		close(conn);
		return resultId;
	}	
	
	public int passwordRewordingService(Member m)
	{		
		ld=new MemberDao();
		conn=getConnection();
		int result=ld.passwordRewordingDao(m, conn);
		close(conn);
		return result;
	}
	
	public List<Member> memberListService(int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		memberList=ld.memberListDao(cPage, numPerPage, conn);
		close(conn);
		return memberList;
	}
	
	public int selectMemberCount()
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<Pet> petListService(int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();				
		petList=ld.petListDao(cPage, numPerPage, conn);
		close(conn);
		return petList;
	}
	
	public int selectPetCount()
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectPetCount(conn);
		close(conn);
		return result;
	}
	
	public List<Member> selectNameService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		memberList=ld.selectNameDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return memberList;
	}
	
	public int selectMemberNameCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectMemberNameCountDao(conn, searchKeyword);
		close(conn);
		return result;
	}	
	
	public List<Member> selectIdService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		memberList=ld.selectIdDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return memberList;
	}
	
	public int selectMemberIdCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<Pet> selectPetNameService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		petList=ld.selectPetNameDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return petList;
	}
	
	public int selectPetNameCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectPetNameCountDao(conn, searchKeyword);
		close(conn);
		return result;
	}	
	
	public List<Pet> selectPetIdService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		petList=ld.selectPetIdDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return petList;
	}
	
	public int selectPetIdCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectPetIdCountDao(conn, searchKeyword);
		close(conn);
		return result;
	}
	
	public List<Pet> selectPetYnService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		petList=ld.selectPetYnDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return petList;
	}
	
	public int selectPetYnCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectPetYnCountDao(conn, searchKeyword);
		close(conn);
		return result;
	}
	
	public List<Member> selectMemberYnService(String searchKeyword, int cPage, int numPerPage)
	{
		ld=new MemberDao();
		conn=getConnection();
		memberList=ld.selectMemberYnDao(searchKeyword, cPage, numPerPage, conn);
		close(conn);		
		return memberList;
	}
	
	public int selectMemberYnCountService(String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new MemberDao().selectMemberYnCountDao(conn,searchKeyword);
		close(conn);
		return result;
	}
	
	
}
