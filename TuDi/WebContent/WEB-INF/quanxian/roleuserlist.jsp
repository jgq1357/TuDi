<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script language="javascript">

</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<center>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
          <tr>
            <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
            <td valign="bottom" class="title">角色管理</td>
          </tr>
      </table></td>
    </tr>
  </table>
  <br>
  <table width="95%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="right" class="td_page"><input name="Submit" type="button" class="buttonface02" onClick="location.href='../quanxian/rolegl'" value="  返回  "></td>
    </tr>
  </table>
  <br>
  <table width="95%" border="0" cellspacing="2" cellpadding="0">
    <tr>
      <td class="td_title">分配了${roleName}角色的所有用户</td>
    </tr>
  </table>
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
	<tr>
	  <td nowrap class="td_top">姓名 </td>
      <td nowrap class="td_top">部门</td>
      <td nowrap class="td_top">性别</td>
      <td nowrap class="td_top">出生年月</td>
    </tr>
	<s:iterator id="userinfo" value="users" var="user">
    <tr>
	  <td nowrap class="td_01">${user.userName} </td>
      <td nowrap class="td_01">${user.department}</td>
      <td nowrap class="td_01">${user.sex}</td>
      <td nowrap class="td_01">${user.birthday}</td>
    </tr>
    </s:iterator>
    
  </table>
</center>
</body>
</html>
