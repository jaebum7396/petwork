package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class FreeBoardUpdate
 */
@WebServlet("/board/freeBoardUpdate")
public class FreeBoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		char admin=' ';
		String loc = "";
		if(loginMember !=null) {
			admin = loginMember.getAdminYN();
		}
		
		int freePostNo = Integer.parseInt(request.getParameter("freePostNo"));
		
		List<FreePost> list = new FreePostService().selectViewText(freePostNo);
		
		request.setAttribute("list", list);
		
		if(admin=='Y') {
			loc="/views/board/admin_free_post_detail_write_update.jsp";
		}
		else {
			loc = "/views/board/free_post_detail_write_update.jsp";
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
