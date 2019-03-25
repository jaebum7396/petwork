package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.QnaService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.Qna;

/**
 * Servlet implementation class QnaUserListServlet
 */
@WebServlet("/qna/qnaUserList")
public class QnaUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	
		Member m = (Member)session.getAttribute("loginMember");
		
		String view = "";
		String msg="";
		String loc = "";
		
		if(m!=null) {
			Qna rqna = new QnaService().selectOne(m);
			view = "/views/qna/qna.jsp";
			request.setAttribute("qnaUser", rqna);
		}else {
			loc = "/";
			view = "/views/common/msg.jsp";
			msg = "로그인 후 사용 가능합니다";
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
