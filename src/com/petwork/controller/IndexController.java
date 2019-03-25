package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.IndexService;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.SharingPost;
import com.petwork.model.vo.Slider;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/indexcontroller")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IndexService is= new IndexService();
		
		//슬라이더
		List<Slider> sliderList=is.indexSliderService();
		
		//게시판 별 새글 알림
		List<FreePost> freeList=is.indexFreePostService();
		List<Parcel> parcelList=is.indexParcelPostService();
		List<SharingPost> sharingList=is.indexShaingPostService();
		
		request.setAttribute("freeList", freeList);
		request.setAttribute("sliderList", sliderList);
		request.setAttribute("parcelList", parcelList);
		request.setAttribute("sharingList", sharingList);
		
		request.getRequestDispatcher("/views/common/main.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
