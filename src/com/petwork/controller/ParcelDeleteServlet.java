package com.petwork.controller;

import java.io.File;
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
import com.petwork.model.vo.ParcelImg;

/**
 * Servlet implementation class ParcelDeleteServlet
 */
@WebServlet("/parcelDelete")
public class ParcelDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String root = getServletContext().getRealPath("/");
		String saveDir = root + "views/upload/parcel";
		List<ParcelImg> pImgs = new ParcelService().parcelImg(postNo);
		for(int i = 0;i<pImgs.size();i++) {
			File originalFile = new File(saveDir+File.separator+request.getParameter("parcelImg"+(i+1)));
			if(originalFile.exists()) originalFile.delete();
		}
		int imgResult = new ParcelService().parcelDeleteImg(postNo);
		int postResult = new ParcelService().parcelDeletePost(postNo);
		
		String loc="";
		String msg="";
		String view="/views/common/msg.jsp";
		if(imgResult>0) {
			if(postResult>0) {
				loc="/parcelMain";
				msg="정상적으로 삭제되었습니다.";
			} 
		} else {
			loc="/parcel/parcelDetailView?postNo="+postNo;
			msg="삭제에 실패하였습니다.";
		}
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
