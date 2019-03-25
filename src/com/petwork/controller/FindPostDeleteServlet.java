package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MissingService;
import com.petwork.model.vo.FindPost;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class FindPostDeleteServlet
 */
@WebServlet("/missingPet/deleteFindPost")
public class FindPostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPostDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean adminMode = request.getParameter("adminMode")==null? false : true;
		
		if(adminMode == true) {
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			if(loginMember==null || loginMember.getAdminYN()=='N')
			{
				request.setAttribute("msg", "잘못된 접근입니다. 회원으로 로그인해주세요.");
				request.setAttribute("loc", "/views/member/loginpage.jsp");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				return;
			}
		}
		
		int postNo = Integer.parseInt(request.getParameter("no"));
		String img = request.getParameter("img");
		
		int result = new MissingService().deleteFindPost(postNo);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		String root = getServletContext().getRealPath("/");
		String saveDir = root + "views" + File.separator + "upload" + File.separator + "missingPet";
		 
		if(result>0)
		{	
			File file = new File(saveDir  + File.separator + img);
			if(file.exists()) file.delete();
			msg = "실종신고가 삭제되었습니다.";
			if(adminMode)
			{
				loc = "/missingPet/findPostList?adminMode=true";
			}else {
				loc = "/missingPet/findPostList";
			}
		}
		else
		{
			if(adminMode)
			{
				msg="실종신고 삭제에 실패하였습니다.";
				loc = "/missingPet/findPostView?no = " + postNo + "&adminMode=true";
			}else {
				msg="실종신고 삭제에 실패하였습니다. 관리자에게 문의해주세요.";
				loc = "/missingPet/findPostView?no = " + postNo;				
			}
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
