<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
<jsp:include page="../HomeHeader.jsp" />
<title>Create Category Type</title>
<div class="row">
<div class="col-sm-3 clearfix"></div>
<div class="col-sm-5 clearfix">
<h5>Category Type Setup</h5><hr/><br/> 
<form>
  <div class="form-group">
    <label for="exampleInputEmail1"> Name</label>
    <input type="hidden" name="catId" id ="catTypeId" value="${response.object.catTypeId}"/>
    <input type="Text" class="form-control" id="catTypeName" value="${response.object.catTypeName}" aria-describedby="langNameHelp" placeholder="Enter Name">
     </div>
  <div class="form-group">
    <label for="exampleInputPassword1"> Code</label>
    <input type="Text" class="form-control" id="catTypeCode" value="${response.object.catTypeCode}" placeholder="Category Type Code">
  </div>
  
  <button type="button" onClick="saveCategory()" class="btn btn-primary">Submit</button>
</form>
 </div>
<div class="col-sm-4 clearfix"></div>
</div>
<script>function saveCategory()
{
	var catId=$('#catTypeId').val();
	if(catId.trim().length==0)
		catId=0;
			
	$.ajax({
		url : 'admin.categoryType.save',
		type : 'POST',

		data : "data=" + '{ "catTypeId": "' + catId+'","catTypeCode": "' + $('#catTypeCode').val()+'","catTypeName": "' + $('#catTypeName').val() + '"} ',
		processData : false,
		success : function(data) {
			if (data == "T") {
				window.location.href = "/admin.categoryType.view";
			} else {
				alert("Invalid Category Type Code");
			}
		}

	});

	}</script>

<jsp:include page="../HomeFooter.jsp" />
	

	