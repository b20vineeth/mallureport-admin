
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<jsp:include page="../../template/WebHeader.jsp" />

<script src="resources/js/cinema_script.js"></script>
<body onload="activateBody()">

	<jsp:include page="../../template/WebMenu.jsp" />
	<div class="site-main-container">
		<!-- Start top-post Area -->
		<section class="top-post-area pt-10">
			<div class="container no-padding">
				<div class="row small-gutters">
					<div class="col-lg-8 top-post-left">
						<div id="carouselExampleSlidesOnly" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
							 
							
							 

								<%
									int i = 0;
								%>
								<c:forEach items="${response.objectMapList['slideShow']}"
									var="object">
									<%
										i++;
									%>
									<div
										class="carousel-item
                            	<%if (i == 1) {%> active<%}%>">
										<img class="d-block w-100" src="${object.picUrl}"
											alt="First slide">
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-lg-4 top-post-right">


						<%
							i = 0;
						%>
						<c:forEach items="${response.objectMapList['slideShow-relative']}"
							var="object">

							<%
								i++;
							%>
							<div class="single-top-post<%if (i == 2) {%> mt-10<%}%>">
								<div class="feature-image-thumb relative">
									<div class="overlay overlay-bg"></div>
									<img class="img-fluid" src="${object.picUrl}" alt="">
								</div>
								<div class="top-post-details">
									<ul class="tags">
										<li><a href="#">Recommended</a></li>
									</ul>
									<a href="image-post.html">
										<h4>${object.title}</h4>
									</a>

								</div>
							</div>

						</c:forEach>



					</div>
				</div>
			</div>
		</section>



		<!-- End top-post Area -->
		<!-- Start latest-post Area -->
		<section class="latest-post-area pb-120">
			<div class="container no-padding">
				<div class="row">
					<div class="col-lg-8 post-list">
						<!-- Start popular-post Area -->
						<div class="popular-post-wrap">
							<h4 class="title">Running Movies</h4>


							<nav>
								<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
									<a class="nav-item nav-link active" id="nav-running-movies-mal-tab"
										data-toggle="tab" href="#nav-running-movies-mal" role="tab"
										aria-controls="nav-running-movies-mal" aria-selected="true">Malayalam
									</a> <a class="nav-item nav-link" id="nav-running-movies-tn-tab"
										data-toggle="tab" href="#nav-running-movies-tn" role="tab"
										aria-controls="nav-running-movies-tn" aria-selected="false">Tamil
									</a> <a class="nav-item nav-link" id="nav-running-movies-bl-tab"
										data-toggle="tab" href="#nav-running-movies-bl" role="tab"
										aria-controls="nav-running-movies-bl" aria-selected="false">Bollywood
									</a> <a class="nav-item nav-link" id="nav-running-movies-hl-tab"
										data-toggle="tab" href="#nav-running-movies-hl" role="tab"
										aria-controls="nav-running-movies-hl" aria-selected="false">Hollywood
									</a>
								</div>
							</nav>
 
							<section id="running-Movies" class="project-tab">
								 <div class="tab-content" id="nav-tabContent-running-movies">
									 <div class="spinner-border text-secondary" role="status">
  											<span class="sr-only">Loading...</span>
									</div>
 								 </div>

							</section>
							<script>
							
							</script>


						</div>
						<!-- End popular-post Area -->
						<!-- Start popular-post Area -->
						<div class="popular-post-wrap">
							<h4 class="title">Popular Posts</h4>
							<div id='content-popular-post'></div>

 
						</div>
						<!-- End popular-post Area -->






						<div class="popular-post-wrap">
							<h4 class="title">Upcomming Movies</h4>

 



							<section id="tabs" class="project-tab">
								<nav>
									<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
										<a class="nav-item nav-link active"
											id="nav-upcomming-movies-mal-tab" data-toggle="tab"
											href="#nav-upcomming-movies-mal" role="tab"
											aria-controls="nav-upcomming-movies-mal" aria-selected="true">Malayalam
										</a> <a class="nav-item nav-link" id="nav-upcomming-movies-tn-tab"
											data-toggle="tab" href="#nav-upcomming-movies-tn" role="tab"
											aria-controls="nav-upcomming-movies-tn" aria-selected="false">Tamil
										</a> <a class="nav-item nav-link" id="nav-upcomming-movies-bl-tab"
											data-toggle="tab" href="#nav-upcomming-movies-bl" role="tab"
											aria-controls="nav-upcomming-movies-bl" aria-selected="false">Bollywood
										</a> <a class="nav-item nav-link" id="nav-upcomming-movies-hl-tab"
											data-toggle="tab" href="#nav-upcomming-movies-hl" role="tab"
											aria-controls="nav-upcomming-movies-hl" aria-selected="false">Hollywood
										</a>
									</div>
								</nav>



								<div class="tab-content" id="nav-tabContent-upcomming-movies">

									  <div class="spinner-border text-secondary" role="status">
  											<span class="sr-only">Loading...</span>
									  </div>

								</div>

							</section>



						</div>






						<!-- End popular-post Area -->
						<!-- Start relavent-story-post Area -->
						<div class="relavent-story-post-wrap mt-30">
							<h4 class="title">Movie Review</h4>


							<div class="relavent-story-list-wrap">
								<div id="listreview"></div>
 							</div>
						</div>
						<!-- End relavent-story-post Area -->



					</div>
					<div class="col-lg-4">
						<div class="sidebars-area">
							<div class="single-sidebar-widget editors-pick-widget">
								<h6 class="title">Editor’s Pick</h6>
								<div class="editors-pick-post">
									<div class="feature-img-wrap relative">
										<div class="feature-img relative">
											<div class="overlay overlay-bg"></div>
											<img class="img-fluid" src="${url}img/e1.jpg" alt="">
										</div>
										<ul class="tags">
											<li><a href="#">Travel</a></li>
										</ul>
									</div>
									<div class="details">
										<a href="image-post.html">
											<h4 class="mt-20">A Discount Toner Cartridge Is Better
												Than Ever.</h4>
										</a>
										<ul class="meta">
											<li><a href="#"><span class="lnr lnr-user"></span>Mark
													wiens</a></li>
											<li><a href="#"><span class="lnr lnr-calendar-full"></span>03
													April, 2018</a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span>06
											</a></li>
										</ul>
										<p class="excert">Lorem ipsum dolor sit amet,
											consecteturadip isicing elit, sed do eiusmod tempor
											incididunt ed do eius.</p>
									</div>
									<div class="post-lists">
										<div class="single-post d-flex flex-row">
											<div class="thumb">
												<img src="${url}img/e2.jpg" alt="">
											</div>
											<div class="detail">
												<a href="image-post.html">
													<h6>Help Finding Information Online is so easy</h6>
												</a>
												<ul class="meta">
													<li><a href="#"><span
															class="lnr lnr-calendar-full"></span>03 April, 2018</a></li>
													<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
												</ul>
											</div>
										</div>
										<div class="single-post d-flex flex-row">
											<div class="thumb">
												<img src="${url}img/e3.jpg" alt="">
											</div>
											<div class="detail">
												<a href="image-post.html">
													<h6>Compatible Inkjet Cartr world famous</h6>
												</a>
												<ul class="meta">
													<li><a href="#"><span
															class="lnr lnr-calendar-full"></span>03 April, 2018</a></li>
													<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
												</ul>
											</div>
										</div>
										<div class="single-post d-flex flex-row">
											<div class="thumb">
												<img src="${url}img/e4.jpg" alt="">
											</div>
											<div class="detail">
												<a href="image-post.html">
													<h6>5 Tips For Offshore Soft Development</h6>
												</a>
												<ul class="meta">
													<li><a href="#"><span
															class="lnr lnr-calendar-full"></span>03 April, 2018</a></li>
													<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="single-sidebar-widget ads-widget">
								<img class="img-fluid" src="${url}img/sidebar-ads.jpg" alt="">
							</div>
							<div class="single-sidebar-widget newsletter-widget">
								<h6 class="title">Newsletter</h6>
								<p>Here, I focus on a range of items andfeatures that we use
									in life without giving them a second thought.</p>
								<div class="form-group d-flex flex-row">
									<div class="col-autos">
										<div class="input-group">
											<input class="form-control" placeholder="Email Address"
												onfocus="this.placeholder = ''"
												onblur="this.placeholder = 'Email Address'" type="text">
										</div>
									</div>
									<a href="#" class="bbtns">Subcribe</a>
								</div>
								<p>You can unsubscribe us at any time</p>
							</div>
							<div class="single-sidebar-widget most-popular-widget">
								<h6 class="title">Most Popular</h6>
								<div class="single-list flex-row d-flex">
									<div class="thumb">
										<img src="${url}img/m1.jpg" alt="">
									</div>
									<div class="details">
										<a href="image-post.html">
											<h6>Help Finding Information Online is so easy</h6>
										</a>
										<ul class="meta">
											<li><a href="#"><span class="lnr lnr-calendar-full"></span>03
													April, 2018</a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
										</ul>
									</div>
								</div>
								<div class="single-list flex-row d-flex">
									<div class="thumb">
										<img src="${url}img/m2.jpg" alt="">
									</div>
									<div class="details">
										<a href="image-post.html">
											<h6>Compatible Inkjet Cartr world famous</h6>
										</a>
										<ul class="meta">
											<li><a href="#"><span class="lnr lnr-calendar-full"></span>03
													April, 2018</a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
										</ul>
									</div>
								</div>
								<div class="single-list flex-row d-flex">
									<div class="thumb">
										<img src="${url}img/m3.jpg" alt="">
									</div>
									<div class="details">
										<a href="image-post.html">
											<h6>5 Tips For Offshore Soft Development</h6>
										</a>
										<ul class="meta">
											<li><a href="#"><span class="lnr lnr-calendar-full"></span>03
													April, 2018</a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
										</ul>
									</div>
								</div>
								<div class="single-list flex-row d-flex">
									<div class="thumb">
										<img src="${url}img/m4.jpg" alt="">
									</div>
									<div class="details">
										<a href="image-post.html">
											<h6>5 Tips For Offshore Soft Development</h6>
										</a>
										<ul class="meta">
											<li><a href="#"><span class="lnr lnr-calendar-full"></span>03
													April, 2018</a></li>
											<li><a href="#"><span class="lnr lnr-bubble"></span>06</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="single-sidebar-widget social-network-widget">
								<h6 class="title">Social Networks</h6>
								<ul class="social-list">
									<li
										class="d-flex justify-content-between align-items-center fb">
										<div class="icons d-flex flex-row align-items-center">
											<i class="fa fa-facebook" aria-hidden="true"></i>
											<p>983 Likes</p>
										</div> <a href="#">Like our page</a>
									</li>
									<li
										class="d-flex justify-content-between align-items-center tw">
										<div class="icons d-flex flex-row align-items-center">
											<i class="fa fa-twitter" aria-hidden="true"></i>
											<p>983 Followers</p>
										</div> <a href="#">Follow Us</a>
									</li>
									<li
										class="d-flex justify-content-between align-items-center yt">
										<div class="icons d-flex flex-row align-items-center">
											<i class="fa fa-youtube-play" aria-hidden="true"></i>
											<p>983 Subscriber</p>
										</div> <a href="#">Subscribe</a>
									</li>
									<li
										class="d-flex justify-content-between align-items-center rs">
										<div class="icons d-flex flex-row align-items-center">
											<i class="fa fa-rss" aria-hidden="true"></i>
											<p>983 Subscribe</p>
										</div> <a href="#">Subscribe</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End latest-post Area -->
	</div>
	<script>
	function activateBody() {
		var object ='${response.resposeObject}';

		var object = object.replace(/\\n/g, "\\n").replace(/\\'/g, "\\'").replace(/\\"/g, '\\"').replace(/\\&/g, "\\&").replace(/\\r/g, "\\r").replace(/\\t/g, "\\t").replace(/\\b/g, "\\b").replace(/\\f/g, "\\f");
		object = object.replace(/[\u0000-\u0019]+/g,""); 
 
		var obj = JSON.parse(object);
		domain=obj.domain;
		var runningMovies=obj.jsonobj['running-movies'];
		var upcommingMovies=obj.jsonobj['upcomming-movies'];
		var recommendMovies=obj.jsonobj['recommend-movies'];
		var movieReviews=obj.jsonobj['movie-review'];
		
		tag="Running movies ";
		link='running-movies';
		updateMovies(runningMovies,'running-movies');
		tag="Upcomming movies ";
		link='upcomming-movies';
		updateMovies(runningMovies,'upcomming-movies');
		tag="Popular movies ";
		link='Popular-movies';
		recommedMovies(recommendMovies);
		movieReview(movieReviews);
		
	}
	function movieReview(content)
	{
		var html="";
		for (i in content) {
			
			html+=populateMovieReview(content[i]);
		}	 
		$('#listreview').html(html);
	}
	function populateMovieReview(obj)
	{
		var html="";
		for (i in obj) {
			console.log(obj[i]);
			html+=' <div class="single-relavent-post row align-items-center"> <div class="col-lg-5 post-left"> <div class="feature-img relative">';
			html+=' <div class="overlay overlay-bg"></div> <img class="img-fluid" src="'+obj[i].thumbnail+'" alt="">';
			html+=' </div> <ul class="tags"> <li><a href="'+domain+'/cinema/Movie-Review">Movie Review</a></li> </ul> </div>';

			html+=' <div class="col-lg-7 post-right"> <a href="'+domain+'/cinema/Movie-Review/'+obj[i].url+'"> <h4>'+obj[i].title+'</h4> </a><ul class="meta"> ';
			html+=' <li><a href="#">Rating <div class="ratings"> <div class="empty-stars"></div> <div class="full-stars" style="width:'+obj[i].movieRate+'%"></div>';
			html+='</div> </a></li> <li><a href="#"><span class="lnr lnr-calendar-full"></span>'+obj[i].releaseDate+'</a></li>';
			html+='</ul> <p class="excert">'+obj[i].shortDesc+'</p> </div> </div>';

		}
		
		return html;
	}
	</script>
	<jsp:include page="../../template/WebFooter.jsp" />
</body>
</html>