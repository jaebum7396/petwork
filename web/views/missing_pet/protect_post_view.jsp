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
		location.href = url + "?no=" + no + "&img=" + img;
	}
	function fn_updateProtectPost(){
		location.href="<%=request.getContextPath()%>/missingPet/updateProtectPost?no=<%=protectPost.getProtectPostNo()%>&raceCode=<%=protectPost.getRaceCode()%>";
	}
	function fn_giveMemberIdSearch(memberId){
		var id = $("#inputMemberId").val();
		var inputId = document.getElementById('inputMemberId');
		var btnConfirm = document.getElementById('giveConfirm');
		
		$('span[id=giveNotice]').text("");
		if(id.length==0)
		{
			$('input[name=inputMemberId]').focus();
			$('span[id=giveNotice]').text("");
			btnConfirm.disabled = true;
			return;
		}
		if(id==memberId){
			$('span[id=giveNotice]').text("본인의 ID는 입력할 수 없습니다");
			btnConfirm.disabled = true;
			return;
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/missingPet/searchIdProtectPost",
			type:"post",
			data: {id:id},
			success:function(data){
				if(data=="true")
				{
					btnConfirm.disabled = false;
					inputId.disabled = true;
					$('span[id=giveNotice]').text("");			
					$('#giveMemberId').val(id);
				}else{
					$('span[id=giveNotice]').text("입력한 회원 ID를 찾을 수 없습니다");
					btnConfirm.disabled = true;
				}
			}
		});
	}
	function fn_givePetModalClose(){
		var inputId = document.getElementById('inputMemberId');
		var btnConfirm = document.getElementById('giveConfirm');
		
		btnConfirm.disabled = true;
		inputId.disabled = false;
	}
	
	function fn_finishProtectPost(){
		var id = $("#giveMemberId").val();

		location.href="<%=request.getContextPath()%>/missingPet/finishProtectPost?no=<%=protectPost.getProtectPostNo()%>&giveId=" + id;
	}
</script>
<section>
	<div class="container-fluid">
		<div class='row'>
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8 post-view-tile my-3' id='section'>
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
						<strong> 
						<%if(protectPost.getProtectPostYn()=='N'){ %>
						<%=protectPost.getMemberPhone().substring(0, 3)%>-<%=protectPost.getMemberPhone().substring(3, 7)%>-<%=protectPost.getMemberPhone().substring(7, 11)%>
						<%}else{ %>
						***-****-****
						<%} %>
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
							<strong>인식번호</strong> <span><%=protectPost.getProtectPetIdentifyNo()!=null? protectPost.getProtectPetIdentifyNo() : "미기입"%></span>
						</div>
						<div>
							<strong>성별</strong> <span><%=protectPost.getProtectPostPetGender() == 'M' ? "수컷" : protectPost.getProtectPostPetGender() == 'F'? "암컷" : "모름"%></span>
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
			
				<% if (loginMember != null && loginMember.getId().equals(protectPost.getMemberId())) { %>
				<div class="row post-view-button py-2">
					<div class="col-md-8 float-left">
					<%if(protectPost.getProtectPostYn() == 'N'){ %>
						<input type="button" class="btn btn-primary" onclick="fn_updateProtectPost()" value="수정" /> 
					<%} %>
							<input type="button"
							class="btn btn-primary"
							onclick="fn_deleteProtectPost('<%=protectPost.getProtectPostNo()%>', '<%=protectPost.getProtectPostImgAddress()%>');"
							value="삭제" />
					</div>
					<%if(protectPost.getProtectPostYn() == 'N'){ %>		
					<div class="col-md-4 float-right">
						<input type="button" id="btnGivePet"
							class="btn btn-primary float-right" data-toggle="modal"
							data-target="#givePetModal" value="찾아줌" />
					</div>
					<%} %>
				</div>
				<% } %>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="givePetModal" tabindex="-1" role="dialog"
				aria-labelledby="givePetModalLabel" aria-hidden="true" data-backdrop="false">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<span class="modal-title" id="givePetModalLabel">주인을 찾아주셨나요?</span>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close" onclick="fn_givePetModalClose()">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<span class="small">아이를 돌려준 상대방의 ID를 검색해주세요</span>
							<input type="text" class="form-control text-center d-inline" 
							name="inputMemberId" id="inputMemberId" placeholder="ID를 입력해주세요">
							<input type="button" class="btn btn-secondary ml-2" value="검색" onclick="fn_giveMemberIdSearch('<%=protectPost.getMemberId() %>');"
							id="giveMemberIdSearch" name="giveMemberIdSearch"><br/>
							<span class="small give-notice mr-2" id="giveNotice"></span>	
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" onclick="fn_givePetModalClose()">닫기</button>
							<button type="button" class="btn btn-primary" name="giveConfirm" id="giveConfirm" onclick="fn_finishProtectPost()" disabled>확인</button>
							<input type="hidden" name="giveMemberId" id="giveMemberId">
						</div>
					</div>
				</div>
			</div>
			
			<div class='col-md-2' id='aside'></div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>