<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.petwork.model.vo.SharingPost" %>
<%
	List<SharingPost> list = (List)request.getAttribute("list");
	List<SharingPost> adminList = (List)request.getAttribute("adminList");
	int cPage = (int)request.getAttribute("cPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String race_code = (String)request.getAttribute("race_code");
	String product = (String)request.getAttribute("product");
%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

<script>
window.onload=function(){
	   var sharingPostTitle=document.querySelector("#search-sharingPostTitle");
	   var sharingPostWriter=document.querySelector("#search-sharingPostWriter");
	   var sharingPostContent=document.querySelector("#search-sharingPostContent");
	   
	   var searchType=document.querySelector("#searchType"); //셀렉트 태그를 가지고온것임 쿼리셀렉터는 선택자이용해서 가지고오는거얌
	   searchType.addEventListener("change",function(){ //change변경됐을때 실행해 그럼 아래 3줄다 없어진다.
		   sharingPostTitle.style.display="none";
		   sharingPostWriter.style.display="none";
		   sharingPostContent.style.display="none";
	      document.querySelector("#search-"+this.value).style.display="inline-block"; 
	   });
	};
	
function fn_raceCodeChanged(){
   var race_code = $("#post-list-category-raceCode option:selected").val();
   location.href= "<%=request.getContextPath()%>/board/sharingBoardList?race_code="+race_code;
};

function fn_productChanged(){
   var product = $("#post-list-category-product option:selected").val();
   location.href= "<%=request.getContextPath()%>/board/sharingBoardList?product=" + product;
};
</script>
<style>
	#sharing-post-table{
		table-layout:fixed;
	}
	td{
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
	}
	#sharing-post-table > thead > tr > th{
		padding-left:0;
		padding-right:0;
	}
	#sharing-post-table > tbody > tr > td{
		padding-left:0;
		padding-right:0;
	}
	#sharing-post-table > thead > tr > th:nth-child(1){
		width:10%;
	}
	#sharing-post-table > thead > tr > th:nth-child(3){
		width:15%;
	}
	#sharing-post-table > thead > tr > th:nth-child(4){
		width:20%;
	}
	#sharing-post-table > thead > tr > th:nth-child(5){
		width:15%;
	}
	#sharing-post-table > thead > tr > th:nth-child(6){
		width:8%;
	}
	
