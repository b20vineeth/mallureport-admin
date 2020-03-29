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
						<label for="exampleInputPassword1"> Language </label> <select
							class="form-control " name="language" id="language">
							<c:forEach items="${responseLang.objectList}" var="objectList">
								<c:if test="${objectList.langId eq response.object.lang}">
									<option selected="selected" value="${objectList.langId}">${objectList.langName}</option>
								</c:if>
								<option value="${objectList.langId}">${objectList.langName}</option>

							</c:forEach>
						</select>




					</div>




				</div>
				
				
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Rating </label> <input
							type="Text" class="form-control" id="movieRate"
							value="${response.object.movieRate}" placeholder="Movie Rating">  

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
						<label for="exampleInputPassword1">Thumbnail</label> <input
							type="Text" class="form-control" id="thumbnail"
							value="${response.object.thumbnail}" placeholder="Thumbnail">
					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Release Date</label>

						<div class="form-group">
							<input type="Text" class="form-control" id="releaseDate"
								value="${response.object.releaseDate}" placeholder="DD/MM/YYY">
						</div>
					</div>
				</div>
			</div>








			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Tag</label> <input type="Text"
							class="form-control" id="tag" value="${response.object.tag}"
							placeholder="#tag">
					</div>
				</div>

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Cast</label> <input type="Text"
							class="form-control" id="cast" value="${response.object.cast}"
							placeholder="Movie Cast">
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
	var movieId = $('#movieId').val();

	if (movieId.trim().length != 0) {
		$("#catId").attr('disabled', 'disabled');
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
					+ $('#movieCode').val() + '","description": "'
					+ $('#description').val() + '","thumbnail": "'
					+ $('#thumbnail').val() + '","releaseDate": "'
					+ $('#releaseDate').val() + '","tag": "' + $('#tag').val()
					+ '","cast": "' + $('#cast').val() + '"} ',
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
