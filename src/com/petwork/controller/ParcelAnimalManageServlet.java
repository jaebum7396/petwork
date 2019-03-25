package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.DoctorService;
import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Animal;
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.ParcelAniView;

/**
 * Servlet implementation class ParcelAnimalManageServlet
 */
@WebServlet("/animalManage")
public class ParcelAnimalManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelAnimalManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		if(m==null)
		{
			request.getRequestDispatcher("/").forward(request, response);			
		}
		else if(m!=null&&m.getAdminYN()=='Y')
		{
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
				numPerPage = 10;
			}
			List<Animal> list = null;
			if(head==null||head.equals("A")) {
				list = new ParcelService().allAnimalList(cPage,numPerPage,head);
				int totalBoard = new ParcelService().animalCount(head);
				int totalPage = (int)Math.ceil((double)totalBoard/numPerPage);
				int pageBarSize = 5;
				int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
				int pageEnd=pageNo+pageBarSize-1;
				if(pageNo==1) {
					pageBar+="<span>[이전]</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
				}
				while(!(pageNo>pageEnd||pageNo>totalPage)) {
					if(cPage==pageNo) {
						pageBar+="<span class='cPage'>"+pageNo+"</span>";
					} else {
						pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
					}
					pageNo++;
				}
				if(pageNo>totalPage) {
					pageBar+="<span>[다음]</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
				}
			} else  {
				list = new ParcelService().allAnimalList(cPage,numPerPage,head);
				int totalBoard = new ParcelService().animalCount(head);
				int totalPage = (int)Math.ceil((double)totalBoard/numPerPage);
				int pageBarSize = 5;
				int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
				int pageEnd=pageNo+pageBarSize-1;
				if(pageNo==1) {
					pageBar+="<span>[이전]</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"&race_code="+head+"'>[이전]</a>";
				}
				while(!(pageNo>pageEnd||pageNo>totalPage)) {
					if(cPage==pageNo) {
						pageBar+="<span class='cPage'>"+pageNo+"</span>";
					} else {
						pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+pageNo+"&numPerPage="+numPerPage+"&race_code="+head+"'>"+pageNo+"</a>";
					}
					pageNo++;
				}
				if(pageNo>totalPage) {
					pageBar+="<span>[다음]</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/animalManage?cPage="+pageNo+"&numPerPage="+numPerPage+"&race_code="+head+"'>[다음]</a>";
				}
			} 
			request.setAttribute("head", head);
			request.setAttribute("list", list);
			request.setAttribute("cPage", cPage);
			request.setAttribute("numPerPage", numPerPage);
			request.setAttribute("pageBar", pageBar);
			request.getRequestDispatcher("/views/parcel/parcel_admin_animalList.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/").forward(request, response);
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
