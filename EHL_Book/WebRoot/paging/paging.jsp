<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String pathPaging = request.getContextPath();
String basePathPaging = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathPaging+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePathPaging%>">
    
    <title>My JSP 'paging.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/paging/btn_css.css" type="text/css" />
	<script type="text/javascript" src="paging/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#first").click(function(){
				$("#currPage").val("1");
				goSubmit();
			});
			$("#up").click(function(){
				var currPage = $("#currPage").val()*1-1;
				$("#currPage").val(currPage);
				goSubmit();
			});
			$("#next").click(function(){
				var currPage = $("#currPage").val()*1+1;
				$("#currPage").val(currPage);
				goSubmit();
			});
			$("#last").click(function(){
				var allPage = $("#allPage").val()
				$("#currPage").val(allPage);
				goSubmit();
			});
			$("#jumpTo").click(function(){
				var jumpTo = $("#jumpToText").val()*1 ;
				var allPage = $("#allPage").val()*1 ;
				var jumpToText = /^(\d)+$/ ;
				if(jumpToText.test($("#jumpToText").val())){
					if(jumpTo<1||jumpTo>allPage){
					}else{
						$("#currPage").val(jumpTo);
						goSubmit();
					}
				}
			});
			
			$(":button[name='showButton']").click(function(){
				var name = $(this).attr("value");
				$("#currPage").val(name*1);
				goSubmit();
			});
			
		});
		function goSubmit(){
			$("#pagingForm").submit() ;
		}
	</script>
	
	<!-- 
  	　　******这个是分页jsp，在使用这个页面时，可以直接将页面引入，以下两个是必要的分页参数，
  	引入时必须设置值（如果使用了PagingUttil.java这个类可以不配置这两个参数，PagingUtil.java会在下面说明）
  	******
	  	1,currPage 是当前的页面为第几页
	  	2,allPage 是总共的页数
  	------------以下引入时可以自定义---------------
	  	3.action 是分页栏的form表单的提交路径   （默认：为空）
	  	4.align 是这个分页栏在的位子有：center,right,left 可选  （默认：center）
	  	5.serch 是上面的搜索框，有：false(不出现），true（出现）  （默认：false）
	  	6.serchAlign 是上面的搜索框在分页栏出现的位子有：center,right,left 可选   （默认：center）
	  	7.colspan 是分页栏跨多少列  （默认：5）
	  	8.color 是分页栏的颜色    （默认：white）
	  	9.width 是分页栏的宽度   （默认：600）
	  	10.height 是分页栏的高度  （默认：30）
	  	11.hidden1 是给的隐藏域有查询关键字时使用
　　		12.hidden2 同上（提供两个隐藏域）
		13.showNumber是否显示中间的选择按钮  false:不显示 true:显示（默认：false）
		14.buttonColor按钮的背景颜色 （默认：白色）
	    15.buttonFontColor按钮上面的字颜色（默认：黑色）
	    16.buttonCheckColor中间数字按钮被选中时的背景颜色（默认：灰色）
  	------------以下是其他页面引入方式---------------
   -->
   <% 
   	   String action = "" ;
   	   String align = "center" ;
	   String serch = "false" ;
	   String serchAlign = "center" ;
	   String colspan = "5" ;
	   String color = "white" ;
	   String width = "600" ;
	   String height = "30" ;
	   String hidden1 = "hidden1" ;
	   String hidden2 = "hidden2" ;
	   String showNumber = "false" ;
	   String buttonColor = "white" ;
	   String buttonFontColor = "black" ;
	   String buttonCheckColor = "gray" ;
	   
	   if(request.getParameter("action")!=null){
	   	action = (String)request.getParameter("action") ;
	   }
	   if(request.getParameter("align")!=null){
	   	align = (String)request.getParameter("align") ;
	   }
	   if(request.getParameter("serch")!=null){
	   	serch = (String)request.getParameter("serch") ;
	   }
	   if(request.getParameter("serchAlign")!=null){
	   	serchAlign = (String)request.getParameter("serchAlign") ;
	   }
	   if(request.getParameter("colspan")!=null){
	   	colspan = (String)request.getParameter("colspan") ;
	   }
	   if(request.getParameter("color")!=null){
	   	color = (String)request.getParameter("color") ;
	   }
	   if(request.getParameter("width")!=null){
	   	width = (String)request.getParameter("width") ;
	   }
	   if(request.getParameter("height")!=null){
	   	height = (String)request.getParameter("height") ;
	   }
	   if(request.getParameter("hidden1")!=null){
	   	hidden1 = (String)request.getParameter("hidden1") ;
	   }
	   if(request.getParameter("hidden2")!=null){
	   	hidden2 = (String)request.getParameter("hidden2") ;
	   }
	   if(request.getParameter("showNumber")!=null){
	   	showNumber = (String)request.getParameter("showNumber") ;
	   }
	   if(request.getParameter("buttonColor")!=null){
	   	buttonColor = (String)request.getParameter("buttonColor") ;
	   }
	   if(request.getParameter("buttonFontColor")!=null){
	   	buttonFontColor = (String)request.getParameter("buttonFontColor") ;
	   }
	   if(request.getParameter("buttonCheckColor")!=null){
	   	buttonCheckColor = (String)request.getParameter("buttonCheckColor") ;
	   }
   	
   %>
	
	
	<style type="text/css" >
		.button {
		font-family: Verdana, Arial, sans-serif;
                display: inline-block;
                background: <%=buttonColor%>  top left repeat-x !important;
                border: 1px solid #DCDCDC !important;
                padding: 4px 7px 4px 7px !important;
                color: <%=buttonFontColor%> !important;
                font-size: 11px !important;
                cursor: pointer;
                }  
                
        #buttonCheck{
			background: <%=buttonCheckColor%>  top left repeat-x !important;
		}
	</style>
	

  </head>
  
  <body>

   	<center>
   	<form action="<%=basePathPaging%><%=action%>" id="pagingForm" method="post">
   	<input type="hidden" name="<%=hidden1 %>" value="<%=request.getParameter(hidden1)==null?"":request.getParameter(hidden1)%>"/>
   	<input type="hidden" name="<%=hidden2 %>" value="<%=request.getParameter(hidden2)==null?"":request.getParameter(hidden2)%>"/>
   	<table align="<%=align%>" width="<%=width%>" height="<%=height%>">
   		<%if(serch.equals("true")){%>
   		 	<tr>
   		 		<td align="<%=serchAlign%>">
   		 			<input type="text" name="serch" id="serch"/>&nbsp;&nbsp;
   		 			<input type="button" value="搜索" />
   		 			<input type="hidden" id="serch" name="serch" value="<s:property value='serch'/>"/>
   		 		</td>
   		 	</tr>
   		 <%} %>
		<tr bgcolor="<%=color%>">
				<td colspan="<%=colspan%>">
					<input type="hidden" id="currPage" name="currPage" value="<s:property value='currPage'/>"/>
					<input type="hidden" id="allPage" name="allPage" value="<s:property value='allPage'/>"/>
					<center><input class="button" type="button" id="first" value="首页" <s:if test="currPage==1">disabled="disabled"</s:if> />&nbsp;&nbsp;
					<input class="button" type="button" id="up" value="上一页" <s:if test="currPage==1">disabled="disabled"</s:if>/>&nbsp;&nbsp;&nbsp;
					
					<%if(showNumber.equals("true")){%>
					
						<s:if test="allPage<5&&allPage>0">
							<s:iterator  begin="1" end="allPage" status="status">
								<input  type="button" class="button" name="showButton" 
										value="<s:property value="#status.count"/>" 
										<s:if test="currPage==#status.count">id="buttonCheck"</s:if>/>
							</s:iterator>
						</s:if>
						<s:elseif test="allPage>4">
							<s:if test="showNumberButtonList.get(3)==allPage">
							<span class="button">...</span>
							</s:if>
								<s:iterator  value="showNumberButtonList" status="status">
									<input  type="button" class="button" name="showButton"
										 value="<s:property value="showNumberButtonList.get(#status.index)"/>" 
										 <s:if test="currPage==showNumberButtonList.get(#status.index)">id="buttonCheck"</s:if>/>
								</s:iterator>
							<s:if test="showNumberButtonList.get(3)!=allPage">
							<span class="button">...</span>
							</s:if>
						</s:elseif>
					
					<%} %>
					<input class="button" type="button" id="next" value="下一页" <s:if test="currPage==allPage">disabled="disabled"</s:if>/>&nbsp;&nbsp; 
					<input class="button" type="button" id="last" value="尾页" <s:if test="currPage==allPage">disabled="disabled"</s:if>/>&nbsp;&nbsp;
					第<s:property value="currPage"/>页 &nbsp;
					共<s:property value="allPage"/>页
					跳到 <input type="text" id="jumpToText" size="3px"/>
						<input class="button" type="button" id="jumpTo" value="跳转"/>
					</center>
				</td>
			</tr>
   	</table>
   	</form>
   	</center>
  </body>
</html>
