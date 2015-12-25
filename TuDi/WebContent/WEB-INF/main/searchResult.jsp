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
	function showTif(dangAnHao,pageNum,view,dalb){
		if(view=="Y") {
			window.open("showTiff.action?dangAnHao="+dangAnHao+"&pageNum="+pageNum+"&dalb="+dalb);
		}else {
			alert("对不起， 您没有权限浏览档案！");
		}
	}
	
	function getResults(currPage,pageNum) {
		
		var daType=document.getElementById("daType").value;
	
		currPage = currPage+pageNum;
		
		
		window.location.href("../main/search?pageNum="+currPage+"&daType="+daType+"&flag=Y&lbh=1");
		
	}
	function getResult(currPage) {
		var daType=document.getElementById("daType").value;
		window.location.href("../main/search?pageNum="+currPage+"&daType="+daType+"&flag=Y&lbh=1");
	
	}
	
	function inits(currPage,total,size) {
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
		document.getElementById("sizegong").innerText=size;
	}	
	
	
</script>
</head>

<body onload="inits(${pageNum},${total},${recordsSize})">
 <input id="daType" type="text" name="daType" value="${daType }" style="display: none;"/>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" background="../images/main_17.gif"><br>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="../images/main_24.gif" width="97" height="11"></td>
        </tr>
      </table>
      <br>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="5"><img src="../images/main_11.gif" width="5" height="5"></td>
         <td background="../images/main_12.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td width="5"><img src="../images/main_14.gif" width="5" height="5"></td>
       </tr>
       <tr>
         <td background="../images/main_19.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td align="center" class="td01">
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="25"><img src="../images/main_28.gif" width="9" height="9" align="middle"> <strong>搜索结果</strong></td>
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
           <table id = "result_table" class="sortable" width="95%" border="0" cellspacing="0" cellpadding="0" style="border: 1px">
				<tr>
					<th>序号</th>
					<th>档案号</th>
					<th>分类号</th>
					<th>目录号</th>
					<th>案卷号</th>
					<th>支号</th>
					<th>登记类别</th>
					<th>土地使用者</th>
					<th>土地坐落</th>
					<th>土地证号</th>
					<th>宗地号</th>
					<th>影像信息</th>
					<th>属性信息</th>
				</tr>
			 <tbody>
				<s:iterator id="d" value="records" status="rec" var="record">
					
					<s:if test='dalb=="zhwsl" && #record.name101=="wjj"'>
						<tr ondblclick="showTif('${record.name1}-${record.name83}-${record.name75}-${record.name80}','000001','${view}','${dalb }')">
					</s:if>
					<s:else>
						<tr ondblclick="showTif('${record.name1}-${record.name2}-${record.name4}-${record.name3}','000001','${view}','${dalb }')">
					</s:else>
					
					<!-- <tr ondblclick="showTif('${record.name1}-${record.name2}-${record.name4}-${record.name3}','000001','${view}','${dalb }')"> -->
						<td id="result" style="border: 1px solid black; text-align: center;"><s:property value="#rec.index+1"/></td>
			
						<s:if test='dalb=="zhwsl" && #record.name101=="wjj"'>
							<td id="result" style="border: 1px solid black; text-align: center;">${record.name1}-${record.name83}-${record.name75}-${record.name80}</td>
						</s:if>
						<s:else>
							<td id="result" style="border: 1px solid black; text-align: center;">${record.name1}-${record.name2}-${record.name4}-${record.name3}</td>
						</s:else>
						
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name4}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name2}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name3}&nbsp;</td>
						
						
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name79}&nbsp;</td> <!--  支号 -->
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name7}&nbsp;</td><!--等级类别-->
						
						
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name56}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name58}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name60}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${record.name59}&nbsp;</td>
						
						<!-- 综合文书类 -->
						<s:if test='dalb=="zhwsl" && #record.name101=="wjj"'>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="showTif('${record.name1}-${record.name83}-${record.name75}-${record.name80}','000001','${view}','${dalb }')">查看影像</a></td>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="window.open('showDjywAllInfo.action?dah=${record.name1}-${record.name83}-${record.name75}-${record.name80}')">查看属性</a></td>
						</s:if>
						
						<!-- 建设用地类 -->
						<s:if test='dalb=="jsydl"'>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="showTif('${record.name1}-${record.name2}-${record.name4}-${record.name3}','000001','${view}','${dalb }')">查看影像</a></td>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="window.open('showJsydlAllInfo.action?dah=${record.name1}-${record.name2}-${record.name4}-${record.name3}')">查看属性</a></td>
						</s:if>
						
						<!-- 地籍业务类 -->
						<s:else>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="showTif('${record.name1}-${record.name2}-${record.name4}-${record.name3}','000001','${view}','${dalb }')">查看影像</a></td>
							<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="window.open('showDjywAllInfo.action?dah=${record.name1}-${record.name2}-${record.name4}-${record.name3}')">查看属性</a></td>
						</s:else>
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
			  		<input id="last" type="button" value=" 上一页 " onclick="getResults(${pageNum},-1)"/>
			  		<img src="../images/spacer.gif" width="50" height="1"/>
			  		<input id="next" type="button" value=" 下一页 " onclick="getResults(${pageNum},1)"/>
			  		<img src="../images/spacer.gif" width="50" height="1"/>
			  		<em style="font-size:15px;">第&nbsp;
			  			<em id="di">
			  				<select name="pageNum" id="page" onchange="getResult(this.options[this.options.selectedIndex].value)">
			  				<s:iterator value="totals" var="to">
			  					<option value="${to}" id="di_${to}">${to}</option>
			  				</s:iterator>
			  				</select>
			  			</em>页/共&nbsp;
			  			<em id="gong">1</em>页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 共<em id="sizegong">1</em>条
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

