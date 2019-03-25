<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.Faq" %>
<%@ include file="/views/common/header.jsp"%>
<%
	Faq f = (Faq)request.getAttribute("faq");
%>
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
$(function(){
	var btnUpdateEnd = document.getElementById('btnUpdateEndFaq');
	btnUpdateEnd.style.display = "none";
})
function fn_updateFaq()
{
	var btnUpdate = document.getElementById('btnUpdateFaq');
	btnUpdate.style.display = "none";
	var btnUpdateEnd = document.getElementById('btnUpdateEndFaq');
	btnUpdateEnd.style.display = "block";
	
	var faqTitle = document.getElementById('faqTitle');
	faqTitle.disabled = false;
	faqTitle.required = true;
	var faqContent = document.getElementById('faqContent');
	faqContent.disabled = false;
	faqContent.required = true;
}
function fn_deleteFaq(){
	if(!confirm("FAQ를 삭제하시겠습니까?"))
	{
		return;
	}
	location.href="<%=request.getContextPath()%>/faq/deleteFaq?faqNo=" + <%=f.getFaqNo()%>;
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
				<form method="post" action="<%=request.getContextPath()%>/faq/adminFaqUpdateEnd">
					<table class="table table-bordered">
						<tr>
							<th>번호</th>
							<td><%= f.getFaqNo()%><input type="hidden" name="faqNo" value="<%=f.getFaqNo()%>">
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control" id="faqTitle" name="faqTitle"
								value="<%=f.getFaqTitle()%>" disabled/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea id="faqContent" name="faqContent" class="form-control" style="resize: none; "rows="15" disabled><%=f.getFaqContent() %></textarea>
							</td>
						</tr>
					</table>
					<input type="button" value="수정" class="btn btn-primary float-right px-4" id="btnUpdateFaq" onclick="fn_updateFaq();">
					<input type="submit" value="완료" class="btn btn-primary float-right px-4" id="btnUpdateEndFaq">
					<input type="button" value="삭제" class="btn btn-primary float-right mr-2 px-4" onclick="fn_deleteFaq();">
				</form>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>