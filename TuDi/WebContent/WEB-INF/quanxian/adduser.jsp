<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加用户</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script type="text/javascript"  src="../js/date.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>


</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">添加用户</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<s:form method="post" action="adduser" namespace="/quanxian">
		   <s:token></s:token>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">用户编号</td>
    <td class="td_form02"><input id="userid" name="user.id" type="text" class="input" size="30" value="${userid}" readonly="readonly"></td>
    <td class="td_form01">用户姓名</td>
    <td class="td_form02">
    	<input id="username" name="user.userName" type="text" class="input" size="30">
    	
    	<span id="nameInfo" style="color:red">
			<s:property value="#errors['userName']"/>&nbsp;
		</span>
		
    </td>
  </tr>
  
  <tr>
  <td class="td_form01">初始密码</td>
     <td class="td_form02">
     	<input id="password" name="user.password" type="password" class="input" size="30">
     	<span id="nameInfo" style="color:red">
			<s:property value="#errors['password']"/>&nbsp;
		</span>
     </td>
   <td class="td_form01">部门</td>
   	 <td class="td_form02"><input id="department" name="user.department" type="text" class="input" size="30"></td>
     
  </tr>

 
  <tr>
  	 <td class="td_form01">出生年月</td>
     <td class="td_form02"><input id="birthday" name="user.birthday" type="text" class="input" size="30" readonly="readonly" onFocus="CalendarWebControl.show(this,false,this.value);">YYYY-MM-DD</td>
      <td class="td_form01">性别</td>
    <td class="td_form02">
    	<select id="sex" name="user.sex" width="30">
    		<option selected value="男">男</option>
    		<option  value="女">女</option>
    	</select>
    	
    	<span id="nameInfo" style="color:red">
			<s:property value="#errors['sex']"/>&nbsp;
		</span>
    </td>
  </tr>
  <tr>
  	 <td class="td_form01">水印文字</td>
   	 <td class="td_form02">
   	 	<input id="sywz" name="user.sywz" type="text" class="input" size="50">
   	 	<span id="nameInfo" style="color:red">
			<s:property value="#errors['miss_sy']"/>&nbsp;
		</span>
   	 </td>
     <td class="td_form01"></td>
     <td class="td_form02"></td>
  </tr>
</table>
<br>


<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
   <tr height="5">
    <td class="td_form01">赋予角色 </td>
    <td class="td_form02">
    <s:iterator id="d" value="roles" var="role" status="r">
    	<input type="radio" name="roleId" value="${role.roleId}"/>${role.roleName}
    </s:iterator>
   		 <span id="nameInfo" style="color:red">
			<s:property value="#errors['role']"/>&nbsp;
		</span>
    </td>
  </tr>
   
</table>
<br> 
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
	<input name="Submit2" type="submit" class="buttonface" value="  确定  ">
    <input name="Submit" type="reset" class="buttonface" value="  清空  "></td>
  </tr>
</table>
</s:form>
<br>
</body>
</html>