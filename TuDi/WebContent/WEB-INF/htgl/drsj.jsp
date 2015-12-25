<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<title>导入数据</title>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr height="30">
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="middle" class="title">导入数据</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<br>
<br>
<s:form action="sjdr" namespace="/htgl" enctype="multipart/form-data">
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
      <tr height="30">
        <td width="120" height="24" class="td_form01">档案类别：</td>
        <td nowrap class=td_form02><select id="type" name="type">
        <s:iterator id="menu" value="menus" var="menu">
        <option value="${menu.tableName }">${menu.shortName }</option> 
        </s:iterator>
        
        </select> </td>
      </tr>
      
      <tr height="30">
        <td height="24" class="td_form01">条目文件</td>
        <td nowrap class=td_form02><input type="file" name="daFile"> (excel文件)</td>
      </tr>
      <tr height="30">
         <td class="td_form01">目录文件</td>
   		 <td class="td_form02"><input type="file" name="ljFile">(excel文件)</td>
 	 </tr>
 	 <tr height="30">
         <td class="td_form01">影像文件</td>
   		 <td class="td_form02"><input type="file" name="yxFile">(zip文件，不超过500M)</td>
 	 </tr>
</table>
<p align="center"><input type="submit" name="sub" value="上传"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" name="cls" value="清空"/>
</p>
</s:form>
<br>

<br>
</body>
</html>