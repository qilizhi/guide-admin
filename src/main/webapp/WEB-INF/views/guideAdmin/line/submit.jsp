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
<style type="text/css">
/* css3 控制tab2进度条 */
@-webkit-keyframes scoller{
		0%{
			width:75%
		}
		100%{
			width:100%	
		}
	}
@keyframes scoller{
		0%{
			width:75%
		}
		100%{
			width:100%	
		}
	}	
	
	.scoller{
		-webkit-animation: scoller 1.2s ease forwards;
		animation: scoller 1.2s ease forwards;
		
	}
	.tab-pane .tishi{ width: 400px;height: 130px;border: 1px solid #ccc;margin: auto; text-align: center; line-height: 24px; padding: 1rem;} 
</style>
<title>发布</title>



</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">信息确认</span>
					</div>

				</div>
				<div class="row">
				 <div class="form-wizard">
                    <div class="form-body">
						<ul class="nav nav-pills nav-justified steps">
	                         <li class="active">
	                             <a href="#tab1" data-toggle="tab" class="step" aria-expanded="true">
	                                 <span class="number"> 1 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 线路 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab2" data-toggle="tab" class="step">
	                                 <span class="number" style="background-color: #36c6d3; color: #fff;"> 2 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 价格 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab3" data-toggle="tab" class="step active">
	                                 <span class="number" style="background-color: #36c6d3; color: #fff;"> 3 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 行程 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab4" data-toggle="tab" class="step">
	                                 <span class="number" style="background-color: #36c6d3; color: #fff;"> 4 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 发布 </span>
	                             </a>
	                         </li>
	                     </ul>
                     <div id="bar" class="progress progress-striped" role="progressbar">
                         <div class="progress-bar progress-bar-success scoller" style="width: 75%;"> </div>
                     </div>
                     <div class="tab-content">
                       <div class="alert alert-danger display-none">
                           <button class="close" data-dismiss="alert"></button> You have some form errors. Please check below. </div>
                       <div class="alert alert-success display-none">
                           <button class="close" data-dismiss="alert"></button> Your form validation is successful! </div>
                       
                       <div class="tab-pane active" id="tab3"></div>
				<div class="portlet-body">
					<!-- BEGIN FORM -->
					<form action="${ctx }/guideAdmin/trip/preView/${line.lineNo}" id=form_sample_3
						class="form-horizontal" method="post"
						enctype="multipart/form-data">
						<div class="form-body">
							<div class="alert alert-danger display-hide">
								<button class="close" data-close="alert"></button>
								You have some form errors. Please check below.
							</div>
							<div class="alert alert-success display-hide">
								<button class="close" data-close="alert"></button>
								Your form validation is successful!
							</div>
							
							
							<div class="tab-pane" id="tab4">
								<!-- <div class="tishi">
										提示<br>
										您的线路已经编辑完成,现在申请上线销售?<br>
										点击 预览 可查看<br>
								</div>  -->
								
                             <h3 class="block">请确认您的信息</h3>
                             <h4 class="form-section">线路</h4>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路编号:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static">${line.lineNo}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路标题:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static">${line.title}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路类型:</label>
                                 <div class="col-md-4">
                                    <%--  <p class="form-control-static" >${line.lineType}</p> --%>
                                     <p class="form-control-static" >${ELineType[line.lineType]}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路价格:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.price}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路天数:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.totalDay}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">线路简介:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.description}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">排序号:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.sort}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">满员人数:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.num}</p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">体验说明:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.remark}</p>
                                 </div>
                             </div>
                             <%-- <div class="form-group">
                                 <label class="control-label col-md-3">内容:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" >${line.content}</p>
                                 </div>
                             </div> --%>
                             <div class="form-group">
									<label class="control-label col-md-3">内容:</label>
									<div class="col-md-6">
	
										<div class="portlet box yellow">
											<div class="portlet-title">
												<div class="caption font-red-intense">
													<i class="icon-speech font-white-intense"></i> <span
														class="caption-subject bold uppercase"></span> <span
														class="caption-helper"></span>
												</div>
												<div class="tools">
													<a href="" class="collapse" title="展开/关闭"> </a>
												</div>
											</div>
											<div class="portlet-body">
												<div id="context2" data-toggle="context"
													data-target="#context-menu">${line.content}</div>
	
											</div>
										</div>
										<!--     <p class="form-control-static" ></p> -->
									</div>
								</div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">背景图:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" ><img id="image" alt=""
										src="${line.imgUrl }" width="200px;" height="auto;"></p>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label col-md-3">小图:</label>
                                 <div class="col-md-4">
                                     <p class="form-control-static" ><img id="image" alt=""
										src="${line.smallImgUrl }" width="200px;" height="auto;"></p>
                                 </div>
                             </div>
                             
                             <h4 class="form-section">价格</h4>
                             
                             <div class="form-group">
                                 <table class="table table-striped table-bordered">
									<thead>
										<tr>
											<th scope="col">线路编号</th>
											<th scope="col">成人价</th>
											<th scope="col">儿童价</th>
											<th scope="col">房差</th>
											<th scope="col">保险价</th>
											<th scope="col">签证费</th>
											<th scope="col">满员人数</th>
											<th scope="col">出行日期</th>
											
										</tr>
									</thead>
									<tbody>
									
										<c:forEach items="${lsPrices}" var="item">
											<tr>
												<td>${item.goodsNo}</td>
												<td>${item.adultPrice}</td>
												<td>${item.childPrice}</td>
												<td>${item.roomDiffPrice}</td>
												<td>${item.safePrice}</td>
												<td>${item.visaPrice}</td>
												<td>${item.num}</td>
												<td><fmt:formatDate value="${item.tuanDate}" pattern="yyyy-MM-dd"/></td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
                             </div>
                             
                             <h4 class="form-section">行程</h4>
                           
								
							 <div class="form-group">
                                 <table class="table table-striped table-bordered">
									<thead>
										<tr>
											<th scope="col">线路编号</th>
											<th scope="col">第几天</th>
											<th scope="col">地点</th>
											<th scope="col">交通</th>
											<th scope="col">住宿</th>
											<th scope="col">主要行程</th>
											
										</tr>
									</thead>
									<tbody>
									
										<c:forEach items="${trips}" var="item">
											<tr>
												<td>${item.lineNo}</td>
												<td>${item.day}</td>
												<td>${item.address}</td>
												<td>${item.traffic}</td>
												<td>${item.hotel}</td>
												<td>${item.tripDetail}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
                             </div>	
							</div>
							
							
						</div> 



						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
											<a class="btn btn-primary btn-sm btn-success"
											href="${ctx}/guideAdmin/line/backToTrip/${lineNo}?startDate=${startDate}&endDate=${endDate}" id="btn-save">上一步</a>
											
										<button type="submit" class="btn btn-primary btn-sm btn-success">发布</button>
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

	
	



</body>
</html>