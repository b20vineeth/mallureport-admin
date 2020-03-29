<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../HomeHeader.jsp" />
<title>View Category</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-11 clearfix">
		<h5>Category</h5>
		<hr /> 

		<div align="right">
			<a href="javascript:enableEdit()">(+)</a>
		</div>

		<div id="search">
			<div class="row">

				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="catName"
							value="${catName}" placeholder="Category Name">
					</div>
				</div>
				<div class="col-sm-2 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="catCode"
							value="${catCode}" placeholder="Category Code">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">


						<select class="form-control" name="catType" id="catType" onchange="display()">
							<option value="">Select</option>
							<c:forEach items="${CategoryType.objectList}" var="objectList">
								<c:if test="${objectList.catTypeId eq catType}">
									<option selected="selected" value="${objectList.catTypeId}">${objectList.catTypeName}</option>
								</c:if>
								<option value="${objectList.catTypeId}">${objectList.catTypeName}</option>

							</c:forEach>
						</select> <input type="hidden" name="page" id="page" value="${page}" />
					</div>
				</div>






				<div class="col-sm-3 clearfix">
					<div class="form-group">
					
					<div id="langDiv">
						<select class="form-control " name="language" id="language">
							<option value="0">--Select--</option>
							<c:forEach items="${responseLang.objectList}" var="objectList">
								<c:if test="${objectList.langId eq languageTag}">
									<option selected="selected" value="${objectList.langId}">${objectList.langName}</option>
								</c:if>
								<option value="${objectList.langId}">${objectList.langName}</option>

							</c:forEach>
						</select>
					</div>
				
					<div id="genderDiv">
						<select class="form-control " name="gender" id="gender">
							<option value="0">--Select--</option>
							<c:forEach items="${gender.objectList}" var="objectList">
								<c:if test="${objectList.genderCode eq genderTag}">
									<option selected="selected" value="${objectList.genderCode}">${objectList.genderName}</option>
								</c:if>
								<option value="${objectList.genderCode}">${objectList.genderName}</option>

							</c:forEach>
						</select>
					</div>
					
					
						


					</div>
				</div>

				<div class="col-sm-1 clearfix">
					<div class="form-group">
						<button type="button" onClick="searchCategory()"
							class="btn btn-primary">Search</button>
					</div>
				</div>
			</div>
		</div>




		<div class="row">

			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Code</th>
						<th>Type</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
					%>
					<c:forEach items="${response.objectList}" var="objectList">

						<c:set var="catType" value="${fn:toLowerCase(objectList.catType)}" />

						<tr>
							<td><%=i++%></td>
							<td>${objectList.catName}</td>
							<td class="lowercase">${objectList.catCode}</td>
							<td class="lowercase">${objectList.catType}<c:if
									test="${not empty objectList.langName}"> (${objectList.langName}) </c:if>
								<c:if test="${not empty objectList.gender}"> 
									
									(
									<c:if test="${objectList.gender=='M'}">
									 Male
								</c:if>
									<c:if test="${objectList.gender=='F'}">
									 Female
								</c:if>

									<c:if test="${objectList.gender=='O'}">
									 Other
								</c:if>
									) 
									
									</c:if>

							</td>
							<td><a href="admin.category.edit?catId=${objectList.catId}"
								title="Edit Category"> <i class="fa fa-edit"
									style="font-size: 20px; color: Green"></i>
							</a> <c:if test="${catType == 'profile'}">
									<a href="admin.profile.edit?catId=${objectList.catId}"
										title="Edit Profile"> <i class="fa fa-user"
										aria-hidden="true" style="font-size: 20px; color: black"></i></a>

								</c:if> <c:if test="${catType == 'movie'}">

									<a href="admin.movie.edit?catId=${objectList.catId}"
										title="Edit Movie Details"> <i class="fa fa-film"
										style="font-size: 20px; color: Blue"></i></a>
								</c:if> <a href="admin.gallery.view?catName=${objectList.catName}"
								title="Add Gallery items"> <i class="fa fa-file-image-o"
									style="font-size: 20px; color: red"></i>
							</a> <a href="admin.video.view?catName=${objectList.catName}"
								title="Add video items"> <i class="fa fa-video-camera"
									style="font-size: 20px; color: blue"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

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
	<script>
		$('#search').toggle();
		function enableEdit() {
			$('#search').toggle();
		}
		function goPage(id) {
			$('#page').val(id);
			searchCategory();
		}
		function searchCategory() {
			window.location.href = "admin.category.view?catName="
					+ $('#catName').val() + "&catCode=" + $('#catCode').val()
					+ "&catType=" + $('#catType').val() + "&page="
					+ $('#page').val()+"&language="+$('#language').val()
					+"&gender="+$('#gender').val()+"&langflag="+langflag+"&&genderflag="+genderflag;
		}
		function editCategory(catId) {
			window.location.href = "admin.category.edit?catId=" + catId;

		}


		var langflag=true;
		var genderflag=true;
			if ($('#catType').val() != 2) {
				$('#langDiv').hide();
				langflag=false;
			}
			if ($('#catType').val() != 1) {
				$('#genderDiv').hide();
				genderflag=false;
			}
			function display() {

				if ($('#catType').val() == 2) {
					$('#langDiv').show();
					langflag=true;
				} else {
					$('#langDiv').hide();
					langflag=false;
				}

				if ($('#catType').val() == 1) {
					$('#genderDiv').show();
					genderflag=true;
				} else {
					$('#genderDiv').hide();
					genderflag=false;
				}
			}
	</script>

	<jsp:include page="../HomeFooter.jsp" />