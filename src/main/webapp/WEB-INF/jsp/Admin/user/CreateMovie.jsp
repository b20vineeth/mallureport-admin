<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Movie</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Movie Setup</h5>
		<hr />
		<br />
		<form action="GET">



			<div class="row">
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Movie Name</label> <input
							type="Text" class="form-control" id="movieName"
							value="${response.object.movieName}" placeholder="Movie Name">

					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Movie Code</label> <input
							type="Text" class="form-control" id="movieCode"
							value="${response.object.movieCode}" placeholder="Movie Code">

					</div>



				</div>
			</div>


			<div class="row">

				<div class="col-sm-6 clearfix">


					<div class="form-group">
						<label for="exampleInputPassword1"> Language </label>  
							
							 <input
							type="Text" class="form-control" id="language" name="language"
							value="${response.object.lang}" placeholder="language">
							 




					</div>




				</div>


				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Release Date</label> <input
							type="Text" class="form-control" id="releaseDate"
							value="${response.object.releaseDate}" placeholder="DD/MM/YYY">
						<input type="hidden" class="form-control" id="movieRate"
							value="${response.object.movieRate}" placeholder="Movie Rating">

					</div>



				</div>
			</div>









			<div class="row">

				<div class="col-sm-6 clearfix">


					<div class="form-group">
						<label for="exampleInputPassword1"> Movie Type </label> <input
							type="Text" class="form-control" id="movieType" name="movieType"
							value="${response.object.movieType}" placeholder="Drama">


					</div>




				</div>


				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Certificate</label> <input
							type="Text" class="form-control" id="certificate"
							value="${response.object.certificate}" placeholder="U">

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
						<label for="exampleInputPassword1">Description</label> <input
							type="hidden" class="form-control" id="thumbnail"
							value="${response.object.thumbnail}" placeholder="Thumbnail">
						<textarea class="form-control" id="description"
							placeholder="Description">${response.object.description}</textarea>

					</div>
				</div>
			</div>



			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Tag</label> <input
							type="Text" class="form-control" id="tag" name="tag"
							value="${response.object.tag}" />

					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Profile </label> <input
							type="text" class="form-control" id="profile" name="profile" value="${response.object.cast}" />




					</div>
				</div>
			</div>










			 



			<input type="hidden" id="movieId" value="${response.object.movieId}" />



			<button type="button" onclick="saveMovie()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
$('input[name="tag"]').amsifySuggestags({
	tagLimit: 10
});

$('input[name="movieType"]').amsifySuggestags({
	suggestionsAction : {
		url : 'admin.common.getdata?type=movieType'
	},
	<c:if test="${not empty response.stringMap['MovieType']}"> 
	suggestions:  
		${response.stringMap['MovieType']}
	</c:if>
});
$('input[name="language"]').amsifySuggestags({
	suggestionsAction : {
		url : 'admin.common.getdata?type=language'
	},
	<c:if test="${not empty response.stringMap['language']}">
	suggestions:  
		${response.stringMap['language']}
	 </c:if>
});


$('input[name="profile"]').amsifySuggestags({
	suggestionsAction : {
		url : 'admin.common.getdata?type=profile'
	},
	<c:if test="${not empty response.stringMap['Cast']}">
	suggestions:  
		${response.stringMap['Cast']}
	 </c:if>
		 
});

 
	var movieId = $('#movieId').val();

	if (movieId.trim().length != 0) {
		$("#movieCode").attr('disabled', 'disabled');
	}
	function getCategory(catId) {

		window.location.href = "admin.movie.edit?catId=" + catId;

	}
	function saveMovie() {
		var movieId = $('#movieId').val();
		if (movieId.trim().length == 0)
			movieId = 0;

		$.ajax({
			url : 'admin.movie.save',
			type : 'POST',

			data : "data=" + '{ "movieId": "' + movieId + '","lang": "'
					+ $('#language').val() + '","shortDesc": "'
					+ $('#shortDesc').val() + '","movieName": "'
					+ $('#movieName').val() + '","movieRate": "'
					+ $('#movieRate').val() + '","movieCode": "'
					+ $('#movieCode').val() +'","movieType": "'
					+ $('#movieType').val() + '","certificate": "'
					+ $('#certificate').val() +'","description": "'
					+ $('#description').val() + '","thumbnail": "'
					+ $('#thumbnail').val() + '","releaseDate": "'
					+ $('#releaseDate').val() + '","tag": "' + $('#tag').val()
					+ '","cast": "' + $('#profile').val() + '"} ',
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
