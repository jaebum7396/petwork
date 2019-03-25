package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.DoctorService;
import com.petwork.model.vo.City;
import com.petwork.model.vo.Member;



/**
 * Servlet implementation class ParcelWriteFindCityServlet
 */
@WebServlet("/doctor/findCity")
public class DoctorFindCityServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorFindCityServlet() {
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
	
	
			String cityCode=request.getParameter("city");
		      City city = new DoctorService().findCity(cityCode);
		      String cityName = city.getCityName();
		      response.setContentType("text/csv;charset=UTF-8");
		      response.getWriter().append(cityName); 

		
		
			
		
	   
	   
	   
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}