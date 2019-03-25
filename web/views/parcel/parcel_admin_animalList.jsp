<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.*,com.petwork.model.vo.Animal"%>
<%
	List<Animal> list = (List)request.getAttribute("list");
	String pageBar=(String)request.getAttribute("pageBar");
	String raceCode=(String)request.getAttribute("head");
%>
<script>
	function fn_checked(){
	    var race_code = $('input[name=parcel-main-race]:checked').val();
	    location.href="<%=request.getContextPath()%>/animalManage?race_code="+race_code;
	}
	function fn_addAnimal(){
		location.href="<%=request.getContextPath()%>/addAnimal";
	}
</script>

<section id="section">
	<div class='container-fluid'>
        <div class='row'>
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
            	<div class="row">
					<div class="col-md-12 text-center">
						<h1 id="parcel-main-title">분양</h1>
						<%if(raceCode==null||raceCode.equals("A")) {%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A" checked onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="E" onclick="fn_checked();"></label>
						<%} else if(raceCode.equals("D")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" checked onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" name="parcel-main-race" class="ml-2 mt-1" value="E" onclick="fn_checked();"></label>
						<%} else if(raceCode.equals("C")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" checked onclick="fn_checked();"></label> 
							<label>기타<input type="radio" name="parcel-main-race" class="ml-2 mt-1" value="E" onclick="fn_checked();"></label>
						<%} else if(raceCode.equals("E")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" name="parcel-main-race" class="ml-2 mt-1" value="E" checked onclick="fn_checked();"></label>
						<%} %>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10"></div>
					<div class="col-md-2 mb-1">
						<button onclick="fn_addAnimal();" class="btn btn-primary">분양동물추가</button>
					</div>
				</div>
				<form action="<%=request.getContextPath()%>/deleteAnimal" method="post">
	                <table id="animalList" class="table table-striped table-bordered">
		                 <thead>
		                     <tr>
		                     	<th></th>
		                         <th>동물종류</th>
		                         <th>동물이름</th>
		                     </tr>
		                 </thead>
		                 <tbody>
		                	<%for(Animal ani : list) {%>
		                		<tr>
		                			<td><input type="checkbox" name="animal_ck" class="animal_ck" value="<%=ani.getRaceCode()+ani.getAnimalNo()%>"></td>
		                			<%if(ani.getRaceCode().equals("D")) {%>
		                				<td>강아지</td>
		                			<%} else if(ani.getRaceCode().equals("C")){%>
		                				<td>고양이</td>
		                			<%} else {%>
		                				<td>기타</td>
		                			<%} %>
	                				<td><%=ani.getAnimalName() %></td>
	                			</tr>
		                   <%} %>
		                 </tbody>
	            	</table>
	            	<div class="row">
	            		<div class="col-md-10"></div>
	            		<div class="col-md-2">
							<input type="submit" class="btn btn-primary" value="분양동물삭제">
	            		</div>
            		
            		</div>
            		</form>
            	<div class="pagingDiv" style="text-align: center">
					<div class="pagination">
						<%=pageBar %>
					</div>
				</div>
            </div>
			</div>
        </div>
</section>

<%@ include file="/views/common/footer.jsp"%>