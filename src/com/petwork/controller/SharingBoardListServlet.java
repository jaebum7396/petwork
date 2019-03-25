package com.petwork.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petwork.model.service.SharingPostService;
import com.petwork.model.vo.Member;
import com.petwork.model.vo.SharingPost;

/**
 * Servlet implementation class SharingBoardListServlet
 */
@WebServlet("/board/sharingBoardList")
public class SharingBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SharingBoardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//////////////////////////////////////////////////////////////////
		// 메인 화면 보여주는 서블릿
		//////////////////////////////////////////////////////////////////
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		char admin =' ';
		String loc = "";
		if(loginMember!=null) {
			admin = loginMember.getAdminYN();
		}
		if(admin=='Y') {
			loc="/views/board/admin_sharing_post.jsp";
		}else {
			loc="/views/board/sharing_post.jsp";
		}
		
		String race_code = request.getParameter("race_code") == null ? "A" : request.getParameter("race_code");
		String product = request.getParameter("product") == null ? "allProduct" : request.getParameter("product");
		String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword") == null ? "": request.getParameter("searchKeyword");

		List<SharingPost> list = null;
		List<SharingPost> adminList = new SharingPostService().selectAdminList();
		
		// page처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}

		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 3;
		}

		int pageSize = 5;
		int pageNo = ((cPage - 1) / pageSize) * pageSize + 1;
		int pageEnd = pageNo + pageSize - 1;

		// 검색을 하였을 경우
		if (searchKeyword.trim().length() != 0) {

			switch (searchType) {
			case "sharingPostTitle":
				list = new SharingPostService().selectSharingPostTitle(searchKeyword, cPage, numPerPage);
				break;
			case "sharingPostWriter":
				list = new SharingPostService().selectSharingPostWriter(searchKeyword, cPage, numPerPage);
				break;
			case "sharingPostContent":
				list = new SharingPostService().selectSharingPostContent(searchKeyword, cPage, numPerPage);
				break;
			}

			int totalSharingPost = 0;

			switch (searchType) {
			case "sharingPostTitle":
				totalSharingPost = new SharingPostService().selectSharingPostTitleCount(searchKeyword);
				break;
			case "sharingPostWriter":
				totalSharingPost = new SharingPostService().selectSharingPostWriterCount(searchKeyword);
				break;
			case "sharingPostContent":
				totalSharingPost = new SharingPostService().selectSharingPostContentiCount(searchKeyword);
				break;
			}

			int totalPage = (int) Math.ceil((double) totalSharingPost / numPerPage);
			String pageBar="";

			if (pageNo == 1) // 1부터 5사이에 숫자면 무저건 1일때 맨앞페이지란것이다.
			{
				pageBar += "<span>[이전]</span>"; // 페이지가 1이면 이전버튼에대한 연결하는것이 없다. 그래서 span태그만을적용했다.
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo - 1)
						+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword
						+ "'class='page-link'>[이전]</a>";

			} // 선택페이지 만들기
			while (!(pageNo > pageEnd || pageNo > totalPage)) {
				if (cPage == pageNo) {
					pageBar += "<span class='cPage'>" + pageNo + "</span>";// 현재보고있는페이지가 페이지 no이면 바꿀필요가엇으니 그냥 스팬으로 ㅎㅎ
				} else {
					pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
							+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>" + pageNo + "</a>";
				}
				pageNo++; // pageEnd까지 증가한다.
			}
			// [다음구현]
			if (pageNo > totalPage) {
				pageBar += "<span>[다음]</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>[다음]</a>";
			}

			request.setAttribute("pageBar", pageBar);
			
		} else {
			if (race_code.equals("A")) {

				if (product.equals("allProduct")) {
					
					// 전체 조회(null)
					int totalSharingPost = new SharingPostService().selectCount(race_code,product);
					
					String pageBar="";
					int totalPage = (int) Math.ceil((double) totalSharingPost / numPerPage);

					list = new SharingPostService().selectList(cPage, numPerPage, race_code, product);
					
					if (pageNo == 1) {
						pageBar += "<span>[이전]</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage="
								+ (pageNo - 1) + "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product="
								+ product + "'>[이전]</a>";
					}
					// 선택페이지 만들기
					while (!(pageNo > pageEnd || pageNo > totalPage)) {
						if (cPage == pageNo) {
							pageBar += "<span class='cPage'>" + pageNo + "</span>";
						} else {
							pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage="
									+ (pageNo) + "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product="
									+ product + "'>" + pageNo + "</a>";
						}
						pageNo++;
					}
					// [다음]구현

					if (pageNo > totalPage) {
						pageBar += "<span>[다음]</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
								+ "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product=" + product
								+ "'>[다음]</a>";
					}
					request.setAttribute("pageBar", pageBar);
					
				} else {
					// 레이스코드 : 전체 상품 : 선택 하였을 경우

					int totalSharingPost = new SharingPostService().selectCount(race_code,product);
					
					
					int totalPage = (int) Math.ceil((double) totalSharingPost / numPerPage);
					String pageBar="";
					
					list = new SharingPostService().selectList(cPage, numPerPage, race_code, product);

					if (pageNo == 1) {
						pageBar += "<span>[이전]</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage="
								+ (pageNo - 1) + "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product="
								+ product + "'>[이전]</a>";
					}
					// 선택페이지 만들기
					while (!(pageNo > pageEnd || pageNo > totalPage)) {
						if (cPage == pageNo) {
							pageBar += "<span class='cPage'>" + pageNo + "</span>";
						} else {
							pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage="
									+ (pageNo) + "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product="
									+ product + "'>" + pageNo + "</a>";
						}
						pageNo++;
					}
					// [다음]구현

					if (pageNo > totalPage) {
						pageBar += "<span>[다음]</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
								+ "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product=" + product
								+ "'>[다음]</a>";
					}
					request.setAttribute("pageBar", pageBar);
				}

			} else {
				// 강아지 코드 선택 하였을 경우

				int totalSharingPost = new SharingPostService().selectCount(race_code,product);
				
				int totalPage = (int) Math.ceil((double) totalSharingPost / numPerPage);
				
				String pageBar="";
				

				list = new SharingPostService().choiceSelectList(cPage, numPerPage, race_code);

				if (pageNo == 1) {
					pageBar += "<span>[이전]</span>";
				} else {
					pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo - 1)
							+ "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product=" + product
							+ "'>[이전]</a>";
				}
				// 선택페이지 만들기
				while (!(pageNo > pageEnd || pageNo > totalPage)) {
					if (cPage == pageNo) {
						pageBar += "<span class='cPage'>" + pageNo + "</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
								+ "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product=" + product + "'>"
								+ pageNo + "</a>";
					}
					pageNo++;
				}
				// [다음]구현

				if (pageNo > totalPage) {
					pageBar += "<span>[다음]</span>";
				} else {
					pageBar += "<a href='" + request.getContextPath() + "/board/sharingBoardList?cPage=" + (pageNo)
							+ "&numPerPage=" + numPerPage + "&race_code=" + race_code + "&product=" + product
							+ "'>[다음]</a>";
					
				}
				request.setAttribute("pageBar", pageBar);
			}

		}
		
		
		request.setAttribute("adminList", adminList);
		request.setAttribute("race_code", race_code);
		request.setAttribute("product", product);
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		
		
		request.getRequestDispatcher(loc).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
