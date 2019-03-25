<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.petwork.model.vo.Member"%>
<%@ page import="com.petwork.model.vo.Doctor"%>       
<% 
   Member loginMember=null;
   loginMember=(Member)session.getAttribute("loginMember");
   
   Doctor loginDoctor=null;
   loginDoctor=(Doctor)session.getAttribute("loginDoctor");  	
	
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    
    <title>Petwork</title>
    
  
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
	           
	    <!-- 관리자 네비 -->
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin_navbar.css">
	    
    <!-- 개별 페이지 스타일 --> 
      
    	<!-- 메인 페이지 -->       
         
		    <!-- 인덱스 스타일 -->
		    <link rel='stylesheet' href="<%=request.getContextPath()%>/css/indexPage/IndexStyle.css">
		    
		    <!-- 인덱스 애니메이션 -->
		    <script src="<%=request.getContextPath()%>/js/indexPage/indexPage.js"></script>
		    <script type="text/javascript" src="<%=request.getContextPath()%>/js/indexPage/owl.carousel.min.js"></script>  
	 
	    
	    <!-- 로그인 페이지 -->
	    
		    <!-- 로그인 그리드 -->
		    <link rel='stylesheet' href="<%=request.getContextPath()%>/css/loginPage/loginGrid.css">
		    
		    <!-- 로그인 스타일 -->
		    <link rel='stylesheet' href="<%=request.getContextPath()%>/css/loginPage/loginStyle.css">         
	    
	    <!-- 회원가입 페이지 -->     
	    
	    	<link rel='stylesheet' href="<%=request.getContextPath()%>/css/signUpPage/signUpStyle.css"/>
	    
	     	<!-- 회원가입 API -->
	     	<!-- 주소API -->
		    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	 
	 	<!-- 마이 페이지 -->
	 	
	 		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/myPage/myPageStyle.css"/> 
       	
  		<!-- 분양 페이지 -->
  		
  			<link rel="stylesheet" href="<%=request.getContextPath()%>/css/parcel/bootstrap_parcel_main.css">
	        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/parcel/bootstrap_parcel_detail.css">
	        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/parcel/bootstrap_parcel_write.css">
	        
        <!-- 의사 페이지 -->
        
        	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/doctorShow/doctorshow.css">
	        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/doctorRegist/doctorRegist.css">
	        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/advice/advice.css">
	        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/doctorClick/clickdoctor.css">
       	<!-- 자유 나눔 게시판 -->
             <link rel="stylesheet" href="<%=request.getContextPath() %>/css/css_board/admin-free-post.css">
         <link rel="stylesheet" href="<%=request.getContextPath() %>/css/css_board/admin-share-post.css">
      
      <!-- 상세페이지 -->   
         <link rel="stylesheet" href="<%=request.getContextPath() %>/css/css_board/admin-post-detail.css">
      <!-- 글 등록 페이지 -->
         <link rel="stylesheet" href="<%=request.getContextPath() %>/css/css_board/post-detail-write.css">
     
		   
</head>

