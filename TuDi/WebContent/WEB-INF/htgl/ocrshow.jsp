<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<title>OCR识别</title>
</head>
<body bgcolor="#ECF5FF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr height="30">
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="middle" class="title">OCR识别</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<br>
<br>
<div align="center">
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0" style="left: 10%;">
  
 	 <tr align="center">
         <td align="center"><div align="center" style="width: 50%; height: 60%;"><img alt="" src="../htgl/image?yx=${yx}"> </div> </td>
   		 <td align="center"><div align="center" style="width: 50%; height: 60%;"> <textarea rows="42" cols="72" style="text-align: center;">${parseResult}</textarea> </div></td>
 	 </tr>
</table>


</div>
<br>

<br>
</body>
</html>