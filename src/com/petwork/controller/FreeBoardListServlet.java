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
 * Servlet implementation class FreeBoardListServlet
 */
@WebServlet("/board/freeBoardList")
public class FreeBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//////////////////////////////////////////////////////////////////
		//메인 화면 보여주는 서블릿
		//////////////////////////////////////////////////////////////////
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		char admin=' ';
		String loc = "";
		String race_code = request.getParameter("race_code");
		List<FreePost> list = null;
		List<FreePost> adminList = new FreePostService().selectAdminList();
		String pageBar = "";
		
		if(loginMember!=null&&loginMember.getAdminYN()=='Y') {
			admin = loginMember.getAdminYN();
		}
		
		if(admin=='Y') {
			loc="/views/board/admin_free_post.jsp";
		}
		else {
			loc="/views/board/free_post.jsp";
		}
		
		//page처리
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}
		catch(NumberFormatException e) {
			numPerPage = 3;
		}
		
		int pageSize = 5;
		int pageNo = ((cPage-1)/pageSize)*pageSize+1;
		int pageEnd = pageNo+pageSize-1;
		
		if(race_code==null||race_code.equals("A")) {
		
			int totalFreePost = new FreePostService().selectCount(race_code);
			
			int totalPage=(int)Math.ceil((double)totalFreePost/numPerPage);
			
			list = new FreePostService().selectList(cPage, numPerPage);
			
			if(pageNo==1)
			{
				pageBar+="<span>[이전]</span>";
			}
			else 
			{
				pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
			}
			//선택페이지 만들기
			while(!(pageNo>pageEnd||pageNo>totalPage))
			{
				if(cPage==pageNo)
				{
					pageBar+="<span class='cPage'>"+pageNo+"</span>";
				}
				else
				{
					pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
				}
				pageNo++;
			}
			//[다음]구현
			
			if(pageNo>totalPage)
			{
				pageBar+="<span>[다음]</span>";
			}
			else 
			{
				pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>[다음]</a>";
			}
			
		}
		else {

			int totalFreePost = new FreePostService().selectCount(race_code);
			
			int totalPage=(int)Math.ceil((double)totalFreePost/numPerPage);
			
			list = new FreePostService().choiceSelectList(cPage,numPerPage,race_code);
		
			
			if(pageNo==1)
			{
				pageBar+="<span>[이전]</span>";
			}
			else 
			{
				pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"&race_code="+race_code+"'>[이전]</a>";
			}
			//선택페이지 만들기
			while(!(pageNo>pageEnd||pageNo>totalPage))
			{
				if(cPage==pageNo)
				{
					pageBar+="<span class='cPage'>"+pageNo+"</span>";
				}
				else
				{
					pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&race_code="+race_code+"'>"+pageNo+"</a>";
				}
				pageNo++;
			}
			//[다음]구현
			
			if(pageNo>totalPage)
			{
				pageBar+="<span>[다음]</span>";
			}
			else 
			{
				pageBar+="<a href='"+request.getContextPath()+"/board/freeBoardList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&race_code="+race_code+"'>[다음]</a>";
			}
			
		}
		request.setAttribute("adminList", adminList);
		request.setAttribute("race_code", race_code);
		request.setAttribute("list", list);
		request.setAttribute("cPage",cPage);
		request.setAttribute("pageBar",pageBar);
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
