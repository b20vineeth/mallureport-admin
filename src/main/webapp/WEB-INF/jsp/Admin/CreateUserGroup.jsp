<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page='AccountMenu.jsp'></jsp:include>
<script src="http://localhost/blog/js/Validation.js?i=1f"></script>
<script src="http://localhost/blog/js/Commonlibrary.js"></script>
<div class="container text-center">
	<div class="row">
		<div class="col-sm-3 well">

			<jsp:include page='AccountSideMenu.jsp'></jsp:include>

		</div>
		<div class="col-sm-9">
			<div class="panel panel-default text-left">
				<div class="panel-body">

					<form id="form" class="form-horizontal" onsubmit="return false">
						<fieldset>

							<!-- Form Name -->
							<legend>Create Group</legend>

							<!-- Text input-->
							<div class="form-group">


								<label class="col-md-3 control-label" for="firstName"> 
									Group Name</label>
								<div class="col-md-6">
									<input id="groupName" name="groupName" type="text"
										onchange="validateGroupName(this.value)"
										placeholder="Group Name" class="form-control input-md"
										required="">

								</div>
								<div class="col-md-3 fontsize14" id="lbl_GroupName"></div>



							</div>


							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-3 control-label" for="GroupCode"> Group Code</label>
								<div class="col-md-6">
									<input id="groupCode" name="groupCode" type="text"
										onChange="validateGroupCode(this.value)"
										placeholder="Group Code" class="form-control input-md"
										required="">

								</div>
								<div class="col-md-3 fontsize14" id="lbl_GroupCode"></div>
							</div>

						 
							<div class="form-group">
								<div class="col-md-3"></div>
								<div class="col-md-9">
									<button id="button1id" name="button1id" class="btn btn-success"
										onClick="submitBtn()">Save</button>
									<button id="button2id" name="button2id" class="btn btn-default"
										onClick="reset()">Clear</button>
								</div>
							</div>

						</fieldset>
					</form>

					<div id="result"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function resetBtn() {
	console.log("reset()");
	$('#lbl_GroupCode').html("");
	$('#lbl_GroupName').html("");
	$("#button1id").attr("disabled", false);
	$('#result').html("");
	$('#form').trigger("reset");

}
var result = false;

function validateGroupCode(value) {
	if (validateCode(value, 4)) {

		var datas = "datas=" + $('#groupCode').val();

		$
				.ajax({
					type : 'POST',
					url : "/adminRegisterValidateGroupCode",
					data : datas,
					dataType : "text",
					success : function(resultData) {
						console.log(resultData);
						var resultData = JSON.parse(resultData);
						console.log(resultData);
						if (resultData.result) {
							$('#lbl_GroupCode')
									.html(
											'<span class="color-green glyphicon glyphicon-ok-sign"></span>');

						} else {
							$('#lbl_GroupCode')
									.html(
											'<span class="color-red glyphicon glyphicon-remove-circle"></span>');

						}

					}
				});

		return true;
	} else {
		$('#lbl_GroupCode')
				.html(
						'<span class="color-red glyphicon glyphicon-remove-circle"></span>');
		return false;
	}
}

function validateGroupName(value) {

	if (validateAlbhabet(value, 3)) {
		$('#lbl_GroupName')
				.html(
						'<span class="color-green glyphicon glyphicon-ok-sign"></span>');
		return true;

	} else {
		$('#lbl_GroupName')
				.html(
						'<span class="color-red glyphicon glyphicon-remove-circle"></span>');
		return false;
	}

} 
function submitBtn() {

	var groupCode=validateGroupCode($('#groupCode').val()) ;
	var groupName=validateGroupName($('#groupName').val());
	 
	console.log("groupCode "+groupCode);
	console.log("groupName "+groupName);
	if (groupCode && groupName)
	 {
		 console.log("Enter");
		$("#button1id").attr("disabled", true);
		var datas = "datas=" + JSON.stringify($('#form').serializeObject());

		$
				.ajax({
					type : 'POST',
					url : "/saveUserGroup",
					data : datas,
					dataType : "text",
					success : function(resultData) {
						var resultData = JSON.parse(resultData);
						console.log(resultData);
						if (resultData.result) {
							$('#result')
									.html(
											"<div align='center' class='color-green'> Success ..</div><br/>");
							load();
						} else {
							$('#result')
									.html(
											"<div align='center'  class='color-red'> oops!  ..</div>");
							$("#button1id").attr("disabled", false);
						}

					}
				});
	}

}

function load() {
	setTimeout("window.open(self.location, '_self');", 3000);
}
</script>


<jsp:include page='AccountFooter.jsp'></jsp:include>
