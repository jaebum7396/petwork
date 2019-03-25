<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.Pet"%>
<%@ page import="com.petwork.model.vo.Member"%>
<%@ page import="com.petwork.model.vo.Animal"%>
<%@ page import="java.util.List"%>
<!-- 아이콘 CSS -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<!-- 다음 주소검색 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%
	/* 해당 회원에게 등록된 애완동물 리스트 */
	List<Pet> myPetList = (List) request.getAttribute("myPetList");
%>
<%@ include file="/views/common/header.jsp"%>

<style>
.findPostSum {
	margin-right: 0px;
}
</style>

<script>
	$(function(){
		$('[name=findPostRegFrm]').submit(function(e){
			var missingAddr=$('input[name=findPostMissingAddress]').val().trim().length;
			var petNo=$('input[name=petNo]').val().trim().length;
			if(missingAddr==0)
			{
				alert("잃어버린 장소를 입력해주세요.");
				e.preventDefault();	
			}else if(petNo==0)
			{
				alert("등록할 아이를 선택해주세요.");
				$('input[name=findPostMyPetSelect]').focus();
				e.preventDefault();	
			}
		})
		
		//잃어버린 날짜 선택 최대날짜를 오늘까지로 설정
		fn_setDatePickerMax();
	});
	function fn_setDatePickerMax(){
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
	
	function fn_myPetSelectChanged(){
		var selectedPetNo = $("#findPostMyPetSelect option:selected").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/missingPet/findPostRegSelectPet",
			type:"post",
			data: {petNo:selectedPetNo},
			success:function(data){
				if(data['postDuplicate']) //true일 경우 중복된 실종신고
				{
					alert("이미 실종신고된 상태입니다. 게시글을 확인해주세요.");
				}else{
					$('#petNo').val(data['myPetNo']);
					$('#findPostPetName').val(data['myPetName']);
					$('#findPostRaceCode').val(data['myPetRace']);
					$('#findPostAnimalName').val(data['selectMyPetAnimalName']);				
					$('#findPostPetGender').val(data['myPetGender']=='M'? "수컷" : "암컷");				
					$('#findPostPetIdentifyNo').val(data['myPetIdentifyNo']);	
				}
			}
		});
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
							<div class="col-md-4 mt-1">
								<form id="findPostSelectFrm"
									action="<%=request.getContextPath()%>/missingPet/findPostRegSelectPet">
									<select id="findPostMyPetSelect" name="findPostMyPetSelect"
										class="form-control" onchange="fn_myPetSelectChanged()">
										<option value="" disabled selected>내 아이 등록하기</option>
										<%
											if (myPetList == null || myPetList.isEmpty()) {
										%>
										<option disabled>없음</option>
										<%
											} else {
												for (Pet p : myPetList) {
										%>
										<option value="<%=p.getPetNo()%>"><%=p.getPetName()%></option>
										<%
											}
											}
										%>
									</select>
								</form>
							</div>
							<div class="col-md-8 mt-2">
								<span style="font-size: 1.5rem; color: #C8D8A8;"><i
									class="fas fa-dog"></i>&nbsp;&nbsp;<i class="fas fa-cat"></i></span>
								&nbsp; <span class="text-muted small">등록된 반려동물에 한해 실종신고가
									가능합니다</span>
							</div>
						</div>
					</article>

					<article class="card-body">
						<form name="findPostRegFrm" method="post"
							enctype="multipart/form-data"
							action="<%=request.getContextPath()%>/missingPet/findPostRegEnd">
							<div class="row">
								<div class="col-md-8 form-group">
									<label>제목</label> <input type="text" class="form-control"
										placeholder="제목을 입력해주세요" name="findPostTitle"
										id="findPostTitle" required>
								</div>
								<div class="form-group col-md-4">
									<label>인식번호</label>
									<input type="text" class="form-control" id="findPostPetIdentifyNo" disabled>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4 form-group">
									<label>이름</label>
									<input type="text" class="form-control" id="findPostPetName" disabled />
								</div>
								<div class="form-group col-md-2">
									<label>성별</label>
									<input type="text" id="findPostPetGender" class="form-control" disabled />
								</div>
								<div class="col-md-2 form-group">
									<label>구분</label>									
									<input type="text" id="findPostRaceCode" class="form-control" disabled>
								</div>
								<div class="col-md-4 form-group">
									<label>품종</label>
									<input type="text" id="findPostAnimalName" class="form-control" disabled>
								</div>
							</div>

							<div class="row">
								<div class="col-md-8 form-group">
									<label>잃어버린 장소</label> <input type="text" class="form-control"
										placeholder="주소를 검색해주세요" onclick="fn_getAddressInfo()"
										name="findPostMissingAddress" id="findPostMissingAddress" readonly>
								</div>
								<div class="col-md-4 form-group">
									<label>잃어버린 날짜</label> <input type="date" class="form-control"
										id="findPostMissingDate" name="findPostMissingDate" required
										min='2018-01-01'>
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
								<div class="form-group col-md-6 mt-4 pt-3 pr-4 text-right">
									<span style="font-size: 1.5rem; color: #516055;"><i
										class="far fa-address-card"></i></span> 
										<span class="text-muted small">연락처 수정은 마이페이지를 이용해주세요</span>
								</div>
							</div>

							<label>사례금</label>
							<div class="row">
								<div class="form-group col-md-3 mt-2">
									<label class="form-check form-check-inline"> <input
										class="form-check-input" type="radio" name="findPostReward"
										value="N" id="findPostRewardN" checked
										onclick="fn_rewardChanged();">없음
									</label>
								</div>

								<div class="form-group col-md-3 mt-2">
									<label class="form-check form-check-inline"> <input
										class="form-check-input" type="radio" name="findPostReward"
										value="C" id="findPostRewardC" onclick="fn_rewardChanged();">찾은
										후 협의
									</label>
								</div>

								<div class="form-group col-md-3">
									<label class="form-check form-check-inline findPostSum">
										<input class="form-check-input" type="radio"
										name="findPostReward" value="Y" id="findPostRewardY"
										onclick="fn_rewardChanged();"> <input type="number"
										class="form-control text-right" id="findPostSum" disabled
										name="findPostSum" min="1">
									</label>
								</div><span class="mt-2">만원</span>
							</div>

							<div class="row">
								<div class="col-md-12 form-group">
									<label>상세설명</label>
									<textarea class="form-control" placeholder="상세설명을 입력해주세요"
										id="findPostContent" name="findPostContent" rows="5" required style="resize: none;"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label>사진</label> <input class="form-control" type="file"
										name="findPostImgAddress" id="findPostImgAddress" required
										accept="image/*">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-12 mt-4">
									<input type="submit" value="등록하기"
										class="btn btn-primary btn-block">
								</div>
							</div>
							<input type="hidden" value="<%=loginMember.getId()%>"
								name="memberId">
							<input type="hidden" name="petNo" id="petNo">
						</form>
						<div class="row">
							<div class="col-md-12">
								<small class="text-muted"> 인식번호로 보호신고를 조회하실 수 있습니다<br>
									반려동물 등록제 인식번호가 있는 반려동물은 반드시 마이페이지에서 인식번호를 등록해주세요!<br>
								</small>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12 border-top card-body text-center mt-3">
								내 아이 등록 <a href="<%=request.getContextPath()%>/mypagecontroller">바로가기 </a>
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