<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.*,com.petwork.model.vo.Doctor" %>
  
  <%Doctor d =(Doctor)session.getAttribute("loginDoctor"); %>
  
<%@ include file="/views/common/header.jsp" %>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
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
                    <div class="row" >
                        <div class="col-md-3">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="clickdoctor-bio-image">
                                   
                                        <img src="<%=request.getContextPath()%>/views/upload/doctor/<%=d.getDoctorImg() %>" alt="image" />
                                        
                                    </div>			
                                </div>
                            </div>	
                        </div>
                        <div class="col-md-9" id="doctorClickM">
            
                   			
                            <div class="form-group row" >
                            	<input type="hidden" name="doctorNo" value="<%=d.getDoctorNo()%>">
                                <label for="doctorClick-license" class="col-md-4 control-label">의사등록번호</label>
                                <div class="col-md-8">
                                    <input type="text" name="doctorClick-license" id="doctorClick-license" class="form-control" value="<%=d.getDoctorLicense()%>" readonly=readonly >
                                </div>
                                
                            </div>
                            <div class="form-group row" >
                                <label for="doctorClick-Name" class="col-md-4 control-label">이름</label>
                                <div class="col-md-8">
                                    <input type="text" id="doctorClick-name" name="doctorClick-name" class="form-control" value="<%=d.getDoctorName()%>" readonly=readonly >
                                </div>
                               
                            </div>
                            <div class="form-group row">
                                <label for="doctorClick-hospital" class="col-md-4 control-label">병원이름</label>
                                <div class="col-md-8">
                                    <input type="text" id="doctorClick-hospital" name="doctorClick-hospital"  class="form-control" value="<%=d.getDoctorHospital()%>" readonly=readonly>
                                </div>
                              
                            </div>
                            <div class="form-group row">
                                <label for="doctorClick-address" class="col-md-4 control-label">병원주소</label>
                                <div class="col-md-8">
                                    <input type="text" name="doctorClick-address" class="form-control postcodify_address" value="<%=d.getDoctorAddress()%>" readonly=readonly />
                                  
                                 </div>
                             </div>
               
                              <div class="form-group row">
                                <label for="doctorClick-phone" class="col-md-4 control-label">전화번호</label>
                                <div class="col-md-8">
                                    <div class='row'>
                                        <div class="col-md-4">
                                        <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone1" id="doctorClick-phone1"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(0,2) %>" readonly=readonly>
                                              <%} else{%>
                                              <input type="text" name="doctorClick-phone1" id="doctorClick-phone1"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(0,3) %>" readonly=readonly>
                                              <%} %>
                                        </div>
                                        
                                        <div class='col-md-4'>
                                          <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone2" id="doctorClick-phone2"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(3,7) %>" readonly=readonly >
                                                 <%} else{%>
                                                 <input type="text" name="doctorClick-phone2" id="doctorClick-phone2"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(4,8) %>" readonly=readonly>
                                                 <%} %>
                                        </div>
                                        
                                        <div class='col-md-4'>
                                         <%if(d.getDoctorAddress().substring(0,2).equals("서울")) {%>
                                               <input type="text" name="doctorClick-phone3" id="doctorClick-phone3"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(8,12) %>" readonly=readonly>
                                         <%} else{%>
                                               <input type="text" name="doctorClick-phone3" id="doctorClick-phone3"  class="form-control doc-shortT" value="<%=d.getDoctorPhone().substring(9,13) %>" readonly=readonly>
                                         <%} %>
                                        </div>
                                    </div>
                                </div>
                               
                            </div>
                            
                          
                         
        						<button type="button" id="doctor-Modify-btn" class="btn btn-primary" onclick="fn_update()" style="float:right">수정</button>
								<button type="button" id="doctor-delete-btn" class="btn btn-primary mr-1" onclick="fn_delete()" style="float:right">탈퇴</button>        					
                            </div>
                            <script>
                          function fn_update(){
		
							location.href="<%=request.getContextPath()%>/doctor/modify";
							}
                          function fn_delete()
                          {
                        	  var delconfirm=confirm("정말로 탈퇴하시겠습니까?");
                        	  if(delconfirm)
                        		  {
                        			location.href='<%=request.getContextPath()%>/doctor/deleteDoctor';
                        		  }
                        	  else{
                        		  return false;
                        	  }
                        	  
                        	  
                          }
                          
                          </script>
                      
                    </div>
                    </div>	
                </div>
            <div class='col-md-2' id='aside'>
                
            </div>  
            </div>
                      
            </div>
           
    
      
    </section>
<%@ include file="/views/common/footer.jsp" %>