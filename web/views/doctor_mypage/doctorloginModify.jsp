<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.*,com.petwork.model.vo.Doctor" %>
  <%Doctor d=(Doctor)session.getAttribute("loginDoctor"); %>
<%@ include file="/views/common/header.jsp" %>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<!-- 첨부파일 바뀌었을때! -->
<script>
$('#doctorModify-img').change(function(e){
	var fileName=$('input[type=file]')[0].files[0].name;
	$('#doctorModify-img').val(fileName);
});





</script>
<section>
<div class='container-fluid' id="contents">
	
        <div class='row'>
            <div class='col-md-2' id='nav'>
            
			</div>
            <div class='col-md-8' id='section' >
            	<div class="row">
                    <div class="col-md-12">
                        <div class="clickdoctor-heading">				
                             <img src="https://image.ibb.co/cbCMvA/logo.png" />
                        </div>
                    </div>	
                </div>
                <div class="clickdoctor-bio-info">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="clickdoctor-bio-image">
                                     <img src="<%=request.getContextPath()%>/views/upload/doctor/<%=d.getDoctorImg() %>" alt="image" />
                                    </div>			
                                </div>
                            </div>	
                        </div>
                        <div class="col-md-9">
                        <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/doctor/modifyEnd" enctype="multipart/form-data" role="form" onsubmit="return check()">
                   			<input type="hidden" name="doctorNo" value="<%=d.getDoctorNo()%>">
                            <div class="form-group row" >
                                <label for="doctorModify-license" class="col-md-4 control-label">의사등록번호</label>
                                <div class="col-md-8">
                                    <input type="text" name="doctorModify-license" id="doctorModify-license" class="form-control" value=<%=d.getDoctorLicense() %> readonly>
                                </div>
                                
                            </div>
                            <div class="form-group row" >
                                <label for="doctorModify-Name" class="col-md-4 control-label">이름</label>
                                <div class="col-md-8">
                                    <input type="text" id="doctorModify-name" name="doctorModify-name" class="form-control" required value=<%=d.getDoctorName() %>>
                                </div>
                               
                            </div>
                            <div class="form-group row">
                                <label for="doctorModify-hospital" class="col-md-4 control-label">병원이름</label>
                                <div class="col-md-8">
                                    <input type="text" id="doctorModify-hospital" name="doctorModify-hospital"  class="form-control" value="<%=d.getDoctorHospital() %>"required>
                                </div>
                              
                            </div>
                            <div class="form-group row">
                                <label for="doctorModify-address" class="col-md-4 control-label">병원주소</label>
                                <div class="col-md-8">
                                    <input type="text" id="doctorModify-address" name="doctorModify-address" class="form-control postcodify_address" value="<%=d.getDoctorAddress()%>" required />
                                  
                                 </div>
                             </div>
                              <div class="form-group row">   
                                <div class="col-sm-7"></div>
        						<div class="col-md-5" style="margin-top:0;">
        						  <button type="button" id="postcodify_search_button" class="btn btn-primary postcodify_address" >검색</button>
        						</div>
                            </div>
                          <div class="form-group row">
                                <label for="doctorClick-phone" class="col-md-4 control-label">전화번호</label>
                                <div class="col-md-8">
                                    <div class='row'>
                                        <div class="col-md-4">
                                        <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone1" id="doctorClick-phone1"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(0,2) %>" required>
                                              <%} else{%>
                                              <input type="text" name="doctorClick-phone1" id="doctorClick-phone1"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(0,3) %>" required>
                                              <%} %>
                                        </div>
                                        
                                        <div class='col-md-4'>
                                          <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone2" id="doctorClick-phone2"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(3,7) %>" required>
                                                 <%} else{%>
                                                 <input type="text" name="doctorClick-phone2" id="doctorClick-phone2"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(4,8) %>" required>
                                                 <%} %>
                                        </div>
                                        
                                        <div class='col-md-4'>
                                         <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone3" id="doctorClick-phone3"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(8,12) %>" required>
                                         <%} else{%>
                                               <input type="text" name="doctorClick-phone3" id="doctorClick-phone3"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(9,13) %>" required>
                                         <%} %>
                                        </div>
                                    </div>
                                </div>
                               
                            </div>
                            <div class="form-group row">
                                <label for="doctorModify-img" class="col-md-4 control-label">사진</label>
                                <div class="col-sm-8">
                                    <span>현재 이미지 : <%=d.getDoctorImg()%></span>
                                    <input type="file" id="doctorModify-img"  name="doctorModify-img" class="form-control" >
                                    <input type="hidden" id="doctorOriName" name="doctorOriName" value=<%=d.getDoctorImg() %> class="form-control">
                                </div>

        							
                            </div>
                          
                           <div class="form-group row">
                               
                                <div class="col-sm-7"></div>
        						<div class="col-md-5">
        						<button type="submit" id="doctor-modify-btn" class="btn btn-primary" style="float:right">수정완료</button>
        						</div>
                            </div>
                            
                        </form> <!-- /form -->
                    </div>
                    </div>	
                </div>
                      
            </div>
                        <div class='col-md-2' id='nav'>
            
			</div>
            </div>
           
        </div>
         <script>
    function check(){
        var addressValue = $('#doctorModify-address').val().substr(0,2);
         
        var firstPhone=$('#doctorClick-phone1').val();
        var secondPhone=$('#doctorClick-phone2').val();
        var thirdPhone=$('#doctorClick-phone3').val();
        
        if(addressValue!="서울"&&firstPhone.length!=3)
           {
             alert("지역번호 자릿수를 맞춰주세요");
              $('#doctorClick-phone1').focus();
              return false;
           }
        
        else if(addressValue=="서울"&&firstPhone.length!=2)
           {
              alert("지역번호 자릿수를 맞춰주세요");
              $('#doctorClick-phone1').focus();
              return false;
           }
        else
           {
              if(secondPhone.length!=4)
              {
                 alert("전화번호 자릿수를 맞춰주세요");
                 $('#doctorClick-phone2').focus();
                 return false;
              }else
                 {
                    if(thirdPhone.length!=4)
                    {
                       alert("전화번호 자릿수를 맞춰주세요");
                       $('#doctorClick-phone3').focus();
                       return false;
                    }
                    else
                    {
                       return true;
                    }
                 }
           }    
    }
   </script>
    </section>
<%@ include file="/views/common/footer.jsp" %>