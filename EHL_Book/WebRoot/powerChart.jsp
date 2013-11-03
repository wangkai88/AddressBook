<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
request.getSession().setAttribute("contextPath",request.getContextPath());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'success.jsp' starting page</title>
    <link href="css/paging.css" rel="stylesheet" type="text/css">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../excanvas.min.js"></script><![endif]-->
 <script type="text/javascript"src="js/jquery-1.9.1.js"></script> 

    <script language="javascript" type="text/javascript" src="js/paging.js"></script>
<script type="text/javascript">
//contextpath
var contextPath = "${contextPath}";
//分页初始化
//sum：记录总数
//count:每页条数
//total:页数
//current:当前页
//previous:上一页
//next:下一页
//first:第一页
//last:最后一页
//jump:跳到X页
//分页初始化/每页显示行数（默认10行），参数只要数据集（list、array、json类型），"paging"对应div的id
var paging1 = new Paging("paging1",data1,{count:1});
var paging2 = new Paging("paging2",data2,{count:2});
var paging3 = new Paging("paging3",data3,{count:2});
</script>
</head>
  
  <body>
	    <div id="paging1" class="paging">
	    </div>	
		<div id="paging2" class="paging">
	    </div>	
		<div id="paging3" class="paging">
	    </div>	
  </body>
</html>