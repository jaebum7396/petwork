<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.FindPost"%>
<!-- 아이콘 CSS -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<!-- 다음 주소검색 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%
	FindPost findPost = (FindPost) request.getAttribute("findPost");
%>
<%@ include file="/views/common/header.jsp"%>

<style>
.findPostSum {
	margin-right: 0px;
}

#findPostImgView {
	width: 100%;
}
</style>

<script>
	$(function(){
		//수정버튼 눌렀을 경우 발생
		$('[name=findPostUpdateFrm]').submit(function(e){
			var missingAddr=$('input[name=findPostMissingAddress]').val().trim().length;
			if(missingAddr==0)
			{
				alert("잃어버린 장소를 입력해주세요.");
				e.preventDefault();	
			}
		})
		
		//첨부파일 변경시 발생
		$('#findPostReImg').change(function(e){
			var fileName = $('input[type=file]')[0].files[0].name;	
			$('#findPostReImg').val(fileName);
		});
		
		//잃어버린 날짜 선택 최대날짜를 오늘까지로 설정
		setDatePickerMax();

	});
	function setDatePickerMax(){
		var datePicker = document.getElementById('findPostMissingDate');
		datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
	}
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
               document.getElementById("findPostMissingAddress").value = expJibunAddr;
               // 커서를 상세주소 필드로 이동한다.
               document.getElementById("findPostMissingAddress").focus();
	           },
	           shorthand : false //'시','도' 부분을 축약 표시
	        	}).open({
      				popupName: 'postcodePopup',
    				left: (window.screen.width / 2) - (width / 2),
    			    top: (window.screen.height / 2) - (height / 2)
	      		});
    }

	//사례금 있음 선택시 금액 입력창 활성화 
	function fn_rewardChanged(){
		var rewardY = document.querySelector("#findPostRewardY"); 
		var sum = document.querySelector("#findPostSum"); 
		
		if(rewardY.checked){
			sum.disabled = false;
			sum.required = true;
		}else{
			sum.disabled = true;
			sum.required = false;
			sum.value = "";
		}
	}
</script>

