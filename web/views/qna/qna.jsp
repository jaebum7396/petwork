<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.Qna" %>
<%
	Qna qnaUser = (Qna)request.getAttribute("qnaUser");
%>
<%@ include file="/views/common/header.jsp"%>
<script>
	$(function(){
		$('#adminProfile').on("change",function(){
			var selectAdmin = $(this).val();
			
			$.ajax({
				url:"<%=request.getContextPath()%>/qna/qnaList",
				data:{"admin":selectAdmin},
				type:"post",
				success:function(data){
					
					$('#adminId').val(data['adminId']);
					$('#adminName').val(data['adminName']);
					$('#adminPhone').val(data['adminPhone']);
					$('#adminEmail').val(data['adminEmail']);
					$('#adminEmail2').val(data['adminEmail2']);
				}
			});
		});
	});
	
	function validate(){
		
		var content = $('#qna-content');

		if(content.val().trim().length==0){
			alert('문의 내용을 작성해주세요');
			content.focus();
			return false;
		}
		return true;
	}
</script>
<section id='myPage-section'>       
            <div class="container-fluid">
                    <div class="row">
                        <div class='col-md-2'></div>
                        <div class="col-md-8">
                        <div class="row">
                      <!-- left column -->
                      <div class="col-md-6 mt-2">
                        <h3 class="page-header">Admin Profile</h3>
                        
                        <div class="form-group mt-4">
                            	<span class="col-md-12">문의사항</span>
	                        <div class="col-md-12">
	                           <select class="form-control" name="adminProfile" id="adminProfile">
		                           <option value="admin1">회원관련문의</option>
		                           <option value="admin3">수의사관련문의</option>
		                           <option value="admin2">자유,나눔관련문의</option>
		                           <option value="admin4">분양관련문의</option>
		                           <option value="admin6">유기견보호관련문의</option>
		                           <option value="admin5">기타문의</option>
	                       		</select>
	                        </div>
                        </div>
                        <div class="form-group">
                            	<span class="col-md-12">아이디</span>
	                        <div class="col-md-12">
	                           <input type="text" class="form-control" name="adminId" id="adminId" readonly value="admin1">
	                        </div>
                        </div>
                        
                        <div class="form-group">
                                <span class="col-md-12">이름</span>
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="adminName" id="adminName" readonly value="주재범">
                            </div>
                        </div>
                        <div class="form-group">
                                <span class="col-md-12">연락처</span>
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="adminPhone" id="adminPhone" readonly value="01050550366">
                            </div>
                        </div>
                        <div class="form-group">
                                <span class="col-md-12">이메일</span>
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="adminEmail" id="adminEmail" readonly value="jaebum6937@gmail.com">
                            </div>
                        </div>
                        
                      </div>
                      <!-- edit form column -->
                      <div class="col-md-6 personal-info mt-2">
                        <h3>Personal info</h3>
                        <form action="<%=request.getContextPath() %>/sendEmail" method="post">
                        <input type="hidden" name="adminEmail2" id="adminEmail2"/>
                            <div class="form-group">
                                <label class="col-md-12 control-label">회원 ID:</label>
                                <div class="col-md-12">
                                    <input class="form-control" value="<%=loginMember.getId() %>" type="text" name="id" id="userId" disabled>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" value="" type="password" name="password"/>
                            <div class="form-group">
                                <label class="col-md-12 control-label">회원 이름:</label>
                                <div class="col-md-12">
                                    <input class="form-control" value="<%=loginMember.getName() %>" type="text" name="name" disabled>
                                </div>
                            </div>
						<div class="form-group">
                            <label class="col-md-12 control-label">회원 연락처:</label>
                            <div class="col-md-12">
                                <input class="form-control" value="<%=loginMember.getPhone() %>" type="text" name="phone" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12 control-label">이메일:</label>
                            <div class="col-md-12">
                                <input class="form-control" value="<%=loginMember.getEmail() %>" type="email" id="email" disabled>
                                <input type="hidden" name="email" value="<%=loginMember.getEmail() %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12 control-label">제목:</label>
                            <div class="col-md-12">
                                <input class="form-control" type="text" name="subject" id="subject" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12 control-label">문의내용:</label>
                            <div class="col-md-12">
                                <textarea cols="30" rows="10" class="form-control" name="qna-content" id="qna-content" required></textarea>
                            </div>
                        </div>
                          <div class="form-group">
                            <div class="col-md-12">
                              <input class="btn btn-primary" value="보내기" type="submit">
                              <span></span>
                              <input class="btn btn-default" value="Cancel" type="reset">
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                	</div>
                  <div class='col-md-2'></div>	
                </div> 
            </div>  
    </section>	
<%@ include file="/views/common/footer.jsp"%>