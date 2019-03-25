<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<section>

	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2 admin-post-list-nav' id='nav'></div>

			<div class='col-md-8' id='section'>

				<form id="free-post-detail-write" name="freePostWriteFrm" action="<%=request.getContextPath() %>/board/freeBoardInsertEnd?loginMember=<%=loginMember.getId() %>" method="POST" enctype="multipart/form-data">
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
                      <div class="col-md-2">
                          <span class="post-detail-write-title-radio" id="post-detail-write-title-radio">글머리</span>
                      </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>강아지 </span><input type="radio" name="RaceCode" class="free-text-head-radio" value="D" checked></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>고양이 </span><input type="radio" name="RaceCode" class="free-text-head-radio" value="C"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="free-text-head-select"><span>기타 </span><input type="radio" name="RaceCode" class="free-text-head-radio" value="E"></label>
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
				<div class="row form-inline">
					<div class="col-md-2">
						<span id="post-detail-write-writer">작성자</span>
					</div>
					<div class="col-md-10"> 
						<input class="form-control" type="text" id="freePostWriter" name="freePostWriter" readonly="readonly" value="<%=loginMember.getId()%>">
					</div>
				</div>
				<div class="row form-inline">
					<div class="col-md-2">
						<span id="post-detail-write-writer">내용</span>
					</div>
					<div class="col-md-10">
						<textarea class="form-control" rows="5" cols="95" name="postContent" id="PostContent" placeholder="상세설명을 입력해주세요" required></textarea>
					</div>
				</div>
				<div class="row form-inline" id="post-detail-write-file">
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
						<input type="reset" value="취소" class="btn btn-primary" id="user-btn-cancel" name="btn-cancel"> 
						<input type="submit" value="등록" class="btn btn-primary" id="btn-ok" name="btn-ok">
					</div>
				</div>
				</form>
			</div>
			<div class='col-md-2' id='aside'></div>


		</div>
	</div>

</section>

<%@ include file="/views/common/footer.jsp"%>