</style>
<section>
	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2 admin-post-list-nav' id='nav'></div>

			<div class='col-md-8' id='section'>
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
                      <div class="col-md-2">
	                	<select id="post-list-category-raceCode" name="PRODUCT_CATEGORY_CODE"
		                     class="post-list-select form-control" onchange="fn_raceCodeChanged();">
		                     <option value="A" <%=race_code.equals("A") ? "selected" : ""%> selected>전체</option>
		                     <option value="D" <%=race_code.equals("D") ? "selected" : ""%>>강아지</option>
		                     <option value="C" <%=race_code.equals("C") ? "selected" : ""%>>고양이</option>
		                     <option value="E" <%=race_code.equals("E") ? "selected" : ""%>>기타</option>
	                  	</select>
                  	</div>
                
					<div class="col-md-8"></div>
					<div class="col-md-2">
						<select id="post-list-category-product" name="post-list-category"
		                     class="post-list-select form-control" onchange="fn_productChanged();">
		                     <option value="allProduct" <%=product.equals("allProduct") ? "selected" : ""%> selected>전체</option>
		                     <option value="P1" <%=product.equals("P1") ? "selected" : ""%>>식품</option>
		                     <option value="P2" <%=product.equals("P2") ? "selected" : ""%>>위생</option>
		                     <option value="P3" <%=product.equals("P3") ? "selected" : ""%>>장난감</option>
		                     <option value="P4" <%=product.equals("P4") ? "selected" : ""%>>하우스</option>
		                     <option value="P5" <%=product.equals("P5") ? "selected" : ""%>>의류</option>
	                  	</select>
					</div>
				</div>

				<!-- 테이블 넣기 -->
				<div class="row mt-2">
					<table class="table table-bordered" id="sharing-post-table">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>거래장소</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<%if(!list.isEmpty()) {%>
						<%for(SharingPost adminSharing : adminList) {%>
						<tbody>
								<tr>
									<td><span style="font-size: 1em; color: #C8D8A8;"><i class="fas fa-star"></i></span></td>
									<td><a href="<%=request.getContextPath()%>/board/sharingBoardForm?sharingPostNo=<%=adminSharing.getSharingPostNo() %>"><%=adminSharing.getSharingPostTitle() %></a></td>
									<td><%=adminSharing.getSharingPostWriter() %></td>
									<td><%=adminSharing.getSharingPostAddress() %></td>
									<td><%=adminSharing.getSharingPostDate() %></td>
									<td><%=adminSharing.getCount() %></td>
								</tr>
						<%} %>
						<%for(SharingPost sharingPost : list) {%>
								 <tr>
								 <%if(sharingPost.getSharingPostYN().equals("Y")) {%>
								 	<td id="sharingYN">나눔완료</td>
								 <%} else { %>
									<td><%=sharingPost.getSharingPostNo() %></td>
								<%} %>
									<td><a href="<%=request.getContextPath()%>/board/sharingBoardForm?sharingPostNo=<%=sharingPost.getSharingPostNo() %>"><%=sharingPost.getSharingPostTitle() %></a></td>
									<td><%=sharingPost.getSharingPostWriter() %></td>
									<td><%=sharingPost.getSharingPostAddress() %></td>
									<td><%=sharingPost.getSharingPostDate() %></td>
									<td><%=sharingPost.getCount() %></td>
								</tr>
								</tbody>
							<%}%> 
						<%}%>
					</table>
				</div>

				<!-- 글머리 서치 부분 -->
				<!--검색기능 추가 -->
				<div class="row">
               <div class="col-md-12 text-center">
                  <div id="search-container">
                     <select id="searchType" class="btn btn-primary">
                        <option value="sharingPostTitle">제목</option>
                        <option value="sharingPostWriter">작성자</option>
                        <option value="sharingPostContent">내용</option> 
                     </select>
                     <div id="search-sharingPostTitle">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/sharingBoardList">
                           <input type="hidden" name="searchType" value="sharingPostTitle" /> 
                           <input class="form-control" type="text" name="searchKeyword" size="25" placeholder="검색할 제목을 입력하세요" />
                           <button class="btn btn-primary ml-1" type="submit">검색</button>
                        </form>
                     </div>
                      <div id="search-sharingPostWriter">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/sharingBoardList">
                           <input type="hidden" name="searchType" value="sharingPostWriter"/> 
                           <input class="form-control" type="text" name="searchKeyword" size="25" placeholder="검색할 작성자을 입력하세요" />
                           <button class="btn btn-primary ml-1" type="submit">검색</button>
                        </form>
                     </div>
                     <div id="search-sharingPostContent">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/sharingBoardList">
                           <input type="hidden" name="searchType" value="sharingPostContent" /> 
                           <input class="form-control" type="text" name="searchKeyword" size="25" placeholder="검색할 내용을 입력하세요" />
                           <button class="btn btn-primary ml-1" type="submit">검색</button>
                        </form>
                     </div>

                  </div>
               </div>
            </div>
			<%if(loginMember!=null) {%>
                    <%if(loginMember.getAdminYN()=='Y') {%>
				<div class="row">
					<div class="col-md-12 text-right">
						<form action="<%=request.getContextPath() %>/board/sharingBoardInsert?loginMember=<%=loginMember.getId() %>" method="POST">
							<input type="submit" class="btn btn-primary" name="sharing-post-btn-write" id="sharing-post-btn-write" value="공지등록">
						</form>
					</div>
				</div>
			<%} else { %>
				<div class="row">
					<div class="col-md-12 text-right">
						<form action="<%=request.getContextPath() %>/board/sharingBoardInsert?loginMember=<%=loginMember.getId() %>" method="POST">
							<input type="submit" class="btn btn-primary" name="sharing-post-btn-write" id="sharing-post-btn-write" value="글쓰기">
						</form>
					</div>
				</div>
			<%} %>
			<%} %>
				<!-- 페이징 버튼 넣기 -->
				<div class="sharing-post-page">
					<div class="col-md-12 text-center">
						<div class="pagination">
							<%=pageBar %>
						</div>
					</div>
				</div>


			</div>
			<div class='col-md-2' id='aside'></div>
		</div>


	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>