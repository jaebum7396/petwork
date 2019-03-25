package com.petwork.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.AdminMemberDeleteService;

/**
 * Servlet implementation class AdminMemberDeleteController
 */
@WebServlet("/adminmemberdeletecontroller")
public class AdminMemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "입력받은 값이 없습니다. 확인 후 다시 시도해주세요.";
		String loc="/adminmemberlistcontroller";
	    String view = "/views/common/msg.jsp";
		String[] delMember=request.getParameterValues("delCheck");
	    
		if(delMember==null) 
	    {
           request.setAttribute("msg", msg);
           request.setAttribute("loc", loc);
           request.getRequestDispatcher(view).forward(request, response);
	    }
	    else
	    {
	    	int result=new AdminMemberDeleteService().AdminMemberDeleteService(delMember);
						
			if(result>0)
			{
				msg="삭제 되었습니다.";
			}
			else
			{
				msg="삭제 실패하였습니다.";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd=request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request, response);
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
