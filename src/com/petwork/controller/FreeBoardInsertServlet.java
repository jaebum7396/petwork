package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.vo.Member;

/**
 * Servlet implementation class FreeBoardInsertServlet
 */
@WebServlet("/board/freeBoardInsert")
public class FreeBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("loginMember");
		String loc = "";
		char admin = ' ';
		if(m!=null) {
			admin = m.getAdminYN();
			
		}
		
		String id = request.getParameter("loginMember");
		
		if(admin=='Y') {
			loc = "/views/board/admin_free_post_detail_write.jsp";
		}
		else{
			loc = "/views/board/free_post_write.jsp";
		}
		request.getRequestDispatcher(loc).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
