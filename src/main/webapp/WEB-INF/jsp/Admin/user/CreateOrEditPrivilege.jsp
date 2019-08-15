<form id='formDiv' class="form-horizontal">
<fieldset>





 
    <div class="row">
        <div class="col-sm-12">
        
        
        
        
        
        
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Privilege Code">Privilege Code</label>  
  <div class="col-md-7">
  <input id="privilegeCode" name="privilegeCode" type="text" value='${form.privilegeCode}' placeholder="Privilege Code" class="form-control input-md" required="privilege Code is required">
    
  </div> <div class="col-md-1"> <div style="padding-top: 8px;"><span id="resPrivilegeCode"></span></div> </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Privilege Name">Privilege Name</label>  
  <div class="col-md-7">
  <input id="privilegeName" name="Privilege Name" type="text" placeholder="Privilege Name"  value='${form.privilegeName}' class="form-control input-md"> 
    
  </div><div class="col-md-1"><div style="padding-top: 8px;"> <span id="resPrivilegeName"></span></div></div>
</div>

 

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Possible Values">Possible Values</label>  
  <div class="col-md-7">
  <input id="possibleValues" name="Possible Values" type="text" placeholder="Possible Values"   value='${form.posibleval}'  class="form-control input-md"> <span id="resPossibleValue"></span>
    
  </div><div class="col-md-1"><div style="padding-top: 8px;"> <span id="resPossibleValues"></span></div></div>
</div>
        
        
      <input type="hidden" name="privilegeId"  id='privilegeId'  value='${form.privilegeId}'  />
        
        
        
        
        </div>
     </div>
     
     
     
     
     
     
     
     
     
     
     
     
     
     



</fieldset>
</form>






<script>


var regx = /^[A-Za-z0-9.]+$/;
$(document).ready(
		function() { 
			  if( $('#privilegeId').val().trim().length>2)
			 {
				 $('#privilegeCode').attr('disabled',true);
			 }
			
			$("#privilegeCode").change(function() {
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
						  	url: 'privilege.validatePrivilegeCode', 
						 	type: 'POST',
						 	dataType: 'json',
						    
						    data: "datas="+'{ "privilegeCode": "'+$('#privilegeCode').val()+'"} ',
						    processData: false,
						    success: function( data, textStatus, jQxhr ){
						    	console.log(data);
						      if(data=='true')
						      {
						    	 $('#resPrivilegeCode').html('<i class="fas fa-check-circle"   style="color:Green;"></i>');  
						      }
						      else
							      {
 
							      
						    	  $('#resPrivilegeCode').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');
						    		$('#privilegeCode').focus();
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
					$('#resPrivilegeCode').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');	
					$('#privilegeCode').focus();
			    	
				}
			    
			});






			$("#privilegeName").change(function() {
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

					 $("#resPrivilegeName").html('<i class="fas fa-check-circle"   style="color:Green;"></i>');  
					    
					 
					 
				}
				else
				{
					$('#resPrivilegeName').html('<i class="fa fa-times-circle-o" aria-hidden="true" style="color:Red;"></i>');	
					$('#privilegeName').focus();
			    	
				}
			    
			});



			
			
			
		 
			
	 
			 
		});

function savePrivilege()
{
	val='privilege.save';
	saveOrUpdatePrivilege(val);
}
function updatePrivilege()
{
	val='privilege.update';
	saveOrUpdatePrivilege(val);
}
function saveOrUpdatePrivilege(val)
{
	var err=new Array(); 
	
	 $.ajax({
	  	url: val, 
	 	type: 'POST',
	 	dataType: 'json',
	    
	 	data: "datas="+'{ "privilegeCode": "'+$('#privilegeCode').val()+'", "privilegeName":  "'+$('#privilegeName').val()+'", "posibleval":  "'+$('#possibleValues').val()+'", "privilegeId":  "'+$('#privilegeId').val()+'" } ',
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
		    	   
		    	  successPop("Privilege has been successfully Created .")
		    	 	gotoPage('SET01'); 
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

</script>