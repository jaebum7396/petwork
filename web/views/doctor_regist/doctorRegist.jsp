<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<section>
   <div class="container-fluid row" id="contents">
      <div class='col-md-2' id='nav'>
          <div class="nav-side-menu">
              <div class="brand">PETWORK</div>
              <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
              <div class="menu-list">
                  <ul id="menu-content" class="menu-content collapse out">
                      <li>
                          <a href="<%=request.getContextPath()%>/intoAdminmain"> HOME </a>
                      </li>
                      <li>
                          <a href="<%= request.getContextPath()%>/adminmemberlistcontroller"> 회원관리 </a>
                  
                      </li>
                      <li>
                          <a href="<%= request.getContextPath()%>/adminpetlistcontroller"> 동물관리 </a>
                      </li>
                        <li data-toggle="collapse" data-target="#doctor" class="collapsed">
                          <a href="#"> 수의사 관리 ▼ </a>
                      </li>
                      <ul class="sub-menu collapse" id="doctor">
                          <li><a href="<%=request.getContextPath()%>/views/doctor_regist/doctorRegist.jsp">수의사 등록</a></li>
                           <li><a href="<%=request.getContextPath()%>/doctor/doctorShow">수의사 명단</a></li>
                      </ul>
                      <li data-toggle="collapse" data-target="#board" class="collapsed">
                          <a href="#"> 게시판 관리 ▼ </a>
                      </li>
                      <ul class="sub-menu collapse" id="board">
                          <li><a href="<%=request.getContextPath()%>/board/freeBoardList">자유게시판</a></li>
                          <li><a href="<%=request.getContextPath()%>/board/sharingBoardList">나눔게시판</a></li>
                          <li><a href="<%=request.getContextPath()%>/parcelMain">분양게시판</a></li>
                      </ul>
                  <li data-toggle="collapse" data-target="#missing"
                     class="collapsed"><a href="#"> 실종/보호 관리 ▼ </a></li>
                  <ul class="sub-menu collapse" id="missing">
                     <li><a
                        href="<%=request.getContextPath()%>/missingPet/findPostList?adminMode=true">찾아주세요</a></li>
                     <li><a
                        href="<%=request.getContextPath()%>/missingPet/protectPostList?adminMode=true">보호중입니다</a></li>
                  </ul>
                  <li data-toggle="collapse" data-target="#advice" class="collapsed">
                          <a href="#"><span>문의 관리 ▼ </span></a>
                      </li>
                      <ul class="sub-menu collapse" id="advice">
                          <li><a href="<%=request.getContextPath()%>/faq/faqList">FAQ</a></li>
                      </ul>
                  </ul>
              </div>
          </div>
      </div>
      <!-- enctype="multipart/form-data" -->
            <div class='col-md-10' id='section'>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/admin/doctorRegist" enctype="multipart/form-data" onsubmit="return check()">
                    <h2 style="text-align:center;margin-bottom:10%;margin-top:2%;">수의사등록</h2>
                     <div class="form-group row" >
                        <label for="doctorRegist-Id" class="col-md-3 control-label">의사ID</label>
                        <div class="col-md-7">
                            <input type="text" name="doctorRegist-Id" id="doctorRegist-Id" class="form-control" required >
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="form-group row" >
                        <label for="doctorRegist-password" class="col-md-3 control-label">의사비밀번호</label>
                        <div class="col-md-7">
                            <input type="text" name="doctorRegist-password" id="doctorRegist-password" class="form-control" required >
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="form-group row" >
                        <label for="doctorRegist-license" class="col-md-3 control-label">의사등록번호</label>
                        <div class="col-md-7">
                            <input type="text" name="doctorRegist-license" id="doctorRegist-license" placeholder="ex)docdce1" class="form-control" required>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="form-group row" >
                        <label for="doctorRegist-Name" class="col-md-3 control-label">이름</label>
                        <div class="col-md-7">
                            <input type="text" id="doctorRegist-name" name="doctorRegist-name" placeholder="ex)홍길동" class="form-control" required >
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="form-group row">
                        <label for="doctorRegist-hospital" class="col-md-3 control-label">병원이름</label>
                        <div class="col-md-7">
                            <input type="text" id="doctorRegist-hospital" name="doctorRegist-hospital" placeholder="00동물병원" class="form-control" required>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="form-group row">
                        <label for="doctorRegist-address" class="col-md-3 control-label">병원주소</label>
                        <div class="col-md-7">
                            <input type="text" id="doctorRegist-address" name="doctorRegist-address" class="form-control postcodify_address"  value="" required/>
             
                       </div>
                       <div class="col-md-2">
                            <button type="button" id="postcodify_search_button" class="btn btn-primary postcodify_address"  >검색</button>
                        </div>
                        
                    </div>
                    <div class="form-group row">
                        <label for="doctorRegist-phone" class="col-md-3 control-label">전화번호</label>
                        <div class="col-md-5">
                           <div class='row'>
                              <div class="col-md-4">
                    
                                    <input type="text" name="doctorRegist-phone1" id="doctorRegist-phone1" placeholder="02" class="form-control doc-shortT" required>
                                      
                              </div>
                              
                              <div class='col-md-4'>
                                    <input type="text" name="doctorRegist-phone2" id="doctorRegist-phone2" placeholder="1234" class="form-control doc-shortT" required>
                              </div>
                              
                              <div class='col-md-4'>
                                    <input type="text" name="doctorRegist-phone3" id="doctorRegist-phone3" placeholder="1234" class="form-control doc-shortT" required>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                    <div class="form-group row">
                        <label for="doctorRegist-img" class="col-md-3 control-label">사진</label>
                        <div class="col-sm-7">
                            <input type="file" id="doctorRegist-img" name="doctorRegist-img" class="form-control" >
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                  
                    <button type="submit" id="doctor-regist-btn" class="btn btn-primary">등록완료</button>
                </form> <!-- /form -->
                <!-- 섹션끝 -->     
            </div> 
          
        </div>
    
<script>
    function check(){
        var addressValue = $('#doctorRegist-address').val().substr(0,2);
         
        var firstPhone=$('#doctorRegist-phone1').val();
        var secondPhone=$('#doctorRegist-phone2').val();
        var thirdPhone=$('#doctorRegist-phone3').val();
       
        if(addressValue=="서울"&&firstPhone.length!=2)
           {
              alert("지역번호 자릿수를 맞춰주세요");
              $('#doctorRegist-phone1').focus();
              return false;
           }
        else
           {
              if(secondPhone.length!=4)
              {
                 alert("전화번호 자릿수를 맞춰주세요");
                 $('#doctorRegist-phone2').focus();
                 return false;
              }else
                 {
                    if(thirdPhone.length!=4)
                    {
                       alert("전화번호 자릿수를 맞춰주세요");
                       $('#doctorRegist-phone3').focus();
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