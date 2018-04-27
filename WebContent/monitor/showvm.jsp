<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a
				href="javascript:void(0)" aria-controls="home" role="tab"
				data-toggle="tab" id="tab1" data-url="manage/managevms.action">虚拟机列表</a></li>
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
		
	</div>

</div>

