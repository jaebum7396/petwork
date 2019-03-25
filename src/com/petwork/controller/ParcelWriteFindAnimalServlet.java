package com.petwork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.Parcel;

/**
 * Servlet implementation class ParcelWriteViewServlet
 */
@WebServlet("/petwork/parcelWriteAnimal")
public class ParcelWriteFindAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelWriteFindAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String raceCode=request.getParameter("raceCode");
		List<Animal> ani = new ParcelService().findAnimal(raceCode);
		String option = "";
		String aniNo = "";
		if(ani.size()>0) {
			for(int i = 0;i < ani.size();i++) {
				option+=(ani.get(i).getAnimalName()+",");
				
			}
		}
		response.setContentType("text/csv;charset=UTF-8");
		response.getWriter().append(option);
		response.getWriter().append(aniNo);
	    


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
