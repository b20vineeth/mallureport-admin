<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../HomeHeader.jsp" />
<title>Update Thumbnail</title>
<div class="row">
	<div class="col-sm-1 clearfix"></div>
	<div class="col-sm-9 clearfix">
		<h5>Update Thumbnail</h5>
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

							<div id='progressBar'
								style='height: 20px; border: 2px solid green; margin-bottom: 20px'>
								<div id='bar'
									style='height: 100%; background: #33dd33; width: 0%'></div>
							</div>
							<input id="upload-file-input" type="file" name="uploadfile"
								accept="*" multiple="multiple" />

							<div id="uploadedFileList"></div>



						</div>
					</div>




				</div>

 
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
 
 
function uploadFile() {
	var datas =  new FormData($("#upload-file-form")[0])

	
   $.ajax({
    url: 'admin.gallery.uploadThumbnail?id='+galleryId,
    type: "POST",
    data:datas,
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function (data) { 
         
    	 window.location.href="/admin.gallery.view";
    	  
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
