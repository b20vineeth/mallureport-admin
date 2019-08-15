<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page='AccountMenu.jsp'></jsp:include>
<script src="http://localhost/blog/js/Validation.js"></script>
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
							<legend>Create Account</legend>

							<!-- Text input-->
							<div class="form-group">


								<label class="col-md-3 control-label" for="firstName">First
									Name</label>
								<div class="col-md-6">
									<input id="firstName" name="firstName" type="text"
										onchange="validateFirstName(this.value)"
										placeholder="First Name" class="form-control input-md"
										required="">

								</div>
								<div class="col-md-3 fontsize14" id="lbl_firstName"></div>



							</div>


							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-3 control-label" for="lastName">Last
									Name</label>
								<div class="col-md-6">
									<input id="lastName" name="lastName" type="text"
										onChange="validateLastName(this.value)"
										placeholder="Last Name" class="form-control input-md"
										required="">

								</div>
								<div class="col-md-3 fontsize14" id="lbl_lastName"></div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-3 control-label" for="email">Email</label>
								<div class="col-md-6">
									<input id="email" onChange="validateEmail(this.value)"
										name="email" type="text" placeholder="email"
										class="form-control input-md" required="">

								</div>
								<div class="col-md-3 fontsize14" id="lbl_email"></div>
							</div>

							<div class="form-group">
								<div class="col-md-3"></div>
								<div class="col-md-9">
									<button id="button1id" name="button1id" class="btn btn-success"
										onClick="submitBtn()">Save</button>
									<button id="button2id" name="button2id" class="btn btn-default"
										onClick="resetBtn()">Clear</button>
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
		$('#lbl_email').html("");
		$('#lbl_firstName').html("");
		$('#lbl_lastName').html("");
		$("#button1id").attr("disabled", false);
		$('#result').html("");
		$('#form').trigger("reset");

	}
	var result = false;

	function validateEmail(value) {
		if (ValidateEmailID(value)) {

			var datas = "datas=" + $('#email').val();

			$
					.ajax({
						type : 'POST',
						url : "/adminRegisterValidateEmail",
						data : datas,
						dataType : "text",
						success : function(resultData) {
							console.log(resultData);
							var resultData = JSON.parse(resultData);
							console.log(resultData);
							if (resultData.result) {
								$('#lbl_email')
										.html(
												'<span class="color-green glyphicon glyphicon-ok-sign"></span>');

							} else {
								$('#lbl_email')
										.html(
												'<span class="color-red glyphicon glyphicon-remove-circle"></span>');

							}

						}
					});

			return true;
		} else {
			$('#lbl_email')
					.html(
							'<span class="color-red glyphicon glyphicon-remove-circle"></span>');
			return false;
		}
	}

	function validateFirstName(value) {

		if (validateAlbhabet(value, 3)) {
			$('#lbl_firstName')
					.html(
							'<span class="color-green glyphicon glyphicon-ok-sign"></span>');
			return true;

		} else {
			$('#lbl_firstName')
					.html(
							'<span class="color-red glyphicon glyphicon-remove-circle"></span>');
			return false;
		}

	}
	function validateLastName(value) {
		if (validateAlbhabet(value, 0)) {
			$('#lbl_lastName')
					.html(
							'<span class="color-green glyphicon glyphicon-ok-sign"></span>');
			return true;

		} else {
			$('#lbl_lastName')
					.html(
							'<span class="color-red glyphicon glyphicon-remove-circle"></span>');
			return false;
		}
	}

	function submitBtn() {

		var email=validateEmail($('#email').val()) ;
		var lastName=validateLastName($('#lastName').val());
		var firstName= validateFirstName($('#firstName').val());
		console.log("lastName "+lastName);
		console.log("firstName "+firstName);
		if (lastName && firstName)
		 {
			 console.log("Enter");
			$("#button1id").attr("disabled", true);
			var datas = "datas=" + JSON.stringify($('#form').serializeObject());

			$
					.ajax({
						type : 'POST',
						url : "/saveUserByAdmin",
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
