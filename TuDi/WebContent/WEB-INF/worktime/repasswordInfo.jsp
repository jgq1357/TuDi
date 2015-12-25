<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>站内消息</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">密码修改成功</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>


<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
    <td width="130" align="center" class="td_form01" >重新登录，点击[确定]</td>
   </tr>	
   <tr>
    <td width="130" align="center" class="td_form01" >回搜索界面，点击[取消]</td>
   </tr>	
</table>
<br>
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
	<input name="Submit2" type="button" class="buttonface" value="  确定  " onclick="parent.location.href='../user/logout'">
    <input name="Submit" type="button" class="buttonface" value="  取消  " onclick="window.location.href='../user/main'"></td>
  </tr>
</table>

</body>
</html>

