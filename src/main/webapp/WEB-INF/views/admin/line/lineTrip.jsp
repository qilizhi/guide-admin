<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
/* css3 控制tab2进度条 */
@
-webkit-keyframes scoller { 0%{
	width: 50%
}

100%{
width






:






75%
}
}
@
keyframes scoller { 0%{
	width: 50%
}

100%{
width






:






75%
}
}
.scoller {
	-webkit-animation: scoller 1.2s ease forwards;
	animation: scoller 1.2s ease forwards;
}
</style>

<title>创建行程</title>



</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">行程描述</span>
					</div>

				</div>
				<div class="row">
					<div class="form-wizard">
						<div class="form-body">
							<ul class="nav nav-pills nav-justified steps">
								<li class="active"><a href="#tab1" data-toggle="tab"
									class="step" aria-expanded="true"> <span class="number">
											1 </span> <span class="desc"> <i class="fa fa-check"></i> 线路
									</span>
								</a></li>
								<li><a href="#tab2" data-toggle="tab" class="step"> <span
										class="number" style="background-color: #36c6d3; color: #fff;">
											2 </span> <span class="desc"> <i class="fa fa-check"></i> 价格
									</span>
								</a></li>
								<li><a href="#tab3" data-toggle="tab" class="step active">
										<span class="number"
										style="background-color: #36c6d3; color: #fff;"> 3 </span> <span
										class="desc"> <i class="fa fa-check"></i> 行程
									</span>
								</a></li>
								<li><a href="#tab4" data-toggle="tab" class="step"> <span
										class="number"> 4 </span> <span class="desc"> <i
											class="fa fa-check"></i> 发布
									</span>
								</a></li>
							</ul>
							<div id="bar" class="progress progress-striped"
								role="progressbar">
								<div class="progress-bar progress-bar-success scoller"
									style="width: 50%;"></div>
							</div>
							<div class="tab-content">
								<div class="alert alert-danger display-none">
									<button class="close" data-dismiss="alert"></button>
									You have some form errors. Please check below.
								</div>
								<div class="alert alert-success display-none">
									<button class="close" data-dismiss="alert"></button>
									Your form validation is successful!
								</div>

								<div class="tab-pane active" id="tab3">
									<h3 class="block">请填写行程详情</h3>
									<!-- 		<a class="btn blue btn-outline" name="addBtn"
										onClick="addRow();">添加</a> -->
								</div>
								<div class="portlet-body">
									<!-- BEGIN FORM -->
									<form
										action="${ctx }/admin/trip/save?startDate=${startDate}&endDate=${endDate}"
										onsubmit="return validForm();" id=form_sample_3
										class="form-horizontal" method="post"
										enctype="multipart/form-data">
										<input type="hidden" name="id" value="${guideLineTrip.id }" />
										<div class="form-body">
											<div class="alert alert-danger display-hide">
												<button class="close" data-close="alert"></button>
												You have some form errors. Please check below.
											</div>
											<div class="alert alert-success display-hide">
												<button class="close" data-close="alert"></button>
												Your form validation is successful!
											</div>

											<input type="hidden" name="lineNo" value="${lineNo}" />
											<div class="tab-pane" id="tab3">

												<table id="test" class="table table-striped table-bordered">
													<thead>
														<tr>
															<th class="text-center">线路编号</th>
															<th class="text-center">第几天</th>
															<th class="text-center">地点</th>
															<th class="text-center">交通</th>
															<th class="text-center">住宿</th>
															<th class="text-center">主要行程</th>
															<!-- 	<th class="text-center">操作</th>
 -->
														</tr>
													</thead>
													<tbody>
											
														<c:choose>

															<c:when test="${fn:length(list)<=0}">
															<c:forEach var="i"  begin="1" end="${guideLine.totalDay}">
																	<tr style="border-bottom: 1px solid #ccc;">
																		<td>${guideLine.lineNo}<input type="hidden"
																			name="guideLineTrips[${i-1}].id"
																			value="" /> <input type="hidden"
																			name="guideLineTrips[${i-1}].lineNo"
																			value="${guideLine.lineNo}" />
																		</td>
																		<td align="center" class="js-num" width="80"><input
																			type="text" class="js-day"
																			name="guideLineTrips[${i-1}].day" id="day"
																			value='${i}' readonly="readonly"></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${i-1}].address"
																				id="address">  </textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${i-1}].traffic"
																				id="traffic"> </textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${i-1}].hotel" id="hotel"></textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${i-1}].tripDetail"
																				id="tripDetail"></textarea></td>
																		<%-- <td><a class="btn blue btn-outline" name="addBtn"
																	onClick="addRow();">添加</a> <a
																	class="btn blue btn-outline" name="addBtn"
																	onClick="delRow(this,${item.id});">删除</a></td> --%>
																	</tr>
																</c:forEach>
															
															
															</c:when>
															<c:otherwise>
																<c:forEach var="item" items="${list}" varStatus="st">
																	<tr style="border-bottom: 1px solid #ccc;">
																		<td>${item.lineNo}<input type="hidden"
																			name="guideLineTrips[${st.index}].id"
																			value="${item.id}" /> <input type="hidden"
																			name="guideLineTrips[${st.index}].lineNo"
																			value="${lineNo}" />
																		</td>
																		<td align="center" class="js-num" width="80"><input
																			type="text" class="js-day"
																			name="guideLineTrips[${st.index}].day" id="day"
																			value='${item.day}' readonly="readonly"></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${st.index}].address"
																				id="address"> ${item.address}  </textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${st.index}].traffic"
																				id="traffic"> ${item.traffic} </textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${st.index}].hotel" id="hotel"> ${item.hotel}</textarea></td>
																		<td><textarea type="text"
																				name="guideLineTrips[${st.index}].tripDetail"
																				id="tripDetail">${item.tripDetail}</textarea></td>
																		<%-- <td><a class="btn blue btn-outline" name="addBtn"
																	onClick="addRow();">添加</a> <a
																	class="btn blue btn-outline" name="addBtn"
																	onClick="delRow(this,${item.id});">删除</a></td> --%>
																	</tr>
																</c:forEach>

															</c:otherwise>
														</c:choose>
													</tbody>
												</table>

											</div>
										</div>

										<div class="form-actions">
											<div class="row">
												<div class="col-md-offset-3 col-md-9">
													<a class="btn btn-primary btn-sm btn-success"
														href="${ctx}/admin/guideLine/backToPrice/${lineNo}?startDate=${startDate}&endDate=${endDate}"
														id="btn-save">上一步</a>

													<button type="submit"
														class="btn btn-primary btn-sm btn-success">下一步</button>
												</div>
											</div>
										</div>
									</form>
									<!-- END FORM-->

								</div>
							</div>
						</div>
					</div>



				</div>
				<!-- END VALIDATION STATES-->
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function validForm() {
			var is_valid = true;

			$("textarea").each(function(i, item) {
				$(this).next("span").remove();
				if ($.trim(this.value) == "") {
					if ($(this).next("span")[0] == null) {
						$(this).after("<span class='text-danger'>不能为空</span>");
					}
					is_valid = false;

				}
				;

			});

			return is_valid;
		}
	</script>



</body>
</html>