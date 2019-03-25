package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreeComment;

/**
 * Servlet implementation class FreeBoardCommentInsertServlet
 */
@WebServlet("/board/freeCommentInsert")
public class FreeBoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게시판 번호
		int freePostNo = Integer.parseInt(request.getParameter("freePostNo"));
		String freeCommentWriter = request.getParameter("freeCommentWriter");
		
		int freeCommentLevel = Integer.parseInt(request.getParameter("freeCommentLevel"));
		int freeCommentRef = Integer.parseInt(request.getParameter("freeCommentRef"));
		String freeCommentContent = request.getParameter("freeCommentContent");
		
		
		FreeComment freeComment = new FreeComment();
		
		freeComment.setFreePostNo(freePostNo);
		freeComment.setFreeCommentWriter(freeCommentWriter);
		freeComment.setFreeCommentLevel(freeCommentLevel);
		freeComment.setFreeCommentRef(freeCommentRef);
		freeComment.setFreeCommentContent(freeCommentContent);
		
		int result = new FreePostService().insertComment(freeComment);
		
		String view = "";
		String loc = "";
		String msg ="";
		if(result>0) {
			//댓글 등록 성공!
			msg = "댓글등록 성공";
			loc = "/board/freeBoardForm?freePostNo="+freeComment.getFreePostNo();
			view="/views/common/msg.jsp";
		}
		else {
			msg = "댓글등록 실패";
			loc = "/board/freeBoardForm?freePostNo="+freeComment.getFreePostNo();
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
