package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.FaqService;
import com.petwork.model.vo.Faq;

/**
 * Servlet implementation class FaqAdminViewServlet
 */
@WebServlet("/faq/faqView")
public class FaqAdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqAdminViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("no"));
		Faq f = new FaqService().selectFaq(num);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(f != null) //성공
		{
			view = "/views/faq/adminFaqView.jsp";
			request.setAttribute("faq", f);
		}
		else //실패 
		{
			msg = "글이 존재하지 않습니다.";
			loc = "/faq/adminFaqList";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		request.getRequestDispatcher(view).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
