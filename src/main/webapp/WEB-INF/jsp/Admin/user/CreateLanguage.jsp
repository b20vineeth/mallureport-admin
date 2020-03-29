<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
<jsp:include page="../HomeHeader.jsp" />
<title>Create Language</title>
<div class="row">
<div class="col-sm-3 clearfix"></div>
<div class="col-sm-5 clearfix">
<h5>Language Setup</h5><hr/><br/> 
<form>
  <div class="form-group">
    <label for="exampleInputEmail1"> Name</label>
    <input type="hidden" name="langId" id ="langId" value="${response.object.langId}"/>
    <input type="Text" class="form-control" id="langName" value="${response.object.langName}" aria-describedby="langNameHelp" placeholder="Enter Name">
     </div>
  <div class="form-group">
    <label for="exampleInputPassword1"> Code</label>
    <input type="Text" class="form-control" id="langCode" value="${response.object.langCode}" placeholder="lang Code">
  </div>
  
  <button type="button" onClick="saveLanguage()" class="btn btn-primary">Submit</button>
</form>
 </div>
<div class="col-sm-4 clearfix"></div>
</div>
<script>function saveLanguage()
{
	var lang=$('#langId').val();
	if(lang.trim().length==0)
		lang=0;
			
	$.ajax({
		url : 'admin.lang.save',
		type : 'POST',

		data : "data=" + '{ "langId": "' + lang+'","langCode": "' + $('#langCode').val()+'","langName": "' + $('#langName').val() + '"} ',
		processData : false,
		success : function(data) {
			if (data == "T") {
				window.location.href = "/admin.lang.view";
			} else {
				alert("Invalid Language Code");
			}
		}

	});

	}</script>

<jsp:include page="../HomeFooter.jsp" />
	

	