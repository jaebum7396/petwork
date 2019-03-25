
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckPop</title>

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
	.idFindInput
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
	
	#findIdBtn
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
		<form name="contact-form" action="<%=request.getContextPath()%>/myidfindcontroller" method="POST">
	    <%if(request.getAttribute("msg")==null) 
	      {%>
		    <div id='inputId-container'>
		    	<p>가입하신 휴대폰 번호를 입력하세요.</p>
				<input type='text' id='inputPhone' name='inputPhone' class='idFindInput form-control'/>
			</div>
	    <%}%> 
		    <div id="checkMail-container">
		    	
				<%if(request.getAttribute("msg")!=null) 
				  {
				  	  String msg=(String)request.getAttribute("msg");
				  	  if(request.getAttribute("msgId")!=null)
				  	  {
				   %>				   		
					  	<p id='checkResult' style='color:#b8cecc;'><%=msg %></p>
				  <%  }
					  else
					  {%>
					  	<div id='inputId-container'>
					    	<p>가입하신 휴대폰 번호를 입력하세요.</p>
							<input type='text' id='inputPhone' name='inputPhone' class='idFindInput form-control'/>
						</div>					    
					  	<p id='checkResult' style='color:red;'><%=msg %></p>
				    <%}%>
				<%}
				  else
				  {
					String msg="휴대폰 번호는 - 없이 번호만 입력하세요.";
				 %>
				  	<p id='checkResult' style='color:#b8cecc;'><%=msg %></p>
				<%}%>	
		    	
		    	<%if(request.getAttribute("msgId")==null) 
		    	  {%>			
			    	<input type='submit' id='findIdBtn' class='btn btn-primary' value='확인'>
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
			</div>		
		</form>
	</div>	  
				
  	<script>
		
		function self_close()
		{
			self.close();
		}
	</script>
	
</body>
</html>