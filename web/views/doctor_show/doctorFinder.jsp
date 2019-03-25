<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.petwork.model.vo.Doctor" %>
    <%List<Doctor> list=(List)request.getAttribute("list"); %>
    <%String pageBar=(String)request.getAttribute("pageBar"); %>
<%@ include file="/views/common/header.jsp" %>

<style>
div#search-doctorName{
      display:inline-block;
   }
   div#search-doctorAddress{
      display:none;
   }
</style>
<script>
window.onload=function(){
   var sname=document.querySelector("#search-doctorName");
   var saddress=document.querySelector("#search-doctorAddress");
   
   var searchType=document.querySelector("#searchType"); //셀렉트 태그를 가지고온것임 쿼리셀렉터는 선택자이용해서 가지고오는거얌
   searchType.addEventListener("change",function(){ //change변경됐을때 실행해 그럼 아래 3줄다 없어진다.
      sname.style.display="none";
      saddress.style.display="none";
      
      document.querySelector("#search-"+this.value).style.display="inline-block"; //디스벨류는 눌렀을때 그값 
   });
}
</script>
<section >
  <section>
   <div class="container-fluid row" id="contents">
      <div class='col-md-2' id='nav'>
          <div class="nav-side-menu">
              <div class="brand">PETWORK</div>
              <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
              <div class="menu-list">
                  <ul id="menu-content" class="menu-content collapse out">
                      <li>
                          <a href="<%=request.getContextPath()%>/intoAdminmain"> HOME </a>
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
                          <li><a href="<%=request.getContextPath()%>/faq/faqList">FAQ</a></li>
                      </ul>
                  </ul>
              </div>
          </div>
      </div>
   
          <div class='col-md-10' id='section'>
                <table id="example" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                         <th>아이디</th>
                            <th>이름</th>
                            <th>병원주소</th>
                            <th>병원이름</th>
                            <th>전화번호</th>
                        </tr>
                    </thead>
                     <tbody>
                      <%for(Doctor d : list){ %>
                        <tr> 
                           <td><%=d.getDoctorId()%></td>
                            <td>
                            <a href="<%=request.getContextPath() %>/admin/clickDoctor?no=<%=d.getDoctorNo()%>"><%=d.getDoctorName()%></a></td>
                            <td><%=d.getDoctorAddress() %></td>
                            <td><%=d.getDoctorHospital() %></td>
                            <td><%=d.getDoctorPhone() %></td>
                        </tr>
                      <%} %>  
                      
                    </tbody>
                </table>
               <!-- 글머리 서치 부분 -->
           <!-- 글머리 서치 부분 -->
            <!--검색기능 추가 -->
            <div class="row">
               <div class="col-md-12 text-center">
                  <div id="search-container">
                     <select id="searchType" class="btn btn-primary">
                        <option value="doctorName">이름</option>
                        <option value="doctorAddress">지역</option>
                      
                     </select>
                     <div id="search-doctorName">
                        <form action="<%=request.getContextPath()%>/admin/doctorFinder">
                           <input type="hidden" name="searchType" value="doctorName" /> <input
                              type="text" name="searchKeyword" size="25"
                              placeholder="검색할 이름을 입력하세요" />
                           <button class="btn btn-primary" type="submit">검색</button>
                        </form>
                     </div>
                      <div id="search-doctorAddress">
                        <form action="<%=request.getContextPath()%>/admin/doctorFinder">
                           <input type="hidden" name="searchType" value="doctorAddress" /> <input
                              type="text" name="searchKeyword" size="25"
                              placeholder="검색할 지역을 입력하세요" />
                           <button class="btn btn-primary" type="submit">검색</button>
                        </form>
                     </div>

                  </div>
               </div>
            </div>
               <div class="pagingDiv" style="text-align: center">
               <div class="pagination">
                  <%=pageBar %>
               </div>
            </div>
             

</section>
<%@ include file="/views/common/footer.jsp" %>