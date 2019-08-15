<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


 



	<table class="table  table-bordered">

		<tbody id="tableBody">
			<tr>

				<td class="col-xs-4 table-row-height">Privilege Code</td>
				<td class="col-xs-6 table-row-height">${form.privilegeCode}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Privilege Name</td>
				<td class="col-xs-6 table-row-height">${form.privilegeName}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Possible value</td>
				<td class="col-xs-6 table-row-height">${form.posibleval}</td>

			</tr>

			<tr>

				<td class="col-xs-4 table-row-height">Created On</td>
				<td class="col-xs-6 table-row-height">${form.createDate}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Created By </td>
				<td class="col-xs-6 table-row-height"> ${form.createdBy}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Validity </td>
				<td class="col-xs-6 table-row-height">${form.validity}</td>

			</tr>
			
			<tr>

				<td class="col-xs-4 table-row-height">Last Updated By </td>
				<td class="col-xs-6 table-row-height"> ${form.updatedBy}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Last Updated  </td>
				<td class="col-xs-6 table-row-height"> ${form.updDate}</td>

			</tr>
			<tr>

				<td class="col-xs-4 table-row-height">Status  </td>
				<td class="col-xs-6 table-row-height">${form.status}</td>

			</tr>
			 
		</tbody>
	</table>
</body>
</html>