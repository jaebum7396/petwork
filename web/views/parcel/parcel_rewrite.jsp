<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.*,java.util.List" %>
<%
	ParcelAniView pav = (ParcelAniView)request.getAttribute("rewrite");
	ParcelImg pMainImg = (ParcelImg)request.getAttribute("pMainImg");
	List<ParcelImg> pSubImg = (List)(request.getAttribute("pSubImg"));
	String cityCode = (String)request.getAttribute("cityCode");
%>
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
        fn_setDatePickerMax();
  });
  function fn_setDatePickerMax(){
        var datePicker = document.getElementById('pet-birth');
        datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
     }
</script>
<section>
	<div class='container-fluid'>
			<form action="<%=request.getContextPath() %>/parcelReWriteEnd" method="post" enctype="multipart/form-data">
		        <div class='row'>
		            <div class='col-md-2' id='nav'>
		            </div>
				            <div class='col-md-8' id='section'>
				                <div class="row mb-1" id="parcel-write-start">
				                    <div class="col-md-2 mt-2">
				                    	<input type="hidden" name="postNo" id="postNo" value="<%=pav.getPostNo()%>">
				                        <span class="parcel-write-text-title">글머리</span>
				                    </div>
				                    <%if(pav.getHead().equals("D")) {%>
					                    <div class="col-md-2">
					                    	<label><span>강아지 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="D" checked></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>고양이 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="C"></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>기타 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="E"></label>
					                    </div>
					                <%} else if(pav.getHead().equals("C")){%>
					                	<div class="col-md-2">
					                    	<label><span>강아지 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="D"></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>고양이 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="C" checked></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>기타 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="E"></label>
					                    </div>
					                <%} else if(pav.getHead().equals("E")){%>
					                	<div class="col-md-2">
					                    	<label><span>강아지 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="D"></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>고양이 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="C"></label>
					                    </div>
					                    <div class="col-md-2">
					                    	<label><span>기타 </span><input type="radio" name="parcel-write-race" class="parcel-text-head-radio ml-2 mt-1" value="E" checked></label>
					                    </div>
					                <%} %>
					                <div class="col-md-4">
				                        <select class="form-control" name="parcel-animal" id="parcel-animal">
				                        	<option><%=pav.getAnimalName() %></option>
				                            
				                        </select>
				                    </div>
				                </div>
		
		
				               
				                <div class="row mb-1" id="parcel-info">
				                     
				                     <div class="col-md-2 mt-2">
				                        <span class="parcel-write-text-title">성별</span>
				                    </div>
				                    <div class="col-md-3">
				                    	<%if(pav.getGender().equals("M")) {%>
					                        <label><span>남아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "M" checked></label>
					                        <label><span>여아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "F"></label>
				                        <%} else{%>
				                        	<label><span>남아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "M"></label>
					                        <label><span>여아 </span><input type="radio" name="pet-gender" class="pet-gender ml-2 mt-1" value = "F" checked></label>
				                        <%} %>
				                    </div>
				                    <div class="col-md-3">
				                    	<%if(pav.getNeutering().equals("Y")) {%>
				                        	<label><span>중성화유무 </span><input type="checkbox" name="pet-neutering" class="pet-neutering ml-2 mt-1" value="Y" checked></label>
				                        <%} else {%>
				                        	<label><span>중성화유무 </span><input type="checkbox" name="pet-neutering" class="pet-neutering ml-2 mt-1" value="Y"></label>
				                        <%} %>
				                    </div>
				                    
				                </div>
				               
				                <div class="row mb-1" id="parcel-city-list">
				                	<div class="col-md-2 mt-2">
				                		<span class="parcel-write-text-title">분양지역</span>
				                	</div>
				                    <div class="col-md-3">
				                        <select class="form-control" name="parcel-city">
				                        <%String[] city = pav.getAddress().split(" "); %>
				                        	<option value="<%=cityCode%>"><%=city[0] %></option>
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
				                    <div class="col-md-4">
				                        <select class="form-control" name="parcel-district" id="parcel-district">
				                        
				                        	<option value="<%=city[1]%>"><%=city[1] %></option>
				                            	                            
				                        </select>
				                    </div>
				                    
				                </div>
				                <div class="row">
				                	<div class="col-md-2 mt-2">
				                		<span class="parcel-write-text-title">펫 생년월일</span>
				                	</div>
				                    <div class="col-md-4">
				                    	<input type="date" class="form-control" name="pet-birth" id="pet-birth" value="<%=pav.getPetBirth()%>">
				                    </div>
				                    <div class="col-md-2 mt-2"  style="text-align: right;">
				                    	<span class="parcel-write-text-title">가격 </span>
				                    </div>
				                    <div class="col-md-3">
				                    	<input type="number" class="form-control" name="parcel-price" min="0" max="99990000" placeholder="가격" value="<%=pav.getPrice()%>"  step="10000">
				                    </div>
				                    <div class="col-md-1"></div>
				                </div>
				                <div class="row">
				                    <div class="col-md-12">
				                        <span class="parcel-write-text-title mr-1">예방접종여부</span>
				                    	<%if(pav.getVaccination()!=null) {%>
					                    	<%if(pav.getVaccination().contains("종합백신")){ %>
					                        	<label><span>종합백신 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="종합백신" checked></label>
					                        <%} else{%>
					                        	<label><span>종합백신 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="종합백신"></label>
					                        <%} %>
					                        <%if(pav.getVaccination().contains("코로나장염")){ %>
					                        	<label><span>코로나장염 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="코로나장염" checked></label>
					                        <%} else{%>
					                        	<label><span>코로나장염 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="코로나장염"></label>
					                        <%} %>
					                        <%if(pav.getVaccination().contains("켄넬코프")){ %>
					                        	<label><span>켄넬코프 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="켄넬코프" checked></label>
					                        <%} else{%>
					                        	<label><span>켄넬코프 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="켄넬코프"></label>
					                        <%} %>
					                        <%if(pav.getVaccination().contains("광견병")){ %>
					                        	<label><span>광견병 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="광견병" checked></label>
					                        <%} else{%>
					                        	<label><span>광견병 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="광견병"></label>
					                        <%} %>
					                        <%if(pav.getVaccination().contains("인플루엔자")){ %>
					                        	<label><span>인플루엔자 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="인플루엔자" checked></label>
					                        <%} else{%>
					                        	<label><span>인플루엔자 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="인플루엔자"></label>
					                        <%} %>
					                        <%if(pav.getVaccination().contains("심장사상충")){ %>
					                        	<label><span>심장사상충 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="심장사상충" checked></label>
					                        <%} else{%>
					                        	<label><span>심장사상충 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="심장사상충"></label>
					                        <%} %>
				                        <%} else {%>
				                        	<label><span>종합백신 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="종합백신"></label>
					                        <label><span>코로나장염 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="코로나장염"></label>
					                        <label><span>켄넬코프  </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="켄넬코프"></label>
					                        <label><span>광견병  </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="광견병"></label>
					                        <label><span>인플루엔자 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="인플루엔자"></label>
					                        <label><span>심장사상충 </span><input type="checkbox" name="vaccination" class="vaccination ml-2 mt-1" value="심장사상충"></label>
				                        <%} %>
				                    </div>
				                </div>
				                <div class="row mb-1">
				                    <div class="col-md-12">
				                    	<span class="parcel-write-text-title">제목</span>
				                    </div>
				                </div>
				                <div class="row mb-1">
				                    <div class="col-md-12">
				                        <input class="form-control" type="text" id="parcel-title" name = "parcel-title" value="<%=pav.getTitle() %>">
				                    </div>
				                </div>
				                <div class="row mb-1">
				                	<div class="col-md-12">
				                		<p class="parcel-write-text-title">내용</p> 
				                	</div>
				                </div>
				                <div class="row mb-1">
				                	<div class="col-md-12">
				                        <textarea rows="10" cols="80" class="form-control" name="parcel-content"><%=pav.getContent() %></textarea>
				                    </div>
				                </div>
				                <div class="row">
					               <div class="col-md-12">
					               <table class="table table-protocol table-bordered">
                  					<tbody>
                  					<tr>
				                        <th>이미지</th>
				                        <td>
				                           <span>
				                              <input type="file" class="form-control" name="newfile1" onclick="refile(<%=pMainImg.getParcelImgAddress() %>);"/>
				                              현재 : <%=pMainImg.getParcelImgAddress() %>
				                           </span>
					                        <input type="hidden" name="oldfile1" value="<%=pMainImg.getParcelImgAddress()%>"/>
				                        </td>
				                     </tr>
					               <%for(int i = 0;i<pSubImg.size();i++) {%>
						              <tr>
				                        <th>이미지</th>
				                        <td>
				                           <span>
				                              <input type="file" class="form-control" name="newfile<%=i+2 %>" onclick="refile(<%=pSubImg.get(i).getParcelImgAddress() %>);"/>
				                           	현재 : <%=pSubImg.get(i).getParcelImgAddress() %>
				                           </span>
					                        <input type="hidden" name="oldfile<%=i+2 %>" value="<%=pSubImg.get(i).getParcelImgAddress()%>"/>
				                        </td>
				                     </tr>
				                     <%} %>
					               </tbody>
               					</table>
               					</div>
					            </div>
				                <div class="row" id="parcel-write-end">
				                    <div class="col-md-8">
				                    </div>
				                    <div class="col-md-2">
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