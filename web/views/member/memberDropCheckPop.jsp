<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ page import="com.petwork.model.vo.Member,java.util.*" %>
<% 
	Member loginMember=(Member)session.getAttribute("loginMember");
	
	/* if(request.getAttribute("checkPw")!=null)
	{
		boolean	checkPw=(boolean)request.getAttribute("checkPw");
	} */
	
	String msg=null;
	if(request.getAttribute("msg")!=null)
	{
		msg=(String)request.getAttribute("msg");
	}
%>
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
	body
	{
		/* background-color:#E0ede9; */
	}
	#checkPw-container 
	{
		margin-top:3em;
		text-align:center !important;
	}
	#checkPassword
	{
		width: 60% !important;
		margin-left:3.8em;
		margin-bottom:2em;
	}
	
</style>

<body>
	<form action='<%=request.getContextPath()%>/myinfodeletecompletecontroller'>
		<div id="checkPw-container">			
			<!-- <input type='hidden' name=''> -->
			<input type='password' name='checkPassword' id='checkPassword' placeholder="비밀번호를 입력하세요" class='form-control'>
			<div id='errorMSG'></div>
			<input type='submit' class='btn btn-primary' value='확인'>
			
		</div>
	</form>
	<script>
	<%if(request.getAttribute("checkPw")!=null)
	  {%>
	  <% boolean checkPw=(boolean)request.getAttribute("checkPw");%>
		$(document).ready(function()
		{			
			if(<%=checkPw%>==true)
			{
				var msg='<%=msg%>';
				
				self.close();
				
				parent.opener.myP_member_del();
				
			}
			else
			{
				$('#errorMSG').append("<p style='color:red;'>비밀번호가 틀립니다.</p>");
			}
		});
	<%}%>
		
		
	</script>
</body>
</html>