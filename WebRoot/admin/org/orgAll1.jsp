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
	    <base target="_self">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<link href="<%=path %>/css/dtree.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="<%=path %>/js/dtree.js" type="text/javascript"></script>
		
		<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
        <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
        <script type='text/javascript' src='<%=path %>/dwr/interface/dwrService.js'> </script>
        
		<title></title>
		<script type="text/javascript">
		    function onClickTreeNode(nodeId)
		    {
		         document.getElementById("img1").style.display="block";
			     dwrService.getForwardHTMLPage(nodeId,callback); 
		    }
		    
		    function callback(data)
		    {
			    //alert(data);
		        // dwr.util.setValue("yuangongByOrg", data, { escapeHtml:false });
		         document.getElementById("yuangongByOrg").innerHTML = data;
		         document.getElementById("img1").style.display="none";
		    }
		    
		    
		    
		    function StringBuffer()
            { 
			   this._strs = new Array; 
			} 
			StringBuffer.prototype.append = function (str) 
			{ 
			   this._strs.push(str); //添加
			} 
			StringBuffer.prototype.pop = function (str) 
			{ 
			   this._strs.pop(str); //删除最后一个
			} 
			StringBuffer.prototype.toString = function()
		    { 
			   return this._strs.join(","); 
			}
            
            function queding()
            {
                 var sb = new StringBuffer(); 
                 
                 var object=document.getElementsByName("yuangong_id");//返回的obeject是数组
				 for(i=0;i<object.length;i++)
				 {
				      if(object[i].checked==true)
					  {
					      sb.append(object[i].value); 
					  }
				 }
				 window.returnValue = sb.toString();
		         window.close();
            }
		</script>
	</head>

	<BODY>
		<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="20%" valign="top" align="left">
                        <div class="dtree">
							<script type="text/javascript">
								d = new dTree('d');
						        d.add(0,-1,'部门组织结构图');//必须有这句
								<c:forEach items="${requestScope.orgList}" var="org">
						            d.add(${org.id},${org.parenOrganization.id},'${org.name}','javaScript:onClickTreeNode(${org.id});',null,null,null,null,'yes');
						        </c:forEach>
								document.write(d);
							</script>
						</div>
                    </td>
				    <td width="80%" valign="top">
				        <div id="yuangongByOrg">
				           <span style="font-size: 18px;">请单击部门选择员工</span>
				        </div>
				        <img id="img1" src="<%=path %>/img/loading.gif" style="display: none"/>
                    </td>
		        </tr>
		</table>
		
		<table width='99%'  border='0'style="margin-top:8px;margin-left: 5px;">
				  <tr>
				    <td>
				      <input type="button" value="确定" style="width: 80px;" onclick="queding()" />
				    </td>
				  </tr>
		</table>
	</body>
</html>
