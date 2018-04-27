<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.1.1
Version: 2.0.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Welcome to Storage Management for Ceph</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet"
	type="text/css" />
<link
	href="assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
<link href="assets/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="assets/css/print.css" rel="stylesheet" type="text/css"
	media="print" />
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet" media="screen">
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="header-inner">
			<!-- BEGIN LOGO -->
			<a class="navbar-brand" href="index.html">
				<p class="biaoti">Storage Manage for Ceph</p>
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse"> <img
				src="assets/img/menu-toggler.png" alt="" />
			</a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<li class="dropdown" id="header_notification_bar"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					data-hover="dropdown" data-close-others="true"> <i
						class="fa fa-warning"></i> <span class="badge"> 2 </span>
				</a>
					<ul class="dropdown-menu extended notification">
						<li>
							<p>You have 2 new notifications</p>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 250px;">
								<li><a href="#"> <span
										class="label label-sm label-icon label-danger"> <i
											class="fa fa-bolt"></i>
									</span> Cluster Heath isn't normal. <span class="time"> 15 mins
									</span>
								</a></li>
								<li><a href="#"> <span
										class="label label-sm label-icon label-warning"> <i
											class="fa fa-bell-o"></i>
									</span> part of osd is down. <span class="time"> 22 mins </span>
								</a></li>
							</ul>
						</li>
						<li class="external"><a href="#"> See all notifications <i
								class="m-icon-swapright"></i>
						</a></li>
					</ul></li>
				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" data-hover="dropdown"
					data-close-others="true"> <img alt=""
						src="assets/img/avatar1_small.jpg" /> <span class="username">
							Bob Nilson </span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" id="trigger_fullscreen"> <i
								class="fa fa-arrows"></i> Full Screen
						</a></li>
						<li><a href="extra_lock.html"> <i class="fa fa-lock"></i>
								Lock Screen
						</a></li>
						<li><a href="login.html"> <i class="fa fa-key"></i> Log
								Out
						</a></li>
					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<div class="page-sidebar navbar-collapse collapse">
				<!-- add "navbar-no-scroll" class to disable the scrolling of the sidebar menu -->
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu" data-auto-scroll="true"
					data-slide-speed="200">
					<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li class="sidebar-search-wrapper">
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
						<form class="sidebar-search" action="extra_search.html"
							method="POST">
							<div class="form-container">
								<div class="input-box">
									<a href="javascript:;" class="remove"> </a> <input type="text"
										placeholder="Search..." /> <input type="button"
										class="submit" value=" " />
								</div>
							</div>
						</form> <!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>
					<li class="menu_level_1"><a href="javascript:;"> <span
							class="title"> Dashboard </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="monitor/showhealth.action"> Cluster Health </a></li>
						</ul></li>

					<li class="menu_level_1"><a href="javascript:;"> <span
							class="title"> Monitor Cluster </span> <span class="arrow ">
						</span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="monitor/showmon.action"> MON Status </a></li>
							<li><a href="javascript:void(0)"
								data-url="monitor/showosd.action"> OSD Status </a></li>
							<li><a href="javascript:void(0)"
								data-url="monitor/showmds.action"> MDS Status </a></li>
							<li><a href="javascript:void(0)"
								data-url="monitor/showpool.action"> Pool Status </a></li>
							<li><a href="javascript:void(0)"
								data-url="monitor/showpg.action"> PG Status </a></li>

						</ul></li>
					<li class="menu_level_1"><a href="javascript:;"> <span class="title">
								Manage Cluster </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="manage/managemon.action"> Manage Monitors </a></li>
							<li><a  href="javascript:void(0)"
								data-url="manage/manageosd.action"> Manage Osds </a></li>
						</ul></li>
					<li class="menu_level_1"><a href="javascript:;"> <span class="title">
								Manage Cluster Storage </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="manage/managepool.action"> Manage Pools </a></li>
							<li><a href="javascript:void(0)"
								data-url="manage/managerbd.action"> Manage RBD </a></li>
							<li><a href="javascript:void(0)"
								data-url="manage/managecrushmap.action"> Manage Crushmap </a></li>
							

						</ul></li>
						<li class="menu_level_1"><a href="javascript:;"> <span class="title">
								Manage VM Bandwidth </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="monitor/showvm.action"> Allocate VM Bandwidth </a></li>
						</ul></li>
						
					<li class="menu_level_1"><a href="javascript:;"> <span class="title">
								Manage OpenStack </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="javascript:void(0)"
								data-url="manage/managevms.action"> Manage VMs </a></li>
							<li><a href="javascript:void(0)"
								data-url="manage/managevolumes.action"> Manage Volumes </a></li>
						</ul></li>
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">Modal title</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
							<div class="modal-footer">
								<button type="button" class="btn blue">Save changes</button>
								<button type="button" class="btn default" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

				<!-- BEGIN PAGE HEADER-->
				<div class="row ">
					<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
					<div class="modal fade" id="portlet-config" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">Modal title</h4>
								</div>
								<div class="modal-body">Widget settings form goes here</div>
								<div class="modal-footer">
									<button type="button" class="btn blue">Save changes</button>
									<button type="button" class="btn default" data-dismiss="modal">Close</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
					<!-- BEGIN STYLE CUSTOMIZER -->
					<div class="theme-panel hidden-xs hidden-sm"
						style="margin-right: 25px;">
						<div class="toggler" style="top: 12px;"></div>
						<div class="toggler-close"></div>
						<div class="theme-options">
							<div class="theme-option theme-colors clearfix">
								<span> THEME COLOR </span>
								<ul>
									<li class="color-black current color-default"
										data-style="default"></li>
									<li class="color-blue" data-style="blue"></li>
									<li class="color-brown" data-style="brown"></li>
									<li class="color-purple" data-style="purple"></li>
									<li class="color-grey" data-style="grey"></li>
									<li class="color-white color-light" data-style="light"></li>
								</ul>
							</div>
							<div class="theme-option">
								<span> Layout </span> <select
									class="layout-option form-control input-small">
									<option value="fluid" selected="selected">Fluid</option>
									<option value="boxed">Boxed</option>
								</select>
							</div>
							<div class="theme-option">
								<span> Header </span> <select
									class="header-option form-control input-small">
									<option value="fixed" selected="selected">Fixed</option>
									<option value="default">Default</option>
								</select>
							</div>
							<div class="theme-option">
								<span> Sidebar </span> <select
									class="sidebar-option form-control input-small">
									<option value="fixed">Fixed</option>
									<option value="default" selected="selected">Default</option>
								</select>
							</div>
							<div class="theme-option">
								<span> Sidebar Position </span> <select
									class="sidebar-pos-option form-control input-small">
									<option value="left" selected="selected">Left</option>
									<option value="right">Right</option>
								</select>
							</div>
							<div class="theme-option">
								<span> Footer </span> <select
									class="footer-option form-control input-small">
									<option value="fixed">Fixed</option>
									<option value="default" selected="selected">Default</option>
								</select>
							</div>
						</div>
					</div>
					<!-- END STYLE CUSTOMIZER -->


					<div style="padding: 0px 25px;">
						<div class="row">
							<div class="col-md-12">
								<!-- BEGIN PAGE TITLE & BREADCRUMB-->
								<h3 class="page-title" id="menu_level_1">Dashboard</h3>
								<ul class="page-breadcrumb breadcrumb">
									<li><i class="fa fa-angle-right"></i></li>
									<li id="menu_level_2">Cluster General Status</li>
								</ul>
							</div>
						</div>
					</div>

					<!-- 子页面开始 -->
					<div id="main_panel_div" style="padding: 0px 25px;"></div>


					<!-- 子页面结束 -->



				</div>




			</div>

		</div>
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">2016 &copy; Ceph Management Tools.</div>
		<div class="footer-tools">
			<span class="go-top"> <i class="fa fa-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

	<script src="assets/plugins/jquery-1.10.2.min.js"
		type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/scripts/core/app.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	<script>
		$(function()
		{
			App.init(); // initlayout and core plugins

			$("a[data-url]").each(function()
			{
				var obj = $(this);
				obj.click(function()
				{
					//将子页面load进来
					$("#main_panel_div").html('<img src="images/ball-triangle.svg" width="100" class="center-block" style="margin-top: 100px; ">');
					$("#main_panel_div").load(obj.attr("data-url"));

					//改变导航条文本
					var level_1_text = obj.closest(".menu_level_1").find("a:first").text().trim();
					var level_2_text = obj.text().trim();
					$("#menu_level_1").text(level_1_text);
					$("#menu_level_2").text(level_2_text);
				});
			});
			$("a[data-url]").first().trigger("click");
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>