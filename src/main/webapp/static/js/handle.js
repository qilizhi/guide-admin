   //表单重置
	function formReset(){
		var $form=$("#searchForm");
		$form.find("input[type!='hidden'][value!='查询'][value!='重置']").each(function(i,item){
			if(item.type=='radio'){
				$(item).parent("span").removeClass();
			}
			
			$(item).removeAttr("checked");
			 item.value='';
	    });
		$form.find("select").each(function(i,item){	
			$(item).find("option:first").attr("selected",'selected');
		});
		 
	 }

	//在网页右下角添加回到顶部按钮,使用方法:在head添加	<meta name="toTop" content="true"/>
	$(function() {
	if ($("meta[name=toTop]").attr("content") == "true") {
		$("<div id='toTop' style='width:50px;heigth:50px;position:fixed;bottom:10px;right:15px;cursor:pointer;z-index:9999;display:none' ><i class='icon-circle-arrow-up' style='font-size:40px;color:#ddd;text-shadow:0 0 4px rgba(0,0,0,0.5);'></i></div>")
				.appendTo('body');
		var $top=$("#toTop");
		$(window).scroll(function() {		
			if ($(this).scrollTop() == 0) {
				$top.hide();
			} else {
				$top.show();
			}
		});
		$top.click(function() {
			$("html,body").animate({
				scrollTop : 0
			}, 666);
		});
	}

	//表单提交时触发校验
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
  	
  //绑定全选事件
	try {
		document.getElementById("checkAll").onclick = function(){
			var boxs = document.getElementsByName("box");
			for (var i = 0; i < boxs.length; i++)
			{
				boxs[i].checked = this.checked;
			}
		};
	} catch (e) {
		
	}
	
	//获取选中 checkbox中的ids
	   function getCheckIds() {
		var ids = "";
		var obj = $("tbody tr td input[type='checkbox']:checked");
		$.each(obj, function(index, data) {
			if (obj.length == index + 1) {
				ids += $(data).attr("id");
			} else {
				ids += $(data).attr("id") + ",";
			}
		});
		return ids;
	}
});

	
  function myconfirm(content,callback){
	
	
  }
  
  
  //弹出提示消息框
  function showtip(content,time){
	  var div=document.createElement("div");
	  var i=document.createElement("i");
	  div.appendChild(i);
	  div.className="showtip";
	  div.innerHTML="<i class='fa fa-exclamation-circle'></i>&nbsp;"+content;
	  document.body.appendChild(div);
	  $(div).animate({"opacity":1,"marginTop":"-10px"},600)
	  setTimeout(function(){
		  $(div).animate({"opacity":0},1000,function(){
			  document.body.removeChild(div);
		  })  
	  }, time==null?2000:time)
	  
 
  }

  //tabel展开与收藏
  
  function toggleDetail(dom){
	  $(dom).toggleClass("active").parent("tr").next("tr").toggle();
  }
	
			
		
	

