package com.petwork.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MemberService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class MyPasswordOverrideController
 */
@WebServlet(name="MyPasswordOverrideController", urlPatterns="/mypasswordoverridecontroller")
public class MyPasswordOverrideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPasswordOverrideController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetId=request.getParameter("modifyTargetId");
		String newPassword=request.getParameter("inputNewPassword");
		Member m=new Member();
		m.setId(targetId);
		m.setPw(newPassword);
		
		MemberService ms=new MemberService();
		
		int result=ms.passwordRewordingService(m);
		String msg=null;
		
		if(result>0)
		{
			msg="비밀번호가 성공적으로 변경되었습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("checkedM", m);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/views/member/emailSendPop.jsp").forward(request,response);
		}
		else
		{
			msg="비밀번호 변경실패하였습니다..";
			request.setAttribute("msg", msg);
			request.setAttribute("checkedM", m);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/views/member/emailSendPop.jsp").forward(request,response);
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
