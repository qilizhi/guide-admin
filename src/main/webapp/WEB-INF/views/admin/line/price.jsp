<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建线路</title>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/price-calendar/price-calendar.js"></script>

</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						线路价格编辑
						<!-- <a class="btn btn-primary btn-xs panel-title-right" href="javascript:history.go(-1);">返回</a> -->
					</h3>
				</div>
				<div class="panel-body">
					<div class="price-condition">
						<input type="hidden" id="routeOrderType" value="" /> 
						<label class="price-condition-label">线路名称:</label> ${guideLine.title}
						<input type="hidden" name="lineNo" value="${guideLine.lineNo}"/>
					</div>
					<div class="price-condition">
						<label class="price-condition-label">门市价：</label> <input
							type="text" id="adultPrice" name="adultPrice" value="${g_price.adultPrice }"
							placeholder="请输入门市价" />
					</div>
					<div class="price-condition">
						<label class="price-condition-label">美丽价：</label> <input
							type="text" id="mlxPrice" name="mlxPrice" value="${g_price.mlxPrice }"
							placeholder="请输入美丽价" /> <label class="price-condition-label">儿童价：</label>
						<input type="text" id="childPrice" name="childPrice" value="${g_price.childPrice }"
							placeholder="请输入儿童价" />
					</div>

					<div class="price-condition">
					<label class="price-condition-label">指定时间段:</label>
                               <div class="date date-picker" data-date-format="yyyy-mm-dd">
                               <input type="text" class="WdatePicker "  readonly name="beginTime">
                               <span class="">
                                   <button class="btn default cal" type="button">
                                       <i class="fa fa-calendar"></i>
                                   </button>
                               </span>
                           </div>
                    <label class="price-condition-label">至</label>
                               <div class="date date-picker" data-date-format="yyyy-mm-dd">
                               <input type="text" class="WdatePicker "  readonly name="endTime">
                               <span class="">
                                   <button class="btn default cal" type="button">
                                       <i class="fa fa-calendar"></i>
                                   </button>
                               </span>
                           </div>       
                           
										
					</div>

					<div class="price-condition">
						<label class="price-condition-label">时间间隔:</label> <input
							type="checkbox" id="weekday" name="weekday" value="--" /> <label
							class="label-weekday" for="weekday">天天发团</label> <input
							type="checkbox" id="weekday0" name="weekday" value="0" /> <label
							class="label-weekday" for="weekday0">星期日</label> <input
							type="checkbox" id="weekday1" name="weekday" value="1" /> <label
							class="label-weekday" for="weekday1">星期一</label> <input
							type="checkbox" id="weekday2" name="weekday" value="2" /> <label
							class="label-weekday" for="weekday2">星期二</label> <input
							type="checkbox" id="weekday3" name="weekday" value="3" /> <label
							class="label-weekday" for="weekday3">星期三</label> <input
							type="checkbox" id="weekday4" name="weekday" value="4" /> <label
							class="label-weekday" for="weekday4">星期四</label> <input
							type="checkbox" id="weekday5" name="weekday" value="5" /> <label
							class="label-weekday" for="weekday5">星期五</label> <input
							type="checkbox" id="weekday6" name="weekday" value="6" /> <label
							class="label-weekday" for="weekday6">星期六</label>
					</div>

					<div class="price-condition price-condition-primary">
						<a class="btn btn-primary btn-sm btn-success" href="javascript:;" id="btn-auto-create-price">生成价格</a> 
						<a class="btn btn-primary btn-sm btn-warning" href="javascript:void(0);" data-url="/travelAgent/routeDatePrice/deleteOrderType"
							id="btn-clear">清除价格</a> 
						<a class="btn btn-primary btn-sm btn-success" href="${ctx}/guideAdmin/line/save" id="btn-save">保存价格</a>
					</div>

				</div>
			</div>

			<!-- 价格日历 -->

			<input type="hidden" name="routeid" value="${guideLine.lineNo}" />
			<div class="price-condition red">温馨提示:生成价格、清除价格、保存价格都是根据条件设置范围来操作数据的变化的;单击日历单元格可进行编辑.</div>
			
			<div id="priceCalendar" style="width: 930px;"></div>



		</div>
	</div>
<br/>
<script type="text/javascript" src="${ctx}/static/js/json2.js"></script>
	<script type="text/javascript">
