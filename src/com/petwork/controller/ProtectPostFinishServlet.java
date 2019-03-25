package com.petwork.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MissingService;

/**
 * Servlet implementation class FindPostFinishServlet
 */
@WebServlet("/missingPet/finishProtectPost")
public class ProtectPostFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostFinishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("no"));
		String giveId = request.getParameter("giveId");
		
		int result = new MissingService().finishProtectPost(postNo, giveId);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result>0)
		{
			msg = "보호상태가 변경되었습니다.";
			loc = "/missingPet/protectPostList";
		}
		else
		{
			msg="보호상태 변경에 실패하였습니다. 관리자에게 문의해주세요.";
			loc = "/missingPet/protectPostView?no = " + postNo;
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
