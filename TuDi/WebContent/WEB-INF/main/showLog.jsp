<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>国土资源信息查询</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/search-style.css" />
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/sorttable.js"></script>
<script type="text/javascript">
	
	function getLogs(currPage,pageNum) {
		currPage = currPage+pageNum;
		window.location.href("../main/applog?pageNum="+currPage);
		
	}
	function getLog(currPage) {
		window.location.href("../main/applog?pageNum="+currPage);
		
	}
	
	function init(currPage,total) {
		if(currPage>=total) {
			document.getElementById("next").disabled = true;
		}else {
			document.getElementById("next").disabled = false;
		}
		if(currPage>1) {
			document.getElementById("last").disabled = false;
		}else {
			document.getElementById("last").disabled = true;
		}
		document.getElementById("di_"+currPage).selected="selected";
		document.getElementById("gong").innerText=total;
	}	
	

</script>

</head>

<body onload="init(${pageNum},${total})">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" background="../images/main_17.gif"><br>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="../images/main_24.gif" width="97" height="11"></td>
        </tr>
      </table>
      <br>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="5"><img src="../images/main_11.gif" width="5" height="5"></td>
         <td background="../images/main_12.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td width="5"><img src="../images/main_14.gif" width="5" height="5"></td>
       </tr>
       <tr>
         <td background="../images/main_19.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td align="center" class="td01">
		   <table width="98%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="25"><img src="../images/main_28.gif" width="9" height="9" align="absmiddle"> <strong>系统日志</strong></td>
               <td width="35" align="right"></td>
             </tr>
             <tr>
               <td height="1" class="td05"><img src="../images/spacer.gif" width="1" height="1"></td>
               <td class="td05"><img src="../images/spacer.gif" width="1" height="1"></td>
             </tr>
             <tr>
               <td height="5"><img src="../images/spacer.gif" width="1" height="1"></td>
               <td><img src="../images/spacer.gif" width="1" height="1"></td>
             </tr>
           </table>
           <table id = "result_table" class="sortable" width="98%" border="0" cellspacing="0" cellpadding="0" style="border: 1px">
				<tr>
					<th>序号</th>
					<th>登录用户名</th>
					<th>利用方式</th>
					<th>IP</th>
					<th>机器名</th>
					<th>操作时间</th>
					<th>分类</th>
					<th>利用内容</th>
				</tr>
			<tbody>
				<s:iterator id="d" value="applogs" status="applog" var="log">
					<tr onclick="">
						<td style="border: 1px solid black; text-align: center;"><s:property value="#applog.index+1"/></td>
						<td style="border: 1px solid black; text-align: center;">${log.userName}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.ways}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.ip}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.pcName}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.oTime}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.category}&nbsp;</td>
						<td style="border: 1px solid black; text-align: center;">${log.context}&nbsp;</td>
					</tr>
				</s:iterator>
			</tbody>
			</table>
           <br>
		</td>
        <td background="../images/main_21.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
      </tr>
      <tr>
			  	<td background="../images/main_19.gif"></td>
			  	<td align="center">
			  		<input id="last" type="button" value=" 上一页 " onclick="getLogs(${pageNum},-1)"/>
			  		<img src="../images/spacer.gif" width="50" height="1"/>
			  		<input id="next" type="button" value=" 下一页 " onclick="getLogs(${pageNum},1)"/>
			  		<img src="../images/spacer.gif" width="50" height="1"/>
			  		<em style="font-size:15px;">
			  			第&nbsp;
			  			<em id="di">
			  				<select name="pageNum" id="page" onchange="getLog(this.options[this.options.selectedIndex].value)">
			  				<s:iterator value="totals" var="to">
			  					<option value="${to}" id="di_${to}">${to}</option>
			  				</s:iterator>
			  				</select>
			  			</em>
			  			页/共&nbsp;
			  			<em id="gong">0</em>
			  			页 
			  		</em>
			  	</td>
			  	<td align="left" background="../images/main_21.gif"></td>
			  	
		</tr>
      <tr>
        <td><img src="../images/main_34.gif" width="5" height="5"></td>
        <td background="../images/main_35.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
        <td><img src="../images/main_36.gif" width="5" height="5"></td>
      </tr>
    </table>
        <br>
        
        
        
    </td>
    
  </tr>
</table>
</body>
</html>

