<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a
				href="javascript:void(0)" aria-controls="home" role="tab"
				data-toggle="tab" id="tab1" data-url="manage/managevms.action">节点列表</a></li>
			<li role="presentation"><a href="javascript:void(0)"
				aria-controls="add" role="tab" data-toggle="tab" id="tab2">添加节点</a></li>
			<li role="presentation"><a href="javascript:void(0)"
				aria-controls="imagelist" role="tab" data-toggle="tab" id="tab3"
				data-url="manage/manageimage.action">镜像管理</a></li>
			<li role="presentation"><a href="javascript:void(0)"
				aria-controls="add" role="tab" data-toggle="tab" id="tab4">添加vm</a></li>
		</ul>
	</div>
	<div class="col-md-12">
		<div id="tab1div">
				
				
		<table class="table table-bordered" id="table_list_div1">
		   <tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
			<th style="width:5px">Num</th>
			<th>Id</th>
			<th>Name</th>	
			<th>imageId</th>
			<th>flavorid</th>
			<th>status</th>
			<th>created</th>
			<th>updated</th>	
			<th>address</th>
			<th>macaddr</th>
			<th>PowerState</th>
			<th>VmState</th>
			</tr>
			
			<s:iterator value="vmsinfos" id="vmsinfo" status="st">
				<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="#vmsinfo[0]" /></td>
					<td><s:property value="#vmsinfo[1]" /></td>
					<td><s:property value="#vmsinfo[2]" /></td>
					<td><s:property value="#vmsinfo[3]" /></td>
					<td><s:property value="#vmsinfo[4]" /></td>
					<td><s:property value="#vmsinfo[6]" /></td>
					<td><s:property value="#vmsinfo[7]" /></td>
					<td><s:property value="#vmsinfo[9]" /></td>
					<td><s:property value="#vmsinfo[10]" /></td>
					<td><s:property value="#vmsinfo[11]" /></td>
					<td><s:property value="#vmsinfo[12]" /></td>
				</tr>
			</s:iterator>
	</table>
				
							
		
		</div>
		<div role="tabpanel" class="tab-pane" id="tab2div"
			style="display: none;">
			<div class="top5">

				<form class="form-horizontal" id="create-vms-form">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">云主机名称:</label>
						<div class="col-sm-10 maxwidth">
							<input type="text" class="form-control" id="hostname"
								name="hostname" placeholder="Enter your Hosting Name">
						</div>
					</div>

					<div class="form-group">
						<label for="flavor" class="col-sm-2 control-label">云主机类型:</label>
						<div class="col-sm-10 maxwidth">
							<select class="form-control" name="flavor" id="flavor">
								<option title="1个虚拟内核/2G内存/20GB硬盘">低配</option>
								<option title="2个虚拟内核/4G内存/40GB硬盘">中配</option>
								<option title="4个虚拟内核/8G内存/80GB硬盘">高配</option>
							</select>
						</div>
					</div>

				   <div class="form-group">
						<label for="image" class="col-sm-2 control-label">操作系统:</label>
						<div class="col-sm-10 maxwidth">
							<select class="form-control" name="image" id="vmImage">
								<option value="0">Ubuntu14 64位</option>
								<option value="1">CentOS7 64位</option>
								<option value="2">Win7 64位</option>
							</select>
						</div>
					</div>
					
					<div class="form-group" id="ubuntu14">
						<label for="soft" class="col-sm-2 control-label">预装软件:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="software" value="0"> mysql
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="software" value="1"> java
							</label>
						</div>
					</div>


					<div class="form-group" id="centos7" style="display: none;">
						<label for="soft" class="col-sm-2 control-label">预装软件:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="software" value="0"> mysql
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="software" value="1"> tomcat
							</label>
						</div>
					</div>

					<div class="form-group" id="win7" style="display: none;">
						<label for="soft" class="col-sm-2 control-label">预装软件:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="software" value="0"> VNCView
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="software" value="1"> 百度云管家
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-success minwidth lp"
								id="button2">创建</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div id="tab3div" style="display: none;">
		
		<table class="table table-bordered" id="table_list_div3">
		   <tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
			<th style="weight:5px">Num</th>
			<th>ID</th>
			<th>Name</th>	
			<th>status</th>
			<th>isSnapshot</th>
			<th>size</th>
			<th>created</th>
			</tr>
		
		</table>
		
		</div>
		
		<div role="tabpanel" class="tab-pane" id="tab4div"
			style="display: none;">
			<div class="top5">

				<form class="form-horizontal" id="create-vms-form2">

					<div class="form-group">
						<label for="name_vm" class="col-sm-2 control-label">云主机名称:</label>
						<div class="col-sm-10 maxwidth">
							<input type="text" class="form-control" id="hostname_vm"
								name="hostname_vm" placeholder="Enter your Hosting Name">
						</div>
					</div>

					<div class="form-group">
						<label for="flavor_vm" class="col-sm-2 control-label">云主机类型:</label>
						<div class="col-sm-10 maxwidth">
							<select class="form-control" name="flavor_vm" id="flavor_vm">
								<option title="1个虚拟内核/2G内存/20GB硬盘">低配</option>
								<option title="2个虚拟内核/4G内存/40GB硬盘">中配</option>
								<option title="4个虚拟内核/8G内存/80GB硬盘">高配</option>
							</select>
						</div>
					</div>

				   <div class="form-group">
						<label for="image_vm" class="col-sm-2 control-label">操作系统:</label>
						<div class="col-sm-10 maxwidth">
							<select class="form-control" name="image_vm" id="Image_vm">
								<option value="c7fe57be-b31d-46ff-ad45-40678920e7b6">Ubuntu14 64位</option>
								<option value="2d8b2564-61c7-4e87-b0db-28298d29bb3f">CentOS7 64位</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-success minwidth lp"
								id="button4">创建</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		
		
	</div>

