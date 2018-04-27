<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<div>
			<p>
				<span class="ziti"> MDS Summary</span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Mdsmap Epoch:</span>&nbsp;&nbsp;<span id="epochvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Mds UP:</span>&nbsp;&nbsp;<span id="upvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Mds IN:</span>&nbsp;&nbsp;<span id="invalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Mds Max:</span>&nbsp;&nbsp;<span id="maxvalue"></span>
			</p>
		</div>
	</div>
	<div class="col-md-12" id="list_div">
		<p>
			<span class="ziti"> MDS List</span>
		</p>
		<table id="table_list_div" class="table table-bordered">
			<tr style="font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<th>Name</th>
				<th>Server</th>
				<th>Status</th>
			</tr>
		</table>
	</div>
</div>
<div id="jsonmds" style="display: none;">
	<s:property value="jsonmds" />
</div>



<script type="text/javascript">
	var json = JSON.parse($("#jsonmds").text());
	var epo = json.mdsmap.epoch;
	var mds_up = json.mdsmap.up;
	var mds_max = json.mdsmap.max;
	var mds_in = json.mdsmap["in"];
	$("#epochvalue").text(epo);
	$("#upvalue").text(mds_up);
	$("#maxvalue").text(mds_max);
	$("#invalue").text(mds_in);

	if (parseInt(mds_up) > 0)
	{
		$.post("monitor/get_mds_list", {}, function(json)
		{
			var temp_html = "<tr><td>NAME</td><td>SERVER</td><td>STAT</td></tr>";
			temp_html = temp_html.replace("NAME", json.list[0]);
			temp_html = temp_html.replace("SERVER", json.list[1]);
			temp_html = temp_html.replace("STAT", json.list[2]);
			$("#table_list_div").append(temp_html);
		},
		//返回类型
		"json");
	}
	else
	{
		$("#list_div").css("display", "none");
	}

	
</script>