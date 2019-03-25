<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.*,com.petwork.model.vo.Doctor" %>
  <%-- <%List<Doctor> cDoctorlist=(List)request.getAttribute("cityDoctor"); %> --%>
  <%List<Doctor> list=(List)request.getAttribute("list"); %>
 	  <%Doctor d =(Doctor)session.getAttribute("loginDoctor"); %>
 	  
<%@ include file="/views/common/header.jsp" %>
  
<script>
var html="";
//도시 리스트 ajax
$(function(){
   $("select[name=advice-cityList]").on("change",function(){
      var city=$(this).val();
      $.ajax({
         url:"<%=request.getContextPath()%>/doctor/findCity",
         data:{"city":city},
         dataType:"html",
         type:"post",
         success:function(data){
           
            
            
            var cityView="<h3 class='advice-cityView'>"+data+"</h3>";
            
            
            $('#cityName').html(cityView);
         }
      })
   });
   
});
//도시셀렉트 ajex
$(function(){
   $("select[name=advice-cityList]").on("change",function(){
      var city=$(this).val();
      $.ajax({
         url:"<%=request.getContextPath()%>/doctor/advice",
         data:{"city":city},
         dataType:"html",
         type:"post",
         success:function(data){
            html=JSON.parse(data);
        	 var temp="";
        	$('.col-xs-12 col-sm-6 col-md-4').remove();
        	 for(var i=0;i<html.length;i++){
        		 
        	temp+="<div class='col-xs-12 col-sm-6 col-md-4'>"
            temp+="<div class='advice-image-flip' ontouchstart='this.classList.toggle('hover');'>"
           temp+=" <div class='advice-mainflip mb-3'>"
            temp+="<div class='advice-frontside'>"
             temp+="<div class='advice-card'>"
             temp+="<div id='adviceFront' name='adviceFront' class='advice-card-body text-center'>"
        	 temp+="<img class='img-fluid' src='<%=request.getContextPath()%>/views/upload/doctor/"+html[i]['doctorImg']+"'>"
        	 temp+="<h4 class='advice-card-title'>"+html[i]["doctorName"]+"</h4>";
        	 temp+="<p class='card-text'>"+html[i]["doctorHospital"]+"</p>";
        	 temp+="<p class='card-text'>"+html[i]["doctorPhone"]+"</p>";
        	 temp+="</div>"
             temp+="</div>"
             temp+="</div>"
           	temp+="<div class='advice-backside'>"
            temp+=" <div class='advice-card'>"
            temp+="<div id='advice-card-body text-center mt-4' class='advice-card-body text-center mt-4'>"
            temp+="<h4 class='advice-card-title'>"+html[i]["doctorName"]+"</h4>"

            temp+="<p class='card-text'>병원주소</p>"
            temp+="<p class='card-text' style='font-size:0.78em;'>"+html[i]["doctorAddress"]+"</p>"
           	temp+="<img class='img-fluid' src='<%=request.getContextPath()%>/views/adminMain2.png'>"
            /*  temp+="<button onclick='advice1();' class='btn btn-primary' id='adivece1'>상담하기</button>" */ 
            temp+="</div>"
            	temp+="</div>"
            		temp+="</div>"
       
            			temp+="</div>"
   
            				temp+="</div>"
            					temp+="</div>"
            					
            						$('#main-row').html(temp); 
        	 }
           
            
            /* $('#advice-card-body text-center mt-4').html(doctorName);
            $('#advice-card-body text-center mt-4').html(doctorHospital);
            $('#advice-card-body text-center mt-4').html(doctorPhone);
             */
         }
      })
   });
   
});
</script>
<script>
function advice1() {window.open("http://192.168.20.228:9100", "의사상담창", "width=580, height=300, left=100, top=50"); }
function advice2() {alert('로그인이 필요한 서비스입니다. 로그인을 해주세요') }
</script>
<section id='content'>
	<div class='container-fluid'>
        
        	<div class="row">
            <div class='col-md-2' id='nav'>
            
            </div>
            
            <div class='col-md-8' id='section'>
                <div class="row">
                    <select id="city" name="advice-cityList" class="advice-cityList col-md-3 form-control" >
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
                        <option value="CITY16">제주특별자치도</option>
                    </select>                    
                </div>

                  
                <section id="advice-team" class="pb-5">
                	<div class="row">
                		<div class="col-md-12" id="cityName">
                     		<h3 class='advice-cityView'>서울특별시</h3>
                     	</div>                         
					</div> 
				
                                <div id = "main-row" class="row">
                                    <!-- Team member -->
                                    	<%for(int i=0;i<list.size();i++){ %>
                                    
                                    <div class="col-xs-12 col-sm-6 col-md-4">
                                        <div class="advice-image-flip" ontouchstart="this.classList.toggle('hover');">
                                            <div class="advice-mainflip mb-3">
                                                <div class="advice-frontside">
                                                    <div class="advice-card">
                                                        <div id="adviceFront" name="adviceFront" class="advice-card-body text-center">
                                                            <img class=" img-fluid" src="<%=request.getContextPath()%>/views/upload/doctor/<%=list.get(i).getDoctorImg()%>" alt="card image">
                                                            <h4 class="advice-card-title"><%=list.get(i).getDoctorName()%></h4>
                                                            <p class="card-text"><%=list.get(i).getDoctorHospital()%></p>
                                                            <p class="card-text"><%=list.get(i).getDoctorPhone()%></p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="advice-backside">
                                                    <div class="advice-card">
                                                        <div id="advice-card-body text-center mt-4" class="advice-card-body text-center mt-4">
                                                            <h4 class="advice-card-title"><%=list.get(i).getDoctorName()%></h4>

                                                            <p class="card-text">병원주소</p>
                                                            <p class="card-text" style='font-size:0.78em;' id="doctorAddressP"><%=list.get(i).getDoctorAddress()%></p>
                                                         <img class='img-fluid' src='<%=request.getContextPath()%>/views/adminMain2.png' >
                                                         
                                                        </div>
                                                    </div>
                                                </div>
                                              
                                            </div>
                                          
                                        </div>
                                    </div>
                                                              <%} %>
                                  </div>

                       </section>
                      <%if(loginMember!=null||loginDoctor!=null) {%>
                    <button onclick='advice1();' class="btn btn-primary mb-3" id='adivece1' style="float:right" >상담하기</button>
                    <%}else{ %>
                    <button onclick='advice2();' class="btn btn-primary mb-3" id='adivece1' style="float:right" >상담하기</button>
                    <%} %>
                  
            </div>
            
            <div class='col-md-2' id='aside'>
             
            </div>
       </div>
    </div>
</section>
<%@ include file="/views/common/footer.jsp" %>