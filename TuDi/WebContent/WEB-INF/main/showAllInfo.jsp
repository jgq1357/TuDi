<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${shortName }</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr height="30">
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">${shortName } 档案号：${dah }</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<br>
<br>
<table width="95%" border="2" align="center" cellspacing="0" cellpadding="0">
<tr>
	<td width="50%">
		<table width="100%" align="right">
			<s:iterator value="recordField1" status="record">
     			 <tr height="30">
      			  <td width="120" height="24" class="td_form01"><s:property value="key"/></td>
       			  <td nowrap class=td_form02><s:property value="value"/></td>
       			  
      			</tr>
      		</s:iterator>
		</table>
	</td>
	<td width="50%">
		<table width="100%" style="top: 0%" align="left">
			<s:iterator value="recordField2" status="record">
     			 <tr height="30">
      			  <td width="120" height="24" class="td_form01"><s:property value="key"/></td>
       			  <td nowrap class=td_form02><s:property value="value"/></td>
       			  
      			</tr>
      		</s:iterator>
		</table>
	</td>
</tr>
	

</table>
<br>

<br>
</body>
</html>