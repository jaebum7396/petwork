package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.CityService;
import com.petwork.model.service.MissingService;
import com.petwork.model.vo.City;
import com.petwork.model.vo.FindPost;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class FindPostListServlet
 */
@WebServlet("/missingPet/protectPostList")
public class ProtectPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		boolean adminMode = request.getParameter("adminMode")==null? false : true;
		String category = request.getParameter("category")==null? "allCategory" : request.getParameter("category");
		String searchFlag = request.getParameter("searchFlag")==null? "false" : "true";
		String cityCode = request.getParameter("cityCode")==null? "allCity" : request.getParameter("cityCode");
		String searchCategory = request.getParameter("searchCategory")==null? "pet_identify_no" : request.getParameter("searchCategory");
		String searchWord = request.getParameter("searchWord")==null? "" : request.getParameter("searchWord");	
		String pageBarType = "";
		String view = "";
		
		if(adminMode == true) {
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			if(loginMember==null || loginMember.getAdminYN()=='N')
			{
				request.setAttribute("msg", "잘못된 접근입니다. 회원으로 로그인해주세요.");
				request.setAttribute("loc", "/views/member/loginpage.jsp");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				return;
			}
			pageBarType = "&adminMode=true";
			view = "/views/missing_pet/admin_protect_post_list.jsp";
		}else {
			view = "/views/missing_pet/protect_post_list.jsp";
		}
		
		//하단 검색창에 검색단어를 입력하지 않았을 경우 전체 리스트 출력
		if(searchWord.trim().length() == 0) 
		{
			searchFlag = "false";
			searchCategory = "pet_identify_no";
		}
		
		int cPage;
		try { 
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e)
		{
			cPage=1;
		}
		
		int numPerPage = 6;
		
		int totalFind = 0;
		int totalPage = 0;
		List<ProtectPost> protectPostList = null;

		if(category.equals("allCategory"))
		{
			if(cityCode.equals("allCity"))
			{
				if(searchFlag.equals("true")) //전체동물 전체지역 검색선택
				{	
					searchCategory = request.getParameter("searchCategory");			
					searchWord = request.getParameter("searchWord");
					
					totalFind = new MissingService().selectProtectPostCount(searchCategory, searchWord);	
					totalPage = (int)Math.ceil((double)totalFind/numPerPage);						
					protectPostList = new MissingService().selectProtectPostList(cPage, numPerPage, searchCategory, searchWord);
					

					if(adminMode)
					{
						pageBarType = "&searchFlag=" + searchFlag + "&searchCategory=" + searchCategory + "&searchWord=" + searchWord + "&adminMode=true";
					}else {
						pageBarType = "&searchFlag=" + searchFlag + "&searchCategory=" + searchCategory + "&searchWord=" + searchWord;
					}
					
				}else {	//전체동물 전체지역 검색미선택	
					totalFind = new MissingService().selectProtectPostCount();
					totalPage = (int)Math.ceil((double)totalFind/numPerPage);
					protectPostList = new MissingService().selectProtectPostList(cPage,numPerPage);
					
					if(adminMode)
					{
						pageBarType = "&adminMode=true";
					}
				}
			}else { //전체동물 선택지역 검색미선택
				totalFind = new MissingService().selectCityProtectPostCount(cityCode);
				totalPage = (int)Math.ceil((double)totalFind/numPerPage);
				protectPostList = new MissingService().selectCityProtectPostList(cPage,numPerPage,cityCode);
				
				if(adminMode)
				{
					pageBarType = "&cityCode=" + cityCode + "&adminMode=true";
				}else {
					pageBarType = "&cityCode=" + cityCode;
				}
			}
		}else { //선택동물 전체지역 검색미선택
			totalFind = new MissingService().selectProtectPostCount(category);
			totalPage = (int)Math.ceil((double)totalFind/numPerPage);
			protectPostList = new MissingService().selectProtectPostList(cPage, numPerPage, category);
			
			if(adminMode)
			{
				pageBarType = "&category=" + category + "&adminMode=true";
			}
			else {
				pageBarType = "&category=" + category;
			}
		}
	
		String pageBar="";
		int pageSize=5;
		int pageNo=((cPage-1)/pageSize)*pageSize+1;
		int pageEnd=pageNo+pageSize-1;
		
		if(pageNo==1)
		{
			pageBar+="<span>[이전]</span>";
		}
		else 
		{
			pageBar+="<a href='"+request.getContextPath()
			+"/missingPet/protectPostList?cPage="+(pageNo-1)
			+"&numPerPage="+numPerPage+pageBarType+"'>[이전]</a>";
		}
		//선택페이지 만들기
		while(!(pageNo>pageEnd||pageNo>totalPage))
		{
			if(cPage==pageNo)
			{
				pageBar+="<span class='cPage'>"+pageNo+"</span>";
			}
			else
			{
				pageBar+="<a href='"+request.getContextPath()
				+"/missingPet/protectPostList?cPage="+(pageNo)
				+"&numPerPage="+numPerPage+pageBarType+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//[다음]구현		
		if(pageNo>totalPage)
		{
			pageBar+="<span>[다음]</span>";
		}
		else 
		{
			pageBar+="<a href='"+request.getContextPath()
					+"/missingPet/protectPostList?cPage="+pageNo
					+"&numPerPage="+numPerPage+pageBarType+"'>[다음]</a>";
		}
		
		//지역 - 도시
		List<City> cityList = new CityService().selectCityList();
		
		request.setAttribute("cPage",cPage);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("protectPostList", protectPostList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("category", category);
		request.setAttribute("cityCode", cityCode);
		request.setAttribute("searchCategory", searchCategory);
		request.setAttribute("searchWord", searchWord);
		
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
