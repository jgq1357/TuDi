<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>统计报表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/bb.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="../js/date.js"></script>
<script type="text/javascript">

	function init(id) {
		document.getElementById(id).checked=true;
	}
	
	
</script>

</head>
<body bgcolor="#ECF5FF" onload="init('${timeType }');">
<div align="center" style="width: 100%; height: 45%;top: 2%;left: 2%;border: 1px solid #ccc;">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr bgcolor="#2894FF" >
			<td> 时间 </td>
			<td>(A)综合类</td>
			<td>(B)财务类</td>
			<td>(C)地籍类</td>
			<td>(D)利用规划类</td>
			<td>(E)建设用地类</td>
			<td>(F)资源监察类</td>
			<td>(G)科技信息类</td>
			<td>(H)电子声像类</td>
			<td>(I)地质矿产类</td>
		</tr>
		<s:iterator value="tjbbs" var="tjbb">
			<tr>
				<td align="center">${tjbb.date }</td>
				<td align="center">${tjbb.zhl }</td>
				<td align="center">${tjbb.cwl }</td>
				<td align="center">${tjbb.djl }</td>
				<td align="center">${tjbb.lyghl}</td>
				<td align="center">${tjbb.jsydl }</td>
				<td align="center">${tjbb.zyjcl }</td>
				<td align="center">${tjbb.kjxxl }</td>
				<td align="center">${tjbb.dzsxl }</td>
				<td align="center">${tjbb.dzgcl }</td>
				
			</tr>
		</s:iterator>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>
<br>
<div align="center" style="border: 1px solid #ccc; left: 2%;height: 45%;">
<s:form action="jytj" namespace="/tjbb">
<s:token></s:token>
	<table>
	<tr>
	<td align="left">
	<div style="border: 1px solid #ccc;">
		
		<table>
			<tr align="left" bgcolor="#66B3FF"><td>统计时间</td> </tr>
			<tr>
			  <td>
				<input type="radio" name="timeType" id="t1" value="year"> <font size="1">按年统计</font>
				<input type="radio" name="timeType" id="t2" value="month"> <font size="1">按月统计</font>
				<input type="radio" name="timeType" id="t3" value="day"> <font size="1">按日统计</font>
			  </td> 
			 </tr>
			<tr><td>起始日期&nbsp;&nbsp;   <input id="start" name="startDate" value="${startDate}" readonly="readonly" type="text" onFocus="CalendarWebControl.show(this,false,this.value);" style="z-index: 6"> </td> </tr>
			<tr><td>终止日期&nbsp;&nbsp;  <input id="end" name="endDate" value="${endDate}" readonly="readonly" type="text" onFocus="CalendarWebControl.show(this,false,this.value);" style="z-index: 6"> </td> </tr>
			<tr><td><input name="tjlb" value="${tjlb}" style="display: none;" readonly="readonly"></td> </tr>
			<tr><td align="center"> <input id="sub" name="sub", type="submit" value="开始统计"> </td>  </tr>
		</table>
		
	</div>
	</td>
	<td align="center">
	<div>
	<ul id="q-graph">
		<li id="q1" class="qtr">
			<ul>
				<s:if test="A.value!=0">
				<li class="north bar" style="height:${A.px}px;">${A.value}</li>
				</s:if>
				<s:if test="B.value!=0">
				<li class="south bar" style="height:${B.px}px;">${B.value}</li>
				</s:if>
			</ul>
		</li>
		<li id="q2" class="qtr">
			<ul>
				<s:if test="C.value!=0">
				<li class="north bar" style="height:${C.px}px;">${C.value}</li>
				</s:if>
				<s:if test="D.value!=0">
				<li class="south bar" style="height:${D.px}px;">${D.value}</li>
				</s:if>
			</ul>
		</li>
		<li id="q3" class="qtr">
			<ul>
				<s:if test="E.value!=0">
				<li class="north bar" style="height:${E.px}px;">${E.value}</li>
				</s:if>
				<s:if test="F.value!=0">
				<li class="south bar" style="height:${F.px}px;">${F.value}</li>
				</s:if>
			</ul>
		</li>
		<li id="q4" class="qtr">
			<ul>
				<s:if test="G.value!=0">
				<li class="north bar" style="height:${G.px}px;">${G.value}</li>
				</s:if>
				<s:if test="H.value!=0">
				<li class="south bar" style="height:${H.px}px;">${H.value}</li>
				</s:if>
			</ul>
		</li>
		<li id="q5" class="qtr">
			<ul>
				<s:if test="I.value!=0">
				<li class="north bar" style="height:${I.px}px;">${I.value}</li>
				</s:if>
			</ul>
		</li>
		<li id="ticks">
			<s:iterator value="z_index" var="z">
				<div class="ticks"><p>${z}</p></div>
			</s:iterator>
		</li>
		<li style="left: 45px;z-index: 3;"><font size="5">A</font></li>
		<li style="left: 90px;z-index: 3;"><font size="5">B</font></li>
		<li style="left: 145px;z-index: 3;"><font size="5">C</font></li>
		<li style="left: 190px;z-index: 3;"><font size="5">D</font></li>
		<li style="left: 245px;z-index: 3;"><font size="5">E</font></li>
		<li style="left: 290px;z-index: 3;"><font size="5">F</font></li>
		<li style="left: 345px;z-index: 3;"><font size="5">G</font></li>
		<li style="left: 390px;z-index: 3;"><font size="5">H</font></li>
		<li style="left: 450px;z-index: 3;"><font size="5">I</font></li>
	</ul>
	</div>
	</td>
	</tr>
	</table>
	</s:form>
</div>
</body>
</html>
