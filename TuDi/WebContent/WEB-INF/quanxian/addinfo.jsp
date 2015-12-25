<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">添加成功</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">用户编号</td>
    <td class="td_form02">${user.id}</td>
    <td class="td_form01">用户姓名</td>
    <td class="td_form02">${user.userName }</td>
  </tr>
  
  
  <tr>
    <td class="td_form01">部门</td>
    <td class="td_form02">${user.department }</td>
    <td class="td_form01">性别</td>
    <td class="td_form02">${user.sex }</td>
  </tr>


</table>
<br>


<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
	<input name="Submit2" type="button" class="buttonface" value="  确定  " onclick="window.location.href='../quanxian/addpage'">
   </td>
  </tr>
</table>

<br>
</body>
</html>