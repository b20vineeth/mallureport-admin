<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View SlideShow</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>View Slide Show</h5>
		<hr />
	<p align="right">	<a href="admin.slideshow.create?type=${type}"><i
			class="fa fa-plus" aria-hidden="true"></i></a></p>
		<div class="row">
<input type="hidden" id="type" value="${type}"/>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Title</th> 
						 
						<th>Slider</th>
 						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
					%>
					<c:forEach items="${response.objectList}" var="objectList">


						<tr>
							<td><%=i++%></td>
							<td><a href="${objectList.link}">${objectList.title}</a></td>
							 
							 
							<td>${objectList.slideflg}</td>
							<td><a
								href="javascript:editSlideShow(${objectList.slideShowid})">
									<i class="fa fa-edit pointer"
									style="font-size: 20px; color: Green"> </i>
							</a> 
							<a href="javascript:deleteSlideShow(${objectList.slideShowid})">
									<i class="fa fa-trash-o" style="font-size: 20px; color: red"></i>
							</a>
							<a href="javascript:cropImage(${objectList.slideShowid})">
									<i class="fa fa-crop" style="font-size: 20px; color: Green"></i>
							</a>
							 
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
	function editSlideShow(id) {
		window.location.href = "admin.slideshow.edit?slideShowId=" + id;

	}
	function deleteSlideShow(id){
 debugger;
	 	$.ajax({
			url : 'admin.slideshow.save',
			type : 'POST',
			data : "data=" + '{ "slideShowid": "' + id + '" ,"status": "N"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.slideshow.view?type="+$('#type').val();
				} else {
					alert("Invalid slideshow Code");
				}
			}

		});
		}
</script>
<jsp:include page="../HomeFooter.jsp" />
