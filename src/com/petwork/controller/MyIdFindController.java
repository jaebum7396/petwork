
package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MemberService;

/**
 * Servlet implementation class MyIdFindController
 */
@WebServlet("/myidfindcontroller")
public class MyIdFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyIdFindController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputPhone=request.getParameter("inputPhone");
		List<String> resultId= new MemberService().findIdService(inputPhone);
		String msg;
		String loc;
		String msgId="";
		
		if(!resultId.isEmpty())
		{
			for(int i=0; i<resultId.size(); i++)
			{
				
				String id=resultId.get(i);
				msgId+=id;
				
				if(i>0)
				{
					msgId+=',';
				}
			}
			msg="입력하신 휴대폰 번호로 "+msgId+" 이/가 등록되어 있습니다.";
			request.setAttribute("msgId", msgId);
			request.setAttribute("msg", msg);
		}
		else
		{
			msg="입력하신 휴대폰 번호로 아이디가 등록되어 있지 않습니다.";
			request.setAttribute("msg", msg);
		}
		RequestDispatcher rd= request.getRequestDispatcher("/views/member/myIdCheckPop.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
