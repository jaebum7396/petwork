<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.*,com.petwork.model.vo.ParcelAniView"%>
<%
	List<ParcelAniView> list = (List)request.getAttribute("list");
	String pageBar=(String)request.getAttribute("pageBar");
	String head=(String)request.getAttribute("head");
	int[] price = (int[])request.getAttribute("price");
%>
<script>
	function fn_checked(){
	    var race_code = $('input[name=parcel-main-race]:checked').val();
	    location.href="<%=request.getContextPath()%>/parcelMain?race_code="+race_code;
	}
	/* $(function(){
		$('input[name=parcel-main-race]').on("change",function(){
			var parcelHead = document.querySelector('input[name=parcel-write-race]:checked').value;
			alert(parcelHead);
		});
	}); */
</script>




<section>
		<div class="container-fluid row" id="contents">
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8'>
				<div class="row">
					<div class="col-md-12 text-center">
						<h1 id="parcel-main-title">분양</h1>
						<%if(head==null||head.equals("A")) {%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A" checked onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" name="parcel-main-race" class="ml-2 mt-1" value="E" onclick="fn_checked();"></label>
						<%} else if(head.equals("D")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" checked onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" name="parcel-main-race" class="ml-2 mt-1" value="E" onclick="fn_checked();"></label>
						<%} else if(head.equals("C")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" checked onclick="fn_checked();"></label> 
							<label>기타<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="E" onclick="fn_checked();"></label>
						<%} else if(head.equals("E")){%>
							<label class="parcle-main-select">전체<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="A"  onclick="fn_checked();"></label> 
							<label class="parcle-main-select">강아지<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="D" onclick="fn_checked();"></label> 
							<label class="parcle-main-select">고양이<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="C" onclick="fn_checked();"></label> 
							<label>기타<input type="radio" class="ml-2 mt-1" name="parcel-main-race" value="E" checked onclick="fn_checked();"></label>
						<%} %>
					</div>
				</div>
				<div class="row">
					<%for(int i = 0; i < list.size(); i++) {%>
						<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
							<a href="<%=request.getContextPath()%>/parcel/parcelDetailView?postNo=<%=list.get(i).getPostNo() %>">
								<div class="team-block">
									<div class="team-img">
										<%if(list.get(i).getPostYn().equals("N")) {%>
											<img src="<%=request.getContextPath()%>/views/upload/parcel/<%=list.get(i).getImgAddress() %>"/>
										<%} else {%>
											<%if(list.get(i).getMemberGender()=='M') {%>
												<img src="<%=request.getContextPath()%>/img/parcel/parcelCompleteMan.jpg"/>
											<%} else {%>
												<img src="<%=request.getContextPath()%>/img/parcel/parcelComplete.jpg"/>
											<%} %>
										<%} %>
										<%if(list.get(i).getPostYn().equals("N")) {%>
											<div class="team-content">
												<div class="row">
													<div class="col-md-12 title">
														<h4 class="text-white mb0"><%=list.get(i).getAnimalName() %></h4>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<p class="team-meta"><%=price[i] %>만원</p>
													</div>
												</div>
											</div>
											<div class="overlay">
												<div class="text">
													<div class="row">
														<div class="col-md-12 hoverTitle">
															<h4 class="mb0 text-white"><%=list.get(i).getAnimalName() %></h4>	
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<p class="mb30 team-meta"><%=price[i] %>만원</p>
															<p class="parcel-main-pet-info">
																성별 : <%if(list.get(i).getGender().equals("M")){%>
						                                            		♂
						                                            	<%} else {%>
						                                            		♀
						                                            	<%} %>
															</p>
															<p class="parcel-main-pet-info">주소 : <%=list.get(i).getAddress() %></p>	
														</div>
													</div>
												</div>
											</div>
										<%} else { %>
											<div class="team-content">
												<div class="row">
													<div class="col-md-12 title">
														<h4 class="text-white mb0"><%=list.get(i).getAnimalName() %></h4>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<p class="team-meta">분양완료!</p>
													</div>
												</div>
											</div>
										<%} %>
									</div>
								</div>
							</a>
						</div>
					<%} %>
				</div>
				<div class="row mb-1">
					<div class="col-md-10">
					</div>
					<div class="col-md-2">
						<%if(loginMember!=null&&loginMember.getId()!=null) {%>
							<form action="<%=request.getContextPath()%>/parcelWrite" method="post">
								<input type="submit" id="write-btn" class="btn btn-primary" value="글쓰기">
							</form>
						<%} %>
					</div>
				</div>
	
				<div class="pagingDiv" style="text-align: center">
					<div class="pagination">
						<%=pageBar %>
					</div>
				</div>

				<form action="<%=request.getContextPath() %>/parcelMain" method="post" id="parcelHeadFrm">
					<input type="hidden" name="parcelHead" id="parcelHead">
				</form>
			</div>
			<div class='col-md-2' id='aside'></div>
		</div>
</section>
<%@ include file="/views/common/footer.jsp"%>