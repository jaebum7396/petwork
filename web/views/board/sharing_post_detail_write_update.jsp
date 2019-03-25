<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.SharingPost, java.util.*" %>
<%
	List<SharingPost> list = (List)request.getAttribute("list");
 %>
<!-- 다음 주소 검색 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
/* 주소 검색 버튼 클릭 시 실행 */
function fn_getAddressInfo(){
	var width = 500; //팝업의 너비
	var height = 600; //팝업의 높이
	new daum.Postcode({
           oncomplete: function(data) {
    	    if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
            } else {
            	var expJibunAddr = data.jibunAddress;
            }
           document.getElementById("sharingPostAddress").value = expJibunAddr;
           // 커서를 상세주소 필드로 이동한다.
           document.getElementById("sharingPostAddress").focus();
           },
           shorthand : false //'시','도' 부분을 축약 표시
        	}).open({
  				popupName: 'postcodePopup',
				left: (window.screen.width / 2) - (width / 2),
			    top: (window.screen.height / 2) - (height / 2)
      		});
}
</script>

<%@ include file="/views/common/header.jsp"%>

<!-- --------------------------------------상세페이지 작성 화면----------------------------------- -->

<section>

	<div class='container-fluid'>
		<div class='row' id="contents">
			<div class='col-md-2 admin-post-list-nav' id='nav'>
				
			</div>

			<div class='col-md-8' id='section'>

				<form id="sharing-post-detail-write" name="sharingPostWriteFrm" action="<%=request.getContextPath() %>/board/sharingBoardUpdateEnd?loginMember=<%=loginMember.getId() %>&sharingPostNo=<%=list.get(0).getSharingPostNo() %>" method="POST" enctype="multipart/form-data" onclick="return updateValidate();">
				<!-- 레이스 나누는 라디오 버튼 부분 -->
				<div class="row" id="post-radio">
                      <div class="col-md-3 mt-2">
                          <span class="post-detail-write-title-radio" id="post-detail-write-title-radio">글머리</span>
                      </div>
                         <%if(list.get(0).getRace_code().equals("D")) {%>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>강아지 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="D" checked></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>고양이 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="C"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>기타 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="E"></label>
                         </div>
                        <%} else if(list.get(0).getRace_code().equals("C")) {%>
                        <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>강아지 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="D" checked></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>고양이 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="C" checked></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>기타 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="E"></label>
                         </div>
                         <%} else {%>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>강아지 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="D"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>고양이 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="C"></label>
                         </div>
                         <div class="col-md-3">
                            <label class="sharing-text-head-select"><span>기타 </span><input type="radio" name="RaceCode" class="sharing-text-head-radio mt-1 ml-1" value="E" checked></label>
                         </div>
                         <%} %>
                   </div>
                   
                   <!-- 상품 선택 글머리 -->
				<div class="row">
					<div class="col-md-3 mt-2">
						<span id="text-head">상품</span>
					</div>
					<div class="col-md-9">
						<%if(list.get(0).getProductCategoryCode().equals("P1")) {%>
							<label class="post-detail-write-head-select">식품	<input class="mt-1 ml-1" type="radio" name="product" value="P1" checked></label> 
							<label class="post-detail-write-head-select">위생<input class="mt-1 ml-1" type="radio" name="product" value="P2"></label> 
							<label class="post-detail-write-head-select">장난감<input class="mt-1 ml-1" type="radio" name="product" value="P3"></label> 
							<label class="post-detail-write-head-select">하우스<input class="mt-1 ml-1" type="radio" name="product" value="P4"></label>
							<label class="post-detail-write-head-select">의류<input class="mt-1 ml-1" type="radio" name="product" value="P5"></label>
						<%} else if(list.get(0).getProductCategoryCode().equals("P2")){%>
							<label class="post-detail-write-head-select">식품	<input class="mt-1 ml-1" type="radio" name="product" value="P1"></label> 
							<label class="post-detail-write-head-select">위생<input class="mt-1 ml-1" type="radio" name="product" value="P2" checked></label> 
							<label class="post-detail-write-head-select">장난감<input class="mt-1 ml-1" type="radio" name="product" value="P3"></label> 
							<label class="post-detail-write-head-select">하우스<input class="mt-1 ml-1" type="radio" name="product" value="P4"></label>
							<label class="post-detail-write-head-select">의류<input class="mt-1 ml-1" type="radio" name="product" value="P5"></label>
						<%} else if(list.get(0).getProductCategoryCode().equals("P3")){%>
							<label class="post-detail-write-head-select">식품	<input class="mt-1 ml-1" type="radio" name="product" value="P1"></label> 
							<label class="post-detail-write-head-select">위생<input class="mt-1 ml-1" type="radio" name="product" value="P2"></label> 
							<label class="post-detail-write-head-select">장난감<input class="mt-1 ml-1" type="radio" name="product" value="P3" checked></label> 
							<label class="post-detail-write-head-select">하우스<input class="mt-1 ml-1" type="radio" name="product" value="P4"></label>
							<label class="post-detail-write-head-select">의류<input class="mt-1 ml-1" type="radio" name="product" value="P5"></label>
						<%} else if(list.get(0).getProductCategoryCode().equals("P4")){%>
							<label class="post-detail-write-head-select">식품	<input class="mt-1 ml-1" type="radio" name="product" value="P1"></label> 
							<label class="post-detail-write-head-select">위생<input class="mt-1 ml-1" type="radio" name="product" value="P2"></label> 
							<label class="post-detail-write-head-select">장난감<input class="mt-1 ml-1" type="radio" name="product" value="P3"></label> 
							<label class="post-detail-write-head-select">하우스<input class="mt-1 ml-1" type="radio" name="product" value="P4" checked></label>
							<label class="post-detail-write-head-select">의류<input class="mt-1 ml-1" type="radio" name="product" value="P5"></label>
						<%} else {%>
							<label class="post-detail-write-head-select">식품	<input class="mt-1 ml-1" type="radio" name="product" value="P1"></label> 
							<label class="post-detail-write-head-select">위생<input class="mt-1 ml-1" type="radio" name="product" value="P2"></label> 
							<label class="post-detail-write-head-select">장난감<input class="mt-1 ml-1" type="radio" name="product" value="P3"></label> 
							<label class="post-detail-write-head-select">하우스<input class="mt-1 ml-1" type="radio" name="product" value="P4"></label>
							<label class="post-detail-write-head-select">의류<input class="mt-1 ml-1" type="radio" name="product" value="P5" checked></label>
						<%} %>
					</div>
				</div>

				<!-- 작성 부분 -->
				<div class="row form-inline">
					<div class="col-md-3">
						<span id="post-detail-write-title">제목</span>
					</div>
					<div class="col-md-9"> 
						<input class="form-control" type="text" id="sharingPostTitle" name="sharingPostTitle" required value="<%=list.get(0).getSharingPostTitle()%>">
					</div>
				</div>
				
				<div class="row form-inline mt-2">
					<div class="col-md-3">
						<span id="post-detail-write-writer">작성자</span>
					</div>
					<div class="col-md-9"> 
						<input class="form-control" type="text" id="sharingPostWriter" name="sharingPostWriter" readonly="readonly" value="<%=list.get(0).getSharingPostWriter()%>">
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-3">
						<span id="post-detail-write-writer">거래주소</span>
					</div>
					<div class="col-md-9"> 
						<input class="form-control" type="text" id="sharingPostAddress" name="sharingPostAddress"  value="<%=list.get(0).getSharingPostAddress()%>" readonly onclick="fn_getAddressInfo();">
					</div>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-3">
						<span id="post-detail-write-YN">거래여부</span>
					</div>
					<%if(list.get(0).getSharingPostYN().equals("N")) {%>
					<div class="col-md-3"> 
						<input class="mt-2 ml-1" type="radio" id="sharingPostYN" name="sharingPostYN" value="Y"> Y
					</div>
					<div class="col-md-3">
						<input class="mt-2 ml-1" type="radio" id="sharingPostYN" name="sharingPostYN" value="N" checked> N
					</div>
					<%} else { %>
					<div class="col-md-3"> 
						<input  class="mt-2 ml-1" type="radio" id="sharingPostYN" name="sharingPostYN" value="Y" checked> Y
					</div>
					<div class="col-md-3">
						<input class="mt-2 ml-1" type="radio" id="sharingPostYN" name="sharingPostYN" value="N"> N
					</div>
					<%} %>
				</div>
				<div class="row form-inline mt-2">
					<div class="col-md-3">
						<span id="post-detail-write-writer">내용</span>
					</div>
					<div class="col-md-9">
						<textarea class="form-control" rows="5" cols="95" name="sharingPostContent" id="PostContent" required><%=list.get(0).getSharingPostContent() %></textarea>
					</div>
				</div>
				<div class="row form-inline mt-2" id="post-detail-write-file">
					<table class="table table-protocol table-bordered">
						<tbody>
						<%if(list.size()>0){ %>
							<%for(int i=0; i<list.size(); i++) { %>
							<tr>
								<th id="sharingPostTh">기존이미지</th>
								<td id="sharingPostTd">
									<%=list.get(i).getSharingImgAddress()%>
								</td>	
								<td>	
									<input class="form-control" type="file" name="newfile<%=i%>"/>
								</td>
							</tr>
								<input type="hidden" name="oldfile<%=i %>" value="<%=list.get(i).getSharingImgAddress()%>"/>

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
					<div class="col-md-12 mb-2" id="sharing-post-detail-write-btn"> 
						<input type="submit" value="수정" class="btn btn-primary" id="btn-ok" name="btn-ok">
					</div>
				</div>
				</form>
			</div>
			
			<div class="col-md-2" id="aside"></div>
		</div>
	</div>

</section>

<%@ include file="/views/common/footer.jsp"%>