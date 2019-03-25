package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.District;

/**
 * Servlet implementation class ParcelWriteFindCityServlet
 */
@WebServlet("/petwork/parcelWriteCity")
public class ParcelWriteFindCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelWriteFindCityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cityCode=request.getParameter("city");
		List<District> city = new ParcelService().findCity(cityCode);
		String cityOption = "";
		if(city.size()>0) {
			for(int i = 0;i < city.size();i++) {
				cityOption+=(city.get(i).getDistrictName()+",");
				
			}
		}
		response.setContentType("text/csv;charset=UTF-8");
		response.getWriter().append(cityOption);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
