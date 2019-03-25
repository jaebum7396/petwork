<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<script>
	
	// 동물리스트 ajax
	$(function(){
		$('input[name=parcel-write-race]').on("change",function(){
			var parcelAnimalCheckedValue = document.querySelector('input[name=parcel-write-race]:checked').value;
			$.ajax({
				url:"<%=request.getContextPath()%>/petwork/parcelWriteAnimal",
				data:{"raceCode":parcelAnimalCheckedValue},
				dataType:"html",
				type:"post",
				success:function(data){
					var ani=data.split(",");
					var html="";
					for(var i = 0;i<ani.length-1;i++){
						html+="<option value='"+ani[i]+"'>"+ani[i]+"</option>";
					}
					$('#parcel-animal').html(html);
				}
			})
		});
	});
	// 도시 리스트 ajax
	$(function(){
		$("select[name=parcel-city]").on("change",function(){
			var city=$(this).val();
			$.ajax({
				url:"<%=request.getContextPath()%>/petwork/parcelWriteCity",
				data:{"city":city},
				dataType:"html",
				type:"post",
				success:function(data){
					var cityOption=data.split(",");
					var html="";
					for(var i = 0;i<cityOption.length-1;i++){
						html+="<option value='"+cityOption[i]+"'>"+cityOption[i]+"</option>";
					}
					$('#parcel-district').html(html);
				}
			})
		});
	});
	$(function(){
        $('#parcel-img1').on("change",function(){
           $('#add-img-text2').css('display','inline');
           $('#parcel-img2').css('display','inline');
        });
     });
	$(function(){
        $('#parcel-img2').on("change",function(){
           $('#add-img-text3').css('display','inline');
           $('#parcel-img3').css('display','inline');
        });
     });
	$(function(){
        $('#parcel-img3').on("change",function(){
           $('#add-img-text4').css('display','inline');
           $('#parcel-img4').css('display','inline');
        });
     });
	$(function(){
        $('#parcel-img4').on("change",function(){
           $('#add-img-text5').css('display','inline');
           $('#parcel-img5').css('display','inline');
        });
     });
	$(function(){
        fn_setDatePickerMax();
  });
  function fn_setDatePickerMax(){
        var datePicker = document.getElementById('pet-birth');
        datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
     }
