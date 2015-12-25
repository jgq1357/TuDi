<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加大类</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script type="text/javascript"  src="../js/date.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script type="text/javascript">
function addRow() {
 var tb = document.getElementById("mytableid");
 var rnum = tb.rows.length+1;
 
 var row = tb.insertRow();
 var cell = row.insertCell();
 cell.innerHTML = "<input type='text' name = 'fieldname'>";
 cell = row.insertCell();
 cell.innerHTML = "<input type='text' name = 'alphaname'>"; 
 cell = row.insertCell();
 cell.align='center';
 cell.innerHTML = "<input type='checkbox' name='condition2'/>";
}
function delRow() {
	 var tb = document.getElementById("mytableid");

	 var rnum=tb.rows.length;

	 if (rnum==0)
	 {
	  alert("没有删除的行");
	  return null;
	 }
	 
	  
	  tb.deleteRow(rnum-1);
	 
	}

function collectParams() {
	var flag = confirm("确定创建该类档案吗？");
	if(flag==false) {
		return;
	}
	var daname=document.getElementById("daname").value;
	var daAlpha=document.getElementById("daalpha").value;
	var subname=document.getElementById("subname").value;
	var fields="";
	var conditions="";
	var obj1 = document.getElementsByName("fields");
	var obj2 = document.getElementsByName("condition");
	for(i=obj1.length-1;i>=0;i--){
		var curr = obj1[i];
		if(curr.checked==true) {
			fields = fields+curr.value;
			if(i!=0) {
				fields = fields+",";
			}
		}
		
	}

	for(i=obj2.length-1;i>=0;i--){
		var con = obj2[i];
		if(con.checked==true) {
			conditions = conditions+con.value;
			if(i!=0) {
				conditions = conditions+",";
			}
		}
		
	}
	var fieldName="";
	var alphaName="";
	var conditions2="";
	var obj3 = document.getElementsByName("fieldname");
	var obj4 = document.getElementsByName("alphaname");
	var obj5 = document.getElementsByName("condition2");
	for(i=obj3.length-1;i>=0;i--){
		var fi = obj3[i];
		var al = obj4[i];
		var co = obj5[i];

		if(fi.value=="" && al.value=="") {
			continue;
		}
		if((fi.value!="" && al.value=="") || (fi.value=="" && al!="")) {
			alert("字段名和字段字母必须匹配！");
			return;
		}
		fieldName=fieldName+fi.value;
		alphaName=alphaName+al.value;
		if(co.checked==true) {
			conditions2=conditions2+al.value;
		}
		if(i!=0) {
			fieldName = fieldName+",";
			alphaName = alphaName+",";
			conditions2 = conditions2+",";
		}
	}

	var roles="";
	var obj6 = document.getElementsByName("roles");
	for(i=obj6.length-1;i>=0;i--){
		var ro = obj6[i];
		if(ro.checked==true) {
			roles = roles+ro.value;
			if(i!=0) {
				roles = roles+",";
			}
		}
		
	}
	
	var url="../htgl/addbigclass?t="+new Date().getTime();
	new Ajax.Request(
		url,
		{	method:"post",
			parameters:{"daName":daname,"daAlpha":daAlpha,"subName":subname,"fields":fields,"searchFields":conditions,"fieldNames":fieldName,"alphaNames":alphaName,"searchFields2":conditions2,"roles":roles},
			onSuccess:function(req){
				var json = req.responseText.evalJSON();
				vaild = json.vaild;
				if(vaild=="") {
					alert("档案创建成功");
					window.parent.location.href('../user/login?t='+new Date().getTime());
					
					
				}else {
					alert(vaild);
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
        <td valign="bottom" class="title">添加大类</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">* 档案名称</td>
    <td class="td_form02"><input id="daname" type="text" class="input" size="30"></td>
    <td class="td_form01">* 数据库表名(字母)</td>
    <td class="td_form02">
    	<input id="daalpha" type="text" class="input" size="30">
    </td>
  </tr>

  
  <tr>
  <td class="td_form01">档案别名</td>
     <td class="td_form02">
     	<input id="subname" type="text" class="input" size="30">
     </td>
    <td class="td_form01"></td>
    <td class="td_form02">
    	
    </td>
  </tr>

 
  <tr>
  	 <td class="td_form01">选择已有表字段</td>
     <td class="td_form02">
     	<div style="height: 20px;">
     		<div style="width: 50%;float: left;" align="left">选择字段</div>
     		<div style="width: 45%;float: left;" align="left">是否为搜索条件</div>
     	</div>
	     	<div style="height: 500px;overflow: scroll">
	     	 <div style="width: 70%;float: left;" align="left">
		     <s:iterator value="existFields" var="field" status="r">
		    	<input type="checkbox" name="fields" value="${field.alpha}"/>${field.name}
		    	
		    	<br>
		    </s:iterator>
		    </div>
		    <div style="width: 25%;float: left;" align="left">
		    <s:iterator id="d2" value="existFields" var="field" status="r">
		    	<input type="checkbox" name="condition" value="${field.alpha}"/>
		    	<br>
		    </s:iterator>
		    </div>
		     </div>
		    
	  </td>
     <td class="td_form01">如没有，添加表字段
     <br><input type="button" value=" 新增一行  " onclick="addRow()"/>
     <br><br><input type="button" value="删除最后一行" onclick="delRow()"/>
     </td>
   	 <td class="td_form02">
   	 	<div style="height: 20px;">
     		<div style="width: 35%;float: left;" align="left">字段名称(中文)</div>
     		<div style="width: 35%;float: left;" align="center">字段简称(字母)</div>
     		<div style="width: 20%;float: left;" align="center">是否为搜索条件</div>
     	</div>
     	<div style="height: 500px;overflow: scroll">
     		<table id="mytableid">
     			<tr>
     			<td><input type="text" name="fieldname" value=""/> </td>
     			<td><input type="text" name="alphaname" value=""/></td>
     			<td align="right"><input style="float: right" type="checkbox" name="condition2"/> </td> 
     			</tr>
     			<tr>
     			<td><input type="text" name="fieldname" value=""/> </td>
     			<td><input type="text" name="alphaname" value=""/></td>
     			<td align="right"><input style="float: right" type="checkbox" name="condition2"/> </td> 
     			</tr>
     			<tr>
     			<td><input type="text" name="fieldname" value=""/> </td>
     			<td><input type="text" name="alphaname" value=""/></td>
     			<td align="right"><input style="float: right" type="checkbox" name="condition2"/> </td> 
     			</tr>
     		</table>
     	</div>
   	 </td>
     
  </tr>
  
</table>
<br>


<table width="95%" border="0" bordercolor="red" align="center" cellspacing="0" cellpadding="0">
	<tr height="5">
    <td class="td_form02">
   		 <s:checkboxlist name="roles" id="roles" list="roles" listKey="roleId" listValue="roleName" label="赋予角色浏览权限"></s:checkboxlist>
    </td>
  </tr>
</table>
<br> 
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
	<input name="Submit2" type="button" class="buttonface" value="  确定  " onclick="collectParams();">
    <input name="Submit" type="reset" class="buttonface" value="  清空  "></td>
  </tr>
</table>

<br>
</body>
</html>