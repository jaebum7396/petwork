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
import com.petwork.model.service.SharingPostService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.SharingImg;
import com.petwork.model.vo.SharingPost;

/**
 * Servlet implementation class SharingBoardInsertEndServlet
 */
@WebServlet("/board/sharingBoardInsertEnd")
public class SharingBoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharingBoardInsertEndServlet() {
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
		String view = "/views/common/msg.jsp";
		String msg = "";
		if(loginMember!=null) {
			admin=loginMember.getAdminYN();
		}
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			if(admin=='Y') {
				loc="/views/board/admin_sharing_post.jsp";
			}
			else {
				loc="/views/board/sharing_post.jsp";
			}
			request.getRequestDispatcher(loc).forward(request, response);
		}
		else {
			try {
				//String root = getServletContext().getRealPath("/");
				String root = getServletContext().getRealPath("/");
				
				//게시판 파일 저장 경로 설정
				String saveDir = root+"views"+File.separator+"upload"+File.separator+"board";
				
				int maxSize = 1024*1024*10;
				
				MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());			
				
				Enumeration fils = mr.getFileNames();
								
				String file = "";
				String fileName = "";
				
				SharingPost sharingPost = new SharingPost();
				
				sharingPost.setRace_code(mr.getParameter("RaceCode"));
				sharingPost.setSharingPostWriter(mr.getParameter("loginMember"));
				sharingPost.setSharingPostTitle(mr.getParameter("sharingPostTitle"));
				sharingPost.setSharingPostContent(mr.getParameter("postContent"));
				sharingPost.setSharingPostAddress(mr.getParameter("sharingPostAddress"));
				sharingPost.setProductCategoryCode(mr.getParameter("product"));
				
				int result = new SharingPostService().insertSharingPost(sharingPost);
				
				//이미지 테이블에 넣기 위해 fk 가지고 오는 구문(하나 객체 가지고 오는 방법)
				SharingPost rsharingPost = new SharingPostService().selectOne(sharingPost);
				
				int load=0;
				if(result>0) {
					
						//이미지가 있을 경우
						while(fils.hasMoreElements()) {
							SharingImg sharingImg = new SharingImg();
							file = (String)fils.nextElement();
							if(file!=null) {
								fileName = mr.getFilesystemName(file);
								
								//해당 파일이 없는 경우 처리
								if(fileName==null)continue;
								
								sharingImg.setSharingImgAddress(fileName);
								sharingImg.setSharingPostNo(rsharingPost.getSharingPostNo());
								load = new SharingPostService().upload(sharingImg);
								
							}
						}
						loc = "/board/sharingBoardList";
						msg = "글 등록 되었습니다";
				}
				else {
					//등록실패 할 경우
					loc = "/board/sharingBoardList";
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
