
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file='/views/common/header.jsp' %>

<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>

<script>

function validate()
{
	if($('#inputId').val().trim().length==0)
	{
		alert("아이디를 입력하세요!");
		$('#inputId').focus();
		
		return false;	
	}
	if($('#inputPw').val().trim().length==0)
	{
		alert("패스워드를 입력하세요!");
		$('#inputPw').focus();
		return false;
	}
	
	return true;
}


</script> 

<style>
	
	.searchIn
	{
		display: inline;
	}
</style>
	
<section >
	
        <div class="container-fluid">
            <div class='row'>
                <div class="col-md-2" id='login-nav'>
                 
                </div>                
                <div class="col-md-8 j-contents">
                	
                    <form action="<%=request.getContextPath()%>/login" method='post' onsubmit="return validate();">
                    	<div class="login-grid-container">
			                <div class="login-head-line">
			                    <div class="login-head-container">
			                        <div class="login-headId-container sero-center">
			                            <p class='login-head'>
			                                회원ID
			                            </p>               
			                        </div>
			                        <div class="login-headPw-container sero-center">
			                            <p class='login-head'>
			                                회원PW
			                            </p>
			                        </div>
			                    </div>
			                </div>
			                <div class="login-input-line">
			                    <div class="login-infoSearch-container garo-center sero-center">
			                    	<div class='btn btn-primary' id='searchBtn'>	
				                    	<a class='searchIn' onclick='idCheckPop();'>아이디 찾기</a>
				                    	/
				                        <a class='searchIn' onclick='emailCheckPop();'> 비밀번호 찾기</a>
			                        </div>
			                    </div>
			                    <script>
			                    	function idCheckPop()
			                    	{
			                    		var popup=open("<%=request.getContextPath()%>/myidcheckpopcontroller","idCheckPop","left=200px, top=100px, width=450px, height=250px");
			                    	}
			                    	function emailCheckPop()
			                    	{
			                    		var popup=open("<%=request.getContextPath()%>/myemailsendpopcontroller","emailSendPop","left=200px, top=100px, width=450px, height=250px");
			                    	}
			                    </script>
			                    <div class="login-input-container">
			                        <div class="login-inputId-container">
			                            <input type='text' name='inputId' id='inputId' class='form-control longT-join sero-center' placeholder=" petwork1">
			                        </div>
			                        <div class="login-inputPw-container">
			                            <input type='password' name='inputPw' id='inputPw' class='form-control longT-join sero-center' placeholder=" ***********">
			                        </div>
			                    </div>
			                    <div class="login-title-container garo-center sero-center">
			                        <p class='login-title'>로그인</p>
			                    </div>
			                </div>
			                <div class="login-check-line">
			                    <div class="login-loginBtn-container garo-center sero-center">
			                        <input type='submit' class='btn btn-primary' id='loginBtn' value='Login'>
			                    </div>
			                    <div class="login-joinBtn-container garo-center sero-center">
			                        <a href='signuppage.jsp' class='btn btn-primary' id='joinBtn'>회원가입</a>
			                    </div>
			                </div>
			            </div>	                
                   	</form>	
                </div>
                <div class="col-md-2">
                
                </div>            
            </div>          
        </div>   

</section>



<%@ include file='/views/common/footer.jsp' %>
