<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>国土资源见擦档案属性</title>
<script type="text/javascript" src="../js/prototype1.6.js">
	
</script>
<script type="text/javascript">

function printGtzyjcl(qzh,mlh,flh,ajh)
{
	var tmp=qzh+'-'+mlh+'-'+flh+'-'+ajh;

	var param = "dah="+tmp;

	if(window.document.getElementById('printLJ').checked)
	{
		param = param + "&ljxx=Y";
	}
	
	if(window.document.getElementById('printJB').checked )
	{
		param = param + "&jbxx=Y";
	}

	
	window.open("printInFo_DJYW.action?"+param);
}

	function showTif(id, dangAnHao, pageNum, tmId) {
		window.open("showTiff.action?dalb=djyw&djywId=" + id + "&dangAnHao=" + dangAnHao
				+ "&pageNum=" + pageNum);// + "&tmId=" + tmId);
		System.out.println("showTiff.action?djywId=" + id + "&dangAnHao=" + dangAnHao
				+ "&pageNum=" + pageNum);
	}
	function action(index){
		$('m' + index + '_content').style.display = 'block';
		$('bt' + index).style.background = '#aceadd';
		if (index == 1) {
			$('m2_content').style.display = 'none';
			$('m3_content').style.display = 'none';
			$('bt2').style.background = '#eceadd';
			$('bt3').style.background = '#eceadd';
		} else if (index == 2) {
			$('m1_content').style.display = 'none';
			$('m3_content').style.display = 'none';
			$('bt1').style.background = '#eceadd';
			$('bt3').style.background = '#eceadd';
		} else if (index == 3) {
			$('m1_content').style.display = 'none';
			$('m2_content').style.display = 'none';
			$('bt2').style.background = '#eceadd';
			$('bt1').style.background = '#eceadd';
		}
	}
</script>

</head>
<body bgcolor="#ECF5FF">
	<br></br>
	<center><font size="3"><strong>立卷信息</strong></font></center>
	<table width="70%" border="1" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; " cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="center"><font	size="2" color="#004B97"><strong>全宗号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.qzh}</td>
			<td align="center"><font	size="2" color="#004B97"><strong>目录号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.mlh}</td>
			<td align="center"><font	size="2" color="#004B97"><strong>案卷号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.ajh}</td>
		</tr>
	</table><br />
	<center><font size="3"><strong>基本情况</strong>
	
	
	</font></center>
	<table border="1" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; " width="70%" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>年度</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.nd}</td>
			<td align="right"><font size="2" color="#004B97"><strong>违法单位</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.wfdw }</td>
		</tr>
		<tr>	
			<td id="address" align="right"><font size="2" color="#004B97"><strong>违法面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.wfmj }</td>
			<td align="right"><font	size="2" color="#004B97"><strong>土地坐落</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.tdzl}</td>
		</tr>
		<tr>	
			<td id="address" align="right"><font size="2" color="#004B97"><strong>办案机关</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.bajg }</td>
			<td align="right"><font	size="2" color="#004B97"><strong>结案时间</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.jasj}</td>
		</tr>
		<tr>	
			<td id="address" align="right"><font size="2" color="#004B97" ><strong>案由</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">&nbsp;&nbsp;${gtzyjcl.ay }</td>
		</tr>
		</table>
		
		<center><font size="3"><strong><input type="button" value="打印" onclick="printGtzyjcl('${gtzyjcl.qzh}','${gtzyjcl.mlh}','${gtzyjcl.flh}','${gtzyjcl.ajh}')" style="background: #eceadd"/></strong></font><br /><br /></center>

</body>
</html>