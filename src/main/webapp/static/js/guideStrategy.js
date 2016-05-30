

/**
 * guideStrategy
 * 
 */

var GuideStrategy=function(){
	
	var delUrl=mlx.ctx+"/admin/guideStrategy/delete";
	var auditUrl=mlx.ctx+"/admin/guideStrategy/audit"
	var onOffUrl=mlx.ctx+"/admin/guideStrategy/onOff"
	
	
	/**获取表格上选 中的ids**/
	 var getCheckIds=function() {
		//获取删 除的ids
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
	
	 /**
	  * 获取当前行的id
	  */
	var getId=function(){
		
		
	}
	
	/**  批量删除 **/
	var batDel = function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		del(ids);
	}
	/* 删除 */
	var del = function(ids) {
		var url = delUrl + "/" + ids;
		/* 设置按钮的语言 */
	//	bootbox.setLocale("zh_CN");
		comm.confirm("提示","你确定要删除这条记录吗?", function() {

				$.ajax({
					url : url,
					type : 'get',
					dataType : "json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '删除成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '删除失败！'+result.result);

						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '删除出错，请求出问题了！');
					}

				});

		});
	}


//初始化下多选
	var initSelectAll = function() {

		$("thead tr th input[type='checkbox']").on(
				"click",
				function(event) {
					//	console.log(event);
					//console.log(event.currentTarget.checked);
					if (event.currentTarget.checked == true) {
						$("tbody tr input[type='checkbox']").prop(
								"checked", true);
					}

					if (event.currentTarget.checked == false) {
						$("tbody tr input[type='checkbox']").prop(
								"checked", false);
					}
				});
	}
	
	
	var batAuditShow=function(){
		var ids=getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		auditShow(ids);
	}
	
	//审核modal
	var  auditShow=function(ids) {
		//去除阻表单默认事件
		var form1 = $('#auditForm');
		form1.submit(function(e) {
			e.preventDefault();
		});
		var data = {};
		data.ids = ids;
		$("#auditForm button[type='submit']").one("click", data, commAudit);
		$("#auditResponsive").modal('show');

	}

	//审核
	var  commAudit=function(pdata) {

		var url = auditUrl + "/" + pdata.data.ids;
		var submitData = $("#auditResponsive form").serialize();
		var modal=$("#auditResponsive")
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					//addHide();
					comm.showMsg('success', '消息提示', '通过成功！');
					modal.modal('hide');
					location.reload();
				} else {
					comm.showMsg('warning', '消息提示', result.result);
					modal.modal('hide');
				}
			},
			error : function(e) {
				comm.showMsg('error', '消息提示', '求请出问题了！');
				modal.modal('hide');
			}

		});
	}
	/*var onOff=function(ids){
		//去除阻表单默认事件
		var form1 = $('#onOffForm');
		form1.submit(function(e) {
			e.preventDefault();
		});
		var data = {};
		data.ids = ids;
		$("#onOffForm button[type='submit']").one("click", data, submitOnOff);
		$("#onOffResponsive").modal('show');
		
	}*/
	
	var submitOnOff=function(id,status){
		
		var url = onOffUrl + "/" + id;
	//	var submitData = $("#onOffResponsive form").serialize();
	//	var modal=$("#onOffResponsive");
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				"status":status
			},
			success : function(result) {
				if (result.code == "200") {
					//addHide();
					comm.showMsg('success', '消息提示', '通过成功！');
				//	modal.modal('hide');
					location.reload();
				} else {
					comm.showMsg('warning', '消息提示', result.result);
				//	modal.modal('hide');
				}
			},
			error : function(e) {
				comm.showMsg('error', '消息提示', '求请出问题了！');
				//modal.modal('hide');
			}

		});
	}
	//预览
	//预览
	var preViewShow=function(url){
		$("#preViewResponsive iframe").attr("src",url);
		$("#preViewResponsive").modal('show');
		
	}
	return {
		
		 batDel:function(){
			 batDel();
		 },
		 del:function(ids){
			 del(ids);
		 },
		 initSelectAll:function(){
			 
			 initSelectAll();
		 },
		 auditShow:function(id){
			 auditShow(id); 
			 
		 } ,onOff:function(id,status){
			 
			 submitOnOff(id,status);
		 },batAuditShow:function(){
			 batAuditShow();
		 },preViewShow:function(url){
			 preViewShow(url);
		 }
	
	}
	
}()

jQuery(document).ready(function(){
	GuideStrategy.initSelectAll();
	$(".batDel").on("click",function(){
		GuideStrategy.batDel();
	});
	$(".del").on("click",function(e){
		//获取当前行的id值
		var id=$(e.currentTarget).parent().attr("id");
		GuideStrategy.del(id);
	});
	$(".audit").on("click",function(e){
		//获取当前行的id值
		var id=$(e.currentTarget).parent().attr("id");
		console.log(id);
		GuideStrategy.auditShow(id);
	});
	$(".onOff").on("click",function(e){
		//获取当前行的id值
		var id=$(e.currentTarget).parent().attr("id");
		var status=$(e.currentTarget).attr("status");
		GuideStrategy.onOff(id,status);
	});
	$(".batAudit").on("click",function(e){
		GuideStrategy.batAuditShow();
	});
	$(".preView").on("click",function(e){
		var url=$(e.currentTarget).parent().find(".detail").attr("href");
		GuideStrategy.preViewShow(url);
	});
});