<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<div>
			<p>
				<span class="ziti"> OSD Summary</span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Osdmap Epoch:</span>&nbsp;&nbsp;<span id="epochvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Osds Total:</span>&nbsp;&nbsp;<span id="totalvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Osds UP:</span>&nbsp;&nbsp;<span id=upvalue></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Osds IN:</span>&nbsp;&nbsp;<span id="invalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Full:</span>&nbsp;&nbsp;<span id="fullvalue"></span>
			</p>
			<p
				style="color: #666; font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<span>Nearfull:</span>&nbsp;&nbsp;<span id="nearfullvalue"></span>
			</p>
		</div>
	</div>
	<div class="col-md-12">
		<p>
			<span class="ziti"> OSD List</span>
		</p>
		<table class="table table-bordered">
			<tr style="font-family: 'Open Sans', sans-serif; font-size: 1.3em;">
				<th>ID</th>
				<th>State</th>
				<th>Attach Status</th>
				<th>CRUSH weight</th>
				<th>Server</th>
				<th>Capacity Total</th>
				<th>Capacity Used</th>
				<th>Capacity Available</th>
				<th>Percent Used Capacity(%)</th>
			</tr>
			<s:iterator value="infoMap" id="infoMap">
				<tr>
					<td><s:property value="#infoMap.key" /></td>
					<td><s:property value="#infoMap.value[1]" /></td>
					<td><s:property value="#infoMap.value[2]" /></td>
					<td><s:property value="#infoMap.value[3]" /></td>
					<td><s:property value="#infoMap.value[4]" /></td>
					<td><s:property value="#infoMap.value[5]" /></td>
					<td><s:property value="#infoMap.value[6]" /></td>
					<td><s:property value="#infoMap.value[7]" /></td>
					<td><s:property value="#infoMap.value[8]" /></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</div>
<div id="jsonosd" style="display: none;">
	<s:property value="jsonosd" />
</div>
<script type="text/javascript">
     
	var json = JSON.parse($("#jsonosd").text());
	var epo=json.osdmap.osdmap.epoch;
	var osd_total=json.osdmap.osdmap.num_osds;
	var osd_up=json.osdmap.osdmap.num_up_osds;
	var osd_in=json.osdmap.osdmap.num_in_osds;
	var osd_full=json.osdmap.osdmap.full;
	var osd_nearfull=json.osdmap.osdmap.nearfull;
	$("#epochvalue").text(epo);
	$("#totalvalue").text(osd_total);
	$("#upvalue").text(osd_up);
	$("#invalue").text(osd_in);
	$("#fullvalue").text(osd_full);
	$("#nearfullvalue").text(osd_nearfull);
</script>