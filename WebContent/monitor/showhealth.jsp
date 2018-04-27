<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-md-12">
		<div>
			<span class="ziti "> Cluster Health: </span> <span class="ziti">
				<s:property value="health"/> </span>
			<p style="color: #FF0000;font-family: 'Open Sans', sans-serif;font-size: 1.5em;"><s:property value="tips"/></p>
		</div>
	</div>

	<!-- END PAGE TITLE & BREADCRUMB-->

	<!-- END PAGE HEADER-->
	<!-- BEGIN DASHBOARD STATS -->
	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div id="mon" style="height: 150px"></div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div id="osd" style="height: 150px"></div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div id="mds" style="height: 150px"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<p style="text-align: center" class="ziti">MON <s:property value="mon_num"/>/<s:property value="mon_num"/></p>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<p style="text-align: center" class="ziti">OSD <s:property value="osd_up"/>/<s:property value="osd_sum"/></p>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<p style="text-align: center" class="ziti">MDS <s:property value="mds_up"/>/<s:property value="mds_sum"/></p>
		</div>
	</div>
</div>

<script type="text/javascript">
	function setPie() {
		// 路径配置
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});
		// 使用
		require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
			var myChart1 = ec.init(document.getElementById('mon'));
			var option1 = {
				color : [ 'green', 'red' ],
				series : [ {
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 2,
						name : 'up'
					}, {
						value : 1,
						name : 'down'
					}, ],
					itemStyle : {
						normal : {
							label : {
								show : false,
							},
							labelLine : {
								show : false,
							}
						}
					}

				} ]

			};			
			
			var myChart2 = ec.init(document.getElementById('osd'));
			var option2 = {
				color : [ 'green', 'red' ],
				series : [ {
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 2,
						name : 'up'
					}, {
						value : 1,
						name : 'down'
					}, ],
					itemStyle : {
						normal : {
							label : {
								show : false,
							},
							labelLine : {
								show : false,
							}
						}
					}

				} ]

			};
			
			var myChart3 = ec.init(document.getElementById('mds'));
			var option3 = {
				color : [ 'green', 'red' ],
				series : [ {
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 1,
						name : 'up'
					}, {
						value : 0,
						name : 'down'
					}, ],
					itemStyle : {
						normal : {
							label : {
								show : false,
							},
							labelLine : {
								show : false,
							}
						}
					}

				} ]

			};

			$.post("monitor/get_all_status",{}, function(json)
			{
				option1.series[0].data[0].value=<s:property value="mon_num"/>;
				option1.series[0].data[1].value=<s:property value="mon_num"/>-<s:property value="mon_num"/>;		
				myChart1.setOption(option1);
				
				option2.series[0].data[0].value=<s:property value="osd_up"/>;
				option2.series[0].data[1].value=<s:property value="osd_sum"/>-<s:property value="osd_up"/>;		
				myChart2.setOption(option2);
				
				option3.series[0].data[0].value=<s:property value="mds_up"/>;
				option3.series[0].data[1].value=<s:property value="mds_sum"/>-<s:property value="mds_up"/>;		
				myChart3.setOption(option3);

			},
			//返回类型
			"json");

		});
	}	
	setPie();
</script>
