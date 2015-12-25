<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成果审核</title>
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
        <td valign="bottom" class="title">借阅审批</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">申请人编号</td>
    <td class="td_form02">${approve.userId}</td>
    <td class="td_form01">申请人姓名</td>
    <td class="td_form02">${approve.userName}</td>
  </tr>
  
  
  <tr>
    <td class="td_form01">申请人部门</td>
    <td class="td_form02">${approve.department}</td>
    <td class="td_form01">申请时间</td>
    <td class="td_form02">${approve.applyTime}</td>
  </tr>

 
  <tr>
    <td class="td_form01">申请权限</td>
     <td class="td_form02">${approve.perName}</td>
     <td class="td_form01">申请描述</td>
     <td class="td_form02"><textarea name="textarea2" cols="40" rows="4" readonly="readonly">${approve.desc}</textarea></td>
  </tr>
</table>
<br>
<s:form method="post" action="approve" namespace="/worktime">
		   <s:token></s:token>
<input name="uid" value="${approve.userId}" type="text" style="display: none;">
<input name="pid" value="${approve.perId}" type="text" style="display: none;">
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
   <tr>
    <td class="td_form01">审核结果</td>
    <td class="td_form02"><select name="approveResult">
      <option value="Y">同意</option>
      <option value="N">不同意</option>
    </select></td>
  </tr>
   <tr>
    <td class="td_form01"> 审核意见</td>
    <td class="td_form02"><textarea name="textarea4" cols="40" rows="4"></textarea></td>
  </tr>
</table>
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