package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.MemberService;
import com.petwork.model.service.MyPageService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class MyInfoModify
 */
@WebServlet(name="MyInfoModifyCompleteController",urlPatterns="/myinfomodifycompletecontroller")
public class MyInfoModifyCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoModifyCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Member currentM=(Member)session.getAttribute("loginMember");		
				
		String myPId = currentM.getId();
		
		//리퀘스트 값 받아오기
		
		String myPInputName = request.getParameter("myP-inputName");
		String myPPhoneNum = request.getParameter("firstPhoneSel")+request.getParameter("secondPhoneInput")+request.getParameter("lastPhoneInput");
		String myPEmail = request.getParameter("myP-emailId")+"@"+request.getParameter("myP-emailDomain");
		String myPAddress = request.getParameter("myP-searchAddress")+"/"+request.getParameter("myP-detailAddress");
				
		Member myPUpdate = new Member();
		
		myPUpdate.setId(myPId);
		
		//비밀번호를 입력하지 않았을때 
		if(request.getParameter("myP-inputPw").equals("z4PhNX7vuL3xVChQ1m2AB9Yg5AULVxXcg/SpIdNs6c5H0NE8XYXysP+DGNKHfuwvY7kxvUdBeoGlODJ6+SfaPg=="))
		{
			//나머지 모든 정보를 입력하지 않았을때 그대로 마이페이지 재출력 
			if((currentM.getPhone().equals(myPPhoneNum))&&(currentM.getEmail().equals(myPEmail))&&(currentM.getAddress().equals(myPAddress)))
			{
				session.setAttribute("loginMember", currentM);
				
				MyPageService mps=new MyPageService();
				
				List petList=mps.myPageLoadingService(currentM);
				request.setAttribute("petList", petList);
				
				if(request.getParameter("myPetSel")!=null)
				{			
					int myPetSel=Integer.parseInt(request.getParameter("myPetSel"));
					request.setAttribute("petIndex", myPetSel);
				}
				
				RequestDispatcher rd=request.getRequestDispatcher("/views/member/mypage.jsp");
				rd.forward(request,response);
			}
			
			//기존의 비밀번호 그대로 객체 업데이트
			else
			{
				myPUpdate.setPw(currentM.getPw());
				
				myPUpdate.setName(myPInputName);
				myPUpdate.setPhone(myPPhoneNum);
				myPUpdate.setEmail(myPEmail);
				myPUpdate.setAddress(myPAddress);
				
				MyPageService mps=new MyPageService();
				int result=mps.myPageMemberUpdateService(myPUpdate);
				
				String msg="";
				String loc="";
				String url="/views/common/msg.jsp";
				
				if(result!=0)
				{
					//세션 갱신
					MemberService ms=new MemberService();
					Member updateMember=ms.memberService(currentM);
					
					session.setAttribute("loginMember", updateMember);
					
					//메시지 출력 후 이동
					msg="회원 정보 수정 완료!";			
					loc="/mypagecontroller";
					
					RequestDispatcher rd=request.getRequestDispatcher(url);
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					rd.forward(request,response);						
				}
				else if(result==0)
				{
					msg="회원 정보 수정 실패!";			
					loc="/mypagecontroller";
					
					RequestDispatcher rd=request.getRequestDispatcher(url);
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					rd.forward(request,response);	
				}
			}
		}
		//비밀번호를 입력 했을때
		else
		{
			//업데이트 로직
			
			String myPPw=request.getParameter("myP-inputPw");
			
			myPUpdate.setName(myPInputName);
			myPUpdate.setPw(myPPw);	
			myPUpdate.setPhone(myPPhoneNum);
			myPUpdate.setEmail(myPEmail);
			myPUpdate.setAddress(myPAddress);
			
			MyPageService mps=new MyPageService();
			int result=mps.myPageMemberUpdateService(myPUpdate);
			
			//메시지 출력 후 이동
			String msg="";
			String loc="";
			String url="/views/common/msg.jsp";
			
			if(result!=0)
			{
				//세션 갱신
				currentM.setPw(request.getParameter("myP-inputPw"));
				MemberService ms=new MemberService();
				Member updateMember=ms.memberService(currentM);
				
				session.setAttribute("loginMember", updateMember);
				
				//페이지 이동
				msg="회원 정보 수정 완료!";			
				loc="/mypagecontroller";
				
				RequestDispatcher rd=request.getRequestDispatcher(url);
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				rd.forward(request,response);						
			}
			else if(result==0)
			{
				msg="회원 정보 수정 실패!";			
				loc="/mypagecontroller";
				
				RequestDispatcher rd=request.getRequestDispatcher(url);
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				rd.forward(request,response);	
			}
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
