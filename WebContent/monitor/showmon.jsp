<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<div>
			<p>
				<span class="ziti"> Monitor Summary</span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Monitors Total:</span>&nbsp;&nbsp;<span><s:property
						value="mon_num" /> </span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Monmap Epoch:</span>&nbsp;&nbsp;<span id="epochvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Quorum:</span>&nbsp;&nbsp;<span id="quorumvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Overall Status:</span>&nbsp;&nbsp;<span id="statvalue"></span>
			</p>
		</div>
	</div>

	<!-- END PAGE TITLE & BREADCRUMB-->
	<!-- END PAGE HEADER-->
	<!-- BEGIN DASHBOARD STATS -->
	<div class="col-md-12">
		<p>
			<span class="ziti"> Monitor List</span>
		</p>
		<table id="mon_table_div" class="table table-bordered">
			<tr style="font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<th>Quorum</th>
				<th>Name</th>
				<th>Address</th>
				<th>Health</th>
				<th>Skew</th>
				<th>Latency</th>
				<th>MB Total</th>
				<th>MB Used</th>
				<th>MB Avail</th>
				<th>Avail Percent(%)</th>
			</tr>

		</table>
	</div>
</div>
<div id="jsonmon" style="display: none;">
	<s:property value="jsonmon" />
</div>

<script type="text/javascript">
     
	var json = JSON.parse($("#jsonmon").text());
	var mon_count = json.monmap.mons.length;
	var stat=json.health.overall_status;
	var quo=json.quorum;
	var epo=json.monmap.epoch;
	$("#epochvalue").text(epo);
	$("#quorumvalue").text(quo);
	$("#statvalue").text(stat);
	var all_content = "";
	for (var i = 0; i < mon_count; i++)
	{
		var quorum = json.quorum[i];
		var name = json.health.health.health_services[0].mons[i].name;
		var address = json.monmap.mons[i].addr;
		address = address.substring(0, address.indexOf("\/"));
		if (mon_count > 1)
		{
			var skew = json.health.timechecks.mons[i].skew;
			var latency = json.health.timechecks.mons[i].latency;
		}
		var health = json.health.health.health_services[0].mons[i].health;
		var kb_total = json.health.health.health_services[0].mons[i].kb_total;
		var kb_used = json.health.health.health_services[0].mons[i].kb_used;
		var kb_avail = json.health.health.health_services[0].mons[i].kb_avail;
		var avail_percent = json.health.health.health_services[0].mons[i].avail_percent;
		

		var temp_content = '<tr><td>TEMP_1</td><td>TEMP_2</td><td>TEMP_3</td><td>TEMP_4</td><td>TEMP_5</td><td>TEMP_6</td><td>TEMP_7</td><td>TEMP_8</td><td>TEMP_9</td><td>TEMP_10</td></tr>';

		temp_content = temp_content.replace("TEMP_1", quorum);
		temp_content = temp_content.replace("TEMP_2", name);
		temp_content = temp_content.replace("TEMP_3", address);
		temp_content = temp_content.replace("TEMP_4", health);
		temp_content = temp_content.replace("TEMP_5", skew);
		temp_content = temp_content.replace("TEMP_6", latency);
		temp_content = temp_content.replace("TEMP_7", kb_total);
		temp_content = temp_content.replace("TEMP_8", kb_used);
		temp_content = temp_content.replace("TEMP_9", kb_avail);
		temp_content = temp_content.replace("TEMP_10", avail_percent);

		all_content += temp_content;
	}

	$("#mon_table_div").append(all_content);

	if (mon_count == 1)
	{
		$("#mon_table_div tr td").eq(5).remove();
		$("#mon_table_div tr td").eq(4).remove();
		$("#mon_table_div tr th").eq(5).remove();
		$("#mon_table_div tr th").eq(4).remove();
	}

	
</script>