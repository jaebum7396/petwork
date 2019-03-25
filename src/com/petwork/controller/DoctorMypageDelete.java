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

/**
 * Servlet implementation class DoctorMypageDelete
 */
@WebServlet("/doctor/deleteDoctor")
public class DoctorMypageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorMypageDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Doctor outDoctor=(Doctor)session.getAttribute("loginDoctor");
		String outDoctorId=outDoctor.getDoctorId();
		int result=new DoctorService().doctorOut(outDoctorId);
		
		String view="";
		if(result>0)
		{
			view="/petwork";
		}
		session.removeAttribute("loginDoctor");
		
		response.sendRedirect(view);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
