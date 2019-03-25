<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.petwork.model.vo.FindPost"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/missingPet/missing_pet_post_view.css">
<%
	FindPost findPost = (FindPost) request.getAttribute("findPost");
%>
<script>
	function fn_deleteFindPost(no, img){
		if(!confirm("게시글을 삭제하시겠습니까?"))
		{
			return;
		}
	
		var url="<%=request.getContextPath()%>/missingPet/deleteFindPost";
		//한글 파일명이 있을 경우 인코딩 처리
		img = encodeURIComponent(img);
		location.href = url + "?no=" + no + "&img=" + img + "&adminMode=true";
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
		
		<div class='col-md-10 post-view-tile my-3 m-0' id='section'>
			<div class='row mt-4'>
				<div class="col-md-12 post-view-header">
					우리
					<%=findPost.getPetName()%>
					찾아주세요 <span class="post-view-header-id">[ <%=findPost.getMemberId()%>
						]
					</span> <span class="post-view-header-date">[ <%=findPost.getFindPostDate()%>
						]
					</span>
				</div>
			</div>
			<div class="row mb-2">
				<div class="col-md-12 post-view-header-tel">
					<strong> <%=findPost.getMemberPhone().substring(0, 3)%>-<%=findPost.getMemberPhone().substring(3, 7)%>-<%=findPost.getMemberPhone().substring(7, 11)%>
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 post-view-img">
					<img id="petImg"
						src="<%=request.getContextPath()%>/views/upload/missingPet/<%=findPost.getFindPostImgAddress()%>"
						alt="Image">
				</div>
			</div>
			<div class="row post-view-info">
				<div class=" col-md-12">
					<div class="place">
						<strong>잃어버린 장소</strong> <span><%=findPost.getFindPostMissingAddress()%></span>
					</div>
					<div class="date">
						<strong>잃어버린 날짜</strong> <span><%=findPost.getFindPostMissingDate()%></span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts-top col-md-12">
					<div>
						<strong>품종</strong> <span><%=findPost.getAnimalName()%></span>
					</div>
					<div>
						<strong>인식번호</strong> <span><%=findPost.getPetIdentifyNo()%></span>
					</div>
					<div>
						<strong>성별</strong> <span><%=findPost.getPetGender() == 'M' ? "수컷" : "암컷"%></span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts col-md-12">
					<strong>글 제목</strong> <span><%=findPost.getFindPostTitle()%></span>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts col-md-12">
					<strong>상세 설명</strong>
					<p><%=findPost.getFindPostContent()%></p>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts col-md-12">
					<strong>사례금</strong> <span> <%if(findPost.getFindPostReward() == 'N') {%>
						없음 <%}else if(findPost.getFindPostReward() == 'C'){%> 찾은 후 합의 <%}else{ %>
						<%=findPost.getFindPostSum()%>만원 <%} %>
					</span>
				</div>
			</div>
			<div class="row post-view-button py-2 float-right">
				<div class="col-md-12">
					<input type="button"
						class="btn btn-primary"
						onclick="fn_deleteFindPost('<%=findPost.getFindPostNo()%>', '<%=findPost.getFindPostImgAddress()%>');"
						value="삭제" />
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>