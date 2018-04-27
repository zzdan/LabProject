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
		</ul>
	</div>
	<div class="col-md-12">
		<div id="tab1div">A</div>
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
								name="software" value="0"> Mysql
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="software" value="1"> Tomcat
							</label>
						</div>
					</div>


					<div class="form-group" id="centos7" style="display: none;">
						<label for="soft" class="col-sm-2 control-label">预装软件:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="software" value="0"> Mysql
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="software" value="1"> Tomcat
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
		<div id="tab3div" style="display: none;">C</div>
	</div>

</div>

<script src="js/jquery-2.0.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("a[id]").each(function() {
			var obj = $(this);
			var idvalue = "#" + obj.attr("id") + "div";
			obj.click(function() {
				$("#tab1div").hide();
				$("#tab2div").hide();
				$("#tab3div").hide();
				$(idvalue).show();
			});
		});
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
	});

	
</script>