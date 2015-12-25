<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统配置</title>
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
        <td valign="bottom" class="title">系统配置</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<s:form method="post" action="updateSysParams" namespace="/quanxian">
		   <s:token></s:token>
		   <br>
		   <br>
		   <br>
		 
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">显示日志行数</td>
    <td class="td_form02"><input id="sysid" name="sys.rowsOfLog" type="text" class="input" size="30" value="${sys.rowsOfLog}" ></td>
    <td class="td_form01">显示搜索结果行数</td>
    <td class="td_form02">
    	<input id="username" name="sys.rowsOfSearch" type="text" class="input" size="30" value="${sys.rowsOfSearch}">
    </td>
  </tr>

  
  <tr>
  <td class="td_form01">日志级别</td>
     <td class="td_form02">
     	<input id="password" name="sys.logLevel" type="text" class="input" size="30" value="${sys.logLevel}">
     	
     </td>
    <td class="td_form01">日志路径</td>
    <td class="td_form02">
    	<input id="sex" name="sys.logPath" type="text" class="input" size="30" value="${sys.logPath}">
    	
    </td>
  </tr>

 
  
</table>
<br>

<input id="update" name="update" type="text" class="input" size="30" value="Y" style="display: none;">
    	

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