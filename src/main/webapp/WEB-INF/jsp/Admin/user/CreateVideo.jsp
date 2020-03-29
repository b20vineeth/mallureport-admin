<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Video</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Video Setup</h5>
		<hr />
		<br />
		<form action="GET">

			<div class="row">
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Title </label> 
 
						 <input
							type="Text" class="form-control" id="title"
							value="${response.object.title}" placeholder="Title">
					 


					</div>



				</div>
				<div class="col-sm-6 clearfix">


					<div class="form-group">
						<label for="exampleInputPassword1"> Thumbnail </label> 
						
					 	 
						 <input
							type="Text" class="form-control" id="thumbnail"
							value="${response.object.thumbnail}" placeholder="thumbnail">




					</div>




				</div>
			</div>












			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Short Description</label>

						<textarea class="form-control" id="shortDesc"
							 
							placeholder="Short Description">${response.object.shortDesc}</textarea>

					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Description</label>


						<textarea class="form-control" id="description"
							  placeholder="Description">${response.object.description}</textarea>

					</div>
				</div>
			</div>




			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Tag</label> <input
							type="Text" class="form-control" id="tag"
							value="${response.object.tag}" placeholder="#tag">
					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Url</label>

						<div class="form-group">
							<input type="Text" class="form-control" id="url"
								value="${response.object.url}" placeholder="url">
						</div>
					</div>
				</div>
			</div>








			<div class="row">

				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Video Url</label>
						
						
							<textarea class="form-control" id="videoUrl"
							 
							placeholder="Video Url">${response.object.videoUrl}</textarea>
						
						
						  
					</div>
				</div>

				 


			</div>



		<input type="hidden" id="videoId" value="${response.object.videoId}"/>


			 
			<button type="button" onclick="saveVideo()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
 
 
	function saveVideo() {
		var videoId = $('#videoId').val();
		if (videoId.trim().length == 0)
			videoId = 0;

		$.ajax({
			url : 'admin.video.save',
			type : 'POST',

			data : "data=" + '{ "videoId": "' + videoId + '","videoUrl": "'
					+ $('#videoUrl').val() + '","shortDesc": "'
					+ $('#shortDesc').val() + '","title": "'
					+ $('#title').val() + '","description": "'
					+ $('#description').val() + '","thumbnail": "'
					+ $('#thumbnail').val() + '","url": "'
					+ $('#url').val() + '","tag": "' + $('#tag').val()
					+ '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.video.view";
				} else {
					alert("Invalid video URL");
				}
			}

		});

	}
</script>

<jsp:include page="../HomeFooter.jsp" />
