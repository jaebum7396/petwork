<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<% List<Animal> dogList = (List)request.getAttribute("dogList"); %>
<%@ include file="/views/common/header.jsp"%>


<script>
	$(function(){
		$('[name=protectPostRegFrm]').submit(function(e){
			var findAddr=$('input[name=protectPostFindAddress]').val().trim().length;
			if(findAddr==0)
			{
				alert("찾은 장소를 입력해주세요.");
				e.preventDefault();	
			}
			var identifyNo=$('input[name=protectPostPetIdentifyNo]').val().trim().length;
			if(identifyNo>0 && identifyNo<15)
			{
				alert("15자리의 인식번호를 입력하세요.");
				$('input[name=protectPostPetIdentifyNo]').focus();
				e.preventDefault();	
			}
		})
		
		$('[type=reset]').click(function(e){
			var findAddr=$('input[name=protectPostFindAddress]').val().trim().length;
			if(!confirm("다시 작성하시겠습니까?"))
			{
				e.preventDefault();	
			}
		})
		
		//찾은 날짜 선택 최대날짜를 오늘까지로 설정
		fn_setDatePickerMax();
	});
	function fn_setDatePickerMax(){
		var datePicker = document.getElementById('protectPostFindDate');
		datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
	}
	
	function onlyNumber(event){
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        return false;
	}
	
	function removeChar(event) {
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        event.target.value = event.target.value.replace(/[^0-9]/g, "");
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
               document.getElementById("protectPostFindAddress").value = expJibunAddr;
               // 커서를 상세주소 필드로 이동한다.
               document.getElementById("protectPostFindAddress").focus();
	           },
	           shorthand : false //'시','도' 부분을 축약 표시
	        	}).open({
      				popupName: 'postcodePopup',
    				left: (window.screen.width / 2) - (width / 2),
    			    top: (window.screen.height / 2) - (height / 2)
	      		});
    }
	
	function fn_racdCodeChanged(){
		var raceCode = $("#protectPostRaceCode option:selected").val();
		var raceCodeSelect = document.getElementById("protectPostRaceCode");
		protectPostAnimalName.options.length = 0;
	
		$.ajax({
			url:"<%=request.getContextPath()%>/missingPet/protectPostRegSelectRace",
			type:"post",
			data: {raceCode:raceCode},
			success:function(data){
				for (i = 0; i < data.length; i++)
				{ 
				     $('#protectPostAnimalName').append($('<option>',
				     {
				        value: data[i]['animalNo'],
				        text : data[i]['animalName']
				    }));
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
							<div class="col-md-12 mt-2">
								<span style="font-size: 1.5rem; color: #C8D8A8;"><i
									class="fas fa-dog"></i>&nbsp;&nbsp;<i class="fas fa-cat"></i></span>
								&nbsp; <span class="text-muted small">아이를 보호중이신가요? 반려동물 등록제 인식번호를 모르실경우 빈칸으로 두시면 됩니다</span>
							</div>
						</div>
					</article>

					<article class="card-body">
						<form name="protectPostRegFrm" method="post"
							enctype="multipart/form-data"
							action="<%=request.getContextPath()%>/missingPet/protectPostRegEnd">
							<div class="row">
								<div class="col-md-8 form-group">
									<label>제목</label> <input type="text" class="form-control"
										placeholder="제목을 입력해주세요" name="protectPostTitle"
										id="protectPostTitle" required>
								</div>
								<div class="form-group col-md-4">
									<label>인식번호</label>
									<input type="text" placeholder="15자리 인식번호" 
									onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'
									maxlength="15" class="form-control" id="protectPostPetIdentifyNo" name="protectPostPetIdentifyNo">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-2">
									<label>성별</label>
									<select id="protectPostPetGender" class="form-control" name="protectPostPetGender">
										<option value="M" selected>수컷</option>
										<option value="F">암컷</option>
										<option value="N">모름</option>
									</select>
								</div>
								<div class="col-md-3 form-group">
									<label>구분</label>		
									<select id="protectPostRaceCode" class="form-control" name="protectPostRaceCode" onchange="fn_racdCodeChanged()">
										<option value="D" selected>강아지</option>
										<option value="C">고양이</option>
										<option value="E">기타</option>
									</select>							
								</div>
								<div class="col-md-4 form-group">
									<label>품종</label>
									<select id="protectPostAnimalName" name="protectPostAnimalName" class="form-control">
									<%for(Animal a : dogList){ %>
									<option value="<%=a.getAnimalNo()%>"><%=a.getAnimalName()%></option>
									<%} %>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="col-md-8 form-group">
									<label>찾은 장소</label> <input type="text" class="form-control"
										placeholder="주소를 검색해주세요" onclick="fn_getAddressInfo()"
										name="protectPostFindAddress" id="protectPostFindAddress" readonly>
								</div>
								<div class="col-md-4 form-group">
									<label>찾은 날짜</label> <input type="date" class="form-control"
										id="protectPostFindDate" name="protectPostFindDate" required
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

							<div class="row">
								<div class="col-md-12 form-group">
									<label>상세설명</label>
									<textarea class="form-control" placeholder="상세설명을 입력해주세요"
										id="protectPostContent" name="protectPostContent" rows="5" required style="resize: none;"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12">
									<label>사진</label> <input class="form-control" type="file"
										name="protectPostImgAddress" id="protectPostImgAddress" required
										accept="image/*">
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-6 mt-4">
									<input type="reset" value="재작성"
										class="btn btn-primary btn-block">
								</div>
								<div class="form-group col-md-6 mt-4">
									<input type="submit" value="등록하기"
										class="btn btn-primary btn-block">
								</div>
							</div>
							<input type="hidden" value="<%=loginMember.getId()%>"
								name="memberId">
						</form>
					</article>
				</div>
			</div>
			<div class='col-md-2' id='aside'></div>
		</div>
		</div>
	</div>
</section>


<%@ include file="/views/common/footer.jsp"%>