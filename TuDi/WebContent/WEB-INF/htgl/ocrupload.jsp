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
<s:form action="ocr" namespace="/htgl" enctype="multipart/form-data">
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  
 	 <tr height="30">
         <td class="td_form01">影像文件</td>
   		 <td class="td_form02"><input type="file" name="yxFile">(jpg文件)<SPAN style="color:red">${errorMessage }</SPAN> </td>
 	 </tr>
</table>
<p align="center"><input type="submit" name="sub" value="上传"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" name="cls" value="清空"/>
</p>
</s:form>
</div>
<br>

<br>
</body>
</html>