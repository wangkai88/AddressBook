$(document).ready(
		function() {

			$("#uname").bind(
					"blur",
					function() {

						// ��ȡ�û���
						var name = $(this).val();
						// ʹ��post��������ajax����
						$.post("./csdn/UserAction_checkName.action?time="
								+ new Date().getTime(), {
							name : name    //data����
						}, function(data) {  //�ص�����
							$("#msg").empty();
							$("#msg").append(data);
						}, "json");  //json����
					});
		});