<body>    
    <header>
        
          <div class='container-fluid'>
              <div class='row'>
                  <div class='col-md-3'>
                      <a href="<%=request.getContextPath()%>/index.jsp"><img src='<%=request.getContextPath()%>/views/adminMain2.png' class="ml-4" id='logo'></a>            
                  </div>
                  <div class='col-md-9 header-contents'>
                      <div class='row'>
                      <div class='col-md-12 relative-nav-nav'>
                          <div class='header-login-nav'>                            
                          	<%if(loginMember==null&&loginDoctor==null) 
                              {%>
                              <ul class='header-login'>   
                                 <li><a href='<%=request.getContextPath()%>/views/member/loginpage.jsp'>|Login|</a>&nbsp</li>
                                 <li><a href='<%=request.getContextPath()%>/views/member/signuppage.jsp'>|Join|</a></li>
                              </ul>  
                            
                            <%}
                          	
                              if(loginMember==null&&loginDoctor!=null)
                              {%>
                              <ul class='header-login'>
                                 <li><a href='<%=request.getContextPath()%>/views/doctor_mypage/doctorMypage.jsp'>|MyPage|</a>&nbsp;</li>
                               	 <li><a href='<%=request.getContextPath()%>/logoutcontroller'>|Logout|</a></li>
                              </ul> 
                            <%}
                              
                              if(loginMember!=null&&loginDoctor==null)
                              { 
                              	 if(loginMember.getAdminYN()=='Y')
	                             {%>
	                             <ul class='header-login'>
	                                 <li><a href='<%=request.getContextPath()%>/intoAdminmain'>|AdminPage|</a>&nbsp</li>
	                               	 <li><a href='<%=request.getContextPath()%>/logoutcontroller'>|Logout|</a></li>
	                             </ul>
	                           <%}
                              	 else
                              	 {%>
                              	 <ul class='header-login'>
                                 	<li><a href='<%=request.getContextPath()%>/mypagecontroller'>|Mypage|</a>&nbsp</li>
                              	 	<li><a href='<%=request.getContextPath()%>/logoutcontroller'>|Logout|</a></li>
                             	</ul>
	                          
                               <%}
                              }%>                          
                                                      
                          <ul id='header-relative-nav'>
                             <li><a href=#>community</a>
                                <ul>
                                   <li>
                                      <a href=>자유게시판</a>            
                                   </li>
                                   <li>
                                      <a href=>분양게시판</a>
                                   </li>
                                   <li>
                                   	  <a href=#>나눔게시판</a>
                                   </li>
                                </ul>
                               </li>
                             <li><a href=#>missing</a>
                                <ul>
                                   <li>
                                      <a href=#>찾아주세요</a>
                                   </li>
                                   <li>
                                      <a href=#>데려가세요</a>
                                   </li>
                                </ul>
                               </li>
                             <li><a href='<%=request.getContextPath()%>/MapServlet'>facility</a></li>
                             <li><a href=#>contact</a></li>
                             <li><a href=#>advice</a></li>
                            </ul>
                            
                          </div>
                      </div>
                      <div class='col-md-12 '>
                          <div class='nav-container'>
                             
                              <ul class="header-nav">
                                  <li class="header-nav-item active">
                                      <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">HOME</a>
                                  </li>
                                  <li class="header-nav-item">
                                      <a class="nav-link" href='#'>COMMUNITY</a>
                                      <ul class=listFather>
                                          <li class=downList>
                                              <a href="<%=request.getContextPath()%>/board/freeBoardList">
                                                     	자유게시판
                                              </a>
                                            
                                          </li>
                                          <li class=downList>
                                              <a href="<%=request.getContextPath()%>/parcelMain">
                                                 &nbsp; 분양게시판
                                              </a>
                                              
                                          </li>
                                          <li class=downList>
                                              <a href="<%=request.getContextPath()%>/board/sharingBoardList">
                                                 &nbsp; 나눔게시판
                                              </a>
                                              
                                          </li>
                                      </ul>
                                  </li>
                                  <li class="header-nav-item">
                                      <a class="nav-link" href='#'>MISSING PET</a>
                                      <ul class=listFather>
                                          <li class=downList>
                                          <%if(loginMember!=null && loginMember.getAdminYN()=='Y'){ %>
                                            <a href="<%=request.getContextPath()%>/missingPet/findPostList?adminMode=true">찾아주세요!</a>
                                           <%}else{%>
                                            <a href="<%=request.getContextPath()%>/missingPet/findPostList">찾아주세요!</a>
                                           <%} %>
                                          </li>
                                          <li class=downList>
                                             <%if(loginMember!=null && loginMember.getAdminYN()=='Y'){ %>
                                              <a href="<%=request.getContextPath()%>/missingPet/protectPostList?adminMode=true">&nbsp; 데려가세요!</a>
                                                 <%}else{ %>
                                               <a href="<%=request.getContextPath()%>/missingPet/protectPostList">&nbsp; 데려가세요!</a>
                                                 <%} %>
                                          </li>
                                      </ul>
                                  </li>
                                  <li class="header-nav-item">
                                      <a class="nav-link" href='<%=request.getContextPath()%>/MapServlet'>PET FACILITY</a>
                                  </li>
                                  <li class="header-nav-item">
                                    
                                       <a class="nav-link" href='<%=request.getContextPath()%>/doctor/adviceMain'>ADVICE</a>
                                  </li>
                                  <li class="header-nav-item">
                                    <a class="nav-link" href='<%=request.getContextPath()%>/faq/faqList'>CONTACT</a> 
                                  </li>
                              </ul> 
                          </div>                         
                      </div>
                  </div>
              </div>            
          </div>
          </div>    
       
    </header>