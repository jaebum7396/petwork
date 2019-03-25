package com.petwork.controller;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.MemberService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListController
 */
@WebServlet("/adminmemberlistcontroller")
public class AdminMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		if(m==null)
		{
			request.getRequestDispatcher("/").forward(request, response);;			
		}
		else if(m!=null&&m.getAdminYN()=='Y')
		{
			int cPage;
			
			try
			{
				cPage=Integer.parseInt(request.getParameter("cPage"));
			}
			catch(NumberFormatException e)
			{
				cPage=1;
			}
			
			int numPerPage; // 페이지당 자료수
			
			try
			{
				numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
				
			}
			catch(NumberFormatException e)
			{
				numPerPage=5;
			}
			List<Member> memberList= new MemberService().memberListService(cPage,numPerPage);
			
			int totalMember=new MemberService().selectMemberCount();
					
			int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
			
			String pageBar="";
			//페이지바 길이
			int pageBarSize=5;
			int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd=pageNo+pageBarSize-1;
			
			if(pageNo==1) //1부터 5사이에 숫자면 무저건 1일때  맨앞페이지란것이다. 
			{ 
				pageBar+="<span>[이전]</span>"; //페이지가 1이면 이전버튼에대한 연결하는것이 없다. 그래서 span태그만을적용했다.
			}else
			{
				pageBar+="<a href='"+request.getContextPath()+"/adminmemberlistcontroller?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
			}
			//선택페이지 만들기
			while(!(pageNo>pageEnd||pageNo>totalPage))
			{
				if(cPage==pageNo)
				{
					pageBar+="<span class='cPage'>"+pageNo+"</span>";//현재보고있는페이지가 페이지 no이면 바꿀필요가엇으니 그냥 스팬으로 ㅎㅎ
				}
				else
				{
					pageBar+="<a href='"+request.getContextPath()+"/adminmemberlistcontroller?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
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
				pageBar+="<a href='"+request.getContextPath()+"/adminmemberlistcontroller?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>[다음]</a>";
			}
			request.setAttribute("memberList", memberList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
			
			request.getRequestDispatcher("/views/admin/adminMemberList.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/").forward(request, response);;
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
