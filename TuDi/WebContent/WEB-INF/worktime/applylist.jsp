<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的申请</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<SCRIPT language=JavaScript type=text/JavaScript>
function windowOpen(theURL,winName,features,width,hight,scrollbars,top,left) 
{
  var parameter="top="+top+",left="+left+",width="+width+",height="+hight;
  if(scrollbars=="no")
 {parameter+=",scrollbars=no";}
  else
 {parameter+=",scrollbars=yes";}
  window.open(theURL,winName,parameter);
}
</SCRIPT>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">我的申请</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>

<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td nowrap class="td_top">申请类别</td>
    <td nowrap class="td_top">申请时间</td>
    <td nowrap class="td_top">是否通过</td>
    <td nowrap class="td_top">审批人</td>
    <td nowrap class="td_top">审批时间</td>

  </tr>
  <tr> 
  	<s:iterator value="applies" var="apply">
			<td class="td_01">${apply.applyType}</td>
			<td class="td_01">${apply.applyTime}</td>
			<td class="td_01">${apply.isPass}</td>
			<td class="td_01">${apply.approveBy}</td>
			<td class="td_01">${apply.approveTime}</td>
	</s:iterator>
    
  </tr>
  <tr> 
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>

  </tr>
  

</table>
<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
    <td height="30" align="right"><img src="../images/1.gif" width="4" height="5" align="absmiddle"> 首页　 <img src="../images/2.gif" width="3" height="5" align="absmiddle"> 上一页　 <img src="../images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页　 <img src="../images/3.gif" width="4" height="5" align="absmiddle"> 末页　　共 1 页 1 条记录</td>
  </tr>
</table>
</body>
</html>
