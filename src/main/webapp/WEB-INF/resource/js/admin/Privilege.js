var filterToggle=false;
var regx = /^[A-Za-z0-9.]+$/;
$(document).ready(
		function() { 
			$('#Search').hide();
			$('#checkAll').on( 'click', function() {
				if (this.checked == true)
					$('#dataTable').find('input[name="selectBox"]')
					.prop('checked', true);
				else
					$('#dataTable').find('input[name="selectBox"]')
					.prop('checked', false);
			});
			
		 
	 
			$("#modal-btn-si").on("click", function(e){
			    e.preventDefault();

				confirmBoxResponse(true,$('#modelResponseId').val());
			      $("#mi-modal").modal('hide');
			      
			  });
			 	 
			 
			  $("#modal-btn-no").on("click", function(e){
				    e.preventDefault();

				  confirmBoxResponse(false,$('#modelResponseId').val());
			    $("#mi-modal").modal('hide');
			  });  
		});
function openPopupUrl(val)
{
	if(val=="C")
	{
		 return  "privilege.create";
	}
}
function openPopup(val)
{
	progressPopUp();
	 
	
	$.post(openPopupUrl(val), { data: "John"})
	  .done(function( data ) {
		  closePopup('progress');
		  $('#modelHeader').html("Create Privilege");
		  $('#modelBody').html(data);
		  var label="'Sending'"
		  $('#modalFooter').html(' <button id="saveBtn" name="saveBtn" onClick="savePrivilege();this.disabled=true;" class="btn btn-primary" >Save</button>');
		  $('#popUp').modal({backdrop: 'static', keyboard: false}) ;
	  }).fail(function() {
		  closePopup('progress');
	  });
	
	  
}
function toggleIcon()
{
	if(filterToggle)
		$('#filterSpan').html("<i class='fas fa-filter' title='Filter ON' style='color: red' data-toggle='tooltip' style='font-size: 24px'></i>");
	else
		$('#filterSpan').html("<i class='fas fa-filter' title='Filter OFF' style='color: green' data-toggle='tooltip' style='font-size: 24px'></i>");

}
 function requestCall(modelResponseId)
 {
	  progressPopUp();
	 var status="";
	 if ($('#toggle_'+modelResponseId).is(':checked')) {
	 		 status="Y";
	 	}
	else
		{
			status="N";
		}
	 
	 $.ajax({
		  	url: 'privilege.statusUpdate', 
		 	type: 'POST',
		 	dataType: 'json',
		    
		    data: "datas="+'{ "id": "'+$('#ids_'+modelResponseId).val()+'", "status":  "'+status+'" } ',
		    processData: false,
		    success: function( data, textStatus, jQxhr ){
		    	
		      if(data[0].result==false)
		    		{
		    	  		var err=new Array();
		    	  		err.push(data[0].message);
		    	  		errorPop(err) ;
		    	  		updateCheckBox(modelResponseId);
		    		}
		       
		         closePopup('progress');
		    },
		    error: function( jqXhr, textStatus, errorThrown ){
		    	updateCheckBox(modelResponseId);
		         closePopup('progress');
		    }
     });

	 
	 
	 
	 
	 
 }
 
 var err=new Array();
 function dateValidate(value,id)
 {
	 if(value.length>0)
		 {
		 	if(!isNaN(value))
		 	{
		 		value=moment().add(value, 'day').format('DD-MM-YYYY');
		 		$('#'+id).val(value);
		 	}
		 	if(value=='.')
		 	{
		 		value=moment().add(0, 'day').format('DD-MM-YYYY');
		 		$('#'+id).val(value);
		 	}
	 
		 	err=new Array();
		 	validateDate(value,'Date');
		 	showerror();
	     }
 }
 function clearBtn()
 {
	 $('#searchPrivilegeCode').val("");
	 $('#searchPrivilegeName').val("");
	 $('#fromDate').val("");
	 $('#toDate').val("");
	 filterToggle=false;
	 
	 toggleIcon();
	  
	 
 }

 
 function pageNavigate(page)
 {
	 $('#navigatePage').val(page);
	 search();
 }
 function search()
 {
	 progressPopUp();
	 err=new Array();
	 filterToggle=true;
		var status="Y";
		if ($('#searchStatus').is(':checked')) {
		
			status="Y";
		}
		else
		{
			status="N";
		}
	  if(validateDate($('#toDate').val(),'To Date ') && validateDate($('#fromDate').val(),'From date ') )
			 {
			 
			 	datas='datas={ "privilegeCode": "'+$('#searchPrivilegeCode').val()+'", "status":  "'+status+'", "privilegeName":  "'+$('#searchPrivilegeName').val()+'","currentPage":  "'+$('#navigatePage').val()+'", "fromDate":  "'+$('#fromDate').val()+'","toDate":"'+$('#toDate').val()+'" } ',
		   
			 	$.ajax({
		  	url: '/privilege.searchView', 
		 	type: 'POST',
		 	dataType: 'json',
		    
		    data: datas,
		    processData: false,
		    success: function( data, textStatus, jQxhr ){
		    	 
		    	startup( JSON.stringify(data));
		       
		         closePopup('progress');
		    },
		    error: function( jqXhr, textStatus, errorThrown ){
		    	updateCheckBox(modelResponseId);
		         closePopup('progress');
		    }
     });
			 }
		 else
			 {
			  	closePopup('progress');
			 	showerror();
			 
			 }
		 toggleIcon();
 }
 function showerror()
 {
	  if( err.length>0 )
	  {	errorPop(err) ;}
 }
 
 function validateDate(date,type)
 {
	    var pattern = /^([0-9]{2})-([0-9]{2})-([0-9]{4})$/;
	    	if( date == null || date == "" )
	    	{
	    		return true;
	    	}
	    	else if (pattern.test(date)) 
	    	{   
	    		  if(!moment(date, 'DD-MM-YYYY',true).isValid())
	    			 {
	    			 	err.push(type+" is Invalid .");
	    			 	return false;
	    			 }
	    		 else
	    			 return true;
	    	}
	    	else if (!pattern.test(date)) 
	    	{
	    		err.push(type+" is Invalid .");
	    		return false;
	    	}
	    	else 
	    	{
	    		return true;
	    	}

 }
 function updateCheckBox(modelResponseId)
 {
	 if ($('#toggle_'+modelResponseId).is(':checked')) {
	 		
			$('#toggle_'+modelResponseId).prop('checked', false);
	 	}
	else
		{
			$('#toggle_'+modelResponseId).prop('checked', true);
		}
 }

 function confirmBoxResponse(result,modelResponseId)
 {
	  
	 if(result)
		 {
		 	requestCall(modelResponseId);
		   
		 }
	 else
		 {
		 
		 	if ($('#toggle_'+modelResponseId).is(':checked')) {
		 		
		 			$('#toggle_'+modelResponseId).prop('checked', false);
			 	}
			else
				{
					$('#toggle_'+modelResponseId).prop('checked', true);
				}
		 	
		 }
	 
	 
	 
 }
