<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.*, com.petwork.model.vo.ProtectPost"%>

<!-- 아이콘 CSS -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	
<% 
int noticeProtectNo = -1;
if(request.getAttribute("noticeProtectNo") != null){
	noticeProtectNo = (int)request.getAttribute("noticeProtectNo");
}%>
<script>
$(function(){
	if(<%=noticeProtectNo%> > 0){
		$('#btnNotice').trigger('click');
	}
});

function confirmNotice(){
	location.href="<%=request.getContextPath()%>/missingPet/protectPostView?no=" + <%=noticeProtectNo%>;
}
</script>
<section id='index-section'>
	<div class="container-fluid">
		<div class="row"'>
			<div class='col-md-2'></div>
			<div class="col-md-8 index-contents" id='index-contents'>
				<script>
						$(document).ready(function() 
						{											
							$.ajax({
								url:"<%=request.getContextPath()%>/indexcontroller",
								type:"get",
								dataType:"html",
								success:function(data)
								{
									console.log(data);
									console.log(data[0]);
									var html=''
									{
										console.log(data);
										$('#index-contents').html(data);
									}
								}
							})												
						});
					</script>
				<div class='col-md-2'></div>
			</div>
		</div>
	</div>
</section>

<!-- 모달창 구현 -->
<button type="button" id="btnNotice" style="display:none;" data-toggle="modal" data-target='#noticeModal'></button>
<div class="modal" tabindex="-1" role="dialog" id="noticeModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">보호신고 알림 <i class="fas fa-bell" style="color: #EDD200"></i></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>실종신고와 인식번호가 일치하는 보호신고 글이 올라왔습니다.</p>
				<p>지금 확인해보시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="confirmNotice();"
					data-dismiss="modal">확인</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>