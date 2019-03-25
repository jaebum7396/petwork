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
import com.petwork.model.service.MemberService;
import com.petwork.model.service.MissingService;
import com.petwork.model.service.PetService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.District;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

/**
 * Servlet implementation class FindPostRegSelectPetServlet
 */
@WebServlet("/missingPet/protectPostRegSelectRace")
public class ProtectPostRegSelectRaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostRegSelectRaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String raceCode = request.getParameter("raceCode");
				
		List<Animal> animalNameList = new PetService().selectAnimalName(raceCode);
		
		JSONArray jobjArr = new JSONArray();
		for(Animal a : animalNameList) {
			JSONObject obj = new JSONObject();
			obj.put("raceCode", a.getRaceCode());
			obj.put("animalNo", a.getAnimalNo());
			obj.put("animalName", a.getAnimalName());
			jobjArr.add(obj);
		}

		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(jobjArr, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
