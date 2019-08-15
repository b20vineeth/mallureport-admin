

var regx = /^[A-Za-z0-9.]+$/;
$(document).ready(
		function() { 
			  if( $('#privilegeGroupCodeId').val().trim().length>2)
			 {
				 $('#privilegeGroupCode').attr('disabled',true);
			 }
			
			$("#privilegeGroupCode").change(function() {
				 var res = this.value.split(".");
				 var resp=true;
				 $.each(res, function (i) {

					 if (regx.test(res[i]) && resp) 
				     {
						 resp=true;
				     }
				    else 
				     {
				    	resp=false;
				     } 
					    
			   });
				if(resp)
				{


					 
					 $.ajax({
						  	url: 'privilegeGroupCode.validateprivilegeGroupCode', 
						 	type: 'POST',
						 	dataType: 'json',
						    
						    data: "datas="+'{ "privilegeGroupCode": "'+$('#privilegeGroupCode').val()+'"} ',
						    processData: false,
						    success: function( data, textStatus, jQxhr ){
						    	console.log(data);
						      if(data=='true')
						      {
						    	 $('#resprivilegeGroupCode').html('<i class="fas fa-check-circle"   style="color:Green;"></i>');  
						      }
						      else
							      {
 
							      
						    	  $('#resprivilegeGroupCode').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');
						    		$('#privilegeGroupCode').focus();
							      }
						       
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
					$('#resprivilegeGroupCode').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');	
					$('#privilegeGroupCode').focus();
			    	
				}
			    
			});






			$("#privilegeGroupCodeName").change(function() {
				 var res = this.value.split(".");
				 var resp=true;
				 $.each(res, function (i) {

					 if (regx.test(res[i]) && resp) 
				     {
						 resp=true;
				     }
				    else 
				     {
				    	resp=false;
				     } 
					    
			   });
				if(resp)
				{

					 $("#resprivilegeGroupCodeName").html('<i class="fas fa-check-circle"   style="color:Green;"></i>');  
					    
					 
					 
				}
				else
				{
					$('#resprivilegeGroupCodeName').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');	
					$('#privilegeGroupCodeName').focus();
			    	
				}
			    
			});



			
			
			
		 
			
	 
			 
		});

function savePrivilegeGroupCode()
{
	val='privilegeGroupCode.save';
	saveOrupdatePrivilegeGroupCode(val);
}
function updatePrivilegeGroupCode()
{
	val='privilegeGroupCode.update';
	saveOrupdatePrivilegeGroupCode(val);
}
function saveOrupdatePrivilegeGroupCode(val)
{
	var err=new Array(); 
	
	 $.ajax({
	  	url: val, 
	 	type: 'POST',
	 	dataType: 'json',
	    
	 	data: "datas="+'{ "privilegeGroupCode": "'+$('#privilegeGroupCode').val()+'", "privilegeGroupCodeName":  "'+$('#privilegeGroupCodeName').val()+'", "privilegeGroupCodeId":  "'+$('#privilegeGroupCodeId').val()+'" } ',
	    processData: false,
	    success: function( data, textStatus, jQxhr ){
	    	
		      if(data.response==false)
		    		{
		    		var datas=data.error;
		    	  	  for(i=0;i<datas.length;i++)
			    	  	 {
		    	  		  err.push(data.error[i].message);
			    	  	 }
		    	  		$('#saveBtn').removeAttr('disabled');
		    			 	errorPop(err) ;
		    	  		 
		    		}
		      else
			      {
		    	   
		    	  successPop("Privilege Group Code has been successfully Created .")
		    	 	gotoPage('SET02'); 
			      }
		         closePopup('progress');
		         closePopup('popUp');
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	updateCheckBox(modelResponseId);
	         closePopup('progress');
	    }
 });
}