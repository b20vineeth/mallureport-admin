<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Category</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Category Setup</h5>
		<hr />

		<div id="search">
			<form>


				<div class="row">
					<div class="col-sm-6 clearfix">
						<div class="form-group">
							<label for="exampleInputEmail1"> Name</label> <input
								type="hidden" name="catId" id="catId"
								value="${response.object.catId}" /> <input type="Text"
								class="form-control" id="catName"
								value="${response.object.catName}"
								aria-describedby="langNameHelp" placeholder="Category Name">
						</div>
					</div>
					<div class="col-sm-6 clearfix">
						<div class="form-group">
							<label for="exampleInputPassword1"> Category Code</label> <input
								type="Text" class="form-control" id="catCode"
								value="${response.object.catCode}" placeholder="Category Code">
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-sm-6 clearfix">

						<div class="form-group">
							<label for="exampleInputPassword1"> Category Type</label> <select
								onchange="displayLanguage()" class="form-control" name="catType"
								id="catType">
								<option value="0">--Select--</option>
								<c:forEach items="${response.objectList}" var="objectList">
									<c:if
										test="${objectList.catTypeId eq response.object.catTypeId}">
										<option selected="selected" value="${objectList.catTypeId}">${objectList.catTypeName}</option>
									</c:if>
									<option value="${objectList.catTypeId}">${objectList.catTypeName}</option>

								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-6 clearfix">
						<div id="langDiv">
							<div class="form-group">
								<label for="exampleInputPassword1"> Language</label> <select
									class="form-control " name="language" id="language">
									<option value="0">--Select--</option>
									<c:forEach items="${responseLang.objectList}" var="objectList">
										<c:if test="${objectList.langId eq response.object.lang}">
											<option selected="selected" value="${objectList.langId}">${objectList.langName}</option>
										</c:if>
										<option value="${objectList.langId}">${objectList.langName}</option>

									</c:forEach>
								</select>
							</div>


						</div>


						<div id="genderDiv">
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


					</div>
				</div>














				<button type="button" onClick="saveCategory()"
					class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>
	var langFlag = true;
	var genderflag = true;
	if ($('#catType').val() == 0) {
		$('#catType').val(2);
	}
	if ($('#catType').val() != 2) {
		$('#langDiv').hide();
		langFlag = false;
	}
	if ($('#catType').val() != 1) {
		$('#genderDiv').hide();
		genderflag = false;
	}
	function displayLanguage() {

		if ($('#catType').val() == 2) {
			$('#langDiv').show();
			langFlag = true;
		} else {
			$('#langDiv').hide();
			langFlag = false;
		}

		if ($('#catType').val() == 1) {
			$('#genderDiv').show();
			genderflag = true;
		} else {
			$('#genderDiv').hide();
			genderflag = false;
		}
	}

	function saveCategory() {
		var catId = $('#catId').val();
		if (catId.trim().length == 0)
			catId = 0;

		var langData = langFlag ? $('#language').val() : 0;
		var genderData = genderflag ? $("input[name='gender']:checked").val()
				: "";

		$.ajax({
			url : 'admin.category.save',
			type : 'POST',

			data : "data=" + '{ "catId": "' + catId + '","catCode": "'
					+ $('#catCode').val() + '","catType": "'
					+ $('#catType').val() + '","lang": "' + langData
					+ '","gender": "' + genderData + '","catName": "'
					+ $('#catName').val() + '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.category.view";
				} else {
					alert("Invalid Category Code");
				}
			}

		});

	}
</script>

<jsp:include page="../HomeFooter.jsp" />