</script>
<section>
	<div class='container-fluid'>
			<form action="<%=request.getContextPath() %>/parcelWriteView" name="parcelWriteFrm" method="post" enctype="multipart/form-data">
		        <div class='row'>
		            <div class='col-md-2' id='nav'>
		            </div>
				            <div class='col-md-8' id='section'>
				                <div class="row mb-1" id="parcel-write-start">
				                    <div class="col-md-2 mt-2">
				                        <span class="parcel-write-text-title" >글머리</span>
				                    </div>
					                    <div class="col-md-2">
					                    	<label><span>강아지 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="D"required></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>고양이 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="C"required></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>기타 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="E"required></label>
					                    </div>
					                    <div class="col-md-4">
				                        <select class="form-control" name="parcel-animal" id="parcel-animal">
				                        	<option>동물종류를 선택하세요</option>
				                            
				                        </select>
				                    </div>
				                </div>

				                <div class="row mb-1" id="parcel-info">
				                    <div class="col-md-2 mt-2">
				                        <span class="parcel-write-text-title">성별</span>
				                    </div>
				                    <div class="col-md-3">
				                        <label><span>남아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "M"required></label>
				                        <label><span>여아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "F"required></label>
				                    </div>
				                    <div class="col-md-3">
				                        <label><span>중성화유무 </span><input type="checkbox" name="pet-neutering" class="pet-neutering ml-2 mt-1" value="Y"></label>
				                    </div>
				                    
				                </div>
				                
				                <div class="row mb-1" id="parcel-city-list">
				                	<div class="col-md-2 mt-2">
				                		<span class="parcel-write-text-title">분양지역</span>
				                	</div>
				                    <div class="col-md-3">
				                        <select class="form-control" name="parcel-city">
				                            <option value="CITY1">서울특별시</option>
				                            <option value="CITY2">부산광역시</option>
				                            <option value="CITY3">광주광역시</option>
				                            <option value="CITY4">대전광역시</option>
				                            <option value="CITY5">대구광역시</option>
				                            <option value="CITY6">울산광역시</option>
				                            <option value="CITY7">인천광역시</option>
				                            <option value="CITY8">경기도</option>
				                            <option value="CITY9">강원도</option>
				                            <option value="CITY10">충청남도</option>
				                            <option value="CITY11">충청북도</option>
				                            <option value="CITY12">경상남도</option>
				                            <option value="CITY13">경상북도</option>
				                            <option value="CITY14">전라남도</option>
				                            <option value="CITY15">전라북도</option>
				                            <option value="CITY16">제주도</option>
				                        </select>
				                    </div>
				                    <div class="col-md-3">
				                        <select class="form-control" name="parcel-district" id="parcel-district">
											<option value="강남구">강남구</option>	
											<option value="강동구">강동구</option>
											<option value="강북구">강북구</option>
											<option value="강서구">강서구</option>
											<option value="관악구">관악구</option>
											<option value="광진구">광진구</option>
											<option value="구로구">구로구</option>
											<option value="금천구">금천구</option>
											<option value="노원구">노원구</option>
											<option value="도봉구">도봉구</option>
											<option value="동대문구">동대문구</option>
											<option value="동작구">동작구</option>
											<option value="마포구">마포구</option>
											<option value="서대문구">서대문구</option>
											<option value="서초구">서초구</option>
											<option value="성동구">성동구</option>
											<option value="성북구">성북구</option>			                            	                            
											<option value="송파구">송파구</option>
											<option value="양천구">양천구</option>
											<option value="영등포구">영등포구</option>
											<option value="용산구">용산구</option>
											<option value="은평구">은평구</option>
											<option value="종로구">종로구</option>
											<option value="중구">중구</option>
											<option value="중랑구">중랑구</option>
				                        </select>
				                    </div>
				                   	<div class="col-md-1"></div>
				                   
				                </div>
				                <div class="row">
				                	<div class="col-md-2 mt-2">
				                		<span class="parcel-write-text-title">펫 생년월일</span>
				                	</div>
				                    <div class="col-md-4">
				                    	<input type="date" class="form-control" name="pet-birth" id="pet-birth" required>
				                    </div>
				                     <div class="col-md-2 mt-2"  style="text-align: right;">
				                    	<span class="parcel-write-text-title">가격 </span>
				                    </div>
				                    <div class="col-md-3">
				                        <input type="number" class="form-control" name="parcel-price" min="0" max="99990000" placeholder="가격"  step="10000" required>
				                    </div>
				                    <div class="col-md-1"></div>
				                </div>
				                <div class="row">
				                    <div class="col-md-12">
				                        <span class="parcel-write-text-title mr-1">예방접종여부</span>
				                    
				                        <label><span>종합백신 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="종합백신"></label>
				                        <label><span>코로나장염 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="코로나장염"></label>
				                        <label><span>켄넬코프  </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="켄넬코프"></label>
				                        <label><span>광견병  </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="광견병"></label>
				                        <label><span>인플루엔자 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="인플루엔자"></label>
				                        <label><span>심장사상충 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="심장사상충"></label>
				                    </div>
				                </div>
				                <div class="row mb-1">
				                    <div class="col-md-12">
				                    	<span class="parcel-write-text-title">제목</span>
				                    </div>
				                </div>
				                <div class="row mb-1">
				                    <div class="col-md-12">
				                        <input type="text" class="form-control" id="parcel-title" name = "parcel-title"  required>
				                    </div>
				                </div>
				                <div class="row mb-1">
				                	<div class="col-md-12">
				                		<p class="parcel-write-text-title">내용</p> 
				                	</div>
				                </div>
				                <div class="row mb-1">
				                    <div class="col-md-12 mb-1">
				                        <textarea rows="10" cols="80" class="form-control" name="parcel-content" required></textarea>
				                    </div>
				                </div>
				                <div class="row mb-1">
					               <div class="col-md-2 mt-2">
					                  <span id="add-img-text">사진첨부</span>
					               </div>
					               <div class="col-md-10" id="add-img">
					                  <input type="file" accept="image/*" class="form-control" name="parcel-img1" id="parcel-img1" required>
					               </div>
					            </div>
					            <div class="row mb-1">
					               <div class="col-md-2 mt-2">
					                  <span id="add-img-text2">사진첨부</span>
					               </div>
					               <div class="col-md-10">
					                  <input type="file" accept="image/*" class="form-control" name="parcel-img2" id="parcel-img2" required>
					               </div>
					            </div>
					            <div class="row mb-1">
					               <div class="col-md-2 mt-2">
					                  <span id="add-img-text3">사진첨부</span>
					               </div>
					               <div class="col-md-10">
					                  <input type="file" accept="image/*" class="form-control" name="parcel-img3" id="parcel-img3" required>
					               </div>
					            </div>
					            <div class="row mb-1">
					               <div class="col-md-2 mt-2">
					                  <span id="add-img-text4">사진첨부</span>
					               </div>
					               <div class="col-md-10">
					                  <input type="file" accept="image/*" class="form-control" name="parcel-img4" id="parcel-img4" required>
					               </div>
					            </div>
					            <div class="row mb-1">
					               <div class="col-md-2 mt-2">
					                  <span id="add-img-text5">사진첨부</span>
					               </div>
					               <div class="col-md-10">
					                  <input type="file" accept="image/*" class="form-control" name="parcel-img5" id="parcel-img5" required>
					               </div>
					            </div>
				                <div class="row" id="parcel-write-end">
				                    <div class="col-md-8">
				                    </div>
				                    <div class="col-md-2">
				                        <input type="reset" value="취소" class="btn btn-primary" id="del-btn">
				                    </div>
				                    <div class="col-md-2">
				                        <input type="submit" value="등록" class="btn btn-primary" id="set-btn">
				                    </div>
				                </div>
				            </div>
		            <div class='col-md-2' id='aside'>
		            </div>
		        </div>
			</form> 
	    </div>  
</section>
<%@ include file="/views/common/footer.jsp"%>