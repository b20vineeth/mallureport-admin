<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../template/Header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-sm-8 clearfix">


			<c:forEach items="${response.objectList}" var="objectList">

				<div class="card clearfix">
					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-sm-4 col-lg-4">
								<img src="http://localhost/picmass/images/pic1.jpg" alt="" />
							</div>
							<div class="col-md-6 col-sm-8 col-lg-8 ">
								<h5>
									<a href="/cinema/${objectList.catCode}">${objectList.catName}</a>
								</h5>

								<div>${objectList.shortDesc}</div>


							</div>
						</div>


					</div>
				</div>


			</c:forEach>








			<div class="clearfix"></div>



			<ul class="pagination pagination-sm">



				<c:choose>
					<c:when test="${response.page.currentPage  == 1}">
						<li class="page-item disabled"><a href="#" class="page-link">
								Previous </a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="${response.domain}/${response.module}/${response.subModule}/Page-${response.page.currentPage  - 1}"><i
								class="material-icons">Previous</i></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" end="${response.page.totalPage}"
					varStatus="loop">
					<c:choose>
						<c:when test="${response.page.currentPage == loop.index}">
							<li class="page-item"><a class="page-link"
								href="${response.domain}/${response.module}/${response.subModule}/Page-${loop.index}">${loop.index}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${response.domain}/${response.module}/${response.subModule}/Page-${loop.index}">${loop.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when
						test="${response.page.currentPage == response.page.totalPage}">
						<li class="page-item  disabled"><a href="${response.domain}/${response.module}/${response.subModule}/Page-${loop.index}" class="page-link"><i
								class="material-icons">Next</i></a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="${response.domain}/${response.module}/${response.subModule}/Page-${response.page.currentPage + 1}"> Next</a></li>
					</c:otherwise>
				</c:choose>



			</ul>











		</div>
		<div class="col-sm-4 clearfix">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">justo odioras justo ac faciDapibus
						ac ac faciDapibusacfaciDapibus DapibusDapibus ac faci Dapibus ac
						faciodio</li>
					<li class="list-group-item">faciDapibus ac faciDapibus
						Dapibusac ac faciDapibusac faciDapibus ac faciDapibus Dapibus ac
						faci ac facilisis in</li>
					<li class="list-group-item">faciDapibus ac faciDapibusac
						faciDapibus ac faciDapibusacac faciDapibus Dapibusac faciDapibus
						ac faciDapibus Dapibus ac faci ac faciDapibusac</li>
					<li class="list-group-item">ac faciDapibusac faciDapibus ac
						faciDapibus ac faciDapibusac Dapibusac faciDapibus ac faciDapibus
						Dapibus ac faci ac faciDapibusac</li>

					<li class="list-group-item">ac faciDapibusac faciDapibus ac
						faciDapibus ac faciDapibusac Dapibusac faciDapibus ac faciDapibus
						Dapibus ac faci ac faciDapibusac</li>

					<li class="list-group-item">ac faciDapibusac faciDapibus ac
						faciDapibus ac faciDapibusac Dapibusac faciDapibus ac faciDapibus
						Dapibus ac faci ac faciDapibusac</li>

					<li class="list-group-item">ac faciDapibusac faciDapibus ac
						faciDapibus ac faciDapibusac Dapibusac faciDapibus ac faciDapibus
						Dapibus ac faci ac faciDapibusac</li>
				</ul>
			</div>
		</div>
	</div>
</div>


<jsp:include page="../../template/Footer.jsp" />