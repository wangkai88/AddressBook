/**
 * 登录提示框
 */
function loginBox(){
	//定义登录状态变量
	top.jQuery.jBox.loginResult = false;
	//定义登录窗口内容
	var html = "<div style='padding:10px;'>";
	    html = html + "姓名：<input style='width:145px' type='text' id='name' name='name' /><br><br>"
	    html = html + "密码：<input type='password' id='pass' name='pass' /><br></div>";
	//定义提交按钮函数
	var submit = function (v, h, f) {
	    if (f.name == '' || f.pass == '') {
	        jQuery.jBox.tip("请输入用户名和密码", 'error', { focusId: "name" }); 
	        return false;
	    }
	    top.jQuery.ajax({
	    	dataType : 'json',
		    url:  'loginForAjax.action',
		    data: {"pname":f.name,"pwd":f.pass},
		    error:error,
		    success :success
		}); 
	    return false;
	};
	//定义成功提交函数
	function success(json){
		if(json.code == "sucess"){
			//top.jQuery.jBox.tip("登录成功");
			loginResult = true;
			top.jQuery.jBox.close("loginbox");
			if(json.href != null && json.href != ""){
				window.location.href = json.href;
			}
		}else{
			top.jQuery.jBox.tip(json.errorText);
		}
	}
	//定义窗口关闭事件函数，按ESC键退出窗口时从新打开
	function closed(){
		if(!loginResult){
			top.jQuery.jBox(html, {closed:closed,draggable: false,id:'loginbox',showClose: false,width:250, title: "登录过期，请重新登录", submit: submit ,buttons: { '登录': 'ok' }});
		}
	}
	//定义失败函数
	function error(){
		top.jQuery.jBox.tip("登录失败");
	}
	//弹出登录提示框
	top.jQuery.jBox(html, {closed:closed,draggable: false,id:'loginbox',showClose: false,width:250, title: "登录过期，请重新登录", submit: submit ,buttons: { '登录': 'ok' }});
}

var rootName = "";
var rootType = "";
var rootPath = "undefined";
var rootCode = "";
var Nodecode = "";
/*
 *初始化树
 */
window.onload = function () {
	var url = "state/cityPathTreeAction!getTreeData.action";
	//alert(0);
	$.ajax({url:url, type:"POST", dataType:"json", error:function (msg) {
		alert(msg.responseText);
	}, success:function (data) {
		if (data != null) {
			viewTree(data);
			//alert(rootCode);
			queryNode(rootName,rootName,rootPath,rootType,rootCode);
		}
	}});
};
/*
 *显示树
 */
function viewTree(data) {
	var treeDiv = document.getElementById("treeDiv");
	var html = "<ul class='tree_nav_item_ov'><li><img src='../imgs/tree/plus5.gif' width='18' height='24' onclick='showChiled(this)'/><font id='city' ></font><ul style=' display:none;' class='tree_nav2'>";
	for (var i = 0; i < data.length; i++) {
		//alert(data[i].volumeyellowlimit);
		if (data[i].code.substring(data[i].code.length - 8) == "00000000") {
			rootName = data[i].name;
			rootType = data[i].type;
			rootCode = data[i].code;
			continue;
		}
		html += "<li >";
		html += "<img src='../imgs/tree/plus5.gif' width='18' height='24' onclick='showChiled(this)'/>";
		//alert(data[i].name);
		html += "<a href='javascript:void(0)'><font onclick=\"queryNode('" + data[i].name + "','" + data[i].name + "', '" + data[i].path + "','" + data[i].type + "','" + data[i].code + "')\">" + data[i].name + "</font></a>";
		if (data[i].list != null) {
			html += "<ul style=' display:none;'>";
			for (var m = 0; m < data[i].list.length; m++) {
				html += "<li >";
				var o = data[i].list[m];
				html += "<img src='../imgs/tree/plus5.gif' width='18' height='24' onclick='showChiled(this)'/>";
				html += "<a href='javascript:void(0)'><font onclick=\"queryNode('" + o.name + "','" + o.area + "', '" + o.path + "','" + o.type + "','" + o.code + "')\">" + o.name + "</font></a>";
				if (data[i].list[m].list != null) {
					html += "<ul style=' display:none;'>";
					for (var n = 0; n < data[i].list[m].list.length; n++) {
						var obj = data[i].list[m].list[n];
						html += "<li onclick=\"queryNode('" + obj.name + "','" + obj.area + "','" + obj.path + "','" + obj.type + "','" + obj.code + "')\"><a href='javascript:void(0)'>";
						//html += "<img src='../imgs/xialasanjiao.png' onclick='showChiled(this)'></img>";
						//html += "<li onclick="showContext('"+obj+"')">";
						html += obj.name;
						html += "</a></li>";
					}
					html += "</ul>";
				}
				html += "</li>";
			}
			html += "</ul>";
		}
		html += "</li>";
	}
	html += "</ur></li></ul>";
	treeDiv.innerHTML = html;
		//alert(volumeyellowlimit);																																		
	//$("#city").html("<a href='#'><font onclick=\"showContext('" + rootName + "','" + rootArea + "', '" + rootPath + "','" + rootType + "','" + rootCode + "','" + volumeyellowlimit + "','" + volumeredlimit + "','" + volumepeaklimit + "','" + congyellowlimit + "','" + congredlimit + "','" + crowdyellowlimit + "','" + crowdredlimit + "')\">" + rootName + "</font></a>"); //设置根节点
	$("#city").html("<a href='#'><font onclick=\"queryNode('" + rootName + "','" + rootName + "', '" + rootPath + "','" + rootType + "','" + rootCode + "')\">" + rootName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></a>");
	//alert($("#city").html);
}
//点击展开隐藏树
function showChiled(obj) {
	var chiled = $(obj).nextAll("ul");//.nextAll().andSelf();
	if (chiled[0].style.display == "none") {
			obj.src = "../imgs/tree/minus5.gif";
		chiled[0].style.display = "block";
	} else {
			obj.src = "../imgs/tree/plus5.gif";
		chiled[0].style.display = "none";
	}
}
//如果 辖区节点的 类型是 道路 则不需要显示 所属道路的名称
function parsePath(path) {
	if (path == "undefined") {
		document.getElementById("path").removeNode(true);
	}
}
//查询单个节点
function queryNode(name, xq, path, type, code) {
	var url = "state/cityPathTreeAction!queryNode.action";
	$.ajax({url:url, type:"POST", dataType:"json",data:{code:code}, error:function (msg) {
		alert(msg.responseText);
	}, success:function (data) {
		if (data != null) {
			//alert(parseType(data)+"data");
			showContext(data.name, xq, path, data.type, data.code, data.volumeyellowlimit, data.volumeredlimit, data.volumepeaklimit, data.congyellowlimit, data.congredlimit, data.crowdyellowlimit, data.crowdredlimit,data.isImportant);
		}
	}});
}