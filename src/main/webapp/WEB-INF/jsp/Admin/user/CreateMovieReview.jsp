<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Movie</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Create Movie Review</h5>
		<hr />
		<br />
		<form action="GET">



			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Title</label> <input
							type="Text" class="form-control" id="title"
							value="${response.object.title}" placeholder="title">

					</div>
				</div>
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Movie Name</label> <input
							type="Text" class="form-control" id="movieCode"
							disabled="disabled" value="${response.object.movieName}"
							placeholder="Movie Code">

					</div>



				</div>
			</div>














			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> ThumbUrl</label> <input
							type="Text" class="form-control" id="thumbnail"
							value="${response.object.thumbnail}" placeholder="thumbnail">

					</div>
				</div>




				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Short Description</label>

						<textarea class="form-control" id="shortDesc"
							placeholder="Short Description">${response.object.shortDesc}</textarea>

					</div>
				</div>
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Description</label>

						<textarea id="description">   ${response.object.description}  </textarea>
					</div>
				</div>
			</div>




			<script
				src="resources/js/tinymce/tinymce.min.js"></script>

			<script>
				 
				tinymce.init({
					  selector: "#description",
					  height: 150,
					  width: 670,
					  plugins: [
					    'advlist autolink lists link image charmap print preview anchor',
					    'searchreplace visualblocks code fullscreen',
					    'insertdatetime media table contextmenu paste code'
					  ],
					  toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
					   
					});
			</script>



			<div class="row">

				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Tag</label> <input type="Text"
							class="form-control" id="tag" value="${response.object.tag}"
							placeholder="#tag">
					</div>
				</div>



			</div>

	<input type="hidden" id="movieId"
				value="${response.object.movieId}" />

			<input type="hidden" id="movieReviewId"
				value="${response.object.movieReviewId}" />



			<button type="button" onclick="saveMovieReview()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
	var movieReviewId = $('#movieReviewId').val();

	function getCategory(catId) {

		window.location.href = "admin.movie.edit?catId=" + catId;

	}
	function saveMovieReview() {

		var content = escape(tinymce.get("description").getContent());
 
 
		var movieReviewId = $('#movieReviewId').val();
		if (movieReviewId.trim().length == 0)
			movieReviewId = 0;

		$.ajax({
			url : 'admin.movie.saveMovieReview',
			type : 'POST',

			data : "data=" + '{ "movieId": "' + $('#movieId').val() + '","shortDesc": "'
			+ $('#shortDesc').val() + '","thumbnail": "'
			+ $('#thumbnail').val() + '","movieReviewId": "'
			+ movieReviewId + '","title": "'
					+ $('#title').val() + '" ,"movieCode": "'
					+ $('#movieCode').val() + '","description": "'
					+ content+ '","tag": "' + $('#tag').val() + '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.movie.view";
				} else {
					alert("Invalid Movie Code");
				}
			}

		}); 

	}
</script>

<jsp:include page="../HomeFooter.jsp" />
