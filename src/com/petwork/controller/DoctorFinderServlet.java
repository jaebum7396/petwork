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
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class DoctorFinderServlet
 */
@WebServlet("/admin/doctorFinder")
public class DoctorFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				HttpSession session=request.getSession();
				Member m=(Member)session.getAttribute("loginMember");
				String view="";
				String msg ="잘못된 접근입니다.";
				String loc ="/";
				if(m==null)
				{	
					view="/views/common/msg.jsp";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					request.getRequestDispatcher(view).forward(request, response);			
				}
				else if(m!=null&&m.getAdminYN()=='Y')
				{
					String searchType=request.getParameter("searchType");
					String searchKeyword=request.getParameter("searchKeyword");
					
					int cPage;
					
					try
					{
						cPage=Integer.parseInt(request.getParameter("cPage"));
					}catch(NumberFormatException e)
					{
						cPage=1;
					}
					int numPerPage; // 페이지당 자료수
					try
					{
						numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
						
					}catch(NumberFormatException e)
					{
						numPerPage=5;
					}
					List<Doctor> list = null;
					switch(searchType)
					{
						case "doctorName" : list=new DoctorService().selectName(searchKeyword,cPage,numPerPage); break;
						case "doctorAddress" : list=new DoctorService().selectAddress(searchKeyword,cPage,numPerPage); break; 
					}
					int totalSelectSearchDoctor=0;
					
					switch(searchType)
					{
						case "doctorName" : totalSelectSearchDoctor=new DoctorService().selectDoctorNameCount(searchKeyword); break;
						case "doctorAddress" : totalSelectSearchDoctor=new DoctorService().selectDoctorAddressCount(searchKeyword); break; 
					}
					
					int totalPage=(int)Math.ceil((double)totalSelectSearchDoctor/numPerPage);
					String pageBar="";
					//페이지바 길이
					int pageBarSize=5;
					int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
					int pageEnd=pageNo+pageBarSize-1;
					if(pageNo==1) //1부터 5사이에 숫자면 무저건 1일때  맨앞페이지란것이다. 
					{ 
						pageBar+="<span>[이전]</span>"; //페이지가 1이면 이전버튼에대한 연결하는것이 없다. 그래서 span태그만을적용했다.
					}else
					{
						pageBar+="<a href='"+request.getContextPath()+"/doctor/doctorShow?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"&searchKeyword="+searchKeyword+"'class='page-link'>[이전]</a>";
						 
					}//선택페이지 만들기
					while(!(pageNo>pageEnd||pageNo>totalPage))
					{
						if(cPage==pageNo)
						{
							pageBar+="<span class='cPage'>"+pageNo+"</span>";//현재보고있는페이지가 페이지 no이면 바꿀필요가엇으니 그냥 스팬으로 ㅎㅎ
						}
						else
						{
							pageBar+="<a href='"+request.getContextPath()+"/doctor/doctorShow?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&searchKeyword="+searchKeyword+"'>"+pageNo+"</a>";
						}
						pageNo++; //pageEnd까지 증가한다. 
					}
					//[다음구현]
					if(pageNo>totalPage)
					{
						pageBar+="<span>[다음]</span>";
					}
					else
					{
						pageBar+="<a href='"+request.getContextPath()+"/doctor/doctorShow?cPage="+(pageNo)+"&numPerPage="+numPerPage+"&searchKeyword="+searchKeyword+"'>[다음]</a>";
					}
					request.setAttribute("list", list);
					request.setAttribute("cPage", cPage);
					request.setAttribute("numPerPage", numPerPage);
					request.setAttribute("pageBar", pageBar);
				
					request.getRequestDispatcher("/views/doctor_show/doctorFinder.jsp").forward(request, response);
				}else
				{
					view="/views/common/msg.jsp";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					request.getRequestDispatcher(view).forward(request, response);
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
