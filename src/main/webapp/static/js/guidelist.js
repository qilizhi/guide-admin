/**
 * 导游列表js
 */

var _GuideList=function(){	
	var addUrl = mlx.ctx + "/admin/userInfo/create";
	var updateUrl = mlx.ctx + "/admin/userInfo/update";
	var delUrl = mlx.ctx + "/admin/userInfo/delete";
	var batdelUrl = mlx.ctx + "/admin/userInfo/deletes";
	var getUrl = mlx.ctx + "/admin/userInfo";
	var forbiddenUrl = mlx.ctx + "/admin/userInfo/forbidden";
	var unforbiddenUrl = mlx.ctx + "/admin/userInfo/unforbidden";
	var auditUrl = mlx.ctx + "/admin/userInfo/audit";
	//批量审核 
	var audit=function () {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}

		auditShow(ids);

	}
	var  auditShow=function(ids) {

		$("#ajax").hide();
		//将对象属性 填充表单
		/* for ( var name in obj) {
			$("#modalForm input[name=" + name + "]").val(obj[name]);
		} */
		//去除绑定事件
		var data = {};
		data.ids = ids;
		$("#modalForm button[type='submit']").on("click", data, commAudit);
		$("#responsive").modal('show');

	}

	//审核
	var  commAudit=function(pdata) {

		var url = auditUrl + "/" + pdata.data.ids;
		var submitData = $("#responsive form").serialize();

		/* 设置按钮的语言 */
		/* 		bootbox.setLocale("zh_CN");
				bootbox.confirm("你确定要通过这些记录吗?", function(result) { */
		/* if (result) { */

		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					//addHide();
					comm.showMsg('success', '消息提示', '通过成功！');
					location.reload();
				} else {
					comm.showMsg('warning', '消息提示', '通过失败！');
				}
			},
			error : function(e) {
				comm.showMsg('error', '消息提示', '审核 出错，网络出问题了！');
			}

		});

		/* } */
		/* }); */
	}

	//批量禁用
	var  forbidden=function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		commForbidden(ids);

	}
	//批量解禁
	var  unforbidden=function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		commUnforbidden(ids);

	}
	var commForbidden=function(ids) {
		var url = forbiddenUrl + "/" + ids;
		/* 设置按钮的语言 */
		bootbox.setLocale("zh_CN");
		bootbox.confirm("你确定要禁用这条记录吗?", function(result) {
			if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '禁用成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '禁用失败！');
						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '禁用出错，网络出问题了！');
					}

				});

			}
		});
	}

	var  commUnforbidden=function(ids) {

		var url = unforbiddenUrl + "/" + ids;
		/* 设置按钮的语言 */
		bootbox.setLocale("zh_CN");
		bootbox.confirm("你确定要解禁这条记录吗?", function(result) {
			if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '解禁成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '解禁失败！');
						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '解禁出错，网络出问题了！');
					}

				});

			}
		});
	}

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

	var  batDel=function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		var url = batdelUrl + "/" + ids;
		/* 设置按钮的语言 */
		bootbox.setLocale("zh_CN");
		bootbox.confirm("你确定要删除这条记录吗?", function(result) {
			if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '删除成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '删除失败！');
						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '删除出错，网络出问题了！');
					}

				});

			}
		});

	}

	var detailShow=function(id) {
		$("#detailResponsive").modal('show');
		var obj = get(id);
		//将对象属性 填充表单
		for ( var name in obj) {

			if (name == "headImgUrl") {

				$("#modalForm textarea[id=" + name + "]").val(obj[name]);

			}

			if (name == "sex") {
				if (obj[name] == 1) {
					$("#modalForm span[id=" + name + "]").html("男");
				} else if (obj[name] == 2) {
					$("#modalForm span[id=" + name + "]").html("女");
				}
				continue;
			}

			$("#modalForm span[id=" + name + "]").html(obj[name]);

		}
		/* $("#ajax").hide();
		$("#modalForm button[type='submit']").one("click", add); */
	}
	var addShow=function() {
		$("#responsive").modal('show');
		$("#ajax").hide();
		//去除绑定事件
		$("#modalForm button[type='submit']").unbind();
		$("#modalForm button[type='submit']").on("click", add);
	}

	/* 隐藏 */
	function modalHide() {
		$("#responsive").modal('hide');
		$("div.modal-backdrop").remove();
		//重设表单 
		$("#modalForm").each(function() {
			this.reset();
		});
	}

	//获取对信息
	var get=function(id) {
		var url = getUrl + "/" + id;
		var data;
		$.ajax({
			url : url,
			type : 'get',
			dataType:"json",
			async : false,
			timeout : 400,
			success : function(result) {
				if (result.code == "200") {
					data = result.result;
				} else {
					return false;
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
			}
		});

		return data;

	}

	//新增
	var  add=function() {
		var submitData = $("#responsive form").serialize();
		$("#ajax").show();
		$.ajax({
			url : addUrl,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					modalHide();
					location.reload();
				} else {
					$("#message").html("创建失败！");
					$("#ajax").hide();
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
				$("#ajax").hide();
			}
		});

	}

	//提 交更新数据
	var update=function(id) {

		//打开modal
		editShow(id);
		var submitData = $("#responsive form").serialize();
		$("#ajax").show();
		$.ajax({
			url : updateUrl,
			type : 'post',
			dataType:"json",
			data : submitData,
			success : function(result) {
				if (result.code == "200") {
					modalHide();
					location.reload();

				} else {
					$("#message").html("更新失败！");
					$("#ajax").hide();
				}
			},
			error : function(e) {
				$("#message").html("请求出错！");
				$("#ajax").hide();
			}
		});
	}

	/* 删除 */
	var  del=function(id) {
		var url = delUrl + "/" + id;
		/* 设置按钮的语言 */
		bootbox.setLocale("zh_CN");
		bootbox.confirm("你确定要删除这条记录吗?", function(result) {
			if (result) {

				$.ajax({
					url : url,
					type : 'post',
					dataType:"json",
					success : function(result) {
						if (result.code == "200") {
							//addHide();
							comm.showMsg('success', '消息提示', '删除成功！');
							location.reload();
						} else {
							comm.showMsg('warning', '消息提示', '删除失败！');

						}
					},
					error : function(e) {
						comm.showMsg('error', '消息提示', '删除出错，网络出问题了！');
					}

				});

			}
		});
	}
	
	var selectAll=function(){
	
		//全选/全不选
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
	//校验
	var formValidate=function(){

		//校验
		var form2 = $('#modalForm');
		var error2 = $('.alert-danger', form2);
		var success2 = $('.alert-success', form2);
		var ajax = $("#ajax");

		form2.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			rules : {
				accessToken : {
					minlength : 2,
					required : true
				},
				weixinPublicId : {
					required : true,
					number : true
				},

				expiresIn : {
					required : true,
					number : true
				},
			},

			invalidHandler : function(event, validator) { //display error alert on form submit              
				success2.hide();
				error2.show();
				App.scrollTo(error2, -200);
			},

			errorPlacement : function(error, element) { // render error placement for each input type
				var icon = $(element).parent('.input-icon').children('i');
				icon.removeClass('fa-check').addClass("fa-warning");
				icon.attr("data-original-title", error.text()).tooltip({
					'container' : 'body'
				});
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group')
						.removeClass("has-success").addClass('has-error'); // set error class to the control group   
			},

			unhighlight : function(element) { // revert the change done by hightlight

			},

			success : function(label, element) {
				var icon = $(element).parent('.input-icon').children('i');
				$(element).closest('.form-group').removeClass('has-error')
						.addClass('has-success'); // set success class to the control group
				icon.removeClass("fa-warning").addClass("fa-check");
			},

			submitHandler : function(form) {
				success2.show();
				ajax.modal('show');
				error2.hide();
				// form[0].submit(); // submit the form
			}
		});

		
	}
	
	//函数初始化
	return {
		del:function(id){
			del(id);			
		},
		update:function(id){
			update(id);
		},
		add:function(){
			add();
		},
		commUnForbidden:function(ids){
			commUnforbidden(ids);
		},
		commForbidden:function(ids){
			commForbidden(ids);
		},auditShow :function(ids){
			
			auditShow(ids);
		},unforbidden:function(){
			unforbidden();
		},audit:function(){
			audit();
		},forbidden:function(){
			forbidden();
		},init:function(){
			selectAll();
//			formValidate();
			
		}
	}
}()

jQuery(document).ready(function(){	
	_GuideList.init();
	
	//绑定按钮
	$(".commForbidden").on("click",function(event){
     	_GuideList.commForbidden(event.target.id);        	
     });			
		
     $(".commUnForbidden").on("click",function(event){
     	_GuideList.commUnForbidden(event.target.id);        	
     });			
     $(".auditShow").on("click",function(event){
     	_GuideList.auditShow(event.target.id);        	
     });			
     $(".unforbidden").on("click",function(event){
     	_GuideList.unforbidden();        	
     });			
     $(".audit").on("click",function(event){
     	_GuideList.audit();        	
     });			
     $(".forbidden").on("click",function(event){
     	_GuideList.forbidden();        	
     });			
});