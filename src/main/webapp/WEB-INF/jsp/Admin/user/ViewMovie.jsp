<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View Movie</title>
<div class="row">
	<div class="col-sm-12 clearfix">
		<h5>Movie</h5>
		<hr />
		<div align="right">
			<a href="javascript:enableEdit()">(+)</a>
		</div>
		<div id="search">
			<div class="row">
				<input type="hidden" name="page" id="page" value="${page}" />
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="movieName"
							value="${movie_name}" placeholder="Movie Name">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="tag"
							value="${movie_tag}" placeholder="Tag">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="cast"
							value="${movie_cast}" placeholder="Cast">
					</div>
				</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<select class="form-control" name="language" id="language">
							<option value="">Select</option>
							<c:forEach items="${language.objectList}" var="objectList">
								<c:if test="${objectList.langId eq movie_language}">
									<option selected="selected" value="${objectList.langId}">${objectList.langName}</option>
								</c:if>
								<option value="${objectList.langId}">${objectList.langName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="releasefrom"
							value="${movie_releasefrom}" placeholder="DD-MM-YYY">
					</div>
				</div>
				<div class="col-sm-1 clearfix">To</div>
				<div class="col-sm-3 clearfix">
					<div class="form-group">
						<input type="Text" class="form-control" id="releaseTo"
							value="${movie_releaseTo}" placeholder="DD-MM-YYY">
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
					<th>Language</th>
					<th>Release Date</th>
					<th>Tag</th>
					<th>Cast</th>
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
						<td>${objectList.movieName}</td>
						<td>${objectList.languageName}</td>
						<td>${objectList.releaseDate}</td>
						<td>${objectList.tag}</td>
						<td>${objectList.cast}</td>
						<td><a href="javascript:editCategory(${objectList.movieId});">
								<i class="fa fa-edit pointer"
								style="font-size: 20px; color: Green"> </i>

						</a> <input type="checkbox" id="enable${objectList.movieId}"
							<c:if test="${objectList.priorityFlag=='Y'}">
                   checked="checked"
                  </c:if>
							onclick="updatePriority(${objectList.movieId})"
							title="Activate/Deactivate Priority" /> <input type="checkbox"
							id="recomended${objectList.movieId}"
							<c:if test="${objectList.recommenedFlag=='Y'}">
                   checked="checked"
                  </c:if>
							onclick="updateRecommenedFlag(${objectList.movieId})"
							title="Activate/Deactivate Recommened Flag" /> <a
							href="javascript:updateReview('${objectList.movieId}');"> <i
								class="fa fa-star pointer" aria-hidden="true"></i>
						</a></td>
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
function updateReview(movieCode)
{
	 	window.location.href = "admin.movie.editReview?movieCode="
				+movieCode; 
	 
}
   $('#search').toggle();
   function enableEdit()
   {
    $('#search').toggle();
   }
   function updateRecommenedFlag(movieId){
 	var recomended="N";
  		if($("#recomended"+movieId).prop("checked") == true){
  			recommendedFlag="Y";
  	 }
  	 else{
  		recommendedFlag="N";
  	}
  	var data = "data=" + '{ "movieId": "' + movieId + '","recommenedFlag": "'
  	+ recommendedFlag +'"} ';
  	 
  	updateStatus(data);
	   
	   }
   function updateStatus(data)
   {
		$.ajax({
	  	    url : "admin.movie.updateStatus",
	  	    type: "POST",
	  	    data : data,
	  	    success: function(data)
	  	    {
	  	     alert("Updated");
	  	    },
	  	    error: function (jqXHR, textStatus, errorThrown)
	  	    {
	  	 
	  	    }
	  	});
	  }
   function updatePriority(movieId)
   {
   	var prioriy="N";
   		if($("#enable"+movieId).prop("checked") == true){
   		 prioriy="Y";
   	 }
   	 else{
   		  prioriy="N";
   	}
   	var data = "data=" + '{ "movieId": "' + movieId + '","priorityFlag": "'
   	+ prioriy +'"} ';
   	 
	updateStatus(data);
   }
   
   function goPage(id)
   {
   $('#page').val(id);
   searchCategory();
   }
   function searchCategory()
   {
   window.location.href="admin.movie.view?movieName="+$('#movieName').val()+"&releaseTo="+$('#releaseTo').val()+"&releasefrom="+$('#releasefrom').val()+"&language="+$('#language').val()+"&tag="+$('#tag').val()+"&cast="+$('#cast').val()+"&page="+$('#page').val();
   }
   function editCategory(catId)
   {
   window.location.href="admin.movie.edit?movieId="+catId;
   
   }
</script>
	<jsp:include page="../HomeFooter.jsp" />