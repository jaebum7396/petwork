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
 * Servlet implementation class FreeBoardSearchServlet
 */
@WebServlet("/board/freePostFinder")
public class FreeBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSearchServlet() {
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
		List<FreePost> list = null;
		String pageBar = "";
		List<FreePost> adminList = new FreePostService().selectAdminList();
		
		if(loginMember!=null) {
			admin = loginMember.getAdminYN();
		}
	
		if(admin=='Y') {
			loc="/views/board/admin_free_post.jsp";
		}
		else {
			loc="/views/board/free_post.jsp";
		}


		 String searchType=request.getParameter("searchType");
		 String searchKeyword=request.getParameter("searchKeyword");
		 
	     int cPage;
	      
	      try
	      {
	         cPage=Integer.parseInt(request.getParameter("cPage"));
	      }catch(NumberFormatException e)
	      {
	         cPage=1;
	      }
	      int numPerPage; // 페이지당 자료수
	      try
	      {
	         numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
	         
	      }catch(NumberFormatException e)
	      {
	         numPerPage=3;
	      }
	      
	      switch(searchType)
	      {
	         case "freePostTitle" : list=new FreePostService().selectFreePostTitle(searchKeyword,cPage,numPerPage); break;
	         case "freePostWriter" : list=new FreePostService().selectFreePostWriter(searchKeyword,cPage,numPerPage); break; 
	         case "freePostContent" : list=new FreePostService().selectFreePostContent(searchKeyword,cPage,numPerPage); break;
	      }
	      int totalSelectSearchFreePost=0;
	      
	      switch(searchType)
	      {
	         case "freePostTitle" : totalSelectSearchFreePost=new FreePostService().selectFreePostTitleCount(searchKeyword); break;
	         case "freePostWriter" : totalSelectSearchFreePost=new FreePostService().selectFreePostWriterCount(searchKeyword); break;
	         case "freePostContent" : totalSelectSearchFreePost=new FreePostService().selectFreePostContentCount(searchKeyword); break;
	      }
	      
	      int totalPage=(int)Math.ceil((double)totalSelectSearchFreePost/numPerPage);
	      
	      
	      //페이지바 길이
	      int pageBarSize=5;
	      int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
	      int pageEnd=pageNo+pageBarSize-1;
	      if(pageNo==1) //1부터 5사이에 숫자면 무저건 1일때  맨앞페이지란것이다. 
	      { 
	         pageBar+="<span>[이전]</span>"; //페이지가 1이면 이전버튼에대한 연결하는것이 없다. 그래서 span태그만을적용했다.
	      }else
	      {
	         pageBar+="<a href='"+request.getContextPath()+"/board/freePostFinder?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"&searchType="+searchType+"&searchKeyword="+searchKeyword+"'class='page-link'>[이전]</a>";
	          
	      }//선택페이지 만들기
	      while(!(pageNo>pageEnd||pageNo>totalPage))
	      {
	         if(cPage==pageNo)
	         {
	            pageBar+="<span class='cPage'>"+pageNo+"</span>";//현재보고있는페이지가 페이지 no이면 바꿀필요가엇으니 그냥 스팬으로 ㅎㅎ
	         }
	         else
	         {
	            pageBar+="<a href='"+request.getContextPath()+"/board/freePostFinder?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&searchType="+searchType+"&searchKeyword="+searchKeyword+"'>"+pageNo+"</a>";
	         }
	         pageNo++; //pageEnd까지 증가한다. 
	      }
	      //[다음구현]
	      if(pageNo>totalPage)
	      {
	         pageBar+="<span>[다음]</span>";
	      }
	      else
	      {
	         pageBar+="<a href='"+request.getContextPath()+"/board/freePostFinder?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&searchType="+searchType+"&searchKeyword="+searchKeyword+"'>[다음]</a>";
	      }
	      
	      request.setAttribute("adminList", adminList);
	      request.setAttribute("list", list);
	      request.setAttribute("cPage", cPage);
	      request.setAttribute("pageBar", pageBar);
	   
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
