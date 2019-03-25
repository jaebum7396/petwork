package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MissingService;
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class FindPostFinishServlet
 */
@WebServlet("/missingPet/finishFindPost")
public class FindPostFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPostFinishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("no"));
		int result = new MissingService().finishFindPost(postNo);
		String identifyNo = request.getParameter("identifyNo");
		
		//보호중 게시판에 해당 동물의 인식번호가 일치하는 글이 올라와있는지 확인하고 상태변경
		int updateResult = new MissingService().updateProtectPostYn(identifyNo);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result>0)
		{
			msg = "실종상태가 변경되었습니다.";
			loc = "/missingPet/findPostList";
		}
		else
		{
			msg="실종상태 변경에 실패하였습니다. 관리자에게 문의해주세요.";
			loc = "/missingPet/findPostView?no = " + postNo;
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
