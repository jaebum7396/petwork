package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreeComment;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class FreeBoardViewServlet
 */
@WebServlet("/board/freeBoardForm")
public class FreeBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////////////////////////////////////////////////////////////
		//상세 화면 보여주는 서블릿
		///////////////////////////////////////////////////////////
		
		char admin=' ';
		String loc = "";
		List<FreePost> list = null;
		List<FreeComment> freeComment = null;
		String pageBar = "";
		
		int freePostNo = Integer.parseInt(request.getParameter("freePostNo"));
		
		Cookie[] cookies=request.getCookies(); //쿠키받기
		String boardCookieVal=""; //쿠키값 저장할거 
		boolean hasRead=false; //false면 글을 안일고 트루면 읽음 
		Member loginMember=(Member)request.getSession().getAttribute("loginMember");
		
		if(cookies!=null)
		{
			outer:
				for(Cookie c : cookies)
				{
					String name=c.getName(); 
					
					String value=c.getValue();
					
					if("boardCookie".equals(name)) 
					{
						boardCookieVal=value;
						
						
						if(value.contains("|"+freePostNo+"|"))
						{
							hasRead=true;
						}
						break outer;
					}
				}
		}
		
		if(!hasRead) //읽지않았으면
		{
			Cookie boardCookie=new Cookie("boardCookie",boardCookieVal+"|"+freePostNo+"|");
			boardCookie.setMaxAge(-1); //브라우저 종료시에 쿠키를 삭제
			response.addCookie(boardCookie);//쿠키추가 
		}
		
		list = new FreePostService().selectView(freePostNo, hasRead);
		
		String view = "";
		
		if(list.isEmpty()) {
			//메인 화면 보여주는 서블릿
			
			view = "/board/freeBoardList";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else {
			
			freeComment = new FreePostService().selectCommentList(freePostNo);
			if(loginMember!=null&&loginMember.getAdminYN()=='Y') {
				admin = loginMember.getAdminYN();
			}
			if(admin=='Y') {
				loc="/views/board/admin_free_post_detail.jsp";
			}
			else {
				loc="/views/board/free_post_detail.jsp";
			}
			request.setAttribute("list", list);
			request.setAttribute("comments", freeComment);
			request.getRequestDispatcher(loc).forward(request, response);
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
