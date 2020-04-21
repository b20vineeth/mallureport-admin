<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View Profile</title>
<div class="row">
	<div class="col-sm-12 clearfix">
		<h5>Profile</h5>
		<hr />
		<div align="right">
			<a href="javascript:enableEdit()">(+)</a>
		</div>
		<div id="search">
			<div class="row">

				<input type="hidden" name="page" id="page" value="${page}" />
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="profileName"
							value="${profileName}" placeholder="Name">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="tag" value="${tag}"
							placeholder="Tag">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<select id="gender" name="gender" 
							class="form-control" >

							<option value="">- Select -</option>
 								<c:choose>
								<c:when test="${gender=='M'}">
									<option value="M" selected="selected">Male</option>

								</c:when>
								<c:otherwise>
									<option value="M">Male</option>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${gender=='F'}">
									<option value="F" selected="selected">Female</option>

								</c:when>
								<c:otherwise>
									<option value="F">Female</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${gender=='O'}">
									<option value="F" selected="selected">Other</option>

								</c:when>
								<c:otherwise>
									<option value="O">Other</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">

						<button type="button" onClick="searchCategory()"
							class="btn btn-primary">Search</button>





					</div>
				</div>

			</div>
		</div>














		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Gender</th>

					<th>Date of Birth</th>
					<th>Tag</th>

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
						<td>${objectList.profileName}</td>
						<td>${objectList.gender}</td>
						<td>${objectList.dateofBirth}</td>
						<td>${objectList.tag}</td>
						 
						<td><i class="fa fa-edit"
							onClick="editProfile(${objectList.profileId})"
							style="font-size: 20px; color: Green"></i></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>









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
	 function enableEdit()
	 {
		 $('#search').toggle();
		}
function goPage(id)
{
	$('#page').val(id);
	searchCategory();
}
function searchCategory()
{
	window.location.href="admin.profile.view?profileName="+$('#profileName').val()+"&gender="+$('#gender').val()+"&tag="+$('#tag').val()+"&page="+$('#page').val();
}
 function editProfile(profileId)
 {
	 window.location.href="admin.profile.edit?profileId="+profileId;

	 }
 </script>

	<jsp:include page="../HomeFooter.jsp" />