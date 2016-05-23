<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动统计</title>
</head>
<body>

	<div class="row">


		<div class="col-md-12">
			<!-- BEGIN CHART PORTLET-->
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-bar-chart font-green-haze"></i> <span
							class="caption-subject bold uppercase font-green-haze">
							当月每天发达红包数:</span> <span class="caption-helper">(所有活动/元)</span>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"> </a> <a
							href="#portlet-config" data-toggle="modal" class="config"> </a>
						<!-- 	 <a		href="javascript:;" class="reload"> </a> -->
							 <a href="javascript:;"
							class="fullscreen"> </a> <a href="javascript:;" class="remove">
						</a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="chart_3" class="chart" style="height: 400px;"></div>
				</div>
			</div>
			<!-- END CHART PORTLET-->
		</div>


		<div class="col-md-6">
			<!-- BEGIN CHART PORTLET-->
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-bar-chart font-green-haze"></i> <span
							class="caption-subject bold uppercase font-green-haze">
							所有红包统计：</span><span class="caption-helper" id="chart1"></span>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"> </a> <a
							href="#portlet-config" data-toggle="modal" class="config"> </a>
						<!--  <a
							href="javascript:;" class="reload"> </a>  -->

						<a href="javascript:;" class="fullscreen"> </a> <a
							href="javascript:;" class="remove"> </a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="chart_6" class="chart" style="height: 525px;"></div>
				</div>
			</div>
			<!-- END CHART PORTLET-->
		</div>
		<div class="col-md-6">
			<!-- BEGIN CHART PORTLET-->
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-bar-chart font-green-haze"></i> <span
							class="caption-subject bold uppercase font-green-haze">已发送红包统计：</span>
						<span class="caption-helper" id="chart2"></span>
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"> </a> <a
							href="#portlet-config" data-toggle="modal" class="config"> </a>
						<!-- 		 <a
							href="javascript:;" class="reload"> </a> <a href="javascript:;"
							class="fullscreen"> </a> -->

						<a href="javascript:;" class="remove"> </a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="chart_7" class="chart" style="height: 400px;"></div>
					<div class="well margin-top-20">
						<div class="row">
							<div class="col-sm-3">
								<label class="text-left">Top Radius:</label> <input
									class="chart_7_chart_input" data-property="topRadius"
									type="range" min="0" max="1.5" value="1" step="0.01" />
							</div>
							<div class="col-sm-3">
								<label class="text-left">Angle:</label> <input
									class="chart_7_chart_input" data-property="angle" type="range"
									min="0" max="89" value="30" step="1" />
							</div>
							<div class="col-sm-3">
								<label class="text-left">Depth:</label> <input
									class="chart_7_chart_input" data-property="depth3D"
									type="range" min="1" max="120" value="40" step="1" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END CHART PORTLET-->
		</div>
	</div>
	<!-- END ROW -->
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/amcharts.js"
		type="text/javascript"></script>

	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/serial.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/pie.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/radar.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/themes/light.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/themes/patterns.js"
		type="text/javascript"></script>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/amcharts/themes/chalk.js"
		type="text/javascript"></script>
	<%--  <script src="${ctx}/static/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script> --%>
	<script
		src="${ctx}/static/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js"
		type="text/javascript"></script>
	<%-- <script src="${ctx}/static/assets/pages/scripts/charts-amcharts.js"
		type="text/javascript"></script> --%>
	<script src="${ctx}/static/js/actRedPacketChart.js"
		type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function() {
			var url1 = mlx.ctx + "/admin/actRedPacket/sendRedPack";
			var url2 = mlx.ctx + "/admin/actRedPacket/totalRedPacket";
			var url3 = mlx.ctx + "/admin/actRedPacket/dayRedPacket";
			var sendedRedPacketData = "";
			var totalRedpacketData = "";
			var dayRedpacketData = "";
			//获取数据
			$.ajax({
				url : url1,
				data : {
					status : 1
				},
				type : "get",
				async : false,
				success : function(result) {

					sendedRedPacketData = result.result;
					var sum = 0;
					$.each(sendedRedPacketData, function(index, data) {

						sum += parseFloat(data.value);
					})
					$("#chart2").html("所有活动已发红包总共 :" + sum + " 元(百分比)");
				}

			});
			$.ajax({
				url : url2,
				data : {
					status : 2
				},
				type : "get",
				async : false,
				success : function(result) {
					var sum = 0;
					$.each(sendedRedPacketData, function(index, data) {

						sum += parseFloat(data.value);
					})
					$("#chart1").html("所有活动红包总共 :" + sum + " 元(百分比)");

					totalRedpacketData = result.result;
				}

			});
			$.ajax({
				url : url3,
				type : "get",
				async : false,
				success : function(result) {
						dayRedpacketData = result.result;
				}

			});
			//画图表
			/* 		console.log(sendedRedPacketData);
					console.log(sendingRedpacketData); */
				//	console.log(dayRedpacketData);
			_RedPacketChart.initRedPacketChart1(sendedRedPacketData);
			_RedPacketChart.initRedPacketChart2(totalRedpacketData);
			_RedPacketChart.initRedPacketChart3(dayRedpacketData);

		})
	</script>
</body>
</html>