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
	function showTif(id,dangAnHao,pageNum,tmId,view,dalb){
		if(view=="Y") {
			window.open("showTiff.action?djywId="+id+"&dangAnHao="+dangAnHao+"&pageNum="+pageNum+"&tmId="+tmId+"&dalb="+dalb);
		}else {
			alert("对不起， 您没有权限浏览档案！");
		}
		
	}
	
	function getResults(currPage,pageNum) {
		currPage = currPage+pageNum;
		window.location.href("../main/superSearch?pageNum="+currPage+"&flag=Y");
		
	}
	function getResult(currPage) {
		window.location.href("../main/superSearch?pageNum="+currPage+"&flag=Y");
	
	}
	
	function inits(currPage,total) {
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

<body onload="inits(${pageNum},${total})">
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
					<th>土地使用者</th>
					<th>土地坐落</th>
					<th>土地证号</th>
					<th>保管期限</th>
					<th>详细</th>
				</tr>
			 <tbody>
				<s:iterator id="d" value="djyws" status="djyw">
					<tr ondblclick="showTif('${id}','${qzh}-${mlh}-${flh}-${ajh}','000001','ds10','${view}','djyw')">
						<td id="result" style="border: 1px solid black; text-align: center;"><s:property value="#djyw.index+1"/></td>
						<td id="result" style="border: 1px solid black; text-align: center;">${qzh}-${mlh}-${flh}-${ajh}</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${tdsyz}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${tdzl}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${tdzh}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;">${synx}&nbsp;</td>
						<td id="result" style="border: 1px solid black; text-align: center;"><a href="#" onclick="showTif('${id}','${qzh}-${mlh}-${flh}-${ajh}','000001','ds10','${view}','djyw')">点击查看</a></td>
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
			  			<em id="gong">1</em>页 
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
    <td width="1" valign="top" background="../images/main_41.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
    <td width="180" align="center" valign="top" background="../images/main_17.gif"><br>
      <table width="163" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25"><img src="../images/main_30.gif" width="9" height="9" align="middle"> <strong>个人信息</strong></td>
        </tr>
      </table>
          <table width="163" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="5"><img src="../images/main_65.gif" width="5" height="5"></td>
              <td background="../images/main_66.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
              <td width="5"><img src="../images/main_69.gif" width="5" height="5"></td>
            </tr>
            <tr>
              <td background="../images/main_70.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
              <td class="td06"><table width="100%" border="0" cellspacing="4" cellpadding="0">
                  <tr>
                    <td><s:property value="#session['user'].userName"/>&nbsp; 先生/女士，您好！<br>
                      欢迎登录档案管理系统<br>
                      <br>
                      上次登录时间为：<br>
                      <span class="F01"><s:property value="#session['user'].lastlogin"/>&nbsp;</span><br>
                      </td>
                  </tr>
              </table></td>
              <td background="../images/main_72.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
            </tr>
            <tr>
              <td><img src="../images/main_80.gif" width="5" height="5"></td>
              <td background="../images/main_81.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
              <td><img src="../images/main_82.gif" width="5" height="5"></td>
            </tr>
          </table>
          <br>
          <table width="163" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="25"><img src="../images/main_61.gif" width="9" height="9" align="middle"> <strong>日历</strong></td>
            </tr>
            <tr>
              <td><table border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
                  <tr>
                    <td bgcolor="#FFFFFF"><script language="JavaScript">
<!-- Begin
var now = new Date();
var month_array = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
document.write("<form name=date_list><table bgcolor=white border=0><tr><td>");
document.write("<select name=month onchange=change_month(this.options.selectedIndex)>");
for(i=0;i<month_array.length;i++)
{
 if (now.getMonth() != i)
 {document.write ("<option value="+i+">"+month_array[i]);}
 else
 {document.write ("<option value="+i+" selected>"+month_array[i]);}

}
document.write("</select>");
document.write("</td><td>");
document.write ("<select name=year onchange=change_year(this.options[this.options.selectedIndex])>");
for(i=1950;i<3000;i++)
{
 if (now.getYear() != i)
 {document.write("<option value="+i+">"+i);}
 else
 {document.write("<option value="+i+" selected>"+i);}
}
document.write("</select></td></tr><tr><td colspan=2><center>");

document.write("<table bgcolor=white border=0 cellspacing = 1 cellpading = 0 width=100%><tr bgcolor=666666 align=center>");
document.write("<td heigh=25 width=19 class=F02>M</td><td width=19 class=F02>T</td><td width=19 class=F02>W</td><td width=19 class=F02>T</td><td width=19 class=F02>F</td><td width=19 class=F03>S</td><td width=19 class=F03>S</td>");
document.write("</tr><tr>");
for(j=0;j<6;j++)
{
 for(i=0;i<7;i++)
 {
   document.write("<td bgcolor=EFEFEF align=center id=d"+i+"r"+j+"></td>")
 }
 document.write("</tr>");
}

document.write("</table>");

document.write("</center></from></td></tr></table>");

var show_date = new Date();

function set_cal(show_date)
{
begin_day = new Date (show_date.getYear(),show_date.getMonth(),1);
begin_day_date = begin_day.getDay();
end_day = new Date (show_date.getYear(),show_date.getMonth()+1,1);
count_day = (end_day - begin_day)/1000/60/60/24;
input_table(begin_day_date,count_day);
}
set_cal(show_date);

function input_table(begin,count)
{
init();
j=0;
if (begin!=0){i=begin-1;}else{i=6}
for (c=1;c<count+1;c++)
{
 colum_name = eval("d"+i+"r"+j);
 if ((now.getDate() == c)&&(show_date.getMonth() == now.getMonth())&&(show_date.getYear() == now.getYear())) {colum_name.style.backgroundColor = "FF7700";colum_name.style.color = "white";};
 colum_name.innerText =  c;
 i++;
 if (i==7){i=0;j++;}
}
}

function init()
{
for(j=0;j<6;j++)
{
 for(i=0;i<7;i++)
 {
 colum_name = eval("d"+i+"r"+j);
 colum_name.innerText =  " ";
 colum_name.style.backgroundColor ="";
 colum_name.style.color ="";
 }
}
}

function change_month(sel_month)
{
show_date = new Date(show_date.getYear(),sel_month,1);
set_cal(show_date);
}

function change_year(sel_year)
{
sel_year = sel_year.value;
show_date = new Date(sel_year,show_date.getMonth(),1);
set_cal(show_date);
}
//  End -->
          </script></td>
                  </tr>
              </table></td>
            </tr>
          </table>  
          <br>
          <br>
          <table width="163" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="1" background="../images/main_57.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
            </tr>
          </table>
          </td>
  </tr>
</table>
</body>
</html>

