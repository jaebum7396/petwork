package com.petwork.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.FaqService;
import com.petwork.model.vo.Faq;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class faqList
 */
@WebServlet("/faq/faqList")
public class FaqListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Faq> list = new FaqService().selectAll();
		
		request.setAttribute("list", list);
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		char admin=' ';
		if(m!=null) {
			admin = m.getAdminYN();
		}
		if(admin=='Y') {
			request.getRequestDispatcher("/faq/adminFaqList").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/faq/faqList.jsp").forward(request, response);
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
