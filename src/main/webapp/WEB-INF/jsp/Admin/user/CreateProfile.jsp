<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Profile</title>
<div class="row">
	<div class="col-sm-2 clearfix"></div>
	<div class="col-sm-8 clearfix">
		<h5>Create Profile</h5>
		<hr />
		<br />
		<form>

			<div class="row">

				<div class="col-sm-6 clearfix">


					<div class="form-group">
					 
						<label for="exampleInputPassword1"> Name </label>  
						<input
							type="Text" class="form-control" id="profileName"
							value="${response.object.profileName}" placeholder="Profile Name">
						
					</div>

				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Date of Birth</label> <input
							type="Text" class="form-control" id="dateofBirth"
							value="${response.object.dateofBirth}" placeholder="DD/MM/YYYY">
					</div>
				</div>
			</div>
			
			<div class="row">
			
			<div class="col-sm-6 clearfix">


					<div class="form-group">
					 
						<label for="exampleInputPassword1"> Profile Code </label>  
						<input
							type="Text" class="form-control" id="profileCode"
							value="${response.object.profileCode}">
						
					</div>

				</div>
				<div class="col-sm-6 clearfix">


					<div class="form-group">
					 
						<label for="exampleInputPassword1"> Language </label>  
						<input
							type="Text" class="form-control" id="language"
							value="${response.object.language}" >
						
					</div>

				</div>
			</div>
			

			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">

						<label for="exampleInputPassword1">Gender</label> <br>

						<c:choose>
							<c:when test="${response.object.gender=='M'}">
								<input type="radio" id="gender" name="gender" value="M"
									checked="checked">
								 
							</c:when>
							<c:otherwise>
								<input type="radio" id="gender" name="gender" value="M">
								
							</c:otherwise>
						</c:choose>
						<label for="male">Male</label>




						<c:choose>
							<c:when test="${response.object.gender=='F'}">
								<input type="radio" id="gender" name="gender" value="F"
									checked="checked">
								 
							</c:when>
							<c:otherwise>
								<input type="radio" id="gender" name="gender" value="F">
							
							</c:otherwise>
						</c:choose>
							<label for="Female">Female</label>



						<c:choose>
							<c:when test="${response.object.gender=='O'}">
								<input type="radio" id="gender" name="gender" value="O"
									checked="checked">
								</c:when>
							<c:otherwise>
								<input type="radio" id="gender" name="gender" value="O">
								
							</c:otherwise>
						</c:choose>
						<label for="other">Other</label>


					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Thumbnail</label> <input
							type="Text" class="form-control" id="thumbnail"
							value="${response.object.thumbnail}" placeholder="Thumbnail">
					</div>
				</div>
			</div>






			<div class="row">

				<div class="col-sm-6 clearfix">


					<div class="form-group">
						<label for="exampleInputPassword1"> Description</label>


						<textarea class="form-control" id="description"
							placeholder=" Description">${response.object.description}</textarea>



					</div>

				</div>
				<div class="col-sm-6 clearfix">

					<div class="form-group">
						<label for="exampleInputPassword1"> Short Desc </label>


						<textarea class="form-control" id="shortDesc"
							placeholder="Short Description">${response.object.shortDesc}</textarea>



					</div>

				</div>
			</div>
			<div class="row">

				<div class="col-sm-6 clearfix">

					<div class="form-group">
						<label for="exampleInputPassword1"> Film </label> <input
							type="Text" class="form-control" id="films"
							value="${response.object.films}" placeholder="Films">
					</div>

				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Tag</label> <input type="Text"
							class="form-control" id="tag" value="${response.object.tag}"
							placeholder="tag">
					</div>


				</div>
			</div>


			<button type="button" onClick="saveCategory()"
				class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-2 clearfix">
		<input type="hidden" name="profileId" id="profileId"
			value="${response.object.profileId}">
	</div>
</div>
<script>




var profileId = $('#profileId').val();
 
if (profileId.trim().length!=0)
{
	$("#catId").attr('disabled', 'disabled');
}
	function getCategory(catId) {

		window.location.href = "admin.profile.edit?catId=" + catId;

	}
	function saveCategory() {
		var profileId = $('#profileId').val();
		if (profileId.trim().length == 0)
			profileId = 0;


		var genderData =  $("input[name='gender']:checked").val();
		
		$.ajax({
			url : 'admin.profile.save',
			type : 'POST',

			data : "data=" + '{ "profileId": "' + profileId + '","profileName": "'
			+ $('#profileName').val() + '","profileCode": "'
			+ $('#profileCode').val() + '","language": "'
			+ $('#language').val() + '","shortDesc": "'
					+ $('#shortDesc').val() + '","thumbnail": "'
					+ $('#thumbnail').val() + '","gender": "'
					+ genderData + '","films": "' + $('#films').val()
					+ '","tag": "' + $('#tag').val() + '","dateofBirth": "'
					+ $('#dateofBirth').val() + '","description": "'
					+ $('#description').val() + '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.profile.view";
				} else {
					alert("Invalid Category Code");
				}
			}

		});

	}
</script>

<jsp:include page="../HomeFooter.jsp" />