</div>

<script src="js/jquery-2.0.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("a[id]").each(function() {
			//console.log(this);
			var obj = $(this);
			var idvalue = "#" + obj.attr("id") + "div";
			obj.click(function() {
				$("#tab1div").hide();
				$("#tab2div").hide();
				$("#tab3div").hide();
				$("#tab4div").hide();
				$(idvalue).show();
				var url=$(this).attr("data-url");
				var aid=$(this).attr("id");
				var whole_html="";
				$.post(url, {}, function(json)
						{
							if(aid=="tab3"){
								//console.log("tab3");
								//console.log(json);
								table_list_div3(json,whole_html);
							}

						},
						//返回类型
						"json");
			});
		});
		
		
		function table_list_div3(json,whole_html){
			$("#table_list_div3  tr:not(:first)").html("");
			var rbd_num=json.list.length;
			for (var i = 0; i < rbd_num; i++){
				var temp_html = "<tr style='font-family:Open Sans, sans-serif;font-size: 1.3em;'>"+
								"<th style='weight:5px'>Num</th>"+
								"<th>ID</th>"+
								"<th>Name</th>"+	
								"<th>status</th>"+
								"<th>isSnapshot</th>"+
								"<th>Size</th>"+
				                "<th>created</th>"+
				                "</tr>";
				temp_html = temp_html.replace("Num", i+1);
				temp_html = temp_html.replace("ID", json.list[i][0]);
				temp_html = temp_html.replace("Name",json.list[i][1]);
				temp_html = temp_html.replace("status", json.list[i][2]);
				if(json.list[i][3]=="false"){
					temp_html = temp_html.replace("isSnapshot","镜像");
				}else{
					temp_html = temp_html.replace("isSnapshot","快照");
				}
				temp_html = temp_html.replace("Size",json.list[i][4]);
				temp_html = temp_html.replace("created",json.list[i][5]);
				whole_html += temp_html;
			}
			$("#table_list_div3").append(whole_html);
		}
		
		
		
		
		
		
		$("#vmImage").change(function(){
			
			if($("#vmImage option:selected").val()=="0"){
				$("#ubuntu14").show();
				$("#centos7").hide();
				$("#win7").hide();
			}else if($("#vmImage option:selected").val()=="1"){
				$("#centos7").show();
				$("#ubuntu14").hide();
				$("#win7").hide();
			}else if($("#vmImage option:selected").val()=="2"){
				$("#centos7").hide();
				$("#ubuntu14").hide();	
				$("#win7").show();		
			}
		});
		$("#button2").click(function() {
			$.post("newvms", $("#create-vms-form").serialize(), function(data,statusText) {
				alert("创建成功");
			});
		});
		$("#button4").click(function() {
			$.post("newvm", $("#create-vms-form2").serialize(), function(data,statusText) {
				console.log(statusText);
				if(statusText=="success"){
					alert("创建成功");
					$("#hostname_vm").val("");
				}else
					{
						alert("创建失败");
					}
				
				
			});
		});
	});

	
</script>