package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.SharingPostService;
import com.petwork.model.vo.SharingComment;



/**
 * Servlet implementation class SharingBoardCommentInsertServlet
 */
@WebServlet("/board/sharingCommentInsert")
public class SharingBoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharingBoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게시판 번호
		int sharingPostNo = Integer.parseInt(request.getParameter("sharingPostNo"));
		String sharingCommentWriter = request.getParameter("sharingCommentWriter");
		
		int sharingCommentLevel = Integer.parseInt(request.getParameter("sharingCommentLevel"));
		int sharingCommentRef = Integer.parseInt(request.getParameter("sharingCommentRef"));
		String sharingCommentContent = request.getParameter("sharingCommentContent");
		
		SharingComment sharingComment = new SharingComment();
		
		sharingComment.setSharingPostNo(sharingPostNo);
		sharingComment.setSharingCommentWriter(sharingCommentWriter);
		sharingComment.setSharingCommentLevel(sharingCommentLevel);
		sharingComment.setSharingCommentRef(sharingCommentRef);
		sharingComment.setSharingCommentContent(sharingCommentContent);
		
		int result = new SharingPostService().insertComment(sharingComment);
		
		String view = "";
		String loc = "";
		String msg ="";
		
		if(result>0) {
			//댓글 등록 성공!
			msg = "댓글등록 성공";
			loc = "/board/sharingBoardForm?sharingPostNo="+sharingComment.getSharingPostNo();
			view="/views/common/msg.jsp";
			
		}
		else {
			msg = "댓글등록 실패";
			loc = "/board/sharingBoardForm?sharingPostNo="+sharingComment.getSharingPostNo();
			view="/views/common/msg.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
