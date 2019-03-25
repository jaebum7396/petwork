<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.petwork.model.vo.*" %>
    <%List<Pet> petList=(List)request.getAttribute("petList"); %>
    <%String pageBar=(String)request.getAttribute("pageBar"); %>
<%@ include file="/views/common/header.jsp" %>

<style>
	div#search-petName
	{
		display:inline-block;
	}
	div#search-petId
	{
		display:none;
	}
	div#search-petDel
	{
		display:none;
	}
	#delPetCheck
	{
		display:inline;
		width: 1em;
		height: 1em;
	}
	
	
</style>
<script>
window.onload=function(){
	var sname=document.querySelector("#search-petName");
	var saddress=document.querySelector("#search-petId");
	var sdelyn=document.querySelector('#search-petDel');
	
	var searchType=document.querySelector("#searchType"); 
    searchType.addEventListener("change",function(){ 
		sname.style.display="none";
		saddress.style.display="none";
		sdelyn.style.display="none";
		
		document.querySelector("#search-"+this.value).style.display="inline-block"; 
	});
}
</script>
<section>
	<div class="container-fluid row" id="contents">
		<div class='col-md-2' id='nav'>
		    <div class="nav-side-menu">
		        <div class="brand">PETWORK</div>
		        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
		        <div class="menu-list">
		            <ul id="menu-content" class="menu-content collapse out">
		                <li>
		                    <a href="<%=request.getContextPath()%>/indexcontroller"> HOME </a>
		                </li>
		                <li>
		                    <a href="<%= request.getContextPath()%>/adminmemberlistcontroller"> 회원관리 </a>
		            
		                </li>
		                <li>
		                    <a href="<%= request.getContextPath()%>/adminpetlistcontroller"> 동물관리 </a>
		                </li>
		                  <li data-toggle="collapse" data-target="#doctor" class="collapsed">
		                    <a href="#"> 수의사 관리 ▼ </a>
		                </li>
		                <ul class="sub-menu collapse" id="doctor">
		                    <li><a href="<%=request.getContextPath()%>/views/doctor_regist/doctorRegist.jsp">수의사 등록</a></li>
		                     <li><a href="<%=request.getContextPath()%>/doctor/doctorShow">수의사 명단</a></li>
		                </ul>
		                <li data-toggle="collapse" data-target="#board" class="collapsed">
		                    <a href="#"> 게시판 관리 ▼ </a>
		                </li>
		                <ul class="sub-menu collapse" id="board">
		                    <li><a href="<%=request.getContextPath()%>/board/freeBoardList">자유게시판</a></li>
		                    <li><a href="<%=request.getContextPath()%>/board/sharingBoardList">나눔게시판</a></li>
		                    <li><a href="<%=request.getContextPath()%>/parcelMain">분양게시판</a></li>
		                </ul>
						<li data-toggle="collapse" data-target="#missing"
							class="collapsed"><a href="#"> 실종/보호 관리 ▼ </a></li>
						<ul class="sub-menu collapse" id="missing">
							<li><a
								href="<%=request.getContextPath()%>/missingPet/findPostList?adminMode=true">찾아주세요</a></li>
							<li><a
								href="<%=request.getContextPath()%>/missingPet/protectPostList?adminMode=true">보호중입니다</a></li>
						</ul>
						<li data-toggle="collapse" data-target="#advice" class="collapsed">
		                    <a href="#"><span>문의 관리 ▼ </span></a>
		                </li>
		                <ul class="sub-menu collapse" id="advice">
		                    <li><a href="<%=request.getContextPath()%>/qna/qnaList">QnA</a></li>
		                    <li><a href="<%=request.getContextPath()%>/faq/faqList">FAQ</a></li>
		                </ul>
		            </ul>
		        </div>
		    </div>
		</div>
	
	    <div class='col-md-10' id='section'>
	        <table id="example" class="table table-striped table-bordered">
	    		<form name='delFrm' action='<%=request.getContextPath()%>/adminpetdeletecontroller'>
	                 <thead>
	                     <tr>
	             			 <th></th>
	                         <th>주인아이디</th>
	                         <th>이름</th>
	                         <th>인식번호</th>
	                         <th>품종</th>
	                         <th>생년월일</th>
	                         <th>성별</th>
	                         <th>삭제여부</th>
	                     </tr>
	                 </thead>
	                 <tbody>
	                 <script>
	                 
	                 </script>
	                 <%for(Pet p : petList){ %>
	                 <tr>
	              	 	<td><input type='checkbox' name='delCheck' value='<%=p.getPetNo()%>'></td>	 
                       	<td><%=p.getMemberId()%></td>
                       	<td><%=p.getPetName()%></td>
                       	<td><%=p.getPetIdentifyNo() %></td>
                       	<td><%=p.getAnimalName()%></td>
                       	<td><%=p.getPetBirth() %></td>
                       	<td><%=p.getPetGender()%></td>
                       	<td>
	                        <%if(p.getPetYn()=='N') 
	                   		  {%>
	                       		 <p>&nbsp;아니오 </p>
	                   	    <%}
	                          else
	                  	      {%>
	                       	     <p>예</p>
	                  	    <%}%>
	               	    </td>
	                 </tr>
	                 <%} %>  
	                   
	                 </tbody>
	  			 </form>
            </table>
            <!-- 글머리 서치 부분 -->
            <!--검색기능 추가 -->
	        <div class="row">
               <div class="col-md-12 text-center">
                  <div id="search-container">
                     <select id="searchType" class="btn btn-primary">
                        <option value="petName">펫 이름</option>
                        <option value="petId">주인 아이디</option>
                        <option value="petDel">삭제여부</option>
                      
                     </select>
                     <div id="search-petName">
                        <form action="<%=request.getContextPath()%>/adminpetfindercontroller">
                           <input type="hidden" name="searchType" value="petName" /> 
                           <input type="text" name="searchKeyword" size="25"
                              placeholder="펫 이름으로 검색" />
                           <button class="btn btn-primary" type="submit">검색</button>
                           <input type='button' class='btn btn-primary' onclick='deleteMember();' value='삭제'>
                        </form>
                     </div>
                     <div id="search-petId">
                        <form action="<%=request.getContextPath()%>/adminpetfindercontroller">
                           <input type="hidden" name="searchType" value="petId" /> <input
                              type="text" name="searchKeyword" size="25"
                              placeholder="주인 아이디로 검색" />
                           <button class="btn btn-primary" type="submit">검색</button>
                           <input type='button' class='btn btn-primary' onclick='deleteMember();' value='삭제'>
                        </form>
                     </div>
                     <div id="search-petDel">
                        <form name='ynSearchFrm' action="<%=request.getContextPath()%>/adminpetfindercontroller">
                           <input type="hidden" name="searchType" value="petDel" /> 
                           <input type='hidden' name="searchKeyword" id='searchKeyword'/>
                           &nbsp;
                           <label for='radioCheckYn'>삭제펫</label><input type="radio" name="radioCheckYn" value='Y' size="25" onclick='delCheckYn()' />&nbsp;
                           <label for='radioCheckYn'>현재펫</label><input type="radio" name="radioCheckYn" value='N' size="25" onclick='delCheckYn()' />&nbsp;                           
                           <input type='button' class='btn btn-primary' onclick='deleteMember();' value='삭제'>
                        </form>
                     </div>
                     
					 <script>					 	
					 	function deleteMember()
					 	{
					 		var delCon=confirm("정말로 삭제하시겠습니까?");
					 		if(delCon)
				 			{
					 			delFrm.submit();
				 			}					 	
					 	}
					 	
					 	function delCheckYn()
					 	{
					 		 var radioYn = $('input[name="radioCheckYn"]:checked').val();
					 		 $('#searchKeyword').val(radioYn);
					 		 ynSearchFrm.submit();
					 	}
					 </script>	
                  </div>
               </div>
            </div>
            <div style="text-align:center">
            <div class="pagination" >
               <%=pageBar%>
            </div>
            </div>
         </div>        
     </div>
</section>
<%@ include file="/views/common/footer.jsp" %>