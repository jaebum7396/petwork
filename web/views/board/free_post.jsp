<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.petwork.model.vo.FreePost" %>
<%
	List<FreePost> list = (List)request.getAttribute("list");
	int cPage = (int)request.getAttribute("cPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String race_code = (String)request.getAttribute("race_code");
	List<FreePost> adminList = (List)request.getAttribute("adminList");
%>	
<%@ include file="/views/common/header.jsp" %>


<script>
window.onload=function(){
   var freePostTitle=document.querySelector("#search-freePostTitle");
   var freePostWriter=document.querySelector("#search-freePostWriter");
   var freePostContent=document.querySelector("#search-freePostContent");
   
   var searchType=document.querySelector("#searchType"); //셀렉트 태그를 가지고온것임 쿼리셀렉터는 선택자이용해서 가지고오는거얌
   searchType.addEventListener("change",function(){ //change변경됐을때 실행해 그럼 아래 3줄다 없어진다.
	  freePostTitle.style.display="none";
      freePostWriter.style.display="none";
      freePostContent.style.display="none";
      document.querySelector("#search-"+this.value).style.display="inline-block"; //디스벨류는 눌렀을때 그값 
   });
}
</script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<section>
	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2 admin-post-list-nav' id='nav'>
				
			</div>

			<div class='col-md-8 text-center' id='section'>
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
				<%if(race_code==null||race_code.equals("A")) {%>
                     <div class="col-md-3">
                          <label class="free-text-head-select"><span>전체 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="A" checked onclick="fn_checked();"></label>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="D" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="C" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="E" onclick="fn_checked();"></label>
                         </div>
                  <%} else if(race_code.equals("D")){%>
                     <div class="col-md-3">
                          <label class="free-text-head-select"><span>전체 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="A" onclick="fn_checked();"></label>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="D" checked onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="C" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="E" onclick="fn_checked();"></label>
                         </div>
                  <%} else if(race_code.equals("C")){%>
                     <div class="col-md-3">
                          <label class="free-text-head-select"><span>전체 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="A" onclick="fn_checked();"></label>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="D" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="C" checked onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="E" onclick="fn_checked();"></label>
                         </div>
                  <%} else if(race_code.equals("E")){%>
                     <div class="col-md-3">
                          <label class="free-text-head-select"><span>전체 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="A" onclick="fn_checked();"></label>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="D" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="C" onclick="fn_checked();"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="free-write-race" class="free-text-head-radio mt-1 ml-2" value="E" checked onclick="fn_checked();"></label>
                         </div>
                  <%} %>
                   </div>
				<script>
					function fn_checked(){
						var race_code = $('input[name=free-write-race]:checked').val();
						
						/* $('input:radio[name=free-write-race][value='+race_code+']').prop("checked",true); */
						location.href="<%=request.getContextPath()%>/board/freeBoardList?race_code="+race_code;
					}
				</script>
				
				<!-- 테이블 넣기 -->
				<div class="row mt-2">
					<table class="table table-bordered" id="free-post-table">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<%if(!list.isEmpty()) {%>
						<%for(FreePost adminFree : adminList) {%>
							<tbody>
								<tr>
									<td><span style="font-size: 1em; color: #C8D8A8;"><i class="fas fa-star"></i></span></td>
									<td><a href="<%=request.getContextPath()%>/board/freeBoardForm?freePostNo=<%=adminFree.getFreePostNo() %>"><%=adminFree.getFreePostTitle() %></a></td>
									<td><%=adminFree.getFreePostWriter() %></td>
									<td><%=adminFree.getFreePostDate() %></td>
									<td><%=adminFree.getCount() %></td>
								</tr>
						<%} %>
						<%for(FreePost freePost : list) {%>
							
							 <tr>
								<td><%=freePost.getFreePostNo() %></td>
								<td><a href="<%=request.getContextPath()%>/board/freeBoardForm?freePostNo=<%=freePost.getFreePostNo() %>"><%=freePost.getFreePostTitle() %></a></td>
								<td><%=freePost.getFreePostWriter() %></td>
								<td><%=freePost.getFreePostDate() %></td>
								<td><%=freePost.getCount() %></td>
							</tr>
							</tbody>
							
						<%} %>
						<%} %>
					</table>
				</div>
				
				<div class="row">
               <div class="col-md-12 text-center">
                  <div id="search-container">
                     <select id="searchType" class="btn btn-primary">
                        <option value="freePostTitle">제목</option>
                        <option value="freePostWriter">작성자</option>
                        <option value="freePostContent">내용</option> 
                     </select>
                     <div id="search-freePostTitle">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/freePostFinder">
                           <input type="hidden" name="searchType" value="freePostTitle" /> 
                           <input class="form-control" type="text" name="searchKeyword" size="25" placeholder="검색할 제목을 입력하세요" />
                           <button class="btn btn-primary ml-1" type="submit">검색</button>
                        </form>
                     </div>
                      <div id="search-freePostWriter">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/freePostFinder">
                           <input type="hidden" name="searchType" value="freePostWriter" /> 
                           <input class="form-control" type="text" name="searchKeyword" size="25" placeholder="검색할 작성자을 입력하세요" />
                           <button class="btn btn-primary ml-1" type="submit">검색</button>
                        </form>
                     </div>
                     <div id="search-freePostContent">
                        <form class="form-inline" action="<%=request.getContextPath()%>/board/freePostFinder">
                           <input type="hidden" name="searchType" value="freePostContent" /> 
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
						<form action="<%=request.getContextPath() %>/board/freeBoardInsert?loginMember=<%=loginMember.getId() %>" method="POST">
							<input type="submit" class="btn btn-primary" name="free-post-btn-write" id="free-post-btn-write" value="공지등록">
						</form>
					</div>
				</div>
			<%} else { %>
				<div class="row">
					<div class="col-md-12 text-right">
						<form action="<%=request.getContextPath() %>/board/freeBoardInsert?loginMember=<%=loginMember.getId() %>" method="POST">
							<input type="submit" class="btn btn-primary" name="free-post-btn-write" id="free-post-btn-write" value="글쓰기">
						</form>
					</div>
				</div>
			<%} %>	
			<%} %>
				<!-- 페이징 버튼 넣기 -->
				<div class="free-post-page">
					<div class="col-md-12 text-center">
						<div class="pagination">
							<%=pageBar %>
						</div>
					</div>
				</div>


			</div>
			<div class="col-md-2" id="aside">
			
			</div>
		</div>


	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>