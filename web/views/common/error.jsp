<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/views/common/error.jsp"%>
<!DOCTYPE html>
<html lang="ko">

<title>PETWORK - ERROR</title>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<head>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<style>

body {
	background-color: #e9ecef;
	background-attachment: scroll;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	line-height: 5px;
}

.display-1 {
	text-align: center;
	color: #e1b7b7;
}

.display-1 .fa {
	animation: fa-spin 5s infinite linear;
}

.display-3 {
	text-align: center;
	color: #df726a;
}

.lower-case {
	text-align: center;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>

<script>
	function fn_returnHome(){
		location.href = "<%=request.getContextPath() %>/index.jsp";
	}

</script>

<body>
	<div class="wrapper">
		<div class="container-fluid" id="body-container-fluid">
			<div class="container">
				<div class="jumbotron text-center">
					<h1 class="display-1">
						<i class="fa fa-spin fa-cog fa-2x"></i>
					</h1>
					<h1 class="display-3">ERROR</h1>
					<input type="button" value="return Homepage" onclick="fn_returnHome();" class="btn btn-danger">
					<p class="lower-case mt-4">sorry, we are working hard to resolve it.</p>
				</div>

			</div>
		</div>
	</div>
</body>