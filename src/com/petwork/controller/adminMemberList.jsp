<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.petwork.model.vo.*" %>
    <%List<Member> memberList=(List)request.getAttribute("memberList"); %>
    <%String pageBar=(String)request.getAttribute("pageBar"); %>
<%@ include file="/views/common/header.jsp" %>

<style>
	div#search-memberName
	{
		display:inline-block;
	}
	div#search-memberId
	{
		display:none;
	}
	div#search-memberDel
	{
		display:none;
	}
	.ynColumn
	{
		min-width: 5.5em;
	}
	.nameColumn
	{
		min-width: 5em;
	}
	.entColumn
	{
		min-width: 8em;
	}
	
	
</style>
<script>
window.onload=function(){
	var sname=document.querySelector("#search-memberName");
	var saddress=document.querySelector("#search-memberId");
	var sdelyn=document.querySelector('#search-memberDel');
	
	var searchType=document.querySelector("#searchType"); 
	searchType.addEventListener("change",function(){ 
		sname.style.display="none";
		saddress.style.display="none";
		sdelyn.style.display='none';
		
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
             	 <form name='delFrm' action='<%=request.getContextPath()%>/adminmemberdeletecontroller'>
	                 <thead>
	                     <tr>
	             			 <th></th>
	                         <th>아이디</th>
	                         <th class='nameColumn'>이름</th>
	                         <th>연락처</th>
	                         <th>이메일</th>
	                         <th class='ynColumn'>탈퇴여부</th>
	                         <th class='entColumn'>가입일</th>
	                     </tr>
	                 </thead>
	                 <tbody>
	                 <%for(Member m : memberList){ %>
	                   <tr>
	              	 	   <td><input type='checkbox' name='delCheck' value='<%=m.getId()%>'></td>	 
	                       <td><%=m.getId()%></td>
	                       <td><%=m.getName()%></td>
	                       <td><%=m.getPhone() %></td>
	                       <td><%=m.getEmail()%></td>
	                       <td>
	                       <%if(m.getMemberYN()=='N') 
	                   		 {%>
	                       		 <p>&nbsp;아니오 </p>
	                   	   <%}
	                         else
	                  	     {%>
	                       	     <p>예</p>
	                  	   <%}%>
	               		   </td>
	                       <td><%=m.getEnrollDate() %></td>
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
                        <option value="memberName">이름</option>
                        <option value="memberId">아이디</option>
                        <option value="memberDel">탈퇴여부</option>
                      
                     </select>
                     <div id="search-memberName">
                        <form action="<%=request.getContextPath()%>/adminmemberfindercontroller">
                           <input type="hidden" name="searchType" value="memberName" /> 
                           <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요" />
                           <button class="btn btn-primary" type="submit">검색</button>
                           <input type='button' class='btn btn-primary' onclick='deleteMember();' value='삭제'>
                        </form>
                     </div>
                      <div id="search-memberId">
                        <form action="<%=request.getContextPath()%>/adminmemberfindercontroller">
                           <input type="hidden" name="searchType" value="memberId" /> 
                           <input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요" />
                           <button class="btn btn-primary" type="submit">검색</button>
                           <input type='button' class='btn btn-primary' onclick='deleteMember();' value='삭제'>
                        </form>
                     </div>
                     <div id="search-memberDel">
                        <form name='ynSearchFrm' action="<%=request.getContextPath()%>/adminmemberfindercontroller">
                           <input type="hidden" name="searchType" value="memberDel" /> 
                           <input type='hidden' name="searchKeyword" id='searchKeyword'/>
                           &nbsp;
                           <label for='radioCheckYn'>탈퇴회원</label><input type="radio" name="radioCheckYn" value='Y' size="25" onclick='delCheckYn()' />&nbsp;
                           <label for='radioCheckYn'>현재회원</label><input type="radio" name="radioCheckYn" value='N' size="25" onclick='delCheckYn()' />&nbsp;                           
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