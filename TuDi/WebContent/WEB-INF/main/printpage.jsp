<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>	
	<style>
		BODY {font-size: 12px;}	
		img{border: none;}	
		TABLE {font-size: 12px;}	
		A:link {TEXT-DECORATION: none}	
		A:visited {TEXT-DECORATION: none}
		A:active {TEXT-DECORATION: none}	
		A:hover {TEXT-DECORATION: underline}	
	</style>
	<style media="print">
	.noprint {display: none;}
	</style>	
	<meta http-equiv="content-type" content="text/html; charset=utf-8">	
<body bgcolor=#ffffff topmargin=5 leftmargin=5 marginheight=5 marginwidth=5>	
	<center>
		<s:iterator value="prints" var="print">
		<div style='width: 100%; height: 100%;' >
				<img id="target" src="water?type=P&dangAnHao=${print.dangAnHao}&pageNum=${print.pageNum}&tmId=${print.tmId}&dalb=${dalb}&hbFlag=Y"/>
		</div>
		<br/>
		</s:iterator>		
	</center>
</body>
</html>