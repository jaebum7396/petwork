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
 * Servlet implementation class MyPetModifyCompleteController
 */
@WebServlet("/mypetmodifycompletecontroller")
public class MyPetModifyCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPetModifyCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Member currentMember=(Member)session.getAttribute("loginMember");
		String currentId=currentMember.getId();
		MyPageService mps=null;

		MyPageAnimalSelService mpas=new MyPageAnimalSelService();

		int petNo=Integer.parseInt(request.getParameter("myPetSel"));
		String petIdentifyInput=request.getParameter("petIdentifyInput");
		String petInputName=request.getParameter("petInputName");
		
		String raceCode=request.getParameter("inputRaceShortSel");
		String animalNo=request.getParameter("inputAnimalShortSel");
		
		String petBirth=request.getParameter("petBirth");
		String petGender=request.getParameter("myPetGender");
		String neutralCheck;
		
		Pet insertPet= new Pet();
		
		insertPet.setPetNo(petNo);
		insertPet.setMemberId(currentId);
		insertPet.setPetIdentifyNo(petIdentifyInput);
		insertPet.setPetName(petInputName);
		insertPet.setRaceCode(raceCode.charAt(0));
		insertPet.setAnimalNo(animalNo);
		
		if(request.getParameter("neutralCheck")!=null)
		{
			insertPet.setPetNeutering('Y');
		}
		else
		{
			insertPet.setPetNeutering('N');
		}
		mps=new MyPageService();
		
		int result=mps.updatePetService(insertPet);
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
			msg="동물 수정 성공!";
			mps=new MyPageService();
			
			List petList=mps.myPageLoadingService(currentMember);
			request.setAttribute("petList", petList);
			
			RequestDispatcher rd=request.getRequestDispatcher("/views/member/mypage.jsp");
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
