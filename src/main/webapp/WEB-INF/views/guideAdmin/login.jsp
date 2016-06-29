<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>登录</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${ctx}/static/css/custom.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <%-- <link href="${ctx}/static/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" /> --%>
        <link href="${ctx}/static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       <%--  <link href="${ctx}/static/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" /> --%>
        <link href="${ctx}/static/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${ctx}/static/assets/global/css/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${ctx}/static/assets/global/css/plugins-md.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" />
        <script type="text/javascript">
window.g_basePath = "${ctx}";
</script>
         </head>
    <!-- END HEAD -->

    <body class=" login">
        <div class="menu-toggler sidebar-toggler"></div>
        <!-- END SIDEBAR TOGGLER BUTTON -->
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="index.html">
                <img src="${ctx}/static/img/logo.png" alt="" width="240" height="80" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" action="${ctx}/guideLogin" data-default="${returnUrl}" method="post">
                <h3 class="form-title font-green">登录</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> 请输入您的账号和密码. </span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">账号</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="账号" name="username" value="13533337663"/> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password" value="123456" /> </div>
                <div class="form-actions">
                    <button type="submit" class="btn green uppercase">登录</button>
                    <label class="rememberme check">
                        <input type="checkbox" name="remember" value="1" />记住我 </label>
                    <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
                </div>
                <div class="login-options">
                   <!--  <h4>其他方式</h4>
                    <ul class="social-icons">
                        <li>
                            <a class="social-icon-color facebook" data-original-title="facebook" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color twitter" data-original-title="Twitter" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color googleplus" data-original-title="Goole Plus" href="javascript:;"></a>
                        </li>
                        <li>
                            <a class="social-icon-color linkedin" data-original-title="Linkedin" href="javascript:;"></a>
                        </li>
                    </ul> -->
                </div>
                <div class="create-account">
                 <!--    <p>
                        <a href="javascript:;" id="register-btn" class="uppercase">申请入驻</a>
                    </p> -->
                </div>
            </form>
            <!-- END LOGIN FORM -->
            <!-- BEGIN FORGOT PASSWORD FORM -->
            <form class="forget-form" action="javascript:;" method="post">
                <h3 class="font-green">忘记密码 ?</h3>
                <p> 请输入您的邮箱地址或者手机来找回密码. </p>
                <div class="form-group">
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" /> </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn btn-default">返回</button>
                    <button type="submit" class="btn btn-success uppercase pull-right">提交</button>
                </div>
            </form>
            <!-- END FORGOT PASSWORD FORM -->
            <!-- BEGIN REGISTRATION FORM -->
            <form class="register-form" action="javascript:;" method="post">
                <h3 class="font-green">扫码入驻</h3>
                <div class="form-group">
                <div id="qrcode" style="text-align: center;"></div>
                </div>
                <div class="form-actions" style="text-align: center;">
                    <button type="button" id="register-back-btn" class="btn btn-default">返回</button>
                </div>
            </form>
            <!-- END REGISTRATION FORM -->
        </div>
        <div class="copyright"> 2016 © 广州蕲蕲信息科技有限公司. <a href="${ctx}" title="广州蕲蕲信息科技有限公司"
		target="_blank">美丽玩家平台管理系统</a> </div>
        <!--[if lt IE 9]>
<script src="${ctx}/static/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${ctx}/static/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
       <%--  <script src="${ctx}/static/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script> --%>
        <%-- <script src="${ctx}/static/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script> --%>
        <script src="${ctx}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${ctx}/static/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script type="text/javascript" src="http://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
        <script type="text/javascript">
        $(function(){
        	$('#qrcode').qrcode({width: 256,height: 256,text: "http://weixin.mlxing.com/mlx/dy"});
        });
        </script>
        <script src="${ctx}/static/js/login.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>

</html>