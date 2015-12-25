<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>借阅申请</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">借阅申请</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>

<table align="center" width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="90" height="24" class="td_form01">姓名</td>
        <td nowrap class=td_form02><s:property value="#session['user'].userName"/></td>
        <td width="90" class="td_form01">性别</td>
        <td nowrap class=td_form02><s:property value="#session['user'].sex"/></td>
      </tr>
      <tr>
        <td height="24" class="td_form01">部门</td>
        <td nowrap class=td_form02><s:property value="#session['user'].department"/></td>
        <td class="td_form01">出生日期</td>
        <td nowrap class=td_form02><s:property value="#session['user'].birthday"/></td>
      </tr>
</table>
<s:form method="post" action="request" namespace="/worktime">
		   <s:token></s:token>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  
  <tr>
    <td class="td_form01">申请类别</td>
    <td class="td_form02"><select name="applyPermission">
      <s:iterator value="permissions" var="permission">
			<option value="${permission.id}">${permission.name}</option>
		</s:iterator>
    </select>    </td>
  </tr>
  <tr>
    <td class="td_form01">申请理由<br>
      <br>
     <br></td>
    <td class="td_form02"><textarea name="description" cols="80" rows="5"></textarea>
      <br>
      </td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="Submit2" type="submit" class="buttonface" value="  提交  "/>
    <input name="Submit" type="reset" class="buttonface" value="  清空  " ></td>
  </tr>
</table>
 </s:form>
<br>
</body>
</html>