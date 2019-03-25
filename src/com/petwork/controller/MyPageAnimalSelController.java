package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MyPageAnimalSelService;
import com.petwork.model.vo.Animal;

/**
 * Servlet implementation class MyPetSelController
 */
@WebServlet("/mypageanimalselcontroller")
public class MyPageAnimalSelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageAnimalSelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inputRace=request.getParameter("inputRace");
		
		MyPageAnimalSelService mpas=new MyPageAnimalSelService();
		List<Animal> selectedAnimalList=mpas.myPageAnimalSelService(inputRace);
		
		String animalLine="";
		if(!selectedAnimalList.isEmpty())
	    {
			for(int i=0;i<selectedAnimalList.size();i++)
			{
				if(i>0) animalLine+="/";
				animalLine+=selectedAnimalList.get(i).getAnimalNo()+" "+selectedAnimalList.get(i).getAnimalName();
			}
        }
		
		response.setContentType("text/csv;charset=UTF-8");
		response.getWriter().append(animalLine);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
