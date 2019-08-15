
<%@page import="java.util.*" %>
<%
Random rand = new Random();
int n = rand.nextInt(90000) + 10000;
 
%>
<script src='/resource/js/admin/PrivilegeGroupCode.js?version=<%=n%>${version}'></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>
 
<br> 
<div class="row">
	<div class="col-sm-9" align='left'>   <a href='javascript:showSearch();'> <span id="filterSpan"><i
			class="fas fa-filter" title="Filter OFF"
			style='color: #8BC34A' data-toggle="tooltip" style='font-size: 24px'></i></span></a></div>
	<div class="col-sm-3" align='right'>

		<a href='javascript:openPopup("C");'><i class="fa fa-plus"
			title="Create New Privilege Group Code" data-toggle="tooltip"
			style='color: #8BC34A' style='font-size: 22px'></i></a> &nbsp; <a
			href='javascript:successPop("");'> <i class='fas fa-user'
			title="User" data-toggle="tooltip" style='color: #8BC34A'
			style='font-size: 22px'></i></a> &nbsp; <a href='javascript:openPopup("UG");'>
			<i class='fas fa-users' style='color: #8BC34A' title="User Group"
			data-toggle="tooltip" style='font-size: 24px'></i>
		</a>&nbsp; <a href='javascript:openPopup("PG");'> <i
			class="fas fa-user-secret" title="Privilege Group Code"
			style='color: #8BC34A' data-toggle="tooltip" style='font-size: 24px'></i></a>
			
			
			
		 
			
	</div>
</div>
<br> 
<div id="Search" class="form-group"  style="border: 1px solid green; padding: 10px;" >
 <input type="hidden" name="navigatePage"  id="navigatePage" value="1"/>


 <div class="row"> 
       <div class="col-sm-1" align="right"> Search :&nbsp;</div>
         <div class="col-sm-2">
           
 		 <input id="searchPrivilegeGroupCodeName" name="searchPrivilegeGroupCodeName" type="text"   placeholder="Privilege Group Code" class="form-control input-md" required="privilege Code is required">
     
        </div>
       
        <div class="col-sm-2">

 
 		 <input id="searchPrivilegeGroupCode" name="searchPrivilegeGroupCode" type="text"   placeholder="Privilege Group Code Name" class="form-control input-md" required="privilege Code is required">
     
		</div>
        <div class="col-sm-2"> 
         <input id="fromDate" name="fromDate" type="text" onchange="dateValidate(this.value,this.id);"   placeholder="dd-mm-yyyy" class="form-control input-md" required="privilege Code is required">
     
     	</div>
        <div class="col-sm-2"> 
         <input id="toDate" name="toDate" type="text"   placeholder="dd-mm-yyyy"  onchange="dateValidate(this.value,this.id);" class="form-control input-md" required="privilege Code is required">
     
     	</div>
        <div class="col-sm-1" style="padding-top:7px;" align="right"> 
        
        <label class='switch' title='Activative/Deactivative'  data-toggle='tooltip'>
		 <input type='checkbox' id='searchStatus' name='status'   checked  >
		 <span class='slider round'></span></label> 
		 
		 </div>
        <div class="col-sm-2" align="left"> <button type="button" class="btn btn-primary" onClick="search()">search</button> &nbsp;
        <button type="button" class="btn btn-primary" onClick="clearBtn()">Clear</button></div>
        
        
    </div>




</div>
<table id="dataTable" class="table  table-fixed  table-bordered">
	<thead id='tableHeader'>
		<tr>
			<th class="col-xs-1 table-row-height">



				<div class="row">
					<div class="col-sm-6" align='center'>
						<input type="checkbox" id='checkAll' />
					</div>
					<div class="col-sm-6" >&nbsp; #</div>
				</div>


			</th>
			<th class="col-xs-2 table-row-height">Privilege Group Code Name</th>
			<th class="col-xs-3 table-row-height">Privilege Group Code</th>
			
			<th class="col-xs-3 table-row-height">Validity</th>
			 
			<th  colspan="2" class="col-xs-3 table-row-height"><center>View</center></th>
		</tr>
	</thead>
	<tbody id='tableBody'>
 
 
	</tbody>
</table>

 <div id="pagination">
 
 
 </div>





<style>
.hover-change
{
color:grey;
}
.hover-change:hover {
    color: red;
}

</style>
<Script>
 
$(document).ready(
		function() { 
			
			var data='${data}';
			startup(data);
			
			$('[data-toggle="tooltip"]').tooltip();   
		});
 
		</Script>

