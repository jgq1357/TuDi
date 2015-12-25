<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加小类</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/clientSideApp.js"></script>
<script type="text/javascript"  src="../js/date.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/win_center.js"></script>
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script type="text/javascript">
function refresh(val) {
	var tb = document.getElementById("ftb"); 
	var rnum=tb.rows.length;

	for(var i=rnum-1;i>=0;i--) {
		tb.deleteRow(i);
	}

	var url="../htgl/smallpage?t="+new Date().getTime();

	   new Ajax.Request(
				url,
				{	method:"post",
					parameters:{"lmId":val,"isJson":"Y"},
					onSuccess:function(req){
						var json = req.responseText.evalJSON();
						fields = json.fieldStr;
						if(fields!="") {
							cons=fields.split("|");
							for(var i=0;i<cons.length-1;i++) {
								field=cons[i].split(",");
								 var row = tb.insertRow();
								var cell = row.insertCell();
								cell.innerHTML = "<input type='checkbox' name='fields' value = '"+field[0]+"'>"+field[1];
							}
						}
					}
				}
				); 
	     
}

function collectParams() {
	var name=document.getElementById("name").value;
	var lbh=document.getElementById("lbh").value;
	var lmId=document.getElementById("lmId").value;
	var fields="";
	var conditions="";
	var obj1 = document.getElementsByName("fields");
	
	for(i=obj1.length-1;i>=0;i--){
		var curr = obj1[i];
		if(curr.checked==true) {
			fields = fields+curr.value;
			if(i!=0) {
				fields = fields+",";
			}
		}
		
	}

	
	var url="../htgl/addsmallclass?t="+new Date().getTime();
	new Ajax.Request(
		url,
		{	method:"post",
			parameters:{"name":name,"lbh":lbh,"lmId":lmId,"fields":fields},
			onSuccess:function(req){
				var json = req.responseText.evalJSON();
				vaild = json.vaild;
				if(vaild=="") {
					alert("小类创建成功");
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
        <td valign="bottom" class="title">添加小类</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td class="td_form01">* 名称</td>
    <td class="td_form02"><input id="name" type="text" class="input" size="30"></td>
    <td class="td_form01">* 类别号</td>
    <td class="td_form02">
    	<input id="lbh" type="text" class="input" size="30" value="${lbh }" readonly="readonly">
    </td>
  </tr>

  
  <tr>
  <td class="td_form01">所属档案</td>
     <td class="td_form02">
     	<select id="lmId" name="daname" onchange="refresh(this.options[this.options.selectedIndex].value)">
        <s:iterator id="menu" value="danames" var="d">
        <option value="${d.lmId }">${d.name }</option> 
        </s:iterator>
        
        </select>
     </td>
    <td class="td_form01"></td>
    <td class="td_form02">
    	
    </td>
  </tr>

 
  <tr>
  	 <td class="td_form01">选择搜索条件</td>
     <td class="td_form02">
     	<div style="height: 20px;">
     		<div style="width: 50%;float: left;" align="left">选择字段</div>
     	</div>
	     	<div style="height: 500px;overflow: scroll">
	     	<table id="ftb">
		     <s:iterator value="existFields" var="field" status="r">
		    	<tr> <td> <input type="checkbox" name="fields" value="${field.alpha}" />${field.name}</td> </tr>
		    </s:iterator>
		    </table>
		     </div>
		    
	  </td>
     <td class="td_form01">
     </td>
   	 <td class="td_form02">
   	 	
   	 </td>
     
  </tr>
  
</table>
<br>



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