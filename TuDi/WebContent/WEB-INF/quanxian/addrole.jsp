<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
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

			function submitInsert() {
				var roleName=document.getElementById("rolename").value;
				var desc=document.getElementById("desc").value;
				var pers="";
				var obj1 = document.getElementById("s2");
				for(i=obj1.options.length-1;i>=0;i--){
					var curr = obj1.options[i];
					pers = pers+curr.value;
					if(i!=0) {
						pers = pers+",";
					}
				}

				var url="../quanxian/addrole?t="+new Date().getTime();
				new Ajax.Request(
					url,
					{	method:"post",
						parameters:{"roleName":roleName,"pers":pers,"description":desc},
						onSuccess:function(req){
							var json = req.responseText.evalJSON();
							flag = json.flag;
							if(flag=="Y") {
								alert("插入成功！");
								window.close();
								window.open('../quanxian/rolegl','mainFrame','');
								
							}else {
								alert("插入失败！");
							}
						}
					}
					);

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
            <td valign="bottom" class="title">新建角色</td>
          </tr>
      </table></td>
    </tr>
  </table>

<table width="95%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="90" align="center" class="td_form01">角色名称</td>
    <td colspan="3" class="td_form02"><input name="roleName" id="rolename" type="text" class="input"></td>
  </tr>
  <tr>
    <td align="center" class="td_form01">角色描述</td>
    <td colspan="3" class="td_form02"><textarea id="desc" name="description" cols="60" rows="5"></textarea></td>
    </tr>
</table>

<div style="background-color: #C3DAF2; width: 95%; margin-left: 3%;" >
		<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr align="center">
				<td align="center">所有权限</td>
				<td align="center"></td>
				<td align="center">授予权限</td>
			</tr>
			<tr align="center">
				<td align="center">
					<select name="alltPermissions" id="s1" style="width:150px;height:180px;" multiple="multiple" ondblclick="moveTo('s1','s2');">
						<s:iterator value="allPermissions" var="ep">
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
					<select name="grantPermissions" id="s2" style="width:150px;height:180px;" multiple="multiple" ondblclick="moveTo('s2','s1');">
						
					</select>
				</td>
			</tr>
		</table>
</div>

  <br>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="center"><input name="Submit" type="button" class="buttonface" value="  增加  " onclick="submitInsert()">
        <input name="Submit" type="submit" class="buttonface" onClick="window.close()" value="  关闭  "></td>
    </tr>
  </table>

</center>
</body>
</html>
