<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="../../WEB-INF/MenuHandler.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html>
<head>
<title>Easy Admin Panel an Admin Panel Category Flat Bootstrap
	Responsive Website Template | Home :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Easy Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link href='<c:url value="/resource/css/bootstrap.min.css"/>'
	rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href='<c:url value="/resource/css/style.css?d=11"/>'
	rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href='<c:url value="/resource/css/font-awesome.css"/>'
	rel="stylesheet">
<!-- jQuery -->
<!-- lined-icons -->
<link rel="stylesheet"
	href='<c:url value="/resource/css/icon-font.min.css"/>' type='text/css' />
<!-- //lined-icons -->
<!-- chart -->
<script src="js/Chart.js"></script>
<!-- //chart -->
<!--animate-->
<link href='<c:url value="/resource/css/animate.css"/>' rel="stylesheet"
	type="text/css" media="all">
<script src='<c:url value="/resource/js/wow.min.js"/>'></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!----webfonts--->
<link
	href='https://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!---//webfonts--->
<!-- Meters graphs -->
<script src='<c:url value="/resource/js/jquery-1.10.2.min.js"/>'></script>
<!-- Placed js at the end of the document so the pages load faster -->

</head>

<body class="sticky-header left-side-collapsed" onload="init()">
	<section> <!-- left side start-->
	<div class="left-side sticky-left-side">

		<!--logo and iconic logo start-->
		<div class="logo">
			<h1>
				<a href="/">Easy <span>Admin</span></a>
			</h1>
		</div>
		<div class="logo-icon text-center">
			<a href="index.html"><i class="lnr lnr-home"></i> </a>
		</div>

		<!--logo and iconic logo end-->
		<div class="left-side-inner">

			<!--sidebar nav start-->
			<ul class="nav nav-pills nav-stacked custom-nav">
				<li class="active"><a href="index.html"><i
						class="lnr lnr-power-switch"></i><span>Dashboard</span></a></li>


				<li class="menu-list"><a href="#"><i class="lnr lnr-cog"></i>
						<span>Settings</span></a>
					<ul class="sub-menu-list">

						<li><a href="#SET01-privilege" onClick="gotoPage('SET01')">SET01
								Privilege</a></li>
						<li><a href="#SET02-privilege" onClick="gotoPage('SET02')">SET02
								Privilege Group</a></li>
						<li><a href="#SET03-user" onClick="gotoPage('SET03')">SET03
								User</a></li>
						<li><a href="#SET04-userGroup" onClick="gotoPage('SET04')">SET04
								User Group</a></li>
						<li><a href="#SET05-allotment" onClick="gotoPage('SET05')">SET05
								Allot</a></li>

					</ul></li>


				<li><a href="forms.html"><i class="lnr lnr-spell-check"></i>
						<span>Forms</span></a></li>
				<li><a href="tables.html"><i class="lnr lnr-menu"></i> <span>Tables</span></a></li>
				<li class="menu-list"><a href="#"><i
						class="lnr lnr-envelope"></i> <span>MailBox</span></a>
					<ul class="sub-menu-list">
						<li><a href="inbox.html">Inbox</a></li>
						<li><a href="compose-mail.html">Compose Mail</a></li>
					</ul></li>
				<li class="menu-list"><a href="#"><i
						class="lnr lnr-indent-increase"></i> <span>Menu Levels</span></a>
					<ul class="sub-menu-list">
						<li><a href="charts.html">Basic Charts</a></li>
					</ul></li>
				<li><a href="codes.html"><i class="lnr lnr-pencil"></i> <span>Typography</span></a></li>
				<li><a href="media.html"><i class="lnr lnr-select"></i> <span>Media
							Css</span></a></li>
				<li class="menu-list"><a href="#"><i class="lnr lnr-book"></i>
						<span>Pages</span></a>
					<ul class="sub-menu-list">
						<li><a href="sign-in.html">Sign In</a></li>
						<li><a href="sign-up.html">Sign Up</a></li>
						<li><a href="blank_page.html">Blank Page</a></li>
					</ul></li>
			</ul>
			<!--sidebar nav end-->
		</div>
	</div>
	<!-- left side end--> <!-- main content start-->
	<div class="main-content">
		<!-- header-starts -->
		<div class="header-section">

			<!--toggle button start-->
			<!-- a class="toggle-btn  menu-collapsed"><i class="fa fa-bars"></i></a-->
			<!--toggle button end-->

			<!--notification menu start -->
			<div class="menu-right">
				<div class="user-panel-top">
					<div class="profile_details_left">
						<ul class="nofitications-dropdown">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false"><i
									class="fa fa-envelope"></i><span class="badge">3</span></a>

								<ul class="dropdown-menu">
									<li>
										<div class="notification_header">
											<h3>You have 3 new messages</h3>
										</div>
									</li>
									<li><a href="#">
											<div class="user_img">
												<img src='<c:url value="/resource/images/1.png"/>' alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li class="odd"><a href="#">
											<div class="user_img">
												<img
													src='<c:url value="/resource/images/1.png"/>images/1.png"/>'
													alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li><a href="#">
											<div class="user_img">
												<img
													src='<c:url value="/resource/images/1.png"/>images/1.png"/>'
													alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li>
										<div class="notification_bottom">
											<a href="#">See all messages</a>
										</div>
									</li>
								</ul></li>
							<li class="login_box" id="loginContainer">
								<div class="search-box">
									<div id="sb-search" class="sb-search">
										<form>
											<input class="sb-search-input"
												style="text-transform: uppercase;" placeholder="Screen Id"
												type="search" id="search" onchange="gotoPage(this.value)">
											<input class="sb-search-submit" type="submit" value="">
											<span class="sb-icon-search"> </span>
										</form>
									</div>
								</div> <!-- search-scripts --> <script
									src="<c:url value='/resource/js/classie.js'/>"></script> <script
									src="<c:url value='/resource/js/uisearch.js'/>"></script> <script>
										new UISearch(document
												.getElementById('sb-search'));
									</script> <!-- //search-scripts -->
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false"><i
									class="fa fa-bell"></i><span class="badge blue">3</span></a>
								<ul class="dropdown-menu">
									<li>
										<div class="notification_header">
											<h3>You have 3 new notification</h3>
										</div>
									</li>
									<li><a href="#">
											<div class="user_img">
												<img src="<c:url value='/resource/images/1.jpg'/>" alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li class="odd"><a href="#">
											<div class="user_img">
												<img src="<c:url value='/resource/images/1.jpg'/>" alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li><a href="#">
											<div class="user_img">
												<img src="<c:url value='/resource/images/1.jpg'/>" alt="">
											</div>
											<div class="notification_desc">
												<p>Lorem ipsum dolor sit amet</p>
												<p>
													<span>1 hour ago</span>
												</p>
											</div>
											<div class="clearfix"></div>
									</a></li>
									<li>
										<div class="notification_bottom">
											<a href="#">See all notification</a>
										</div>
									</li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false"><i
									class="fa fa-tasks"></i><span class="badge blue1">22</span></a>
								<ul class="dropdown-menu">
									<li>
										<div class="notification_header">
											<h3>You have 8 pending task</h3>
										</div>
									</li>
									<li><a href="#">
											<div class="task-info">
												<span class="task-desc">Database update</span><span
													class="percentage">40%</span>
												<div class="clearfix"></div>
											</div>
											<div class="progress progress-striped active">
												<div class="bar yellow" style="width: 40%;"></div>
											</div>
									</a></li>
									<li><a href="#">
											<div class="task-info">
												<span class="task-desc">Dashboard done</span><span
													class="percentage">90%</span>
												<div class="clearfix"></div>
											</div>

											<div class="progress progress-striped active">
												<div class="bar green" style="width: 90%;"></div>
											</div>
									</a></li>
									<li><a href="#">
											<div class="task-info">
												<span class="task-desc">Mobile App</span><span
													class="percentage">33%</span>
												<div class="clearfix"></div>
											</div>
											<div class="progress progress-striped active">
												<div class="bar red" style="width: 33%;"></div>
											</div>
									</a></li>
									<li><a href="#">
											<div class="task-info">
												<span class="task-desc">Issues fixed</span><span
													class="percentage">80%</span>
												<div class="clearfix"></div>
											</div>
											<div class="progress progress-striped active">
												<div class="bar  blue" style="width: 80%;"></div>
											</div>
									</a></li>
									<li>
										<div class="notification_bottom">
											<a href="#">See all pending task</a>
										</div>
									</li>
								</ul></li>
							<div class="clearfix"></div>
						</ul>
					</div>
					<div class="profile_details">
						<ul>
							<li class="dropdown profile_details_drop"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false">
									<div class="profile_img">
										<span
											style="background:url(<c:url value='/resource/images/1.jpg'/>) no-repeat center">
										</span>
										<div class="user-name">
											<p>
												Michael<span>Administrator</span>
											</p>
										</div>
										<i class="lnr lnr-chevron-down"></i> <i
											class="lnr lnr-chevron-up"></i>
										<div class="clearfix"></div>
									</div>
							</a>
								<ul class="dropdown-menu drp-mnu">
									<li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
									<li><a href="#"><i class="fa fa-user"></i>Profile</a></li>
									<li><a href="sign-up.html"><i class="fa fa-sign-out"></i>
											Logout</a></li>
								</ul></li>
							<div class="clearfix"></div>
						</ul>
					</div>
					<div class="social_icons">
						<div class="col-md-4 social_icons-left">
							<a href="#" class="yui"><i class="fa fa-facebook i1"></i><span>300<sup>+</sup>
									Likes
							</span></a>
						</div>
						<div class="col-md-4 social_icons-left pinterest">
							<a href="#"><i class="fa fa-google-plus i1"></i><span>500<sup>+</sup>
									Shares
							</span></a>
						</div>
						<div class="col-md-4 social_icons-left twi">
							<a href="#"><i class="fa fa-twitter i1"></i><span>500<sup>+</sup>
									Tweets
							</span></a>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<!--notification menu end -->
		</div>
		<!-- //header-ends -->
		<div id="page-wrapper">








			<div class="but_list">

				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Library</a></li>
					<li class="active">Data</li>
				</ol>
			</div>





			<style>
