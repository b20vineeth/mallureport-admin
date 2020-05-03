<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View Job</title>
<div class="row">

	<div class="col-sm-12 clearfix">
		<h5>Job</h5>
		<hr />

		<div align="right">
			<a href="javascript:enableEdit()">(+)</a>
		</div>
		<div id="search">
			<div class="row">

				<input type="hidden" name="page" id="page" value="${page}" />
				<div class="col-sm-4 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="jobName"
							value="${jobName}" placeholder="Job Name">
					</div>
				</div>
				<div class="col-sm-4 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="type" value="${type}"
							placeholder="type">
					</div>
				</div>



				<div class="col-sm-4 clearfix">
					<div class="form-group">

						<button type="button" onClick="search()" class="btn btn-primary">Search</button>

					</div>
				</div>

			</div>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th style="width: 3%">#</th>
					<th style="width: 15%">Job Name</th>
					<th style="width: 5%">Type</th>
					<th>Previous</th>
					<th>Next</th>
					<th style="width: 15%">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 1;
				%>
				<c:forEach items="${response.objectList}" var="objectList">


					<tr>
						<td><%=i++%></td>
						<td>${objectList.jobName}</td>
						<td>${objectList.type}</td>
						<td>${objectList.previousFireTime}</td>
						<td>${objectList.nextFireTime}</td>

						<td>
							<div class="row">
								<div class="col-sm-1">
									<i class="fa fa-edit" onClick="editJob(${objectList.jobId})"
										style="font-size: 20px; color: Green"></i>
								</div>

								<div class="col-sm-7">
									<span style="float: left;">
									  <a
												href="javascript:executeNow('${objectList.jobCode}','start')"><i
													class="fa fa-play"  title="Execute Now"
													style="font-size: 20px; color: red"></i></a> &nbsp;</span>

									<c:choose>
										<c:when test="${objectList.jobStatus  == 'A'}">
											<span style="float: left;"><a
												href="javascript:statusUpdate('D','${objectList.jobCode}')"><i
													class="fa fa-toggle-on"  title="Deactivate"
													style="font-size: 20px; color: Green"></i></a></span>

										</c:when>
										<c:otherwise>
											<span style="float: left;"><a
												href="javascript:statusUpdate('A','${objectList.jobCode}')"><i
													class="fa fa-toggle-off" title="Activate"
													style="font-size: 20px; color: Red"></i></a></span>

										</c:otherwise>
									</c:choose>


								</div>
								<div class="col-sm-8"></div>
							</div>
						</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="clearfix"></div>
		<c:if test="${response.page.totalPage>1}">
			<ul class="pagination pagination-sm">
				<c:choose>
					<c:when test="${response.page.currentPage  == 1}">
						<li class="page-item disabled"><a href="#" class="page-link">
								Previous </a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="javascript:goPage(${response.page.currentPage - 1})"><i
								class="material-icons">Previous</i></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" end="${response.page.totalPage}"
					varStatus="loop">
					<c:choose>
						<c:when test="${response.page.currentPage == loop.index}">
							<li class="page-item"><a class="page-link"
								href="javascript:goPage(${loop.index})">${loop.index}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="javascript:goPage(${loop.index})">${loop.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when
						test="${response.page.currentPage == response.page.totalPage}">
						<li class="page-item  disabled"><a
							href="javascript:goPage(${loop.index})" class="page-link"><i
								class="material-icons">Next</i></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="javascript:goPage(${response.page.currentPage + 1})">
								Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:if>
	</div>










	<style>
.noReview01 {
	color: red;
}

.activeReview01 {
	color: green;
}
</style>
	<script>
	function statusUpdate(status,jobCode)
	{
		 
		var url='';
		if(status=='A')
		 {
			 url='admin.job.activate'
		 }
		if(status=='D')
		 {
			 url='admin.job.deactivate'
		 } 		
		$.ajax({
			url : url,
			type : 'POST',
	 		data : "data=" + '{ "jobCode": "' + jobCode + '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.job.view?jobCode="+jobCode;
				} else {
					alert("Invalid job");
				}
			}

		});

		}

	function executeNow(jobCode,status)
	{
		 
		var url='';
		if(status=='start')
		 {
			url="admin.job.executeNow?jobCode="+jobCode;
		 }
		if(status=='stop')
		 {
			url="admin.job.stop?data="+jobCode;
		 } 			
		$.ajax({
			url : url,
			type : 'GET',
	 	 	processData : false,
			success : function(data) {
				 
					window.location.href = "/admin.job.view?jobCode="+jobCode;
				 
			}

		});

		}
		  
		$('#search').toggle();
		function enableEdit() {
			$('#search').toggle();
		}
	 

		function goPage(id) {
			$('#page').val(id);
			searchCategory();
		}
		function search() {
			window.location.href = "admin.job.list?jobName="
					+ $('#jobName').val() + "&type="
					+ $('#type').val() + "&page=" + $('#page').val();
		}
		function editJob(id) {
			window.location.href = "admin.job.edit?jobId=" + id;

		}
	</script>
	<jsp:include page="../HomeFooter.jsp" />