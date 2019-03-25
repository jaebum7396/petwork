<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 확인</title>

	<!-- 기본 적용 -->
       
	    <!-- jQuery library -->
	    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
	    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
	    
	    <!-- Latest compiled and minified CSS -->
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css"/>
	    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	    
	    <!-- Latest compiled JavaScript -->
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	    
	    <!-- 기본 공통 스타일 -->
	    <link rel='stylesheet' href="<%=request.getContextPath()%>/css/general.css"/>
</head>
<style>

	/* .emailBody
	{
		background-color:#e0ede9;
	} */
	#checkMail-container 
	{
		margin-top:2em;
		text-align:center;		
	}
	#checkMail-container p
	{
		font-weight: 600;
		color: #516055;
	}
	#inputId-container
	{
		margin-top: 2em;
		text-align:center;
	}
	#inputId-container p
	{
		font-weight: 600;
		color: #516055;
	}
	.emailFindInput
	{
		padding-left:0;
		padding-right:0;
		width: 90%;
		margin-left: auto;
		margin-right:auto;
	}
	.passcodeSubmit
	{
		margin-top:3.8em;
	}
	.newPasswordSubmit
	{
		margin-top:3.8em;
	}
	.closeBtn
	{
		margin-top:3.8em;
	}
	
	#sendMailBtn
	{
		width: 25%;
		margin-top:2em;
	}
	
	#passcodeSubmit
	{
		display:none;
	}
	
	#inputNewPassword
	{
		display:none;
	}
	#timer
	{
		font-weight:600;
		font-color:#e0ede9;
	}
</style>
<body class='emailBody'>
	<div class='checkMail-container'>
	<input type='hidden' value=''>
	<%if(request.getAttribute("checkedM")==null)
	  {%>
		<form name="contact-form" action="<%=request.getContextPath()%>/myemailsendcodecontroller" method="POST">
		    <div id='inputId-container'>
		    	<p>비밀번호를 찾으실 아이디를 입력하세요.</p>
				<input type='text' id='inputId' name='inputId' class='emailFindInput form-control'/>
			</div>
		    
		    <div id="checkMail-container">
		    	
				<%if(request.getAttribute("msg")!=null) 
				  {
				  	String msg=(String)request.getAttribute("msg");
				  	String findId=(String)request.getAttribute("findId");	
			  	 %>
					<p id='checkResult' style='color:red;'><%=findId%><%=msg %></p>
				<%} %>	
		    				
			   <input type='submit' id='sendMailBtn' class='btn btn-primary' value='확인'>			
			</div>
		</form>
	<%} 
	  else if(request.getAttribute("checkedM")!=null)
	  {
	%>	<form name="contact-form" onsubmit='return fn_override_pass();' action="<%=request.getContextPath()%>/mypasswordoverridecontroller" method="POST">
		    <div id='inputId-container'>
		    <%if(request.getAttribute("msg")!=null) 
			  {
		    	String msg=(String)request.getAttribute("msg");
		    	
		    	if(request.getAttribute("findId")!=null&&request.getAttribute("random6")!=null)
		    	{
			    	String findId=(String)request.getAttribute("findId");
			    	int random6=(int)request.getAttribute("random6");
				  	
			    }%>  		

				<p id='checkResult' style='color:green;'><%=msg %></p>
			<%}
			  if(request.getAttribute("result")==null)
			  {%>		    	
				<input type='text' id='inputPasscode' name='inputPasscode' class='emailFindInput form-control'/>
				<input type='password' id='inputNewPassword' name='inputNewPassword' class='emailFindInput form-control'/>
				<input type='button' onclick='fn_check_passcode();' id='passcodeCheck' class='btn btn-primary passcodeSubmit' value='인증코드 확인'>
				<input type='submit'  id='passcodeSubmit' class='btn btn-primary newPasswordSubmit' value='비밀번호 재설정' disabled='disabled'>
				<input type='hidden' name='modifyTargetId' value='<%=(String)request.getAttribute("findId")%>'>
			<%}
			  else
			  {%>
			  	<script>
			  		
			  		setTimeout(function(){self.close();},10000);
			  		
			  		var count = 10;
			  		var counter = setInterval(timer,1000);
			  		function timer()
			  		{
			  			count--;
			  			if(count<=0)
		  				{
			  				clearInterval(counter);
			  				
			  				document.getElementById('timer').innerHTML="";
			  				return
		  				}
			  			document.getElementById('timer').innerHTML=count;
			  		}
			  	</script>
			  	<div id='timer-container' class='timer-container'>
			  		<p>본 팝업창은 <span id='timer'></span> 초 뒤 자동으로 닫힙니다</p>
			  	</div>			  	  
			  	<input type='button' class='btn btn-primary closeBtn' onclick='self_close()' value="닫기">
			<%}%>  	  
				<script>
				<%if(request.getAttribute("result")==null)
				 {%>
					function fn_check_passcode()
					{
						if($('#inputPasscode').val()!=<%=(int)request.getAttribute("random6")%>)
						{	var random=<%=(int)request.getAttribute("random6")%>
							
						
							$('#checkResult').css('color','red');
							$('#checkResult').text("인증코드가 다릅니다.")
						}
						else
						{
							$('#checkResult').css('color','green');
							$('#checkResult').text("새 비밀번호 입력.");
							$('#inputPasscode').css('display','none');//인증코드 확인 인풋 사라짐
							$('#inputPasscode').attr('disabled',true);//인증코드 확인 인풋 비활성화
							$('#inputNewPassword').css('display','inline');//비밀번호 재설정 인풋 활성화
							$('#passcodeCheck').css('display','none');//인증코드확인 버튼 사라짐
							$('#passcodeCheck').attr('disabled',true);//인증코드확인 버튼 비활성화
							$('#passcodeSubmit').css('display','inline');//비밀번호 재설정 버튼 활성화
							$('#passcodeSubmit').attr('disabled', false);
						}												
					}
					
					function fn_override_pass()
					{
						var overridePw=$("#inputNewPassword").val().trim();
					
						
						if(!/^[a-zA-Z0-9]{8,20}$/.test(overridePw))
					    { 
							$('#checkResult').text('비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.'); 
							$('#checkResult').css('color','red');
					        $('#inputNewPassword').focus();
					        return false;
				        }
						
					    var chk_num = overridePw.search(/[0-9]/g); 
					    var chk_eng = overridePw.search(/[a-z]/ig);

					    if(chk_num < 0 || chk_eng < 0)
					    { 
					    	$('#checkResult').text('비밀번호는 숫자와 영문자를 혼용하여야 합니다.');
					    	$('#checkResult').css('color','red');
					        $('#inputNewPassword').focus();
					   		return false;
					    }					    
					    return true;					    
					}
			   <%}%>	
					
					function self_close()
					{
						self.close();
					}
				</script>
			</div>
		</form>				    
	<%} %>	
	</div>

</body>
</html>