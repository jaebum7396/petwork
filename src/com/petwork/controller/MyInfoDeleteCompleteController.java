package com.petwork.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.MyPageService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class MyInfoDeleteCompleteController
 */
@WebServlet(name="MyInfoDeleteCompleteController", urlPatterns="/myinfodeletecompletecontroller")
public class MyInfoDeleteCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoDeleteCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkPassword=request.getParameter("checkPassword");
		
		HttpSession session=request.getSession();
		Member myPDel=(Member)session.getAttribute("loginMember");
		MyPageService mps=null;
		
		String msg=null;
		String loc=null;
		boolean checkPw=myPDel.getPw().equals(checkPassword);
		request.setAttribute("checkPw", checkPw);
		
		if(checkPw==true)
		{
			mps=new MyPageService();
			int result=mps.myPageMemberDeleteService(myPDel);
			
			msg="회원탈퇴가 완료 되었습니다. 메인 페이지로 이동합니다.";
			loc="/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd=request.getRequestDispatcher("/views/member/memberDropCheckPop.jsp");
			if(session!=null) {
				session.invalidate();
			}
			rd.forward(request, response);
		}
		if(checkPw==false)
		{
			msg="비밀번호를 확인하세요";
			request.setAttribute("msg", msg);
			RequestDispatcher rd=request.getRequestDispatcher("/views/member/memberDropCheckPop.jsp");
			rd.forward(request, response);
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
