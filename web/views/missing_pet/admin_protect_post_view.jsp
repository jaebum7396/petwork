<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.petwork.model.vo.ProtectPost"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/missingPet/missing_pet_post_view.css">
<%
	ProtectPost protectPost = (ProtectPost) request.getAttribute("protectPost");
%>
<script>
	function fn_deleteProtectPost(no, img){
		if(!confirm("게시글을 삭제하시겠습니까?"))
		{
			return;
		}
	
		var url="<%=request.getContextPath()%>/missingPet/deleteProtectPost";
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
					아이를 보호중입니다 <span class="post-view-header-id">[ <%=protectPost.getMemberId()%>
						]
					</span> <span class="post-view-header-date">[ <%=protectPost.getProtectPostDate()%>
						]
					</span>
				</div>
			</div>
			<div class="row mb-2">
				<div class="col-md-12 post-view-header-tel">
					<strong> <%=protectPost.getMemberPhone().substring(0, 3)%>-<%=protectPost.getMemberPhone().substring(3, 7)%>-<%=protectPost.getMemberPhone().substring(7, 11)%>
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 post-view-img">
					<img id="petImg"
						src="<%=request.getContextPath()%>/views/upload/missingPet/<%=protectPost.getProtectPostImgAddress()%>"
						alt="Image">
				</div>
			</div>
			<div class="row post-view-info">
				<div class=" col-md-12">
					<div class="place">
						<strong>찾은 장소</strong> <span><%=protectPost.getProtectPostFindAddress()%></span>
					</div>
					<div class="date">
						<strong>찾은 날짜</strong> <span><%=protectPost.getProtectPostFindDate()%></span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts-top col-md-12">
					<div>
						<strong>품종</strong> <span><%=protectPost.getAnimalName()%></span>
					</div>
					<div>
						<strong>인식번호</strong> <span><%=protectPost.getProtectPetIdentifyNo() != null ? protectPost.getProtectPetIdentifyNo() : "미기입"%></span>
					</div>
					<div>
						<strong>성별</strong> <span><%=protectPost.getProtectPostPetGender() == 'M' ? "수컷"
					: protectPost.getProtectPostPetGender() == 'F' ? "암컷" : "모름"%></span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts col-md-12">
					<strong>글 제목</strong> <span><%=protectPost.getProtectPostTitle()%></span>
				</div>
			</div>

			<div class="row">
				<div class="post-view-posts col-md-12">
					<strong>상세 설명</strong>
					<p><%=protectPost.getProtectPostContent()%></p>
				</div>
			</div>

			<div class="row post-view-button py-2 float-right">
				<div class="col-md-12">
					<input type="button" class="btn btn-primary"
						onclick="fn_deleteProtectPost('<%=protectPost.getProtectPostNo()%>', '<%=protectPost.getProtectPostImgAddress()%>');"
						value="삭제" />
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>