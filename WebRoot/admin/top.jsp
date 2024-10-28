<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<STYLE type=text/css> 
	*{
		FONT-SIZE: 12px; COLOR: white
	}
	#logo {
		COLOR: white
	}
	#logo A {
		COLOR: white
	}
	FORM {
		MARGIN: 0px
	}
	</STYLE>
	<SCRIPT src="<%=path %>/js/Clock.js" type=text/javascript></SCRIPT>
    <script type="text/javascript">
       function out()
       {
           //javascript:window.opener=null;
           //window.parent.close();
           window.parent.location.href="<%=path %>/login.jsp";
       }
    </script>
  </head>
  
  <BODY leftmargin="0" topmargin="0">
        <DIV style="BACKGROUND-IMAGE: url(<%=path %>/img/logo.png); HEIGHT: 80px">
	       <TABLE cellSpacing=0 cellPadding=0 width="100%">
	          <TR>
	            <TD align=left width="30%">
	               	<font style="font-size: 30px;color: white;font-weight: bolder;">
	               	    <br/>
	               	    &nbsp;
	               	     企业员工信息管理系统
	               	</font>
	            </TD>
	            <TD align=right width="70%">
	                 <br/>
		             <SPAN style="PADDING-RIGHT: 10px;">
		                 <font style="font-size: 20px;">
		                 <c:if test="${sessionScope.userType==0}">
						      欢迎您：系统管理员
						 </c:if>
						 <c:if test="${sessionScope.userType==1}">
						      欢迎您：${sessionScope.yuangong.name }
						 </c:if>
						 </font>
			             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			             <SPAN id=clock style="font-size: 20px;"></SPAN>
			             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			             <a href="#" onclick="out()" style="text-decoration: none" style="font-size: 20px;">退出系统</A> 
			         </SPAN>
	            </TD>
	          </TR>
	       </TABLE>
        <DIV>
	    <SCRIPT type=text/javascript>
	       var clock = new Clock();clock.display(document.getElementById("clock"));
	    </SCRIPT>
</BODY>
</html>
