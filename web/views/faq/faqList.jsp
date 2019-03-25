<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import ="java.util.*, com.petwork.model.vo.Faq"%> 
<%
	List<Faq> list = (List)request.getAttribute("list");
%>
<%@ include file="/views/common/header.jsp"%>
<style>
#section > h3{
	font-weight: 600;
}
</style>
<script>
	function fn_qna(){
		location.href="<%=request.getContextPath()%>/qna/qnaUserList";
	}
	
</script>
<section>
	<div class="container-fluid">
		<div class='row'>
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8' id='section'>
				<h3>자주 묻는 질문</h3>
				<div id="accordion">
				<%if(list == null || list.size() == 0){ %>
				<%}else{ 
					for(Faq f : list){%>
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link" style="color:#516055;"data-toggle="collapse"
									data-target="#<%=f.getFaqNo() %>" aria-expanded="true"
									aria-controls="collapseOne"><%=f.getFaqTitle()%>
								</button>
							</h5>
						</div>
						<%if(f == list.get(0)){ %>
						<div id="<%=f.getFaqNo() %>" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body"><%=f.getFaqContent() %>
							</div>
						</div>
						<%}else{ %>
						<div id="<%=f.getFaqNo() %>" class="collapse"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body"><%=f.getFaqContent() %>
							</div>
						</div>
						<%} %>
					</div>
					<%} %>
					<%} %>
					
					<%if(loginDoctor==null) {%>
						<button class="btn btn-primary float-right my-2" onclick="fn_qna();">QNA질문하기</button>
					<%} %>
				</div>
			</div>
		   <div class='col-md-2' id='aside'></div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp"%>