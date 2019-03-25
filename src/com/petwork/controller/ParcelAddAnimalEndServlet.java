package com.petwork.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class ParcelAddAnimalEndServlet
 */
@WebServlet("/addAnimalEnd")
public class ParcelAddAnimalEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelAddAnimalEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String view="/views/common/msg.jsp";
		String msg ="잘못된 접근입니다.";
		String loc ="/";
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		if(m==null)
		{
			System.out.println("멤버 없어?");
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/").forward(request, response);			
		}
		else if(m!=null&&m.getAdminYN()=='Y')
		{
			String raceCode = request.getParameter("parcel-race");
			String animalName = request.getParameter("animal_name");
			int result = new ParcelService().addAnimal(raceCode,animalName);
			
			if(result>0) {
				msg="등록 성공하였습니다.";
				loc="/animalManage";
			} else {
				msg="등록 실패하였습니다.";
				loc="/addAnimal";
			}
			System.out.println(msg);
			System.out.println(loc);
			request.setAttribute("loc", loc);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
		}
		else
		{
			System.out.println("관리자아니냐?");
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/").forward(request, response);
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
