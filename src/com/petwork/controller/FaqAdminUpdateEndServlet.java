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
 * Servlet implementation class FaqAdminUpdateEnd
 */
@WebServlet("/faq/adminFaqUpdateEnd")
public class FaqAdminUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqAdminUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("faqNo"));
		String title = request.getParameter("faqTitle");
		String content = request.getParameter("faqContent");
		
		Faq f = new Faq();
		f.setFaqNo(no);
		f.setFaqTitle(title);
		f.setFaqContent(content);
		
		int result = new FaqService().updateFaq(f);
		
		String view = "/views/common/msg.jsp";
		String loc = "/faq/faqView?no=" + no;
		String msg = "";
		
		if(result > 0)
		{
			msg = "수정이 완료 되었습니다.";
		}
		else
		{
			msg = "수정이 실패하였습니다.";
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
