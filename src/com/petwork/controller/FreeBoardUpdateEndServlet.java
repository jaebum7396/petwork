package com.petwork.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.petwork.model.service.FreePostService;
import com.petwork.model.vo.FreePost;

/**
 * Servlet implementation class FreeBoardUpdateEndServlet
 */
@WebServlet("/board/freeBoardUpdateEnd")
public class FreeBoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("loginMember");
		int no = Integer.parseInt(request.getParameter("freePostNo"));
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.getRequestDispatcher("/views/board/admin_free_post.jsp").forward(request, response);
		}
		else {
			try {
				String root = getServletContext().getRealPath("/");
				String saveDir = root+"views"+File.separator+"upload"+File.separator+"board";
				int maxSize = 1024*1024*10;
				String encoding = "UTF-8";
				
				MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
				
				Enumeration fils = mr.getFileNames();
				
				FreePost freePost = new FreePost();
				
				//올드파일 가지고 오기 위해 리스트로 가져오기
				List<FreePost> list = new FreePostService().selectViewText(no);
				
				String oldfile0 = mr.getParameter("oldfile0");
				String oldfile1 = mr.getParameter("oldfile1");
				String oldfile2 = mr.getParameter("oldfile2");
				
				String[] str = new String[list.size()];
				int j =0;
				
				while(fils.hasMoreElements()) {
					String file = (String)fils.nextElement();
					if(file!=null) {
						String fileName = mr.getFilesystemName(file);
						if(fileName==null)continue;
						str[j]=fileName;
						}
					j++;
				}
				
				//결과 값 반환변수!
				int result = 0;
				
					for(int index=0; index<j; index++) {
						if(list.get(index).getFreeImgAddress()!=null) {
							if(list.get(index).getFreeImgAddress().equals(str[index])) {
								
								list.get(index).setFreeImgAddress(mr.getParameter("oldfile"+index));
								
							}
							if(!list.get(index).getFreeImgAddress().equals(str[index])) {
								
								list.get(index).setFreeImgAddress(str[index]);
								
								
								//바뀌면 기존 파일 삭제 
								File deleteFile = new File(saveDir + File.separator + mr.getParameter("oldfile"+index));
								if(deleteFile.exists()) deleteFile.delete();
								
							}
						}
						
					}
				
				for(int index=0; index<list.size(); index++) {
					
					result = new FreePostService().updateFreePostImg(list.get(index), index);
					
					if(result > 0) {
						
						list.get(index).setFreePostNo(no);
						list.get(index).setRace_code(mr.getParameter("RaceCode"));
						list.get(index).setFreePostWriter(mr.getParameter("loginMember"));
						list.get(index).setFreePostTitle(mr.getParameter("freePostTitle"));
						list.get(index).setFreePostContent(mr.getParameter("freePostContent"));
						
						int finalResult = new FreePostService().updateFreePost(list.get(index), index);
						
						if(finalResult>0) {
							
							request.setAttribute("list", list);
							request.getRequestDispatcher("/board/freeBoardForm?freePostNo="+list.get(0).getFreePostNo()).forward(request, response);
						}
					}
					else {
						//그림 삽입 안 됬을 경우
						request.getRequestDispatcher("/board/freeBoardForm").forward(request, response);
						return;
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
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
