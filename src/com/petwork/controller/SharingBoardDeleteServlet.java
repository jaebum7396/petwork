package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.SharingPostService;
import com.petwork.model.vo.SharingPost;


/**
 * Servlet implementation class SharingBoardDeleteServlet
 */
@WebServlet("/board/sharingBoardDelete")
public class SharingBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharingBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("loginMember");
		//히든 폼에서 부터 게시글 번호
		int sharingPostNo = Integer.parseInt(request.getParameter("sharingPostNo"));
		
		String root = getServletContext().getRealPath("/");
		String saveDir = root+"views"+File.separator+"upload"+File.separator+"board";
		
		//현 해당 하는 게시판에 대한 정보 가져오기
		List<SharingPost> list=new SharingPostService().selectViewText(sharingPostNo);
		
		//리스트가 있는 경우
		if(list.get(0).getSharingImgAddress()!=null) {
			
			int result = new SharingPostService().deleteSharingPostImg(sharingPostNo);
				
				if(result>0) {
					//게시판 삭제 후 파일 찾아서 해당 게시판에 있는 파일 삭제
					
					
					for(int j=0; j<list.size(); j++) {
						File deleteImg = new File(saveDir+File.separator+list.get(j).getSharingImgAddress());
						if(deleteImg.exists()) deleteImg.delete();
					}	
					
					int finalResult = new SharingPostService().deleteSharingPost(sharingPostNo);
					
					if(finalResult>0) {
						//게시판 삭제 완료
						
						request.getRequestDispatcher("/board/sharingBoardList").forward(request, response);
						return;
					}
					else {
						//게시판 삭제 안됬을 경우!
						
						request.getRequestDispatcher("/board/sharingBoardForm?sharingPostNo="+sharingPostNo).forward(request, response);
					}
				}
				else {
				
					request.getRequestDispatcher("/board/sharingBoardForm?sharingPostNo="+sharingPostNo).forward(request, response);
				}
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
