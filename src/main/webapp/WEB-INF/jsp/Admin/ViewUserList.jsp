<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">

<jsp:include page='AccountMenu.jsp'></jsp:include>
<script src="http://localhost/blog/js/jquery-confirm.js"></script>
<title>Account Summary</title>
<div class="container text-center">
	<div class="row">
		<div class="col-sm-3 well">

			<jsp:include page='AccountSideMenu.jsp'></jsp:include>

		</div>
		<div class="col-sm-9">

			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default text-left">
						<div class="panel-body">

							<h4>Account Summary</h4>
							<br>
							<%
								int i = 1;
							%>

 <p align="right">  <a href="/createUserByAdmin">
          <span class="glyphicon glyphicon-plus"></span>
        </a> </p>

							<table class="table table-fixed">
								<thead>
									<tr>

										<th class="col-xs-1">#</th>
										<th class="col-xs-2">Name</th>
										<th class="col-xs-2">Username</th>
										<th class="col-xs-5" > Details</th>
										<th class="col-xs-2">&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${fn:length(result.list) gt 0}">

										<c:forEach items="${result.list}" var="element">



											<tr>
												<td class="col-xs-1"><%=i++%></td>
												<td class="col-xs-2">${element.firstName}&nbsp;${element.lastName}</td>
												<td class="col-xs-2">${element.username}</td>
												<td class="col-xs-5">
<ul>
												<li>Email : ${element.email} </li>
												
												<li>Created Date : <fmt:formatDate value="${element.createdDate}" pattern="dd-MM-YYYY HH:mm:ss" /></li>
												<li> Updated Date :
													<fmt:formatDate value="${element.updatedDate}"
														pattern="dd-MM-YYYY HH:mm:ss" /> </li>
												<li> 
												Validity To: <fmt:formatDate
														value="${element.validityTo}" pattern="dd-MM-YYYY" /></li> 
</ul>
												</td>
												<td class="col-xs-2"><input type="hidden" value='${element.status}'
													id='txt_status_${element.username}'
													name='txt_status_${element.username}'> <c:choose>
														<c:when test="${element.status =='Y'}">



															<label class="switch"> <input type="checkbox"
																onChange="clickEvent(this)" value="Y"
																name='status_${element.username}' checked> <span
																class="slider round"></span>
															</label>
															<br />
														</c:when>
														<c:otherwise>
															<label class="switch"> <input type="checkbox"
																onChange="clickEvent(this)" value="N"
																name='status_${element.userId}'> <span
																class="slider round"></span>
															</label>
															<br />
														</c:otherwise>
													</c:choose></td>
											</tr>

										</c:forEach>

									</c:if>

								</tbody>
							</table>


 



 
							<c:if test="${result.currentPage != 1}">


								<ul class="pagination pagination-sm">
									<c:forEach var="i" begin="1" end="${result.totalPageCount}">
										<li
											<c:if test="${result.currentPage == i}">
									class="active"
									</c:if>><a
											href="?p=${i}"> <c:out value="${i}" /></a></li>
									</c:forEach>
								</ul>
							</c:if>

						</div>
					</div>
				</div>
			</div>


		</div>

	</div>
</div>

<script>
	function callAlert(obj) {

		$.confirm({
			title : 'Confirm!',
			content : 'Do You Want to Continue !',
			buttons : {
				confirm : function() {
					toggleValue("txt_" + obj.name);
				},
				cancel : function() {
					window.location.reload();
				},

			}
		});
	}

	function toggleValue(val) {

		if ($('#' + val).val() == 'Y') {
			$('#' + val).val('N');
		} else {
			$('#' + val).val('Y');
		}
		console.log($('#' + val).val());
	}
	function clickEvent(obj) {

		callAlert(obj);

	}
</script>
<jsp:include page='AccountFooter.jsp'></jsp:include>
