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
 * Servlet implementation class FindPostUpdateEndServlet
 */
@WebServlet("/missingPet/findPostUpdateEnd")
public class FindPostUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPostUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			request.setAttribute("msg", "실종신고 수정 오류[form:enctype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}

		String root = getServletContext().getRealPath("/");
		String saveDir = root + "views" + File.separator + "upload" + File.separator + "missingPet";
		int maxSize = 1024 * 1024 * 10; //10Mb
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				
		FindPost p = new FindPost();
		p.setFindPostNo(Integer.parseInt(mr.getParameter("findPostNo")));
		p.setFindPostTitle(mr.getParameter("findPostTitle"));
		p.setFindPostContent(mr.getParameter("findPostContent"));
		p.setFindPostMissingAddress(mr.getParameter("findPostMissingAddress"));
		
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
		
		String reFileName = mr.getFilesystemName("findPostReImg");
		String oriFileName = mr.getParameter("findPostOriImg");
		
		if(reFileName != null)
		{
			p.setFindPostImgAddress(reFileName);
		}else {
			p.setFindPostImgAddress(oriFileName);
		}
		
				
		int result = new MissingService().updateFindPost(p);
		
		String view = "/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result>0)
		{
			if(reFileName != null)
			{			
				File oriFile = new File(saveDir  + File.separator + oriFileName);
				if(oriFile.exists()) oriFile.delete();
			}
			msg = "실종신고가 수정되었습니다.";
			loc = "/missingPet/findPostView?no=" + p.getFindPostNo();
		}
		else
		{
			msg = "실종신고 수정에 실패하였습니다. 관리자에게 문의해주세요.";
			loc = "/missingPet/updateFindPost?no=" + p.getFindPostNo();
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
