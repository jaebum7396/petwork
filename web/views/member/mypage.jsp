<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file='/views/common/header.jsp' %>

<%@ page import="com.petwork.model.vo.Pet,java.util.*" %>

<% 
   loginMember=(Member)session.getAttribute("loginMember");
%>

<section id='myPage-section'>
<% %>   
   <div class='row'>
       <div class='col-md-2'>
      
      </div>
      <div class='col-md-8'>
         <div class="myPage-grid-container">
      
            <form name='myPForm' method='post'>
               
                <div class="myInfo-area" >
                   <div class="myPTitle-container">
                      <p class='myInfoTitle'>내 정보</p>
                   </div>
                   <div class="myP-completeBtn-container">
                        <div class="myP-modifyComplete-container">
                           <input type='button' id='myP-modifyBtn' onclick='fn_myP_modify_able_check();' class='btn btn-primary' value='정보수정'>
                           <input type='button' id='myP-modifyBtnComplete' onclick='myPFormSubmit(1);' class='btn btn-primary' value='수정완료'>  
                           <script>
                              function fn_myP_modify_able_check()
                            {
                               var modiCon=confirm("정보 수정하시겠습니까?");
                               if(modiCon==true)
                               {
                                  <%-- myPForm.action='<%=request.getContextPath()%>/myinfodeletecheckcontroller'; --%>
                                  var url="<%=request.getContextPath()%>/myinfomodifycontroller";
                                  var title="비밀번호 확인";
                                  var shape="left=200px, top=100px, width=300px, height=200px";
                                  
                                  var popup=open(url,title,shape);
                                              
                                  //popup창에서 이 폼을 작동시키게 하는 구문!
                                  /* myPForm.target=title;
                                  myPForm.action=url; */                                   
                               }
                            }                           
                           </script>
                        </div>
                        
                        <div class="myP-cancelBtn-container">
                         <input type='button' class='btn btn-primary' onclick='myPFormSubmit(2);' value='회원탈퇴'>
                      </div>
                      <script>
                         function myPFormSubmit(index)
                        {
                           if(index==1)
                           {
                              myPForm.action='<%=request.getContextPath()%>/myinfomodifycompletecontroller';
                              
                              var myOnSub=myInfo_validate();
                              
                              if(myOnSub==true)
                              {
                                 myPForm.submit();
                              }
                              
                           }
                           if(index==2)
                           {
                              var delCon=confirm("정말로 탈퇴 하시겠습니까?");
                              
                              if(delCon==true)
                              {
                                 <%-- myPForm.action='<%=request.getContextPath()%>/myinfodeletecheckcontroller'; --%>
                                 var url="<%=request.getContextPath()%>/myinfodeletecheckcontroller";
                                 var title="비밀번호 확인";
                                 var shape="left=200px, top=100px, width=300px, height=200px";
                                 
                                 var popup=open("",title,shape);
                                 
                                 
                                 //popup창에서 이 폼을 작동시키게 하는 구문!
                                 myPForm.target=title;
                                 myPForm.action=url;
                                 myPForm.submit();
                              }
                           }
                           if(index==3)
                           {
                              
                           }                           
                           
                        }
                         
                         
                      </script>
                   </div>
                   <div class="myP-head-line">
                       <div class="myP-headId-container sero-center">
                          <p class='myPage-head'>회원 ID</p>
                       </div>                        
                        <div class="myP-headPw-container sero-center">
                           <p class='myPage-head'>회원 PW</p>
                        </div>
                       <div class="myP-headName-container sero-center">
                          <p class='myPage-head'>이름</p>
                       </div>
                       <div class="myP-headBirth-container sero-center">
                          <p class='myPage-head'>생년월일</p>
                       </div>
                       <div class="myP-headGender-container sero-center">
                          <p class='myPage-head'>성별</p>   
                       </div>
                       <div class="myP-headPhone-container sero-center">
                          <p class='myPage-head'>연락처</p>
                       </div>
                      <div class="myP-headEmail-container sero-center">
                         <p class='myPage-head'>이메일</p>
                      </div>
                       <div class="myP-headAddress-container sero-center">
                          <p class='myPage-head'>주소</p>
                          
                       </div>
                   </div>
                   <div class="myP-input-line" id='myP-input-line'>
                        <div class="myP-inputId-container">
                           <input type='text' id='myP-inputId' name='myP-inputId' class='form-control myPage-input' value='<%=loginMember.getId() %>' readonly>
                        </div>
                         <div class="myP-inputPw-container">
                            <input type='password' id='myP-inputPw' name='myP-inputPw' class='form-control myPage-input' value='' placeholder='************' readonly>
                         </div>
                        <div class="myP-inputName-container">
                           <input type='text' id='myP-inputName' name='myP-inputName' class='form-control myPage-input' value='<%=loginMember.getName()%>'readonly>
                        </div>
                        <div class="myP-inputBirth-container">
                           <input type='date' id='myP-inputBirth' name='myP-inputBirth' class='form-control' value='<%=loginMember.getBirth()%>' disabled>                
                        </div>
                        <div class="myP-inputGender-container">
                           <div class='myP-mRadio-container'>
                              <input type='radio' name='myPGender' id='mGender' value='M' disabled>
                              <label for='mGender'>남</label>
                           </div>
                           <div class='myP-wRadio-container'>
                              <input type='radio' name='myPGender' id='wGender' value='F' disabled>
                              <label for='wGender'>여</label>
                           </div>
                           
                           <!-- 젠더 체크 -->
                           
                           <script>
                              
                              var genderValue='<%=loginMember.getGender()%>';
                 
                              if(genderValue=='M')
                              {
                                 $("input:radio[name='myPGender']:radio[value='M']").prop('checked', true); 
                              }
                              else
                              {
                                 $("input:radio[name='myPGender']:radio[value='F']").prop('checked', true); 
                              }                           
                           </script>
                           
                        </div>
                        <div class="myP-inputPhone-container">
                           <div class='myP-firstNum-container'>
                              <select class='form-control myPage-input myPage-sel myPage-firstNum' name='firstPhoneSel' id='firstPhoneSel' disabled>
                                 
                                 <option value='010'>
                                     010
                                  </option>
                                  <option value='011'>
                                     011
                                  </option>
                                  <option value='012'>
                                     012
                                  </option>
                                  <option value='016'>
                                     016
                                  </option>
                                  <option value='017'>
                                     017
                                  </option>
                                  <option value='018'>
                                     018
                                  </option>
                                  <option value='019'>
                                     019
                                  </option>
                                  
                              </select>
                           </div>
                           <div class='myP-secondNum-container'>
                              <input type='text' class='form-control myPage-input myPage-secondNum' name='secondPhoneInput' id='secondPhoneInput' disabled>
                           </div>
                           <div class='myP-lastNum-container'>
                              <input type='text' class='form-control myPage-input myPage-lastNum' name='lastPhoneInput' id='lastPhoneInput' disabled>
                           </div>
                        </div>
                        
                        <script>
                           
                           //폰 넘버 쪼개서 값 넣기
                           
                           var phoneNum="<%=loginMember.getPhone()%>"
                         
                           var firstNum=phoneNum.substring(0,3);
                      
                           var secondNum=phoneNum.substr(3,4);
                          
                           var lastNum=phoneNum.substr(7,4); 
                         
                           
                           $('#firstPhoneSel').val(firstNum).prop('selected',true);
                           $('#secondPhoneInput').val(secondNum);
                           $('#lastPhoneInput').val(lastNum);
                   
                           
                        </script>
                        
                        <div class="myP-inputEmail-container">
                           <div class='myP-inputEmailId-container'>
                              <input type='text' class='form-control myPage-input myPage-inputEmailId' name='myP-emailId' id='myP-emailId' disabled>
                           </div>
                           <div class='myP-inputEmailDomain-container'>
                              <select class='form-control myPage-input myPage-sel' name='myP-emailDomain' id='myP-emailDomain' disabled>
                                 <option value=''>
                                     domain.com
                                  </option>
                                  <option value='naver.com'>
                                     naver.com
                                  </option>   
                                  <option value='hanmail.net'>
                                     hanmail.net
                                  </option>
                                  <option value='gmail.com'>
                                     gmail.com
                                  </option>
                                  <option value='hanmir.com'>
                                     hanmir.com
                                  </option>
                                  <option value='nate.com'>
                                     nate.com
                                  </option>
                                  <option value='freechal.com'>
                                     freechal.com
                                  </option>
                                  <option value='kh.com'>
                                     kh.com
                                  </option>
                                  <option value='dreamwiz.com'>
                                     dreamwiz.com
                                  </option>
                              </select>
                           </div>
                        </div>
                        
                        <script>
                        
                           //이메일 쪼개서 값넣기
                           
                           var emailAddress='<%=loginMember.getEmail()%>';
                         
                           var splitEmail= emailAddress.split('@');
                         
                           $('#myP-emailId').val(splitEmail[0]);
                           $('#myP-emailDomain').val(splitEmail[1]).prop('selected',true);
                           
                        </script>
                        
                        <div class="myP-inputSearchAddress-container">
                           <input type='text' class='form-control postcodify_address myPage-postcodify' name='myP-searchAddress' id="postcodify_search_button" disabled>
                        </div>
                        <div class="myP-inputDetailAddress-container">
                           <input type='text' class='form-control' name='myP-detailAddress' id='myP-detailAddress' readonly>
                        </div>
                        
                        <script> 
                        
                           /* 어드레스 쪼개서 넣기 */
                           
                           $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); 
                           var address ='<%=loginMember.getAddress()%>';
                         
                           var splitAddress=address.split('/');
                           $('#postcodify_search_button').val(splitAddress[0]);
                           $('#myP-detailAddress').val(splitAddress[1]);
                        
                        </script>
                        
                   </div>
                   <div class="myP-check-line">
                        <div class="myP-checkPw-container"></div>
                        <div class="myP-checkEmail-container"></div>
                        <div class="myP-addressSearchBtn-container sero-center">
                           
                        </div>
                   </div>
                </div>
             </form>
          
          
             <form name='petForm' method='post'>
                
                <div class="petInfo-area">
                   
                   <div class="petTitle-container">
                      <p class='petInfoTitle'>펫 정보</p>                      
                   </div>
                   
                   <div class="pet-insertBtn-container">
                      
                   </div>
                   
                   <div class="pet-completeBtn-container ">
                      <div class='pet-modifyComplete-container'>
                           <input type='button' id='pet-modifyBtn' onclick='fn_pet_modify_able_check();' class='btn btn-primary mr-1' value='정보수정'>
                           <input type='button' id='pet-modifyBtnComplete' onclick='petFormSubmit(2)' class='btn btn-primary mr-1' value='수정완료'>
                        </div>
                        <div class="pet-deleteBtn-container">
                         <input type='button' id='pet-delBtn' onclick='petFormSubmit(3)' class='btn btn-primary' value='펫 삭제'>
                           <input type='button' id='pet-insertBtnComplete' onclick='petFormSubmit(4)' class='btn btn-primary' value='등록완료'> 
                      </div>      
                      
                   </div>
                   <div class="pet-head-line">
                       <div class="pet-headSelect-container sero-center">
                          <p class='myPage-head'>펫 선택</p>
                       </div>
                        <div class="pet-headName-container sero-center">
                           <p class='myPage-head'>이름</p>
                        </div>
                        <div class='pet-headidentfy-container sero-center'>
                           <p class='myPage-head'>인식번호</p>
                        </div>
                        <div class="pet-headRace-container sero-center">
                           <p class='myPage-head'>품종</p>
                        </div>
                        <div class="pet-headBirth-container sero-center">
                           <p class='myPage-head'>생년월일</p>
                        </div>
                        <div class="pet-headGender-container sero-center">
                           <p class='myPage-head'>성별</p>
                        </div>
                        <div class="pet-headNeutral-container sero-center">
                           <p class='myPage-head'>중성화</p>
                        </div>
                   </div>
                   <div class="pet-input-line">
                        <div class="pet-selectInput-container">
                        
                           <select id='myPetList' name='myPetSel' class='form-control' onchange='petFormSubmit(1)'>
                              
                              <option value='998' selected>선택</option>
                              
                               <option value='999'>펫 등록</option>
                               
                               <% if(request.getAttribute("petList")!=null)
                                  {
                                     List<Pet> petList=(List)request.getAttribute("petList");
                                     
                                     for(int i=0;i<petList.size();i++)
                                     {%>
                                        <option value='<%=petList.get(i).getPetNo() %>'><%=petList.get(i).getPetName() %></option>                                        
                                    <%}
                                  }
                               %>
                                                          
                           </select>
                           <%-- <%
                           List<Pet> petList=(List)request.getAttribute("petList");
                           for(int i=0;i<petList.size();i++) 
                           {%>
                              <input type='hidden' id='<%=petList.get(i).getPetIdentifyNo() %>' value=<%=petList.get(i).getPetBirth() %>>   
                          <%} %> --%>
                                                      
                           
                           <!-- 셀렉트 값 바꿔주기 -->
                           <script>
                           
                        </script>                        
                           
                        </div>                        
                        
                        <div class='pet-identifyInput-container'>                     
                           <input type='text' class='form-control' id='petIdentifyInput' name='petIdentifyInput' onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' 
                           value='' maxlength="15" readonly>
                           <script>
                              
                           </script>                    
                        </div>
                        <div class="pet-inputName-container">
                        
                           <input type='text' class='form-control' id='petInputName' name='petInputName' readonly='readonly'>
                                                      
                        </div>
                        <div class="pet-inputRace-container">
                           <input type='text' class='form-control' id='animalName' name='animalName' value='' readonly>
                           
                           <select class='form-control pet-shortSel' name='inputRaceShortSel' id='inputRaceShortSel'>
                              <option value='N'>
                                 선택   
                              </option>                              
                              <option value='D'>
                                 멍멍이
                              </option >
                              <option value='C'>
                                 고양이
                              </option>
                              <option value='E'>
                                 기타
                              </option>
                           </select>
                           
                           <script>                     
                           var raceValue=$("#inputRaceShortSel").val()
                           
                           $(function(){
                                                            
                              $("select[name=inputRaceShortSel]").on("change",function(){
                                
                                    
                                 var inputRaceShortSel=$(this).val();
                                 if(inputRaceShortSel!='N')
                                 {
                                    $.ajax({
                                       url:"<%=request.getContextPath()%>/mypageanimalselcontroller",
                                       type:"post",
                                       data: {"inputRace":inputRaceShortSel},//자바스크립트 객체!
                                       dataType:"html",
                                       success:function(data)
                                       {                                       
                                          var animalObj=data.split("/");
                                          
                                       
                                          
                                          var html="";
                                          for(var i=0;i<animalObj.length;i++)
                                          {
                                             var animalObjSplit=animalObj[i].split(" ");
                                           
                                             html+="<option value='"+animalObjSplit[0]+"'>"+animalObjSplit[1]+"</option>";
                                          }
                                          $('#inputAnimalShortSel').html(html); 
                                                                                    
                                       }
                                    })
                                 }                                 
                              })
                           })
                        </script>
                           
                           <select class='form-control pet-shortSel' name='inputAnimalShortSel' id='inputAnimalShortSel'>
                           
                           </select>
                        </div>
                        <div class="pet-inputBirth-container">
                           <input type='date' class='form-control' id='petBirth' name='petBirth' readonly>
                           
                        </div>
                        <div class="pet-inputGender-container">
                           <div class='myPet-mRadio-container'>
                              <input type='radio' name='myPetGender' id='mPetGender' value='M' disabled>
                              <label for='mPetGender'>남</label>
                           </div>
                           <div class='myPet-wRadio-container'>
                              <input type='radio' name='myPetGender' id='wPetGender' value='F' disabled>
                              <label for='wPetGender'>여</label>
                           </div>
                           
                           <!-- 펫 젠더 체크 --> 
                           <script>
                           
                           </script>
                           
                        </div>
                        
                        <!-- 중성화 체크 -->
                        <div class="pet-inputNeutral-container garo-center">
                           <input type='checkbox' id='neutralCheck' name='neutralCheck' disabled>
                           <!-- <label for='neutralCheck' id='neutralCheckLabel'></label> -->
                           <script>
                           
                           </script>
                        </div>
                        
                   </div>
                   <div class="pet-check-line">
                      <div class='pet-enrollPet-container'>
                         
                      </div>
                   </div>
                   
                  </div>
                  
               </form>
               
         </div>      
      </div>
      <div class='col-md-2'>
      
      </div>
   </div>   
