package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreePost;

/**
 * Servlet implementation class FreeBoardDeleteServlet
 */
@WebServlet("/board/freeBoardDelete")
public class FreeBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("loginMember");
		//히든 폼에서 부터 게시글 번호
		int freePostNo = Integer.parseInt(request.getParameter("freePostNo"));
		
		String root = getServletContext().getRealPath("/");
		
		String saveDir = root+"views"+File.separator+"upload"+File.separator+"board";
		
		//현 해당 하는 게시판에 대한 정보 가져오기
		List<FreePost> list=new FreePostService().selectViewText(freePostNo);
		
		//리스트가 있는 경우
		if(list.get(0).getFreeImgAddress()!=null) {
			
			int result = new FreePostService().deleteFreePostImg(freePostNo);
				
				if(result>0) {
					//게시판 삭제 후 파일 찾아서 해당 게시판에 있는 파일 삭제
					
					
					for(int j=0; j<list.size(); j++) {
						File deleteImg = new File(saveDir+File.separator+list.get(j).getFreeImgAddress());
						
						if(deleteImg.exists()) deleteImg.delete();
					}	
					
					int finalResult = new FreePostService().deleteFreePost(freePostNo);
					
					if(finalResult>0) {
						//게시판 삭제 완료
						
						request.getRequestDispatcher("/board/freeBoardList").forward(request, response);
						return;
					}
					else {
						//게시판 삭제 안됬을 경우!
						
						request.getRequestDispatcher("/board/freeBoardForm?freePostNo="+freePostNo).forward(request, response);
					}
				}
				else {
					
					request.getRequestDispatcher("/board/freeBoardForm?freePostNo="+freePostNo).forward(request, response);
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
