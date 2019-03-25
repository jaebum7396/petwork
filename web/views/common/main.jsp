<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*,com.petwork.model.vo.*' %>
<%
	List<Slider> sliderList=(List)request.getAttribute("sliderList");
	List<FreePost> freePostList=(List)request.getAttribute("freeList");
	List<Parcel> parcelPostList=(List)request.getAttribute("parcelList");
	List<SharingPost> sharePostList=(List)request.getAttribute("sharingList");
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
           
    <!-- 관리자 네비 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin_navbar.css">
    
<!-- 개별 페이지 스타일 --> 
     
    <!-- 메인 페이지 -->       
        
	    <!-- 인덱스 스타일 -->
	    <link rel='stylesheet' href="<%=request.getContextPath()%>/css/indexPage/IndexStyle.css">
	    
	    <!-- 인덱스 애니메이션 -->
	    <script src="js/indexPage/indexPage.js"></script>
	    <script type="text/javascript" src="js/indexPage/owl.carousel.min.js"></script>  
 
    
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
	 						
		<div class="index-slider">
            <div class="index-slider-container">
                <p class="index-slider-header">유기동물 정보</p>
                <div class="row">
                    <div class="col-md-12">
                        <div id="news-slider5" class="owl-carousel">
                        <%for(int i=0; i<sliderList.size(); i++) 
                          {%>
                            <div class="index-post-slide5" id='index-post-slide5'>
								<div class="index-post-img">
									<a href='<%=request.getContextPath()%>/missingPet/findPostView?no=<%=sliderList.get(i).getFindPostNo()%>'>
										<img src='<%=request.getContextPath()%>/views/upload/missingPet/<%=sliderList.get(i).getFindPostImageAddress()%>' alt="">
									</a>
								</div>
								<div class="index-post-review">
								    <div class="index-post-title">
										<a href="<%=request.getContextPath()%>/missingPet/findPostView?no=<%=sliderList.get(i).getFindPostNo() %>" class="index-post-title-link">
										   	<%=sliderList.get(i).getFindPostTitle()%>
										</a>
								    </div>
								    <div class="index-post-description">
								        <p>날짜 : <%=sliderList.get(i).getFindPostMissingDate()%></p><br><br>
								        <p>지역 : <%=sliderList.get(i).getFindPostMissingAddress()%></p><br><br>
								        <p>사례금 : 
								        	<%if(sliderList.get(i).getFindPostReward()=='Y'){ %>
								        		<%=sliderList.get(i).getFindPostSum()%>만원
							        		<%}else if(sliderList.get(i).getFindPostReward()=='C'){ %>
								        		찾은 후 협의
							        		<%}else{ %>
								        		없음
							        		<%} %>
								        </p>
								   </div>
								</div>									
	                        </div>
	                    <%} %>
	                    </div>
	                </div>
	            </div>
	        </div>
        </div>
        <!-- 게시판 신규 글 안내 부분 -->
        <div class="index-board">
            <section id="index-tabs" class="index-board-tab">
                <div class="index-container">
                    <div class="row">
                        <div class="col-md-12">
                            <nav>
                                <div class="nav index-nav-tabs nav-fill" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active" id="nav-petSale-tab" data-toggle="tab" href="#nav-petSale"
                                        role="tab" aria-controls="nav-petSale" aria-selected="true">분양</a>
                                    <a class="nav-item nav-link" id="nav-share-tab" data-toggle="tab" href="#nav-share"
                                        role="tab" aria-controls="nav-share" aria-selected="false">나눔</a>
                                    <a class="nav-item nav-link" id="nav-free-tab" data-toggle="tab" href="#nav-free"
                                        role="tab" aria-controls="nav-free" aria-selected="false">자유</a>
                                </div>
                            </nav>
                            <div class="tab-content" id="nav-tabContent">
                                <!-- 분양 게시판 -->
                                <div class="tab-pane fade show active" id="nav-petSale" role="tabpanel" aria-labelledby="nav-petSale-tab">
                                    <table class="index-table" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th class="index-title">제목</th>
                                                <th class="index-date">작성일</th>
                                                <th class="index-writer">작성자</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for(int i=0;i<parcelPostList.size();i++) 
                                           {%>
                                            <tr>
                                                <td class="index-title">
	                                                <a href="<%=request.getContextPath()%>/parcel/parcelDetailView?postNo=<%=parcelPostList.get(i).getPostNo() %>">
	                                                	<%=parcelPostList.get(i).getTitle()%>
	                                                </a>
                                                </td>
                                                <td class="index-date"><%=parcelPostList.get(i).getPostDate()%></td>
                                                <td class="index-writer"><%=parcelPostList.get(i).getId() %></td>
                                            </tr>
                                         <% }%>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- 판매 게시판 -->
                                
                                <!-- 나눔 게시판 -->
                                <div class="tab-pane fade" id="nav-share" role="tabpanel" aria-labelledby="nav-share-tab"><!-- 셰어 게시판(수정 해야함) -->
                                    <table class="index-table" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th class="index-title">제목</th>
                                                <th class="index-date">작성일</th>
                                                <th class="index-writer">작성자</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for(int i=0;i<sharePostList.size();i++) 
                                           {%>
                                            <tr>
                                                <td class="index-title">
                                                	<a href="<%=request.getContextPath()%>/board/sharingBoardForm?sharingPostNo=<%=sharePostList.get(i).getSharingPostNo() %>">
                                                		<%=sharePostList.get(i).getSharingPostTitle()%>
                                               		</a>
                                             	</td>
                                                <td class="index-date"><%=sharePostList.get(i).getSharingPostDate()%></td>
                                                <td class="index-writer"><%=sharePostList.get(i).getSharingPostWriter() %></td>
                                            </tr>
                                         <% }%>  
                                        </tbody>
                                    </table>
                                </div>
                                <!-- 자유 게시판 -->
                                <div class="tab-pane fade" id="nav-free" role="tabpanel" aria-labelledby="nav-free-tab">
                                    <table class="index-table" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th class="index-title">제목</th>
                                                <th class="index-date">작성일</th>
                                                <th class="index-writer">작성자</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <% for(int i=0;i<freePostList.size();i++) 
                                           {%>
                                            <tr>
                                                <td class="index-title">
	                                                <a href="<%=request.getContextPath()%>/board/freeBoardForm?freePostNo=<%=freePostList.get(i).getFreePostNo() %>">
	                                                	<%=freePostList.get(i).getFreePostTitle()%>
	                                                </a>
                                                </td>
                                                <td class="index-date"><%=freePostList.get(i).getFreePostDate()%></td>
                                                <td class="index-writer"><%=freePostList.get(i).getFreePostWriter() %></td>
                                            </tr>
                                        <% }%>    
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>                    
    </div>