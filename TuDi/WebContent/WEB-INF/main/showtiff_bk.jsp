<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>国土资源局档案数字化管理系统</title>
	<link href="../css/tif.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
	<script type="text/javascript" src="../js/prototype1.6.js"></script>
	<script type="text/javascript">
	var g_dangAnHao;
	var g_tmId;
	var g_pageNum=1;
	var g_next;
	var g_total;
	var g_dalb;
	var url="waterJson!countFileNum.action?t="+new Date().getTime();
	

	function getTotal(dangAnHao, tmId,next,tmTotal) {
		new Ajax.Request(
				url,
				{	method:"post",
					parameters:{"dangAnHao":dangAnHao,"tmId":tmId,"dalb":g_dalb},
					onSuccess:function(req){
						var json = req.responseText.evalJSON();
						g_total = json.total;
						var tar = document.getElementById("xia");
						if(g_total==0) {
							alert("该模块没有影像！！！");
						}
						if(g_total>1) {
							tar.disabled=false;
						}else {
							tar.disabled=true;
						}
						document.getElementById("gong").innerText=g_total;
						document.getElementById("di").innerText=1;
						$('dj'+next).style.background = '#aceadd';
						var index = 0;	
						while(index < tmTotal) {
								if(index!=next) {
								$('dj'+index).style.background = '#eceadd';
								}
							index = index+1;
						}
						
					}
				}
				);
			g_tmId = tmId;	
	}
	
	function getImage1(dangAnHao, tmId, pageNum, next,tmTotal) {
		
		g_dangAnHao = dangAnHao;
		g_tmId = tmId;
		g_pageNum = pageNum;

		var tar = document.getElementById("target");
		tar.src = "water?dangAnHao="+dangAnHao+"&pageNum="+pageNum+"&tmId="+tmId+"&dalb="+g_dalb;
		document.getElementById("shang").disabled=true;
		document.getElementById("first").disabled=true;
		document.getElementById("xia").disabled = false;
		document.getElementById("last").disabled = false;

		
		
		getTotal(g_dangAnHao,g_tmId,next,tmTotal);
		
	}		
	function getImage(dangAnHao){
		
		var tar = document.getElementById("target");
		
	
			
			g_pageNum = g_pageNum+1;
			
	         tar.src = "water?dangAnHao="+dangAnHao+"&pageNum="+g_pageNum+"&tmId="+g_tmId+"&dalb="+g_dalb;
		
		if(g_pageNum>1) {
			var tar = document.getElementById("shang");
			tar.disabled=false;
			tar = document.getElementById("first");
			tar.disabled=false;
		}

		if(g_pageNum>=g_total) {
			var tar = document.getElementById("xia");
			tar.disabled = true;
			tar = document.getElementById("last");
			tar.disabled = true;
		}
		document.getElementById("di").innerText=g_pageNum;
	}
	function getImage2(dangAnHao){
		
		var tar = document.getElementById("target");
		
		if(g_tmId=='' ||g_tmId == null){
			g_tmId = 'ds10';
			g_pageNum=1;
			tar.src = "water?dangAnHao="+dangAnHao+"&pageNum="+g_pageNum+"&tmId="+g_tmId+"&dalb="+g_dalb;
			
		}else{
			var tar = document.getElementById("target");
			g_pageNum = g_pageNum-1;
			
	         tar.src = "water?dangAnHao="+dangAnHao+"&pageNum="+g_pageNum+"&tmId="+g_tmId+"&dalb="+g_dalb;
	         
		}
		if(g_pageNum<=1) {
			var tar = document.getElementById("shang");
			tar.disabled=true;
			tar = document.getElementById("first");
			tar.disabled=true;
		}

		if(g_pageNum<g_total) {
			var tar = document.getElementById("xia");
			tar.disabled = false;
			tar = document.getElementById("last");
			tar.disabled = false;
		}
		document.getElementById("di").innerText=g_pageNum;
	}
	
	function getImageByPageNum(dangAnHao,pageNum) {
		g_pageNum = pageNum;
		if(g_pageNum==-1) {
			g_pageNum=g_total;
		}
		var tar = document.getElementById("target");
		tar.src = "water?dangAnHao="+dangAnHao+"&pageNum="+g_pageNum+"&tmId="+g_tmId+"&dalb="+g_dalb;
		if(g_pageNum<=1){
			document.getElementById("shang").disabled=true;
			document.getElementById("first").disabled=true;
			document.getElementById("xia").disabled = false;
			document.getElementById("last").disabled = false;
		} else if(g_pageNum>=g_total) {
			document.getElementById("xia").disabled = true;
			document.getElementById("last").disabled = true;
			document.getElementById("shang").disabled = false;
			document.getElementById("first").disabled = false;
		} else {
			document.getElementById("shang").disabled = false;
			document.getElementById("xia").disabled = false;
			document.getElementById("first").disabled = false;
			document.getElementById("last").disabled = false;
		}
		document.getElementById("di").innerText=g_pageNum;
	}

	function init(dalb) {
		g_dalb = dalb;
		getTotal("${dangAnHao}","${tmId}",0,0);
	}


	function doPrint(){
		var bdhtml=window.document.body.innerHTML;
		var WebBrowser = '<OBJECT ID="printwb" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
		var str="<html>";	
		str +=	'<style>';	
		str +=	'BODY {font-size: 12px;}';	
		str +=	'img{border: none;}';	
		str +=	'TABLE {font-size: 12px;}';	
		str +=	'A:link {TEXT-DECORATION: none}';	
		str +=	'A:visited {TEXT-DECORATION: none}';	
		str +=	'A:active {TEXT-DECORATION: none}';	
		str +=	'A:hover {TEXT-DECORATION: underline}';	
		str +=	'</style>';	
		str +=	'<style media="print">';	
		str +=	'.noprint {display: none;}';	
		str +=	'</style>';	
		str +=	'<meta http-equiv="content-type" content="text/html; charset=utf-8">';	
		str +=	"<body bgcolor=#ffffff topmargin=5 leftmargin=5 marginheight=5 marginwidth=5>";	
		str +=	"<center><table width=600 border=0 cellpadding=0 cellspacing=20 ><tr><td>";
		var obj = document.getElementsByTagName("p");
		for(i=0;i<obj.length;i++) {
			str += "<div style='width: 100%; height: 100%;' >";
			str += '<img id="target" src="';
			str += obj[i].innerText;	
			str += '&hbFlag=Y" width="100%"/></div><br/>';	
		}
		
		str +=	"</td></tr></table></center>";	
		str += WebBrowser; str +=	"</body></html>"; 
		window.document.body.innerHTML=str;
		
}
	</script>
	</head>
	<body onload="init('${dalb}')">
		
		<div id="tif_page" style="width: 98%">
			<div style="width: 15%; height: 80%; position: absolute; overflow:auto;">
				<table width="100%">
					<s:iterator value="djljs" status="djlj">
						<tr style="height: 15%;">
							<td id="dj${id}" style="background: #eceadd"><a href="#" onclick="getImage1('${dangAnHao}','${lj}',${pageNum},${id},${tmAmount})" >${tm}</a></td>
						</tr>
					</s:iterator>
				</table>
				<input id="button" type="button" value="显示档案属性" onclick="window.location.href='showAllInfo.action?djywId=${djywId}'"></input>
				<br/>
				<input id="button" type="button" value="打印" onclick="doPrint()"></input>
			</div >
			<!--PRINT_BEGIN-->
			<div id="tif_container" style="width:82%;height:80%; overflow:auto; position:absolute; margin-left: 15%">
					
						<img id="target" src="water?dangAnHao=${dangAnHao}&pageNum=1&tmId=${tmId}&dalb=${dalb}" width="100%" />
					
				
			</div>
			<!--PRINT_END-->
			<br />
			<div style="display: none;">
				<s:iterator value="prints" var="print">
					<p id="">water?dangAnHao=${print.dangAnHao}&pageNum=${print.pageNum}&tmId=${print.tmId}&dalb=${dalb}</p>
				</s:iterator>
			</div>
			
			<div id="pageButton" style="width: 80%; height: 4%; top: 95%; position: absolute; margin-left: 15%">
						<input id="first" type="button" value="首 页" onclick="getImageByPageNum('${dangAnHao}',1)" disabled="disabled" style="position: absolute; margin-left: 30%"/>
						<input id="shang" type="button" value="上一页" onclick="getImage2('${dangAnHao}')" disabled="disabled" style="position: absolute; margin-left: 40%"/>
						<input id="xia" type="button" value="下一页" onclick="getImage('${dangAnHao}')" style="position:absolute; margin-left: 50%" />
						<input id="last" type="button" value="尾 页" onclick="getImageByPageNum('${dangAnHao}',-1)" style="position:absolute; margin-left: 60%" />
						<em style="position:absolute; right:8%;font-size:15px;">第&nbsp;<em id="di">0</em>页/共&nbsp;<em id="gong">0</em>页 </em>
			</div>
		</div>
		
	</body>
</html>