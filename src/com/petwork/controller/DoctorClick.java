package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.DoctorService;
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class DoctorClick
 */
@WebServlet("/admin/clickDoctor")
public class DoctorClick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorClick() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		String view="";
		String msg ="잘못된 접근입니다.";
		String loc ="/";
		if(m==null)
		{	
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);			
		}
		else if(m!=null&&m.getAdminYN()=='Y')
		{
			int doctorNo=Integer.parseInt(request.getParameter("no"));
			Doctor d = new DoctorService().selectDoctorClick(doctorNo);
			
			if(d!=null)
			{
				request.setAttribute("doctorClick", d);
				view="/views/doctor_click/doctorClick.jsp";
			}
			request.getRequestDispatcher(view).forward(request, response);
		}
		else
		{
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
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
