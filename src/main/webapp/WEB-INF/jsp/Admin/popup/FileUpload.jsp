<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title> Jcrop  </title>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8" />

<script src="resources/js/jquery.min.js"></script>
<script src="resources//js/jquery.Jcrop.js"></script>
<script type="text/javascript">
var widthHeight= new Array();
	jQuery(function($) {
	var imgSize="${imgsize}"
		 var img=imgSize.split("X");
		var jcrop_api;

		initJcrop(img[0],img[1]);
		updateImgSize(imgSize);
		$('#coords').on(
				'change',
				'input',
				function(e) {
					var x1 = $('#x1').val(), x2 = $('#x2').val(), y1 = $('#y1')
							.val(), y2 = $('#y2').val();
					jcrop_api.setSelect([ x1, y1, x2, y2 ]);
				});

	});

	function showCoords(c) {
		$('#x1').val(c.x);
		$('#y1').val(c.y);
		$('#x2').val(c.x2);
		$('#y2').val(c.y2);
		$('#w').val(c.w);
		$('#h').val(c.h);
	};

	function clearCoords() {
		$('#coords input').val('');
	};

	function initJcrop(w,h) {
		$('#target').Jcrop({
			onChange : showCoords,
			onSelect : showCoords,
			onRelease : clearCoords
		}, function() {
			jcrop_api = this;
			jcrop_api.setSelect([ 0, 0, w, h ]);
			jcrop_api.setOptions({
				bgFade : true
			});
			jcrop_api.ui.selection.addClass('jcrop-selection');
		});
	};
</script>
<link rel="stylesheet" href="resources/demo_files/main.css"
	type="text/css" />
<link rel="stylesheet" href="resources/demo_files/demos.css"
	type="text/css" />
<link rel="stylesheet" href="resources//css/jquery.Jcrop.css"
	type="text/css" />

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="jc-demo-box">

					<div id="switchBtn"></div>
					<form id="imgUpload" method="POST" enctype="multipart/form-data">


						<input type="file" id="file" name="files" accept="image/*" />
						<script>
	function savecordinates()
	{
		
		var imgcord=$('#wh').val()+":"+"{'x1':"+$('#x1').val()+",'y1':"+$('#y1').val()+",'w':"+$('#w').val()+",'h':"+$('#h').val()+"}";
		widthHeight.push(imgcord);
		console.log(widthHeight);
	}
	function updateImgSize(wh){
		$('#wh').val(wh);
		}
						
						function changefileSize(w,h){
							
								savecordinates();
								updateImgSize(w+"X"+h);
							  	jcrop_api.destroy();
						         initJcrop(w,h);
							}
							function readURL(input) {
								if (input.files && input.files[0]) {
									var reader = new FileReader();

									reader.onload = function(e) {
										$('#target').attr('src',
												e.target.result);
									}
									reader.readAsDataURL(input.files[0]);
								}
							}
							$("#file").change(function() {
								readURL(this);
							});
							function upload() {
								$.ajax({
									url : 'admin.common.saveimageUpload',
									type : "POST",
									data : new FormData($("#imgUpload")[0]),
									enctype : 'multipart/form-data',
									processData : false,
									contentType : false,
									cache : false,
									timeout : 1000000,
									success : function(data) {
										 if(data=="T")
											 {
											 	window.location.href="admin.movie.view";
											 }

									},
									error : function(data) {
										console.log(data);
									}
								});
							}
						</script>
						<img id="target" /> <br />
						<br />

						<div class="inline-labels">
							<label>X1 <input type="text" style="width: 50px" id="x1"
								name="x1" /></label> <label>Y1 <input type="text"
								style="width: 50px" id="y1" name="y1" /></label> <label>X2 <input
								type="text" style="width: 50px" id="x2" name="x2" /></label> <label>Y2
								<input type="text" style="width: 50px" id="y2" name="y2" />
							</label> <label>W <input type="text" style="width: 50px" id="w"
								name="w" /></label> <label>H <input type="text"
								style="width: 50px" id="h" name="h" /></label>
								<input type="hidden"  id="wh" name="wh"  value="${imgsize}"/>
						 <input type="hidden"  id="movid" name="movid"  value="${movieId}"/>
					
						
						</div>
					</form>



 
					<button onClick="upload()">Upload</button>
					<div class="clearfix"></div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>

