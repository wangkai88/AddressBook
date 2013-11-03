$(document).ready(
		function() {

			$("#uname").bind(
					"blur",
					function() {

						// 获取用户名
						var name = $(this).val();
						// 使用post方法发送ajax请求
						$.post("./csdn/UserAction_checkName.action?time="
								+ new Date().getTime(), {
							name : name    //data数据
						}, function(data) {  //回调函数
							$("#msg").empty();
							$("#msg").append(data);
						}, "json");  //json类型
					});
		});