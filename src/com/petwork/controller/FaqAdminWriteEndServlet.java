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
 * Servlet implementation class FaqAdminWriteEndServlet
 */
@WebServlet("/faq/adminFaqWriteEnd")
public class FaqAdminWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqAdminWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		Faq f = new Faq();
		f.setFaqTitle(title);
		f.setFaqContent(contents);
		
		int result =  new FaqService().writeFaq(f);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result>0)
		{
			msg = "FAQ가 등록되었습니다.";
			loc = "/faq/adminFaqList";
		}
		else
		{
			msg = "FAQ 등록에 실패하였습니다.";
			loc = "/faq/adminFaqWrite";
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
