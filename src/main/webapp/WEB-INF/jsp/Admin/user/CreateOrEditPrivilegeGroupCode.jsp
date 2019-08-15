

<%@page import="java.util.*" %>
<%
Random rand = new Random();
int n = rand.nextInt(90000) + 10000;
 
%>
<script src='/resource/js/admin/PrivilegeGroupCodePopUp.js?version=<%=n%>${version}'></script>
 
<form id='formDiv' class="form-horizontal">
<fieldset>





 
    <div class="row">
        <div class="col-sm-12">
        
        
        
        
        
        
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Privilege Group Code">Privilege Group Code</label>  
  <div class="col-md-7">
  <input id="privilegeGroupCode" name="privilegeGroupCode" type="text" value='${form.privilegeGroupCode}' placeholder="Privilege Group Code" class="form-control input-md" required="Privilege Group Code is required">
    
  </div> <div class="col-md-1"> <div style="padding-top: 8px;"><span id="resprivilegeGroupCode"></span></div> </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Privilege Group Code Name">Privilege Group Code Name</label>  
  <div class="col-md-7">
  <input id="privilegeGroupCodeName" name="Privilege Group Code Name" type="text" placeholder="Privilege Group Code Name"  value='${form.privilegeGroupCodeName}' class="form-control input-md"> 
    
  </div><div class="col-md-1"><div style="padding-top: 8px;"> <span id="resprivilegeGroupCodeName"></span></div></div>
</div>

 

 
        
        
      <input type="hidden" name="privilegeGroupCodeId"  id='privilegeGroupCodeId'  value='${form.privilegeGroupCodeId}'  />
        
        
        
        
        </div>
     </div>
     
     
     
     
     
     
     
     
     
     
     
     
     
     



</fieldset>
</form>



 