package com.petwork.controller;

import java.io.IOException; 
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.MyPageAnimalSelService;
import com.petwork.model.service.MyPageService;
import com.petwork.model.service.SignUpService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

/**
 * Servlet implementation class MyPageSignUpController
 */
@WebServlet("/mypetinsertcontroller")
public class MyPetInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPetInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Member currentMember=(Member)session.getAttribute("loginMember");
		MyPageService mps=null;
		
		String petMemberId=currentMember.getId();
		
		String petIdentifyInput=request.getParameter("petIdentifyInput");
		String petInputName=request.getParameter("petInputName");
		
		String raceCode=request.getParameter("inputRaceShortSel");
		String animalNo=request.getParameter("inputAnimalShortSel");
		
		String petBirth=request.getParameter("petBirth");
		String petGender=request.getParameter("myPetGender");
		String neutralCheck;
		
		Pet insertPet= new Pet();
		
		insertPet.setMemberId(petMemberId);
		insertPet.setPetIdentifyNo(petIdentifyInput);
		insertPet.setPetName(petInputName);
		insertPet.setRaceCode(raceCode.charAt(0));
		insertPet.setAnimalNo(animalNo);
		insertPet.setPetBirth(Date.valueOf(petBirth));
		insertPet.setPetGender(petGender.charAt(0));
		
		if(request.getParameter("neutralCheck")!=null)
		{
			insertPet.setPetNeutering('Y');
		}
		else
		{
			insertPet.setPetNeutering('N');
		}
		
		mps=new MyPageService();
		int result=mps.insertPetService(insertPet);
		
		String url="/views/common/msg.jsp";
		String msg="";
		String loc="";
		
		if(result<=0)
		{
			msg="동물 등록이 실패하였습니다.";
			mps=new MyPageService();
			
			List petList=mps.myPageLoadingService(currentMember);
			request.setAttribute("petList", petList);
						
			RequestDispatcher rd=request.getRequestDispatcher("/views/member/mypage.jsp");
			rd.forward(request,response);
		}
		else
		{
			loc="/mypagecontroller";
			msg="동물 등록 성공!";
			mps=new MyPageService();
			
			List petList=mps.myPageLoadingService(currentMember);
			request.setAttribute("petList", petList);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd=request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request,response);
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
