<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.FreePost, java.util.*" %>
<%
	List<FreePost> list = (List)request.getAttribute("list");
 %>
<%@ include file="/views/common/header.jsp" %>

<!-- --------------------------------------상세페이지 수정 화면----------------------------------- -->

<section>

	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2 admin-post-list-nav' id='nav'>
				
			</div>

			<div class='col-md-8' id='section'>
				<form id="free-post-detail-write" name="freePostWriteFrm" action="<%=request.getContextPath() %>/board/freeBoardUpdateEnd?loginMember=<%=loginMember.getId() %>&freePostNo=<%=list.get(0).getFreePostNo() %>" method="POST" enctype="multipart/form-data">
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
                      <div class="col-md-2 mt-1">
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
						<input class="form-control" type="text" id="freePostTitle" name="freePostTitle" required value="<%=list.get(0).getFreePostTitle()%>">
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-2">
						<span id="post-detail-write-writer">작성자</span>
					</div>
					<div class="col-md-10"> 
						<input class="form-control" type="text" id="freePostWriter" name="freePostWriter" readonly="readonly" value="<%=list.get(0).getFreePostWriter()%>">
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-2">
						<span id="post-detail-write-writer">내용</span>
					</div>
					<div class="col-md-10">
						<textarea class="form-control" rows="5" cols="95" name="freePostContent" id="PostContent" required><%=list.get(0).getFreePostContent() %></textarea>
					</div>
				</div>
				<div class="row form-inline mt-2" id="post-detail-write-file">
					<table class="table table-protocol table-bordered">
						<tbody>
						<%if(list.size()>0){ %>
							<%for(int i=0; i<list.size(); i++) { %>
							<tr>
								<th id="freePostTh">기존이미지</th>
								<td id="freePostTd">
									<%=list.get(i).getFreeImgAddress()%>
								</td>	
								<td>	
									<input class="form-control" type="file" name="newfile<%=i%>"/>
								</td>
							</tr>
								<input type="hidden" name="oldfile<%=i %>" value="<%=list.get(i).getFreeImgAddress()%>"/>

								<%} %>
							<%} %>
						</tbody>
					</table>
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
						<input type="submit" value="수정" class="btn btn-primary" id="btn-ok" name="btn-ok">
					</div>
				</div>
				</form>
			</div>
			
			<div class="col-md-2" id="aside">
			
			</div>

		</div>
	</div>

</section>

<%@ include file="/views/common/footer.jsp" %>