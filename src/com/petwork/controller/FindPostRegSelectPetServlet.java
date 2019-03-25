package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.petwork.model.service.MemberService;
import com.petwork.model.service.MissingService;
import com.petwork.model.service.PetService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Pet;

/**
 * Servlet implementation class FindPostRegSelectPetServlet
 */
@WebServlet("/missingPet/findPostRegSelectPet")
public class FindPostRegSelectPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPostRegSelectPetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int petNo = Integer.parseInt(request.getParameter("petNo"));
		
		boolean postDuplicate = new MissingService().postDuplicateCheck(petNo);
		
		JSONObject jobj = new JSONObject();
		if(postDuplicate) //true면 실종신고 중복 상태
		{
			jobj.put("postDuplicate", postDuplicate);
		}else {
			Pet myPet = new PetService().selectMyPet(petNo);
			
			// 선택한 내 애완동물의 품종 정보
			Animal a = new Animal();
			a.setRaceCode(String.valueOf(myPet.getRaceCode()));
			a.setAnimalNo(myPet.getAnimalNo());
			String selectMyPetAnimalName = new PetService().selectAnimalName(a);
			
			String myPetRace = "";
			
			switch (myPet.getRaceCode()) {
			case 'D': myPetRace = "강아지"; break;
			case 'C': myPetRace = "고양이"; break;
			case 'E': myPetRace = "기타"; break;
			}
			jobj.put("myPetNo", myPet.getPetNo());
			jobj.put("myPetName", myPet.getPetName());
			jobj.put("myPetRace", myPetRace);
			jobj.put("selectMyPetAnimalName", selectMyPetAnimalName);
			jobj.put("myPetGender", myPet.getPetGender());
			jobj.put("myPetIdentifyNo", myPet.getPetIdentifyNo());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(jobj, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
