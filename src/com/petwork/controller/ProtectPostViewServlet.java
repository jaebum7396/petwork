package com.petwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MissingService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class FindPostView
 */
@WebServlet("/missingPet/protectPostView")
public class ProtectPostViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean adminMode = request.getParameter("adminMode")==null? false : true;
		int postNo = Integer.parseInt(request.getParameter("no"));
		
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
		
		ProtectPost p = new MissingService().selectProtectPost(postNo);
		
		String view="";
		if(p != null)
		{
			if(adminMode == true) {
				view="/views/missing_pet/admin_protect_post_view.jsp";
			}else {
				view="/views/missing_pet/protect_post_view.jsp";
			}
			request.setAttribute("protectPost", p);			
		}
		else 
		{
			view="/views/common/msg.jsp";
			request.setAttribute("msg", "게시글이 없습니다");
			if(adminMode == true) {
				request.setAttribute("loc", "/missingPet/protectPostList?adminMode=true");
			}else {
				request.setAttribute("loc", "/missingPet/protectPostList");
			}
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
