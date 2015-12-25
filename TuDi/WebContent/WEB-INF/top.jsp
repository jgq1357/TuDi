<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script type="text/javascript"> 


function refresh() { 
var url="../worktime/tixingJson!countRemindNum.action?t="+new Date().getTime();
       new Ajax.Request(
				url,
				{	method:"post",
					onSuccess:function(req){
						var json = req.responseText.evalJSON();
						var g_numOfRemind = json.numOfRemind;
						
						if(g_numOfRemind==0) {
							document.getElementById("remind").innerText='提醒';
						}else {
							document.getElementById("remind").innerText='提醒('+g_numOfRemind+')';
						}
					}
				}
				);
			
} 
function init() {
setInterval('refresh()',60000); 

}

</script>

</head>

<body onload="init()">
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/index_02.gif">
  <tr>
    <td width="500" valign="middle"><font size="5" color="white">&nbsp;&nbsp;&nbsp;国土资源局档案数字化管理系统</font></td>
    <td align="right" valign="bottom"><table border="0" cellspacing="7" cellpadding="0">
      <tr>
        
        <td width="50" valign="bottom"><img src="../images/index_09.gif" width="20" height="16" align="absbottom"><a href="../main/presearch?daType=3" target="mainFrame" class="a02">首页</a></td>
        <td width="50" valign="bottom"><img src="../images/index_11.gif" width="20" height="16" align="absbottom"><a href="../user/logout" target="_parent" class="a02">退出</a></td>
        </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="8"><img src="../images/spacer.gif" width="1" height="1"></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/index_21.gif">
  <tr>
    <td width="20" height="32">&nbsp;</td>
    <td class="F02">
    <img src="../images/index_23.gif" width="26" height="32" align="absmiddle"/>
    <s:property value="#session['user'].userName"/>&nbsp;先生/女士，您好！ </td>
    <td align="right" class="F02"><a href="../worktime/tixing" target="mainFrame" class="a01" id="remind"> 提醒 </a> |  
    <a href="../worktime/cpassword" target="mainFrame" class="a01">修改密码</a>
    </td>
    <td width="14" class="F02"></td>
  </tr>
</table>

</body>
</html>
