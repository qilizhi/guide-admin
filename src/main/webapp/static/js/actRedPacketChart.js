/**
 * 用户图表
 */

var _RedPacketChart = function() {

	var initChart1 = function(data) {
		var chart = AmCharts
				.makeChart(
						"chart_7",
						{
							"type" : "pie",
							"theme" : "light",

							"fontFamily" : 'Open Sans',

							"color" : '#888',

							"dataProvider" : data,
							"valueField" : "value",
							"titleField" : "category",
							"outlineAlpha" : 0.4,
							"depth3D" : 15,
							"balloonText" : "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
							"angle" : 30,
							"exportConfig" : {
								menuItems : [ {
									icon : '/lib/3/images/export.png',
									format : 'png'
								} ]
							}
						});

		jQuery('.chart_7_chart_input').off().on('input change', function() {
			var property = jQuery(this).data('property');
			var target = chart;
			var value = Number(this.value);
			chart.startDuration = 0;

			if (property == 'innerRadius') {
				value += "%";
			}

			target[property] = value;
			chart.validateNow();
		});

		$('#chart_7').closest('.portlet').find('.fullscreen').click(function() {
			chart.invalidateSize();
		});
	}
	var initChart2 = function(data) {

		var chart = AmCharts.makeChart("chart_6", {
			"type" : "pie",
			"theme" : "light",

			"fontFamily" : 'Open Sans',

			"color" : '#888',

			"dataProvider" : data,
			"valueField" : "value",
			"titleField" : "category",
			"exportConfig" : {
				menuItems : [ {
					icon : App.getGlobalPluginsPath()
							+ "amcharts/amcharts/images/export.png",
					format : 'png'
				} ]
			}
		});

		$('#chart_6').closest('.portlet').find('.fullscreen').click(function() {
			chart.invalidateSize();
		});

	}
	var initChart3 = function(data) {
		var chart = AmCharts.makeChart("chart_3", {
			"type" : "serial",
			"theme" : "light",

			"fontFamily" : 'Open Sans',
			"color" : '#888888',

			"pathToImages" : "../../static/assets/global/plugins/amcharts/amcharts/images/",

			"dataProvider" : data,
			"balloon" : {
				"cornerRadius" : 6
			},
			"valueAxes" : [ {
				"value" : "mm",
				"durationUnits" : {
					"hh" : "h ",
		
					"mm" : "元"
				},
				"axisAlpha" : 0
			} ],
			"graphs" : [ {
				"bullet" : "square",
				"bulletBorderAlpha" : 1,
				"bulletBorderThickness" : 1,
				"fillAlphas" : 0.3,
				"fillColorsField" : "color",
				"legendValueText" : "[[value]]",
				"lineColorField" : "color",
				"title" : "value",
				"valueField" : "value"
			} ],
			"chartScrollbar" : {},
			"chartCursor" : {
				"categoryBalloonDateFormat" : "YYYY MMM DD",
				"cursorAlpha" : 0,
				"zoomable" : false
			},
			"dataDateFormat" : "YYYY-MM-DD",
			"categoryField" : "category",
			"categoryAxis" : {
				"dateFormats" : [ {
					"period" : "DD",
					"format" : "DD"
				}, {
					"period" : "WW",
					"format" : "MMM DD"
				}, {
					"period" : "MM",
					"format" : "MMM"
				}, {
					"period" : "YYYY",
					"format" : "YYYY"
				} ],
				"parseDates" : true,
				"autoGridCount" : false,
				"axisColor" : "#555555",
				"gridAlpha" : 0,
				"gridCount" : 50
			}
		});

		$('#chart_3').closest('.portlet').find('.fullscreen').click(function() {
			chart.invalidateSize();
		});
	}

	return {
		// main function to initiate the module
		initRedPacketChart1 : function(data) {
			initChart1(data);

		},
		initRedPacketChart2 : function(data) {
			initChart2(data);
		},
		initRedPacketChart3 : function(data) {
			initChart3(data);
		}
	};

}();
