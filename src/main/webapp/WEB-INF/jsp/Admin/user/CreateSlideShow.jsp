<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create SlideShow</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Slideshow Setup</h5>
		<hr />
		<br />
		<form action="GET" id="upload-file-form">



			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Title</label> <input
							type="Text" class="form-control" id="title"
							value="${response.object.title}" placeholder="Movie Name">

					</div>
				</div>
				 
			</div>
			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						 
<label for="exampleInputPassword1"> Slider </label> 


						<c:choose>
							<c:when test="${response.object.slideflg=='Y'}">
								<input type="radio" id="slideflg" name="slideflg" value="Y"
									checked="checked">
								 
							</c:when>
							<c:otherwise>
								<input type="radio" id="slideflg" name="slideflg" value="Y">
							
							</c:otherwise>
						</c:choose>
							<label for="slideflg">Yes</label>



						<c:choose>
							<c:when test="${response.object.slideflg=='N'}">
								<input type="radio" id="slideflg" name="slideflg" value="N"
									checked="checked">
								</c:when>
							<c:otherwise>
								<input type="radio" id="slideflg" name="slideflg" value="N">
								
							</c:otherwise>
						</c:choose>
						<label for="other">No</label>

 
					</div>
				</div>
				 
			</div>
			
			
			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Link </label> <input
							type="Text" class="form-control" id="link"
							value="${response.object.link}" placeholder="picUrl">

					</div>
				</div>
				 
			</div>
			  <div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
					<c:if test="${empty response.object.slideShowid}">	 	
						<input id="upload-file-input" type="file" name="uploadfile" accept="*" multiple="multiple" />
					</c:if>	
						<input type="hidden" name ="picUrl" id="picUrl" value="${response.object.picUrl}"/>

					</div>
				</div>
				 
			</div>
			<input type="hidden" id="slideShowid" value="${response.object.slideShowid}" />
			<input type="hidden" id="type" value="${response.object.type}" />



			<button type="button" onclick="saveSlideshow()" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<script>


$(document).ready(function() {
	   $("#upload-file-input").on("change", uploadFile);
});


function uploadFile() {
 
   $.ajax({
    url: 'admin.gallery.uploads',
    type: "POST",
    data: new FormData($("#upload-file-form")[0]),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function (data) {  
    	 var objs=JSON.parse(data);
         
     	for (var obj of objs) 
     	  {
     		var file='/pic/'+obj.FileName;
     		$('#picUrl').val(file);
     	  }
    	 
     	  
    },
    error: function (data) {
    	 console.log(data);
    }
  });
}  







	var slideShowid = $('#slideShowid').val();

 
	function saveSlideshow() {
		var slideShowid = $('#slideShowid').val();
		if (slideShowid.trim().length == 0)
			slideShowid = 0;

		var slideflg =  $("input[name='slideflg']:checked").val();
		
		$.ajax({
			url : 'admin.slideshow.save',
			type : 'POST',

			data : "data=" + '{ "slideShowid": "' + slideShowid + '","link": "'
			+ $('#link').val() + '","title": "'
			+ $('#title').val() + '","type": "'
			+ $('#type').val() + '","slideflg": "'
			+ slideflg + '","picUrl": "' + $('#picUrl').val() + '"} ',
			processData : false,
			success : function(data) {
				if (data == "T") {
					window.location.href = "/admin.slideshow.view?type="+$('#type').val();
				} else {
					alert("Invalid slideshow Code");
				}
			}

		});

	}
</script>

<jsp:include page="../HomeFooter.jsp" />