function editPrvilege(id)
{
	 
progressPopUp();
	 
	
	$.post("privilege.edit", { privilegeId: $('#ids_'+id).val()})
	  .done(function( data ) {
		  closePopup('progress');
		  $('#modelHeader').html("Edit Privilege");
		  $('#modelBody').html(data);
		  var label="'Sending'"
		  $('#modalFooter').html(' <button id="saveBtn" name="saveBtn" onClick="updatePrivilege();this.disabled=true;" class="btn btn-primary" >Save</button>');
		  $('#popUp').modal({backdrop: 'static', keyboard: false}) ;
	  }).fail(function() {
		  closePopup('progress');
	  });
}

function selectToggle(index)
{
	$('#modelResponseId').val(index);
	
	if (!$('#toggle_'+index).is(':checked')) {
		$('#confirmTitle').html("Do you want to Deactivate ?")
	 	}
	else
		{
		 $('#confirmTitle').html("Do you want to Activate ?")
		}
	
 
	
	$('#mi-modal').modal({
	      backdrop: 'static',
	      keyboard: false
	  })

}
function showSearch()
{
	$('#Search').toggle()
	 	
}
function pagination(data)
{
	if(parseInt(data.totalPage)>1)
		{
		 if(data.currentPage==0){
			 data.currentPage=1;
		 }
		var pageHtml='<ul class="pagination pagination-sm">' ;
		for(i=1;i<=parseInt(data.totalPage);i++)
				  {
					
					if(parseInt(data.currentPage)==i)
						{
							pageHtml+='<li class="disabled"><a href="#">'+i+'</a></li>';
						}
					else
						{
							pageHtml+='<li><a href="javascript:pageNavigate('+i+')" >'+i+'</a></li>';
						}
				
				  }
		 pageHtml+="</ul>";
		   $('#pagination').html(pageHtml);
		}
	 
}
function startup(data)
{
	var id=1;
	var jsonObj=JSON.parse(data);
	var page = {currentPage:jsonObj.currentPage, totalPage:jsonObj.totalPage, perPage:jsonObj.perPage};
	pagination(page);
	$('#dataTable tbody').html('');
 if(jsonObj.obj.length>0)
 {
	for(i=0;i<jsonObj.obj.length;i++)
	{
		var html ="<tr onClick='selectCheckBox("+id+")'> <td class='col-xs-1 table-row-height' align='center'> <div class='row'>" 
		+ "<div class='col-sm-6' align='center'> <input name='selectBox' id='selectBox_"+id+"' type='checkbox' />"+
		"<input name='ids' id='ids_"+id+"' type='hidden' value='"+jsonObj.obj[i].privilegeId+"' />" +
		"</div> <div class='col-sm-6'>"+id+"</div> </div></td>"+
		" <td class='col-xs-2 table-row-height'>"+jsonObj.obj[i].privilegeName+"</td>"+
		"<td class='col-xs-3 table-row-height'>"+jsonObj.obj[i].privilegeCode+"</td>"+
		
		"<td class='col-xs-3 table-row-height'>"+jsonObj.obj[i].validatityFrom+" to "+jsonObj.obj[i].validatityTo+"</td>"+
		
		"<td class='col-xs-1 table-row-height'>" ;
		 
		
		html+="&nbsp; <label class='switch' title='Activate/Deactivate'  data-toggle='tooltip' >" +
		"<input type='checkbox' id='toggle_"+id+"' name='toggle' value='"+jsonObj.obj[i].status+"' onClick='selectToggle("+id+")' ";
		
		if(jsonObj.obj[i].status=='Y')
		 {
			html+=" checked ";
		 }
		html+=" > <span class='slider round'></span></label>  " ;
		
		 html+="</td><td class='col-xs-2 table-row-height'>";
		
		 html+=	"&nbsp; <a href='javascript:editPrvilege("+id+");'><span  title='Edit Privilege Details' data-toggle='tooltip' class='fa-stack hover-change' style='vertical-align: top;'>  " +
			"<i class='far fa-circle fa-stack-2x'></i> <i class='fas fa-pencil-alt fa-stack-1x'></i> " +
			"</span></a>" ;
		
		 html+=" &nbsp; <a href='javascript:viewPrivilege("+id+");'>" +
			"<span class='fa-stack hover-change'   title='View Privilege Details' data-toggle='tooltip'  style='vertical-align: middle;'>  " +
			"<i class='far fa-circle fa-stack-2x'></i> <i class='fas fa-eye fa-stack-1x'></i> " +
			"</span> </a>"  ;
			  
		 html+=	"&nbsp; <a href='javascript:viewPrivilege("+id+");'>" +
		 		"<span class='fa-stack hover-change' style='vertical-align: top;'  title='View User Group Details' data-toggle='tooltip' >  " +
			"<i class='far fa-circle fa-stack-2x'></i> <i class='fas fa-users fa-stack-1x'></i> " +
			"</span></a>" ;
		
		 
		 
		 
		 html+=	"&nbsp; <a href='javascript:viewPrivilege("+id+");'><span  title='View Privilege Group Details' data-toggle='tooltip' class='fa-stack hover-change' style='vertical-align: top;'>  " +
			"<i class='far fa-circle fa-stack-2x'></i> <i class='fas fa-user-secret fa-stack-1x'></i> " +
			"</span></a>" ;
		 
	 
		html+=	"</td></tr>";
		 
		$("#dataTable tbody").append(html);
		id++;
	}
 }
 else
	 {
	  html="<tr><td colspan='6' class='col-xs-12 table-row-height'> <p align='center'>No Data Found .</p></td></tr>";
	 
	 $("#dataTable tbody").append(html);
	 }
	
}
function viewPrivilege(id)
{

	
	$.post("privilege.showPrivilegeDetails", { privilegeId: $('#ids_'+id).val()})
	  .done(function( data ) {
		  closePopup('progress');
		  $('#modelHeader').html("View Privilege Details");
		  $('#modelBody').html(data );
		  $('#modalFooter').html(' ');
			 
		  var label="'Sending'"
		   $('#popUp').modal({backdrop: 'static', keyboard: false}) ;
	  }).fail(function() {
		  closePopup('progress');
	  });


	 
	
	
	
	
	
	}