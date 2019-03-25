<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/missingPet/popper.min.js"></script>
<%@ page
	import="java.util.*, com.petwork.model.vo.City, com.petwork.model.vo.FindPost"%>
<%
	List<FindPost> findPostList = (List) request.getAttribute("findPostList");
	List<City> cityList = (List) request.getAttribute("cityList");
	String pageBar = (String) request.getAttribute("pageBar");
	int cPage = (int) request.getAttribute("cPage");
	String category = (String) request.getAttribute("category");
	String cityCode = (String) request.getAttribute("cityCode");
	String searchCategory = (String) request.getAttribute("searchCategory");
	String searchWord = (String) request.getAttribute("searchWord");
%>
<%@ include file="/views/common/header.jsp"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/missingPet/missing_pet_post_list.css">

<script>
function fn_cityChanged(){
	var cityCode = $("#post-list-city option:selected").val();
	location.href= "<%=request.getContextPath()%>/missingPet/findPostList?cityCode=" + cityCode;
}

function fn_categoryChanged(){
	var category = $("#post-list-category option:selected").val();
	location.href= "<%=request.getContextPath()%>/missingPet/findPostList?category=" + category;
	}
</script>
<section>
	<div class="container-fluid">
		<div class='row'>
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8 mt-2' id='section'>
				<h3>우리 아이 찾아주세요</h3>
				<div class="row">
					<div class="col-md-6">
						<select id="post-list-category" name="post-list-category"
							class="post-list-select" onchange="fn_categoryChanged()">
							<option value="allCategory" <%=category.equals("allCategory") ? "selected" : ""%>>전체</option>
							<option value="D" <%=category.equals("D") ? "selected" : ""%>>강아지</option>
							<option value="C" <%=category.equals("C") ? "selected" : ""%>>고양이</option>
							<option value="E" <%=category.equals("E") ? "selected" : ""%>>기타</option>
						</select>
						<%if(loginDoctor==null){ %>
						<button type="button" class="btn btn-primary ml-2"
							onclick="location.href='<%=request.getContextPath()%>/missingPet/findPostReg'">등록하기</button>
						<%} %>
					</div>
					<div class="col-md-6">
						<div class="float-right">
							<select id="post-list-city" name="post-list-city"
								class="post-list-select" onchange="fn_cityChanged()">
								<option value="allCity" <%=cityCode.equals("allCity")? "selected" : "" %>>전체지역(시)</option>
								<%
									for (City c : cityList) {
								%>
								<option value="<%=c.getCityCode()%>" <%=cityCode.equals(c.getCityCode())? "selected" : "" %>><%=c.getCityName()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
				</div>
				<div class='row post-list-card-row'>
					<%
						if (findPostList.isEmpty()) {
					%>
					<div class="col-md-12" id="post-list-card-notice">
						<span>등록된 게시글이 없습니다.</span>
					</div>
					<%
						} else {
					%>
					<%
						for (FindPost f : findPostList) {
					%>
					<div class="col-md-4 mt-3 p-1">
						<div class="card post-list-card">
							<div>
								<a
									href="<%=request.getContextPath()%>
								/missingPet/findPostView?no=<%=f.getFindPostNo()%>">
									<!-- 찾지못한 경우 --> <img class="card-img-top" alt="Image"
									src="
									<%if (f.getFindPostYn() == 'N') {%> 
										<%=request.getContextPath()%>/views/upload/missingPet/<%=f.getFindPostImgAddress()%>
									<%} else {%>
										<%=request.getContextPath()%>/img/missingPet/pet_found_img.jpg		
									<%}%>
									">
								</a>
							</div>
							<%
								String addr = "";
								String[] array = f.getFindPostMissingAddress().split(" ");
										for (int i = 0; i < array.length; i++) {
											addr = array[0] + " " + array[1];
										}
							%>
							<div class="card-block p-3 post-list-card-text">
								<div>
									<strong>지&nbsp;&nbsp;&nbsp;역</strong> <span><%=addr%>
									</span>
								</div>
								<div>
									<strong>품&nbsp;&nbsp;&nbsp;종</strong> <span><%=f.getAnimalName()%></span>
								</div>
								<div>
									<strong>사례금</strong> <span> <%
									 	if (f.getFindPostReward() == 'N') {
									 %> 없음 <%
									 	} else if (f.getFindPostReward() == 'C') {
									 %>찾은 후 합의<%
									 	} else {
									 %> <%=f.getFindPostSum()%>만원 <%
									 	}
									 %>
									</span> 
								</div>
								<div><span class="float-right my-0 pt-2" id="post-list-card-date"><%=f.getFindPostMissingDate()%></span></div>
							</div>
						</div>
					</div>
					<%
						}
						}
					%>
				</div>
				<div class='row mt-4'>
					<div class="col-md-12 post-list-bottom">
						<form class="form-inline"
							action="<%=request.getContextPath()%>/missingPet/findPostList"
							method="POST">
							<select class="form-control col-md-2 mr-3" name="searchCategory">
								<option value="pet_identify_no" <%=searchCategory.equals("pet_identify_no")? "selected" : "" %>>인식번호</option>
								<option value="member_id" <%=searchCategory.equals("member_id")? "selected" : "" %>>아이디</option>
								<option value="member_phone" <%=searchCategory.equals("member_phone")? "selected" : "" %>>전화번호</option>
							</select> <input type="text" class="form-control col-md-7 mr-3"
								placeholder="검색어를 입력하세요" name="searchWord" value="<%=searchWord%>" onkeyup="this.value=this.value.replace(/[^A-Za-z0-9]/g,'')"> <input
								type="hidden" value="true" name="searchFlag"> 
								
								<input class="btn btn-primary col-md-2" type="submit" value="검색">
								
						</form>
					</div>
				</div>
				<div class="row text-center mt-3 mb-4">
					<div class="pagination" id="missing-list-pageBar">
						<%=pageBar%>
					</div>
				</div>
			</div>
			<div class='col-md-2' id='aside'></div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>