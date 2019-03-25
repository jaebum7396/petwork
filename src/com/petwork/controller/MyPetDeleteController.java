package com.petwork.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class MyPetDeleteController
 */
@WebServlet("/mypetdeletecontroller")
public class MyPetDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPetDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Member currentMember=(Member)session.getAttribute("loginMember");
		
		int deletePetNo=Integer.parseInt(request.getParameter("myPetSel"));
		System.out.println(deletePetNo);
		MyPageService mps=null;
		String msg=null;
		String loc=null;
		mps=new MyPageService();
		int result=mps.myPagePetDeleteService(deletePetNo);
		
		List petList=mps.myPageLoadingService(currentMember);
		
		if(petList!=null)
		{
			request.setAttribute("petList", petList);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/views/member/mypage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
