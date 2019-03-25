package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.petwork.model.service.CityService;
import com.petwork.model.service.DoctorService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.City;
import com.petwork.model.vo.District;
import com.petwork.model.vo.Doctor;

/**
 * Servlet implementation class MapServlet
 */
@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cityCode = request.getParameter("cityCode")==null? "CITY1" : request.getParameter("cityCode");
		String districtCode = request.getParameter("districtCode")==null? "DISTRICT1" : request.getParameter("districtCode");
		boolean changeCity = request.getParameter("changeCity")==null? false : true;
		List<City> cityList = new CityService().selectCityList();
		List<District> districtList = new CityService().selectDistrict(cityCode);		
		String cityName = request.getParameter("cityName")==null? "서울특별시" : request.getParameter("cityName");
		List<Doctor> doctorList = new DoctorService().doctorMapList(cityName);		

		if(changeCity)
		{
			JSONArray jobjArr1 = new JSONArray();
			for(District d : districtList) {
				JSONObject obj = new JSONObject();
				obj.put("districtCode", d.getDistrictCode());
				obj.put("districtName", d.getDistrictName());
				jobjArr1.add(obj);
			}
			
			JSONArray jobjArr2 = new JSONArray();
			
			for(Doctor d : doctorList) {

				JSONObject obj = new JSONObject();
				obj.put("doctorHospital", d.getDoctorHospital());
				obj.put("doctorAddress", d.getDoctorAddress());
				obj.put("doctorPhone", d.getDoctorPhone());
				obj.put("doctorX", d.getDoctorX());
				obj.put("doctorY", d.getDoctorY());
								
				jobjArr2.add(obj);
			}
			
			response.setContentType("application/json;charset=UTF-8");
			
			String obj1 = new Gson().toJson(jobjArr1);
			String obj2 = new Gson().toJson(jobjArr2);

			String bothJson = "["+obj1+","+obj2+"]";
		    response.getWriter().write(bothJson);
		}else {
			request.setAttribute("districtList", districtList);	
			request.setAttribute("cityList", cityList);
			request.setAttribute("cityCode", cityCode);
			request.setAttribute("districtCode", districtCode);
			request.setAttribute("doctorList", doctorList);
			request.getRequestDispatcher("/views/map/mapList.jsp").forward(request, response);
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
