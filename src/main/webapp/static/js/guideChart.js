/**
 * 用户图表
 */

var _userChart = function() {

	var initChart1 = function(data) {
		var chart1 = AmCharts
				.makeChart(
						"chart_1",
						{
							"type" : "serial",
							"theme" : "light",
							"pathToImages" : App.getGlobalPluginsPath()
									+ "amcharts/amcharts/images/",
							"autoMargins" : false,
							"marginLeft" : 30,
							"marginRight" : 8,
							"marginTop" : 10,
							"marginBottom" : 26,

							"fontFamily" : 'Open Sans',
							"color" : '#888',

							"dataProvider" : data,
							"valueAxes" : [ {
								"axisAlpha" : 0,
								"position" : "left"
							} ],
							"startDuration" : 1,
							"graphs" : [ {
								"alphaField" : "alpha",
								"balloonText" : "<span style='font-size:13px;'>[[category]][[title]]:<b>[[value]]</b> [[additional]]</span>",
								"dashLengthField" : "dashLengthColumn",
								"fillAlphas" : 1,
								"title" : "新增用户",
								"type" : "column",
								"valueField" : "value"
							} /*
								 * , { "balloonText": "<span
								 * style='font-size:13px;'>[[title]] in
								 * [[category]]:<b>[[value]]</b>
								 * [[additional]]</span>", "bullet": "round",
								 * "dashLengthField": "dashLengthLine",
								 * "lineThickness": 3, "bulletSize": 7,
								 * "bulletBorderAlpha": 1, "bulletColor":
								 * "#FFFFFF", "useLineColorForBulletBorder":
								 * true, "bulletBorderThickness": 3,
								 * "fillAlphas": 0, "lineAlpha": 1, "title":
								 * "Expenses", "valueField": "expenses" }
								 */],
							"categoryField" : "date",
							"categoryAxis" : {
								"gridPosition" : "start",
								"axisAlpha" : 0,
								"tickLength" : 0
							}
						});

		$('#chart_1').closest('.portlet').find('.fullscreen').click(function() {
			chart1.invalidateSize();
		});

	}
	var initChart2 = function(data) {
		var chart2 = AmCharts
				.makeChart(
						"chart_2",
						{
							"type" : "serial",
							"theme" : "light",
							"pathToImages" : App.getGlobalPluginsPath()
									+ "amcharts/amcharts/images/",
							"autoMargins" : false,
							"marginLeft" : 30,
							"marginRight" : 8,
							"marginTop" : 10,
							"marginBottom" : 26,

							"fontFamily" : 'Open Sans',
							"color" : '#888',

							"dataProvider" : data,
							"valueAxes" : [ {
								"axisAlpha" : 0,
								"position" : "left"
							} ],
							"startDuration" : 1,
							"graphs" : [ {
								"alphaField" : "alpha",
								"balloonText" : "<span style='font-size:13px;'>[[category]][[title]]:<b>[[value]]</b> [[additional]]</span>",
								"dashLengthField" : "dashLengthColumn",
								"fillAlphas" : 1,
								"title" : "新注册导游",
								"type" : "column",
								"valueField" : "value"
							} /*
								 * , { "balloonText": "<span
								 * style='font-size:13px;'>[[title]] in
								 * [[category]]:<b>[[value]]</b>
								 * [[additional]]</span>", "bullet": "round",
								 * "dashLengthField": "dashLengthLine",
								 * "lineThickness": 3, "bulletSize": 7,
								 * "bulletBorderAlpha": 1, "bulletColor":
								 * "#FFFFFF", "useLineColorForBulletBorder":
								 * true, "bulletBorderThickness": 3,
								 * "fillAlphas": 0, "lineAlpha": 1, "title":
								 * "Expenses", "valueField": "expenses" }
								 */],
							"categoryField" : "date",
							"categoryAxis" : {
								"gridPosition" : "start",
								"axisAlpha" : 0,
								"tickLength" : 0
							}
						});
		$('#chart_2').closest('.portlet').find('.fullscreen').click(function() {
			chart2.invalidateSize();
		});

	}

	return {
		// main function to initiate the module

		initMemberChart : function(data) {
			initChart1(data);

		},
		initGuideChart : function(data) {

			initChart2(data);
		}
	};

}();

