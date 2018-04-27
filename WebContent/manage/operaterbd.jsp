<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="tabbable tabs-left">
	<ul class="nav nav-tabs">
		<s:iterator value="rbdinfo" id="rbdinfo">
			<li class=""><a href="javascript:void(0)" data-toggle="tabdata"
				data-url='manage/get_rbd_list?poolName=<s:property
						value="#rbdinfo" />'><s:property
						value="#rbdinfo" /></a></li>
		</s:iterator>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active">
			<p>
				<span class="ziti">RBD List</span>
				<button class="btn btn-info  newrbd" data-toggle="modal"
					data-target="#myModal">Add a RBD</button>
			</p>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h4 id="myModalLabel" class="fontblack">Add a new rbd</h4>
						</div>
						<div class="modal-body">
							<div>
								<form method="post" class="form-horizontal inputdir" id="newrbdForm">
									<input type="input" class="form-control" id="pool_name" name="pool_name"
										placeholder="please enter a pool name">
									<input type="input" class="form-control inputdir" id="rbd_name" name="rbd_name"
										placeholder="please enter new rbd name">	
									<input type="input" class="form-control inputdir" id="rbd_size" name="rbd_size"
										placeholder="please enter new rbd size">											
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

			<table class="table tablelength table-bordered" id="table_list_div">
			<tr style="font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<th>Number</th>
				<th>Name</th>
				<th>Size</th>
				<th>Order</th>
				<th>Format</th>
			</tr>
		</table>
		</div>
	</div>
</div>

			

<script>
	$(function()
	{
		$("a[data-url]").each(function()
		{
			var obj = $(this);
			obj.click(function()
			{
				$("#table_list_div  tr:not(:first)").html("");
				//将子页面load进来				
				var whole_html="";
				$.post(obj.attr("data-url"), {}, function(json)
				{
					
					var rbd_num=json.list.length;
					
					for (var i = 0; i < rbd_num; i++){
						var temp_html = "<tr><td>NUMBER</td><td>NAME</td><td>SIZE</td><td>ORDER</td><td>FORMAT</td></tr>";
						temp_html = temp_html.replace("NUMBER", i+1);
						temp_html = temp_html.replace("NAME", json.list[i][0]);
						temp_html = temp_html.replace("SIZE",  json.list[i][1]);
						temp_html = temp_html.replace("ORDER", json.list[i][2]);
						temp_html = temp_html.replace("FORMAT",json.list[i][3]);						
						whole_html += temp_html;
					}
					$("#table_list_div").append(whole_html);
					

				},
				//返回类型
				"json");
				
			});
		});
		$("#newrbd").click(function(){
			alert("test");
		});

		$("#EnterButton").click(function(){
			var d=$("#newrbdForm").serialize();
			$.post("manage/newrbd",d,function(json){
				if(json.result==1){
				alert("add success");
				window.location.reload();
				}else{
					alert("add false");
				}
			},"json");
		});
		
		
		
		
		
		
		
	});

	
</script>