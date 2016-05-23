<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" >
<style>
#yl_main .BODY{
	background-color:inherit;
    width: 250px;
    margin-left: 35px;
    margin-top: 50px;
}
</style>
</head>
<body>
<div class="row">
				<div class="col-md-4">
					<div class="bg-mobile">
		          <iframe  id="yl_main" src="http://weixin.mlxing.com/" height="497px" width="283px" style="border: none; position: absolute; top:30px;left:19px;"></iframe>
		              </div>
				</div>
				<div class="col-md-8">
					<div class="note note-success">
						<p>提示：栏目的设置可以参考如下菜单地址</p>
					</div>
					<div class="list-group">
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/')">
								<i class="fa fa-home"></i>
								首页
								http://weixin.mlxing.com/								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/article/lists')">
								<i class="fa fa-book"></i>
								推荐阅读
								http://weixin.mlxing.com/article/lists								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/shop/gm')">
								<i class="fa fa-shopping-cart"></i>
								购物车
								http://weixin.mlxing.com/shop/gm								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/shop/bill')">
								<i class="fa fa-share-alt-square"></i>
								分销中心 
								http://weixin.mlxing.com/shop/bill								</a>
								
							</div>
								<!-- <div class="note note-success" onclick="doyl('http://weixin.mlxing.com/plug/mall/a')">
						            <p>全部商品，权限地址：http://weixin.mlxing.com/plug/mall/a</p>
					             </div>
					             <div class="note note-info" onclick="doyl('http://weixin.mlxing.com/plug/mall/a/1')">
						            <p>普通会员购买，权限地址：http://weixin.mlxing.com/plug/mall/a/1</p>
					             </div>
					              <div class="note note-danger" onclick="doyl('http://weixin.mlxing.com/plug/mall/a/2')">
						            <p>仅微店店长购买，权限地址：http://weixin.mlxing.com/plug/mall/a/2</p>
					             </div>
					              <div class="note">
						            <p>其他商城风格同理</p>
					             </div> -->
								
						<!-- 	<div class="list-group">
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/plug/mall/a')">
								<i class="fa fa-ra"></i>
								商城风格一
								http://weixin.mlxing.com/plug/mall/a								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/plug/mall/b')">
								<i class="fa fa-life-ring"></i>
								商城风格二
								http://weixin.mlxing.com/plug/mall/b								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/plug/mall/c')">
								<i class="fa fa-bank"></i>
								商城风格三
								http://weixin.mlxing.com/plug/mall/c								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/plug/mall/d')">
								<i class="fa fa-fire"></i>
								商城风格四
								http://weixin.mlxing.com/plug/mall/d								</a>
								<a href="javascript:;" class="list-group-item" onclick="doyl('http://weixin.mlxing.com/plug/mall/e')">
								<i class="fa fa-circle-thin"></i>
								商城风格五
								http://weixin.mlxing.com/plug/mall/e								</a>
							</div> -->
							
						<!-- <div class="portlet light">
						<div class="portlet-title tabbable-line">
							<div class="caption">
								<i class="icon-umbrella font-red-sunglo"></i>
								<span class="caption-subject font-red-sunglo bold uppercase">首页设置</span>
							</div>
						</div>
						<div class="portlet-body">
							<form method="post" id="index_up" name="index_up" novalidate="novalidate">
								<div class="form-group form-md-line-input">
									<input type="text" class="form-control" name="tj_sp" value="1">
									<label for="form_control_1">主推商品ID或主推内容的超链接</label>
								</div>
								<button data-dismiss="modal" type="button" class="btn default">
					                 <i class="fa fa-refresh"></i>
					               重置
				                </button>
								<button data-dismiss="modal" type="submit" class="btn default blue">
					                 <i class="fa fa-send"></i>
					               设置
				                </button>
							</form>
						</div>
					</div> -->
				</div>
			</div>
			
<script type="text/javascript">
function doyl(url){
	$("#yl_main").attr("src",url);
}
</script>			
			
</body>
</html>