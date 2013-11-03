<%@ taglib prefix="n" uri="/WEB-INF/n.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="tangs" uri="http://www.kcyg.cn/pages" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.	0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>廊坊交通管控信息平台</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/easyui.css">
    <link href="css/paging.css" rel="stylesheet" type="text/css">

   <link rel="stylesheet" type="text/css" href="css/icon.css" />
   <link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="zTree/css/demo.css"/>
 <script type="text/javascript"src="js/jquery-1.9.1.js"></script> 
 <script language="javascript" type="text/javascript" src="js/paging.js"></script>
<!-- 		<script type="text/javascript" src="js/jquery.min.js"></script>
	    <script type="text/javascript" src="js/jquery.easyui.min.js"></script> -->
	    <script language="javascript" src="./script/jquery.cookie-min.js"></script>
<script language="javascript" src="js/pagination.js"></script>
<script type="text/javascript" src="zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="js/addressbook_control.js"></script>
<script type="text/javascript"src="js/suggest.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body onload="" onresize="">
<div id="top">
  <div class="logo" style="margin-top:8px;"><img src="imgs/contact_logi.png" alt="logo"/></div>
  
  <div class="loginfo">
    <div class="logout" onclick="window.close()"><span style=" display:block; float:right; height:25px; background:#071531; padding-top:4px;">关闭</span></div>
  </div>
  <div class="contact_s">
<!--     <input type="text" name="textfield" class="search_input" /><input type="button" class="search_contactbt" value=" " /> -->
			<input type="text" id="keyWord" class="search_input" /><input type="button" class="search_contactbt" value=" " />
			<select id="words" multiple="multiple" size="10"
				style="width:210px;height:150px;background-color: #fff;display:none;">
			</select>	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </div>
</div>
<div class="top_bottom" id="top_bottom"><img src="imgs/top_nav_bottom_l.jpg" alt="顶部间隔左边图片" width="100" height="11" /></div>
<div id="contact_left"><img src="imgs/left_title.png" alt="左边顶" width="179" height="32" />

			<div  data-options="fit:true,border:false">
				<div style="z-index:12754; border: 1px solid lightgray;  width: 50px; height: 35px; display: none; padding: 4px;" id="treeLoadMsg">
					<img src="images/loading.gif" style="vertical-align: middle;" width="32" height="32"/>
					<span>Loading...</span>
				</div>
		        <ul id="treeDemo" class="ztree"></ul>
			</div>
</div>
<div id="contact">
  <div class="pagetitle"><span>廊坊支队</span> -- <span><a href="#">第一大队</a></span></div>
  <div style="margin:20px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td width="120" align="left">单位名称：</td>
        <td colspan="3" class="name">河北省交警总队</td>
        <td width="5%">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="left">单位地址：</td>
        <td colspan="3"><b>河北省石家庄市合作路358号</b></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="left">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    编：</td>
        <td width="25%"><b>050081</b> </td>
        <td width="120" align="left">电话区号：</td>
        <td width="22%"><strong>0311</strong> </td>
        <td width="120" colspan="3">总&nbsp;&nbsp;&nbsp;&nbsp;  机：</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="left">传    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</td>
        <td><b>87867780</b></td>
        <td>值班电话：</td>
        <td><strong>87867777、 83636882</strong> </td>
        <td colspan="3"> 办公室：</td>
        <td>&nbsp;</td>
      </tr>
      </table>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table4">
    <tr>
      <th>职位</th>
      <th>姓名</th>
      <th>办公电话</th>
      <th>手机</th>
      <th>个人电话</th>
      <th>电子邮箱</th>
    </tr>
	<tr id="paging" class="paging">	
	</tr>
  </table>
</div>
</body>
</html>
