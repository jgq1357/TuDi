<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../js/stm31.js"></script>
<script type="text/javascript">
	function init(currPage,total) {
		if(currPage>=total) {
			document.getElementById("next").disabled = true;
			document.getElementById("last").disabled = true;
		}else {
			document.getElementById("next").disabled = false;
			document.getElementById("last").disabled = false;
		}
		if(currPage>1) {
			document.getElementById("pre").disabled = false;
			document.getElementById("first").disabled = false;
		}else {
			document.getElementById("pre").disabled = true;
			document.getElementById("first").disabled = true;
		}
		document.getElementById("total").innerText=currPage+"/"+total;
		
	}
	
	function dorefresh(pageNum,value) {
		pageNum = pageNum + value;
		window.location.href("updatepage.action?pageNum="+pageNum);
	}
	
	function deleteusers(userid, username) {
		var flag = confirm("确定要删除该用户["+username+"]吗？");
		if(flag==true) {
			var url="../quanxian/deleteuser?t="+new Date().getTime();
			new Ajax.Request(
				url,
				{	method:"post",
					parameters:{"userid":userid},
					onSuccess:function(req){
						var json = req.responseText.evalJSON();
						flag = json.flag;
						if(flag=="Y") {
							alert("删除成功！");
							window.location.href('../quanxian/updatepage');
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

<body onload="init(${pageNum},${total})" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
          <tr>
            <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
            <td valign="bottom" class="title">用户管理</td>
          </tr>
      </table></td>
    </tr>
  </table>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td align="center"><br>
      <s:form action="queryUser" namespace="/quanxian" method="post">
    	<table width="95%"  border="0" cellpadding="0" cellspacing="0">
      	<tr>
        <td class="td_page">姓名：<input name="queryName" type="text" class="input"  size="40">
          
              <input name="simple" type="submit" class="buttonface02" value="  查询  "  >
          	  <input type="button" class="buttonface02" value="所有用户" onclick="window.location.href('../quanxian/updatepage')">
          </td>
        </tr>
      </table>
      </s:form>
        <br>
     
        <table width="95%" border="0" cellpadding="2" cellspacing="0" class="table01">
          <tr>
          	<td class="td_top">序号</td>
            <td class="td_top">姓名</td>
            <td class="td_top">部门</td>
            <td class="td_top">性别</td>
            <td class="td_top">出生日期</td>
            <td class="td_top" align="center">操作</td>
          </tr>
          <s:iterator id="d" value="users" var="user" status="u">
          <tr>
          	<td class="td_01"><s:property value="#u.index+1"></s:property> </td>
            <td class="td_01">${user.userName }</td>
            <td class="td_01">${user.department }</td>
            <td class="td_01">${user.sex }</td>
            <td class="td_01">${user.birthday }</td>
            <td class="td_01">[<a href="../quanxian/updateuserpage?userId=${user.id}">更新用户</a>] [<a href="#" onclick="deleteusers('${user.id}','${user.userName}')">删除用户</a>]</td>
          </tr>
          </s:iterator>
           <tr>
            <td class="td_01">&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_01">&nbsp;</td>
          </tr>
          
          </table>
        </td></tr>
  
</table>
<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
    <td height="30" align="right">
    <img src="../images/1.gif" width="4" height="5" align="absmiddle"> 
    <a href="#" onclick="dorefresh(1,0)" id="first">首页</a>　 
    <img src="../images/2.gif" width="3" height="5" align="absmiddle">
    <a href="#" onclick="dorefresh(${pageNum},-1)" id="pre"> 上一页　</a> 
    <img src="../images/2-2.gif" width="3" height="5" align="absmiddle">
    <a href="#" onclick="dorefresh(${pageNum},1)" id="next"> 下一页</a>　 
    <img src="../images/3.gif" width="4" height="5" align="absmiddle">
    <a href="#" onclick="dorefresh(${total},0)" id="last">末页</a> 　　第 <em id="total">1/1</em> 页 </td>
  </tr>
</table>
</center>
</body>
</html>
