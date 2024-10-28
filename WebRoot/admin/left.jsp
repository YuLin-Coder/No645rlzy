<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		{
			FONT-SIZE: 12px
		}
		#menuTree A {
			COLOR: #566984; TEXT-DECORATION: none
		}
		</STYLE>

		<SCRIPT src="<%=path%>/js/TreeNode.js" type=text/javascript></SCRIPT>
		<SCRIPT src="<%=path%>/js/Tree.js" type=text/javascript></SCRIPT>
	</head>

	<BODY style="BACKGROUND-POSITION-Y: -120px;  BACKGROUND-REPEAT: repeat-x" topmargin="5">
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%">
				<TR>
					<TD width=10 height=29>
						<IMG src="<%=path %>/img/bg_left_tl.gif">
					</TD>
					<TD
						style="FONT-SIZE: 18px; BACKGROUND-IMAGE: url(<%=path %>/img/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
						Main Menu
					</TD>
					<TD width=10>
						<IMG src="<%=path %>/img/bg_left_tr.gif">
					</TD>
				</TR>
				<TR>
					<TD style="BACKGROUND-IMAGE: url(<%=path %>/img/bg_left_ls.gif)"></TD>
					<TD id=menuTree style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" vAlign=top></TD>
					<TD style="BACKGROUND-IMAGE: url(<%=path %>/img/bg_left_rs.gif)"></TD>
				</TR>
		</TABLE>
		<SCRIPT type=text/javascript>
                var tree = null;
	            var root = new TreeNode('系统菜单');
	            
	            <c:if test="${sessionScope.userType==0}"> 
	            var fun2 = new TreeNode('密码修改');
	            var fun21 = new TreeNode('密码修改', '<%=path %>/admin/userinfo/userPw.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun2.add(fun21);
	            root.add(fun2);
	            
	            
	            var fun3 = new TreeNode('部门管理');
	            var fun31 = new TreeNode('部门管理', '<%=path %>/org?type=orgMana', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun3.add(fun31);
	            root.add(fun3);
	            
	            var fun4 = new TreeNode('员工管理');
	            var fun41 = new TreeNode('员工管理', '<%=path %>/yuangong?type=yuangongMana', 'tree_node.gif', null, 'tree_node.gif', null);
	            var fun42 = new TreeNode('员工录入', '<%=path %>/admin/yuangong/yuangongAdd.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun4.add(fun41);
	            fun4.add(fun42);
	            root.add(fun4);
	            
	            
	            var fun7 = new TreeNode('出勤管理');
	            var fun71 = new TreeNode('出勤管理', '<%=path %>/chuqin?type=chuqinMana', 'tree_node.gif', null, 'tree_node.gif', null);
	            var fun72 = new TreeNode('出勤录入', '<%=path %>/admin/chuqin/chuqinAdd.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun7.add(fun71);
	            fun7.add(fun72);
	            root.add(fun7); 
	            
	            
	            var fun6 = new TreeNode('工资管理');
	            var fun61 = new TreeNode('工资管理', '<%=path %>/gongzi?type=gongziMana', 'tree_node.gif', null, 'tree_node.gif', null);
	            var fun62 = new TreeNode('工资录入', '<%=path %>/admin/gongzi/gongziAdd.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun6.add(fun61);
	            fun6.add(fun62);
	            root.add(fun6);
	            
	            
	            var fun11 = new TreeNode('请假审核');
	            var fun111 = new TreeNode('请假审核', '<%=path %>/qingjiashenqing?type=qingjiashenqingMana', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun11.add(fun111);
	            root.add(fun11); 
	            
	            
	            </c:if>
	            
	            <c:if test="${sessionScope.userType==1}"> 
	            var fun2 = new TreeNode('密码修改');
	            var fun21 = new TreeNode('密码修改', '<%=path %>/admin/userinfo/userPw_yuangong.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun2.add(fun21);
	            root.add(fun2);
	            
	            
	            
	            var fun3 = new TreeNode('我的请假');
	            var fun31 = new TreeNode('我的请假', '<%=path %>/qingjiashenqing?type=qingjiashenqingMana_me', 'tree_node.gif', null, 'tree_node.gif', null);
	            var fun32 = new TreeNode('请假申请', '<%=path %>/admin/qingjiashenqing/qingjiashenqingAdd.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun3.add(fun31);
	            fun3.add(fun32);
	            root.add(fun3); 
	            
	            var fun7 = new TreeNode('我的工资');
	            var fun71 = new TreeNode('我的工资', '<%=path %>/gongzi?type=gongziMana_me', 'tree_node.gif', null, 'tree_node.gif', null);
	            fun7.add(fun71);
	            root.add(fun7); 
	            
	            
	            </c:if>
	            
	            tree = new Tree(root);
	            tree.show('menuTree')
        </SCRIPT>
	</BODY>
</html>
