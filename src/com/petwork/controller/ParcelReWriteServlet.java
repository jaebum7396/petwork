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
 * Servlet implementation class ParcelReWriteServlet
 */
@WebServlet("/ParcelRewrite")
public class ParcelReWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelReWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int postNo=Integer.parseInt(request.getParameter("postNo"));
		ParcelAniView p = new ParcelService().reWrite(postNo);
		String cityName = p.getAddress().substring(0, 3);
		String cityCode = new ParcelService().findCityCode(cityName);
		ParcelImg pMainImg = new ParcelService().parcelDetailMainImg(postNo);
		List<ParcelImg> pSubImg = new ParcelService().parcelDetailSubImg(postNo);
		request.setAttribute("rewrite", p);
		request.setAttribute("pMainImg", pMainImg);
		request.setAttribute("pSubImg", pSubImg);
		request.setAttribute("cityCode", cityCode);
		String loc="";
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		char admin=' ';
		if(loginMember!=null) {
			admin = loginMember.getAdminYN();
		}
		if(admin=='Y') {
			loc = "/views/parcel/parcel_admin_rewrite.jsp";
		} else {
			loc = "/views/parcel/parcel_rewrite.jsp";
		}
		request.getRequestDispatcher(loc).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
