<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
<title>美丽行</title>
</head>
<body >
	<div class="row" ng-app="app" ng-controller="ctrl">
		<div class="col-md-12">
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">微信菜单设置<small style="font-weight: normal;margin-left:30px;">1.自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
2、一级菜单最多4个汉字，二级菜单最多7个汉字</small></span>
					</div>
					
				</div>
				<div >
				
				<!--手机展示  -->
				<div class="col-md-5 " >
				<span style="margin:10px 10px 20px 60px;display:inline-block;">公众号:&nbsp;&nbsp;&nbsp;
				<select  ng-model="wxPubId"  ng-change="getWeiButtonDate()" style="width: 220px;height: 34px;">
			     <c:forEach var="item" items="${list}" >
				 <option value="${item.id }">${item.name }</option>
				 </c:forEach> 
				</select>
				<img src="${ctx}/static/img/preload_img.gif" ng-if="preload">
				<span  class="text-danger" ng-bind="msg" ng-if="msg"></span>
				</span>
				 <div class="bg-mobile">
				 <iframe frameborder="0" width="282px" height="464px" src="http://weixin.mlxing.com" name="mobileFrame" style="margin:30px 0px 0px 20px;"></iframe>
				 <div class="bg-mobile-sub">
				   <div class="btn-group dropup  pull-left " >
                        <button class="btn  dropdown-toggle mobile-btn" type="button" data-toggle="dropdown" aria-expanded="true" ng-bind="menu1==null?'菜单一':menu1"> 菜单一
                        </button>
                        <ul class="dropdown-menu pull-right moblie-sub-menu" >
                            <li ng-if="menu1_s5">
                                <a href="{{menu1_u5}}"  target="mobileFrame"  ng-bind="menu1_s5"> 子菜单五 </a>
                            </li>
                            <li  ng-if="menu1_s4">
                                <a href="{{menu1_u4}}"  target="mobileFrame"  ng-bind="menu1_s4">  子菜单四</a>
                            </li>
                            <li ng-if="menu1_s3">
                                <a href="{{menu1_u3}}"   target="mobileFrame"  ng-bind="menu1_s3">  子菜单三 </a>
                            </li>
                            <li ng-if="menu1_s2">
                                <a href="{{menu1_u2}}" target="mobileFrame"  ng-bind="menu1_s2">  子菜单二 </a>
                            </li>
                             <li ng-if="menu1_s1">
                                <a href="{{menu1_u1}}"   target="mobileFrame" ng-bind="menu1_s1"> 子菜单一 </a>
                            </li>
                            </ul>                                      
                      </div>
                       <div class="btn-group dropup  pull-left ">
                        <button class="btn  dropdown-toggle mobile-btn" type="button" data-toggle="dropdown" ng-bind="menu2==null?'菜单二':menu2" > 菜单二
                           
                        </button>
                        <ul class="dropdown-menu pull-right moblie-sub-menu" >
                            <li ng-if="menu2_s5">
                                <a href="{{menu2_u5}}"  target="mobileFrame"  ng-bind="menu2_s5"> 子菜单五 </a>
                            </li>
                            <li  ng-if="menu1_s4">
                                <a href="{{menu2_u4}}" target="mobileFrame"  ng-bind="menu2_s4">  子菜单四</a>
                            </li>
                            <li ng-if="menu1_s3">
                                <a href="{{menu2_u3}}" target="mobileFrame"   ng-bind="menu2_s3">  子菜单三 </a>
                            </li>
                            <li ng-if="menu1_s2">
                                <a href="{{menu2_u2}}" target="mobileFrame"  ng-bind="menu2_s2">  子菜单二 </a>
                            </li>
                             <li ng-if="menu2_s1">
                                <a href="{{menu2_u1}}" target="mobileFrame"  ng-bind="menu2_s1"> 子菜单一 </a>
                            </li>
                            </ul>                                      
                      </div>
                       <div class="btn-group dropup  pull-left ">
                        <button class="btn  dropdown-toggle mobile-btn " type="button" data-toggle="dropdown" ng-bind="menu3==null?'菜单三':menu3"> 菜单三
                           
                        </button>
                        <ul class="dropdown-menu pull-right moblie-sub-menu" >
                           <li ng-if="menu3_s5">
                                <a href="{{menu3_u5}}"  target="mobileFrame"  ng-bind="menu3_s5"> 子菜单五 </a>
                            </li>
                            <li  ng-if="menu3_s4">
                                <a href="{{menu3_u4}}" target="mobileFrame"  ng-bind="menu3_s4">  子菜单四</a>
                            </li>
                            <li ng-if="menu3_s3">
                                <a href="{{menu3_u3}}" target="mobileFrame"   ng-bind="menu3_s3">  子菜单三 </a>
                            </li>
                            <li ng-if="menu3_s2">
                                <a href="{{menu3_u2}}" target="mobileFrame"  ng-bind="menu3_s2">  子菜单二 </a>
                            </li>
                             <li ng-if="menu3_s1">
                                <a href="{{menu3_u1}}" target="mobileFrame"  ng-bind="menu3_s1"> 子菜单一 </a>
                            </li>
                            </ul>                                      
                      </div>
                      
				   </div>
				
			</div>
				
				</div>
	
	<!--表单展示  -->
	<form action="${ctx}/admin/wxMenu/edit" id="inputForm" 
		method="post" class="form-horizontal col-md-7" style="margin-top:20px;"  onsubmit="return formSubmit()"  >
		<input type="hidden" name="id" value={{wxPubId}}>
		<div class="form-group">
             <label class="col-lg-3 control-label" >菜单一：</label>
              <div class="input-group col-lg-5">
                  <input type="text" class="form-control"  name="button[0].name" placeholder="菜单一(必填)" maxlength="4" ng-model="menu1" maxlength="7" required>
                  <a class="input-group-addon" onclick="toggleSubMenu('sub_menu1',this)">
                      <i class="fa  fa-plus    font-red"></i>
                  </a>
              </div>
          </div>
		<ul class="mobile-ul form-body" id="sub_menu1">
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[0].sub_button[0].name"   placeholder="(必填)子菜单一" ng-model="menu1_s1" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[0].sub_button[0].url"   placeholder="(必填)链接地址" ng-model="menu1_u1" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[0].sub_button[1].name"   placeholder="子菜单二" ng-model="menu1_s2" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control"  name="button[0].sub_button[1].url"  placeholder="链接地址" ng-model="menu1_u2" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[0].sub_button[2].name"   placeholder="子菜单三" ng-model="menu1_s3" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[0].sub_button[2].url"  placeholder="链接地址" ng-model="menu1_u3" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[0].sub_button[3].name"   placeholder="子菜单四" ng-model="menu1_s4" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[0].sub_button[3].url"   placeholder="链接地址" ng-model="menu1_u4" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[0].sub_button[4].name"  placeholder="子菜单五" ng-model="menu1_s5" maxlength="7"  />
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[0].sub_button[4].url"  placeholder="链接地址" ng-model="menu1_u5" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		</ul>
		<div class="form-group">
             <label class="col-lg-3 control-label " >菜单二：</label>
              <div class="input-group col-lg-5">
                  <input type="text" class="form-control" name="button[1].name"  placeholder="菜单二(必填)"   ng-model="menu2" maxlength="4" maxlength="7" required/>
                  <a class="input-group-addon" onclick="toggleSubMenu('sub_menu2',this)">
                      <i class="fa fa-plus font-red"></i>
                  </a>
              </div>
          </div>
         <ul class="mobile-ul form-body" id="sub_menu2">
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[1].sub_button[0].name" placeholder="(必填)子菜单一" ng-model="menu2_s1" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[1].sub_button[0].url"  placeholder="(必填)链接地址" ng-model="menu2_u1" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''" />
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[1].sub_button[1].name"  placeholder="子菜单二" ng-model="menu2_s2" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[1].sub_button[1].url" placeholder="链接地址" ng-model="menu2_u2" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[1].sub_button[2].name" placeholder="子菜单三" ng-model="menu2_s3" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[1].sub_button[2].url" placeholder="链接地址" ng-model="menu2_u3" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[1].sub_button[3].name" placeholder="子菜单四" ng-model="menu2_s4" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[1].sub_button[3].url" placeholder="链接地址" ng-model="menu2_u4" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[1].sub_button[4].name"  placeholder="子菜单五" ng-model="menu2_s5" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[1].sub_button[4].url"  placeholder="链接地址" ng-model="menu2_u5" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		</ul>         
		<div class="form-group">
             <label class="col-lg-3 control-label" >菜单三：</label>
              <div class="input-group col-lg-5">
                  <input type="text" class="form-control" name="button[2].name"  placeholder="菜单三(必填)" maxlength="4" ng-model="menu3" maxlength="7" required/>
                  <a class="input-group-addon" onclick="toggleSubMenu('sub_menu3',this)">
                      <i class="fa fa-plus font-red"></i>
                  </a>
              </div>
          </div>
		<ul class="mobile-ul form-body" id="sub_menu3" >
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[2].sub_button[0].name"   placeholder="(必填)子菜单一" ng-model="menu3_s1" maxlength="7"/>
				
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[2].sub_button[0].url"  placeholder="(必填)链接地址" ng-model="menu3_u1" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[2].sub_button[1].name"  placeholder="子菜单二" ng-model="menu3_s2" maxlength="7"/>
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[2].sub_button[1].url" placeholder="链接地址" ng-model="menu3_u2" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control"  name="button[2].sub_button[2].name" placeholder="子菜单三" ng-model="menu3_s3" maxlength="7"/>
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[2].sub_button[2].url" placeholder="链接地址" ng-model="menu3_u3" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[2].sub_button[3].name" placeholder="子菜单四" ng-model="menu3_s4" maxlength="7"/>
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[2].sub_button[3].url" placeholder="链接地址" ng-model="menu3_u4" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		<li class="form-group form-md-line-input ">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-4">
			<input type="text" class="form-control" name="button[2].sub_button[4].name" placeholder="子菜单五" ng-model="menu3_s5" maxlength="7"/>
				<div class="form-control-focus"></div>
			</div>
			<div class="col-lg-5">
			<input type="text" class="form-control" name="button[2].sub_button[4].url" placeholder="链接地址" ng-model="menu3_u5" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':''"/>
				
				<div class="form-control-focus"></div>
			</div>
		</li>
		</ul>
		 <div class="text-center">
		
		 <button type="submit" data-loading-text="提交中.."  id="sub_button" class="demo-loading-btn btn btn-primary">修改</button>
	 	</div>
	 	 <div id="_tip" class="text-danger text-center" style="margin-top:5px;"></div>
	</form>
					</div>
				</div>
			</div>
		</div>
	
	<script src="${ctx}/static/js/angular.min.js"></script>
    <script src="${ctx}/static/js/weiData.js"></script>	
	<script>
	      /*滑动效果  */
	     function toggleSubMenu(id,dom){
	         $(":animated").stop();
	         $(".mobile-ul").not("#"+id).slideUp();
	         var $icon=$(dom).find("i");
	        $icon.toggleClass("fa-minus");
	         $(".fa-plus").not($icon).removeClass("fa-minus");
	         $("#"+id).slideToggle();
	       }
	       
	        function formSubmit(id){
	        sub_button.disabled="disabled";
	          $.post(inputForm.action,$(inputForm).serialize(),function(data){
	            sub_button.removeAttribute("disabled");
	             _tip.innerHTML=data;
	             setTimeout(function(){
	               _tip.innerHTML="";
	             }, 3000)
	          })
	          
	          return false;
	         
	       } 
	       
       
	   angular.module("app",[]).controller("ctrl",function($scope,$http){
           //异步请求数据
	       $scope.getWeiButtonDate=function(){
	        $scope.preload=true;
	         $scope.msg=null;
	       $http.get("${ctx}/admin/wxMenu/detail/"+$scope.wxPubId).success(function(data){
	     
	           $scope.preload=false;
	         $scope.clear();
	       if(data=='appId或appsecret有误'){
	        $scope.msg=data; 
	       }else if((JSON.parse(data)).errcode==46003){
	            $scope.msg="还没创建菜单,请创建"; 
	       }
	       else{
        
            $scope.items=JSON.parse(data).menu;
	         bindDate($scope);
	        }          
	       });    
            }
            
            $scope.clear=function() {
             $scope.menu1="";
             $scope.menu2="";
             $scope.menu3="";
             $scope.menu1_s1="";
	         $scope.menu1_s2="";
	         $scope.menu1_s3="";
	         $scope.menu1_s4="";
	         $scope.menu1_s5="";
	         $scope.menu1_u1="";
	         $scope.menu1_u2="";
	         $scope.menu1_u3="";
	         $scope.menu1_u4="";
	         $scope.menu1_u5="";
	         $scope.menu2_s1="";
	         $scope.menu2_s2="";
	         $scope.menu2_s3="";
	         $scope.menu2_s4="";
	         $scope.menu2_s5="";
	         $scope.menu2_u1="";
	         $scope.menu2_u2="";
	         $scope.menu2_u3="";
	         $scope.menu2_u4="";
	         $scope.menu2_u5="";
	         $scope.menu3_s1="";
	         $scope.menu3_s2="";
	         $scope.menu3_s3="";
	         $scope.menu3_s4="";
	         $scope.menu3_s5="";
	         $scope.menu3_u1="";
	         $scope.menu3_u2="";
	         $scope.menu3_u3="";
	         $scope.menu3_u4="";
	         $scope.menu3_u5="";
            
            }

          
	      function  bindDate ($scope){
	      //绑定数据
	      /*一级菜单  */
	     $scope.menu1= $scope.items.button[0].name;
	     $scope.menu2= $scope.items.button[1].name;
	     $scope.menu3= $scope.items.button[2].name;
	      /*二级菜单  */   
	     $scope.items.button.forEach(function(item,i){
	         if(i==0){
	         $scope.menu1_s1=item.sub_button[0]!=null?item.sub_button[0].name:"";
	         $scope.menu1_s2=item.sub_button[1]!=null?item.sub_button[1].name:"";
	         $scope.menu1_s3=item.sub_button[2]!=null?item.sub_button[2].name:"";
	         $scope.menu1_s4=item.sub_button[3]!=null?item.sub_button[3].name:"";
	         $scope.menu1_s5=item.sub_button[4]!=null?item.sub_button[4].name:"";
	         $scope.menu1_u1=item.sub_button[0]!=null?item.sub_button[0].url:"";
	         $scope.menu1_u2=item.sub_button[1]!=null?item.sub_button[1].url:"";
	         $scope.menu1_u3=item.sub_button[2]!=null?item.sub_button[2].url:"";
	         $scope.menu1_u4=item.sub_button[3]!=null?item.sub_button[3].url:"";
	         $scope.menu1_u5=item.sub_button[4]!=null?item.sub_button[4].url:"";
	         }else if(i==1){
	         $scope.menu2_s1=item.sub_button[0]!=null?item.sub_button[0].name:"";
	         $scope.menu2_s2=item.sub_button[1]!=null?item.sub_button[1].name:"";
	         $scope.menu2_s3=item.sub_button[2]!=null?item.sub_button[2].name:"";
	         $scope.menu2_s4=item.sub_button[3]!=null?item.sub_button[3].name:"";
	         $scope.menu2_s5=item.sub_button[4]!=null?item.sub_button[4].name:"";
	         $scope.menu2_u1=item.sub_button[0]!=null?item.sub_button[0].url:"";
	         $scope.menu2_u2=item.sub_button[1]!=null?item.sub_button[1].url:"";
	         $scope.menu2_u3=item.sub_button[2]!=null?item.sub_button[2].url:"";
	         $scope.menu2_u4=item.sub_button[3]!=null?item.sub_button[3].url:"";
	         $scope.menu2_u5=item.sub_button[4]!=null?item.sub_button[4].url:"";
	         }else {
	         $scope.menu3_s1=item.sub_button[0]!=null?item.sub_button[0].name:"";
	         $scope.menu3_s2=item.sub_button[1]!=null?item.sub_button[1].name:"";
	         $scope.menu3_s3=item.sub_button[2]!=null?item.sub_button[2].name:"";
	         $scope.menu3_s4=item.sub_button[3]!=null?item.sub_button[3].name:"";
	         $scope.menu3_s5=item.sub_button[4]!=null?item.sub_button[4].name:"";
	         $scope.menu3_u1=item.sub_button[0]!=null?item.sub_button[0].url:"";
	         $scope.menu3_u2=item.sub_button[1]!=null?item.sub_button[1].url:"";
	         $scope.menu3_u3=item.sub_button[2]!=null?item.sub_button[2].url:"";
	         $scope.menu3_u4=item.sub_button[3]!=null?item.sub_button[3].url:"";
	         $scope.menu3_u5=item.sub_button[4]!=null?item.sub_button[4].url:"";
	         }
	     });
	       }
	     })
     
	</script>
</body>

</html>