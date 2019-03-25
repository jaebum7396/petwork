package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.FreePostService;

/**
 * Servlet implementation class FreeBoardCommentDeleteServlet
 */
@WebServlet("/board/freeCommentDelete")
public class FreeBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int freePostNo = Integer.parseInt(request.getParameter("freePostNo"));
		int freeCommentNo = Integer.parseInt(request.getParameter("del"));
		
		int result = new FreePostService().deleteFreeComment(freePostNo, freeCommentNo);
		
		if(result>0) {
			//댓글 삭제 성공
			request.getRequestDispatcher("/board/freeBoardForm").forward(request, response);
		}
		else {
			//댓글 삭제 실패
			request.getRequestDispatcher("/board/freeBoardForm").forward(request, response);
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
