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
import com.petwork.model.vo.ProtectPost;

/**
 * Servlet implementation class FindPostUpdateEndServlet
 */
@WebServlet("/missingPet/protectPostUpdateEnd")
public class ProtectPostUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectPostUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			request.setAttribute("msg", "보호신고 수정 오류[form:enctype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}

		String root = getServletContext().getRealPath("/");
		String saveDir = root + "views" + File.separator + "upload" + File.separator + "missingPet";
		int maxSize = 1024 * 1024 * 10; //10Mb
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				
		ProtectPost p = new ProtectPost();
		p.setProtectPostNo(Integer.parseInt(mr.getParameter("protectPostNo")));
		p.setProtectPostTitle(mr.getParameter("protectPostTitle"));
		p.setProtectPostContent(mr.getParameter("protectPostContent"));
		
		String identifyNo = mr.getParameter("protectPostPetIdentifyNo");
		if(identifyNo.trim().length() > 0) p.setProtectPetIdentifyNo(identifyNo);
		else p.setProtectPetIdentifyNo(null);
		
		p.setProtectPostPetGender(mr.getParameter("protectPostPetGender").charAt(0));
		p.setAnimalNo(mr.getParameter("protectPostAnimalName"));
		
		p.setProtectPostFindAddress(mr.getParameter("protectPostFindAddress"));
		
		DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
        try {
			date = formatter.parse(mr.getParameter("protectPostFindDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        p.setProtectPostFindDate(date);
    
		String reFileName = mr.getFilesystemName("protectPostReImg");
		String oriFileName = mr.getParameter("protectPostOriImg");
		
		if(reFileName != null)
		{
			p.setProtectPostImgAddress(reFileName);
		}else {
			p.setProtectPostImgAddress(oriFileName);
		}
		
		int result = new MissingService().updateProtectPost(p);
		
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
			msg = "보호신고가 수정되었습니다.";
			loc = "/missingPet/protectPostView?no=" + p.getProtectPostNo();
		}
		else
		{
			msg = "보호신고 수정에 실패하였습니다. 관리자에게 문의해주세요.";
			loc = "/missingPet/updateProtectPost?no=" + p.getProtectPostNo();
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
