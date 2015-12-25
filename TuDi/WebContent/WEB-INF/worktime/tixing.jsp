<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <title>提醒信息</title>
 <link href="../css/style.css" rel="stylesheet" type="text/css">

 </head>

 <body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <center>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
           <tr>
             <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
             <td valign="bottom" class="title">提醒信息</td>
           </tr>
       </table></td>
     </tr>
   </table>
   <table width="760"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="1" class="td01"><img src="../images/spacer.gif" width="1" height="1"></td>
   </tr>
 </table>
   <br>
   <table width="95%"  border="0" cellpadding="5" cellspacing="0" class="table01">
   		<tr>
     		<td class="td_top" width="55" align="center">序号</td>
     		<td width="330" class="td_top">手续名称</td>
     		<td class="td_top">&nbsp;详细信息</td>
     	</tr>
      	<tr>
      		<s:iterator value="processes" var="processe" status="pro">
				<td align="center" class="td07"><s:property value="#pro.index+1"/></td>
				<td class="td07">${processe}</td>
				<td class="td_01"><a href="${link}" target="mainFrame">查看</a></td>
			</s:iterator>
      	</tr>
      	<tr>
      		<td align="center" class="td07">&nbsp;</td>
         	<td class="td07">&nbsp;</td>
		 	<td class="td_01">&nbsp;</td>
      	</tr>
      
 </table>
 <table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
    <td height="30" align="right"><img src="../images/1.gif" width="4" height="5" align="absmiddle"> 首页　 <img src="../images/2.gif" width="3" height="5" align="absmiddle"> 上一页　 <img src="../images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页　 <img src="../images/3.gif" width="4" height="5" align="absmiddle"> 末页　　共 1 页 1 条记录</td>
  </tr>
</table>
 <br>
 </center>
 </body>
</html>