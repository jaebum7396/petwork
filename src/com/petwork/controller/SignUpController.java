package com.petwork.controller;

import java.io.IOException; 
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.SignUpService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet(name="SignUpController",urlPatterns="/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String joinId=request.getParameter("joinId");
		String joinPw=request.getParameter("joinPw");
		String joinName=request.getParameter("joinName");
		String joinBirthString=request.getParameter("joinBirth");
		
		Date joinBirthDate=Date.valueOf(joinBirthString);
		char joinGender=request.getParameter("joinGender").charAt(0);
		String joinPhoneNum=request.getParameter("firstPhone")+request.getParameter("secondPhone")+request.getParameter("lastPhone");
		String joinEmail=request.getParameter("joinEmailId")+"@"+request.getParameter("joinEmailDomain");
		String joinAddress=request.getParameter("addSearch")+"/"+request.getParameter("addDetail");
			
		Member signUpMember =new Member();
		
		signUpMember.setId(joinId);
		signUpMember.setPw(joinPw);
		signUpMember.setName(joinName);
		signUpMember.setBirth(joinBirthDate);
		signUpMember.setGender(joinGender);
		signUpMember.setPhone(joinPhoneNum);
		signUpMember.setEmail(joinEmail);
		signUpMember.setAddress(joinAddress);
		
		SignUpService sus = new SignUpService();
		int result=sus.signUpService(signUpMember);
		
		String url="/views/common/msg.jsp";
		String msg="";
		String loc="";
		
		if(result<=0)
		{
			msg="회원 가입이 실패하였습니다.";
			loc="/views/member/signuppage.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(url);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			rd.forward(request,response);
		}
		else
		{
			msg="회원 가입 성공! 메인페이지로 이동합니다.";
			loc="/";
			RequestDispatcher rd=request.getRequestDispatcher(url);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			rd.forward(request,response);
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
