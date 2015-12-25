<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<script language="javascript">
function windowOpen(theURL,winName,features,width,hight,scrollbars,top,left) 
{
  var parameter="top="+top+",left="+left+",width="+width+",height="+hight;
  if(scrollbars=="no")
 {parameter+=",scrollbars=no";}
  else
 {parameter+=",scrollbars=yes";}
  window.open(theURL,winName,parameter);
}


function deleteroles(roleid,rolename) {
	var flag = confirm("确定要删除该角色["+rolename+"]吗？");
	if(flag==true) {
		var url="../quanxian/deleterole?t="+new Date().getTime();
		new Ajax.Request(
			url,
			{	method:"post",
				parameters:{"roleId":roleid},
				onSuccess:function(req){
					var json = req.responseText.evalJSON();
					flag = json.flag;
					if(flag=="Y") {
						alert("删除成功！");
						window.location.href('../quanxian/rolegl');
					}else {
						alert("删除失败！");
					}
				}
			}
			);
	}
}

</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
          <tr>
            <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
            <td valign="bottom" class="title">角色管理</td>
          </tr>
      </table></td>
    </tr>
  </table>
  <br>
  <table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="center" class="td_page"><input name="Submit" type="button" class="buttonface02" onClick="windowOpen('../quanxian/addrolepage','','','600','500','no','150','80')" value="  新建角色 ">
        </td>
    </tr>
  </table>
  <form name="form1" method="post" action="">
    <table width="95%"  border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td class="td_title"> 当前用户所建角色列表 </td>
      </tr>
    </table>
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
    <tr>
      <td  align="center" class="td_top"></td>
      <td  class="td_top">角色名称</td>
      <td  class="td_top">创建时间 </td>
      <td  class="td_top">操作</td>
    </tr>
	<s:iterator id="jsgl" value="roles" var="role">
    <tr>
      <td height="19" align="center" class="td_01"></td>
      <td class="td_01"><a href="#">${role.roleName}</a></td>
      <td class="td_01">${role.createTime}</td>
      <td class="td_01">[<a href="../quanxian/roleuserlist?roleId=${role.roleId}">查看用户</a>] [<a href="#" onclick="deleteroles('${role.roleId}','${role.roleName}')">删除角色</a>]</td>
    </tr>
    </s:iterator>
    
  </table>
  </form>
</center>
</body>
</html>
