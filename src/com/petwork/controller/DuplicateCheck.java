package com.petwork.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MemberService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class DuplicateCheck
 */
@WebServlet("/duplicateCheck")
public class DuplicateCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuplicateCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd=null;
		String checkId=request.getParameter("joinId");
		System.out.println("체크할 아이디: "+checkId);
		Member m=new Member();
		m.setId(checkId);
		
		System.out.println("중복체크 비즈니스 로직");
		
		MemberService ms=new MemberService();
		Member exist=ms.memberService(m);
		boolean isAble=(exist==null)?true:false;
		
		request.setAttribute("joinId", checkId);
		request.setAttribute("isAble", isAble);
		
		rd=request.getRequestDispatcher("views/member/duplicateCheckPop.jsp");
		rd.forward(request, response);
		
		
		
		
		/*if(exist==null&&checkId.length()>=6)
		{
			System.out.println(checkId+"은/는 사용가능한 아이디");
			msg=checkId+"은/는 사용하실 수 있는 아이디 입니다.";
			checkedId=checkId;
			check=1;
		
		}
		else if(checkId.length()<6)
		{
			msg="아이디는 최소 6글자 이상이어야 합니다.";
		}
		else
		{	
			System.out.println(exist.getId()+"은/는 사용불가한 아이디");
			msg=exist.getId()+"은/는 중복된 아이디 입니다.";
			checkedId=exist.getId();		
						
		}
		
		rd=request.getRequestDispatcher(views);
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/views/member/signuppage.jsp");
		request.setAttribute("check", check);
		request.setAttribute("checked", checkedId);
		
		rd.forward(request, response);
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
