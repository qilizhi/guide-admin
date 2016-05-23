/**
 *
 *环信群的js
 *@author qilizhi
 * 
 */
 
var EmGroup=function(){
	
	/**		初始化下拉框	
	selectName 选择框的名字
**/
var initSelect = function(selectName,value) {
var select = $("select[name='" + selectName + "']");
//用户数据
$.ajax({
	url : mlx.ctx + "/admin/emUser/list",
	type : "get",
	dataType : "json",
	success : function(data) {
		var options = "";
		if (data.code == "200") {
			var users = data.result;
			console.log(users);
			$.each(users, function(index, obj) {
				options += "<option value='"+obj.emUser+"'";
				if(value!=null&&obj.emUser==value){
				options+="selected";
				}
				options+=" >"+ obj.emUser + "</option>";
			});
		}

		select.empty();
		select.append(options);
		select.selectpicker('refresh');
	},
	error : function(e) {
		comm.errorMsg("请求出错！");
	}

});
}
	/** 图片上传的控件 **/
	var initImgUpload = function() {
		var inputFile=$("input[type='file']");
		//图上传
		$("#supprogress").css('display', "none");
		$("input[type='file']").css({
			'right' : '0',
			'width' : '70px',
			'height' : '22px',
			'z-index' : '22',
			'margin-top' : '-20px',
			'margin-left' : '-5px',
			'opacity' : '0',
			'-ms-filter' : 'alpha(opacity=0)',
			'direction' : 'ltr',
			'cursor' : 'pointer',
		});
		$("input[type='file']").parent().css({

			'padding' : '0',

		});
		inputFile.on(
				'change',
				function(e) {
					var files = this.files;
					var fullname = $(this).val();
					$("#imageName")
							.html(
									fullname.substring(fullname
											.lastIndexOf("\\") + 1));
					$("#supprogress").css('display', "block");
					$("#image").attr("src", "");
					$("input[name='image']").val("");
				})
		inputFile.fileupload(
				{
					type : "post",
					dataType : 'json',
					url : mlx.ctx + '/upload',
					progressall : function(e, data) {
						var progress = parseInt(data.loaded / data.total
								* 100, 10);
						$('.progress .progress-bar-success').css('width',
								progress + '%');
						$('.progress .progress-bar-success').text(
								progress + '%');
						//console.log(data);
					},

					done : function(e, data) {
						if (data.result.code == "200") {
							$("#supprogress").css('display', "none");
							$("#image").attr("src",
									data.result.result[0].filePath);
							$("input[name='image']").val(
									data.result.result[0].filePath);
							$("#load").html("重传");
						} else {
							$('.progress .progress-bar-success').text(
									data.result.msg);
						}
						//console.log(data);
						$('.progress .progress-bar-success').text("done");
					}
				});
	}

	//表单较验
	var formValidate = function(url) {
		var form1 = $('#inputForm');
		form1.submit(function(e) {
			e.preventDefault();

		});
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);
		form1.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {

				emGname : {
					minlength : "至少要有两个字符！",
					required : "不能为空！"
				},
				emAffiliationsCount : {
					required : "不能为空！",
					number : "请输入数字"
				},
				emGdesc : {
					required : "不能为空！"

				},
				image : {
					required : "不能为空！"

				},
				emGuser : {
					required : "不能为空！"

				},
				ownerSign : {
					required : "不能为空！"

				},
				ownerImage : {
					required : "不能为空！"

				},
				emGid : {
					required : "不能为空！",
					number : "请输入数字"
				},
				emAllowinvites : {
					required : "不能为空！"

				},
				enterRule : {
					required : "不能为空！"

				},
				emMaxusers : {
					required : "不能为空！",
					number : "请输入数字"
				}
			},
			rules : {
				emGname : {
					minlength : 2,
					required : true
				},
				emAffiliationsCount : {
					required : true,
					number : true
				},
				image : {
					required : true

				},
				emGdesc : {
					required : true

				},
				emGuser : {
					required : true

				},
				ownerSign : {
					required : true

				},
				ownerImage : {
					required : true

				},
				emGid : {
					required : true,
					number : true

				},
				emAllowinvites : {
					required : true

				},
				enterRule : {
					required : true

				},
				emMaxusers : {
					required : true,
					number : true
				}
			},

			invalidHandler : function(event, validator) { //display error alert on form submit              
				success1.hide();
				error1.show();
				App.scrollTo(error1, -200);
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
			},

			unhighlight : function(element) { // revert the change done by hightlight
				$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error'); // set success class to the control group
			},

			submitHandler : function(form) {
				success1.show();
				error1.hide();
				$.post(url, $(form).serialize(),
						function(result) {
							if (result.code == "200") {
								window.location.href = mlx.ctx
										+ "/admin/group";
							}else{
								comm.errorMsg(result.result);
							}
						}, "json");
			}
		});

	}
	
	
	
	
	return {
		formInit:function(url){
			formValidate(url);
			initImgUpload();			
		},
	    selectInit:function(selectName,value){
	    	initSelect(selectName,value);
	    } 
		
	}
}();