/* 	var RouteDatePrice = function(id,routeid,dayNo,minPrice,cprice,eprice){
		this.id = id;
		this.routeId = routeid;
		this.dayNo = dayNo;
		this.adultPrice = minPrice;
		this.childPrice = cprice;
		this.date = eprice;
	}; */
	
	
	//价格编辑
	var $mPriceEdit ={
			getBasePrice:function(){
				var $weekDays = [];
				$("input[type='checkbox'][name='weekday']:checked").each(function(i,obj){
					$weekDays.push($(obj).val());
				});
				return {
					adultPrice:$.isNumeric($("#adultPrice").val()) ? $("#adultPrice").val() : 0,
					childPrice:$.isNumeric($("#childPrice").val()) ? $("#childPrice").val() : 0,
					mlxPrice:$.isNumeric($("#mlxPrice").val()) ? $("#mlxPrice").val() : 0,
					beginTime:$("input[type='text'][name='beginTime']").val(),
					endTime:$("input[type='text'][name='endTime']").val(),
					weekDays:$weekDays,
					routeId:$("input[type='hidden'][name='routeid']").val()
				};
			},	
			//检查日期的有效性
			checkDateValid:function(){
				var $bas = this.getBasePrice();
				if(!$bas.beginTime || !$bas.endTime){
					comm.infoMsg("请设定好开始时间和结束时间",null,150);
					return false;
				};
				var $beginDate = Date.parse($bas.beginTime.replace(/-/g,"/"));
				var $endDate = Date.parse($bas.endTime.replace(/-/g,"/"));
				if($beginDate > $endDate){
					comm.infoMsg("结束时间不能小于开始时间",null,150);
					return false;
				}
				return true;
			},
			checkDateRange:function(dateStr,beginDateStr,endDateStr){
				if(!beginDateStr || !endDateStr)return true;
				var $date = Date.parse(dateStr.replace(/-/g,"/"));
				var $beginDate = Date.parse(beginDateStr.replace(/-/g,"/"));
				var $endDate = Date.parse(endDateStr.replace(/-/g,"/"));
				if($date >= $beginDate && $date <= $endDate){
					return true;
				}
				return false;
			},
			checkWeekDays:function(aryWeekDays,currWeek){
				if(aryWeekDays.length <= 0)return true;
				var $return = false;
				$.each(aryWeekDays,function(i,value){
					if(value === "--"){
						$return = true;
						return true;
					}
					if(value === currWeek){
						$return = true;
						return true;
					}
				});
				return $return;
			},
			getRoutePriceData:function(){
				var $routePrices = [];
				var $BasePrices = this.getBasePrice();
				$("#priceCalendar td.td").each(function(i,obj){
					var adultPrice = $(obj).attr("data-cprice");
					var childPrice = $(obj).attr("data-eprice");
					var id = $(obj).attr("data-id");
					var routeid = $BasePrices.routeId;
					var routeOrderType = $(obj).attr("data-routeordertype");
					var mlxPrice = $(obj).attr("minprice");
					var date = $(obj).attr("data-full-date");
					var $b = mlxPrice.length > 0 ? parseInt(mlxPrice, 0) : 0;
					if(parseInt(mlxPrice, 0) > 0 
							||($b == 0 && parseInt(id,0) > 0)){
						var lineDatePrice = {};
						lineDatePrice.id = id;
						lineDatePrice.lineNo = routeid;
						lineDatePrice.adultPrice = adultPrice;
						lineDatePrice.childPrice = childPrice;
						lineDatePrice.mlxPrice = mlxPrice;
						lineDatePrice.lineDate = date;
						
						$routePrices.push(lineDatePrice);	
					}
				});
				return $routePrices;
			},
			
			
			
			
	};

	
	$(function(){
		var result = ${lineDataPrices};
/* 		for (var i = 0; i < 20; i++) {
			result[i] = new RouteDatePrice(i, i,'2016-3-'+(i+1), 330, 280, 250);
		} */
		
		var $mTemp = '<div class="price-edit" style="width: 50px;color:black;">'
	        +'<input type="text" name="minprice" value="" placeholder="美丽价" title="美丽价">'
	        +'<input type="text" name="data-eprice" placeholder="儿童价" title="儿童价" value="">'
	        +'<input type="text" name="data-cprice" placeholder="门市价" title="门市价" value=""></div>';
		
		 $("#priceCalendar").priceCalendar({
				showMonthNum: 5, //日历显示月份
				defaultText:"点击编辑",
				useEditModel:true,//是否使用编辑模式
			    json: result, //传递过来的json
			    startTime:new Date(),
			    tdAttrs: function(jsonObj){
			    	if(!jsonObj){
			    		return "";
			    	}
			    	if($("#adultPrice").val().length <= 0){
			    		$("#adultPrice").val(jsonObj.cPrice);
			    	}
			    	if($("#mlxPrice").val().length <= 0){
			    		$("#mlxPrice").val(jsonObj.minPrice);
			    	}
			    	if($("#childPrice").val().length <= 0){
			    		$("#childPrice").val(jsonObj.ePrice);
			    	}
			    	var $value = $("#select-routeOrderType option:selected").val();
			    	var $attr = " @attrName = '@attrValue' ";
			    	var $attrString = "";
			    	//console.log(jsonObj);
			    	$attrString += $attr.replace("@attrName", "data-cprice").replace("@attrValue", jsonObj.cPrice || "");
			    	$attrString += $attr.replace("@attrName", "data-eprice").replace("@attrValue", jsonObj.ePrice || "");
			    	$attrString += $attr.replace("@attrName", "data-id").replace("@attrValue", jsonObj.id || "");
			    	$attrString += $attr.replace("@attrName", "data-routeId").replace("@attrValue", jsonObj.routeId || "");
			    	$attrString += $attr.replace("@attrName", "data-routeOrderType").replace("@attrValue", $value);
			    	return $attrString;
			    },
			    tdClick: function(obj,date,price,cprice,eprice,currTdObj) {
			    	if($("div.price-edit",currTdObj).length <= 0){
			    		var temp = $($mTemp).appendTo(currTdObj);
			    		var dataType = $(currTdObj).attr("data-routeordertype");
			    		if(!dataType){
			    			var routeOrderType = $("#select-routeOrderType option:selected").val();
			    			$(currTdObj).attr("data-routeordertype",routeOrderType);
			    		}
			    		var $eprice = $(currTdObj).attr("data-eprice");
			    		var $cprice = $(currTdObj).attr("data-cprice");
			    		$("input[type='text'][name='minprice']",currTdObj).val(price);
			    		$("input[type='text'][name='data-eprice']",currTdObj).val($eprice);
			    		$("input[type='text'][name='data-cprice']",currTdObj).val($cprice);
			    		$("input[type='text']",temp).on("blur",function(){
			    			var $Atr = $(this).attr("name");
			    			if($.trim($(this).val()).length > 0 && parseInt($(this).val()) >= 0){
			    				$(this).parent().parent().attr($Atr,$(this).val());
			        		}
			        		else if($.trim($(this).val()).length > 0){
			        			comm.infoMsg("请输入正确的日期价格",null,150);
			        			$(this).val("");
			        		}else{
			        			$(this).parent().parent().attr($Atr,$(this).val());
			        		}
			    			
			    		});
			    		$("span.price",currTdObj).hide();
			    	}
			    },
			    prevMonthButtonClick:function(){},
			    nextMonthButtonClick:function(){}
			});
		 
		 
		 
			//保存价格
			$("#btn-save").bind("click",function(e){
				//在保存前点击再次生成日历表数据
				//$("#btn-auto-create-price").click();
				
				e.preventDefault();
				var $BasePrices = $mPriceEdit.getBasePrice();
				
				//门市价 美丽价不能为空
				if($.trim($BasePrices.adultPrice).length <= 0 
						|| parseInt($BasePrices.adultPrice,0) <= 0
						|| $.trim($BasePrices.mlxPrice).length <= 0 
						|| parseInt($BasePrices.mlxPrice,0) <= 0){
					comm.infoMsg("请输入门市价和美丽价",null,150);
					return;
				}
				//门市价必须 > 美丽价
				if($.trim($BasePrices.adultPrice).length <= 0 
						|| $.trim($BasePrices.mlxPrice).length <= 0 
						|| parseInt($BasePrices.mlxPrice,0) > parseInt($BasePrices.adultPrice,0)){
					comm.infoMsg("门市价不能小于美丽价",null,150);
					return;
				}
				if($.trim($BasePrices.routeId).length <= 0
						|| parseInt($BasePrices.routeId,0) <= 0){
					comm.infoMsg("抱歉没有找到线路的编号,请重新进入",null,150);
					return;
				}
				
				//日期选择检查
				$mPriceEdit.checkDateValid();
				//json生成
				var $data = $mPriceEdit.getRoutePriceData();
				$data = JSON.stringify($data);
				
				if($data.length <= 0){
					comm.infoMsg("请输入日期价格数据",null,150);
					return;
				}
				var $url = $(this).attr("href");
				comm.confirm("提示","确定现在提交吗?",function(){
					$.post($url,{"params":$data},function(result){ 
						comm.infoMsg(result.msg);
						if(result.code === 200){
							setTimeout(function(){
								window.location.href = "${ctx}/guideAdmin/line/list";
							},1000);
						}
						
						return;
					});
				});
			});
			
			//生成日历表格中的价格
			$("#btn-auto-create-price").on("click",function(e){
				var $BasePrices = $mPriceEdit.getBasePrice();
				
				if($.trim($BasePrices.adultPrice).length <= 0 
						|| parseInt($BasePrices.adultPrice,0) <= 0
						|| $.trim($BasePrices.mlxPrice).length <= 0 
						|| parseInt($BasePrices.mlxPrice,0) <= 0){
					comm.infoMsg("请输入门市价和美丽价");
					return;
				}
				//门市价必须 > 美丽价
				if($.trim($BasePrices.adultPrice).length <= 0 
						|| $.trim($BasePrices.mlxPrice).length <= 0 
						|| parseInt($BasePrices.mlxPrice,0) > parseInt($BasePrices.adultPrice,0)){
					comm.infoMsg("门市价不能小于美丽价");
					return;
				}
				if($.trim($BasePrices.routeId).length <= 0
						|| parseInt($BasePrices.routeId,0) <= 0){
					comm.infoMsg("抱歉没有找到线路的编号,请重新进入",null,150);
					return;
				}
				if(!$mPriceEdit.checkDateValid())return;
				// data-cprice="0" data-eprice="0" data-id="0" 
				// data-routeid="0" data-routeordertype="1" minprice="0" 
				// data-full-date="2014-05-15" data-date="15" data-week="4"
				$("#tableCalendar td.td").each(function(i,obj){
					var $week = $(obj).attr("data-week");
					var $date = $(obj).attr("data-full-date");
					var $value = $("#select-routeOrderType option:selected").val();
					if(!$mPriceEdit.checkDateRange($date, $BasePrices.beginTime, $BasePrices.endTime)){
						return;
					}
					if(!$mPriceEdit.checkWeekDays($BasePrices.weekDays, $week)){
						return;
					}
					$(obj).attr("data-cprice",$BasePrices.adultPrice);
					$(obj).attr("data-eprice",$BasePrices.childPrice);
					$(obj).attr("minprice",$BasePrices.mlxPrice);
					$(obj).attr("data-routeordertype",$value);
					$("span.price",$(obj)).html("<dfn>¥</dfn>" + $BasePrices.mlxPrice);
					$("input[type='text'][name='minprice']",$(obj)).val($BasePrices.mlxPrice);
					$("input[type='text'][name='eprice']",$(obj)).val($BasePrices.childPrice);
				});
			});
			
			$("#btn-clear").on("click",function(e){
				var $orderType = $('#select-routeOrderType').val();
				var $supplierId = $("[name='supplierId']").val();
				var $routeId = $("[name='routeid']").val();
				var $href = $(this).attr("data-url");
				comm.confirm("提示","您确定要清除该类型价格吗？",function(){
					//日期选择检查
					$mPriceEdit.checkDateValid();
					//清除日历中的价格
					var $BasePrices = $mPriceEdit.getBasePrice();
					$("#tableCalendar td.td").each(function(i,obj){
						var $week = $(obj).attr("data-week");
						var $date = $(obj).attr("data-full-date");
						if(!$mPriceEdit.checkDateRange($date, $BasePrices.beginTime, $BasePrices.endTime)){
							return;
						}
						if(!$mPriceEdit.checkWeekDays($BasePrices.weekDays, $week)){
							return;
						}
						$(obj).attr("data-cprice","");
						$(obj).attr("data-eprice","");
						$(obj).attr("minprice","");
						$("span.price",$(obj)).html("<dfn>¥</dfn>--");
						$("input[type='text'][name='minprice']",$(obj)).val("");
						$("input[type='text'][name='eprice']",$(obj)).val("");						
					});
				});
				
				//日期选择检查
				$mPriceEdit.checkDateValid();
				//删除数据库中的价格数据
				$.post('${ctx}/guideAdmin/line/delLinePrcie/' + $routeId,{"beginTime":$("input[name=beginTime]").val(),
					"endTime":$("input[name=endTime]").val()},function(result){
						
					comm.infoMsg(result.msg);
					return;
				});				
			});		 
		 
		 
		 
	});
	
	</script>


</body>
</html>