.table-fixed thead {
	width: 97%;
}

.table-fixed tbody {
	height: 320px;
	overflow-y: auto;
	width: 100%;
}

.table-fixed thead, .table-fixed tbody, .table-fixed tr, .table-fixed td,
	.table-fixed th {
	display: block;
}

.table-fixed tbody td, .table-fixed thead>tr>th {
	float: left;
	
}
.table-fixed  tbody tr:hover td, .table-fixed  tbody tr:hover th {
  background-color:#c9f791; 
}

</style>





			<div class="bs-example4" data-example-id="contextual-table">


				<table class="table table-fixed table-striped" >
					<thead>
						<tr>
						   
							<th class="col-xs-2">#</th>
							<th class="col-xs-7">Name</th>
							<th class="col-xs-2">Points</th>
							<th class="col-xs-1">Points</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr><tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr><tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr><tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr><tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr><tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr>
						<tr>
							
							<td class="col-xs-2">1</td>
							<td class="col-xs-7">Mike Adams</td>
							<td class="col-xs-2">23</td>
							<td class="col-xs-1">23</td>
						</tr>
					</tbody>
				</table>
			</div>



		</div>

	</div>
	<!--footer section start--> <footer>
	<p>
		&copy 2015 Design by <a href="https://w3layouts.com/" target="_blank">w3layouts.</a>
	</p>
	</footer> <!--footer section end--> <!-- main content end--> </section>

	<script src='<c:url value="/resource/js/jquery.nicescroll.js"/>'></script>
	<script src='<c:url value="/resource/js/scripts.js"/>'></script>
	<!-- Bootstrap Core JavaScript -->
	<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
</body>
</html>



<script>
	function init() {
		//alert("Load");
		initMap();

	}
	function gotoPage(val) {
		alert(val);
	}
</script>













