<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create SlideShow</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Slideshow Setup</h5>
		<hr />
		<br />
		<form action="GET" id="upload-file-form">



			<div class="row">
				<div class="col-sm-12 clearfix">
				
				
					<div class="form-group">
						<label for="exampleInputPassword1"> Category</label>
						
						<select
							class="form-control " name="category" id="category" onchange="changeCategory()">
							<c:forEach items="${category}" var="category">
								<c:if test="${category.categoryCode eq response.object.category}">
									<option selected="selected" value="${category.categoryCode}">${category.category}</option>
								</c:if>
								<option value="${category.categoryCode}">${category.category}</option>

							</c:forEach>
						</select>
						
						 

					</div>
				</div>
				 
			</div>
			 
			
			
			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Tag </label> <input
							type="Text" class="form-control" id="tag"
							value="${response.object.tag}" placeholder="tag">

					</div>
				</div>
				 
			</div>
			   
			<input type="hidden" id="settingsid" value="${response.object.settingsId}" />
			<input type="hidden" id="type" value="${response.object.type}" />



			<button type="button" onclick="saveSlideshow()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>


 function changeCategory()
 {


	 $.ajax({
			url : 'admin.settings.save',
			type : 'POST',

			data : "data=" + '{ "settingsid": "' + settingsid + '","category": "' + $('#category').val() + '"} ',
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
 

	var settingsid = $('#settingsid').val();

 
	function saveSlideshow() {
		var slideShowid = $('#slideShowid').val();
		if (slideShowid.trim().length == 0)
			slideShowid = 0;

		var slideflg =  $("input[name='slideflg']:checked").val();
		
		$.ajax({
			url : 'admin.slideshow.save',
			type : 'POST',

			data : "data=" + '{ "slideShowid": "' + slideShowid + '","link": "'
			+ $('#link').val() + '","title": "'
			+ $('#title').val() + '","type": "'
			+ $('#type').val() + '","slideflg": "'
			+ slideflg + '","picUrl": "' + $('#picUrl').val() + '"} ',
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
