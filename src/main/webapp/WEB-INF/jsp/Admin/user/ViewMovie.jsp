<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>View Movie</title>
<div class="row">
 <%String movieIds=""; %>
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
						<td>${objectList.lang}</td>
						<td>${objectList.releaseDate}</td>
					 
					 
						<td>
						<input type="hidden" name="movieIds" id="movieIds" value="${objectList.movieId}"/>
						
						<a href="javascript:editCategory(${objectList.movieId});">
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
							title="Activate/Deactivate Recommened Flag" />
							
							 <a title="Rate "  id="ratingStatus${objectList.movieId}" 
							 <c:if test="${objectList.movieRate==0}">
							  class="noReview01"
							 </c:if>
							  <c:if test="${objectList.movieRate>1}">
							  class="activeReview01"
							 </c:if>
							href="javascript:updateRating(${objectList.movieId},'${objectList.movieName}',${objectList.movieRate});"> <i
								class="fa fa-star pointer" aria-hidden="true" style="font-size: 20px;"></i> </a>
								
							<a title="Update Review"  id="reviewStatus${objectList.movieId}" class="noReview01"
							href="javascript:updateReview(${objectList.movieId},'${objectList.movieName}');">	
							<i class="fa fa-comments-o" aria-hidden="true" style="font-size: 20px;"></i>
								</a>	
						
						<a id="galleryStatus${objectList.movieId}" class="noReview01" href="javascript:uploadImage(${objectList.movieId})">
						<i class="fa fa-picture-o" 	style="font-size: 20px;" aria-hidden="true"></i></a>
						
						</td>
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
	
	
	
	<div id="updatRate" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
         <h4 class="modal-title" > <span id="movieNameTitle"></span> </h4>  <button type="button" class="close" data-dismiss="modal">&times;</button>
     
      </div>
      <div class="modal-body">
    	<input type="hidden" id="ratemovId" name="ratemovId" />
    	Rate : <input type="Text" id="rate" name="rate" /> /100
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveRating()">Save</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	
	
	
	
	<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
         <h4 class="modal-title">Image Size </h4>  <button type="button" class="close" data-dismiss="modal">&times;</button>
     
      </div>
      <div class="modal-body">
  <input type="radio" id="imgsize" name="imgsize" value="335X180" checked="checked"><label for="335X180">Thumb1 (335X180)</label>
  <i id="thumb1" aria-hidden="true"></i><br>
		  <input type="radio" id="imgsize" name="imgsize" value="690X390" > <label for="690X390">Thumb2 (690X390)</label>
		   <i id="thumb2" aria-hidden="true"></i>
		<input type="hidden" id="movId" name="movId"  value="${objectList.movieId}"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="generate()">Generate</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	
	
	
	<style>
		.noReview01{
		 color:red;
		}
		.activeReview01{
		 color:green;
		}
		</style>
	<script>
	getReviewStatus();
	//getGalleryStatus();
	var movieId="";


	function saveRating()
	{

	 	var data = "data=" + '{ "movieId": "' +  $('#ratemovId').val() + '","movieRate": "'
	  	+  $('#rate').val() +'"} ';
	  $.ajax({
	  	    url : "admin.movie.updateMovieStatus",
	  	    type: "POST",
	  	    data : data,
	  	    success: function(data)
	  	    { 
	  	     }
	  	});

		
	}
	function generate()
	{
		var imgsize =  $("input[name='imgsize']:checked").val();
		window.location.href = "admin.common.imageUpload?movieId="+$('#movId').val()+"&imgsize="+imgsize;
	}
	function uploadImage(movieId)
	{ 
		var data=document.getElementById("galleryStatus"+movieId).getAttribute("data");
		 data=JSON.parse(data);
		 if(data)
			 {
		 		if(data.thumb1)
					 $('#thumb1').attr("class","fa fa-check-circle activeReview01" );
				if(data.thumb2)
					 $('#thumb2').attr("class","fa fa-check-circle activeReview01" );
			 }
		 else
		  {		
			  $('#thumb1').removeAttr("class");
			  $('#thumb2').removeAttr("class");

		}
		$('#movId').val(movieId);
		$('#myModal').modal('show'); 
		
		 
	}
	function updateRating(movieId,movieName,movieRate)
	{
		$('#movieNameTitle').html("Rating : "+movieName);
		$('#ratemovId').val(movieId);
		$('#rate').val(movieRate);
		$('#updatRate').modal('show'); 	
	}
	function getReviewStatus()
	{
		movieId = $("input[id='movieIds']")
        .map(function(){return $(this).val();}).get(); 
       
		var  data= "data=" + '{ "movieIds": "' + movieId + '"} ';
		 
		$.ajax({
	  	    url : "admin.movie.getStatus",
	  	    type: "POST",
	  	    data : data,
	  	    success: function(data)
	  	    {
		  	    data=JSON.parse(data);
		  	 	 if(data['review']!='null' && data['review'].length>0)
			  	 	 {
		  	 			var review=JSON.parse(data['review']);
		  	  			 for(i=0;i<review.length;i++)
			  				 {
				  				$("#reviewStatus"+review[i].movieId).attr("class","activeReview01") ;
			  				 }
			  	 	  }
		  	 	 if(data['cinema']!='null' && data['cinema'].length>0)
		  	 	 {
		  	 		var cinema=JSON.parse(data['cinema']);
		  	 		 for(i=0;i<cinema.length;i++)
  				 	 {
		  	 			 var dat=JSON.stringify(cinema[i])
				  		$("#galleryStatus"+cinema[i].movid).attr("class","activeReview01") ;
						$("#galleryStatus"+cinema[i].movid).attr("data",dat) ;
  				 	 }
		  	 	 }
	  	      },
	  	    error: function (jqXHR, textStatus, errorThrown)
	  	    {
	  	 
	  	    }
	  	});
	  	
	 
		 
	}
	function getReviewStatus01()
	{
		movieId = $("input[id='movieIds']")
        .map(function(){return $(this).val();}).get(); 
       
		var  data= "data=" + '{ "movieIds": "' + movieId + '"} ';
		 
		$.ajax({
	  	    url : "admin.movie.reviewStatus",
	  	    type: "POST",
	  	    data : data,
	  	    success: function(data)
	  	    {
		  	    data=JSON.parse(data);
		  	   
		  	    for(i=0;i<data.length;i++)
			  	 {
				  	$("#reviewStatus"+data[i].movieId).attr("class","activeReview01") ;
			  	 }
	  	     
	  	    },
	  	    error: function (jqXHR, textStatus, errorThrown)
	  	    {
	  	 
	  	    }
	  	});
	  	
	 
		 
	}

	function getGalleryStatus()
	{
		movieId = $("input[id='movieIds']")
        .map(function(){return $(this).val();}).get(); 
       
		var  data= "data=" + '{ "movieIds": "' + movieId + '"} ';
		 
		$.ajax({
	  	    url : "admin.movie.galleryStatus",
	  	    type: "POST",
	  	    data : data,
	  	    success: function(data)
	  	    {
		  	    data=JSON.parse(data);
		  	     for(i=0;i<data.length;i++)
			  	 {
				  	 var dat=JSON.stringify(data)
				  	$("#galleryStatus"+data[i].movid).attr("class","activeReview01") ;
					$("#galleryStatus"+data[i].movid).attr("data",dat) ;
			  	 }
	  	     
	  	    },
	  	    error: function (jqXHR, textStatus, errorThrown)
	  	    {
	  	 
	  	    }
	  	});
	  	
	 
		 
	}

	
function updateReview(movieId,movieName)
{
	 	window.location.href = "admin.movie.editReview?movieId="
				+movieId+"&movieName="+movieName; 
	 
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