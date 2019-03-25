package com.petwork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.MemberService;
import com.petwork.model.service.MissingService;
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.FindPost;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class loginController
 */
@WebServlet(name="LoginController",urlPatterns="/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService ls=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		ls = new MemberService();
		Doctor inputDoc=new Doctor();
		Member inputData=new Member();
		
		String view="/views/common/msg.jsp";
		String loc="/views/member/loginpage.jsp";
		String msg="";//찾을수 없을때 메세지!
		
		String id=request.getParameter("inputId");
		String pw=request.getParameter("inputPw");
		
		//닥터 로그인 선행 수행
		inputDoc.setDoctorId(id);
		inputDoc.setDoctorPwd(pw);
		
		Doctor returnDoc=ls.doctorMemberService(inputDoc);
		
		if(returnDoc==null)
		{			
			inputData.setId(id);
			inputData.setPw(pw);		
			
			Member returnData=ls.memberService(inputData);			
			
			if(returnData==null||returnData.getMemberYN()=='Y')
			{
				msg="입력하신 정보가 존재하지 않습니다!";
				
				request.setAttribute("loc", loc);
				request.setAttribute("msg", msg);				
				RequestDispatcher rd=request.getRequestDispatcher(view);				
				rd.forward(request, response);
			}
			
			else if(returnData!=null&&returnData.getPw().equals(inputData.getPw()))
			{
				if(returnData.getAdminYN()=='Y')
				{
					
					HttpSession session=request.getSession();
					session.setAttribute("loginMember", returnData);	
										
					response.sendRedirect(request.getContextPath()+"/");
				}
				else
				{
					HttpSession session=request.getSession();//세션생성~!
					
					session.setAttribute("loginMember", returnData);//세션의 로그인 멤버를 리턴 데이터로 세팅
					
					//시작부!
					List<FindPost> userFindPost = new MissingService().selectFindPostId(returnData.getId());
					List<ProtectPost> protectAllList = new MissingService().selectProtectPostAllList();
					int noticeProtectNo = -1;
					for(FindPost f : userFindPost)
					{
						for(ProtectPost p : protectAllList)
						{
							if(f.getPetIdentifyNo().equals(p.getProtectPetIdentifyNo())){
								noticeProtectNo = p.getProtectPostNo();
							}
						}
					}		
					request.setAttribute("noticeProtectNo", noticeProtectNo);
					request.getRequestDispatcher("/").forward(request, response);
				}
			}
			else
			{
				msg="입력하신 정보가 일치하지 않습니다!";
				
				request.setAttribute("loc", loc);
				request.setAttribute("msg", msg);
				
				RequestDispatcher rd=request.getRequestDispatcher(view);
				rd.forward(request, response);			
			}
		}
		
		else if(returnDoc!=null&&returnDoc.getDoctorPwd().equals(inputDoc.getDoctorPwd()))
		{
			HttpSession session=request.getSession();
			
			session.setAttribute("loginDoctor", returnDoc);	
			
			response.sendRedirect(request.getContextPath()+"/");
		}
		else
		{
			msg="입력하신 정보가 일치하지 않습니다!";
			
			request.setAttribute("loc", loc);
			request.setAttribute("msg", msg);
			
			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
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
