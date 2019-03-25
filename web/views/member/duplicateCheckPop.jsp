<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//true면 사용할 수 있다. false면 사용할 수 없다.
	boolean isAble=(boolean)request.getAttribute("isAble");
	String checkedId=(String)request.getAttribute("joinId");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
	#checkId-container 
	{
		margin-top:3em;
		text-align:center;
	}
	
</style>

<body>
	<div id="checkId-container">
		<%if(isAble) 
		  { %>
			<p style="color:green;"><span style="color:#516055">[<%=checkedId %>]</span>는 사용가능합니다.</p>
			<br><br>
			<button class='btn btn-primary' type="button" onclick="setUserId('<%=checkedId%>');">닫기</button>
		<%}
		  else 
		  {
		%>
			<p style="color:red;"><span style="color:#516055">[<%=checkedId %>]</span>는 사용할 수 없습니다.</p>
			<br><br>
			<button class='btn btn-primary' type="button" onclick="setUserId(1);">닫기</button>
			
		<%} %>
	</div>
	<script>
		
		function setUserId(checkedId)
		{
			var frm=opener.document.signupform;//부모창을 호출
			
			if(checkedId==1)
			{
				frm.joinId.value='';
				frm.idValid.value='0';
				frm.joinId.focus();
			}
			else
			{
				frm.joinId.value=checkedId;
				frm.idValid.value='1';
				frm.joinPw.focus();				
			}
			
			self.close();//현재 열려있는 창을 닫는 것	
		}
		
		
	</script>

</body>
</html>