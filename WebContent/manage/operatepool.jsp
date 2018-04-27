<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
	<p><span class="ziti"> Pool Management</span></p> 
	<button  class="btn btn-info  newosd" data-toggle="modal" data-target="#myModal">Add a pool</button>
	  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    	  <div class="modal-content">
    	  <div class="modal-header">      
       		 <h4  id="myModalLabel" class="fontblack">Add a new pool</h4>
          </div>
      	 <div class="modal-body">
							<div>
								<form method="post" class="form-horizontal inputdir" id="newpoolForm">
									<input type="input" class="form-control" id="pool_name" name="pool_name"
										placeholder="please enter a pool name">
									<input type="input" class="form-control inputdir" id="pg_num" name="pg_num"
										placeholder="please enter pg number">	
									<input type="input" class="form-control inputdir" id="pgp_num" name="pgp_num"
										placeholder="please enter pgp number">											
								</form>
							</div>

		 </div>
		 <div class="modal-footer">
							<button type="button" class="btn btn-primary" id="EnterButton">Enter</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
        </div>
     </div> 
    </div>
		<table class="table table-bordered">
		<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
		<th>ID</th>
		<th>Name</th>	
		<th>replicated</th>
		<th>crush_ruleset</th>
		<th>pg_num</th>
		<th>pgp_num</th>
		<th>Operation</th>	
		</tr>
		<s:iterator value="poolinfos" id="poolinfo" status="st">
				<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="#poolinfo[0]" /></td>
					<td><s:property value="#poolinfo[1]" /></td>
					<td><s:property value="#poolinfo[2]" /></td>
					<td><s:property value="#poolinfo[3]" /></td>
					<td><s:property value="#poolinfo[4]" /></td>
					<td><!-- Split button -->
            			<button id="removebtn" onclick="removepool(this)" type="button" class="btn btn-danger ">remove</button>
      				</td>
				</tr>
			</s:iterator>
	</table>
	</div>
</div>
<script>
function removepool(e){
	//var pool_name=$(e).parent().parent().children().eq(1).text();
	var pool_name=$(e).parent().parent().children().eq(1).text();
	$.post("manage/removepool",{"pool_name":pool_name},function(json){
			if(json.result==1){
			alert("remove success");
			window.location.reload();
			}else{
				alert("remove false");
			}
		},"json");
}
$("#EnterButton").click(function(){
	var d=$("#newpoolForm").serialize();
	console.log(d);
	$.post("manage/newpool",d,function(json){
		if(json.result==1){
		alert("add success");
		window.location.reload();
		}else{
			alert("add false");
		}
	},"json");
});



		  
</script>