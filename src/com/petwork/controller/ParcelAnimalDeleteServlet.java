package com.petwork.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.ParcelService;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class ParcelAnimalDeleteServlet
 */
@WebServlet("/deleteAnimal")
public class ParcelAnimalDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParcelAnimalDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		String view="";
		String msg ="잘못된 접근입니다.";
		String loc ="/";
		if(m==null)
		{
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/").forward(request, response);			
		}
		else if(m!=null&&m.getAdminYN()=='Y')
		{
			msg = "입력받은 값이 없습니다. 확인 후 다시 시도해주세요.";
			loc = "/animalManage";
			view = "/views/common/msg.jsp";
			String[] raceAndAnimalNo = request.getParameterValues("animal_ck");
			if(raceAndAnimalNo==null) {
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
			} else {
				String[] raceCode = new String[raceAndAnimalNo.length];
				String[] animalNo = new String[raceAndAnimalNo.length];
				int result=0;
				for(int i = 0; i < raceAndAnimalNo.length; i++) {
					raceCode[i] = raceAndAnimalNo[i].substring(0, 1);
					animalNo[i] = raceAndAnimalNo[i].substring(1);
					result += new ParcelService().deleteAnimal(animalNo[i],raceCode[i]);
				}
				
				if(result>0) {
					msg = "정상적으로 삭제되었습니다.";
				} else {
					msg = "삭제에 실패하였습니다. 다시 시도해주세요.";
				}
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
			}
		}
		else
		{
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
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
