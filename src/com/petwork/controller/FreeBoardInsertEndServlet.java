package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreeImg;
import com.petwork.model.vo.FreePost;
import com.petwork.model.vo.Member;

/**
 * Servlet implementation class FreeBoardInsertEndServlet
 */
@WebServlet("/board/freeBoardInsertEnd")
public class FreeBoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		char admin = ' ';
		String loc="";
		String view = "";
		String msg = "";
		if(loginMember!=null) {
			admin=loginMember.getAdminYN();
		}
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			if(admin=='Y') {
				loc="/views/board/admin_free_post.jsp";
			}
			else {
				loc="/views/board/free_post.jsp";
			}
			request.getRequestDispatcher(loc).forward(request, response);
		}
		else {
			try {
				
				String root = getServletContext().getRealPath("/");
				
				
				//게시판 파일 저장 경로 설정
				String saveDir = root+"views"+File.separator+"upload"+File.separator+"board";
				
				int maxSize = 1024*1024*10;
				
				MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());			
				
				Enumeration fils = mr.getFileNames();
								
				String file = "";
				String fileName = "";
				
				FreePost freePost = new FreePost();
				
				freePost.setRace_code(mr.getParameter("RaceCode"));
				freePost.setFreePostWriter(mr.getParameter("loginMember"));
				freePost.setFreePostTitle(mr.getParameter("freePostTitle"));
				freePost.setFreePostContent(mr.getParameter("postContent"));
				
				int result = new FreePostService().insertFreePost(freePost);
				
				//이미지 테이블에 넣기 위해 fk 가지고 오는 구문(하나 객체 가지고 오는 방법)
				FreePost rfreePost = new FreePostService().selectOne(freePost);
				
				int load=0;
				if(result>0) {
					
						//이미지가 있을 경우
						while(fils.hasMoreElements()) {
							FreeImg freeImg = new FreeImg();
							file = (String)fils.nextElement();
							if(file!=null) {
								fileName = mr.getFilesystemName(file);
								
								//해당 파일이 없는 경우 처리
								if(fileName==null)continue;
								
								freeImg.setFreeImgAddress(fileName);
								freeImg.setFreePostNo(rfreePost.getFreePostNo());
								load = new FreePostService().upload(freeImg);
								
							}
						}
						loc = "/board/freeBoardList";
						view = "/views/common/msg.jsp";
						msg = "글 등록 되었습니다";
				}
				else {
					//등록실패 할 경우
					loc = "/board/freeBoardList";
					view = "/views/common/msg.jsp";
					msg = "글 등록 실패하였습니다";
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("loc", loc);
			request.setAttribute("msg", msg);
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
