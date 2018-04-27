<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-md-12">
	<h1>tree info</h1>
	<table class="table table-bordered">
		<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
		<th>ID</th>
		<th>WEIGHT</th>	
		<th>TYPE/NAME</th>
		<th>UP/DOWN</th>
		<th>REWEIGHT</th>
		<th>PRIMARY-AFFINITY </th>	
		</tr>
		<s:iterator value="treeinfos" id="treeinfo" status="st">
				<tr style="font-family: 'Open Sans', sans-serif;font-size: 1.3em;">
					<td><s:property value="#treeinfo[0]" /></td>
					<td><s:property value="#treeinfo[1]" /></td>
					<td><s:property value="#treeinfo[2]" /></td>
					<td><s:property value="#treeinfo[3]" /></td>
					<td><s:property value="#treeinfo[4]" /></td>
					<td><s:property value="#treeinfo[5]" /></td>
				</tr>
			</s:iterator>
	</table>
	
	    
</div>
