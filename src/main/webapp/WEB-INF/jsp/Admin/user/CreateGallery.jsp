<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Create Gallery</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Gallery Setup</h5>
		<hr />
		<br />
		<c:if test="${not empty response.object.galleryId}">
			<div>
				<img src="/pic/${response.object.thumbnail}" />
			</div>
		</c:if>

		<form id="upload-file-form">

			<div class="row">
				<div class="col-sm-12 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Title </label> <input
							type="Text" class="form-control" id="title"
							value="${response.object.title}" placeholder="Title">



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
						<label for="exampleInputPassword1">Movie</label> <input
							type="Text" class="form-control" id="movie" name="movie"
							value="${response.object.movieTag}" />

					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1"> Profile </label> <input
							type="text" class="form-control" id="profile" name="profile" value="${response.object.profileTag}" />




					</div>
				</div>
			</div>
















			<div class="row">

				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Tag</label> <input type="Text"
							class="form-control" id="tag" value="${response.object.tag}"
							placeholder="#tag" name="tag">
					</div>
				</div>
				<div class="col-sm-6 clearfix">
					<div class="form-group">
						<label for="exampleInputPassword1">Url</label>

						<div class="form-group">
							<input type="Text" class="form-control" id="url"
								value="${response.object.url}" placeholder="url">
						</div>
					</div>
				</div>
			</div>




			<c:if test="${empty response.object.galleryId}">

				<div class="row">


					<div class="col-sm-12 clearfix">
						<div class="form-group">

							<textarea class="form-control" id="galleryContent"
							placeholder=" Gallery">${response.object.shortDesc}</textarea>


						</div>

					</div>
				</div>

			</c:if>
			<input type="hidden" id="galleryId"
				value="${response.object.galleryId}" />



			<button type="button" class="btn btn-primary" onclick="saveData()">Submit</button>
		</form>
	</div>
	<div class="col-sm-1 clearfix"></div>
</div>
<style>
img {
	margin: 5px;
}

.imgDisplay {
	float: left;
	width: 200px;
	height: auto;
}
</style>
<script>
$('input[name="tag"]').amsifySuggestags({
	tagLimit: 10
});
$('input[name="profile"]').amsifySuggestags({
	suggestionsAction : {
		url : 'admin.common.getdata?type=profile'
	},
	<c:if test="${not empty response.object.profile}">
	suggestions:  
		${response.object.profile}
	 ,
	</c:if>
		 whiteList: true	
});

$('input[name="movie"]').amsifySuggestags({
	 
	suggestionsAction : {
		url : 'admin.common.getdata?type=cinema'
	},

	<c:if test="${not empty response.object.movie}">
	suggestions:  
		${response.object.movie}
	 ,
	</c:if>
	whiteList: true			
});
var galleryId = $('#galleryId').val();

if (galleryId.trim().length!=0)
{
	$("#thumbnail").attr('disabled', 'disabled');
	$("#url").attr('disabled', 'disabled');
}

var img=[];
 
$(document).ready(function() {
	   $("#upload-file-input").on("change", uploadFile);
});

function saveData()
{
 
	var galleryId = $('#galleryId').val();
	if (galleryId.trim().length == 0)
		galleryId = 0;

	$.ajax({
		url : 'admin.gallery.save',
		type : 'POST',

		data : "data=" + '{ "galleryId": "' + galleryId + '","galleryUrl": "'
				+ img.toString() + '","shortDesc": "'
				+ $('#shortDesc').val() + '","title": "'
				+ $('#title').val() + '","description": "'
				+ $('#description').val() + '","galleryContent": "' + $('#galleryContent').val() + '","url": "'
				+ $('#url').val() + '","movie": "'
				+ $('#movie').val() + '","profile": "'
				+ $('#profile').val() + '","tag": "' + $('#tag').val()
				+ '"} ',
		processData : false,
		success : function(data) {
			if (data == "T") {
				window.location.href = "/admin.gallery.view";
			} else {
				alert("Invalid Gallery URL");
			}
		}

	});
}
 
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
    		var li = document.createElement('div');
    		img.push(obj.FileName)
    		var file='"'+obj.FileName+'"';
    		var id=Math.random();
    	    li.innerHTML = "<div class='imgDisplay' id='div"+id+"'><img src='"+obj.path+"'/><a href='javascript:picDelete("+file+","+id+")'>Delete</a><div>";  
    	    document.getElementById('uploadedFileList').appendChild(li);
    	  }
    	  
    },
    error: function (data) {
    	 console.log(data);
    }
  });
}  

function picDelete(del,id)
{
	var index=img.indexOf(del);
	if(index>-1)
		img.splice(index, 1);
	document.getElementById("div"+id).style.display = "none";
	 
	console.log(img); 
}




 
</script>

<jsp:include page="../HomeFooter.jsp" />
