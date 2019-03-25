package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.petwork.model.service.MissingService;
import com.petwork.model.vo.FindPost;

/**
 * Servlet implementation class FindPostRegEnd
 */
@WebServlet("/missingPet/findPostRegEnd")
public class FindPostRegEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPostRegEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			request.setAttribute("msg", "실종신고 오류[form:enctype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}

		String root = getServletContext().getRealPath("/");
		String saveDir = root + "views" + File.separator + "upload" + File.separator + "missingPet";
		int maxSize = 1024 * 1024 * 10; //10Mb
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		FindPost p = new FindPost();
		p.setMemberId(mr.getParameter("memberId"));
		p.setPetNo(Integer.parseInt(mr.getParameter("petNo")));
		p.setFindPostTitle(mr.getParameter("findPostTitle"));
		p.setFindPostContent(mr.getParameter("findPostContent"));
		p.setFindPostMissingAddress(mr.getParameter("findPostMissingAddress"));
		
		String missingAddr = mr.getParameter("findPostMissingAddress");		
		
		DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
        try {
			date = formatter.parse(mr.getParameter("findPostMissingDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        p.setFindPostMissingDate(date);
        
		char reward = mr.getParameter("findPostReward").charAt(0); 
		p.setFindPostReward(reward);
		if(reward == 'N' || reward == 'C')
		{
			p.setFindPostSum(0);
		}else {
			p.setFindPostSum(Integer.parseInt(mr.getParameter("findPostSum")));
		}
		
		String fileName = mr.getFilesystemName("findPostImgAddress");
		p.setFindPostImgAddress(fileName);
				
		int result = new MissingService().insertFindPost(p);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result>0)
		{
			msg = "실종신고가 등록되었습니다.";
			loc = "/missingPet/findPostList";
		}
		else
		{
			msg="실종신고 등록에 실패하였습니다. 관리자에게 문의해주세요.";
			loc = "/missingPet/findPostReg";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
