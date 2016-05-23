define(function(require, exports, module) {
	$(document).ready(function(){	
		//聚焦第一个输入框
		$("#name").focus();
		//为formGallery注册validate函数
		$("#inputForm").validate();
	});
	
});