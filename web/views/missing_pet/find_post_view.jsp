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
		location.href = url + "?no=" + no + "&img=" + img;
	}
	function fn_updateFindPost(){
		location.href="<%=request.getContextPath()%>/missingPet/updateFindPost?no=<%=findPost.getFindPostNo()%>";
	}
	function fn_finishFindPost(){
		if(!confirm("아이를 찾으셨나요?"))
		{
			return;
		}
		location.href="<%=request.getContextPath()%>/missingPet/finishFindPost?no=<%=findPost.getFindPostNo()%>&identifyNo=<%=findPost.getPetIdentifyNo()%>";
	}
</script>
<section>
	<div class="container-fluid">
		<div class='row'>
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8 post-view-tile my-3' id='section'>
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
						<strong>
						<%if(findPost.getFindPostYn()=='N'){ %>
						<%=findPost.getMemberPhone().substring(0, 3)%>-<%=findPost.getMemberPhone().substring(3, 7)%>-<%=findPost.getMemberPhone().substring(7, 11)%> 
						<%}else{ %>
						***-****-****
						<%} %>
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
			
				<% if (loginMember != null && loginMember.getId().equals(findPost.getMemberId())) { %>
				<div class="row post-view-button py-2">
					<div class="col-md-8 float-left">
					<%if(findPost.getFindPostYn() == 'N'){ %>
						<input type="button" class="btn btn-primary"
							onclick="fn_updateFindPost()" value="수정" />
					<%} %>
						<input type="button" class="btn btn-primary"
							onclick="fn_deleteFindPost('<%=findPost.getFindPostNo()%>', '<%=findPost.getFindPostImgAddress()%>');" value="삭제" />
					</div>
					<%if(findPost.getFindPostYn() == 'N'){ %>			
					<div class="col-md-4 float-right">
						<input type="button" id="btnConfirm"
							class="btn btn-primary float-right" onclick="fn_finishFindPost()" value="찾음" />
					</div>
					<%} %>
				</div>
				<% } %>
			</div>
			
			<div class='col-md-2' id='aside'></div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>