<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>建设用地</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr height="30">
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">建设用地</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<br>
<br>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
      <tr height="30">
        <td width="120" height="24" class="td_form01">档案号</td>
        <td nowrap class=td_form02>${jsydl.qzh}-${jsydl.mlh}-${jsydl.flh}-${jsydl.ajh}</td>
        <td width="120" class="td_form01">文号</td>
        <td nowrap class=td_form02>${jsydl.wh}</td>
      </tr>
      <tr height="30">
        <td height="24" class="td_form01">转让方</td>
        <td nowrap class=td_form02>${jsydl.zrf}</td>
        <td class="td_form01">受让方</td>
        <td nowrap class=td_form02>${jsydl.srf}</td>
      </tr>
    <tr height="30">
    <td class="td_form01">批复文号</td>
    <td class="td_form02">${jsydl.pfwh}</td>
    <td class="td_form01">申请人</td>
    <td class="td_form02">${jsydl.sqr}</td>
  </tr>
  <tr height="30">
    <td class="td_form01">土地坐落</td>
    <td class="td_form02">${jsydl.tdzl}</td>
    <td class="td_form01">用地单位</td>
    <td class="td_form02">${jsydl.yddw}</td>
  </tr>
  <tr height="30">
    <td class="td_form01">土地使用者</td>
    <td class="td_form02">${jsydl.tdsyz}</td>
    <td class="td_form01">土地证号</td>
    <td class="td_form02">${jsydl.tdzh}</td>
  </tr>
   <tr height="30">
    <td class="td_form01">宗地号</td>
    <td class="td_form02">${jsydl.zdh}</td>
    <td class="td_form01">批准文号</td>
    <td class="td_form02">${jsydl.pzwh}</td>
  </tr>
  <tr height="30">
    <td class="td_form01">用途</td>
    <td class="td_form02"><textarea name="textarea" cols="60" rows="4" readonly="readonly">${jsydl.yt}</textarea></td>
    <td class="td_form01">备注</td>
    <td class="td_form02"><textarea name="textarea3" cols="60" rows="4" readonly="readonly">${jsydl.bz}</textarea></td>
  </tr>
    
</table>
<br>

<br>
</body>
</html>