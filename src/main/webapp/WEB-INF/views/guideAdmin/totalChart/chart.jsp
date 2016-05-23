<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图表</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN CHART PORTLET-->
			<div class="row">
				<div class="col-md-6">
					<!-- BEGIN CHART PORTLET-->
                      <div class="portlet light portlet-fit bordered">
                          <div class="portlet-title">
                              <div class="caption">
                                  <i class=" icon-layers font-green"></i>
                                  <span class="caption-subject font-green bold uppercase">${thisYear}年每月销售情况</span>
                              </div>
                              <div class="actions">
                                  <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                      <i class="icon-cloud-upload"></i>
                                  </a>
                                  <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                      <i class="icon-wrench"></i>
                                  </a>
                                  <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                      <i class="icon-trash"></i>
                                  </a>
                              </div>
                          </div>
                          <div class="portlet-body">
                              <div id="echarts_bar" style="height: 500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);" _echarts_instance_="1461034473688"><div style="position: relative; overflow: hidden; width: 1050px; height: 500px;"><div data-zr-dom-id="bg" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 1050px; height: 500px; -webkit-user-select: none;"></div><canvas width="1050" height="500" data-zr-dom-id="0" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 1050px; height: 500px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="1050" height="500" data-zr-dom-id="1" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 1050px; height: 500px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="1050" height="500" data-zr-dom-id="_zrender_hover_" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 1050px; height: 500px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><div class="echarts-dataview" style="position: absolute; display: block; overflow: hidden; transition: height 0.8s, background-color 1s; z-index: 1; left: 0px; top: 0px; width: 1050px; height: 0px; background-color: rgb(240, 255, 255);"></div><div class="echarts-tooltip zr-element" style="position: absolute; display: none; border: 0px solid rgb(51, 51, 51); white-space: nowrap; transition: left 0.4s, top 0.4s; border-radius: 4px; color: rgb(255, 255, 255); padding: 5px; left: 795px; top: 209px; background-color: rgba(0, 0, 0, 0.701961);">Oct<br>unitPrice : 20<br>saleQuantity : 18.8</div></div></div>
                          </div>
                      </div>
					
					<!-- END CHART PORTLET-->
				</div>

			</div>
			<!-- END CHART PORTLET-->
		</div>
	</div>
	<script type="text/javascript">
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>

    <script src="${ctx}/static/assets/global/plugins/echarts/echarts.js" type="text/javascript"></script>

<script type="text/javascript">
jQuery(document).ready(function() {
    // ECHARTS
    require.config({
        paths: {
            echarts: '${ctx}/static/assets/global/plugins/echarts/'
        }
    });

    // DEMOS
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/chord',
            'echarts/chart/eventRiver',
            'echarts/chart/force',
            'echarts/chart/funnel',
            'echarts/chart/gauge',
            'echarts/chart/heatmap',
            'echarts/chart/k',
            'echarts/chart/line',
            'echarts/chart/map',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/scatter',
            'echarts/chart/tree',
            'echarts/chart/treemap',
            'echarts/chart/venn',
            'echarts/chart/wordCloud'
        ],
        function(ec) {
        	//取数据
        	
        	$.ajax({
        		url:"${ctx}/guideAdmin/total/order",
        		type:"get",
        		dataType:"json",
        		success:function(data){
        			var datas=data.result;
        			console.log(datas);
        			createChart(datas.totalPrice,datas.totalNum);
        			
        		},error:function(e){
        			
        		}
        	});
        	
        	
        	var createChart=function (totalPrice,totNum){
        		  //--- BAR ---
                var myChart = ec.init(document.getElementById('echarts_bar'));
                myChart.setOption({
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['totalPrice', 'totalQuantity']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                    }],
                    yAxis: [{
                        type: 'value',
                        splitArea: {
                            show: true
                        }
                    }],
                    series: [{
                        name: 'totalPrice',
                        type: 'bar',
                        data: totalPrice
                    }, {
                        name: 'totalQuantity',
                        type: 'bar',
                        data: totNum
                    }]
                });
        		
        	}
        	
        	
        	
          

            
        }
    );
});


</script>
	

</body>
</html>