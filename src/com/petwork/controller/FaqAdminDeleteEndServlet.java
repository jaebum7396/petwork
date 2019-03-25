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
 * Servlet implementation class FaqDeleteServlet
 */
@WebServlet("/faq/deleteFaq")
public class FaqAdminDeleteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqAdminDeleteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("faqNo"));
		
		
		int result = new FaqService().deleteFaq(no);
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result > 0)
		{
			msg = "삭제가 완료 되었습니다.";
		    loc = "/faq/adminFaqList";
		}
		else
		{
			msg = "삭제를 실패하였습니다.";
			loc = "/faq/faqView?no=" + no;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
