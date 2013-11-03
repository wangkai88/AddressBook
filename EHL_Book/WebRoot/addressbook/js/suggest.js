$(document).ready(
		function() {

			// 给input输入框注册keyup键盘点击事件
			$("#keyWord").bind(
					"keyup",
					function() {
						// 获取输入的值
						var keyVal = $(this).val();
						// 判断如果输入框为空时，清空隐藏select并返回，不再执行以下代码
						if ("" == keyVal) {
							// 清空并隐藏select框
							$("#words").empty().hide(); // 对象链式操作，jQuery的特性
							return;
						}
						// 发送ajax请求
//						$.post("./book/UserAction_keyVals.action?time="
//								+ new Date().getTime(), {
//							keyWord : keyVal
//						}, function(data) {
//							// 清空
//							$("#words").empty();
//							// 获取相关的属性
//							var jsonUsers = data.users;
//							for ( var i = 0; i < jsonUsers.length; i++) {
//								// 得到具体的user对象
//								var jsonUser = jsonUsers[i];
//								// 创建option
//								var $opt = $("<option></option>");
//								$opt.text(jsonUser.name);
//								// 把创建的opt添加到select中
//								$("#words").append($opt).show();
//							}
//						}, "json");
						//通讯录更加名字模糊搜索
						var url = "addbook/addressbookAction!getkeyVals.action";
						$.ajax({
							url : url,
							type : 'POST',
							dataType : 'json',
							data:{keyWord : keyVal},
							error : function(msg) {
								alert(msg.responseText);
							},
							success : function(data) {
								// 清空
								$("#words").empty();
								// 获取相关的属性
								var jsonUsers = data.users;
								for ( var i = 0; i < jsonUsers.length; i++) {
									// 得到具体的user对象
									var jsonUser = jsonUsers[i];
									// 创建option
									var $opt = $("<option></option>");
									$opt.text(jsonUser.name);
									// 把创建的opt添加到select中
									$("#words").append($opt).show();
								}
							}
						});
						// 为select框注册双击事件
						$("#words").bind("dblclick", function() {
							// 为input框设值
							$("#keyWord").val($(this).val());
							$("#words").empty().hide();
						});

						// 为select框注册回车事件
						$("#words").bind("keyup", function(event) {
							// 按键13为回车键
							if (event.which == 13) {
								$("#keyWord").val($(this).val());
								$("#words").empty().hide();
							}
						});

					});
		});