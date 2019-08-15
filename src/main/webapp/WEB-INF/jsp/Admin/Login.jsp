<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    %>
 
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html lang="en">
<head>
	<title>Login  </title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="http://localhost/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="http://localhost/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
	<link rel="stylesheet" type="text/css" href="http://localhost/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="http://localhost/login/css/main.css?u=11">
	<link rel="stylesheet" type="text/css" href="http://localhost/login/css/style.css?h=11020">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				 <form id="loginForm" onsubmit="return false" >
					<span class="login100-form-title p-b-32">
						  Login
					</span>

					<span class="txt1 p-b-11">
						Username
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
						<input class="input100" type="text" name="Username"  id="Username" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="Password" id="pass" >
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="rememberMe">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div>

						<div>
							<a href="#" class="txt3">
								Forgot Password?
							</a>
						</div>
					</div>
					
				 
					
					<div class="container-login100-form-btn">
						<button class="login100-form-btn"  onclick="authenticate()">
							Login
						</button> 
						<div class="padd success" id='loginResult'> </div>
					</div>
					 
			 
			 </form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!--===============================================================================================-->
	<script src="http://localhost/login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="http://localhost/login/vendor/bootstrap/js/popper.js"></script>
	<script src="http://localhost/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="http://localhost/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="http://localhost/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="http://localhost/login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="http://localhost/login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="http://localhost/login/js/main.js"></script>
<script src="http://localhost/login/js/login_script.js?u=s91"></script>


</body>
</html>