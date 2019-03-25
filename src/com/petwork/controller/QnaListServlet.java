package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.petwork.model.service.QnaService;
import com.petwork.model.vo.Qna;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet("/qna/qnaList")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject jobj= new JSONObject();
		String selectAdmin = request.getParameter("admin");
		
		Qna qnaAdmin = new QnaService().selectAdmin(selectAdmin);
		
		jobj.put("adminId", qnaAdmin.getId());
		jobj.put("adminName", qnaAdmin.getName());
		jobj.put("adminPhone", qnaAdmin.getPhone());
		jobj.put("adminEmail", qnaAdmin.getEmail());
		jobj.put("adminEmail2", qnaAdmin.getEmail());
		
		if(qnaAdmin!=null) {
			response.setContentType("application/json;charset=UTF-8");
			new Gson().toJson(jobj,response.getWriter());
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
