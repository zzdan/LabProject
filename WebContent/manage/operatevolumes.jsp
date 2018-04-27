<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a
				href="javascript:void(0)" aria-controls="home" role="tab"
				data-toggle="tab" id="tab1" data-url="manage/managevms.action">云硬盘列表</a></li>
			<li role="presentation"><a href="javascript:void(0)"
				aria-controls="add" role="tab" data-toggle="tab" id="tab2">添加云硬盘</a></li>
		</ul>
	</div>
	<div class="col-md-12">
		<div id="tab1div">
		
		<table class="table table-bordered">
		<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
		<th style="weight:5px">Num</th>
		<th>ID</th>
		<th>Name</th>	
		<th>Description</th>
		<th>Status</th>
		<th>Size</th>
		<th>Zone</th>
		<th>Created</th>
		<th style="weight:5px">VolumeType</th>	
		<th>ImageRef</th>
		<th>Metadata</th>
		</tr>
		<s:iterator value="voluminfos" id="voluminfo" status="st">
				<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="#voluminfo[0]" /></td>
					<td><s:property value="#voluminfo[1]" /></td>
					<td><s:property value="#voluminfo[2]" /></td>
					<td><s:property value="#voluminfo[3]" /></td>
					<td><s:property value="#voluminfo[4]" /></td>
					<td><s:property value="#voluminfo[5]" /></td>
					<td><s:property value="#voluminfo[6]" /></td>
					<td><s:property value="#voluminfo[7]" /></td>
					<td><s:property value="#voluminfo[8]" /></td>
					<td><s:property value="#voluminfo[9]" /></td>
					<td><s:property value="#voluminfo[10]" /></td>
				</tr>
			</s:iterator>
	</table>
		
		</div>
		<div role="tabpanel" class="tab-pane" id="tab2div"
			style="display: none;">
			<div class="top5">

				<form class="form-horizontal" id="create-vol-form">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">云硬盘名称:</label>
						<div class="col-sm-10 maxwidth">
							<input type="text" class="form-control" id="volname"
								name="volname" placeholder="Enter your volume Name">
						</div>
					</div>

					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">云硬盘类型:</label>
						<div class="col-sm-10 maxwidth">
							<select class="form-control" name="voltype" id="voltype">
								<option>数据盘</option>
								<option>启动盘</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="data">
						<label for="shareable" class="col-sm-2 control-label">云硬盘共享:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="share" value="0"> 是
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="share" value="1"> 否
							</label>

						</div>
					</div>
					<div class="form-group" id="boot" style="display: none;">
						<label for="source" class="col-sm-2 control-label">云硬盘镜像源:</label>
						<div class="col-sm-10 maxwidth">
							<label class="checkbox-inline"> <input type="checkbox"
								name="imagename" value="0"> ubuntu14
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="imagename" value="1"> centos7
							</label> <label class="checkbox-inline"> <input type="checkbox"
								name="imagename" value="2"> win7
							</label>

						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">云硬盘大小:</label>
						<div class="col-sm-10 maxwidth">
							<input type="text" class="form-control" id="volsize"
								name="volsize" placeholder="Enter the Size of Volume(G)">
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
				$(idvalue).show();
			});
		});
		$("#voltype").change(function() {

			if ($("#voltype option:selected").text() == "启动盘") {
				$("#boot").show();
				$("#data").hide();

			} else {
				$("#boot").hide();
				$("#data").show();
			}
		});
		$("#button2").click(
				function() {
					$.post("newvolume", $("#create-vol-form").serialize(),
							function(data, statusText) {
								$("#volname").val("");
								alert("创建成功");
							});
				});
	});

	
</script>