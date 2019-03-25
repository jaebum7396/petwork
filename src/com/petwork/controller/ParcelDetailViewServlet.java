package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.ParcelAniView;
import com.petwork.model.vo.ParcelImg;

/**
 * Servlet implementation class ParcelDetailViewServlet
 */
@WebServlet("/parcel/parcelDetailView")
public class ParcelDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		ParcelAniView pav = new ParcelService().parcelDetailView(postNo);
		List<ParcelImg> list = new ParcelService().parcelDetailSubImg(postNo);
		int price = pav.getPrice()/10000;
		
		ParcelImg pImg = new ParcelService().parcelDetailMainImg(postNo);
		String id = pav.getId();
		Member m = new ParcelService().parcelDetailMember(id);
		String view = "";
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		char admin=' ';
		if(loginMember!=null) {
			admin = loginMember.getAdminYN();
		}
		if(admin=='Y') {
			view = "/views/parcel/parcel_admin_detail.jsp";
		} else {
			view = "/views/parcel/parcel_detail.jsp";
		}
		if(pav != null) {
			request.setAttribute("price", price);
			request.setAttribute("parcel", pav);
			request.setAttribute("member", m);
			request.setAttribute("list", list);
			request.setAttribute("pImg", pImg);
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
