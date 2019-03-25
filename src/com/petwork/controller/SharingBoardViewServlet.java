package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.SharingPostService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.SharingComment;
import com.petwork.model.vo.SharingPost;

/**
 * Servlet implementation class SharingBoardViewServlet
 */
@WebServlet("/board/sharingBoardForm")
public class SharingBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SharingBoardViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		////////////////////////////////////////////////////////////
		// 상세 화면 보여주는 서블릿
		///////////////////////////////////////////////////////////
		char admin=' ';
		
		int sharingPostNo = Integer.parseInt(request.getParameter("sharingPostNo"));
		
		Cookie[] cookies = request.getCookies(); // 쿠키받기
		String boardCookieVal = ""; // 쿠키값 저장할거
		boolean hasRead = false; // false면 글을 안일고 트루면 읽음
		Member loginMember = (Member) request.getSession().getAttribute("loginMember");
		
		//관리자 인지 유무 
		if(loginMember!=null) {
			admin=loginMember.getAdminYN();
		}
		
		if (cookies != null) {
			outer: for (Cookie c : cookies) {
				String name = c.getName();
				
				String value = c.getValue();
				
				if ("boardCookie".equals(name)) {
					boardCookieVal = value;

					if (value.contains("|" + sharingPostNo + "|")) {
						hasRead = true;
					}
					break outer;
				}
			}
		}

		if (!hasRead) // 읽지않았으면
		{
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal + "|" + sharingPostNo + "|");
			boardCookie.setMaxAge(-1); // 브라우저 종료시에 쿠키를 삭제
			response.addCookie(boardCookie);// 쿠키추가
		}

		List<SharingPost> list = new SharingPostService().selectView(sharingPostNo,hasRead);

		String view = "";

		if (list.isEmpty()) {
			// 메인 화면 보여주는 서블릿
			
			view = "/board/sharingBoardList";
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			
			List<SharingComment> sharingComment = new SharingPostService().selectCommentList(sharingPostNo);
			if(admin=='Y') {
				view = "/views/board/admin_sharing_post_detail.jsp";
			}else {
				view="/views/board/sharing_post_detail.jsp";
			}
			request.setAttribute("list", list);
			request.setAttribute("comments", sharingComment);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