<section>
	<div class="container-fluid">
		<div class='row' id="contents">
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8' id='section'>
				<div class="row mt-4 mb-4">
					<div class="card">
						<article class="card-header">
							<div class="row">
								<div class="col-md-8 mt-2">
									<span style="font-size: 1.5rem; color: #C8D8A8;"><i
										class="fas fa-dog"></i>&nbsp;&nbsp;<i class="fas fa-cat"></i></span>
									&nbsp; <span class="text-muted small">연락처 및 내 아이 정보 수정은 마이페이지를 이용해주세요</span>
								</div>
							</div>
						</article>
						<article class="card-body">
							<form name="findPostUpdateFrm" method="post"
								enctype="multipart/form-data"
								action="<%=request.getContextPath()%>/missingPet/findPostUpdateEnd">
								<div class="row">
									<div class="col-md-8 form-group">
										<label>제목</label> <input type="text" class="form-control"
											placeholder="제목을 입력해주세요" name="findPostTitle"
											value="<%=findPost.getFindPostTitle()%>" required>
									</div>
									<div class="form-group col-md-4">
										<label>인식번호</label> <input type="text" class="form-control"
											value="<%=findPost.getPetIdentifyNo()%>" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-md-4 form-group">
										<label>이름</label> <input type="text" class="form-control"
											value="<%=findPost.getPetName()%>" disabled />
									</div>
									<div class="form-group col-md-2">
										<label>성별</label> <input type="text"
											value="<%=findPost.getPetGender() == 'M' ? "수컷" : "암컷"%>"
											class="form-control" disabled />
									</div>
									<div class="col-md-2 form-group">
										<label>구분</label> <input type="text" class="form-control"
											value="<%=findPost.getRaceCode() == 'D' ? "강아지" : findPost.getRaceCode() == 'C' ? "고양이" : "기타"%>"
											disabled>
									</div>
									<div class="col-md-4 form-group">
										<label>품종</label> <input type="text" class="form-control"
											value="<%=findPost.getAnimalName()%>" disabled>
									</div>

								</div>

								<div class="row">
									<div class="col-md-8 form-group">
										<label>잃어버린 장소</label> <input type="text" class="form-control"
											placeholder="주소를 검색해주세요" onclick="fn_getAddressInfo()"
											name="findPostMissingAddress" id="findPostMissingAddress"
											value="<%=findPost.getFindPostMissingAddress()%>" readonly>
									</div>
									<div class="col-md-4 form-group">
										<label>잃어버린 시간</label> <input type="date" class="form-control"
											id="findPostMissingDate" name="findPostMissingDate" required
											min='2018-01-01'
											value="<%=findPost.getFindPostMissingDate()%>">
									</div>
								</div>

								<div class="row">
									<div class="form-group col-md-2 pr-1">
										<label>연락처</label> <input type="text"
											class="form-control text-center" disabled
											value="<%=loginMember.getPhone().substring(0, 3)%>">
									</div>
									<div class="form-group col-md-2 pr-1">
										<label>&nbsp;</label> <input type="text"
											class="form-control text-center" disabled
											value="<%=loginMember.getPhone().substring(3, 7)%>">
									</div>
									<div class="form-group col-md-2 pr-0">
										<label>&nbsp;</label> <input type="text"
											class="form-control text-center" disabled
											value="<%=loginMember.getPhone().substring(7, 11)%>">
									</div>
								</div>

								<label>사례금</label>
								<div class="row">
									<div class="form-group col-md-3 mt-2">
										<label class="form-check form-check-inline"> <input
											class="form-check-input" type="radio" name="findPostReward"
											value="N" id="findPostRewardN"
											<%=findPost.getFindPostReward() == 'N' ? "checked" : ""%>
											onclick="fn_rewardChanged();">없음
										</label>
									</div>

									<div class="form-group col-md-3 mt-2">
										<label class="form-check form-check-inline"> <input
											class="form-check-input" type="radio" name="findPostReward"
											value="C" id="findPostRewardC" onclick="fn_rewardChanged();"
											<%=findPost.getFindPostReward() == 'C' ? "checked" : ""%>>찾은
											후 협의
										</label>
									</div>

									<div class="form-group col-md-3">
										<label class="form-check form-check-inline findPostSum">
											<input class="form-check-input" type="radio"
											name="findPostReward" value="Y" id="findPostRewardY"
											onclick="fn_rewardChanged();"
											<%=findPost.getFindPostReward() == 'Y' ? "checked" : ""%>>
											<input type="number" class="form-control text-right"
											id="findPostSum" <%if (findPost.getFindPostSum() > 0) {%>
											value=<%=findPost.getFindPostSum()%> <%} else {%>
											disabled="disabled" <%}%> name="findPostSum" min="1">
										</label>
									</div>
									<span class="mt-2">만원</span>
								</div>

								<div class="row">
									<div class="col-md-12 form-group">
										<label>상세설명</label>
										<textarea class="form-control" placeholder="상세설명을 입력해주세요"
											id="findPostContent" name="findPostContent" rows="5" required style="resize: none;"><%=findPost.getFindPostContent()%></textarea>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-12">
										<label>사진</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<img id="findPostImgView" name="findPostImgView"
											src="<%=request.getContextPath()%>/views/upload/missingPet/<%=findPost.getFindPostImgAddress()%>">
									</div>
									<div class="col-md-9 mt-4 pt-2">
										<input class="form-control" type="file" id="findPostReImg"
											name="findPostReImg" accept="image/*"> <input
											type="hidden" name="findPostOriImg" id="findPostOriImg"
											value="<%=findPost.getFindPostImgAddress()%>">
									</div>
								</div>


								<div class="row">
									<div class="form-group col-md-12 mt-4">
										<input type="submit" value="수정하기"
											class="btn btn-primary btn-block">
									</div>
								</div>
								<input type="hidden" name="findPostNo"
									value="<%=findPost.getFindPostNo()%>">
							</form>
							<div class="row">
								<div class="col-md-12">
									<small class="text-muted"> 인식번호로 보호신고를 조회하실 수 있습니다<br>
										반려동물 등록제 인식번호가 있는 반려동물은 반드시 마이페이지에서 인식번호를 등록해주세요!<br>
									</small>
								</div>
							</div>
						</article>
					</div>
				</div>
				<div class='col-md-2' id='aside'></div>
			</div>
		</div>
	</div>
</section>


<%@ include file="/views/common/footer.jsp"%>