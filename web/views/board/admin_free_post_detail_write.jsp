<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>

<!-- --------------------------------------상세페이지 작성 화면----------------------------------- -->

<section>

	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2' id='nav'>
		    <div class="nav-side-menu">
		        <div class="brand">PETWORK</div>
		        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
		        <div class="menu-list">
		            <ul id="menu-content" class="menu-content collapse out">
		                <li>
		                    <a href="<%=request.getContextPath()%>/indexcontroller"> HOME </a>
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
		                    <li><a href="<%=request.getContextPath()%>/qna/qnaList">QnA</a></li>
		                    <li><a href="<%=request.getContextPath()%>/faq/faqList">FAQ</a></li>
		                </ul>
		            </ul>
		        </div>
		    </div>
		</div>

			<div class='col-md-10' id='section'>
				<form id="free-post-detail-write" name="freePostWriteFrm" action="<%=request.getContextPath() %>/board/freeBoardInsertEnd?loginMember=<%=loginMember.getId() %>" method="POST" enctype="multipart/form-data">
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
                      <div class="col-md-2">
                          <span class="post-detail-write-title-radio" id="post-detail-write-title-radio">글머리</span>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="RaceCode" class="free-text-head-radio mt-1 ml-1" value="D" checked></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="RaceCode" class="free-text-head-radio mt-1 ml-1" value="C"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="RaceCode" class="free-text-head-radio mt-1 ml-1" value="E"></label>
                         </div>
                         <div class="col-md-1"></div>
                   </div>
				
				<!-- 작성 부분 -->
				<div class="row form-inline">
					<div class="col-md-2">
						<span id="post-detail-write-title">제목</span>
					</div>
					<div class="col-md-10"> 
						<input class="form-control" type="text" id="freePostTitle" name="freePostTitle" required>
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-2">
						<span id="post-detail-write-writer">작성자</span>
					</div>
					<div class="col-md-10"> 
						<input class="form-control" type="text" id="freePostWriter" name="freePostWriter" readonly="readonly" value="<%=loginMember.getId()%>">
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-2">
						<span id="post-detail-write-writer">내용</span>
					</div>
					<div class="col-md-10">
						<textarea  class="form-control" style="resize: none;" rows="5" cols="95" name="postContent" id="PostContent" placeholder="상세설명을 입력해주세요" required></textarea>
					</div>
				</div>
				<div class="row form-inline mt-2" id="post-detail-write-file">
					<div class="col-md-2">
						<span id="post-detail-write-add-img-text">사진첨부</span>
					</div>
					<div class="col-md-10" id="post-detail-write-add-img">
						<input class="form-control" type="file" id="postImgAddress0" name="postImgAddress1" required>
					</div>
					<div class="col-md-2">
						<span id="post-detail-write-add-img-text2">사진첨부</span>
					</div>
					<div class="col-md-10" id="post-detail-write-add-img">
						<input class="form-control" type="file" id="postImgAddress1" name="postImgAddress2">
					</div>
					<div class="col-md-2">
						<span id="post-detail-write-add-img-text3">사진첨부</span>
					</div>
					<div class="col-md-10" id="post-detail-write-add-img">
						<input class="form-control" type="file" id="postImgAddress2" name="postImgAddress3">
					</div>
					
				</div>
				<script>

				$(function(){
					$('#postImgAddress0').on("change",function(){
						$('#post-detail-write-add-img-text2').css('display','inline');
						$('#postImgAddress1').css('display','inline');
					});
				});
				$(function(){
					$('#postImgAddress1').on("change",function(){
						$('#post-detail-write-add-img-text3').css('display','inline');
						$('#postImgAddress2').css('display','inline');
					});
				});
			    </script>
				<div class="row">
					<div class="col-md-12 mb-2" id="free-post-detail-write-btn">
						<input type="reset" value="취소" class="btn btn-primary" id="btn-cancel" name="btn-cancel"> 
						<input type="submit" value="등록" class="btn btn-primary" id="btn-ok" name="btn-ok">
					</div>
				</div>
				</form>
			</div>

		</div>
	</div>

</section>

<%@ include file="/views/common/footer.jsp" %>