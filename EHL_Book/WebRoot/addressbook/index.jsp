<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Suggest</title>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>

<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/suggest.js"></script>

</head>

<body>
	<div align="center">

		<div>
			<input type="text" id="keyWord"
				style="width: 400px;height: 27px;background-color: #fff;">
			&nbsp;&nbsp; <input type="button" value="百度一下" />
		</div>
		<div>
			<select id="words" multiple="multiple" size="10"
				style="width: 400px;height: 200px;background-color: #fff;">
			</select>	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		</div>
	</div>

</body>
</html>
