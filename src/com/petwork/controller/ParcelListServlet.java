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
import com.petwork.model.vo.Parcel;
import com.petwork.model.vo.ParcelAniView;

/**
 * Servlet implementation class ParcelMainServlet
 */
@WebServlet("/parcelMain")
public class ParcelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String head = request.getParameter("race_code");
		String pageBar="";
		int cPage;
		int numPerPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			cPage = 1;
		}
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 6;
		}
		List<ParcelAniView> list = null;
		int[] price = null;
		if(head==null||head.equals("A")) {
			list = new ParcelService().parcelList(cPage,numPerPage,head);
			price = new int[list.size()];
			for(int i = 0; i < list.size(); i++) {
				price[i] = list.get(i).getPrice()/10000;
			}
			int totalBoard = new ParcelService().selectCount(head);
			int totalPage = (int)Math.ceil((double)totalBoard/numPerPage);
			int pageBarSize = 5;
			int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd=pageNo+pageBarSize-1;
			if(pageNo==1) {
				pageBar+="<span>[이전]</span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
			}
			while(!(pageNo>pageEnd||pageNo>totalPage)) {
				if(cPage==pageNo) {
					pageBar+="<span class='cPage'>"+pageNo+"</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
				}
				pageNo++;
			}
			if(pageNo>totalPage) {
				pageBar+="<span>[다음]</span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
			}
		} else  {
			list = new ParcelService().parcelList(cPage,numPerPage,head);
			price = new int[list.size()];
			for(int i = 0; i < list.size(); i++) {
				price[i] = list.get(i).getPrice()/10000;
			}
			int totalBoard = new ParcelService().selectCount(head);
			int totalPage = (int)Math.ceil((double)totalBoard/numPerPage);
			int pageBarSize = 5;
			int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd=pageNo+pageBarSize-1;
			if(pageNo==1) {
				pageBar+="<span>[이전]</span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"&race_code="+head+"'>[이전]</a>";
			}
			while(!(pageNo>pageEnd||pageNo>totalPage)) {
				if(cPage==pageNo) {
					pageBar+="<span class='cPage'>"+pageNo+"</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+pageNo+"&numPerPage="+numPerPage+"&race_code="+head+"'>"+pageNo+"</a>";
				}
				pageNo++;
			}
			if(pageNo>totalPage) {
				pageBar+="<span>[다음]</span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/parcelMain?cPage="+pageNo+"&numPerPage="+numPerPage+"&race_code="+head+"'>[다음]</a>";
			}
		} 
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		char admin=' ';
		if(m!=null) {
			admin = m.getAdminYN();
		}
		request.setAttribute("price", price);
		request.setAttribute("head", head);
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);

		if(admin=='Y') {
			request.getRequestDispatcher("/views/parcel/parcel_admin_main.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/parcel/parcel_main.jsp").forward(request, response);
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
