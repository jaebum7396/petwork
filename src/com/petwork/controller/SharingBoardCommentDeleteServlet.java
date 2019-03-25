package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.SharingPostService;

/**
 * Servlet implementation class SharingBoardCommentDeleteServlet
 */
@WebServlet("/board/sharingCommentDelete")
public class SharingBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharingBoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sharingPostNo = Integer.parseInt(request.getParameter("sharingPostNo"));
		int sharingCommentNo = Integer.parseInt(request.getParameter("del"));
		
		int result = new SharingPostService().deleteSharingComment(sharingPostNo, sharingCommentNo);
		
		if(result>0) {
			//댓글 삭제 성공
			
			request.getRequestDispatcher("/board/sharingBoardForm").forward(request, response);
		}
		else {
			//댓글 삭제 실패
			
			request.getRequestDispatcher("/board/sharingBoardForm").forward(request, response);
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
