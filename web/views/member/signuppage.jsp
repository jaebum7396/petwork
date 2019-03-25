



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file='/views/common/header.jsp' %>

<script>   


$(function(){
    fn_setDatePickerMax();
});

function fn_setDatePickerMax(){
    var datePicker = document.getElementById('joinBirth');
    datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
 }
 
function fn_enroll_validate()
{
   if($('input[name=idValid]')[0].value=='0')
   {
      alert('아이디 중복체크를 해주세요');
      return false;   
   }
   
   if($('#joinId').val().trim().length==0)
   {
      alert("아이디를 입력하세요!");
      $('#joinId').focus();
      
      return false;   
   }
   
   
   if($('#joinPw').val().trim().length==0)
   {
      alert("패스워드를 입력하세요!");
      $('#joinPw').focus();
      return false;
   }
   
   var joinPw=$("#joinPw").val().trim();
   
   if(!/^[a-zA-Z0-9]{8,20}$/.test(joinPw))
    { 
        alert('비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.'); 
        $('#joinPw').focus();
        return false;
    }
   
    var chk_num = joinPw.search(/[0-9]/g); 
    var chk_eng = joinPw.search(/[a-z]/ig);

    if(chk_num < 0 || chk_eng < 0)

    { 
        alert('비밀번호는 숫자와 영문자를 혼용하여야 합니다.');
        $('#joinPw').focus();
        return false;
    }    
   
   if($('#joinName').val().trim().length==0)
   {
      alert("이름을 입력하세요");
      $('#joinName').focus();
      return false;
   }
   
   if($('#joinBirth').val().trim().length==0)
   {
      alert("생년월일을 입력하세요");
      $('#joinBirth').focus();
      return false;
   }
   
   if($('input:radio[name=joinGender]').is(':checked')==false)
   {
      alert("성별을 입력하세요");
      return false;
   }
   
   if($('#addSearch').val().trim().length==0)
   {
      alert("주소를 검색해주세요");
      $('#postcodify_search_button').focus();
      return false;
   }
   
   if($('#addDetail').val().trim().length==0)
   {
      alert("상세주소를 입력해주세요");
      $('#addDetail').focus();
      return false;
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
   
   if($('#joinEmailId').val().trim().length==0)
   {
      alert("메일 아이디를 입력해주세요");
      $('#joinEmailId').focus();
      return false;
   }
   var f=document.signupform;
   
   if(f.joinEmailDomain.value=='')
   {
      alert("이메일 도메인을 선택해주세요");
      $('#joinEmailDomain').focus();
      return false;
   }   
   
   return true;
};

function fn_signUp_regularExpression()
{
   
}
      
</script>

<section>
   <div class='container-fluid' id='signup-container'>
      <div class='row j-contents'>
         <div class='col-md-2'>
         
         </div>
         <div class='col-md-8'>
            
            <form name='signupform' action='<%=request.getContextPath()%>/signup' method='post' onsubmit='return fn_enroll_validate()'>
            
               <div class="signUp-grid-container" id='join-contents'>
                  <div class="join-head-line">
                      <div class="join-idHead-container sero-center">
                           <p class='join-head'>회원 ID</p>
                      </div>
                      <div class="join-pwHead-container sero-center">
                         <p class='join-head'>회원 PW</p>
                      </div>
                      <div class="join-nameHead-container sero-center">
                         <p class='join-head'>이름</p>
                      </div>
                      <div class="join-birthHead-container sero-center">
                         <p class='join-head'>생년월일</p>
                      </div>
                      <div class="join-genderHead-container sero-center">
                         <p class='join-head'>성별</p>
                      </div>
                      <div class="join-phoneHead-container sero-center">
                         <p class='join-head'>연락처</p>
                      </div>
                      <div class="join-emailHead-container sero-center">
                         <p class='join-head'>이메일</p>
                      </div>
                      <div class="join-addressHead-container sero-center">
                         <p class='join-head'>주소</p>
                      </div>
                    </div>
                  <div class="join-input-line">
                      <div class="join-title-container">
                         <p class='signUpTitle'>회원가입</p>
                      </div>
                      <div class="join-idInput-container">                         
                         <input type="text" id='joinId' name='joinId' class='form-control' value='' placeholder="아이디 입력">
                         <input type='hidden' name='idValid' value='0'>
                      </div>
                      <div class="join-pwInput-container">
                         <input type="password" id='joinPw' name='joinPw' class='form-control' placeholder='패스워드 입력'>
                      </div>
                      <div class="join-nameInput-container">
                         <input type="text" id='joinName' name='joinName' class='form-control' placeholder='이름 입력'>
                      </div>
                      <div class="join-birthInput-container">
                         <input type="date" id='joinBirth' name='joinBirth' class='form-control'>
                      </div>
                      <div class="join-genderInput-container">
                         <div class="join-mRadio-container">
                              <input type="radio" id='mRadio' name='joinGender' value='M' required>
                              <label for='mRadio'>남</label>
                         </div>
                         <div class="join-wRadio-container">
                              <input type="radio" id='wRadio' name='joinGender' value='F' required>
                              <label for='wRadio'>여</label>
                         </div>
                      </div>
                      <div class="join-phoneInput-container">
                            <div class="join-firstPhone-container">
                              <select class='form-control' id='firstPhoneInput' name='firstPhone'>
                                 <option value='010' selected>
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
                         <div class="join-secondPhone-container">
                              <input type="text" class='form-control' id='secondPhoneInput' name='secondPhone' required>   
                         </div>
                          <div class="join-lastPhone-container">
                              <input type="text" class='form-control' id='lastPhoneInput' name='lastPhone' required>   
                          </div>
                        </div>
                      <div class="join-emailInput-container">
                          <div class="join-emailIdInput-container">
                               <input type='text' class='form-control join-middle-input' id='joinEmailId' name='joinEmailId' placeholder='Email id' required>
                          </div>
                           <div class="join-emailDomainInput-container">
                               <select class='form-control' id='joinEmailDomain' name='joinEmailDomain' required>
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
                               </select>
                          </div>
                      </div>
                      <div class="join-addressSearch-container">
                            <input type='text' class='form-control join-long-input postcodify_address' id="postcodify_search_button" name='addSearch' placeholder='입력 하시려면 클릭하세요' required>                                                
                        </div>
                      <div class="join-addressDetail-container">
                         <input type='text' class='form-control postcodify_details' id="postcodify_search_button" name='addDetail' placeholder='상세 주소를 입력하세요' required>
                      </div>
                      <div class="join-complete-container">
                          <div class="join-signUpBtn-container">
                              <input type='submit' class='btn btn-primary' value='회원가입'>
                          </div>
                          <div class="join-cancelBtn-container">
                             <input type='reset' class='btn btn-primary' value='가입취소'>              
                          </div>
                      </div>
                    </div>
                    
                    <div class="join-check-line">
                      <div class="join-duplicateCheckBtn-container">
                         <input type='button' onclick='fn_checkduplicate();' class='btn btn-primary' value='중복체크'>
                      </div>
                      <div class="join-constaintText-container sero-center garo-center">
                         <p class='join-constraintText'>8자 이상, 영문+숫자 조합</p>
                      </div>
                      <div class='join-addressCheckBtn-container'>
                         <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
                      </div>
                    </div>
                 </form>
            </div>   
         <div class='col-md-2'></div>
      </div>
      <form action="" name="checkIdDuplicateFrm">
         <input type="hidden" name="joinId"/>
      </form>
   </div>
</section>

<script>

<%-- 
function validationSubmit(index)
{
   if(index==1)
   {
      signupform.action='<%=request.getContextPath()%>/signup';
   }
   if(index==2)
   {
      signupform.action='<%=request.getContextPath()%>/duplicateCheck';
   }
   signupform.submit();
   
} --%>

//아이디 중복검사하기 
function fn_checkduplicate()
{
   var joinId=$("#joinId").val().trim();
   
   if(!joinId || joinId.length<8)
   {
      alert("아이디를 8글자 이상 입력하세요~!");
      return;   
      
   }
   
   if(!/^[a-zA-Z0-9]{8,20}$/.test(joinId))
    { 
        alert('아이디는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.'); 
        $('#joinId').focus();
        return false;
    }
   
   //팝업창에 대한 설정해주기
   var url="<%=request.getContextPath()%>/duplicateCheck";
   var title="duplicateCheck";
   var shape="left=200px, top=100px, width=300px, height=200px";
   
   var popup=open("",title,shape);
   
   //현재페이지에 있는값을 새창으로 옮기는 작업~!
   checkIdDuplicateFrm.joinId.value=joinId;
   //popup창에서 이 폼을 작동시키게 하는 구문!
   checkIdDuplicateFrm.target=title;
   checkIdDuplicateFrm.action=url;
   checkIdDuplicateFrm.method="post";
   checkIdDuplicateFrm.submit();      
   
   
   //window.open(url,"명칭/여는방식",shape)   
}


</script>

<%@ include file='/views/common/footer.jsp' %>
<!-- 
$(function(){
    fn_setDatePickerMax();
});

function fn_setDatePickerMax(){
    var datePicker = document.getElementById('joinBirth');
    datePicker.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
 } -->