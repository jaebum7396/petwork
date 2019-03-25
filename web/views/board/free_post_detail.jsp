<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.FreePost, com.petwork.model.vo.FreeComment, java.util.*"  %>
<%
	List<FreePost> list = (List)request.getAttribute("list");
	List<FreeComment> commentList=(List)request.getAttribute("comments");
%>
<%@ include file="/views/common/header.jsp" %>

<section>
	<div class="container-fluid">
		<div class="row" id="contents">
			<div class='col-md-2' id='nav'>
				
			</div>

			<div class='col-md-8' id='section'>
				<!-- 관리자 자유게시판 상세페이지 -->
				<div class="row">
					<div class="col-md-12" id="post-detail-date">
						<span id="date"><%=list.get(0).getFreePostDate() %></span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<table class="table table-protocol table-bordered" id="post-detail-tbl">
							<tbody>
								<tr>
									<th>작성자</th>
									<td><span id="member-id"><%=list.get(0).getFreePostWriter() %></span></td>
								</tr>

								<tr>
									<th>제목</th>
									<td><span id="free-post-detail-write-title-input"><%=list.get(0).getFreePostTitle() %></span></td>
								</tr>

								<tr>
									<th>내용</th>
									<td>
										<span>
										<%if(!list.isEmpty()) {%>
			                                 <%for(FreePost freePost : list) {%>
			                                 <%if(freePost.getFreePostWriter().contains("admin")) {%>
			                                 	<img id="post-detail-img-admin"   src="<%=request.getContextPath() %>/views/upload/board/<%=freePost.getFreeImgAddress() %>">
			                                 <%} else { %>
			                                    <img id="post-detail-write-img"   src="<%=request.getContextPath() %>/views/upload/board/<%=freePost.getFreeImgAddress() %>">
			                                 <%}
			                              	}
			                              } %>
										</span>
										<textarea id="post-data-role-body" class="form-control mt-2" rows="5" cols="95" readonly><%=list.get(0).getFreePostContent() %></textarea>
									</td>
								</tr>
								
							</tbody>
						</table>
						
					</div>
				</div>
				<%if(loginMember!=null) {%>
				<%if(loginMember.getAdminYN()=='Y'||loginMember.getId().equals(list.get(0).getFreePostWriter())) {%>
				<div class="row mt-2">
					<div class="col-md-8"></div>
					<div class="col-md-2">
						<div id="post-detail-update">
							<input class="btn btn-primary" id="user-update" type="submit" value="수정하기" onclick="freePostUpdate();">
						</div>
					</div>
					<div class="col-md-2">
						<input class="btn btn-primary" id="delete" type="button" value="삭제하기" onclick="fn_deleteFreePost();">
					</div>
				</div>
				<%} %>
				
				<form action="<%=request.getContextPath()%>/board/freeBoardDelete?loginMember=<%=loginMember.getId() %>" method="post" name="deleteFrm">
					<input type="hidden" name="freePostNo" value="<%=list.get(0).getFreePostNo() %>"/>
					<%for(int i=0; i<list.size(); i++){ %>
					<input type="hidden" name="freePostImg<%=i %>" value="<%=list.get(i).getFreeImgAddress() %>"/>	
					<%} %>
				</form>
				<%} %>
				<hr />
				<script>
					function freePostUpdate(){
						location.href="<%=request.getContextPath()%>/board/freeBoardUpdate?freePostNo=<%=list.get(0).getFreePostNo()%>";
					};
					
					function fn_deleteFreePost(){
						if(!confirm("게시글을 삭제하시겠습니까?")){
							return;
						}
						$('[name=deleteFrm]').submit();
					};
										
				</script>
				
				<!-- 댓글 달기 -->
				<%if(loginMember!=null) {%>
				<div class="row">
					<div class="col-md-12 comment-editor">
						<form class="form-inline mb-2" id="post-comment-frm" action="<%=request.getContextPath()%>/board/freeCommentInsert" name="postCommentFrm" method="post">
							<!-- 게시판 번호 -->
							<input type="hidden" name="freePostNo" value="<%=list.get(0).getFreePostNo()%>"/>
							<!-- 게시판 댓글작성자 -->
							<input type="hidden" name="freeCommentWriter" value="<%=loginMember.getId()%>"/>
							<!-- 댓글 레벨 -->
							<input type="hidden" name="freeCommentLevel" value="1"/>
							<!-- 댓글 참조번호 -->
							<input type="hidden" name="freeCommentRef" value="0"/>
							<label for="postComment">Comment&nbsp;&nbsp;</label>
							<input class="form-control" type="text" placeholder="Comment" id="userPostComment" name="freeCommentContent"/>&nbsp;
							<button type="submit" class="btn btn-primary" id="btn-insert">댓글등록</button>
						</form>
					</div>
				</div>
				<%} %>
				
				<hr/>

				<!-- 댓글 달았을 때 나오는 폼 -->
				<table class="tbl-comment">
				<%if(commentList!=null){ 
					for(FreeComment fc : commentList){
						if(fc.getFreeCommentLevel()==1){
				%>
		         <tr>
		            <td>
		               <sub class="comment-writer"><%=fc.getFreeCommentWriter()%></sub>
		               <sub class="comment-date"><%=fc.getFreeCommentDate()%></sub>
		               <br/>
		               <span id="commentContent"><%=fc.getFreeCommentContent() %></span>
		            </td>
		            <td>
		            <%if(loginMember!=null) {%>
		               <button class="btn-reply btn btn-primary reply-btn" value="<%=fc.getFreeCommentNo() %>">답글</button>
		               
		               <%if(loginMember.getId().equals(fc.getFreeCommentWriter())||loginMember.getAdminYN()=='Y') {%>
		               		<button class="btn-delete btn reply-btn" id="freeCommentDelete" name="freeCommentDelete" value="<%=fc.getFreeCommentNo() %>">삭제</button>
		            </td>
		               <%} %>
		            <%} %>
		         </tr>
		      <%}else {%>
		         <tr class='level2'>
		            <td id="leve2Comment">
		               <sub class="comment-writer"><%=fc.getFreeCommentWriter()%></sub>
		               <sub class="comment-date"><%=fc.getFreeCommentDate()%></sub>
		               <br/>
		               <span id="commentContent"><%=fc.getFreeCommentContent() %></span>
		            </td>
		            <td>
		               
		            </td>
		         </tr>
		         <%}
		         }
		      }%>
		      </table>
				
				<script>
				
					//답글 이벤트
					$('.btn-reply').on('click',function(e){
					<%if(loginMember!=null){%>
					var tr=$('<tr></tr>');
					var html="<td style='display:none text-align:left;' colspan='2'>"; 
					html+="<form class='form-inline mb-2' action='<%=request.getContextPath()%>/board/freeCommentInsert' method='post'>";
					html+="<input type='hidden' name='freePostNo' value='<%=list.get(0).getFreePostNo()%>'/>";
					html+="<input type='hidden' name='freeCommentWriter' value='<%=loginMember.getId()%>'/>";
					html+="<input type='hidden' name='freeCommentLevel' value='2'/>";
					html+="<input type='hidden' name='freeCommentRef' value='"+$(this).val()+"'/>";
					html+="<input class='form-control' type='text' placeholder='Comment' id='userPostComment' name='freeCommentContent'/>";
					html+="<button type='submit' class='btn-insert2 btn btn-primary'>댓글등록</button>";
					html+="</form></td>";
					tr.html(html);
					tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
					$(this).off('click');
					tr.find('form').submit(function(e){
						if(<%=loginMember==null%>){
							fn_loginAlert();
							e.preventDefault();
							return;
						}
						var len=$(this).children($('input[name=freeCommentContent]')).val().trim().length;
						if(len==0){
							e.preventDefault();
						}
					});
					tr.find($('input[name=freeCommentContent]')).focus();
					<%}%>
					});
				
					$(function(){
						$('button[name=freeCommentDelete]').on("click",function(e){
							if(!confirm("댓글 삭제 하시겠습니까?")){
								return;
							}
							
							/* del은 현재 보는 커멘트 숫자 commentNo이다 */
							location.href="<%=request.getContextPath()%>/board/freeCommentDelete?freePostNo=<%=list.get(0).getFreePostNo() %>&del="+$(this).val();

						});
					});
					
					$('#freeComment').focus(function(){
						if(<%=loginMember==null%>){
							fn_loginAlert();
						}
					});
					
					$('[name=postCommentFrm]').submit(function (e){
						if(<%=loginMember==null%>){
							fn_loginAlert();
							e.preventDefault();
							return;
						}
						var len = $('input[name=freeCommentContent]').val().trim().length;
						if(len == 0){
							alert('내용을 입력하세요');
							e.preventDefault();
							$('#freeComment').focus();
						}
					});
					
					function fn_loginAlert(){
						alert('로그인 후 이용가능 합니다.');
					};

				</script>
			</div>
			<div class='col-md-2' id='aside'>
				
			</div>


		</div>
	</div>

</section>

<%@ include file="/views/common/footer.jsp" %>