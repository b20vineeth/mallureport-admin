<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<jsp:include page="../HomeHeader.jsp" />
<title>Create Language</title>
<div class="row">
<div class="col-sm-1 clearfix"></div>
<div class="col-sm-9 clearfix">
<h5>Category Type </h5><hr/>


<table class="table table-hover">
    <thead>
      <tr>
       <th>#</th>
        <th>Name</th>
        <th>Code </th>
        <th> &nbsp;</th>
      </tr>
    </thead>
    <tbody>
    <%int i=1; %>
    <c:forEach items="${response.objectList}" var="objectList">
      <tr>
         <td><%=i++ %></td>
        <td>${objectList.catTypeName}</td>
        <td>${objectList.catTypeCode}</td>
        <td><i class="fa fa-edit" onClick="editCategory(${objectList.catTypeId})" style="font-size:20px;color:Green"></i> </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>




 </div>
<div class="col-sm-2 clearfix"></div>
</div>
<script>

function editCategory(id)
{
	window.location.href="admin.categoryType.edit?catTypeId="+id
}

 </script>

<jsp:include page="../HomeFooter.jsp" />
	

	