</section>
<!-- 서브밋 선택 -->

<script>

	$(function(){
	    fn_setDatePickerMax();
	});
	
	function fn_setDatePickerMax(){
	    var datePicker = document.getElementById('petBirth');
	    datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
	 }
      function petFormSubmit(index)
      {
         if(index==1)
         {
            if($('#myPetList').val()=='999')
            {               
               $('#petIdentifyInput').val('');
               $("#petIdentifyInput").attr('readonly', false); 
               /* $('#petIdentifyInput').prop('required', true); */
               
               $('#petInputName').val('');
               $("#petInputName").attr('readonly', false); 
               $('#petInputName').prop('required', true);
               
               $('#petBirth').val('');
               $("#petBirth").attr('readonly', false); 
               $('#petBirth').prop('required', true);
               
               $('#animalName').val('');
               $('#animalName').css('display','none');
               
               $('myPetGender').prop('required', true);
               
               $("input:radio[name='myPetGender']:radio[value='M']").prop('checked', false);
               $("#mPetGender").attr('readonly', false);
               $("#mPetGender").attr('disabled',false);
               
               $("input:radio[name='myPetGender']:radio[value='F']").prop('checked', false);
               $("#wPetGender").attr('readonly', false);
               $("#wPetGender").attr('disabled',false);
               
               $('.pet-shortSel').prop('required', true);
               $('.pet-shortSel').css('display','inline');

               $("#neutralCheck").prop('checked', false); 
               $("#neutralCheck").attr('readonly', false);
               $("#neutralCheck").attr('disabled',false)
               
               $('#pet-modifyBtnComplete').css('display','none');
               $('#pet-modifyBtn').css('display','none');
               $('#pet-delBtn').css('display','none');
               $('#pet-insertBtnComplete').css('display','inline');
               
               $("#petIdentifyInput").css('border', 'solid 2px #594a49');
               $("#petInputName").css('border', 'solid 2px #594a49');
               $("#petBirth").css('border', 'solid 2px #594a49');
               $('.pet-shortSel').css('border', 'solid 2px #594a49');
               
               $('#petBirth').css('color', 'black');
               $('#animalName').css('color', 'black');
            }
            
            else if($('#myPetList').val()!='998')
            {
               $('#pet-modifyBtnComplete').css('display','none');
               $('#pet-modifyBtn').css('display','inline');
               $('#pet-delBtn').css('display','inline');
               $('#pet-insertBtnComplete').css('display','none');
               $('.pet-shortSel').css('display','none');
               
               $('#animalName').css('display','inline');
               $("#petIdentifyInput").attr('readonly', true); 
               $("#petInputName").attr('readonly', true); 
               $("#petBirth").attr('readonly', true); 
               $("#mPetGender").attr('readonly', true);
               $("#mPetGender").attr('disabled',true);
               $("#wPetGender").attr('readonly', true);
               $("#wPetGender").attr('disabled',true);
               
               $("#neutralCheck").attr('readonly', true);
               $("#neutralCheck").attr('disabled',true);
               
               $("#petIdentifyInput").css('border', 'solid 1px black');
               $("#petInputName").css('border', 'solid 1px black');
               $("#petBirth").css('border', 'solid 1px black');
               $('#animalName').css('border', 'solid 1px black');
               $('#petBirth').css('color', 'black');
               $('#animalName').css('color', 'black');
               
               var myPetSel=$('#myPetList').val();               
               
               $.ajax({
                  url:"<%=request.getContextPath()%>/mypetgetcontroller",
                  type:"get",
                  data: {"myPetSel":myPetSel},                  
                  success:function(data)
                  {                    
                     $('#petNo').val(data[0]['petNo']);
                     $('#petIdentifyInput').val(data[0]["petIdentifyNo"]);
                     $('#petInputName').val(data[0]["petName"]);
                     $('#animalName').val(data[0]["animalName"]);
                     $('#petBirth').val(data[0]["petBirthStringFormat"]);
                     
                     if(data[0]["petGender"]=='M')
                     {
                        $("input:radio[name='myPetGender']:radio[value='M']").prop('checked', true);
                     }
                     else
                     {
                        $("input:radio[name='myPetGender']:radio[value='W']").prop('checked', true);
                     }
                     if(data[0]["petNeutering"]=='Y')
                     {
                        $('#neutralCheck').prop('checked',true);
                     }
                     else
                     {
                        $('#neutralCheck').prop('checked',false);
                     }
                  }
               })
            }
         }
         if(index==2)
         {
           var petIdentifyInput=$('#petIdentifyInput').val();
           var chk_eng = petIdentifyInput.search(/[a-z]/ig);
           
           if(chk_eng<0&&$('#petIdentifyInput').val().trim().length==0||chk_eng<0&&$('#petIdentifyInput').val().trim().length==15)
            {
              petForm.action='<%=request.getContextPath()%>/mypetmodifycompletecontroller';
                petForm.submit();
            }
           else
             {
                alert("숫자 15자를 입력하세요.");
                $('#petIdentifyInput').focus();
             }
         }
         if(index==3)
         {
            var petDelCon=confirm("정말로 삭제하시겠습니까?")
            
            if(petDelCon==true)
            {
               petForm.action='<%=request.getContextPath()%>/mypetdeletecontroller';
               petForm.submit();
            }
         }
         if(index==4)
         {
            var petIdentifyInput=$('#petIdentifyInput').val();
           
            var chk_eng = petIdentifyInput.search(/[a-z]/ig);
         
           
            if($('#petInputName').val().trim().length==0)
            {
               alert("펫 이름을 입력해주세요");
               $('#petInputName').focus();
            }            
           else if(chk_eng>0 || $('#petIdentifyInput').val().trim().length > 0 && $('#petIdentifyInput').val().trim().length!=15)
            {
           
                alert("숫자 15자를 입력하세요.");
                $('#petIdentifyInput').focus();
             }            
            else if($('#inputRaceShortSel').val()=='N')
            {
               alert("동물 분류 카테고리를 선택해주세요.");
               $('#inputRaceShortSel').focus();
            }
            else if($('#petBirth').val().trim().length==0)
            {
               alert("생년 월일을 선택해주세요.");
               $('#petBirth').focus();
            }
            else if($('input:radio[name=myPetGender]').is(':checked')==false)
            {
               alert("성별을 입력하세요");
            }
            /* else if(&()) */
            else
            {
               petForm.action='<%=request.getContextPath()%>/mypetinsertcontroller';
               petForm.submit(); 
               
            }
            
         }      
      }
      
      function fn_myP_modify_able()
      {
         $('#myP-modifyBtn').css('display','none');
         $('#myP-modifyBtnComplete').css('display','inline');
         $('#myP-inputBirth').css('display','inline');
         $('#myP-viewBirth').css('display','none');
         
         /* $("#myP-inputPw").focus(); */
         $('#myP-inputName').attr('readonly', false);
         $('#myP-inputName').css('border', 'solid 2px #594a49');
         $("#myP-inputPw").attr('readonly', false);    
         
         $(".myP-inputPhone-container").find('*').attr('readonly', false);
         $(".myP-inputEmail-container").find('*').attr('disabled', false);
         
         $(".myP-inputSearchAddress-container").find('*').attr('readonly', false);
         $(".myP-inputDetailAddress-container").find('*').attr('readonly', false);
         
         $("#myP-inputPw").css('border', 'solid 2px #594a49'); 
         $('.myPage-firstNum').css('border', 'solid 2px #594a49');
         $(".myP-inputPhone-container").find('input').css('border', 'solid 2px #594a49');
         
         $(".myP-inputPhone-container").find('input').attr('disabled', false);
         $(".myP-inputPhone-container").find('select').attr('disabled', false);
         
         $(".myP-inputEmail-container").find('input').css('border', 'solid 2px #594a49');
         $(".myP-inputEmail-container").find('select').css('border', 'solid 2px #594a49');
         
         $('#postcodify_search_button').css('border', 'solid 2px #594a49');
         $('#postcodify_search_button').attr('disabled',false)
         
         $(".myP-inputDetailAddress-container").find('input').css('border', 'solid 2px #594a49');
         
         $('#myP-inputId').css('border', 'solid 1px white');
         $('#myP-inputBirth').css('border', 'solid 1px white');
         
         $('#myP-inputId').css('color', '#d2e3e1');
         $('#myP-inputBirth').css('color', '#d2e3e1');         
      };
      
      function fn_pet_modify_able_check()
      {      
         $('#petIdentifyInput').css('border', 'solid 2px #594a49');
         $("#petIdentifyInput").attr('readonly', false);          
         
         $("#petInputName").attr('readonly', false); 
         $('#petInputName').css('border', 'solid 2px #594a49');
         
         $('#petBirth').css('color', '#d2e3e1');
         $('#animalName').css('color', '#d2e3e1');
         
         /* $('#petBirth').css('border', 'solid 2px #594a49');
         $("#petBirth").attr('readonly', false);  */
         
         /* $('#animalName').css('border','solid 2px #594a49');    */      
         
         /* $("#mPetGender").attr('readonly', false);      
         $("#wPetGender").attr('readonly', false); */
         
         $('.neutralCheck').css('border', 'solid 2px #594a49');
         $("#neutralCheck").attr('disabled', false);
         /* $("#neutralCheckLabel").css('border', 'solid 2px #594a49'); */         
         
         $('#pet-modifyBtn').css('display','none');
         $('#pet-modifyBtnComplete').css('display','inline');
      }
      
      function myP_member_del(msg)
      {
         /* confirm(msg); */
         
         window.location='index.jsp';  
      }
      
      function myInfo_validate()
      {                     
         var modifyPw=$("#myP-inputPw").val().trim();
         
         if($('#myP-inputPw').val().trim().length!==0)
         {
            if(!/^[a-zA-Z0-9]{8,20}$/.test(modifyPw))
             { 
                 alert('비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.'); 
                 $('#myP-inputPw').focus();
                 return false;
             }
             var chk_num = modifyPw.search(/[0-9]/g); 
             var chk_eng = modifyPw.search(/[a-z]/ig);
         
             if(chk_num < 0 || chk_eng < 0)   
             { 
                 alert('비밀번호는 숫자와 영문자를 혼용하여야 합니다.');
                 $('myP-inputPw').focus();
                 return false;
             }             
         }
         
          if($('#secondPhoneInput').val().trim().length==0)
         {
            alert("두번째 번호를 입력해주세요");
            $('#secondPhoneInput').focus();
            return false;
         }
         if($('#secondPhoneInput').val().trim().length>4)
         {
            alert("두번째 번호가 너무 깁니다.");
            $('#secondPhoneInput').focus();
            return false;
         }
         if($('#lastPhoneInput').val().trim().length==0)
         {
            alert("세번째 번호를 입력해주세요");
            $('#lastPhoneInput').focus();
            return false;
         }
         if($('#lastPhoneInput').val().trim().length>4)
         {
            alert("세번째 번호가 너무 깁니다.");
            $('#lastPhoneInput').focus();
            return false;
         }         
         return true;
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

      /* 
      function myP_alert()
      {
         alert();
      } */      
      
</script>   

<%@ include file='/views/common/footer.jsp' %>