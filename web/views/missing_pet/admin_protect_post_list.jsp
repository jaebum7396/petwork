<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/missingPet/popper.min.js"></script>
<%@ page
	import="java.util.*, com.petwork.model.vo.City, com.petwork.model.vo.ProtectPost"%>
<%
	List<ProtectPost> protectPostList = (List) request.getAttribute("protectPostList");
	List<City> cityList = (List) request.getAttribute("cityList");
	String pageBar = (String) request.getAttribute("pageBar");
	int cPage = (int) request.getAttribute("cPage");
	String category = (String) request.getAttribute("category");
	String cityCode = (String) request.getAttribute("cityCode");
	String searchCategory = (String) request.getAttribute("searchCategory");
	String searchWord = (String) request.getAttribute("searchWord");
%>
<%@ include file="/views/common/header.jsp"%>
<style>
	.nav-side-menu {
		height: 145%;
	}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/missingPet/missing_pet_post_list.css">

<script>
function fn_cityChanged(){
	var cityCode = $("#post-list-city option:selected").val();
	location.href= "<%=request.getContextPath()%>/missingPet/protectPostList?cityCode=" + cityCode + "&adminMode=true";
}

function fn_categoryChanged(){
	var category = $("#post-list-category option:selected").val();
	location.href= "<%=request.getContextPath()%>/missingPet/protectPostList?category=" + category + "&adminMode=true";
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
		<div class='col-md-10 mt-2' id='section'>
			<h3>보호중입니다 [관리자]</h3>
			<div class="row">
				<div class="col-md-6">
					<select id="post-list-category" name="post-list-category"
						class="post-list-select" onchange="fn_categoryChanged()">
						<option value="allCategory"
							<%=category.equals("allCategory") ? "selected" : ""%>>전체</option>
						<option value="D" <%=category.equals("D") ? "selected" : ""%>>강아지</option>
						<option value="C" <%=category.equals("C") ? "selected" : ""%>>고양이</option>
						<option value="E" <%=category.equals("E") ? "selected" : ""%>>기타</option>
					</select>
				</div>
				<div class="col-md-6">
					<div class="float-right">
						<select id="post-list-city" name="post-list-city"
							class="post-list-select" onchange="fn_cityChanged()">
							<option value="allCity"
								<%=cityCode.equals("allCity")? "selected" : "" %>>전체지역(시)</option>
							<%
									for (City c : cityList) {
								%>
							<option value="<%=c.getCityCode()%>"
								<%=cityCode.equals(c.getCityCode())? "selected" : "" %>><%=c.getCityName()%></option>
							<%
									}
								%>
						</select>
					</div>
				</div>
			</div>
			<div class='row post-list-card-row'>
				<%
						if (protectPostList.isEmpty()) {
					%>
				<div class="col-md-12" id="post-list-card-notice">
					<span>등록된 게시글이 없습니다.</span>
				</div>
				<%
						} else {
					%>
				<%
						for (ProtectPost p : protectPostList) {
					%>
				<div class="col-md-4 mt-3 p-1">
					<div class="card protect-post-list-card">
						<div>
							<a
								href="<%=request.getContextPath()%>
								/missingPet/protectPostView?no=<%=p.getProtectPostNo()%>&adminMode=true">
								<!-- 찾지못한 경우 --> <img class="card-img-top" alt="Image"
								src="
									<%if (p.getProtectPostYn() == 'N') {%> 
										<%=request.getContextPath()%>/views/upload/missingPet/<%=p.getProtectPostImgAddress()%>
									<%} else {%>
										<%=request.getContextPath()%>/img/missingPet/pet_found_img.jpg		
									<%}%>
									">
							</a>
						</div>
						<%
								String addr = "";
								String[] array = p.getProtectPostFindAddress().split(" ");
										for (int i = 0; i < array.length; i++) {
											addr = array[0] + " " + array[1];
										}
							%>
						<div class="card-block p-2 m-1 post-list-card-text">
							<div>
								<strong>지&nbsp;&nbsp;&nbsp;역</strong> <span class="m-1">
									<%=addr%>
								</span>
							</div>
							<div>
								<strong>품&nbsp;&nbsp;&nbsp;종</strong> <span class="m-1"><%=p.getAnimalName()%></span>
							</div>
							<div>
								<span class="float-right my-0 pt-2" id="post-list-card-date"><%=p.getProtectPostFindDate()%></span>
							</div>
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
						action="<%=request.getContextPath()%>/missingPet/protectPostList?adminMode=true"
						method="POST">
						<select class="form-control col-md-2 mr-3" name="searchCategory">
							<option value="pet_identify_no"
								<%=searchCategory.equals("pet_identify_no")? "selected" : "" %>>인식번호</option>
							<option value="member_id"
								<%=searchCategory.equals("member_id")? "selected" : "" %>>아이디</option>
							<option value="member_phone"
								<%=searchCategory.equals("member_phone")? "selected" : "" %>>전화번호</option>
						</select> <input type="text" class="form-control col-md-7 mr-3"
							placeholder="검색어를 입력하세요" name="searchWord"
							value="<%=searchWord%>"
							onkeyup="this.value=this.value.replace(/[^A-Za-z0-9]/g,'')">
						<input type="hidden" value="true" name="searchFlag"> <input
							class="btn btn-primary col-md-2" type="submit" value="검색">

					</form>
				</div>
			</div>
			<div class="row text-center mt-3 mb-4">
					<div class="pagination" id="missing-list-pageBar">
					<%=pageBar%>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>