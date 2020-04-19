
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<jsp:include page="../../template/WebHeader.jsp" />
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
										<li><a href="#">Food Habit</a></li>
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

							<c:set var="category">Movie-R</c:set>

							<% int j=0; %>
							<c:forEach var="objectMapList"
										items="${response.objectMapList}">
									
 								<c:if test="${fn:containsIgnoreCase(category, objectMapList.key)}">
							       
							       	<c:forEach items="${objectMapList.value}" var="object">
									<%j++; if(j%4==1){ %>
									
									<div class="row mt-20 medium-gutters">
									<%} %>
							 			 <div class="col-lg-3 single-popular-post">
 											<div class="feature-img-wrap relative">
													<div class="feature-img relative">
														<div class="overlay overlay-bg"></div>
															<img class="img-fluid" src="${object.thumb1}" alt="">
													</div>
 											</div>
											<div class="details">
												<a href="${object.url}">
													<h4>${object.title}</h4>
												</a>
 								    		</div>
 						       			 </div>
 						       			 <%if(j%4==0){ %>
									</div>
									<%} %>
									</c:forEach>
									
							</c:if>
						</c:forEach>
							
							
							
 						</div>
						<!-- End popular-post Area -->
						<!-- Start popular-post Area -->







						<div class="popular-post-wrap">
							<h4 class="title">Upcomming Movies</h4>
 							<c:set var="category">Movie-U</c:set>
 						     
							 

							<%  j=0; %>
							<c:forEach var="objectMapList"
										items="${response.objectMapList}">
									
 								<c:if test="${fn:containsIgnoreCase(category, objectMapList.key)}">
							       
							       	<c:forEach items="${objectMapList.value}" var="object">
									<%j++; if(j%4==1){ %>
									
									<div class="row mt-20 medium-gutters">
									<%} %>
							 			 <div class="col-lg-3 single-popular-post">
 											<div class="feature-img-wrap relative">
													<div class="feature-img relative">
														<div class="overlay overlay-bg"></div>
															<img class="img-fluid" src="${object.thumb1}" alt="">
													</div>
 											</div>
											<div class="details">
												<a href="${object.url}">
													<h4>${object.title}</h4>
												</a>
 								    		</div>
 						       			 </div>
 						       			 <%if(j%4==0){ %>
									</div>
									<%} %>
									</c:forEach>
									
							</c:if>
						</c:forEach>
						 

						</div>






						<!-- End popular-post Area -->
						<!-- Start relavent-story-post Area -->




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
	<jsp:include page="../../template/WebFooter.jsp" />
</body>
</html>