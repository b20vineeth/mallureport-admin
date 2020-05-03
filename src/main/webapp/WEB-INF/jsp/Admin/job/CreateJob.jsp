<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
<jsp:include page="../HomeHeader.jsp" />
<title>Create Cron Job</title>
<div class="row">
<div class="col-sm-3 clearfix"></div>
<div class="col-sm-5 clearfix">
<h5>Cron Job Setup</h5><hr/><br/> 
<form>
  <div class="form-group">
    <label for="exampleInputEmail1"> Name</label>
    <input type="hidden" name="jobId" id ="jobId" value="${response.object.jobId}"/>
    <input type="Text" class="form-control" id="jobName" value="${response.object.jobName}" aria-describedby="langNameHelp" placeholder="Job Name">
     </div>
  <div class="form-group">
    <label for="exampleInputPassword1"> Type</label>
    <input type="Text" class="form-control" id="type" value="${response.object.type}" placeholder="type">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"> Cron Expresion </label>
    <input type="Text" class="form-control" id="cronExp" value="${response.object.cronExp}" placeholder="Cron Expression">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"> File   </label>
    <input type="Text" class="form-control" id="jobfile" value="${response.object.jobfile}" placeholder="Job File ">
  </div>
  <button type="button" onClick="save()" class="btn btn-primary">Submit</button>
</form>
 </div>
<div class="col-sm-4 clearfix"></div>
</div>
<script>function save()
{
	var job=$('#jobId').val();
	if(job.trim().length==0)
		job=0;
			
	$.ajax({
		url : 'admin.job.save',
		type : 'POST',
 		data : "data=" + '{ "jobId": "' + job + '","jobName": "'
		+ $('#jobName').val() + '","jobfile": "'
		+ $('#jobfile').val() + '","type": "'
		+ $('#type').val() + '","cronExp": "'
		+ $('#cronExp').val() + '"} ',
		processData : false,
		success : function(data) {
			if (data == "T") {
				window.location.href = "/admin.job.view";
			} else {
				alert("Invalid job Code");
			}
		}

	});

	}</script>

<jsp:include page="../HomeFooter.jsp" />
	

	