<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />

<SCRIPT type="text/javascript">

	function refresh(lbh) {
		if(lbh==0) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==1) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==2) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==3) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==4) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==5) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","Y");
			showCondition("bpgdw_line","Y");
			showCondition("gjbgbh_line","Y");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}else if(lbh==6) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","Y");
			showCondition("dyzsh_line","Y");
		}else if(lbh==7) {
			showCondition("dah_line","Y");
			showCondition("tdsyz_line","Y");
			showCondition("tdzl_line","Y");
			showCondition("zdh_line","Y");
			showCondition("tdzh_line","Y");
			showCondition("wtdw_line","N");
			showCondition("bpgdw_line","N");
			showCondition("gjbgbh_line","N");
			showCondition("sqr_line","N");
			showCondition("dyzsh_line","N");
		}
	}

	function showCondition(id,flag) {
		if(flag=='Y') {
			document.getElementById(id).style.display = 'inline';
		}else {
			document.getElementById(id).style.display = 'none';
		}
		
	}

	function getAll() {
		var lbh = document.getElementById("lb_select").value;
		window.location.href("../main/all?lbh="+lbh);
	}


</SCRIPT>

</head>

<body>
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
               <td height="25"><img src="../images/main_28.gif" width="9" height="9" align="absmiddle"> <strong>检索条件</strong></td>
               <td width="35" align="right"><a href="javascript: getAll();">全部</a></td>
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
           <s:form method="post" action="superSearch" theme="simple" namespace="/main">
		   <s:token></s:token>
		   <table width="70%" border="0" cellspacing="0" cellpadding="0">
			  <tr id="dah_line" style="display: inline;" height="50">
				<td class="td1" align="right">档案号:</td>
				<td class="td2" align="center"><input type="text" name="dah" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="dahm"/>模糊查询</td>
			  </tr>				
			  <tr id="tdsyz_line" style="display: inline;" height="50">
				<td class="td1" align="right">土地使用者:</td>
				<td class="td2" align="center"><input type="text" name="tdsyz" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="tdsyzm"/>模糊查询</td>
			  </tr>	
			  <tr id="tdzl_line" style="display: inline;" height="50">
				<td class="td1" align="right">土地坐落:</td>
				<td class="td2" align="center"><input type="text" name="tdzl" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="tdzlm"/>模糊查询</td>
			  </tr>
			  <tr id="zdh_line" style="display: inline;" height="50">
				<td class="td1" align="right">宗地号:</td>
				<td class="td2" align="center"><input type="text" name="zdh" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="zdhm"/>模糊查询</td>
			  </tr>
		
			  <tr id="tdzh_line" style="display: inline;" height="50">
			    <td class="td1" align="right">土地证号:</td>
				<td class="td2" align="center"><input type="text" name="tdzh" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="tdzhm"/>模糊查询</td>
			  </tr>
			  <tr id="wtdw_line" style="display: none" height="50">
				<td class="td1" align="right">委托单位:</td>
				<td class="td2" align="center"><input type="text" name="wtdw" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="wtdwm"/>模糊查询</td>
			  </tr>
			  <tr id="bpgdw_line" style="display: none" height="50">
				<td class="td1" align="right">被评估单位:</td>
				<td class="td2" align="center"><input type="text" name="bpgdw" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="bpgdwm"/>模糊查询</td>
			  </tr>
			  <tr id="gjbgbh_line" style="display: none;" height="50">
				<td class="td1" align="right">估价报告编号:</td>
				<td class="td2" align="center"><input type="text" name="gjbgbh" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="gjbgbhm"/>模糊查询</td>
			  </tr>
			   <tr id="sqr_line" style="display: none;" height="50">
				<td class="td1" align="right">申请人:</td>
				<td class="td2" align="center"><input type="text" name="sqr" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="sqrm"/>模糊查询</td>
			  </tr>
			   <tr id="dyzsh_line" style="display: none;" height="50">
				<td class="td1" align="right">抵押证书号:</td>
				<td class="td2" align="center"><input type="text" name="dyzsh" size="25"/></td>
				<td class="td3" align="left"><input type="checkbox" name="dyzshm"/>模糊查询</td>
			  </tr>
			  <tr>
			  	<td><img src="../images/spacer.gif" width="1" height="5"/></td>
			  </tr>
			  <tr>
			  	<td class="td1" align="right">类别:</td>
			  	<td class="td2" align="center">
			  		<select id="lb_select" name="lbh" onchange="refresh(this.options[this.options.selectedIndex].value)">
			  			<option value="0">全部</option>
			  			<option value="1">集体土地建设用地使用权初始登记</option>
			  			<option value="2">国有土地使用权初始登记</option>
			  			<option value="3">变更土地登记</option>
			  			<option value="4">集体土地所有权登记</option>
			  			<option value="5">土地评估</option>
			  			<option value="6">抵押注销登记</option>
			  			<option value="7">地籍注销</option>
			  		</select> 
			  	</td>
			  	<td></td>
			  </tr>
			  <tr>
			  	<td><img src="../images/spacer.gif" width="1" height="15"/></td>
			  </tr>
			  <tr>
			  	<td></td>
			  	<td align="center"><input type="submit" value=" 搜索 "/><img src="../images/spacer.gif" width="50" height="1"/><input type="reset" value=" 清空 "/></td>
			  	<td align="left"></td>
			  	
			  </tr>
		   </table> 
		   <br>
		   
		   </s:form>
           <br>
		</td>
        <td background="../images/main_21.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
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
    <td width="190" align="center" valign="top" background="../images/main_17.gif"><br>
      <table width="163" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25"><img src="../images/main_30.gif" width="9" height="9" align="absmiddle"> <strong>个人信息</strong></td>
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
              <td height="25"><img src="../images/main_61.gif" width="9" height="9" align="absmiddle"> <strong>日历</strong></td>
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

