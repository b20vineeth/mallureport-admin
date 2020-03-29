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
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Title</label> <input
							type="Text" class="form-control" id="title"
							value="${response.object.title}" placeholder="title">

					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Movie Code</label> <input
							type="Text" class="form-control" id="movieCode"
							disabled="disabled" value="${response.object.movieCode}"
							placeholder="Movie Code">

					</div>



				</div>
			</div>














			<div class="row">

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
				src="https://cdn.tiny.cloud/1/isl39apbt7r6lzd25jgwhwkhcfdraw8gwczyrqi9d74o1h7v/tinymce/5/tinymce.min.js"
				referrerpolicy="origin"></script>

			<script>
				tinymce
						.init({
							selector : "#description",
							plugins : 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
							toolbar : 'a11ycheck addcomment showcomments casechange checklist code formatpainter pageembed permanentpen table',
							toolbar_mode : 'floating',
							tinycomments_mode : 'embedded',
							tinycomments_author : 'Author name',
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



			<input type="hidden" id="movieReviewId"
				value="${response.object.movieReviewId}" />



			<button type="button" onclick="saveMovieReview()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
	var movieId = $('#movieReviewId').val();

	function getCategory(catId) {

		window.location.href = "admin.movie.edit?catId=" + catId;

	}
	function saveMovieReview() {

		var content = tinymce.get("description").getContent();
 
 
		var movieId = $('#movieReviewId').val();
		if (movieId.trim().length == 0)
			movieId = 0;

		$.ajax({
			url : 'admin.movie.saveMovieReview',
			type : 'POST',

			data : "data=" + '{ "movieId": "' + movieId + '","shortDesc": "'
					+ $('#shortDesc').val() + '","title": "'
					+ $('#title').val() + '" ,"movieCode": "'
					+ $('#movieCode').val() + '","description": "'
					+ $('#description').val() + '","tag": "' + $('#tag').val() + '"} ',
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
