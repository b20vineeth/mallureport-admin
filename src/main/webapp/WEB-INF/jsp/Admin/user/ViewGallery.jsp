<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View Gallery</title>
<div class="row">
	<div class="col-sm-12 clearfix">
		<h5>Gallery</h5>
		<hr />
		<div align="right">
			<a href="javascript:enableEdit()">(+)</a>
		</div>
		<div id="search">
			<div class="row">

				<input type="hidden" name="page" id="page" value="${page}" />
				<div class="col-sm-4 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="tag" value="${tag}"
							placeholder="tag">
					</div>
				</div>
				<div class="col-sm-4 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="title"
							value="${title}" placeholder="Tag">
					</div>
				</div>



				<div class="col-sm-4 clearfix">
					<div class="form-group">

						<button type="button" onClick="searchVideo()"
							class="btn btn-primary">Search</button>





					</div>
				</div>

			</div>
		</div>














		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>title</th>
					<th>tag</th>

					<th>Description</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 1;
				%>
				<c:forEach items="${response.objectList}" var="objectList">
					<tr>
						<td><img src="pic/${objectList.thumbnail}" height="40"
							width="auto" /></td>
						<td>${objectList.title}</td>
						<td>${objectList.tag}</td>

						<td>${objectList.shortDesc}</td>
						<td><i class="fa fa-edit"
							onClick="editGallery(${objectList.galleryId})"
							style="font-size: 20px; color: Green"></i> <i
							class="fa fa-file-photo-o"
							onClick="updateThumbnail(${objectList.galleryId})"
							style="font-size: 18px; color: Green"></i></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>









		<div class="clearfix"></div>

		<c:if test="${response.page.totalPage>1}">


			<ul class="pagination ">



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
	searchVideo();
}
function searchVideo()
{
	window.location.href="admin.gallery.view?tag="+$('#tag').val()+"&title="+$('#title').val()+"&page="+$('#page').val();
}
 function editGallery(id)
 {
	 window.location.href="admin.gallery.edit?galleryId="+id;

	 }
 function updateThumbnail(id)
 {

	 window.location.href="admin.gallery.updateThumbnail?galleryId="+id;
	 }
 </script>

	<jsp:include page="../HomeFooter.jsp" />