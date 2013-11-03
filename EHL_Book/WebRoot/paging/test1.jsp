<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<jsp:include page="paging.jsp">
		<jsp:param value="rest/goPaging" name="action"/>
		<jsp:param value="center" name="align"/>
		<jsp:param value="false" name="serch"/>
		<jsp:param value="center" name="serchAlign"/>
		<jsp:param value="7" name="colspan"/>
		<jsp:param value="800" name="width"/>
		<jsp:param value="30" name="height"/>
		<jsp:param value="true" name="showNumber"/>
		<jsp:param value="white" name="buttonColor"/>
		<jsp:param value="black" name="buttonFontColor"/>
		<jsp:param value="gray" name="buttonCheckColor"/>
	</jsp:include>
  </body>
</html>
