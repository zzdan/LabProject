<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
	<p><span class="ziti"> OSD Management</span></p> 
	<button  class="btn btn-info  newosd" data-toggle="modal" data-target="#myModal">Add a osd</button>
	  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    	  <div class="modal-content">
    	  <div class="modal-header">      
       		 <h4  id="myModalLabel" class="fontblack">Add a new osd</h4>
          </div>
      	  <div class="modal-body">
      	      <div>
    				<select class="form-control">
  			            <option>monitor</option>
  						<option>node1</option>
  						<option>node2</option> 						
					</select>
 					<form class="form-horizontal inputdir" id="newosdForm">
                       <input type="input" class="form-control" id="dir" name="mewdir" placeholder="/var/local/osd0">
                    </form>
   			 </div>
             
         </div>
         <div class="modal-footer">
              <button type="button" class="btn btn-primary" id="EnterButton">Enter</button>
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>       
         </div>
        </div>
     </div> 
    </div>
		<table class="table table-bordered">
		<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
		<th>Name</th>	
		<th>Server</th>
		<th>Attach Status</th>
		<th>State</th>
		<th>Capacity Total(MB)</th>
		<th>Capacity Used(MB)</th>
		<th>Capacity Available(MB)</th>
		<th>Percent Used Capacity</th>
		<th>Operation</th>	
		</tr>
		<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
		<td>osd.0</td>
		<td>monitor</td>
		<td>IN</td>
		<td>UP</td>
		<td>12806</td>
		<td>1456</td>
		<td>10350</td>
		<td>11</td>
		<td><!-- Split button -->
            <button type="button" class="btn btn-danger">stop</button>
            <button type="button" class="btn btn-success">restart</button>
            <button type="button" class="btn btn-danger">remove</button>
        </td>
		</tr>
	</table>
	</div>
</div>
<script>
$("#EnterButton").click(function(){			
	$.post("manage/newosd.action",function()
			{
		         alert("add success");

			});
			
		  });
		  
</script>