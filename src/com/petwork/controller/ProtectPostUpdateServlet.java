package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.dao.MissingDao;
import com.petwork.model.service.MissingService;
import com.petwork.model.service.PetService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class FindPostUpdateServlet
 */
@WebServlet("/missingPet/updateProtectPost")
public class ProtectPostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("no"));
		String raceCode = request.getParameter("raceCode");
 	
		List<Animal> animalList = new PetService().selectAnimalName(raceCode);
		
		ProtectPost protectPost = new MissingService().selectProtectPost(postNo);
				
		request.setAttribute("protectPost", protectPost);
		request.setAttribute("animalList", animalList);
		
		request.getRequestDispatcher("/views/missing_pet/protect_post_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
