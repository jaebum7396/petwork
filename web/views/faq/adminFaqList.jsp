<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.petwork.model.vo.Faq"%>
<%
	List<Faq> list = (List) request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
<style>
#section>h3 {
	font-weight: 600;
}
#section > div > div{
	text-align: center;
}
#section{
	text-align: center;
}
#section > div{
	margin-left: 10%;
}
</style>

<script>
 function fn_writeFaq(){
	 location.href="<%=request.getContextPath()%>/faq/adminFaqWrite";
	}
</script>
<section>
	<div class="container-fluid row" id="contents">
		<div class='col-md-2' id='nav'>
		    <div class="nav-side-menu">
		        <div class="brand">PETWORK</div>
		        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
		        <div class="menu-list">
		            <ul id="menu-content" class="menu-content collapse out">
		                <li>
		                    <a href="<%=request.getContextPath()%>/intoAdminmain"> HOME </a>
		                </li>
		                <li>
		                    <a href="<%= request.getContextPath()%>/adminmemberlistcontroller"> 회원관리 </a>
		            
		                </li>
		                <li>
		                    <a href="<%= request.getContextPath()%>/adminpetlistcontroller"> 동물관리 </a>
		                </li>
		                  <li data-toggle="collapse" data-target="#doctor" class="collapsed">
		                    <a href="#"> 수의사 관리 ▼ </a>
		                </li>
		                <ul class="sub-menu collapse" id="doctor">
		                    <li><a href="<%=request.getContextPath()%>/views/doctor_regist/doctorRegist.jsp">수의사 등록</a></li>
		                     <li><a href="<%=request.getContextPath()%>/doctor/doctorShow">수의사 명단</a></li>
		                </ul>
		                <li data-toggle="collapse" data-target="#board" class="collapsed">
		                    <a href="#"> 게시판 관리 ▼ </a>
		                </li>
		                <ul class="sub-menu collapse" id="board">
		                    <li><a href="<%=request.getContextPath()%>/board/freeBoardList">자유게시판</a></li>
		                    <li><a href="<%=request.getContextPath()%>/board/sharingBoardList">나눔게시판</a></li>
		                    <li><a href="<%=request.getContextPath()%>/parcelMain">분양게시판</a></li>
		                </ul>
						<li data-toggle="collapse" data-target="#missing"
							class="collapsed"><a href="#"> 실종/보호 관리 ▼ </a></li>
						<ul class="sub-menu collapse" id="missing">
							<li><a
								href="<%=request.getContextPath()%>/missingPet/findPostList?adminMode=true">찾아주세요</a></li>
							<li><a
								href="<%=request.getContextPath()%>/missingPet/protectPostList?adminMode=true">보호중입니다</a></li>
						</ul>
						<li data-toggle="collapse" data-target="#advice" class="collapsed">
		                    <a href="#"><span>문의 관리 ▼ </span></a>
		                </li>
		                <ul class="sub-menu collapse" id="advice">
		                    <li><a href="<%=request.getContextPath()%>/faq/faqList">FAQ</a></li>
		                </ul>
		            </ul>
		        </div>
		    </div>
		</div>
		<div class='col-md-10 mt-5' id='section'>
			<h3>자주 묻는 질문</h3>
			<div class="row mt-3">
				<div class="col-md-11">
					<table class="table table-bordered" id="free-post-table">
						<thead>
							<tr>
								<th width="120px">번호</th>
								<th>제목</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (list == null || list.size() == 0) {
							%>
							<tr>
								<td>FAQ 리스트가 비어있습니다.</td>
							</tr>
							<%
								} else {
									for (Faq f : list) {
							%>
							<tr>
								<td><%=f.getFaqNo()%></td>
								<td><a href="<%=request.getContextPath()%>/faq/faqView?no=<%=f.getFaqNo()%>"><%=f.getFaqTitle()%></a></td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row float-right">
				<div class="col-md-2">
					<input type="button" class="btn btn-primary" value="글쓰기"
						onclick="fn_writeFaq();">
				</div>

			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>