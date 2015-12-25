<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新用户</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<script>
			function moveTo(id1,id2){
				var obj1 = document.getElementById(id1);
				var obj2 = document.getElementById(id2);
				//for(i=0;i<obj1.options.length;i++){
				var flag = false;
				for(i=obj1.options.length-1;i>=0;i--){
					var curr = obj1.options[i];
					if(curr.selected){
						flag = true;
						//如果该选项被用户选择了，先移动到第二个多选框，
						//然后删除该选项。
						var v1 = curr.value;
						var txt1 = curr.text;
						var op1 = new Option(txt1,v1);
						op1.selected = true;
						obj2.options[obj2.options.length] = op1;
						obj1.options[i] = null;
					}
				}
				if(!flag){
					alert('至少选择一个选项');
				}
			}
			
			function moveAllTo(id1,id2){
				var obj1 = document.getElementById(id1);
				var obj2 = document.getElementById(id2);
				for(i=obj1.options.length-1;i>=0;i--){
						var curr = obj1.options[i];
						var v1 = curr.value;
						var txt1 = curr.text;
						var op1 = new Option(txt1,v1);
						obj2.options[obj2.options.length] = op1;
						obj1.options[i] = null;
				}
			}

			function submitUpdate() {
				var userid=document.getElementById("userid").value;
				var pers="";
				var obj1 = document.getElementById("s1");
				for(i=obj1.options.length-1;i>=0;i--){
					var curr = obj1.options[i];
					pers = pers+curr.value;
					if(i!=0) {
						pers = pers+",";
					}
				}

				var url="../quanxian/updateuser?t="+new Date().getTime();
				new Ajax.Request(
					url,
					{	method:"post",
						parameters:{"userId":userid,"pers":pers},
						onSuccess:function(req){
							var json = req.responseText.evalJSON();
							flag = json.flag;
							if(flag=="Y") {
								alert("更新成功！");
								window.location.href('../quanxian/updatepage');
							}else {
								alert("更新失败！");
							}
						}
					}
					);

			}
</script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="../images/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">更新用户</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>

<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">用户编号</td>
    <td class="td_form02"><input id="userid" type="text" class="input" size="30" value="${user.id}" readonly="readonly"></td>
    <td class="td_form01">用户姓名</td>
    <td class="td_form02">
    	<input type="text" class="input" size="30" value="${user.userName}" readonly="readonly">
    </td>
  </tr>
  
  
  <tr>
  <td class="td_form01">部门</td>
     <td class="td_form02">
     	<input type="text" class="input" size="30" value="${user.department}" readonly="readonly">
     </td>
    <td class="td_form01">性别</td>
    <td class="td_form02">
    	<input type="text" class="input" size="30" value="${user.sex}" readonly="readonly">
    </td>
  </tr>

 
  <tr>
  	 <td class="td_form01">出生年月</td>
   	 <td class="td_form02"><input type="text" class="input" size="30" value="${user.birthday}" readonly="readonly"></td>
     <td class="td_form01"></td>
     <td class="td_form02"> </td>
  </tr>
</table>
<br>
<div style="background-color: #C3DAF2; width: 95%; margin-left: 3%;" >
		<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr align="center">
				<td align="center">已授予权限</td>
				<td align="center"></td>
				<td align="center">未授予权限</td>
			</tr>
			<tr align="center">
				<td align="center">
					<select name="existPermissions" id="s1" style="width:150px;height:180px;" multiple="multiple" ondblclick="moveTo('s1','s2');">
						<s:iterator value="existPermissions" var="ep">
						<option value="${ep.id}">${ep.name}</option>
						</s:iterator>
					</select>
				</td>
				<td align="center">
					<p>
					<input type="button" value="--&gt;" style="width:100px;" onclick="moveTo('s1','s2');"/>
					</p>
					<p>
					<input type="button" value="--&gt;&gt;" style="width:100px;" onclick="moveAllTo('s1','s2');"/>
					</p>
					<p>
					<input type="button" value="&lt;--" style="width:100px;" onclick="moveTo('s2','s1');"/>
					</p>
					<p>
					<input type="button" value="&lt;&lt;--" style="width:100px;" onclick="moveAllTo('s2','s1');"/>
					</p>
				</td>
				<td align="center">
					<select name="s2" id="s2" style="width:150px;height:180px;" multiple="multiple" ondblclick="moveTo('s2','s1');">
						<s:iterator value="noPermissions" var="np">
							<option value="${np.id}">${np.name}</option>
						</s:iterator>
					</select>
				</td>
			</tr>
		</table>
</div>
<br>
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
	<input name="Submit2" type="button" class="buttonface" value="  确定  " onclick="submitUpdate()">
    <input name="Submit" type="button" class="buttonface" value="  返回  " onclick="window.location.href('../quanxian/updatepage')"></td>
  </tr>
</table>

<br>
</body>
</html>